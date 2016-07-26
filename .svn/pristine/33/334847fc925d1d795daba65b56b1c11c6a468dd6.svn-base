package inspur.crawl.urlMonitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.cloudera.impala.jdbc41.DataSource;

import inspur.crawl.ruleManage.controller.Parse;
import inspur.crawl.ruleManage.controller.UrlParser;
import inspur.crawl.ruleManage.pojo.ParseRuleTemp;
import inspur.crawl.urlMonitor.controller.Parameter;
import inspur.crawl.urlMonitor.mapper.urlMonitorMapper;
import inspur.crawl.urlMonitor.pojo.TaskNameId;
import inspur.crawl.urlMonitor.pojo.UrlRelationHistory;

@Service
public class CrawlInstationService {
	public static Connection connection() throws Exception {
		Connection connection = null;
		Class.forName("com.cloudera.impala.jdbc41.Driver");
		DataSource ds = new DataSource();
		ds.setURL("jdbc:impala://qing:21050");
		connection = ds.getConnection();
		return connection;
	}

	@Resource
	urlMonitorMapper umm;

	/**
	 * 查询oracle数据库
	 */
	/**
	 * 获得task_id与task_name对应关系 例如list。get(0)获得TaskNameId的Javabean:TaskNameId
	 * [task_id=362, task_name=仓位在线—全部数据]
	 * 
	 * @return
	 */
	public List<TaskNameId> getNameId() {
		List<TaskNameId> list = umm.getNameId();
		return list;
	}

	/**
	 * 获得taskinstanceid的发布时时间与结束时间
	 * 通过TaskNameId类中getPublish_time()、getFinish_time方法获得时间
	 */
	public TaskNameId getTime(String instance_id) {
		TaskNameId tni = umm.getTime(instance_id);
		return tni;
	}

	/**
	 * first page
	 */
	/**
	 * 任务的列表以及下载总量 map-key：任务id map-value：任务中下载的数量
	 * 
	 * @param pa
	 * @return
	 */
	public Map<String, String> queryTaskId(Parameter pa) {
		String page = null;
		String offset = null;
		if (pa.getPage() == "") {
			page = "1";
		} else {
			page = pa.getPage();
		}
		offset = (Integer.parseInt(page) - 1) * 10 + "";

		Map<String, String> map = new HashMap<String, String>();
		String sqlStatement = "select taskid,sum(num) from parse_statistics_test7 group by taskid order by taskid limit 10 offset  "
				+ offset + " ;";
		try {
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);
			System.out.println("\n== Begin Query Results ======================");
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
				System.out.println(rs.getString(1) + "----" + rs.getString(2));
			}
			System.out.println("== End Query Results =======================\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		return map;
	}

	/**
	 * 所有的任务id个数
	 * 
	 * @return
	 */
	public int queryTaskIdNum() {
		int num = 0;
		Map<String, String> map = new HashMap<String, String>();
		String sqlStatement = "select count(distinct(taskid)) from parse_statistics_test7;";
		try {
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);
			while (rs.next()) {
				num = rs.getInt(1);
			}
			System.out.println(num);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		return num;
	}

	/**
	 * 某一个任务下的所有任务实例列表以及其下载的总量 map-key:任务实例id map-value：任务实例下载的数量
	 * 
	 * @param taskId
	 * @return
	 */
	public Map<String, String> queryTaskInstance(String taskId) {
		Map<String, String> map = new HashMap<String, String>();
		String sqlStatement = "select taskinstanceid,sum(num) from parse_statistics_test7 where taskid='" + taskId
				+ "' group by taskinstanceid ;";
		try {
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);
			System.out.println("\n== Begin Query Results ======================");
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
				System.out.println(rs.getString(1) + "----" + rs.getString(2));
			}
			System.out.println("== End Query Results =======================\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		return map;
	}

	/**
	 * 
	 * taskInstanceId page
	 */
	/**
	 * 任务实例中各个类型的下载总数 map-key:url类型，value：所属url的数量
	 * 
	 * @param taskInstanceId
	 * @return
	 */
	public Map<String, String> queryTaskInstanceAll(String taskInstanceId) {
		Map<String, String> map = new HashMap<String, String>();
		String sqlStatement = "select urltype,sum(num) from parse_statistics_test7 where " + "taskinstanceid='"
				+ taskInstanceId + "' " + "group by urltype";
		try {
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);
			System.out.println("\n== Begin Query Results ======================");
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
				System.out.println(rs.getString(1) + "----" + rs.getString(2));
			}
			System.out.println("== End Query Results =======================\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		return map;
	}

	/**
	 * 不同的时间粒度内各个url类型的数量 map-key:urltype, 时间粒度为小时：map-value：日期/小时-数量
	 * 时间粒度为天：map-value：日期-数量
	 * 
	 * @param taskInstanceId
	 * @param startTime
	 * @param endTime
	 * @param type
	 * @return
	 */
	public List<Map<String, String>> queryTaskInstanceLineChart(String taskInstanceId, String startTime, String endTime,
			String type) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String sqlStatement = "";
		if (type.equals("0"))
			sqlStatement = "select urltype,daytime,hourtime,num from parse_statistics_test7 where taskinstanceid='"
					+ taskInstanceId + "' and daytime<='" + endTime + "' and daytime>='" + startTime
					+ "' order by daytime, hourtime;";
		else
			sqlStatement = "select urltype,daytime,sum(num) from parse_statistics_test7 where taskinstanceid='"
					+ taskInstanceId + "' and daytime<='" + endTime + "' and daytime>='" + startTime
					+ "'  group by urltype,daytime; ";
		System.out.println(sqlStatement);
		try {
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);
			System.out.println("\n== Begin Query Results ======================");
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				if (type.equals("0")) {
					map.put(rs.getString(1), rs.getString(2) + "/" + rs.getString(3) + "|" + rs.getString(4));
					list.add(map);
				} else {
					map.put(rs.getString(1), rs.getString(2) + "|" + rs.getString(3));
					list.add(map);
				}
				System.out.println(rs.getString(1) + "----" + rs.getString(2) + "----------" + rs.getString(3));
			}
			System.out.println("== End Query Results =======================\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		return list;
	}

	/**
	 * 任务实例中url详细列表 map-key：url map-value：urltype|daytime
	 * 
	 * @param taskInstanceId
	 * @param offset:从第几行开始查询，此作为分页参数
	 * @return
	 */
	public List<Map<String, String>> queryTaskInstanceList(String taskInstanceId, String offset) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String sqlStatement = "select url,urltype,daytime from parse_statistics_test6 where taskinstanceid='"
				+ taskInstanceId + "'  order by url limit 10 offset " + offset + " ;";
		try {
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);
			System.out.println("\n== Begin Query Results ======================");
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put(rs.getString(1), rs.getString(2) + "|" + rs.getString(3));
				list.add(map);
				System.out.println(rs.getString(1) + "----" + rs.getString(2) + "-----" + rs.getString(3));
			}
			System.out.println("== End Query Results =======================\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		return list;
	}

	/**
	 * 任务实例中url详细中总数
	 */
	public int querydetailNumTaskInstanceId(String taskInstanceId) {
		int num = 0;
		Map<String, String> map = new HashMap<String, String>();
		String sqlStatement = "select count(*) from parse_statistics_test6 where taskinstanceid='" + taskInstanceId
				+ "';";
		try {
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);
			while (rs.next()) {
				num = rs.getInt(1);
			}
			System.out.println(num);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		return num;
	}

	/**
	 * taskId page
	 */
	/**
	 * 任务各个类型的下载总数 map-key:url类型，value：所属url的数量
	 * 
	 * @param taskId
	 * @return
	 */
	public Map<String, String> queryTaskList(String taskId) {
		Map<String, String> map = new HashMap<String, String>();
		String sqlStatement = "select urltype,sum(num) from parse_statistics_test7 where taskid='" + taskId
				+ "'group by urltype;";
		try {
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);
			System.out.println("\n== Begin Query Results ======================");
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
				System.out.println(rs.getString(1) + "----" + rs.getString(2));
			}
			System.out.println("== End Query Results =======================\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		return map;
	}

	/**
	 * 任务各个任务实例的不同类型下载总数 map-key:url类型， map-value：任务实例|数量
	 * 
	 * @param taskId
	 * @return
	 */
	public Map<String, String> queryTaskAndInstance(String taskId) {
		Map<String, String> map = new HashMap<String, String>();
		String sqlStatement = "select urltype,taskinstanceid,sum(num) from parse_statistics_test7  where taskid='"
				+ taskId + "' group by urltype,taskinstanceid;";
		try {
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);
			System.out.println("\n== Begin Query Results ======================");
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2) + "|" + rs.getString(3));
				System.out.println(rs.getString(1) + "----" + rs.getString(2));
			}
			System.out.println("== End Query Results =======================\n\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		return map;
	}

	/**
	 * 查询在某次实例执行时，一个url由某规则所解析出的url的子条数
	 * 
	 * @param instanceId
	 * @param parseRuleId
	 * @param parentUrl
	 * @return
	 */
	public int countSubUrls(String instanceId, String parseRuleId, String parentUrl) {
		String sqlStatement = "select count(*) from url_relation_partition  where task_instance_id='" + instanceId
				+ "' and p_url='" + parentUrl + "' and ruleid='" + parseRuleId + "'";
		try {
			Statement stmt = connection().createStatement();
			long start = System.currentTimeMillis();
			System.out.println("\n== Begin Query Results ======================\r\n"+sqlStatement+"\r\n");
			ResultSet rs = stmt.executeQuery(sqlStatement);
			while (rs.next()) {
				int count = rs.getInt(1);
				long end = System.currentTimeMillis();
				System.out.println("\n== Begin Query Results ======================\r\nuse time:"+((end-start)/1000)+"s");
				rs.close();
				stmt.close();
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 根据实例id、规则id，查询某个url所生成的子url
	 * 
	 * @param instanceId
	 * @param parseRuleId
	 * @param parentUrl
	 * @return
	 */
	public List<UrlRelationHistory> queryByParentUrlAndPage(String taskId,String instanceId, String parseRuleId, String parentUrl, int pageSize,
			int pageNo) {
		List<UrlRelationHistory> historys = new ArrayList<>();
		String sqlStatement = "select  c_url,depth,publish_time from url_relation_partition  where task_instance_id='"
				+ instanceId + "' and p_url='" + parentUrl + "' " + " and ruleid='" + parseRuleId
				+ "' order by c_url limit " + pageSize + " offset " + ((pageNo - 1) * pageSize);
		try {
			Statement stmt = connection().createStatement();
			long start = System.currentTimeMillis();
			System.out.println("\n== Begin Query Results ======================\r\n"+sqlStatement+"\r\n");
			ResultSet rs = stmt.executeQuery(sqlStatement);
			CassandraCrawlingHistoryOps ops = new CassandraCrawlingHistoryOps();
			while (rs.next()) {
				UrlRelationHistory history = new UrlRelationHistory();
				String depth = rs.getString(1);
				if (depth != null && depth.matches("\\d+")) {
					history.setDepth(Integer.valueOf(depth));
				}
				history.setInstanceId(instanceId);
				history.setParentUrl(parentUrl);
				history.setParseRuleId(parseRuleId);
				history.setPublishTime(new Date(rs.getLong(3)));
				history.setSubUrl(rs.getString(1));
				historys.add(history);

				//查询cassandra，看url是否被下载
				List<CrawlingHistory> crawlingHistories =ops.fetchRecentByInstance(rs.getString(1), instanceId, taskId);
				if(crawlingHistories.size()>0){
					if(crawlingHistories.get(0).status_code==null){
						history.setStatusCode("null");

					}else{
						history.setStatusCode(crawlingHistories.get(0).status_code);
					}
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("\n== End Query Results ======================\r\nuse time:"+((end-start)/1000)+"s");
			rs.close();
			stmt.close();
				} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return historys;
	}
	
	/**
	 * 根据实例id、规则id，查询某个url所生成的子url
	 * 
	 * @param instanceId
	 * @param parseRuleId
	 * @param parentUrl
	 * @return
	 */
	public List<UrlRelationHistory> queryByCurrentUrlAndPage(String instanceId, String parseRuleId, String url, int pageSize,
			int pageNo) {
		List<UrlRelationHistory> historys = new ArrayList<>();
		String sqlStatement = "select  p_url,depth,publish_time from url_relation_partition  where task_instance_id='"
				+ instanceId + "' and c_url='" + url + "' " + " and ruleid='" + parseRuleId
				+ "' order by p_url limit " + pageSize + " offset " + ((pageNo - 1) * pageSize);
		try {
			Statement stmt = connection().createStatement();
			long start = System.currentTimeMillis();
			System.out.println("\n== Begin Query Results ======================\r\n"+sqlStatement+"\r\n");
			ResultSet rs = stmt.executeQuery(sqlStatement);
			while (rs.next()) {
				UrlRelationHistory history = new UrlRelationHistory();
				String depth = rs.getString(1);
				if (depth != null && depth.matches("\\d+")) {
					history.setDepth(Integer.valueOf(depth));
				}
				history.setInstanceId(instanceId);
				history.setParentUrl(rs.getString(1));
				history.setParseRuleId(parseRuleId);
				history.setPublishTime(new Date(rs.getLong(3)));
				history.setSubUrl(url);
				historys.add(history);
			}
			long end = System.currentTimeMillis();
			System.out.println("\n== End Query Results ======================\r\nuse time:"+((end-start)/1000)+"s");
			rs.close();
			stmt.close();
				} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return historys;
	}

	private String addUrlPatternCond(ParseRuleTemp parseRule){
		StringBuffer buffer = new StringBuffer();
		String urlPattern = parseRule.getUrlPattern();
		if(urlPattern!=null){
			String[] subPatterns = urlPattern.split("\\s+");
			boolean isFirst = true;
			for (String subPattern : subPatterns) {
				if (subPattern == null || subPattern.length() == 0)
						continue;
				char includeChar = subPattern.charAt(0);
				subPattern = subPattern.substring(1);
				//需要把其中的转义字符\替换成\\
				subPattern = subPattern.replaceAll("\\\\", "\\\\\\\\");
				if (includeChar == '+') {
					if(isFirst){
						buffer.append(" and (c_url rlike '"+subPattern+"' ");
						isFirst = false;
					}else{
						buffer.append(" or c_url rlike '"+subPattern+"' ");
					}
				}
			}
			
			buffer.append(")");
			
			for (String subPattern : subPatterns) {
				if (subPattern == null || subPattern.length() == 0)
						continue;
				char includeChar = subPattern.charAt(0);
				subPattern = subPattern.substring(1);
				if (includeChar == '-') {
					buffer.append(" and c_url not rlike '"+subPattern+"' ");
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * 比较parseRuleId1的输出的url和parseRuleId2的输入的url的差别
	 * 
	 * @param instanceId
	 * @param parseRuleId
	 * @param parentUrl
	 * @return
	 */
	public List<UrlRelationHistory> differByPage(String instanceId, String parseRuleId1, ParseRuleTemp parseRule2,
			int pageSize, int pageNo) {
		List<UrlRelationHistory> historys = new ArrayList<>();
		String sqlStatement = "select  c_url,depth,publish_time,p_url from url_relation_partition  where task_instance_id='"
				+ instanceId + "' and ruleid='" + parseRuleId1
				+ "' ";
		
		sqlStatement = sqlStatement + addUrlPatternCond(parseRule2);
		
		sqlStatement = sqlStatement + "and c_url not in ( select p_url from url_relation_partition where" + " task_instance_id='"
				+ instanceId + "' and ruleid='" + parseRule2.getId() + "') order by c_url limit " + pageSize + " offset "
				+ ((pageNo - 1) * pageSize);
		try {
			Statement stmt = connection().createStatement();
			long start = System.currentTimeMillis();
			System.out.println("\n== Begin Query Results ======================\r\n"+sqlStatement+"\r\n");
			ResultSet rs = stmt.executeQuery(sqlStatement);
			while (rs.next()) {
				UrlRelationHistory history = new UrlRelationHistory();
				history.setDepth(rs.getInt(2));
				history.setInstanceId(instanceId);
				history.setParentUrl(rs.getString(4));
				history.setParseRuleId(parseRuleId1);
				history.setPublishTime(new Date(rs.getLong(3)));
				history.setSubUrl(rs.getString(1));
				historys.add(history);
			}
			long end = System.currentTimeMillis();
			rs.close();
			stmt.close();
			System.out.println("\n== End Query Results ======================\r\nuse time:"+((end-start)/1000)+"s");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return historys;
	}

	/**
	 * 
	 * @param instanceId
	 * @param parseRuleId1
	 * @param parseRuleId2
	 * @return
	 */
	public int countDiffer(String instanceId, String parseRuleId1, ParseRuleTemp parseRule2) {
		
		if(parseRule2==null) return -1;
		
		String sqlStatement = "select  count(*) from url_relation_partition  where task_instance_id='" + instanceId
				+ "' and ruleid='" + parseRuleId1
				+ "' ";
		
		sqlStatement = sqlStatement +addUrlPatternCond(parseRule2);
		
		sqlStatement = sqlStatement + "and c_url not in ( select p_url from url_relation_partition where" + " task_instance_id='"
				+ instanceId + "' and ruleid='" + parseRule2.getId() + "')";
		try {
			Statement stmt = connection().createStatement();
			
			long start = System.currentTimeMillis();
			System.out.println("\n== Begin Query Results ======================\r\n"+sqlStatement+"\r\n");	
			ResultSet rs = stmt.executeQuery(sqlStatement);
			while (rs.next()) {
				int count= rs.getInt(1);
				long end = System.currentTimeMillis();
				System.out.println("\n== End Query Results ======================\r\nuse time:"+((end-start)/1000)+"s");
				rs.close();
				stmt.close();
				return count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	/**
	 * 规则1的输出url集合，作为规则2的输入ulr集合时，有可能会产生漏采，其中漏采数据量比较大时，需要进行查询
	 * @param instanceId
	 * @param parseRuleId1
	 * @param parseRuleId2
	 * @param url
	 * @return
	 */
	public int countSearchDiffer(String instanceId, String parseRuleId1, ParseRuleTemp parseRule2,String url) {
		
		if(UrlParser.urlPatternFilter(parseRule2.getUrlPattern(), url)==false){
			return 0;
		}
		
		String sqlStatement = "select  count(*) from url_relation_partition  where task_instance_id='" + instanceId
				+ "' and ruleid='" + parseRuleId1
				+ "' and c_url='"+url+"'";
		try {
			Statement stmt = connection().createStatement();
			long start = System.currentTimeMillis();
			System.out.println("\n== Begin Query Results ======================\r\n"+sqlStatement+"\r\n");
			ResultSet rs = stmt.executeQuery(sqlStatement);
			int count1 = 0;
			while (rs.next()) {
				 count1 = rs.getInt(1);
				 break;
			}
			rs.close();
			
			long end = System.currentTimeMillis();
			System.out.println("\n== End Query Results ======================\r\nuse time:"+((end-start)/1000)+"s");
			
			sqlStatement = "select  count(*) from url_relation_partition  where task_instance_id='" + instanceId
					+ "' and ruleid='" + parseRule2.getId()
					+ "' and p_url='"+url+"'";
			int count2 = 0;
			start = System.currentTimeMillis();
			System.out.println("\n== Begin Query Results ======================\r\n"+sqlStatement+"\r\n");
			rs = stmt.executeQuery(sqlStatement);
			while (rs.next()) {
				 count2 = rs.getInt(1);
				 break;
			}
			end = System.currentTimeMillis();
			System.out.println("\n== End Query Results ======================\r\nuse time:"+((end-start)/1000)+"s");
			rs.close();
			stmt.close();
			if(count1<=0) return 0;
			if(count2==0) return count1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
