package com.inspur.bigdata.hbaseio.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inspur.bigdata.hbaseio.exceptions.RowkeyGenerateException;
import com.inspur.bigdata.hbaseio.model.HBaseClient;
import com.inspur.bigdata.hbaseio.model.OpenTSDB;
import com.inspur.bigdata.hbaseio.pojo.DataPoint;
import com.inspur.bigdata.hbaseio.pojo.Metric;
import com.inspur.bigdata.hbaseio.pojo.MetricTarget;
import com.inspur.bigdata.hbaseio.pojo.Stage;
import com.inspur.bigdata.hbaseio.pojo.TimeSeriesData;

/**
 * 写入监控数据的Dao， TimeSeries数据对应的数据库有3张表，包括具体的数据表，meta表，counter表
 * 设计思路参考《OpenTBS的设计之道》
 * 
 * @author maolh
 * @date 20160126
 */
public class TimeSeriesDao {

	private static final Logger logger = LoggerFactory.getLogger(TimeSeriesDao.class);
	private static OpenTSDB tsdb = new OpenTSDB();

	/**
	 * 保存多条数据到hbase中
	 * 
	 * @param points
	 * @throws IOException
	 */
//	public static boolean saveDataPoints(List<DataPoint> points) {
//		try {
//			HBaseClient client = HBaseClient.getInstance();
//			HTable dataTable = client.openTable(tsdb.TSDB_DATA_TABLENAME);
//			List<Put> puts = new ArrayList<Put>();
//			for (DataPoint point : points) {
//				byte[] key = OpenTSDB.generateKey(point);
//				logger.info(
//						"插入一条数据:" + point.toString() + ",key:" + Arrays.toString(key) + ",value:" + point.getValue());
//				// 根据时间戳和value的值，生成qualifier
//				byte[] qualifier = OpenTSDB.generateQualifier(point);
//				Put put = new Put(key);
//				Number value = point.getValue();
//				if (value instanceof Byte) {
//					byte[] b = new byte[] { (byte) value };
//					put.add(Bytes.toBytes(tsdb.getDefaultColumnFamily()), qualifier, b);
//				}
//				if (value instanceof Short) {
//					Short s = (Short) value;
//					put.add(Bytes.toBytes(tsdb.getDefaultColumnFamily()), qualifier, Bytes.toBytes(s));
//				}
//				if (value instanceof Integer) {
//					Integer i = (Integer) value;
//					put.add(Bytes.toBytes(tsdb.getDefaultColumnFamily()), qualifier, Bytes.toBytes(i));
//				}
//				if (value instanceof Long) {
//					Long l = (Long) value;
//					put.add(Bytes.toBytes(tsdb.getDefaultColumnFamily()), qualifier, Bytes.toBytes(l));
//				}
//				if (value instanceof Float) {
//					Float f = (Float) value;
//					put.add(Bytes.toBytes(tsdb.getDefaultColumnFamily()), qualifier, Bytes.toBytes(f));
//				}
//				if (value instanceof Double) {
//					Double d = (Double) value;
//					put.add(Bytes.toBytes(tsdb.getDefaultColumnFamily()), qualifier, Bytes.toBytes(d));
//				}
//				puts.add(put);
//			}
//			dataTable.put(puts);
//			dataTable.close();
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//			e.printStackTrace();
//		}
//		return true;
//	}

	/**
	 * 保存一条数据到hbase中
	 * 
	 * @param point
	 * @return
	 */
//	public static boolean saveDataPoint(DataPoint point) {
//		List<DataPoint> points = new ArrayList<DataPoint>();
//		if (point != null) {
//			points.add(point);
//			return saveDataPoints(points);
//		}
//		return false;
//	}

	/**
	 * 获取一条数据的所有时间序列对
	 * 
	 * @param point
	 * @return 返回的 List<TimeSeriesData>中已经按时间戳排序好
	 * @throws IOException
	 */
	public static List<TimeSeriesData> getTimeSeries(DataPoint point) throws IOException {
		try {
			byte[] key = tsdb.generateKey(point);
			return tsdb.getTimeSeries(key);
		} catch (RowkeyGenerateException e) {
			e.printStackTrace();
		}
		return new ArrayList<TimeSeriesData>();
	}

	/**
	 * 获取一个满足条件的数据的所有时间序列对
	 * 
	 * @param point
	 * @return 返回的 List<TimeSeriesData>中已按时间戳排序好
	 * @throws IOException
	 */
	public List<TimeSeriesData> getTimeSeries(Metric metric, Date date, MetricTarget mt, String targetId, Stage stage)
			throws IOException {
		// target_id_tagv_uid 可以为空
		if (metric == null || date == null || mt == null || stage == null) {
			return new ArrayList<TimeSeriesData>();
		}
		DataPoint point = new DataPoint();
		point.setMetric(metric);
		point.setTime(date);
		point.setMetricTarget(mt);
		point.setTargetId(targetId);
		point.setStage(stage);
		return getTimeSeries(point);
	}

	/**
	 * 获取最新的一条数据
	 * 
	 * @return
	 */
	public static TimeSeriesData getLastestTimeSeries(Metric metric, MetricTarget mt, String targetId, Stage stage) {
		try {
			Date date = new Date();
			TimeSeriesIterator iterator = getRange(metric, mt, targetId, stage, null, date, TimeUnit.SECONDS, true);
			if (iterator.hasNext()) {
				TimeSeriesData tsd = iterator.next();
				iterator.close();
				return tsd;
			}
		} catch (Exception e) {
			logger.equals("获取最近的一条数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过过滤器，选择出与某一行除时间外都相同的行
	 * 
	 * @param compRow
	 * @param filters
	 * @param reversed
	 *            是否逆向操作
	 * @return
	 * @throws IOException
	 */
	private static Result getByFiltersAndSameKey(byte[] compRow, FilterList filters, boolean reversed)
			throws IOException {
		HBaseClient client = HBaseClient.getInstance();
		ResultScanner rs = client.getByFilters(OpenTSDB.TSDB_DATA_TABLENAME, filters, reversed);
		Result result = null;
		// 找到各种属性能够匹配的key
		while ((result = rs.next()) != null) {
			if (OpenTSDB.matchWithNoTime(compRow, result.getRow())) {
				break;
			}
		}
		rs.close();
		return result;
	}

	/**
	 * 获取离特定时间最近的一条数据，可以指定是在该时间的左侧 ，还是右侧
	 * 
	 * @param point
	 * @param leftSide
	 *            如果当前时间有数据，则用当前时间的数据，否则，leftSide为真，会获取当前数据的左边的一条数据，
	 *            否则就获取当前数据的右边的一条数据
	 * @return
	 */
	public static TimeSeriesData getNearestTimeSeries(DataPoint point, boolean leftSide) {
		try {
			byte[] endKey = tsdb.generateKey(point);
			byte[] startKey = tsdb.getMetricUID(point.getMetric());
			//startKey[3]=startKey[4]=startKey[5]=startKey[6]=-128;
			RowFilter endRf = new RowFilter(CompareOp.LESS_OR_EQUAL, new BinaryComparator(endKey)); // OK
			RowFilter startRf = new RowFilter(CompareOp.GREATER_OR_EQUAL, new BinaryComparator(startKey));

			// 筛选出匹配的所有的行
			Result result = getByFiltersAndSameKey(endKey, new FilterList(startRf, endRf), true);
			if (result != null) {
				List<TimeSeriesData> list = tsdb.getTimeSeries(result.getRow());
				// 返回最后一条
				if (list.size() > 0) {
					TimeSeriesData lastSeriesData = list.get(list.size() - 1);
					// 最后一条
					if (lastSeriesData.getTime() < point.getTime()) {
						if (leftSide)
							return lastSeriesData;
						else {
							// 需要获取下一条的第一条数据
							endRf = new RowFilter(CompareOp.GREATER, new BinaryComparator(endKey)); // OK
							result = getByFiltersAndSameKey(endKey, new FilterList(endRf), false);
							if (result != null) {
								list = tsdb.getTimeSeries(result.getRow());
								if (list.size() > 0) {
									lastSeriesData = list.get(0);
									return lastSeriesData;
								}
							}
						}
					}
					// 第一条
					TimeSeriesData firstSeriesData = list.get(0);
					// 最后一条
					if (firstSeriesData.getTime() > point.getTime()) {
						if (leftSide == false)
							return lastSeriesData;
						else {
							// 需要获取上一条的最后一条数据
							endRf = new RowFilter(CompareOp.LESS, new BinaryComparator(endKey)); // OK
							result = getByFiltersAndSameKey(endKey, new FilterList(endRf), true);
							if (result != null) {
								list = tsdb.getTimeSeries(result.getRow());
								if (list.size() > 0) {
									lastSeriesData = list.get(list.size() - 1);
									return lastSeriesData;
								}
							}
						}
					}

					// 二分查找
					int idx = Collections.binarySearch(list, point.getData());
					if (idx >= 0) {
						// 存在
						return list.get(idx);
					} else {
						idx = -idx - 2;
						if (leftSide && idx < list.size())
							return list.get(idx);
						if (leftSide == false && idx + 1 < list.size())
							return list.get(idx + 1);
					}

				}
			}

		} catch (Exception e) {
			logger.equals("获取最近的一条数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取一段时间范围内的数据，默认按时间顺序返回，如果增加reversed参数，则按逆序返回
	 * 
	 * @param metric
	 * @param mt
	 * @param targetId
	 * @param stage
	 * @param startDate
	 * @param endDate
	 * @param tu
	 * @return
	 */
	public static TimeSeriesIterator getRange(Metric metric, MetricTarget mt, String targetId, Stage stage,
			Date startDate, Date endDate, TimeUnit tu) {
		return getRange(metric, mt, targetId, stage, startDate, endDate, tu, false);
	}

	/**
	 * 获取一段时间范围内的数据，默认按时间顺序返回，包含reversed参数，为真时，则按逆序返回
	 * 如果fromDate==null或endDate==null，就没有时间限制
	 * 
	 * @param metric
	 * @param mt
	 * @param targetId
	 * @param stage
	 * @param fromDate
	 * @param endDate
	 * @param tu
	 * @param reversed
	 *            逆序返回
	 * @return
	 */
	public static TimeSeriesIterator getRange(Metric metric, MetricTarget mt, String targetId, Stage stage,
			Date startDate, Date endDate, TimeUnit tu, boolean reversed) {
		// targetId == null可以为空
		if (metric == null || mt == null || stage == null) {
			return null;
		}
		try {

			if (startDate == null) {
				startDate = new Date(0);// 初始时间设置为公元1970年
			}
			if (endDate == null) {
				endDate = new Date();
			}

			DataPoint point = new DataPoint();
			point.setMetric(metric);
			point.setMetricTarget(mt);
			point.setTargetId(targetId);
			point.setStage(stage);
			point.setTime(startDate.getTime());
			byte[] startKey = tsdb.generateKey(point);
			point.setTime(endDate.getTime());
			point.setTime(endDate.getTime());
			byte[] endKey = tsdb.generateKey(point);

			HBaseClient client = HBaseClient.getInstance();
			// InclusiveStopFilter
			// TODO:添加Suffix Filter
			// Filter isFilter = new InclusiveStopFilter(endKey);

			RowFilter startRowFilter = new RowFilter(CompareOp.LESS_OR_EQUAL, new BinaryComparator(endKey));
			RowFilter endRowFilter = new RowFilter(CompareOp.GREATER_OR_EQUAL, new BinaryComparator(startKey));
			FilterList filters = new FilterList(startRowFilter, endRowFilter);
			// ResultScanner rs =
			// client.getByFilters(OpenTSDB.TSDB_DATA_TABLENAME, startKey,
			// endKey, filters,reversed);
			ResultScanner rs = client.getByFilters(OpenTSDB.TSDB_DATA_TABLENAME, filters, reversed);
			if (rs != null) {
				TimeSeriesIterator timeSeriesIterator = new TimeSeriesIterator(rs,
						startDate == null ? null : startDate.getTime(), endDate == null ? null : endDate.getTime(), tu,
						endKey, reversed);
				return timeSeriesIterator;
			}
		} catch (Exception e) {
			logger.equals("获取数据出错：" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除特定metric的所有的数据
	 * 
	 * @param metric
	 * @throws IOException
	 */
	public static void delete(Metric metric) throws IOException {
		if (metric == null)
			return;

		HBaseClient client = HBaseClient.getInstance();

		PrefixFilter prefixFilter = new PrefixFilter(tsdb.getMetricUID(metric));
		FilterList filters = new FilterList(prefixFilter);

		ResultScanner rs = client.getByFilters(OpenTSDB.TSDB_DATA_TABLENAME, filters, false);

		Result r;
		while ((r = rs.next()) != null) {
			logger.info("删除掉一行数据：" + Bytes.toString(r.getRow()));
			client.deleteRow(OpenTSDB.TSDB_DATA_TABLENAME, r.getRow());
		}
	}

}
