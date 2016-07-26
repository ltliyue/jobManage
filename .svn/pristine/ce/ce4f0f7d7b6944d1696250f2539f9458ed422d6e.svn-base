package com.inspur.bigdata.partitionConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.SimpleStatement;
import com.inspur.kafka.crawl.memory.CassandraOpsBase;
import com.inspur.kafka.crawl.model.TaskPartition;
import com.inspur.kafka.helpers.CalLargestOffset;

/**
 * Cassandra TaskPartitionsOps数据接口实现类
 */
public class CassandraTaskConfigOps extends CassandraOpsBase 
	implements TaskConfigOps {

	//分区查询cql
	private static PreparedStatement prepareFetchLast = null;
	//配置提交cql
	private static PreparedStatement postConfigCql = null;
	//任务停止cql
	private static PreparedStatement stopTaskCql = null;
	//任务启动cql
	private static PreparedStatement startTaskCql = null;
	//url过滤规则提交cql
	private static PreparedStatement postFilterCql = null;
	//
	private static PreparedStatement prepareQueryPartitions = null;
	//
	private static SimpleStatement prepareQueryPartitionsWithoutTask = null;
	//
	private static PreparedStatement prepareInsertPartitions = null;
	
	private static Integer partitions[] = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19}; 
	
	private CassandraTaskConfigOps() {
		
	}
	
	private static CassandraTaskConfigOps ops = null;
	
	public static CassandraTaskConfigOps getDBops() {
		if(ops == null) {
			ops = new CassandraTaskConfigOps();
		}
		return ops;
	}
	
	public void checkPrepare() {
		if(this.session==null || this.cluster==null || this.cluster.isClosed()) {
    		prepare();
    	}
	}
	
	/**
	 * 根据任务ID查询最新分区信息
	 */
	public TaskConfig fetchLastByTaskId(Long taskId) {
//		if(prepareFetchLast == null) {
			prepareFetchLast = session.prepare("SELECT partitions,interval FROM task_config WHERE task_id=? limit 1");
//		}
        BoundStatement boundStatement = prepareFetchLast.bind(taskId.toString());
        ResultSet results = session.execute(boundStatement);
        TaskConfig taskInfo = new TaskConfig();
        taskInfo.setTaskId(taskId);
        if(results!=null && !results.isExhausted()) {
        	Row row = results.one();
        	taskInfo.setPartitions(row.getSet("partitions", Integer.class));
        	//taskInfo.setEffectiveDate(row.getDate("effective_date"));
        	taskInfo.setInterval(row.getLong("interval"));
        }
        return taskInfo;
	}

	/**
	 * 根据任务ID查询最新limit条分区信息
	 * @param limit 查询条数
	 */
	public List<TaskConfig> fetchByTaskId(Long taskId, int limit) {
		List<TaskConfig> taskPartitions = new ArrayList<TaskConfig>();
		PreparedStatement preparedStatement = session.prepare("SELECT partitions FROM task_config WHERE task_id=? limit "+limit);
        BoundStatement boundStatement = preparedStatement.bind(taskId.toString());
        ResultSet results = session.execute(boundStatement);
        for (Row row : results) {
        	TaskConfig taskInfo = new TaskConfig();
        	taskInfo.setTaskId(taskId);
        	taskInfo.setPartitions(row.getSet("partitions", Integer.class));
        	//taskInfo.setEffectiveDate(row.getDate("effective_date"));
        	taskPartitions.add(taskInfo);
        }
        return taskPartitions;
	}

	/**
	 * 同步配置信息至Cassandra数据库
	 */
	public void postConfig(TaskConfig taskConfig) {
//		if(postConfigCql==null) {
			postConfigCql = session.prepare(
					"insert into task_config(task_id, \n" +
                    "    effective_date, \n" +
                    "    interval,\n" +
                    "    crawler_type,\n" +
                    "    execute_js,\n" +
                    "    crawler_scope\n" +
                    ")\n" +
                    "values(?, ?, ?, ?, ?,?)");
//		}
		
		BoundStatement boundStatement = postConfigCql.bind(taskConfig.getTaskId().toString(), taskConfig.getEffectiveDate(),
				taskConfig.getInterval(), taskConfig.getCrawlerType(),
				taskConfig.getExecuteJs(),taskConfig.getCrawlerScope());
		
		this.session.execute(boundStatement);
	}
	
	public void postPartitions(TaskConfig taskConfig) {
//		if(postConfigCql==null) {
			postConfigCql = session.prepare(
					"insert into task_config(task_id, \n" +
					"    partitions\n" +
                    ")\n" +
                    "values(?,?)");
//		}
		
		BoundStatement boundStatement = postConfigCql.bind(taskConfig.getTaskId().toString(), taskConfig.getPartitions());
		
		this.session.execute(boundStatement);
	}
	
	/**
	 * 同步url过滤规则至Cassandra数据库
	 */
	public void postUrlFilter(Long taskId, List<String> filters) {
//		if(postFilterCql==null) {
			postFilterCql = session.prepare(
					"insert into url_filters(task_id, \n" +
                    "    filters\n" +
                    ")\n" +
                    "values(?, ?)");
//		}
		
		BoundStatement boundStatement = postFilterCql.bind(taskId.toString(), filters);
		this.session.execute(boundStatement);
	}
	
	/**
	 * 同步停止信息至Cassandra数据库
	 */
	public void updateTaskStatus(Long taskId, int status) {
//		if(stopTaskCql==null) {
			stopTaskCql = session.prepare(
					"update task_config set status=? where task_id=?");
//		}
		
		BoundStatement boundStatement = stopTaskCql.bind(status, taskId.toString());
		this.session.execute(boundStatement);
	}
	
	public synchronized int runPartition(Long taskId){
		//从Cassandra查询现在占用分区
//		CassandraTaskConfigOps cassandraOps = CassandraTaskConfigOps.getDBops();
		List<TaskPartition> taskPartition = queryPartition(taskId, 1);
		int partition = 0 ;
		if(taskPartition.size()>0){
			partition = taskPartition.get(0).getPartition();
		}else{
			List<Integer> partitionList = queryUsePartitions();
			List<Integer> freeList = new ArrayList<Integer>(Arrays.asList(partitions));
			for (int i = 0; i < partitionList.size(); i++) {
				int a = Arrays.binarySearch(partitions, partitionList.get(i));
				if(a>=0){
					freeList.remove(partitionList.get(i));
				}
			}
			//不存在空闲分区时，对分区取offset值最小的分区
			if(freeList.size()<=0){
				System.out.println("进入缺乏空闲分区状态，计算偏移量获取合适分区！！！");
				Map<Integer, Long> map = new HashMap<Integer, Long>();
				List<Long> offsets = new ArrayList<Long>();
				for(Integer ipartition : partitions){
					CalLargestOffset clo = new CalLargestOffset();
					long lastOffset = clo.findGroupTopicOffsetInfo(ipartition);
					if(lastOffset>0){
						map.put(ipartition, lastOffset);
						offsets.add(lastOffset);
						System.out.println("partition:"+ipartition+"lastOffset:"+lastOffset);
					}
				}
				long minOffset = Collections.min(offsets);
				for(Map.Entry<Integer, Long> entry : map.entrySet()){
					if(entry.getValue()==minOffset){
						partition = entry.getKey();
					}
				}
			}else{
				System.out.println("获取空闲分区中的最小值。");
//				Random ra =new Random();
		//		int partition = freeList.get((int) (Math.random()*freeList.size()));
//				partition = freeList.get(ra.nextInt(freeList.size()));
				partition = Collections.min(freeList);
			}

			//插入任务和分区关系
			insertTaskPartition(taskId,partition);
		}

//		cassandraOps.close();
		System.out.println("分区号："+partition);

		return partition;
	}
	
	public List<TaskPartition> queryPartition(Long taskId, int limit){
//		if(prepareQueryPartitions == null) {
			String sql = "select * from taskpartition_relation where task_id =? and status = 0 LIMIT ?";
			prepareQueryPartitions = session.prepare(sql);
//		}
		BoundStatement boundStatement = prepareQueryPartitions.bind(taskId, limit);
		ResultSet results = session.execute(boundStatement);
		List<TaskPartition> taskPartition = new ArrayList<TaskPartition>();
		for(Row row : results){
			TaskPartition tp =new TaskPartition();
			tp.setTaskId(taskId);
			tp.setPartition(row.getInt("partition"));
			tp.setStatus(row.getInt("status"));
			taskPartition.add(tp);
		}
		return taskPartition;
	}

	public List<Integer> queryUsePartitions() {
		List<Integer> list = new ArrayList<Integer>();
//		if(prepareQueryPartitionsWithoutTask == null) {
			String sql = "select partition from taskpartition_relation where status = 0";
			prepareQueryPartitionsWithoutTask = new SimpleStatement(sql);
//		}
		ResultSet results = session.execute(prepareQueryPartitionsWithoutTask);
		for(Row row : results){
			list.add(row.getInt("partition"));
		}
		return list;
	}

	public void insertTaskPartition(Long taskId, int partition) {
//		if(prepareInsertPartitions == null) {
			String sql = "insert into taskpartition_relation (task_id, partition, status, modify_time) values (?, ?, ?, ?)";
			prepareInsertPartitions = session.prepare(sql);
//		}
		BoundStatement boundStatement = prepareInsertPartitions.bind(taskId, partition, 0, new Date());
		session.execute(boundStatement);
	}

}
