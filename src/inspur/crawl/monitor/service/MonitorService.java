package inspur.crawl.monitor.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.inspur.bigdata.hbaseio.dao.TimeSeriesDao;
import com.inspur.bigdata.hbaseio.dao.TimeSeriesIterator;
import com.inspur.bigdata.hbaseio.pojo.DataPoint;
import com.inspur.bigdata.hbaseio.pojo.Metric;
import com.inspur.bigdata.hbaseio.pojo.MetricTarget;
import com.inspur.bigdata.hbaseio.pojo.Stage;
import com.inspur.bigdata.hbaseio.pojo.TimeSeriesData;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import inspur.crawl.codeManage.mapper.SiteCodeMapper;
import inspur.crawl.codeManage.mapper.StandardCodeContentMapper;
import inspur.crawl.codeManage.pojo.SiteCodeCriteria;
import inspur.crawl.codeManage.pojo.StandardCodeContentCriteria;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.tools.DataBaseConnection;
import inspur.crawl.common.tools.DateFormater;
import inspur.crawl.common.tools.RestServiceUtil;
import inspur.crawl.monitor.mapper.MonitorDataMapper;
import inspur.crawl.monitor.pojo.MonitorSourceData;
import inspur.crawl.monitor.util.MonitorChartType;
import inspur.crawl.monitor.util.MonitorConstant;
import inspur.crawl.monitor.util.MonitorMathUtil;
import inspur.crawl.taskManage.mapper.TaskInstanceMapper;
import inspur.crawl.taskManage.pojo.CrawlerTask;
import inspur.crawl.taskManage.pojo.TaskInstance;

@Service
public class MonitorService {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Resource
	TaskInstanceMapper instanceMapper;
	@Resource
	SiteCodeMapper siteMapper;
	@Resource
	StandardCodeContentMapper contentMapper;
	@Resource
	MonitorDataMapper monitorMapper;

	public Map<String, Object> generateTypeData(MetricTarget mt, Date beginTime, Date endTime, String targetId,
			TimeUnit tu) {
		Map<String, Object> dataMap = new HashMap<>();
		if (mt.equals(MetricTarget.TASK)) {
			Map<String, Object[]> downloadChart = loadRangeData(Metric.TASK_UNIT_DOWNLOAD_INCR, mt, targetId,
					Stage.FETCH, beginTime, endTime, tu);
			Map<String, Object[]> parseChart = loadRangeData(Metric.TASK_UNIT_URLPARSE_INCR, mt, targetId, Stage.PARSE,
					beginTime, endTime, tu);
			Map<String, Object[]> extractItemChart = loadRangeData(Metric.TASK_ONCE_EXTRACT_ITEMINCR, mt, targetId,
					Stage.EXTRACT, beginTime, endTime, tu);
			Map<String, Object[]> extractRecordChart = loadRangeData(Metric.TASK_ONCE_EXTRACT_RECORDINCR, mt, targetId,
					Stage.EXTRACT, beginTime, endTime, tu);
			Map<String, Object[]> dumpKafkaLogChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt, "kafkaLog",
					Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpKafkaLogCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"kafkaLog", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpFetchChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt, "urlFetchRequest",
					Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpFetchCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"urlFetchRequest", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpParseChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt, "urlParseRequest",
					Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpParseCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"urlParseRequest", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpRelationChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt,
					"urlRelationResult", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpRelationCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"urlRelationResult", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> mergeEventChart = loadRangeData(Metric.WHOLE_ONCE_RECORDSMERGE_INCRSUM, mt,
					"all", Stage.MERGE, beginTime, endTime, tu);
			Map<String, Object> handleChart = returnHandle(mt, beginTime, endTime, targetId);
			dataMap.put(MonitorChartType.HANDLE_DATA, handleChart);
			dataMap.put(MonitorChartType.DOWNLOAD_INCR, downloadChart);
			dataMap.put(MonitorChartType.PARSE_INCR, parseChart);
			dataMap.put(MonitorChartType.EXTRACT_ITEMINCR, extractItemChart);
			dataMap.put(MonitorChartType.EXTRACT_RECORDINCR, extractRecordChart);
			dataMap.put(MonitorChartType.KAFKALOG_DUMP_INCR_NUM, dumpKafkaLogChart);
			dataMap.put(MonitorChartType.KAFKALOG_DUMP_INCR_CAP, dumpKafkaLogChart);
			dataMap.put(MonitorChartType.URLFETCH_DUMP_INCR_NUM, dumpFetchChart);
			dataMap.put(MonitorChartType.URLFETCH_DUMP_INCR_CAP, dumpFetchCapChart);
			dataMap.put(MonitorChartType.URLPARSE_DUMP_INCR_NUM, dumpParseChart);
			dataMap.put(MonitorChartType.URLPARSE_DUMP_INCR_CAP, dumpParseCapChart);
			dataMap.put(MonitorChartType.URLRELATION_DUMP_INCR_NUM, dumpRelationChart);
			dataMap.put(MonitorChartType.URLRELATION_DUMP_INCR_CAP, dumpRelationCapChart);
			dataMap.put(MonitorChartType.WHOLE_MERGE_EVENT_NUM, mergeEventChart);
		} else if (mt.equals(MetricTarget.WHOLE)) {
			Map<String, Object[]> downloadChart = loadRangeData(Metric.WHOLE_UNIT_DOWNLOAD_INCR, mt, targetId,
					Stage.FETCH, beginTime, endTime, tu);
			Map<String, Object[]> parseChart = loadRangeData(Metric.WHOLE_UNIT_URLPARSE_INCR, mt, targetId, Stage.PARSE,
					beginTime, endTime, tu);
			Map<String, Object[]> extractItemChart = loadRangeData(Metric.WHOLE_ONCE_EXTRACT_ITEMINCR, mt, targetId,
					Stage.EXTRACT, beginTime, endTime, tu);
			Map<String, Object[]> extractRecordChart = loadRangeData(Metric.WHOLE_ONCE_EXTRACT_RECORDINCR, mt, targetId,
					Stage.EXTRACT, beginTime, endTime, tu);
			Map<String, Object[]> dumpKafkaLogChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt, "kafkaLog",
					Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpKafkaLogCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"kafkaLog", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpFetchChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt, "urlFetchRequest",
					Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpFetchCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"urlFetchRequest", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpParseChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt, "urlParseRequest",
					Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpParseCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"urlParseRequest", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpRelationChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt,
					"urlRelationResult", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpRelationCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"urlRelationResult", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> mergeEventChart = loadRangeData(Metric.WHOLE_ONCE_RECORDSMERGE_INCRSUM, mt,
					"all", Stage.MERGE, beginTime, endTime, tu);
			Map<String, Object> handleChart = returnHandle(mt, beginTime, endTime, targetId);
			Map<String, Object> precChart = returnPrec(mt, beginTime, endTime, targetId);
			dataMap.put(MonitorChartType.PREC_DATA, precChart);
			dataMap.put(MonitorChartType.HANDLE_DATA, handleChart);
			dataMap.put(MonitorChartType.DOWNLOAD_INCR, downloadChart);
			dataMap.put(MonitorChartType.PARSE_INCR, parseChart);
			dataMap.put(MonitorChartType.EXTRACT_ITEMINCR, extractItemChart);
			dataMap.put(MonitorChartType.EXTRACT_RECORDINCR, extractRecordChart);
			dataMap.put(MonitorChartType.KAFKALOG_DUMP_INCR_NUM, dumpKafkaLogChart);
			dataMap.put(MonitorChartType.KAFKALOG_DUMP_INCR_CAP, dumpKafkaLogChart);
			dataMap.put(MonitorChartType.URLFETCH_DUMP_INCR_NUM, dumpFetchChart);
			dataMap.put(MonitorChartType.URLFETCH_DUMP_INCR_CAP, dumpFetchCapChart);
			dataMap.put(MonitorChartType.URLPARSE_DUMP_INCR_NUM, dumpParseChart);
			dataMap.put(MonitorChartType.URLPARSE_DUMP_INCR_CAP, dumpParseCapChart);
			dataMap.put(MonitorChartType.URLRELATION_DUMP_INCR_NUM, dumpRelationChart);
			dataMap.put(MonitorChartType.URLRELATION_DUMP_INCR_CAP, dumpRelationCapChart);
			dataMap.put(MonitorChartType.WHOLE_MERGE_EVENT_NUM, mergeEventChart);
		} else if (mt.equals(MetricTarget.DEMAND)) {
			Map<String, Object[]> downloadChart = loadRangeData(Metric.RQRMNT_UNIT_DOWNLOAD_INCR, mt, targetId,
					Stage.FETCH, beginTime, endTime, tu);
			Map<String, Object[]> parseChart = loadRangeData(Metric.RQRMNT_UNIT_URLPARSE_INCR, mt, targetId,
					Stage.PARSE, beginTime, endTime, tu);
			Map<String, Object[]> extractItemChart = loadRangeData(Metric.WHOLE_ONCE_EXTRACT_ITEMINCR, mt, targetId,
					Stage.EXTRACT, beginTime, endTime, tu);
			Map<String, Object[]> extractRecordChart = loadRangeData(Metric.WHOLE_ONCE_EXTRACT_RECORDINCR, mt, targetId,
					Stage.EXTRACT, beginTime, endTime, tu);
			Map<String, Object[]> dumpKafkaLogChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt, "kafkaLog",
					Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpKafkaLogCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"kafkaLog", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpFetchChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt, "urlFetchRequest",
					Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpFetchCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"urlFetchRequest", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpParseChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt, "urlParseRequest",
					Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpParseCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"urlParseRequest", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpRelationChart = loadRangeData(Metric.WHOLE_UNIT_FILEDUMP_INCR, mt,
					"urlRelationResult", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> dumpRelationCapChart = loadRangeDataCal(Metric.WHOLE_UNIT_CAPACITYDUMP_INCR, mt,
					"urlRelationResult", Stage.RESTORE, beginTime, endTime, tu);
			Map<String, Object[]> mergeEventChart = loadRangeData(Metric.WHOLE_ONCE_RECORDSMERGE_INCRSUM, mt,
					"all", Stage.MERGE, beginTime, endTime, tu);
			Map<String, Object> handleChart = returnHandle(mt, beginTime, endTime, targetId);
			Map<String, Object> precChart = returnPrec(mt, beginTime, endTime, targetId);
			dataMap.put(MonitorChartType.PREC_DATA, precChart);
			dataMap.put(MonitorChartType.HANDLE_DATA, handleChart);
			dataMap.put(MonitorChartType.DOWNLOAD_INCR, downloadChart);
			dataMap.put(MonitorChartType.PARSE_INCR, parseChart);
			dataMap.put(MonitorChartType.EXTRACT_ITEMINCR, extractItemChart);
			dataMap.put(MonitorChartType.EXTRACT_RECORDINCR, extractRecordChart);
			dataMap.put(MonitorChartType.KAFKALOG_DUMP_INCR_NUM, dumpKafkaLogChart);
			dataMap.put(MonitorChartType.KAFKALOG_DUMP_INCR_CAP, dumpKafkaLogChart);
			dataMap.put(MonitorChartType.URLFETCH_DUMP_INCR_NUM, dumpFetchChart);
			dataMap.put(MonitorChartType.URLFETCH_DUMP_INCR_CAP, dumpFetchCapChart);
			dataMap.put(MonitorChartType.URLPARSE_DUMP_INCR_NUM, dumpParseChart);
			dataMap.put(MonitorChartType.URLPARSE_DUMP_INCR_CAP, dumpParseCapChart);
			dataMap.put(MonitorChartType.URLRELATION_DUMP_INCR_NUM, dumpRelationChart);
			dataMap.put(MonitorChartType.URLRELATION_DUMP_INCR_CAP, dumpRelationCapChart);
			dataMap.put(MonitorChartType.WHOLE_MERGE_EVENT_NUM, mergeEventChart);
		}
		
		Map<String, Object[]> demandMap = generateDemandMap(beginTime, endTime);
		Map<String, Object[]> siteMap = generateSiteMap(beginTime, endTime);
		dataMap.put("demandMap", demandMap);
		dataMap.put("siteMap", siteMap);
		return dataMap;
	}

	//生成需求数据
	private Map<String, Object[]> generateDemandMap(Date beginTime, Date endTime) {
		Map<String, Object[]> demandMap = new HashMap<>();
		int demandNum = 0;
		int crawlNum = 0;
		int deliverNum = 0;
		List<Map> demandList = monitorMapper.selectDemandData();
		for(Map map:demandList) {
			if(map.get("DEMAND_STATUS").equals("01") || map.get("DEMAND_STATUS").equals("02")) {
				demandNum = demandNum + ((BigDecimal) map.get("NUM")).intValue();
			}else if(map.get("DEMAND_STATUS").equals("03") || map.get("DEMAND_STATUS").equals("05") || map.get("DEMAND_STATUS").equals("06")) {
				crawlNum = crawlNum + ((BigDecimal) map.get("NUM")).intValue();
			}else if(map.get("DEMAND_STATUS").equals("07")) {
				deliverNum = deliverNum + ((BigDecimal) map.get("NUM")).intValue();
			}
		}
		demandMap.put("demandNum", new Integer[]{demandNum});
		demandMap.put("crawlNum", new Integer[]{crawlNum});
		demandMap.put("deliverNum", new Integer[]{deliverNum});
		return demandMap;
	}

	//生成网站数据
	private Map<String, Object[]> generateSiteMap(Date beginTime, Date endTime) {
		Map<String, Object[]> siteMap = new HashMap<>();
		int totalNum = 0;
		int demandNum = 0;
		int deliverNum = 0;
		List<Map> siteList = monitorMapper.selectSiteData();
		for(Map map:siteList) {
			if(map.get("ST").equals("total")) {
				totalNum = totalNum + ((BigDecimal) map.get("NUM")).intValue();
			}else if(map.get("ST").equals("deliver")) {
				deliverNum = deliverNum + ((BigDecimal) map.get("NUM")).intValue();
			}else if(map.get("ST").equals("demand")) {
				demandNum = demandNum + ((BigDecimal) map.get("NUM")).intValue();
			}
		}
		siteMap.put("demandNum", new Integer[]{demandNum});
		siteMap.put("deliverNum", new Integer[]{deliverNum});
		siteMap.put("crawlNum", new Integer[]{totalNum - deliverNum - demandNum});
		return siteMap;
	}
	
	/**
	 * 加载范围数据
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
	public Map<String, Object[]> loadRangeData(Metric metric, MetricTarget mt, String targetId, Stage stage,
			Date startDate, Date endDate, TimeUnit tu) {
		TimeSeriesIterator dataIterator = TimeSeriesDao.getRange(metric, mt, targetId, stage, startDate, endDate, tu);
		// 组织数据
		Map<String, Object[]> rangeDatas = new HashMap<>();
		List<String> axisData = new ArrayList<>();
		List<Integer> data = new ArrayList<>();
		if(dataIterator != null) {
			while (dataIterator.hasNext()) {
				TimeSeriesData tsd = dataIterator.next();
				if (tsd != null) {
					Date date = new Date(tsd.getTime());
					axisData.add(sdf.format(date));
					data.add(tsd.getData().intValue());
				}
			}
			dataIterator.close();// 不能忘记关闭
		}
		if (data.size() == 0) {
			data.add(0);
			data.add(0);
			axisData.add(sdf.format(startDate));
			axisData.add(sdf.format(endDate));
		}
		rangeDatas.put("axisData", axisData.toArray(new String[axisData.size()]));
		rangeDatas.put("data", data.toArray(new Integer[data.size()]));
		return rangeDatas;
	}

	public Map<String, Object[]> loadRangeDataCal(Metric metric, MetricTarget mt, String targetId, Stage stage,
			Date startDate, Date endDate, TimeUnit tu) {
		TimeSeriesIterator dataIterator = TimeSeriesDao.getRange(metric, mt, targetId, stage, startDate, endDate, tu);
		// 组织数据
		Map<String, Object[]> rangeDatas = new HashMap<>();
		List<String> axisData = new ArrayList<>();
		List<Integer> data = new ArrayList<>();
		if(dataIterator != null) {
			while (dataIterator.hasNext()) {
				TimeSeriesData tsd = dataIterator.next();
				if (tsd != null) {
					Date date = new Date(tsd.getTime());
					axisData.add(sdf.format(date));
					data.add(tsd.getData().intValue() / (1024 * 1024));
				}
			}
			dataIterator.close();// 不能忘记关闭
		}
		if (data.size() == 0) {
			data.add(0);
			data.add(0);
			axisData.add(sdf.format(startDate));
			axisData.add(sdf.format(endDate));
		}
		rangeDatas.put("axisData", axisData.toArray(new String[axisData.size()]));
		rangeDatas.put("data", data.toArray(new Integer[data.size()]));
		return rangeDatas;
	}

	/**
	 * 查看数据清洗总数量
	 * 
	 * @param instance_id
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	public String returnSum(MetricTarget mt, Date beginTime, Date endTime, String targetId) {
		String sum = "0";
		try {
			Connection c = DataBaseConnection.getConnection("jdbc:oracle:thin:@172.22.4.16:1521:tmsjcj", "crawlm",
					"crawlm");
			if (mt.equals(MetricTarget.TASK)) {
				String sql = "select sum(m.table_num) num from storm_handle_monitor m,(select  min(TIME_STAMP) time_stamp,rule_id,min(sys_time) sys_time from storm_handle_monitor  where instance_id=? and time_stamp>=? and time_stamp<? group by rule_id ) s where m.sys_time = s.sys_time and m.rule_id=s.rule_id ";

				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, targetId);
				ps.setString(2, beginTime.getTime() + "");
				ps.setString(3, endTime.getTime() + "");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					sum = rs.getString("num");
				}
				rs.close();
				ps.close();
			} else if (mt.equals(MetricTarget.WHOLE)) {
				String sql = "select sum(m.table_num) num from storm_handle_monitor m,(select  min(TIME_STAMP) time_stamp,rule_id,min(sys_time) sys_time from storm_handle_monitor  where  time_stamp>=? and time_stamp<? group by rule_id ) s where m.sys_time = s.sys_time and m.rule_id=s.rule_id ";

				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, beginTime.getTime() + "");
				ps.setString(2, endTime.getTime() + "");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					sum = rs.getString("num");
				}
				rs.close();
				ps.close();
			}

			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sum == null) {
			sum = "0";
		}
		return sum;
	}

	/**
	 * 查看清洗量
	 */
	public Map<String, Object> returnHandle(MetricTarget mt, Date beginTime, Date endTime, String targetId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Connection c = DataBaseConnection.getConnection("jdbc:oracle:thin:@172.22.4.16:1521:tmsjcj", "crawlm",
					"crawlm");
			if (mt.equals(MetricTarget.TASK)) {
				String sql = "select * from storm_handle_monitor a where rowid =(select min(rowid) from storm_handle_monitor b where a.time_stamp=b.time_stamp) and a.instance_id=? and a.time_stamp>=? and a.time_stamp<?";
				int up_num = 0;
				int del_num = 0;
				int dis_num = 0;
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, targetId);
				ps.setString(2, beginTime.getTime() + "");
				ps.setString(3, endTime.getTime() + "");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					String type = rs.getString("kafka_topic");
					if ("dis".equals(type)) {
						dis_num += Integer.valueOf(rs.getString("handle_num"));
					} else if ("update".equals(type)) {
						up_num += Integer.valueOf(rs.getString("handle_num"));
					} else if ("del".equals(type)) {
						del_num += Integer.valueOf(rs.getString("handle_num"));
					}
				}
				map.put("sum", returnSum(mt, beginTime, endTime, targetId));
				map.put("dis", dis_num);
				map.put("update", up_num);
				map.put("del", del_num);
				rs.close();
				ps.close();
			} else if (mt.equals(MetricTarget.WHOLE)) {
				String sql = "select * from storm_handle_monitor a where rowid =(select min(rowid) from storm_handle_monitor b where a.time_stamp=b.time_stamp) and a.time_stamp>=? and a.time_stamp<?";
				int up_num = 0;
				int del_num = 0;
				int dis_num = 0;
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, beginTime.getTime() + "");
				ps.setString(2, endTime.getTime() + "");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					String type = rs.getString("kafka_topic");
					if ("dis".equals(type)) {
						dis_num += Integer.valueOf(rs.getString("handle_num"));
					} else if ("update".equals(type)) {
						up_num += Integer.valueOf(rs.getString("handle_num"));
					} else if ("del".equals(type)) {
						del_num += Integer.valueOf(rs.getString("handle_num"));
					}
				}
				map.put("sum", returnSum(mt, beginTime, endTime, targetId));
				map.put("dis", dis_num);
				map.put("update", up_num);
				map.put("del", del_num);
				rs.close();
				ps.close();
			}
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 查看预处理
	 */
	public Map<String, Object> returnPrec(MetricTarget mt, Date beginTime, Date endTime, String targetId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int secc_num = 0;
		int fail_num = 0;
		try {
			Connection c = DataBaseConnection.getConnection("jdbc:oracle:thin:@172.22.4.16:1521:tmsjcj", "crawlm",
					"crawlm");
			if (mt.equals(MetricTarget.TASK)) {
				String sql = "select * from storm_prec_monitor a where rowid =(select min(rowid) from storm_prec_monitor b where a.time_stamp=b.time_stamp) and a.instance_id=? and a.time_stamp>=? and a.time_stamp<?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, targetId);
				ps.setString(2, beginTime.getTime() + "");
				ps.setString(3, endTime.getTime() + "");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					String type = rs.getString("export_num");
					if ("success".equals(type)) {
						secc_num++;
					} else {
						fail_num++;
					}
				}
				map.put("success", secc_num);
				map.put("fail", fail_num);
				rs.close();
				ps.close();
			} else if (mt.equals(MetricTarget.WHOLE)) {
				String sql = "select * from storm_prec_monitor a where rowid =(select min(rowid) from storm_prec_monitor b where a.time_stamp=b.time_stamp) and a.time_stamp>=? and a.time_stamp<?";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, beginTime.getTime() + "");
				ps.setString(2, endTime.getTime() + "");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					String type = rs.getString("export_num");
					if ("success".equals(type)) {
						secc_num++;
					} else {
						fail_num++;
					}
				}
				map.put("success", secc_num);
				map.put("fail", fail_num);
				rs.close();
				ps.close();
			}

			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(map.isEmpty()) {
			map.put("success", secc_num);
			map.put("fail", fail_num);
		}
		return map;
	}

	public List<TaskInstance> findTaskInstances(CrawlerTask crawlerTask, Page page) {
		return instanceMapper.listPageForSelect(crawlerTask, page);
	}

	/**
	 * 根据状态分组统计实例状态
	 * 
	 * @param crawlerTask
	 * @return
	 */
	public Map countInstanceByStatus(CrawlerTask crawlerTask) {
		List<Map<String, BigDecimal>> countList = instanceMapper.countByStatus(crawlerTask);
		Map<String, BigDecimal> statusCount = new HashMap<>();
		for (Map<String, BigDecimal> count : countList) {
			statusCount.put(count.get("STATUS").toString(), count.get("COUNT"));
		}
		return statusCount;
	}

	/*
	 * 根周期配置分组统计数量
	 */
	public Map countInstanceByPeriod(CrawlerTask crawlerTask) {
		List<Map<String, BigDecimal>> countList = instanceMapper.countByPeriod(crawlerTask);
		Map<String, BigDecimal> periodCount = new HashMap<>();
		for (Map<String, BigDecimal> count : countList) {
			periodCount.put(count.get("PERIOD").toString(), count.get("COUNT"));
		}
		return periodCount;
	}

	/**
	 * 获得主题数据
	 * 
	 * @return
	 */
	public Map<String, Object> getGeneralCount() {
		Calendar start = Calendar.getInstance();
		start.set(Calendar.DAY_OF_MONTH, 1);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);

		Calendar lastMonthEnd = Calendar.getInstance();
		lastMonthEnd.add(Calendar.MONTH, -1);
		Calendar lastMonthStart = (Calendar) start.clone();
		lastMonthStart.add(Calendar.MONTH, -1);

		StandardCodeContentCriteria criteria = new StandardCodeContentCriteria();
		criteria.createCriteria().andPublishTimeGreaterThan(start.getTime());
		// 全部数据项
		Integer numAll = contentMapper.countByExample(null);
		// 本月新增数据项
		Integer numNew = contentMapper.countByExample(criteria);
		criteria.clear();
		criteria.createCriteria().andPublishTimeGreaterThan(lastMonthStart.getTime())
				.andPublishTimeLessThan(lastMonthEnd.getTime());
		Integer numLasMonth = contentMapper.countByExample(criteria);

		// 主题数查询
		SiteCodeCriteria scCriteria = new SiteCodeCriteria();
		scCriteria.createCriteria().andPublishTimeGreaterThan(start.getTime());

		Integer typeAll = siteMapper.countByExample(null);
		// List<Map<String, Integer>> typeGroupCount =
		// siteMapper.groupCountByTime();
		Integer typeNew = siteMapper.countByExample(scCriteria);
		scCriteria.clear();
		scCriteria.createCriteria().andPublishTimeGreaterThan(lastMonthStart.getTime())
				.andPublishTimeLessThan(lastMonthEnd.getTime());
		Integer typeLastMonth = siteMapper.countByExample(scCriteria);

		Integer typeIncr = typeNew - typeLastMonth;

		//数据项数
		Map<String, Object> result = new HashMap<>();
		result.put("numAll", numAll);
		result.put("numNew", numNew);
		result.put("numIncr", numNew - numLasMonth);
		result.put("numIncrPer", MonitorMathUtil.incrPercentByMonthVal(numNew, numLasMonth));

		//获得数据记录和存储相关数据
		getMonitorDataNum(result);
		// 主题
		result.put("typeAll", typeAll);
		result.put("typeNew", typeNew);
		result.put("typeIncr", typeNew - typeLastMonth);
		result.put("typeIncrPer", MonitorMathUtil.incrPercentByMonthVal(typeNew, typeLastMonth));
		return result;
	}

	/**
	 * 获得数据条数
	 * 
	 * @param start
	 * @param lastStart
	 * @param lastEnd
	 * @param result
	 */
	public void getMonitorDataNum(Map<String, Object> result) {
		List<MonitorSourceData> datas = monitorMapper.selectLatestData();
		for(MonitorSourceData data:datas) {
			if(data.getDataType().equals(MonitorConstant.DATATYPE_DATA)) {
				result.put("dataAll", data.getDataAll());
				result.put("dataNew", data.getDataNew());
				result.put("dataIncr", data.getDataIncr());
				result.put("dataIncrPer", data.getDataIncrPer());
			}else if(data.getDataType().equals(MonitorConstant.DATATYPE_DUMP)) {
				result.put("dumpAll", data.getDataAll());
				result.put("dumpNew", data.getDataNew());
				result.put("dumpIncr", data.getDataIncr());
				result.put("dumpIncrPer", data.getDataIncrPer());
			}
		}
		
	}

	public static void main(String[] args) {
		Date endDate = new Date();
		Date startDate = new Date();
		startDate.setDate(1);
		System.out.println(startDate);
		/*
		 * TimeSeriesIterator dataIterator =
		 * TimeSeriesDao.getRange(Metric.WHOLE_UNIT_URLPARSE_INCR,
		 * MetricTarget.WHOLE, null, Stage.PARSE, startDate, endDate,
		 * TimeUnit.MINUTES);
		 */
		// TimeSeriesIterator dataIterator =
		// TimeSeriesDao.getRange(Metric.WHOLE_UNIT_FILEDUMP_INCR,
		// MetricTarget.WHOLE,
		// "kafkaLog16-02-22",
		// Stage.RESTORE,
		// startDate,
		// endDate,
		// TimeUnit.MINUTES);
		TimeSeriesData fileTsd = TimeSeriesDao.getLastestTimeSeries(Metric.TASK_UNIT_DOWNLOAD_INCRSUM,
				MetricTarget.TASK, "aa17ffe3-0acc-45ca-a8dd-2eb0a9220994", Stage.FETCH);
		// 组织数据
		// Map<String, Number> rangeDatas = new HashMap<>();
		// while(dataIterator.hasNext()) {
		// TimeSeriesData data= dataIterator.next();
		// Date date = new Date(data.getTime());
		// Number value = data.getData();
		// rangeDatas.put(sdf.format(date), value);
		// }
		System.out.println("fileTsd" + fileTsd);
		// System.out.println(rangeDatas);
		TimeSeriesData tsdAll = TimeSeriesDao.getLastestTimeSeries(Metric.WHOLE_ALL_CAPACITYDUMP_SUM,
				MetricTarget.WHOLE, "urlParseRequest", Stage.RESTORE);
		Long dumpAll = 0L;
		// 全部数据项
		if (tsdAll != null) {
			dumpAll = tsdAll.getData().longValue() / (1024 * 1024 * 1024);
		}
		System.out.println(dumpAll);
	}
}
