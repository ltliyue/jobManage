package inspur.crawl.contentExtract.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import inspur.crawl.dataManage.service.DataSourceService;
import inspur.crawl.taskManage.controller.TaskConstant;
import inspur.crawl.taskManage.pojo.TaskInstance;
import inspur.crawl.taskManage.pojo.TaskInstanceKey;
import inspur.crawl.taskManage.service.TaskInstanceService;

@RestController
@RequestMapping("/contentextract")
public class ContentExtractController {
	private static String logdir = "/home/inspur/mrjob/logs/rest_content_extract_job";
	private static String rest_host = "";
	// 处理阶段；0：采集；1：转储；2：合并；3：抽取；4：清洗整合；5交付
	//private static int CONTENT_EXTRACT_STAGE = 3;

	@Resource
	DataSourceService dataSourceService;

	//@Resource
	//CrawlerCombineInstanceService service;

	@Resource
	TaskInstanceService instanceService;

	static {
		Properties prop = new Properties();
		InputStream s = ContentExtractController.class.getResourceAsStream("/restful.properties");
		if (s == null) {
			throw new RuntimeException("Cannot find restful.properties");
		}
		try {
			prop.load(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		rest_host = prop.getProperty("rest.url", "http://172.22.5.2:7001/rest");
	}

	@InitBinder("contentextract")
	public void typeBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("contentextract.");
	}

	/**
	 * 查询所有实例
	 * 
	 * @param taskid
	 * @return
	 */
	@RequestMapping(value = "/cxinstance", method = RequestMethod.POST)
	@ResponseBody
	public List<TaskInstance[]> cxinstance(String taskid) {
		List<TaskInstance> extractList = instanceService.getInstaces(Long.valueOf(taskid),TaskConstant.InstanceStage.EXTRACT);
		List<TaskInstance> crawlList = instanceService.getInstaces(Long.valueOf(taskid),TaskConstant.InstanceStage.CRAWL);
		Map<String, TaskInstance> extractMap = new LinkedHashMap<String,TaskInstance>();
		Map<String, TaskInstance> crawlMap =  new LinkedHashMap<String,TaskInstance>();
		for(TaskInstance instance:extractList){
			extractMap.put(instance.getInstanceId(),instance);
		}
		for(TaskInstance instance:crawlList){
			crawlMap.put(instance.getInstanceId(),instance);
		}
		
		List<TaskInstance[]> list = new ArrayList<TaskInstance[]>();
		for(String instance:crawlMap.keySet()){
			if(extractMap.containsKey(instance)){
				TaskInstance[] instances = new TaskInstance[2];
				instances[0] = extractMap.get(instance);
				instances[1] = crawlMap.get(instance);
				list.add(instances);
			}
		}
		return list;
	}

	/*
	 * 文件合并
	 */
	@RequestMapping(value = "/contentextract", method = RequestMethod.GET)
	public ModelAndView contentExtract() throws Exception {
		return new ModelAndView("sys/contentExtract/contentExtract");
	}

	
	@RequestMapping(value = "/runextract", method = RequestMethod.POST)
	@ResponseBody
	/**
	 * 
	 * @param instanceId
	 * @param extractType 是否进行增量抽取
	 * @return
	 * @throws Exception
	 */
	public String runContentExtract(String instanceId, String extractType) throws Exception {
		Client c = Client.create();
		if (instanceId != null && instanceId.isEmpty() == false) {
			// TODO:首先重置任务状态,每个实例的统计数据在oracle中的 crawler_task_instance表中
			// 重置，将所有的处理数据的总量置为0
			TaskInstanceKey instanceKey = new TaskInstanceKey();
			instanceKey.setInstanceId(instanceId);
			instanceKey.setStage(TaskConstant.InstanceStage.EXTRACT);
			TaskInstance instance = instanceService.selectByPrimaryKey(instanceKey);
			if (instance == null)
				return "数据库中不存在该阶段的实例的状态！实例ID：" + instanceId + ",阶段：合并";
			//如果还未完成，不能启动, 完成就是2，未完成可以设0或1
			DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			/**
			if (instance.getStatus()!=TaskConstant.TASK_STOP && instance.getStatus()!=TaskConstant.TASK_WAIT){
				return "该实例的抽取任务已经启动，还未完成，上一次启动时间：" + df.format(instance.getPublishTime());
			}
			*/
			instance.setInstanceId(instanceId);
			instance.setStatus(TaskConstant.TASK_STOP);
			instance.setStage(TaskConstant.InstanceStage.EXTRACT);
			
			instance.setHandledAmount(new BigDecimal(0));
			instance.setHandledPercent(new BigDecimal(0));
			instance.setFinishTime(null);
			instance.setPublishTime(new Date());
			instance.setStatus(TaskConstant.TASK_START);//表示已经启动
			//TODO:instance.setStatus();
			int updateStatus = instanceService.update(instance);
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
				      .getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			session.setAttribute("selectTaskId", instance.getTaskId());
			session.setAttribute("selectInstanceId", instance.getInstanceId());
			if(extractType==null){
				extractType = "0";
			}
			if (updateStatus > 0) {
				//rest服务中  tt=true表示全量    tt=false 表示增量
				WebResource r = c.resource(rest_host + "extract/content?tt=true&instanceid=" + instanceId+"&tt="+(extractType.equals("1")?"true":"false"));
				String s = r.get(String.class);
				return s;
			} else {
				return "更新状态失败！";
			}
		} else {
			return "传入的实例id为空！";
		}
	}

	@RequestMapping(value = "/runextractByTask", method = RequestMethod.POST)
	@ResponseBody
	public String runContentExtractByTask(String inputPath, String taskId) throws Exception {
		Client c = Client.create();
		if (taskId != null && taskId.isEmpty() == false) {
			WebResource r = c.resource(rest_host + "extract/contentByTask?path=" + inputPath + "&taskid=" + taskId);
			String s = r.get(String.class);
			return s;
		} else {
			WebResource r = c.resource(rest_host + "extract/content?path=" + inputPath);
			String s = r.get(String.class);
			return s;
		}
	}

	@RequestMapping(value = "/progress", method = RequestMethod.POST)
	@ResponseBody
	@Produces("text/plain;charset=utf-8")
	public String progress(String instanceId) {
		String s = "";
		if (instanceId != null && instanceId.isEmpty() == false) {
			try {
				Client c = Client.create();
				WebResource r = c.resource(rest_host + "extract/progress/" + instanceId+".out");
				s = r.get(String.class);
				c.destroy();
			} catch (Exception e) {
				return e.getMessage();
			}
		}
		return s;
	}
}
