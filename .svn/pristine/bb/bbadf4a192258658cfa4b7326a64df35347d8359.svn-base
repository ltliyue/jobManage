package inspur.crawl.taskManage.service;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.taskManage.controller.TaskConstant;
import inspur.crawl.taskManage.mapper.CrawlerTaskConfigMapper;
import inspur.crawl.taskManage.mapper.CrawlerTaskMapper;
import inspur.crawl.taskManage.mapper.TaskFilterMapper;
import inspur.crawl.taskManage.mapper.TaskInstanceMapper;
import inspur.crawl.taskManage.mapper.TaskPartitionMapper;
import inspur.crawl.taskManage.pojo.CrawlerTask;
import inspur.crawl.taskManage.pojo.CrawlerTaskConfig;
import inspur.crawl.taskManage.pojo.CrawlerTaskCriteria;
import inspur.crawl.taskManage.pojo.TaskFilterKey;
import inspur.crawl.taskManage.pojo.TaskInstance;
import inspur.crawl.taskManage.pojo.TaskPartitionKey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspur.avro.model.java.UrlFetchRequest;
import com.inspur.avro.model.java.UrlType;
import com.inspur.bigdata.partitionConfig.CassandraTaskConfigOps;
import com.inspur.bigdata.partitionConfig.TaskConfig;
import com.inspur.kafka.crawl.model.TaskPartition;
import com.inspur.kafka.helpers.CalLargestOffset;
import com.inspur.kafka.main.KafkaProducerClient;

@Service
public class TaskService {

	@Resource
	CrawlerTaskMapper tMapper;
	@Resource
	TaskInstanceMapper iMapper;
	@Resource
	TaskPartitionMapper partitionMapper;
	@Resource
	TaskFilterMapper filterMapper;
	@Resource
	CrawlerTaskConfigMapper configMapper;
	@Resource
	private HttpSession session;
	@Resource
	private PartitionService partitionService;
	
	@Resource
	TaskInstanceService instanceService;
	/**
	 * 修改任务
	 * 
	 * @param task
	 * @return
	 */
	public int update(CrawlerTask task) {
		try {
			task.setLastOperateTime(new Date());
			return tMapper.updateByPrimaryKeySelective(task);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 通过实例ID获取任务ID
	 * 
	 * @param InstanceId
	 * @return
	 */
	public String getTaskIdByInstaceId(String InstanceId) {
		try {
			return tMapper.getTaskIdByInstaceId(InstanceId);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 查询任务列表(分页)
	 * 
	 * @param task
	 * @param page
	 * @return
	 */
	public List<CrawlerTask> findTasks(CrawlerTask task, Page page) {
		// TODO 加入查询参数
		return tMapper.listPageTask(task, page);
	}

	/**
	 * 查询所有有效任务(不分页) 用于下拉框选择等查询
	 * 
	 * @return
	 */
	public List<CrawlerTask> findTasks() {
		CrawlerTaskCriteria criteria = new CrawlerTaskCriteria();
		criteria.createCriteria()
				.andStatusNotEqualTo(TaskConstant.TASK_ABOLISH);
		return tMapper.selectByExample(criteria);
	}

	/**
	 * 新增任务
	 * 
	 * @param crawlerTask
	 * @return
	 */
	public long addTask(CrawlerTask crawlerTask) {
		// HttpServletRequest request = ( ((ServletRequestAttributes))
		// RequestContextHolder.getRequestAttributes()).getRequest();
		Date current = new Date();
		crawlerTask.setCreateTime(current);
		crawlerTask.setLastOperateTime(current);
		crawlerTask.setStatus(TaskConstant.TASK_CREATE);
		crawlerTask.setCreator(session.getAttribute(Sessions.SESSION_USER)
				.toString());
		tMapper.insert(crawlerTask);
		CrawlerTaskConfig config = new CrawlerTaskConfig();
		config.setTaskId(crawlerTask.getTaskId());
		config.setModifier(crawlerTask.getCreator());
		config.setModifyTime(current);
		configMapper.insert(config);
		return crawlerTask.getTaskId();
	}

	/**
	 * 终止任务
	 * 
	 * @param taskId
	 *            任务ID
	 * @param intanceId
	 *            当前实例ID
	 * @return
	 */
	public String instanceTerminate(Long taskId, String instanceId) {
		Date current = new Date();
		CrawlerTask task = new CrawlerTask();
		task.setTaskId(taskId);
		task.setStatus(TaskConstant.TASK_STOP);
		task.setLastOperateTime(current);
		tMapper.updateByPrimaryKeySelective(task);

		TaskInstance instance = new TaskInstance();
		instance.setTaskId(taskId);
		instance.setInstanceId(instanceId);
		;
		instance.setFinishTime(current);
		instance.setStatus(TaskConstant.TASK_STOP);
		iMapper.updateByPrimaryKeySelective(instance);
		// 同步至Cassandra
		CassandraTaskConfigOps.getDBops().checkPrepare();
		CassandraTaskConfigOps.getDBops().updateTaskStatus(taskId,
				TaskConstant.TASK_STOP);
		
		CassandraTaskConfigOps.close();
		
		//通知任务启动，改变缓存中的任务状态
		configNofity(task.getTaskId());
		
		return TaskConstant.SUCCESS;
	}
	
	
//	public static void main(String[] args) {
//		CassandraTaskConfigOps.getDBops().runPartition(240L);
//	}
	
	/**
	 * 废弃任务
	 * 
	 * @param taskId
	 */
	public String taskAbolish(Long taskId) {
		CrawlerTask task = tMapper.selectByPrimaryKey(taskId);
		if (task.getStatus() == TaskConstant.TASK_START) {
			return "无法删除正在采集中的任务，请先停止采集实例!";
		}
		tMapper.abolishTask(taskId, TaskConstant.TASK_ABOLISH);
		return TaskConstant.SUCCESS;
	}

	/**
	 * 启动采集任务
	 * 
	 * @param taskId
	 * @return
	 */
	@Transactional
	public String startTask(Long taskId, String publisherId) {
		Date current = new Date();
		// 生成采集阶段任务实例
		TaskInstance instance = new TaskInstance();
		instance.setTaskId(taskId);
		instance.setInstanceId(UUID.randomUUID().toString());
		instance.setStage(TaskConstant.InstanceStage.CRAWL);
		//edit by zhangyc 2016-05-12 定时任务没有session，无法获取当前用户，所以改为传值设置publisherId
		instance.setPublisherId(publisherId);
		instance.setPublishTime(current);
		instance.setStatus(TaskConstant.TASK_START);
		iMapper.insert(instance);
		
		instanceService.insertOtherInstances(instance);

		CrawlerTask task = tMapper.selectByPrimaryKey(taskId);
		task.setCurrentInstance(instance.getInstanceId());
		task.setStatus(TaskConstant.TASK_START);
		task.setLastOperateTime(current);
		tMapper.updateByPrimaryKeySelective(task);

		CrawlerTaskConfig config = configMapper.selectByPrimaryKey(taskId);

		// 发送消息至KAFKA
		UrlFetchRequest request = new UrlFetchRequest();
		request.setTaskId(taskId);
		request.setCurrentDepth(TaskConstant.START_DEPTH);
		request.setFailureCount(TaskConstant.START_FAILURE_COUNT);
		request.setMaxDepth(config.getMaxDepth());
		request.setHttpMethodName(config.getTransmitMode());
		request.setPublisherId(instance.getPublisherId());
		request.setPublishTime(current.getTime());
		request.setTaskInstanceId(instance.getInstanceId());
		request.setUrl(task.getUrl());
		request.setUrlHash(task.getUrl().hashCode());
		request.setUrlType(UrlType.NAV);
		request.setDownloadTool(config.getCrawlerType());
		// 同步Cassandra
		CassandraTaskConfigOps.getDBops().checkPrepare();
		CassandraTaskConfigOps.getDBops().updateTaskStatus(taskId,
				TaskConstant.TASK_START);
		//根据任务ID进行分区计算并插入Cassandra数据库
		CassandraTaskConfigOps.getDBops().runPartition(taskId);
		//通知任务启动，改变缓存中的任务状态
		configNofity(task.getTaskId());
		
		sendUrlRequest(request);
		CassandraTaskConfigOps.close();
		return TaskConstant.SUCCESS;
	}

	/**
	 * 根据taksId获得task
	 * 
	 * @param taskId
	 * @return
	 */
	public CrawlerTask getTaskById(Long taskId) {
		return tMapper.selectByPrimaryKey(taskId);
	}

	/**
	 * 根据taskId获得任务对应的kafka分区信息
	 * 
	 * @param taskId
	 * @return
	 */
	public List<TaskPartitionKey> getTaskPartitions(Long taskId) {
		String partitions = partitionMapper.selectByTaskId(taskId);
		Integer[] allPartitions = TaskConstant.PARTITIONS;
		List<TaskPartitionKey> list = new ArrayList<>();
		if (partitions != null && !"".equals(partitions)) {
			String[] selectedPartitions = partitions.split(",");
			for (Integer p : allPartitions) {
				if (ArrayUtils.contains(selectedPartitions, p.toString())) {
					list.add(new TaskPartitionKey(taskId, p));
				} else {
					list.add(new TaskPartitionKey(p));
				}

			}
		} else {
			for (Integer p : allPartitions) {
				list.add(new TaskPartitionKey(p));
			}
		}

		return list;
	}

	public String getTaskPartitionString(Long taskId) {
		return partitionMapper.selectByTaskId(taskId);
	}

	/**
	 * 更新间隔和分区等配置
	 * 
	 * @param crawlerTask
	 *            ,crawlerTaskConfig
	 * @return
	 */
	@Transactional
	public String updateTaskConfig(CrawlerTask crawlerTask,
			CrawlerTaskConfig crawlerTaskConfig) {
		Date cdate = new Date();
		crawlerTask.setLastOperateTime(cdate);
		// 更新任务配置信息
		tMapper.updateByPrimaryKeySelective(crawlerTask);
		// 从系统获得修改人
		crawlerTaskConfig.setModifyTime(cdate);
		crawlerTaskConfig.setModifier(session.getAttribute(
				Sessions.SESSION_USER).toString());
		configMapper.updateByPrimaryKey(crawlerTaskConfig);
		
		// 更新任务url过滤规则配置信息
		filterMapper.deleteByTaskId(crawlerTask.getTaskId());
		List<String> urlFilters = new ArrayList<String>();
		if (crawlerTask.getUrlFilters() != null
				&& !"".equals(crawlerTask.getUrlFilters())) {
			String[] filters = crawlerTask.getUrlFilters().split(",");
			for (String filter : filters) {
				TaskFilterKey key = new TaskFilterKey(crawlerTask.getTaskId(),
						filter);
				filterMapper.insert(key);
				urlFilters.add(filter);
			}
		}

		// 查询最新task信息
		// crawlerTask = getTaskById(crawlerTask.getTaskId());
		TaskConfig config = new TaskConfig();

		config.setTaskId(crawlerTask.getTaskId());
		config.setEffectiveDate(cdate);
		config.setStatus(crawlerTask.getStatus());
		config.setInterval(crawlerTaskConfig.getInterval());
		config.setCrawlerType(crawlerTaskConfig.getCrawlerType());
		config.setExecuteJs(crawlerTaskConfig.getExecuteJs());
		config.setCrawlerScope(crawlerTaskConfig.getCrawlerScope());
		// 同步Cassandra
		CassandraTaskConfigOps.getDBops().checkPrepare();
		CassandraTaskConfigOps.getDBops().postConfig(config);
		CassandraTaskConfigOps.getDBops().postUrlFilter(
				crawlerTask.getTaskId(), urlFilters);
		CassandraTaskConfigOps.close();
		// 发送消息至kafka
		configNofity(crawlerTask.getTaskId());
		return TaskConstant.SUCCESS;
	}

	@Transactional
	public String updateTaskConfigForPeriodAndPartitions(CrawlerTask crawlerTask,
			CrawlerTaskConfig taskConfig) {
		Date cdate = new Date();
		// 从系统获得修改人
		taskConfig.setModifyTime(cdate);
		taskConfig.setModifier(session.getAttribute(
				Sessions.SESSION_USER).toString());
		configMapper.updateByPrimaryKeySelective(taskConfig);
		
		taskConfig = configMapper.selectByPrimaryKey(taskConfig.getTaskId());
		// 更新任务分区配置信息,如果选择分组则更新，不选择就不更新
		Set<Integer> ps = new HashSet<Integer>();
		if (crawlerTask.getGroups() != null
				&& !"".equals(crawlerTask.getGroups())) {
			partitionMapper.deleteByTaskId(crawlerTask.getTaskId());
			String[] partitions = partitionService
					.getPartitionsFromGroup(crawlerTask.getGroups());
			// crawlerTask.getPartitions().split(",");
			for (String partition : partitions) {
				TaskPartitionKey key = new TaskPartitionKey(
						crawlerTask.getTaskId(), Integer.parseInt(partition));
				partitionMapper.insert(key);
				ps.add(Integer.parseInt(partition));
			}
		}else {
			if(crawlerTask.getPartitions()!=null
			&&!"".equals(crawlerTask.getPartitions())) {
				String[] partitions = crawlerTask.getPartitions().split(",");
				for (String partition : partitions) {
					TaskPartitionKey key = new TaskPartitionKey(
							crawlerTask.getTaskId(), Integer.parseInt(partition));
					partitionMapper.insert(key);
					ps.add(Integer.parseInt(partition));
				}
			}
		}
		// 查询最新task信息
		// crawlerTask = getTaskById(crawlerTask.getTaskId());
		TaskConfig config = new TaskConfig();

		config.setTaskId(crawlerTask.getTaskId());
		config.setEffectiveDate(cdate);
		config.setPartitions(ps);
//		config.setInterval(taskConfig.getInterval());
//		config.setCrawlerType(taskConfig.getCrawlerType());
//		config.setExecuteJs(taskConfig.getExecuteJs());
//		config.setCrawlerScope(taskConfig.getCrawlerScope());
		// 同步Cassandra
		CassandraTaskConfigOps.getDBops().checkPrepare();
		CassandraTaskConfigOps.getDBops().postPartitions(config);
		CassandraTaskConfigOps.close();
		// 发送消息至kafka
		configNofity(crawlerTask.getTaskId());
		return TaskConstant.SUCCESS;
	}
	/**
	 * 将任务ID发送至kafka
	 * 
	 * @param taskId
	 */
	public void configNofity(Long taskId) {
		int partition = KafkaProducerClient.caculatePartition(
				TaskConstant.TOPIC_TASK_CONFIG, taskId.hashCode(), taskId);
		// kafka分区发送任务ID，提醒任务配置变更
		KafkaProducerClient.sendToKafka(TaskConstant.TOPIC_TASK_CONFIG,
				partition, String.valueOf(taskId), String.valueOf(taskId));
	}

	/**
	 * 发送任务至kafka
	 * 
	 * @param request
	 */
	public void sendUrlRequest(UrlFetchRequest request) {
		int partition = KafkaProducerClient.caculatePartition(
				TaskConstant.URL_TASK, request.getTaskId().hashCode(),
				request.getTaskId());
		// kafka分区发送任务ID，提醒任务配置变更
		Object obj = KafkaProducerClient.sendToKafka(TaskConstant.URL_TASK,
				partition,
				request.getTaskId() + "|" + request.getTaskInstanceId() + "|"
						+ request.getUrl(), request);
		System.out.println(obj.toString());
	}

	/**
	 * 查询任务实例
	 * 
	 * @param taskInstance
	 * @param page
	 * @return
	 */
	public List<TaskInstance> findTaskInstances(TaskInstance taskInstance,
			Page page) {
		return iMapper.listPageInstance(taskInstance, page);
	}

	/**
	 * 查询任务url过滤规则，拼成string
	 * 
	 * @param taskId
	 * @return
	 */
	public String getTaskUrlFilterString(Long taskId) {
		return filterMapper.selectStringByTaskId(taskId);
	}

	/**
	 * 查询任务的配置信息
	 * 
	 * @param taskId
	 * @return
	 */
	public CrawlerTaskConfig getTaskConfig(Long taskId) {
		//TODO 判断是否生成任务配置，如果没有的话先生成配置数据
		CrawlerTaskConfig config = configMapper.selectByPrimaryKey(taskId);
		if(config == null) {
			config = new CrawlerTaskConfig();
			config.setTaskId(taskId);
			config.setModifier(session.getAttribute(Sessions.SESSION_USER)
				.toString());
			config.setModifyTime(new Date());
			configMapper.insert(config);
		}
		return config;
	}

	/**
	 * 统计任务，以status为维度分组
	 * @param crawlerTask
	 * @return
	 */
	public Map<String, BigDecimal> countTaskByStatus(CrawlerTask crawlerTask) {
		List<Map<String, BigDecimal>> countList = tMapper.countByStatus(crawlerTask);
		Map<String, BigDecimal> statusCount = new HashMap<>();
		for(Map<String, BigDecimal> count:countList) {
			statusCount.put(count.get("STATUS").toString(), count.get("COUNT"));
		}
		return statusCount;
	}
	
	/**
	 * 统计任务，以period采集频率为维度分组
	 * @param crawlerTask
	 * @return
	 */
	public Map<String, BigDecimal> countTaskByPeriod(CrawlerTask crawlerTask) {
		List<Map<String, BigDecimal>> countList = tMapper.countByPeriod(crawlerTask);
		Map<String, BigDecimal> periodCount = new HashMap<>();
		for(Map<String, BigDecimal> count:countList) {
			periodCount.put(count.get("PERIOD").toString(), count.get("COUNT"));
		}
		return periodCount;
	}

	/**
	 * 获取当前session用户
	 * @return
	 */
	public String getSessionUser() {
		try {
			return session.getAttribute(
					Sessions.SESSION_USER).toString();
		} catch (Exception e) {
			System.out.println("获取当前用户出错");
			e.printStackTrace();
		}
		return null;
	}
	
}
