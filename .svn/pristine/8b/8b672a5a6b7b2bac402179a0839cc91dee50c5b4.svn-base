package com.inspur.bigdata.hbaseio.dao;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;

import com.inspur.bigdata.hbaseio.model.OpenTSDB;
import com.inspur.bigdata.hbaseio.pojo.TimeSeriesData;
import com.inspur.publicTools.ByteMathUtils;

/**
 * List<TimeSeriesData>的迭代器，防止数据量过大时
 * 
 * @author maolh
 *
 */
public class TimeSeriesIterator implements Iterator<TimeSeriesData> {
	// HBase的返回结果的迭代器
	private ResultScanner rs;
	// 下一个值

	// 用来做开始时间过滤
	private Long startTime;

	// 用来做结束时间过滤
	private Long endTime;

	// 用来做各种属性的过滤
	private byte[] comp;

	private TimeUnit tu;

	// 是否逆序
	private boolean reversed;

	protected final LinkedList<TimeSeriesData> cache = new LinkedList<TimeSeriesData>();

	/**
	 * rs的一条数据会包含一系列数据，startTime和endTime用来防止返回多余的数据
	 * 
	 * @param rs
	 * @param startTime
	 * @param endTime
	 */
	public TimeSeriesIterator(ResultScanner rs, Long startTime, Long endTime, TimeUnit tu, byte[] comp,
			boolean reversed) {
		this.rs = rs;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tu = tu;
		this.comp = comp;
		this.reversed = reversed;
	}

	/**
	 * 必须显示的调用close方法，关闭该迭代器，否则会报hbase的IOException
	 */
	@Override
	public boolean hasNext() {
		if (cache.isEmpty() == false) {
			if (startTime != null && cache.peek().getTime() < startTime) {
				while (cache.isEmpty() == false && cache.peek().getTime() < startTime) {
					cache.poll();
				}
			}
		}
		if (cache.isEmpty()) {
			try {
				if (rs != null) {
					Result next = rs.next();
					if (next != null) {
						byte[] row = next.getRow();
						while (OpenTSDB.matchWithNoTime(row, this.comp) == false) {
							next = rs.next();
							if (next != null) {
								row = next.getRow();
							} else {
								break;
							}
						}
					}
					if (next != null) {
						byte[] row = next.getRow();
						List<TimeSeriesData> tsdList = OpenTSDB.getTimeSeries(row);
						if (TimeUnit.HOURS == this.tu) {
							if (tsdList.size() > 0) {
								cache.add(tsdList.get(tsdList.size() - 1));
							}
						} else if (TimeUnit.MINUTES == this.tu) {
							String hourS = ByteMathUtils.encodeToBinaryString(Arrays.copyOfRange(row, 3, 7));
							long htime = Long.parseLong(hourS, 2) * 1000;
							TimeSeriesData[] values = new TimeSeriesData[60];// 从0到60分钟，每一分钟的最后的数据
							Arrays.fill(values, null);

							TimeSeriesData tsd = new TimeSeriesData();
							for (int i = 0; i < 60; i++) {
								tsd.setTime(htime + i * 60 * 1000);
								// 二分查找
								int idx = Collections.binarySearch(tsdList, tsd);
								if (idx >= 0) {
									// 存在
									values[i] = tsdList.get(idx);
								} else {
									idx = -idx - 1;
									// 如果分钟数一样
									if (idx < tsdList.size()) {
										long time = tsdList.get(idx).getTime();
										if ((time - time % 60000) / 60000 % 60 == i) {
											values[i] = tsdList.get(idx);
										}
									}
								}
							}
							if (reversed) {
								for (int i = 59; i >= 0; i--) {
									if (values[i] != null) {
										cache.add(values[i]);
										;
									}
								}
							} else {
								for (int i = 0; i < 60; i++) {
									if (values[i] != null) {
										cache.add(values[i]);
										;
									}
								}
							}

							values = null;// 显示释放
						} else {
							if (reversed) {
								Collections.reverse(tsdList);
								cache.addAll(tsdList);
							} else {
								cache.addAll(tsdList);
							}
						}
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		if (cache.isEmpty()) {
			if (rs != null) {
				rs.close();
			}
			return false;
		}

		if (endTime != null && cache.peek().getTime() > endTime) {
			rs.close();
			return false;
		}
		return true;
	}

	@Override
	public TimeSeriesData next() {
		if (!hasNext()) {
			return null;
		}
		return cache.poll();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public void close() {
		if (this.rs != null) {
			this.rs.close();
			this.cache.clear();
		}
	}
}
