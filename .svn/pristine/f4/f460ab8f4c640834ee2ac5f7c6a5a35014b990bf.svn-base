package inspur.crawl.ruleManage.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.avro.generic.GenericData.Record;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.SgmlPage;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.inspur.avro.model.java.UrlType;
import com.inspur.kafka.crawl.model.CrawlRequest;
import com.inspur.kafka.crawl.model.CrawlResult;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.Encoding;
import inspur.crawl.common.tools.HttpClientSingle;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapList;
import inspur.crawl.ruleManage.service.ParseRuleService;
import inspur.crawl.sysManage.pojo.Account;

@RestController
@RequestMapping("/parseRule")
public class ParseRuleController extends BaseController {
	@Resource
	ParseRuleService parseRuleService;

	@InitBinder("parseRule")
	public void typeBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("parseRule.");
	}

	/**
	 * 内容抽取规则管理
	 */
	@RequestMapping(value = "/setParseRule", method = RequestMethod.GET)
	public ModelAndView userManage(ParseRule record) throws Exception {
		List<ParseRule> list = queryByPage(record);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		map.put("page", record.getPage());
		if(record.getTaskId()==null){
			return new ModelAndView("sys/rule/parseRuleManage", map);
		}else{
			return new ModelAndView("sys/rule/parseRuleManageModal", map);
		}
	}

	@RequestMapping(value = "/searchParseRule", method = RequestMethod.GET)
	public ModelAndView extractRule(ParseRule record) throws Exception {
		if(record.getPage()==null){
			record.setPage(new Page());
		}
		//record.setName(Encoding.encoding(record.getName()));//本机上需要增加，服务器上不需要增加?
		List<PageExtractRule> list =  parseRuleService.listPageBySearch(record,record.getPage());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		if(record.getName()!=null && record.getName().isEmpty()==false){
			map.put("inputName",record.getName());
		}
		if(record.getTaskId()!=null && record.getTaskId().isEmpty()==false){
			map.put("inputName",record.getTaskId());
		}
		map.put("page", record.getPage());
		map.put("ruleName",record.getName());
		map.put("taskId",record.getTaskId());
		map.put("extractType", record.getExtractType());
		return new ModelAndView("sys/rule/parseRuleManage", map);
	}

	public List<ParseRule> queryByPage(ParseRule record) throws Exception {
		List<ParseRule> page = parseRuleService.listPageParseRule(record);
		return page;
	}

	public List<ParseRule> queryByPage(ParseRule record, String taskId) throws Exception {
		List<ParseRule> page = parseRuleService.listPageParseRule(record, taskId);
		return page;
	}

	/**
	 * 跳转到模态窗口
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/parseRuleManageModal", method = RequestMethod.GET)
	public ModelAndView parseRuleManageModal(ParseRule record, @RequestParam String taskId) throws Exception {
		List<ParseRule> list = queryByPage(record, taskId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		map.put("page", record);
		map.put("taskId", taskId);
		return new ModelAndView("sys/rule/parseRuleManageModal", map);
	}

	/**
	 * 添加 跳转
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/parseRule", method = RequestMethod.GET)
	public ModelAndView parseRule(@RequestParam String t, @RequestParam String id) throws Exception {
		if ("1".equals(t)) {
			return request("sys/rule/parseRuleAdd");
		} else if ("2".equals(t)) {
			return request("sys/rule/parseRuleUpdate").addObject("id", id);
		}
		return null;
	}

	/**
	 * 跳转到模态窗口
	 * 
	 * @param t
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/parseRuleModal", method = RequestMethod.GET)
	public ModelAndView parseRuleModal(@RequestParam String t, @RequestParam String id) throws Exception {
		if ("1".equals(t)) {
			return request("sys/rule/parseRuleAddModal").addObject("taskId", id);
		} else if ("2".equals(t)) {
			return request("sys/rule/parseRuleUpdateModal").addObject("id", id);
		}
		return null;
	}

	/**
	 * 查询所有解析规则
	 */
	@RequestMapping(value = "/getAllParseRule", method = RequestMethod.POST)
	@ResponseBody
	public List<ParseRule> getAllParseRule() throws Exception {
		List<ParseRule> list = parseRuleService.queryAll();
		return list;
	}

	/**
	 * 查询可用解析规则
	 */
	@RequestMapping(value = "/enableParseRule", method = RequestMethod.POST)
	@ResponseBody
	public List<ParseRule> userEQuery() throws Exception {
		List<ParseRule> list = parseRuleService.queryEnable();
		return list;
	}

	/**
	 * 解析规则增加
	 */
	@RequestMapping(value = "/addParseRule", method = RequestMethod.POST)
	@ResponseBody
	public void addParseRule(ParseRule rule, PrintWriter out) throws Exception {
		// 生成19位UUID
		String id = Numbers.uuid();
		rule.setId(id);
		rule.setTime(new Date());

		int i = parseRuleService.getAdd(rule);
		if (i == 1) {
			out.print(id);
		} else {
			out.print(i);
		}
	}

	/**
	 * id 查询解析规则l
	 */
	@RequestMapping(value = "/getParseRule", method = RequestMethod.POST)
	@ResponseBody
	public ParseRule getParseRule(String id) throws Exception {
		return parseRuleService.getRule(id);
	}

	/**
	 * 解析规则修改
	 */
	@RequestMapping(value = "/updateParseRule", method = RequestMethod.POST)
	@ResponseBody
	public void updateParseRule(ParseRule rule, PrintWriter out) throws Exception {

		int i = parseRuleService.getUp(rule);

		out.print(i);
	}

	/**
	 * 测试规则配置
	 * 
	 * @param url
	 * @param extractType
	 * @param regex
	 * @param regexGroupId
	 * @param xpath
	 * @param containHtml
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/testParseRule", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> testParseRule(ParseRule rule, ParseRuleMidMapList midMaps, String testUrl)
			throws Exception {

		String html = null;
		StringBuffer buffer = new StringBuffer("");
		if (testUrl != null) {
			CrawlRequest request = new CrawlRequest();
			request.setUrl(testUrl);
			request.setTimeout(10 * 1000);
			request.setRetryHandler(3);

			try {
				CrawlResult cr = HttpClientSingle.t(request,3);
				html = cr.getContent();
				Map<String, Object> rsMap = new HashMap<String, Object>();

				if (cr.getStatusCode().equals("200") == false || html == null) {
					buffer.append("网页下载失败!\r\n");
				} else {
					buffer.append("网页下载成功!\r\n");
				}
			} catch (Exception e) {
				buffer.append("网页下载失败!" + e.getMessage());
			}
		}
		List<ParseRule> rules = new ArrayList<ParseRule>();
		rules.add(rule);

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, UrlType> urlTypeMap = null;//UrlParser.parseAndLog(rules, midMaps.getMidMaps(), html, testUrl, buffer);
		map.put("srcUrl", testUrl);
		map.put("extractUrlSize", urlTypeMap.size());
		map.put("extractUrls", urlTypeMap.toString());
		map.put("log", buffer.toString());
		map.put("testUrlHtml", html);
		System.out.println("测试url：" + testUrl + ",发现URL的条数：" + urlTypeMap.size());
		return map;
	}

	/**
	 * 解析规则删除
	 */
	@RequestMapping(value = "/deleteParseRule", method = RequestMethod.POST)
	@ResponseBody
	public int deleteParseRule(String id) throws Exception {
		String[] ids = id.split(";");
		int i = 0;
		for (String d : ids) {
			i += parseRuleService.getDel(d);
		}
		return i;
	}

	/**
	 * 解析规则删除,并跳转到模态窗口
	 */
	@RequestMapping(value = "/deleteParseRuleModal", method = RequestMethod.GET)
	public ModelAndView deleteParseRuleModal(@RequestParam String id, @RequestParam String taskId) throws Exception {
		String[] ids = id.split(";");
		int i = 0;
		for (String d : ids) {
			i += parseRuleService.getDel(d);
		}
		ParseRule record = new ParseRule();
		List<ParseRule> list = queryByPage(new ParseRule(), taskId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		map.put("page", record);
		map.put("taskId", taskId);
		return new ModelAndView("sys/rule/parseRuleManageModal", map);
	}
	
	
	/////////////////////////////代码重构///////////////////////////////////////////
	
	
	
}
