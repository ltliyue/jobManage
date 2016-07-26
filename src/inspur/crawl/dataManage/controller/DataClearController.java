package inspur.crawl.dataManage.controller;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.siteManage.pojo.RealSiteCode;
import inspur.crawl.siteManage.service.RealSiteService;
import inspur.crawl.taskManage.pojo.CrawlerTask;
import inspur.crawl.taskManage.service.TaskService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dataclear")
public class DataClearController extends BaseController{
	@Resource
	TaskService taskService;
	@Resource
	RealSiteService siteService;
	@InitBinder("crawlerTask")  
	public void taskBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("crawlerTask.");  
	}
	@InitBinder("dataclear")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("dataclear.");  
	}
	
	@InitBinder("page")  
	public void pageBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("page.");  
	}
	/**
	 * 任务结果
	 */
	@RequestMapping(value = "/dataclean", method = RequestMethod.GET)
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
		return request("sys/dataManage/dataResults",map);
	}
	/**
	 * 任务结果
	 */
	@RequestMapping(value = "/dataimp", method = RequestMethod.GET)
	public ModelAndView taskresultimp(CrawlerTask crawlerTask, Page page) throws Exception {
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
		return request("sys/dataManage/dataResultsImp",map);
	}
	/**
	 * 任务结果
	 */
	@RequestMapping(value = "/maindata", method = RequestMethod.GET)
	public ModelAndView maindata(CrawlerTask crawlerTask, Page page) throws Exception {
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
		return request("sys/dataManage/mainResults",map);
	}
	/**
	 * hdfs清洗
	 */
	@RequestMapping(value = "/hdfsclean", method = RequestMethod.GET)
	public ModelAndView hdfsclean(CrawlerTask crawlerTask, Page page) throws Exception {
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
		return request("sys/dataManage/hdfsClean",map);
	}
}
