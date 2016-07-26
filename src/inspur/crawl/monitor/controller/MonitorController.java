package inspur.crawl.monitor.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inspur.bigdata.hbaseio.pojo.MetricTarget;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.DateFormater;
import inspur.crawl.monitor.service.MonitorService;
import inspur.crawl.siteManage.pojo.RealSiteCode;
import inspur.crawl.taskManage.pojo.CrawlerTask;
import inspur.crawl.taskManage.pojo.TaskInstance;
import inspur.crawl.taskManage.service.TaskService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

	@Resource
	MonitorService monitorService;
	@Resource
	TaskService taskService;
	
	@InitBinder("crawlerTask")  
	public void taskBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("crawlerTask.");  
	}
	
	@InitBinder("page")  
	public void pageBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("page.");  
	}
	
	/**
	 * 默认首页
	 * @return
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request) {
		Date endTime = new Date();
		Date beginTime = new Date(System.currentTimeMillis() - 24*60*60*1000);
		JSONObject jsonObject = JSONObject.fromObject(monitorService.generateTypeData(MetricTarget.WHOLE,
				beginTime, endTime, null,
				TimeUnit.MINUTES));
		Map<String,Object> maph = monitorService.returnHandle(MetricTarget.WHOLE,
				beginTime, endTime, null);
		HttpSession session = request.getSession();
		session.setAttribute(Sessions.SESSION_CAI, null);
		
		return request("sys/monitor/monitor")
				.addObject("mapData",jsonObject)
				.addObject("type",MetricTarget.WHOLE)
				.addObject("beginTime",DateFormater.dateFormat(beginTime, DateFormater.yyyyMMddHHmmss))
				.addObject("endTime",DateFormater.dateFormat(endTime, DateFormater.yyyyMMddHHmmss))
				.addObject("targetId",null)
				.addObject("timeUnit",TimeUnit.MINUTES)
				.addObject("handleM",maph);
	}
	
	/**
	 * 加载所有默认数据
	 * @return
	 */
	@RequestMapping(value = "/initData", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> initData() {
		//默认加载24小时内的总量数据
		Date endTime = new Date();
		Date beginTime = new Date(System.currentTimeMillis() - 24*60*60*1000);
		return monitorService.generateTypeData(MetricTarget.WHOLE,
				beginTime, endTime, null,
				TimeUnit.MINUTES);
	}
	
	/**
	 * 加载指定数据
	 * @return
	 */
	@RequestMapping(value = "/monitorData", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> monitorData(String type, Date beginTime, Date endTime, String timeUnit, String targetId) {
		return monitorService.generateTypeData(MetricTarget.valueOf(type.toUpperCase()),
				beginTime, endTime, targetId,
				TimeUnit.valueOf(timeUnit.toUpperCase()));
	}
	/**
	 * 加载指定清洗数据
	 */
	/**
	 * 加载最新新增数据
	 * @return
	 */
	@RequestMapping(value = "/monitorDataIncr", method = RequestMethod.GET)
	@ResponseBody
	public String monitorDataIncr() {
		return null;
	}
	
	/**
	 * 实例选择页面
	 * @return
	 */
	@RequestMapping(value = "/instanceList", method = RequestMethod.GET)
	public ModelAndView instanceList(CrawlerTask crawlerTask, Page page) {
		List<TaskInstance> list = monitorService.findTaskInstances(crawlerTask, page);
		Map countStatus = monitorService.countInstanceByStatus(crawlerTask);
		Map countPeriod = monitorService.countInstanceByPeriod(crawlerTask);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("instances", list);
		map.put("page", page);
		map.put("countStatus", countStatus);
		map.put("countPeriod", countPeriod);
		return request("sys/monitor/instanceList", map);
	}
	
	/**
	 * 加载数据资源数据
	 */
	@RequestMapping(value = "/generalNum", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> generalNum() {
		Map<String, Object> generalResult = monitorService.getGeneralCount();
		return generalResult;
	}
}
