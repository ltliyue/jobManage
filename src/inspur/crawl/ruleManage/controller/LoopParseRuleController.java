package inspur.crawl.ruleManage.controller;

import java.awt.image.LookupOp;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.avro.generic.GenericData.Record;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.type.ClobTypeHandler;
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
import inspur.crawl.ruleManage.pojo.LoopParseRule;
import inspur.crawl.ruleManage.pojo.LoopVarRule;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapList;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapTemp;
import inspur.crawl.ruleManage.pojo.ParseRuleTemp;
import inspur.crawl.ruleManage.service.LoopParseRuleService;
import inspur.crawl.ruleManage.service.LoopVarRuleService;
import inspur.crawl.ruleManage.service.ParseRuleService;
import inspur.crawl.sysManage.pojo.Account;

@RestController
@RequestMapping("/loopParseRule")
public class LoopParseRuleController extends BaseController {
	
	public static final String abbr_str="为方便显示,此处省略大量字符";
	@Resource
	LoopParseRuleService loopParseRuleService;

	@Resource
	LoopVarRuleService loopVarRuleService;

	@InitBinder("loopParseRule")
	public void typeBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("loopParseRule.");
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
			i += loopParseRuleService.getDel(d);
		}
		return i;
	}

	/**
	 * 解析规则修改
	 */
	@RequestMapping(value = "/updateParseRule", method = RequestMethod.POST)
	@ResponseBody
	public void updateParseRule(@RequestParam String id, @RequestParam String loopSample,
			@RequestParam String parseRuleTempId,
			@RequestParam String loopVars,
			PrintWriter out) throws Exception {

		LoopParseRule rule = new LoopParseRule();
		//rule.setId(id);
		rule.setParseRuleId(parseRuleTempId);
		rule.setLoopSample(loopSample);
		rule.setLoopVars(loopVars);
		int i = loopParseRuleService.updateByFk(rule);
		out.print(i);
	}

	/**
	 * 解析规则修改
	 * @param id 该LoopParseRule的id
	 * @param parseRuleTempId 该LoopParseRule的外键ParseRuleTemp的Id
	 */
	@RequestMapping(value = "/updateParseRuleAndVarRule", method = RequestMethod.POST)
	@ResponseBody
	public void updateParseRule(HttpServletRequest request, @RequestParam String id,
			@RequestParam String parseRuleTempId,
			@RequestParam String loopSample,
			@RequestParam String loopVars, @RequestParam String varRuleId, @RequestParam String loopVarRule,
			@RequestParam String taskId, PrintWriter out) throws Exception {

		LoopParseRule rule = new LoopParseRule();
		//rule.setId(id);
		rule.setParseRuleId(parseRuleTempId);
		rule.setLoopSample(loopSample);
		rule.setLoopVars(loopVars);
		int i = loopParseRuleService.updateByFk(rule);
		out.print(i);

		String[] tokens = loopVarRule.split("\\|");
		if (tokens.length == 4) {
			// loop by integer
			LoopVarRule lvRule = new LoopVarRule();
			lvRule.setId(varRuleId);
			lvRule.setLoopStart(tokens[0]);
			lvRule.setLoopEnd(tokens[1]);
			lvRule.setLoopStep(new BigDecimal(tokens[2]));
			lvRule.setLoopFormat(tokens[3]);
			lvRule.setLoopVarType(new BigDecimal("0"));// 数字是0,字符串是1
			loopVarRuleService.getUp(lvRule);
		}

		if (tokens.length == 2) {
			// loop by string
			if (tokens[0].contains("/") || tokens[0].contains("\\")) {
				// loop by file
				LoopVarRule lvRule = new LoopVarRule();
				String fileName = tokens[0];
				if (tokens[0].lastIndexOf("\\") >= 0) {
					fileName = fileName.substring(tokens[0].lastIndexOf("\\") + 1);
				}
				String realPath = request.getSession().getServletContext().getRealPath("/upload/" + taskId);
				String content = FileUtils.readFileToString(new File(realPath + "/" + fileName), "utf-8");
				content = content.replaceAll("\r\n", ",");
				lvRule.setLoopStrList(content);
				lvRule.setId(varRuleId);
				lvRule.setLoopStrEncode(tokens[1]);
				lvRule.setLoopVarType(new BigDecimal("1"));// 数字是0,字符串是1
				loopVarRuleService.getUp(lvRule);
			} else {
				LoopVarRule lvRule = new LoopVarRule();
				if(tokens[0].isEmpty()==false){
					lvRule.setLoopStrList(tokens[0]);
				}
				lvRule.setId(varRuleId);
				lvRule.setLoopStrEncode(tokens[1]);
				lvRule.setLoopVarType(new BigDecimal("1"));// 数字是0,字符串是1
				loopVarRuleService.getUp(lvRule);
			}
		}

	}
	
	
	public static LoopVarRule create(HttpServletRequest request,String loopVarRule,String taskId) throws IOException{
		String[] tokens = loopVarRule.split("\\|");
		if (tokens.length == 4) {
			// loop by integer
			LoopVarRule lvRule = new LoopVarRule();
			lvRule.setLoopStart(tokens[0]);
			lvRule.setLoopEnd(tokens[1]);
			lvRule.setLoopStep(new BigDecimal(tokens[2]));
			lvRule.setLoopFormat(tokens[3]);
			lvRule.setLoopVarType(new BigDecimal("0"));// 数字是0,字符串是1
			return lvRule;
		}

		if (tokens.length == 2) {
			// loop by string
			if (tokens[0].startsWith("file:////")) {
				// loop by file
				LoopVarRule lvRule = new LoopVarRule();
				String fileName = tokens[0].substring("file:////".length());
				if (fileName.lastIndexOf("\\") >= 0) {
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				}
				String realPath = request.getSession().getServletContext().getRealPath("/upload/" + taskId);
				String content = FileUtils.readFileToString(new File(realPath + "/" + fileName), "utf-8");
				content = content.replaceAll("\r\n", ",");
				lvRule.setLoopStrList(content);
				lvRule.setLoopStrEncode(tokens[1]);
				lvRule.setLoopVarType(new BigDecimal("1"));// 数字是0,字符串是1
				return lvRule;
			} else {
				LoopVarRule lvRule = new LoopVarRule();
				lvRule.setLoopStrList(tokens[0]);
				lvRule.setLoopStrEncode(tokens[1]);
				lvRule.setLoopVarType(new BigDecimal("1"));// 数字是0,字符串是1
				return lvRule;
			}
		}
		return null;
	}
	
	
	/**
	 * 解析规则修改
	 */
	@RequestMapping(value = "/updateVarRule", method = RequestMethod.POST)
	@ResponseBody
	public void updateVarRule(HttpServletRequest request, @RequestParam String loopVarRule, @RequestParam String id,
			@RequestParam String taskId, PrintWriter out) throws Exception {
		LoopVarRule lvRule = create(request, loopVarRule, taskId);
		if(lvRule!=null){
			lvRule.setId(id);
			loopVarRuleService.getUp(lvRule);
		}
	}

	/**
	 * 解析规则增加
	 */
	@RequestMapping(value = "/addParseRule", method = RequestMethod.POST)
	@ResponseBody
	public void addParseRule(HttpServletRequest request, @RequestParam("loopVarRules[]") List<String> loopVarRules,
			@RequestParam String taskId, @RequestParam String parseRuleId, @RequestParam String loopSample,
			@RequestParam String loopVars) throws Exception {
		// 生成19位UUID
		String id = Numbers.uuid();
		LoopParseRule loopParseRule = new LoopParseRule();
		loopParseRule.setId(id);
		loopParseRule.setLoopSample(loopSample);
		loopParseRule.setLoopVars(loopVars);
		loopParseRule.setParseRuleId(parseRuleId);
		loopParseRuleService.add(loopParseRule);
		
		String[] looVarArray = loopVars.split(",");
		int i = 0;
		for (String loopVarRule : loopVarRules) {
			LoopVarRule lvRule = create(request, loopVarRule, taskId);
			if(lvRule!=null){
				lvRule.setId(Numbers.uuid());
				lvRule.setLoopParseRuleId(id);
				lvRule.setLoopVarName(looVarArray[i]);
				loopVarRuleService.add(lvRule);
			}
			i++;
		}
	}

	@RequestMapping(value = "/getByParseRuleId", method = RequestMethod.POST)
	@ResponseBody
	public List<LoopParseRule> getByParseRuleId(String id) throws Exception {
		List<LoopParseRule> list = loopParseRuleService.getByParseRuleId(id);
		return list;
	}
	

	@RequestMapping(value = "/getByLoopParseRuleId", method = RequestMethod.POST)
	@ResponseBody
	public List<LoopVarRule> getByLoopParseRuleId(String id) throws Exception {
		List<LoopVarRule> list = loopVarRuleService.getByLoopParseRuleId(id);
		// 截取字段长度，不超过500字符
		for (LoopVarRule loopVarRule : list) {
			if (loopVarRule.getLoopStrList() != null && loopVarRule.getLoopStrList().length() >= 500) {
				String loopStrList = loopVarRule.getLoopStrList().substring(0, 450) + "...此处省略"
						+ (loopVarRule.getLoopStrList().length() - 500) + "个字符..."
						+ loopVarRule.getLoopStrList().substring(loopVarRule.getLoopStrList().length() - 50);
				loopVarRule.setLoopStrList(loopStrList);
			}
		}
		return list;
	}
	
	@RequestMapping(value = "/getByLoopParseRuleIds", method = RequestMethod.POST)
	@ResponseBody
	public List<List<LoopVarRule>> getByLoopParseRuleIds(@RequestParam("ids[]") String[] ids) throws Exception {
		List<List<LoopVarRule>> listOfList = new ArrayList<List<LoopVarRule>>();
		for(String id:ids){
			List<LoopVarRule> list = loopVarRuleService.getAbbrByLoopParseRuleId(id);
			// 截取字段长度，不超过500字符
			for (LoopVarRule loopVarRule : list) {
				if (loopVarRule.getLoopStrList() != null && loopVarRule.getLoopStrList().length() >= 500) {
					String loopStrList = loopVarRule.getLoopStrList().substring(0, 450) + "..."+abbr_str+"..."
						+ loopVarRule.getLoopStrList().substring(loopVarRule.getLoopStrList().length() - 50);
					loopVarRule.setLoopStrList(loopStrList);
				}
			}
			listOfList.add(list);
		}
		return listOfList;
	}
}
