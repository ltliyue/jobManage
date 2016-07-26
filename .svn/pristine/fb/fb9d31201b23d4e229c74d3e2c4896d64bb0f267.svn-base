package inspur.crawl.ruleManage.controller;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.inspur.kafka.crawl.model.CrawlRequest;
import com.inspur.kafka.crawl.model.CrawlResult;

import inspur.crawl.codeManage.pojo.StandardCode;
import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.tools.HttpClientSingle;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.ruleManage.controller.ContentExtractor.MyClassLoader;
import inspur.crawl.ruleManage.pojo.ElementExtractRule;
import inspur.crawl.ruleManage.pojo.ElementExtractRuleList;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.service.ElementExtractRuleService;
import inspur.crawl.ruleManage.service.ExtractRuleService;
import inspur.crawl.ruleManage.service.PageExtractRuleService;
import inspur.crawl.siteManage.service.RealSiteService;
import inspur.crawl.taskManage.pojo.CrawlerTaskConfig;
import inspur.crawl.taskManage.service.TaskService;

@RestController
@RequestMapping("/pageExtractRule")
public class PageExtractRuleController extends BaseController {
	@Resource
	PageExtractRuleService service;

	@Resource
	ElementExtractRuleService elementRuleservice;

	@Resource
	TaskService taskService;

	@Resource
	RealSiteService realSiteService;

	@Resource
	ExtractRuleService extractRuleService;

	@InitBinder("pageExtractRule")
	public void typeBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("pageExtractRule.");
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
	public List<PageExtractRule> getAllParseRule() throws Exception {
		List<PageExtractRule> list = service.queryAll();
		return list;
	}

	/**
	 * 解析规则增加
	 */
	@RequestMapping(value = "/addPageExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public void addPageExtractRule(PageExtractRule rule, String storeColumnMap, PrintWriter out) throws Exception {
		// 生成19位UUID
		String id = Numbers.uuid();
		rule.setId(id);
		rule.setTime(new Date());

		int i = service.getAdd(rule);
		if (i == 1) {
			rule.setId(id);
			Map<String, String> storeColumnMapm = new HashMap<>();
			if (storeColumnMap != null) {
				String[] tokens = storeColumnMap.split(",");
				for (String token : tokens) {
					if (token.indexOf(":") >= 0) {
						storeColumnMapm.put(token.substring(0, token.indexOf(":")),
								token.substring(token.indexOf(":") + 1));
					}
				}
			}
			if (createSelfDefinedRules(rule, storeColumnMapm, out) == false) {
				deleteExtractRule(id);
			} else {
				out.print(id);
			}
		} else {
			out.print(i);
		}
	}

	/**
	 * id 查询解析规则l
	 */
	@RequestMapping(value = "/getExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public PageExtractRule getExtractRule(String id) throws Exception {
		return service.getRule(id);
	}

	public boolean createSelfDefinedRules(PageExtractRule rule, Map<String, String> storeColumnMap, PrintWriter out) {
		// 如果是上传jar包，需要先删除已经存在的规则，然后再增加新的规则
		if (rule.getExtractType() == 2) {
			elementRuleservice.deleteByTypeAndId(rule.getId(), "3");
			// 读取jar包，然后再增加规则
			String jarPath = rule.getJarPath();
			String className = rule.getClassName();
			// 下载jar到本地
			if (jarPath == null || className == null) {
				return true;
			}
			try {
				FileSystem fs = FileSystem.get(new Configuration());
				jarPath = jarPath.substring("hdfs://".length());
				String jarName = jarPath.substring(jarPath.lastIndexOf("/") + 1);

				String localJarDir = ContentExtractor.ruleJarLocalDirLinux;
				if (System.getProperties().get("os.name").toString().toLowerCase().contains("windows")) {
					localJarDir = ContentExtractor.ruleJarLocalDirWin;
				}
				if (new File(localJarDir).exists() == false) {
					new File(localJarDir).mkdirs();
				} else if (new File(localJarDir).isDirectory() == false) {
					new File(localJarDir).deleteOnExit();
					new File(localJarDir).mkdirs();
				}
				if (new File(localJarDir + jarName).exists() == false) {
					fs.copyToLocalFile(new Path(jarPath), new Path(localJarDir));
				}
				URL[] urlsx = new URL[] {};

				MyClassLoader classLoader = new MyClassLoader(urlsx, null);
				classLoader.addJar(new File(localJarDir + jarName).toURI().toURL());
				className = className.replaceAll("\\.", "/");
				InputStream inputStream = classLoader.getResourceAsStream(className + ".java");
				if (inputStream == null) {
					out.println("jar包中不包含源文件！jar包中应该包含：" + className + ".java");
					return false;
				}
				String content = org.apache.commons.io.IOUtils.toString(inputStream);
				if (content == null) {
					out.println("" + className + ".java内容为空！");
					return false;
				}

				// 删除已经创建的
				// 根据siteId读取相应的规则
				for (String columnCode : storeColumnMap.keySet()) {
					if (content.indexOf("\"" + columnCode + "\"") >= 0) {
						ElementExtractRule er = new ElementExtractRule();
						String erid = Numbers.uuid();
						er.setId(erid);
						er.setPageExtractRuleId(rule.getId());
						er.setExtractType("3");// 自定义
						er.setStoreColumnName(columnCode);
						er.setStoreColumnComment(storeColumnMap.get(columnCode));
						elementRuleservice.getAdd(er);
					}
				}
				inputStream.close();
				classLoader.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * 解析规则修改
	 */
	@RequestMapping(value = "/updateExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public void updateExtractRule(PageExtractRule rule, String storeColumnMap, PrintWriter out) throws Exception {
		int i = service.getUp(rule);
		if (i == 1) {
			Map<String, String> storeColumnMapm = new HashMap<>();
			if (storeColumnMap != null) {
				String[] tokens = storeColumnMap.split(",");
				for (String token : tokens) {
					if (token.indexOf(":") >= 0) {
						storeColumnMapm.put(token.substring(0, token.indexOf(":")),
								token.substring(token.indexOf(":") + 1));
					}
				}
			}
			createSelfDefinedRules(rule, storeColumnMapm, out);
		}
		out.print(i);
	}

	/**
	 * 解析规则删除,并跳转到模态窗口
	 */
	@RequestMapping(value = "/deleteExtractRuleModal", method = RequestMethod.GET)
	public ModelAndView deleteExtractRule(@RequestParam String id, @RequestParam String taskId) throws Exception {
		String[] ids = id.split(";");
		int i = 0;
		for (String d : ids) {
			i += service.getDel(d);
		}
		return new ModelAndView("sys/rule/extractRuleManageModal");
	}

	/**
	 * 角色删除
	 */
	@RequestMapping(value = "/deleteExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public int deleteExtractRule(String id) throws Exception {
		String[] ids = id.split(";");
		int i = 0;
		for (String d : ids) {
			i += service.getDel(d);
		}
		return i;
	}

	/**
	 * 跳转到模态窗口
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/extractRuleManageModal", method = RequestMethod.GET)
	public ModelAndView extractRuleManageModal(PageExtractRule record, @RequestParam String taskId) throws Exception {
		List<PageExtractRule> list = queryByPage(record, taskId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		map.put("page", record);
		map.put("taskId", taskId);
		return new ModelAndView("sys/rule/extractRuleManageModal", map);
	}

	@RequestMapping(value = "/setExtractRule", method = RequestMethod.GET)
	public ModelAndView extractRule(PageExtractRule record) throws Exception {
		List<PageExtractRule> list = service.listPageExtractRule(record);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		map.put("page", record.getPage());
		return new ModelAndView("sys/rule/extractRuleManage", map);
	}

	@RequestMapping(value = "/searchExtractRule", method = RequestMethod.GET)
	public ModelAndView searchExtractRule(PageExtractRule record) throws Exception {
		if (record.getPage() == null) {
			record.setPage(new Page());
		}

		// record.setName(Encoding.encoding(record.getName()));//本机需要增加，服务器上不需要增加嘛
		List<PageExtractRule> list = service.listPageBySearch(record, record.getPage());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		// page.setCurrentPage(page.getCurrentPage()+1);
		// record.setPage(page);
		if (record.getName() != null && record.getName().isEmpty() == false) {
			map.put("inputName", record.getName());
		}
		if (record.getTaskId() != null && record.getTaskId().isEmpty() == false) {
			map.put("inputName", record.getTaskId());
		}
		map.put("page", record.getPage());
		map.put("ruleName", record.getName());
		map.put("taskId", record.getTaskId());
		map.put("containsMulti", record.getContainsMulti());
		return new ModelAndView("sys/rule/extractRuleManage", map);
	}

	public List<PageExtractRule> queryByPage(PageExtractRule record, String taskId) throws Exception {
		List<PageExtractRule> page = service.listPageExtractRule(record, taskId);
		return page;
	}

	@RequestMapping(value = "/testExtractRule", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> testExtractRule(PageExtractRule rule, ElementExtractRuleList elementExtractRuleList,
			String storeColumnMap, String testUrl) throws Exception {

		if (testUrl == null || elementExtractRuleList == null) {
			return null;
		}

		CrawlRequest request = new CrawlRequest();
		request.setUrl(testUrl);
		request.setTimeout(10 * 1000);
		request.setRetryHandler(3);
		// 读取任务配置信息，选择是使用get方式下载，还是post方式下载
		// CRAWLER_TYPE 下载方式1:httpclient,2:htmlunit
		// TRANSMIT_MODE GET 或 POST
		CrawlerTaskConfig crawlerTask = null;
		if (rule.getTaskId() != null) {
			crawlerTask = taskService.getTaskConfig(Long.valueOf(rule.getTaskId()));
		}
		if (crawlerTask != null) {
			request.setChangecrawl(crawlerTask.getCrawlerType());
			// 发送方式：POST或者GET
			if (crawlerTask.getTransmitMode() != null) {
				if (crawlerTask.getTransmitMode().equals("GET")) {
					request.setDownloadType(0);
				}
				if (crawlerTask.getTransmitMode().equals("POST")) {
					request.setDownloadType(1);
				}
			}
			if (crawlerTask.getCrawlerType() != null) {
				request.setChangecrawl(crawlerTask.getCrawlerType());
			}
		}
		CrawlResult cr = HttpClientSingle.t(request, 3);
		String html = cr.getContent();
		StringBuffer logBuffer = new StringBuffer();
		Map<String, Object> rsMap = new HashMap<String, Object>();

		if (cr.getStatusCode().equals("200") == false || html == null) {
			logBuffer.append("网页下载失败!\r\n");
		} else {
			logBuffer.append("网页下载成功!\r\n");
		}

		Map<String, String> storeColumnMapm = new HashMap<>();
		if (storeColumnMap != null) {
			String[] tokens = storeColumnMap.split(",");
			for (String token : tokens) {
				if (token.indexOf(":") >= 0) {
					storeColumnMapm.put(token.substring(0, token.indexOf(":")),
							token.substring(token.indexOf(":") + 1));
				}
			}
		}
		List<Map<String, String>> mapList = ContentExtractor.extract(rule, elementExtractRuleList.getRules(),
				storeColumnMapm, html, testUrl, logBuffer);
		StringBuffer extract_content = new StringBuffer();
		int i = 0;
		if (mapList != null) {
			for (Map<String, String> map : mapList) {
				extract_content.append(i + ":" + map.toString() + "\r\n");
				i++;
			}
		}

		// 这里面其实是注释
		Set<String> columns = new HashSet<>();
		for (Map<String, String> map : mapList) {
			columns.addAll(map.keySet());
		}
		// 在这里检查, 自定义的规则
		if (rule.getExtractType() == 2) {

			checkSelfDefined(rule.getId(), rule.getTaskId(), columns);
		}

		rsMap.put("extract_content", extract_content.toString());
		rsMap.put("log", logBuffer.toString());
		rsMap.put("html", html);
		rsMap.put("extract_size", mapList.size());
		return rsMap;
	}

	/**
	 * 检查element_extract_rule中是否存在相关的列，如果不存在，就需要创建
	 * 在自定义插件中，由于element_extract_rule不存在相应的列，导致数据不能导出 所以需要检查并且创建
	 * 
	 * @param id
	 * @param column
	 * @param elementRules
	 */
	public void checkSelfDefined(String pageExtractRuleId, String taskId, Set<String> columnComments) {

		List<ElementExtractRule> elementExtractRules = elementRuleservice.getByPageExtractRule(pageExtractRuleId);
		String siteId = extractRuleService.getSiteId(taskId);
		List<StandardCodeContent> slist = realSiteService.querySiteScc(siteId);
		Map<String, String> commentNameMap = new HashMap<>();
		Map<String, String> nameCommentMap = new HashMap<>();
		for (StandardCodeContent scd : slist) {
			String name = scd.getFieldCode(); // 英文代码作为列名
			String comment = scd.getFieldName();// 中文注释
			commentNameMap.put(comment, name);
			nameCommentMap.put(name, comment);
		}

		for (String columnComment : columnComments) {
			// 检查每一列是否都在数据库中的element_extract_rule中有相应的表
			boolean findColumn = false;
			for (ElementExtractRule elementExtractRule : elementExtractRules) {
				if (elementExtractRule.getStoreColumnComment().equals(columnComment)) {
					findColumn = true;
					break;
				}
			}
			if (findColumn == false) {
				// 插入一条规则
				try {
					String columnName = commentNameMap.get(columnComment);
					ElementExtractRule rule = new ElementExtractRule();
					rule.setId(Numbers.uuid());
					rule.setPageExtractRuleId(pageExtractRuleId);
					rule.setExtractType("3");
					rule.setStoreColumnName(columnName);
					rule.setStoreColumnComment(columnComment);
					elementRuleservice.getAdd(rule);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
