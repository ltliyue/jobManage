package inspur.crawl.ruleManage.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.tools.Encoding;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.sysManage.pojo.Account;
import inspur.crawl.sysManage.pojo.Role;
import inspur.crawl.ruleManage.pojo.ExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.service.ExtractRuleService;
import inspur.crawl.ruleManage.service.HtmlUtils;
import inspur.crawl.ruleManage.service.RemoveSlibingProcessor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.type.filter.RegexPatternTypeFilter;
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
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RestController
@RequestMapping("/extractRule")
public class ExtractRuleController extends BaseController {
	@Resource
	ExtractRuleService extractRuleService;

	@InitBinder("extractRule")
	public void typeBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("extractRule.");
	}

	/**
	 * 内容抽取规则管理
	 */
	@RequestMapping(value = "/setExtractRule", method = RequestMethod.GET)
	public ModelAndView userManage(ExtractRule record) throws Exception {
		List<ExtractRule> list = queryByPage(record);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		map.put("page", record);
		return new ModelAndView("sys/rule/extractRuleManage", map);
	}
	
	public List<ExtractRule> queryByPage(ExtractRule record) throws Exception {
		List<ExtractRule> page = extractRuleService.listPageExtractRule(record);

		return page;
	}
	
	public List<ExtractRule> queryByPage(ExtractRule record,String taskId) throws Exception {
		List<ExtractRule> page = extractRuleService.listPageExtractRule(record,taskId);
		return page;
	}

	/**
	 * 添加 跳转
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/extractRule", method = RequestMethod.GET)
	public ModelAndView extractRule(@RequestParam String t, @RequestParam String id) throws Exception {
		if ("1".equals(t)) {
			String siteId=extractRuleService.getSiteId(id);
			return request("sys/rule/extractRuleAddModal").addObject("taskId", id).addObject("siteId",siteId);
		} else if ("2".equals(t)) {
			String taskId = extractRuleService.getTaskId(id);
			String siteId=extractRuleService.getSiteId(taskId);
			return request("sys/rule/extractRuleUpdateModal").addObject("id", id).addObject("siteId",siteId);
		}
		return null;
	}
	
	/**
	 * 跳转到模态窗口
	 * @param t
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/extractRuleModal", method = RequestMethod.GET)
	public ModelAndView extractRuleModal(@RequestParam String t, @RequestParam String id, @RequestParam int modal) throws Exception {
		if ("1".equals(t)) {
			String siteId=extractRuleService.getSiteId(id);
			return request("sys/rule/extractRuleAddModal").addObject("taskId", id).addObject("siteId",siteId).addObject("modal", modal);
		} else if ("2".equals(t)) {
			String taskId = extractRuleService.getTaskId(id);
			String siteId=extractRuleService.getSiteId(taskId);
			return request("sys/rule/extractRuleUpdateModal").addObject("id", id).addObject("siteId",siteId).addObject("modal", modal);
		}else if("-1".equals(t)){
			String siteId=extractRuleService.getSiteId(id);
			//这里做测试页面用，不影响已经发布的版本
			return request("sys/rule/extractRuleAddModal-Copy").addObject("taskId", id).addObject("siteId",siteId).addObject("modal", modal);
		}
		return null;
	}

	

	/**
	 * 跳转到模态窗口
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/extractRuleManageModal", method = RequestMethod.GET)
	public ModelAndView extractRuleManageModal(ExtractRule record,@RequestParam String taskId) throws Exception {
		List<ExtractRule> list = queryByPage(record,taskId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		map.put("page", record);
		map.put("taskId", taskId);
		return new ModelAndView("sys/rule/extractRuleManageModal", map);
	}

	/**
	 * 查询所有角色
	 */
	@RequestMapping(value = "/getAllExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public List<ExtractRule> getAllExtractRule() throws Exception {
		List<ExtractRule> list = extractRuleService.queryAll();
		return list;
	}

	/**
	 * 查询可用角色
	 */
	@RequestMapping(value = "/enableExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public List<ExtractRule> userEQuery() throws Exception {
		List<ExtractRule> list = extractRuleService.queryEnable();
		return list;
	}

	/**
	 * 角色增加
	 */
	@RequestMapping(value = "/addExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public void addExtractRule(ExtractRule rule, PrintWriter out) throws Exception {
		// 生成19位UUID
		String id = Numbers.uuid();
		rule.setId(id);
		rule.setTime(new Date());

		int i = extractRuleService.getAdd(rule);

		out.print(i);
	}

	/**
	 * id 查询
	 */
	@RequestMapping(value = "/getExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public ExtractRule getExtractRule(String id) throws Exception {
		return extractRuleService.getRule(id);
	}

	
	/**
	 * 修改
	 */
	@RequestMapping(value = "/updateExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public void updateExtractRule(ExtractRule rule, PrintWriter out) throws Exception {

		int i = extractRuleService.getUp(rule);

		out.print(i);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/deleteExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public int deleteExtractRule(String id) throws Exception {
		String[] ids = id.split(";");
		int i = 0;
		for (String d : ids) {
			i += extractRuleService.getDel(d);
		}
		return i;
	}
	
	/**
	 * 规则删除,并跳转到模态窗口
	 */
	@RequestMapping(value = "/deleteExtractRuleModal", method = RequestMethod.GET)
	public ModelAndView deleteParseRuleModal(@RequestParam String id,@RequestParam String taskId) throws Exception {
		String[] ids = id.split(";");
		int i = 0;
		for (String d : ids) {
			i += extractRuleService.getDel(d);
		}
		return request("sys/rule/extractRuleManageModal").addObject("taskId", taskId).addObject("status",i);
	}
	
	//跳转到模态窗口siteItemChooseModal
	@RequestMapping(value = "/siteItemChooseModal", method = RequestMethod.GET)
	public ModelAndView siteItemChooseModal(@RequestParam String taskId,@RequestParam String eventOwner) throws Exception {
		String siteId=extractRuleService.getSiteId(taskId);
		return request("sys/rule/siteItemChoose").addObject("id", siteId).addObject("eventOwner",eventOwner);
	}
	
	@RequestMapping(value = "/siteItemChoose", method = RequestMethod.GET)
	public ModelAndView siteItemChoose(@RequestParam String taskId,@RequestParam String eventOwner) throws Exception {
		String siteId=extractRuleService.getSiteId(taskId);
		return request("sys/rule/siteItemChoose").addObject("id", siteId).addObject("eventOwner",eventOwner);
	}
	
	@RequestMapping(value = "/itemChoose", method = RequestMethod.GET)
	public ModelAndView itemChoose(@RequestParam String taskId,@RequestParam String eventOwner) throws Exception {
		String siteId=extractRuleService.getSiteId(taskId);
		return request("sys/rule/itemChoose").addObject("id", siteId).addObject("eventOwner",eventOwner);
	}
	
}
