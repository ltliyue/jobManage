package inspur.crawl.ruleManage.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.SgmlPage;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapTemp;
import inspur.crawl.ruleManage.service.HtmlUtils;
import inspur.crawl.ruleManage.service.ParseRuleMidMapService;
import inspur.crawl.ruleManage.service.ParseRuleMidMapTempService;
import inspur.crawl.ruleManage.service.RemoveSlibingProcessor;

@RestController
@RequestMapping("/parseRuleMidMapTemp")
public class ParseRuleMidMapTempController extends BaseController {
	@Resource
	ParseRuleMidMapTempService parseRuleMidMapTempService;

	@InitBinder("parseRuleMidMapTemp")
	public void typeBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("parseRuleMidMap.");
	}


	/**
	 * 添加 跳转
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/parseRuleMidMap", method = RequestMethod.GET)
	public ModelAndView parseRuleMidMap(@RequestParam String t, @RequestParam String id) throws Exception {
		if ("1".equals(t)) {
			return request("sys/rule/parseRuleMidMapAdd");
		} else if ("2".equals(t)) {
			return request("sys/rule/parseRuleMidMapUpdate").addObject("id", id);
		}
		return null;
	}

	/**
	 * 查询所有解析规则中间映射变量
	 */
	@RequestMapping(value = "/getAllParseRuleMidMap", method = RequestMethod.POST)
	@ResponseBody
	public List<ParseRuleMidMapTemp> getAllParseRuleMidMap() throws Exception {
		List<ParseRuleMidMapTemp> list = parseRuleMidMapTempService.queryAll();
		return list;
	}

	/**
	 * 解析规则中间映射变量增加
	 */
	@RequestMapping(value = "/addParseRuleMidMap", method = RequestMethod.POST)
	@ResponseBody
	public void addParseRuleMidMap(ParseRuleMidMapTemp rule, PrintWriter out) throws Exception {
		// 生成19位UUID
		String id = Numbers.uuid();
		rule.setId(id);
		rule.setTime(new Date());

		int i = parseRuleMidMapTempService.getAdd(rule);
		if (i == 1) {
			out.print(id);
		} else {
			out.print(i);
		}
	}

	/**
	 * id 查询解析规则中间映射变量l
	 */
	@RequestMapping(value = "/getParseRuleMidMap", method = RequestMethod.POST)
	@ResponseBody
	public ParseRuleMidMapTemp getParseRuleMidMap(String id) throws Exception {
		return parseRuleMidMapTempService.getRule(id);
	}
	
	
	/**
	 * id 查询解析规则中间映射变量l
	 */
	@RequestMapping(value = "/getMidMapByRule", method = RequestMethod.POST)
	@ResponseBody
	public List<ParseRuleMidMapTemp> getParseRuleMidMapByRule(String id) throws Exception {
		return parseRuleMidMapTempService.getMidMapByRuleId(id);
	}

	/**
	 * 解析规则中间映射变量修改
	 */
	@RequestMapping(value = "/updateParseRuleMidMap", method = RequestMethod.POST)
	@ResponseBody
	public void updateParseRuleMidMap(ParseRuleMidMapTemp rule, PrintWriter out) throws Exception {

		int i = parseRuleMidMapTempService.getUp(rule);

		out.print(i);
	}

	/**
	 * 解析规则中间映射变量删除
	 */
	@RequestMapping(value = "/deleteParseRuleMidMap", method = RequestMethod.POST)
	@ResponseBody
	public int deleteParseRuleMidMap(String id) throws Exception {
		String[] ids = id.split(";");
		int i = 0;
		for (String d : ids) {
			i += parseRuleMidMapTempService.getDel(d);
		}
		return i;
	}
}
