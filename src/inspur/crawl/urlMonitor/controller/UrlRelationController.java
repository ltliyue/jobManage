package inspur.crawl.urlMonitor.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.hadoop.hive.ql.plan.api.Task;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import inspur.crawl.ruleManage.pojo.ParseRuleTemp;
import inspur.crawl.ruleManage.service.ParseRuleTempService;
import inspur.crawl.taskManage.service.TaskService;
import inspur.crawl.urlMonitor.pojo.UrlRelationHistory;
import inspur.crawl.urlMonitor.service.CrawlInstationService;

@RestController
@RequestMapping("/urlrelation")
public class UrlRelationController {
	public static final int pagesize = 10;
	@Resource
	CrawlInstationService service;
	@Resource
	ParseRuleTempService  ruleService;
	@Resource
	TaskService taskService;
	
	@InitBinder("urlrelation")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("urlrelation.");  
	}
	
	/**
	 * 测试url=http://localhost:8080/crawlManage/urlrelation/showSubUrls?instanceId=608a5c9d-0bc7-4522-adf2-06172d250b1e&parentUrl=http%3A%2F%2Fwww.dianping.com%2Flasa
	 */
	@RequestMapping(value = "/showSubUrls", method = RequestMethod.GET)
	public ModelAndView showSubUrls(String instanceId,String parseRuleId,String parentUrl) throws Exception {
		//返回总条数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentUrl", parentUrl);
		//parentUrl = URLDecoder.decode(parentUrl);
		int count = service.countSubUrls(instanceId, parseRuleId, parentUrl);
		map.put("totalcount", count);
		map.put("totalpage", (count%pagesize==0?(count/pagesize):(count/pagesize+1)));
		map.put("curpage", "1"); //表示显示的是子urls
		map.put("instanceId", instanceId);
		map.put("parseRuleId", parseRuleId);
		String taskId = taskService.getTaskIdByInstaceId(instanceId);
		if(count>0){
			map.put("histories", service.queryByParentUrlAndPage(taskId,instanceId, parseRuleId, parentUrl, pagesize, 1));//查询第一页
		}
		return new ModelAndView("sys/diagnoseCrawl/subURL", map);
	}
	
	/**
	 * 由外围传入总个数，减少impala的查询次数
	 * @param instanceId
	 * @param parseRuleId
	 * @param parentUrl
	 * @param totalCount
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fastShowSubUrls", method = RequestMethod.GET)
	public ModelAndView showSubUrls(String taskId,String instanceId,String parseRuleId,String parentUrl,int totalCount) throws Exception {
		//返回总条数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentUrl", parentUrl);
		//parentUrl = URLDecoder.decode(parentUrl);
		int count = totalCount;//service.countSubUrls(instanceId, parseRuleId, parentUrl);
		map.put("totalcount", count);
		map.put("totalpage", (count%pagesize==0?(count/pagesize):(count/pagesize+1)));
		//map.put("businessType", "0"); //表示显示的是子urls
		map.put("curpage", "1"); //表示显示的是子urls
		map.put("instanceId", instanceId);
		map.put("parseRuleId", parseRuleId);
		if(count>0){
			map.put("histories", service.queryByParentUrlAndPage(taskId,instanceId, parseRuleId, parentUrl, pagesize, 1));//查询第一页
		}
		return new ModelAndView("sys/diagnoseCrawl/subURL", map);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/showDifferUrls", method = RequestMethod.GET)
	public ModelAndView showDifferUrls(String instanceId,String parseRuleId1,String parseRuleId2) throws Exception {
		//返回总条数
		Map<String, Object> map = new HashMap<String, Object>();
		
		//获取具体的ParseRule
		ParseRuleTemp parseRule = ruleService.getRule(parseRuleId2);
		int count = service.countDiffer(instanceId, parseRuleId1, parseRule);
		map.put("totalcount", count);
		map.put("totalpage", (count%pagesize==0?(count/pagesize):(count/pagesize+1)));
		map.put("businessType", "1"); //表示显示的是differ urls
		map.put("curpage", "1"); //表示显示的是子urls
		map.put("instanceId", instanceId);
		map.put("parseRuleId1", parseRuleId1);
		map.put("parseRuleId2", parseRuleId2);
		String parseRuleName1 = ruleService.getRule(parseRuleId1).getName();
		String parseRuleName2 = ruleService.getRule(parseRuleId2).getName();
		map.put("parseRuleName1", parseRuleName1);
		map.put("parseRuleName2", parseRuleName2);
		if(count>0){
			map.put("histories", service.differByPage(instanceId, parseRuleId1, parseRule, pagesize, 1));//查询第一页
		}
		return new ModelAndView("sys/diagnoseCrawl/diffURL", map);
	}
	
	
	/**
	 * 搜索不同的区别的url
	 */
	@RequestMapping(value = "/searchDifferUrls", method = RequestMethod.GET)
	public ModelAndView searchDifferUrls(String instanceId,String parseRuleId1,String parseRuleId2,String url) throws Exception {
		//返回总条数
		Map<String, Object> map = new HashMap<String, Object>();
		
		int count = service.countSearchDiffer(instanceId, parseRuleId1, ruleService.getRule(parseRuleId2),url);
		map.put("totalcount", count);
		map.put("totalpage", (count%pagesize==0?(count/pagesize):(count/pagesize+1)));
		map.put("businessType", "1"); //表示显示的是differ urls
		map.put("curpage", "1"); //表示显示的是子urls
		map.put("instanceId", instanceId);
		map.put("parseRuleId1", parseRuleId1);
		map.put("parseRuleId2", parseRuleId2);
		String parseRuleName1 = ruleService.getRule(parseRuleId1).getName();
		String parseRuleName2 = ruleService.getRule(parseRuleId2).getName();
		map.put("parseRuleName1", parseRuleName1);
		map.put("parseRuleName2", parseRuleName2);
		map.put("queryurl", url);
		if(count>0){
			map.put("histories", service.queryByCurrentUrlAndPage(instanceId, parseRuleId1, url, pagesize, 1));//查询第一页
		}
		return new ModelAndView("sys/diagnoseCrawl/diffURL", map);
	}
	
	
	@RequestMapping(value = "/getSubUrlsListByPageNo", method = RequestMethod.POST)
	@ResponseBody
	public List<UrlRelationHistory> getSubUrlsByPageNo(String instanceId,String parseRuleId, int pageNo, String parentUrl){
		String taskId = taskService.getTaskIdByInstaceId(instanceId);
		return service.queryByParentUrlAndPage(taskId,instanceId, parseRuleId, parentUrl, pagesize, 1);
	}
	
	
	/**
	 * 规则串接时，进行URL的比较，如果规则A的输出的URL是规则B的输入的URL，比较不同的内容
	 */
	@RequestMapping(value = "/getDifferUrlsByPageNo", method = RequestMethod.POST)
	@ResponseBody
	public List<UrlRelationHistory> getDifferUrlsByPageNo(String instanceId,String parseRuleId1, String parseRuleId2,int pageNo){
		return service.differByPage(instanceId, parseRuleId1, ruleService.getRule(parseRuleId2), pagesize, pageNo);
	}
	
	/**
	 * 规则串接时，进行URL的比较，如果规则A的输出的URL是规则B的输入的URL，比较不同的内容，对输出的结果进行搜索
	 */
	@RequestMapping(value = "/serchDifferUrlsByPageNo", method = RequestMethod.POST)
	@ResponseBody
	public List<UrlRelationHistory> searchDifferUrlsByPageNo(String instanceId,String parseRuleId, String url,int pageNo){
		return service.queryByCurrentUrlAndPage(instanceId, parseRuleId, url, pagesize, pageNo);
	}
}
