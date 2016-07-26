package inspur.crawl.taskManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.demandAna.pojo.CrawlerDemand;
import inspur.crawl.demandAna.service.DemandAnalyService;
import inspur.crawl.siteManage.pojo.RealSiteCode;
import inspur.crawl.siteManage.service.RealSiteService;
import inspur.crawl.taskManage.pojo.CrawlerTask;
import inspur.crawl.taskManage.pojo.CrawlerTaskConfig;
import inspur.crawl.taskManage.pojo.PartitionGroup;
import inspur.crawl.taskManage.pojo.TaskInstance;
import inspur.crawl.taskManage.pojo.TaskPartitionKey;
import inspur.crawl.taskManage.service.PartitionService;
import inspur.crawl.taskManage.service.TaskService;

@RestController
@RequestMapping("/datatask")
public class TaskController extends BaseController{
	
	@Resource
	TaskService taskService;
	@Resource
	RealSiteService siteService;
	@Resource
	PartitionService partitionService;
	@Resource
	DemandAnalyService demandService;
	
	@InitBinder("crawlerTask")  
	public void taskBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("crawlerTask.");  
	}
	
	@InitBinder("taskInstance")  
	public void instanceBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("taskInstance.");  
	}
	
	@InitBinder("crawlerTaskConfig")  
	public void configBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("taskConfig.");  
	}
	
	@InitBinder("page")  
	public void pageBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("page.");  
	}
	/**
	 * 任务管理
	 * pageType:0 任务确立 1任务调度
	 */
	@RequestMapping(value = "/settask", method = RequestMethod.GET)
	public ModelAndView setauthority(CrawlerTask crawlerTask, Page page, boolean goSetting, Integer pageType) throws Exception {
		List<CrawlerTask> list = taskService.findTasks(crawlerTask, page);
		Map countStatus = taskService.countTaskByStatus(crawlerTask);
		Map countPeriod = taskService.countTaskByPeriod(crawlerTask);
		
		List<RealSiteCode> sites = siteService.queryAllEntrance();
		
		if(pageType == null) {
			pageType = 0;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<CrawlerDemand> demands = demandService.queryDemand();
		map.put("tasks", list);
		map.put("page", page);
		map.put("goSetting", goSetting);
		map.put("countStatus", countStatus);
		map.put("countPeriod", countPeriod);
		map.put("sites", sites);
		map.put("pageType", pageType);
		map.put("demands", demands);
		return request("sys/taskManage/taskManage",map);
	}
	
	/**
	 * 新建任务页面
	 */
	@RequestMapping(value = "/taskadd", method = RequestMethod.GET)
	public ModelAndView taskAdd() throws Exception {
		List<RealSiteCode> sites = siteService.queryAllEntrance();
		return request("sys/taskManage/taskAdd").addObject("sites", sites);
	}
	/**
	 * 临时演示
	 */
	@RequestMapping(value = "/return1", method = RequestMethod.GET)
	public ModelAndView returns() throws Exception {
		return request("sys/index_dsfcj");
	}
	/**
	 * 任务结果
	 */
	@RequestMapping(value = "/taskresult", method = RequestMethod.GET)
	public ModelAndView taskresult(CrawlerTask crawlerTask, Page page) throws Exception {
		List<CrawlerTask> list = taskService.findTasks(crawlerTask, page);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("tasks", list);
		map.put("page", page);
		Map countStatus = taskService.countTaskByStatus(crawlerTask);
		Map countPeriod = taskService.countTaskByPeriod(crawlerTask);
		
		List<RealSiteCode> sites = siteService.queryAllEntrance();
		map.put("countStatus", countStatus);
		map.put("countPeriod", countPeriod);
		map.put("sites", sites);
		return request("sys/taskManage/taskResults",map);
	}
	
	/**
	 * 任务实例
	 */
	@RequestMapping(value = "/taskInstanceList", method = RequestMethod.GET)
	public ModelAndView taskInstanceList(TaskInstance taskInstance, Page page) throws Exception {
		List<TaskInstance> list = taskService.findTaskInstances(taskInstance, page);
		CrawlerTask task = taskService.getTaskById(taskInstance.getTaskId());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("instances", list);
		map.put("page", page);
		map.put("task", task);
		return request("sys/taskManage/taskInstanceList",map);
	}
	
	/**
	 * 新建任务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/taskaddsub", method = RequestMethod.POST)
	@ResponseBody
	public long taskAddSub(CrawlerTask crawlerTask) throws Exception {
		long i = taskService.addTask(crawlerTask);
		return i;
	}
	
	/**
	 * 修改任务
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "/taskupdate", method = RequestMethod.POST)
	@ResponseBody
	public String taskUpdate(@RequestParam String flag,CrawlerTask crawlerTask) throws Exception {
		int	i  = taskService.update(crawlerTask);
		if(i==1) {
			return TaskConstant.SUCCESS;
		}
		return TaskConstant.FAIL;
	}
	
	/**
	 * 启动任务
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "/startTask", method = RequestMethod.POST)
	@ResponseBody
	public String startTask(Long taskId) throws Exception {
		try {
			return taskService.startTask(taskId, taskService.getSessionUser());
		} catch (Exception e) {
			return "操作异常："+e.getMessage();
		}
	}
	
	/**
	 * 终止任务
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "/instanceTerminate", method = RequestMethod.POST)
	@ResponseBody
	public String instanceTerminate(Long taskId, String intanceId) throws Exception {
		try {
			return taskService.instanceTerminate(taskId, intanceId);
		} catch (Exception e) {
			LOG.error("操作异常："+e.getMessage());
		}
		return TaskConstant.FAIL;
	}
	
	/**
	 * 废弃任务
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "/taskAbolish", method = RequestMethod.POST)
	@ResponseBody
	public String taskAbolish(Long taskId) throws Exception {
		try {
			return taskService.taskAbolish(taskId);
		} catch (Exception e) {
			return "操作异常："+e.getMessage();
		}
	}
	
	/**
	 * 任务创建后跳转配置页面
	 */
	@RequestMapping(value = "/taskConfigInit", method = RequestMethod.GET)
	public ModelAndView taskConfigInit(Long taskId) throws Exception {
		return request("sys/taskManage/taskConfigInit").addObject("taskId", taskId);
	}
	
	/**
	 * 任务配置
	 */
	@RequestMapping(value = "/taskConfigSub", method = RequestMethod.POST)
	@ResponseBody
	public String taskConfigSub(CrawlerTask crawlerTask, CrawlerTaskConfig taskConfig) throws Exception {
		try {
			return taskService.updateTaskConfig(crawlerTask, taskConfig);
		} catch (Exception e) {
			return "操作异常："+e.getMessage();
		}
	}
	
	/**
	 * 任务调度配置
	 */
	@RequestMapping(value = "/taskControlConfigSub", method = RequestMethod.POST)
	@ResponseBody
	public String taskControlConfigSub(CrawlerTask crawlerTask, CrawlerTaskConfig taskConfig) throws Exception {
		try {
			return taskService.updateTaskConfigForPeriodAndPartitions(crawlerTask, taskConfig);
		} catch (Exception e) {
			return "操作异常："+e.getMessage();
		}
	}
	
	/**
	 * 任务监控
	 */
	@RequestMapping(value = "/taskMonitor", method = RequestMethod.POST)
	public ModelAndView taskMonitor(CrawlerTask crawlerTask) throws Exception {
		return request("sys/taskManage/taskMonitor");
	}
	
	/**
	 * 任务监控:异步获取监控信息
	 */
	@RequestMapping(value = "/taskMonitorLoad", method = RequestMethod.POST)
	@ResponseBody
	public String taskMonitorLoad(CrawlerTask crawlerTask) throws Exception {
		return "";
	}
	
	/**
	 * 任务配置
	 */
	@RequestMapping(value = "/taskConfigModal", method = RequestMethod.GET)
	public ModelAndView taskConfigModal(Long taskId) throws Exception {
		CrawlerTask task = taskService.getTaskById(taskId);
		CrawlerTaskConfig config = taskService.getTaskConfig(taskId);
		//List<TaskPartitionKey> partitions = taskService.getTaskPartitions(taskId);
		//String partitions = taskService.getTaskPartitionString(taskId);
		//List<PartitionGroup> groups = partitionService.findAllGroup();
		String filters = taskService.getTaskUrlFilterString(taskId);
		return request("sys/taskManage/taskConfigModal").addObject("task", task)
				.addObject("config", config)
				//.addObject("partitions", partitions)
				//.addObject("groups", groups)
				.addObject("urlFilters", filters);
	}
	
	/**
	 * 任务调度配置
	 */
	@RequestMapping(value = "/taskControlConfig", method = RequestMethod.GET)
	public ModelAndView taskControlConfig(Long taskId) throws Exception {
		CrawlerTask task = taskService.getTaskById(taskId);
		CrawlerTaskConfig config = taskService.getTaskConfig(taskId);
		//List<TaskPartitionKey> partitions = taskService.getTaskPartitions(taskId);
		String partitions = taskService.getTaskPartitionString(taskId);
		List<PartitionGroup> groups = partitionService.findAllGroup();
		//String filters = taskService.getTaskUrlFilterString(taskId);
		return request("sys/taskManage/taskControlConfig").addObject("task", task)
				.addObject("config", config)
				.addObject("partitions", partitions)
				.addObject("groups", groups);
	}
}
