package inspur.crawl.taskManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import inspur.crawl.taskManage.controller.TaskConstant;
import inspur.crawl.taskManage.mapper.TaskInstanceMapper;
import inspur.crawl.taskManage.pojo.TaskInstance;
import inspur.crawl.taskManage.pojo.TaskInstanceCriteria;
import inspur.crawl.taskManage.pojo.TaskInstanceKey;

@Service
public class TaskInstanceService {

	@Resource
	TaskInstanceMapper instanceMapper;
	
	public void insertOtherInstances(TaskInstance instance) {

		//合并
		TaskInstance mergeInstance = new TaskInstance(); 
		mergeInstance.setInstanceId(instance.getInstanceId());
		mergeInstance.setTaskId(instance.getTaskId());
		mergeInstance.setStage(TaskConstant.InstanceStage.MERGE);
		mergeInstance.setStatus(TaskConstant.TASK_WAIT);
		mergeInstance.setPublisherId(instance.getPublisherId());
		mergeInstance.setPublishTime(instance.getPublishTime());
		//抽取
		TaskInstance extractInstance = new TaskInstance(); 
		extractInstance.setInstanceId(instance.getInstanceId());
		extractInstance.setTaskId(instance.getTaskId());
		extractInstance.setStage(TaskConstant.InstanceStage.EXTRACT);
		extractInstance.setStatus(TaskConstant.TASK_WAIT);
		extractInstance.setPublisherId(instance.getPublisherId());
		extractInstance.setPublishTime(instance.getPublishTime());
		
		//预处理
		TaskInstance processInstance = new TaskInstance(); 
		processInstance.setInstanceId(instance.getInstanceId());
		processInstance.setTaskId(instance.getTaskId());
		processInstance.setStage(TaskConstant.InstanceStage.PROCESS);
		processInstance.setStatus(TaskConstant.TASK_WAIT);
		processInstance.setPublisherId(instance.getPublisherId());
		processInstance.setPublishTime(instance.getPublishTime());
		//清洗处理
		TaskInstance handleInstance = new TaskInstance(); 
		handleInstance.setInstanceId(instance.getInstanceId());
		handleInstance.setTaskId(instance.getTaskId());
		handleInstance.setStage(TaskConstant.InstanceStage.HANDLE);
		handleInstance.setStatus(TaskConstant.TASK_WAIT);
		handleInstance.setPublisherId(instance.getPublisherId());
		handleInstance.setPublishTime(instance.getPublishTime());
		
		instanceMapper.insert(mergeInstance);
		instanceMapper.insert(extractInstance);
		instanceMapper.insert(processInstance);
		instanceMapper.insert(handleInstance);
	}
	
	/**
	 * 修改任务状态实例
	 */
	public int update(TaskInstance instance){
		TaskInstanceCriteria tic = new TaskInstanceCriteria();
		tic.createCriteria().andInstanceIdEqualTo(instance.getInstanceId()).andStageEqualTo(instance.getStage());
		return instanceMapper.updateByExampleSelective(instance,tic);
	}
	
	/**
	 * 
	 */
	
	public TaskInstance selectByPrimaryKey(TaskInstanceKey key){
		return instanceMapper.selectByPrimaryKey(key);
	}
	
	/**
	 * 根据任务id和阶段名查询任务实例
	 * @param taskId
	 * @return
	 */
	public List<TaskInstance> getInstaces(Long taskId,int stage)
	{
		TaskInstanceCriteria tic = new TaskInstanceCriteria();
		tic.createCriteria().andTaskIdEqualTo(taskId).andStageEqualTo(stage);
		tic.setOrderByClause("PUBLISH_TIME desc");
		return instanceMapper.selectByExample(tic);	
	}
	
}
