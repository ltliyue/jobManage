package inspur.crawl.ruleManage.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.SgmlPage;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.ruleManage.pojo.ElementExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapList;
import inspur.crawl.ruleManage.service.ElementExtractRuleService;
import inspur.crawl.ruleManage.service.ParseRuleService;

@RestController
@RequestMapping("/elementExtractRule")
public class ElementExtractRuleController extends BaseController {
	@Resource
	ElementExtractRuleService service;

	@InitBinder("elementExtractRule")
	public void typeBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("elementExtractRule.");
	}

	/**
	 * 规则增加
	 */
	@RequestMapping(value = "/addElementExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public void addElementExtractRule(ElementExtractRule rule, PrintWriter out) throws Exception {
		// 生成19位UUID
		String id = Numbers.uuid();
		rule.setId(id);
		int i = service.getAdd(rule);
		if (i == 1) {
			out.print(id);
		} else {
			out.print(i);
		}
	}

	/**
	 * id 查询解析规则l
	 */
	@RequestMapping(value = "/getByPageExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public List<ElementExtractRule> getByPageExtractRule(String id) throws Exception {
		return service.getByPageExtractRule(id);
	}
	

	/**
	 * 解析规则修改
	 */
	@RequestMapping(value = "/updateElementExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public void updateExtractRule(ElementExtractRule rule, PrintWriter out) throws Exception {
		int i = service.getUp(rule);
		out.print(i);
	}

	
	/**
	 * 解析规则删除
	 */
	@RequestMapping(value = "/deleteElementExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public int deleteElementExtractRule(String id) throws Exception {
		String[] ids = id.split(";");
		int i = 0;
		for (String d : ids) {
			i += service.getDel(d);
		}
		return i;
	}
}
