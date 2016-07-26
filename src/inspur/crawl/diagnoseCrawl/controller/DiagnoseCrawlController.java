package inspur.crawl.diagnoseCrawl.controller;
import  inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.tools.Encoding;
import inspur.crawl.diagnoseCrawl.pojo.ParsePUrl;
import inspur.crawl.diagnoseCrawl.pojo.ParseRuleDia;
import inspur.crawl.diagnoseCrawl.pojo.TaskAndTaskInstance;
import inspur.crawl.diagnoseCrawl.service.DiagnoseCrawlService;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleTemp;
import inspur.crawl.ruleManage.service.ParseRuleService;
import inspur.crawl.ruleManage.service.ParseRuleTempService;
import inspur.crawl.urlMonitor.service.CrawlInstationService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/diagnoseCrawl")
public class DiagnoseCrawlController extends BaseController {
	@Resource
	ParseRuleTempService parseRuleTempService;
	@Resource
	DiagnoseCrawlService service;
	@InitBinder("diagnoseCrawl")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("diagnoseCrawl.");  
	}
	/**
	 * 
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView ruleList(ParseRuleDia record) throws Exception {
		List<ParseRuleDia> list = service.listPage(record);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		map.put("page", record.getPage());
		return request("sys/diagnoseCrawl/ruleManage",map);
	}
	@RequestMapping(value = "/searchParseRule", method = RequestMethod.GET)
	public ModelAndView extractRule(ParseRuleDia record ) throws Exception {
		if (record.getPage() == null) {
			record.setPage(new Page());
		}
//		 record.setName(Encoding.encoding(record.getName()));//本机上需要增加，服务器上不需要增加?
		List<ParseRuleDia> list = service.listPageBySearch(record, record.getPage());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		if (record.getName() != null && record.getName().isEmpty() == false) {
			map.put("inputName", record.getName());
		}
		if (record.getTaskId() != null) {
			map.put("inputName", record.getTaskId());
		}
		map.put("page", record.getPage());
		map.put("ruleName", record.getName());
		map.put("taskId", record.getTaskId());
		map.put("extractType", record.getExtractType());
		return new ModelAndView("sys/diagnoseCrawl/ruleManage", map);
	}

	public List<ParseRuleTemp> queryByPage(ParseRuleTemp record) throws Exception {
		List<ParseRuleTemp> page = parseRuleTempService.listPageParseRule(record);
		return page;
	}

	public List<ParseRuleTemp> queryByPage(ParseRuleTemp record, String taskId) throws Exception {
		List<ParseRuleTemp> page = parseRuleTempService.listPageParseRule(record, taskId);
		return page;
	}
	@RequestMapping(value = "/getInstanceList", method = RequestMethod.POST)
	public void getInstanceList(String ruleId,String taskId,HttpServletResponse response) throws Exception{
		List<TaskAndTaskInstance> list = service.getInstanceID(taskId);
		StringBuffer buff= new StringBuffer();
		List<String> li = new ArrayList<>();
		for(TaskAndTaskInstance tt:list){
			if(!li.contains(tt.getInstance_id()+"---"+tt.getPublish_time()))
				li.add(tt.getInstance_id()+"---"+tt.getPublish_time());
		}
		for(String str:li){
			buff.append(str+"|");
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(buff.toString());
	}
	@RequestMapping(value = "/initPurl", method = RequestMethod.GET)
	public ModelAndView initPurl(ParsePUrl record) throws Exception {
		System.out.println(record);
		String pager=null;
		List<ParsePUrl> list = null;
		List<ParsePUrl> list1=null;
//		if(record.getP_url() != null)
//			list=service.getPUrlListBypurl(record);
//		else
		list=service.getPUrlList1(record);
		List<TaskAndTaskInstance> listRule = service.getRule(record.getTask_id());
		int num=service.getPUrlPage1(record);
		TaskAndTaskInstance detail = service.getDetail(record.getRule_id());
		System.out.println(detail);
		try{
			record.getPager().length();
			pager=record.getPager();
		}catch(NullPointerException e){
			pager="1";
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		//规则id的所有的p_url以所有的数量(p_url,num)
		map.put("rules", list);
		//task_id下的所有规则id及其对应的名称(id,name)
		map.put("ruleDetails", listRule);
		//规则id对应的任务名称、任务id、规则名称、规则id(task_name,taskid,name,id)
		map.put("detail", detail);
		//页数
		map.put("pager", pager);
		System.out.println("pager:"+pager);
		//规则对应的任务实例id
		map.put("task_instance_id", record.getTask_instance_id());
		if(pager.equals("1")&&!pager.equals(""+num)){
			map.put("lable", "disabled");
			map.put("nable", "abled");
		} 
		if(!pager.equals("1")&&!pager.equals(""+num)){
			map.put("lable", "abled");
			map.put("nable", "abled");
		}
		if(!pager.equals("1")&&pager.equals(""+num)){
			map.put("lable", "abled");
			map.put("nable", "disabled");
		}
		if(pager.equals("1")&&pager.equals(""+num)){
			map.put("lable", "disabled");
			map.put("nable", "disabled");
		}
		map.put("allpage", num+"");
		System.out.println(map);
		return request("sys/diagnoseCrawl/pUrlManager",map);		
	}
//	public List<ParsePUrl> queryPUrlByPage(ParsePUrl record) throws Exception {
//		List<ParsePUrl> page = service.getPUrlList(record);
//		return page;
//	}
}
