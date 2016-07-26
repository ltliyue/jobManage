package inspur.crawl.ruleManage.controller;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.HttpClientSingle;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.ruleManage.pojo.LoopParseRule;
import inspur.crawl.ruleManage.pojo.LoopVarRule;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapList;
import inspur.crawl.ruleManage.pojo.ParseRuleTemp;
import inspur.crawl.ruleManage.service.LoopVarRuleService;
import inspur.crawl.ruleManage.service.ParseRuleTempService;
import inspur.crawl.taskManage.pojo.CrawlerTaskConfig;
import inspur.crawl.taskManage.service.TaskService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inspur.avro.model.java.UrlType;
import com.inspur.kafka.crawl.model.CrawlRequest;
import com.inspur.kafka.crawl.model.CrawlResult;

@RestController
@RequestMapping("/parseRuleTemp")
public class ParseRuleTempController extends BaseController {
	@Resource
	ParseRuleTempService parseRuleTempService;

	@Resource
	LoopVarRuleService loopVarRuleService;
	
	@Resource
	TaskService taskService;
	

	@InitBinder("parseRuleTemp")
	public void typeBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("parseRuleTemp.");
	}

	/**
	 * 内容抽取规则管理
	 */
	@RequestMapping(value = "/setParseRule", method = RequestMethod.GET)
	public ModelAndView userManage(ParseRuleTemp record) throws Exception {
		List<ParseRuleTemp> list = queryByPage(record);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		map.put("page", record.getPage());
		return new ModelAndView("sys/rule/parseRuleManage", map);
	}

	@RequestMapping(value = "/searchParseRule", method = RequestMethod.GET)
	public ModelAndView extractRule(ParseRuleTemp record) throws Exception {
		if (record.getPage() == null) {
			record.setPage(new Page());
		}
		// record.setName(Encoding.encoding(record.getName()));//本机上需要增加，服务器上不需要增加?
		List<PageExtractRule> list = parseRuleTempService.listPageBySearch(record, record.getPage());
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
		return new ModelAndView("sys/rule/parseRuleManage", map);
	}

	public List<ParseRuleTemp> queryByPage(ParseRuleTemp record) throws Exception {
		List<ParseRuleTemp> page = parseRuleTempService.listPageParseRule(record);
		return page;
	}

	public List<ParseRuleTemp> queryByPage(ParseRuleTemp record, String taskId) throws Exception {
		List<ParseRuleTemp> page = parseRuleTempService.listPageParseRule(record, taskId);
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
	public ModelAndView parseRuleManageModal(ParseRuleTemp record, @RequestParam String taskId) throws Exception {
		List<ParseRuleTemp> list = queryByPage(record, taskId);
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
	public ModelAndView parseRule(@RequestParam String t, @RequestParam String id, @RequestParam int modal)
			throws Exception {
		if ("1".equals(t)) {
			return request("sys/rule/parseRuleAdd").addObject("modal", modal);
		} else if ("2".equals(t)) {
			return request("sys/rule/parseRuleUpdate").addObject("id", id).addObject("modal", modal);
		}
		return null;
	}
	
	
	/**
	 * 使用frameset，左右分栏式，左侧为规则，右边为原始网页
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/framesetTest", method = RequestMethod.GET)
	public ModelAndView framesetTest(@RequestParam String taskId)
			throws Exception {
		return request("sys/rule/framesetTest").addObject("taskid",taskId);
	}
	

	/**
	 * 跳转到模态窗口
	 * 
	 * @param t
	 * @param id
	 * @param modal
	 *            是否返回模态框，还是非模态框，模态框中的网页包含下一步，非模态框中的网页不包含下一步
	 *            模态框中的网页，提交后返回该任务的配置列表，非模态框中的网页返回所有任务的配置列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/parseRuleModal", method = RequestMethod.GET)
	public ModelAndView parseRuleModal(@RequestParam String t, @RequestParam String id, @RequestParam int modal)
			throws Exception {
		if ("1".equals(t)) {
			return request("sys/rule/parseRuleAddModal").addObject("taskId", id).addObject("modal", modal);
		} else if ("2".equals(t)) {
			return request("sys/rule/parseRuleUpdateModal").addObject("id", id).addObject("modal", modal);
		}
		return null;
	}

	/**
	 * 查询所有解析规则
	 */
	@RequestMapping(value = "/getAllParseRule", method = RequestMethod.POST)
	@ResponseBody
	public List<ParseRuleTemp> getAllParseRule() throws Exception {
		List<ParseRuleTemp> list = parseRuleTempService.queryAll();
		return list;
	}

	/**
	 * 查询可用解析规则
	 */
	@RequestMapping(value = "/enableParseRule", method = RequestMethod.POST)
	@ResponseBody
	public List<ParseRuleTemp> userEQuery() throws Exception {
		List<ParseRuleTemp> list = parseRuleTempService.queryEnable();
		return list;
	}

	/**
	 * 解析规则增加
	 */
	@RequestMapping(value = "/addParseRule", method = RequestMethod.POST)
	@ResponseBody
	public void addParseRule(ParseRuleTemp rule, PrintWriter out) throws Exception {
		// 生成19位UUID
		String id = Numbers.uuid();
		rule.setId(id);
		rule.setTime(new Date());

		int i = parseRuleTempService.getAdd(rule);
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
	public ParseRuleTemp getParseRule(String id) throws Exception {
		return parseRuleTempService.getRule(id);
	}

	/**
	 * 解析规则修改
	 */
	@RequestMapping(value = "/updateParseRule", method = RequestMethod.POST)
	@ResponseBody
	public void updateParseRule(ParseRuleTemp rule, PrintWriter out) throws Exception {

		int i = parseRuleTempService.getUp(rule);

		out.print(i);
	}

	// 生成LoopVarRule，变量的个数
	public List<LoopVarRule> create(String[] loopVarRules, HttpServletRequest request, String taskId)
			throws IOException {
		List<LoopVarRule> rules = new ArrayList<LoopVarRule>();
		if (loopVarRules == null || loopVarRules.length == 0)
			return rules;
		// loopParseRuleId:loopVarRuleId:具体的loopVarRule的内容

		for (int i = 0; i < loopVarRules.length; i++) {
			int fidx = loopVarRules[i].indexOf(":");
			int sidx = fidx + loopVarRules[i].substring(loopVarRules[i].indexOf(":") + 1).indexOf(":") + 1;
			String loopParseRuleId = loopVarRules[i].substring(0, fidx);
			String loopVarRuleId = loopVarRules[i].substring(fidx + 1, sidx);
			String loopVarRuleStr = loopVarRules[i].substring(sidx + 1);
			LoopVarRule varRule = LoopParseRuleController.create(request, loopVarRuleStr, taskId);
			if (varRule != null) {
				varRule.setId(loopVarRuleId);
				varRule.setLoopParseRuleId(loopParseRuleId);
				if (varRule.getLoopStrList() != null
						&& varRule.getLoopStrList().contains(LoopParseRuleController.abbr_str)) {
					// 从数据库中读取真正的内容
					LoopVarRule actVarRule = loopVarRuleService.getRule(loopVarRuleId);
					varRule.setLoopStrList(actVarRule.getLoopStrList());
				}
				//从文件中读取内容
				if (varRule.getLoopStrList() != null
						&& varRule.getLoopStrList().startsWith("file:////")) {
					// 从数据库中读取真正的内容
					String fileName = varRule.getLoopStrList().substring("file:////".length());
					if (fileName.lastIndexOf("\\") >= 0) {
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					}
					String realPath = request.getSession().getServletContext().getRealPath("/upload/" + taskId);
					String content = FileUtils.readFileToString(new File(realPath + "/" + fileName), "utf-8");
					content = content.replaceAll("\r\n", ",");
					varRule.setLoopStrList(content);
				}
				rules.add(varRule);
			}
		}
		return rules;
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
	public Map<String, Object> testParseRule(HttpServletRequest hrequest, ParseRuleTemp rule,
			ParseRuleMidMapList midMaps, LoopParseRule loopParseRule, String[] loopVarRules, String testUrl
			)
					throws Exception {
		
		List<LoopVarRule> loopVarRuleList = create(loopVarRules, hrequest, String.valueOf(rule.getTaskId()));

		String html = null;
		StringBuffer buffer = new StringBuffer("");
		if (testUrl != null) {
			CrawlRequest request = new CrawlRequest();
			request.setUrl(testUrl);
			request.setTimeout(10 * 1000);
			request.setRetryHandler(3);
			//读取任务配置信息，选择是使用get方式下载，还是post方式下载
			//CRAWLER_TYPE 下载方式1:httpclient,2:htmlunit
			//TRANSMIT_MODE GET 或 POST
			CrawlerTaskConfig crawlerTask = null;
			if(rule.getTaskId()!=null){
				crawlerTask = taskService.getTaskConfig(rule.getTaskId());
			}
			if(crawlerTask!=null){
				request.setChangecrawl(crawlerTask.getCrawlerType());
				//发送方式：POST或者GET
				if(crawlerTask.getTransmitMode()!=null){
					if(crawlerTask.getTransmitMode().equals("GET")){
						request.setDownloadType(0);
					}
					if(crawlerTask.getTransmitMode().equals("POST")){
						request.setDownloadType(1);
					}
				}
				if(crawlerTask.getCrawlerType()!=null){
					request.setChangecrawl(crawlerTask.getCrawlerType());
				}
			}
			try {
				CrawlResult cr = HttpClientSingle.t(request,3);//下载失败后最多尝试3次
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

		Map<String, Object> map = new HashMap<String, Object>();
		List<LoopParseRule> loopParseRules = new ArrayList<LoopParseRule>();
		Set<String> varRuleIds = new LinkedHashSet<String>();
		//应该注意传递过来的varRule的loopParseRuleId不应该为空，如果没有，就虚构一个
		for (LoopVarRule varRule : loopVarRuleList) {
			if(varRule.getLoopParseRuleId()!=null && varRule.getLoopParseRuleId().isEmpty()==false){
				varRuleIds.add(varRule.getLoopParseRuleId());
			}
		}

		int i = 0;
		if (loopParseRule.getLoopVars() == null) {
			buffer.append("循环变量集合为null\r\n");
		} else {
			String[] loopVars = loopParseRule.getLoopVars().split(",");
			for (LoopVarRule varRule : loopVarRuleList) {
				varRule.setLoopVarName(loopVars[i%loopVars.length]);
				i++;
			}
		}
		for (String varRuleId : varRuleIds) {
			LoopParseRule tLoopParseRule = new LoopParseRule(loopParseRule);
			tLoopParseRule.setId(varRuleId);
			loopParseRules.add(tLoopParseRule);
			i++;
		}

		Map<String, UrlType> urlTypeMap = UrlParser.parseAndLog(rule, midMaps.getMidMaps(), loopParseRules,
				loopVarRuleList, html, testUrl, buffer);
		if(urlTypeMap==null){
			urlTypeMap = new HashMap<String, UrlType>();
		}
		map.put("srcUrl", testUrl);
		map.put("extractUrlSize", urlTypeMap.size());
	
		
		//超过500条就省略
		if(urlTypeMap.size()<500){
			map.put("extractUrls", urlTypeMap.toString());
		}else{
			Map<String, UrlType> subUrlTypeMap = new LinkedHashMap<String,UrlType>();
			int j = 0;
			Set<Map.Entry<String, UrlType>> urlTypeMapEntry = urlTypeMap.entrySet();
			for(Map.Entry<String, UrlType> entry:urlTypeMapEntry){
				subUrlTypeMap.put(entry.getKey(), entry.getValue());
				j++;
				if(j>=500) break;
			}
			map.put("extractUrls", urlTypeMap.toString());
		}
		map.put("log", buffer.toString());
		map.put("testUrlHtml", html);
		System.out.println("测试url：" + testUrl + ",发现URL的条数：" + urlTypeMap.size());
		//Gson gson=new Gson(); 
		//writer.write(gson.toJson(map));
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
			i += parseRuleTempService.getDel(d);
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
			i += parseRuleTempService.getDel(d);
		}
		ParseRuleTemp record = new ParseRuleTemp();
		List<ParseRuleTemp> list = queryByPage(new ParseRuleTemp(), taskId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rules", list);
		map.put("page", record);
		map.put("taskId", taskId);
		return new ModelAndView("sys/rule/parseRuleManageModal", map);
	}
	//////////////////////////////
	
	
	/**
	 * 上传Jar包到hdfs上
	 * @throws IOException 
	 */
	@RequestMapping(value = "/uploadJar", method = RequestMethod.POST)
	public void uploadJar(HttpServletRequest request, @RequestParam MultipartFile[] jarFile,
			@RequestParam String taskId, PrintWriter out) throws IOException {

		int i = 0;
		String fileName = null;
		Map<String, String> map = new HashMap<String, String>();
		
		FileSystem fs = FileSystem.get(new Configuration());
		
		for (MultipartFile myfile : jarFile) {
			if (myfile.isEmpty()) {
				i = 3;
				System.out.println("文件未上传");
			} else {
				// 上传到hdfs文件中，hdfs文件的名称是：jar包名字+用户名字+随机ID+.jar
				
				String dirStr =  UrlParser.ruleJarHdfsDir;//"/user/jstorm/lib";//上传到/user/jstorm/lib下面
				Path dir = new Path(dirStr);
				if (fs.exists(dir)==false) {
					fs.mkdirs(dir);
				}
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				try {

					if (fileName == null || fileName.isEmpty()) {
						fileName = myfile.getOriginalFilename();
					} else {
						fileName = fileName + ";" + myfile.getOriginalFilename();
					}
					if(fileName.endsWith(".jar")==false) {
						out.print("上传失败!不是Jar文件");
						return;
					}
					fileName = fileName.substring(0, fileName.lastIndexOf(".jar"));
					String uuid = Numbers.uuid();
					HttpSession session = request.getSession();
					String username = session.getAttribute(Sessions.SESSION_USER).toString();
					fileName = fileName +"_"+username+"_"+uuid+".jar";
					InputStream ins = myfile.getInputStream();
					OutputStream outs = fs.create(new Path(dirStr+"/"+fileName), true);
					IOUtils.copyBytes(ins, outs, 4096, true);
					i = 1;
					out.print("hdfs://"+dirStr+"/"+fileName);
				} catch (IOException e) {
					i = 0;
					e.printStackTrace();
				}
				map.put("fileName", fileName);
				map.put("flag", i + "");
			}
		}
	}

	/**
	 * 提交新建需求
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request, @RequestParam MultipartFile[] loopStrListFile,
			@RequestParam String taskId, PrintWriter out) {

		String enc = request.getCharacterEncoding();
		int i = 0;
		String fileName = null;
		Map<String, String> map = new HashMap<String, String>();
		for (MultipartFile myfile : loopStrListFile) {
			if (myfile.isEmpty()) {
				i = 3;
				System.out.println("文件未上传");
			} else {
				// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext().getRealPath("/upload/" + taskId);
				// String realPath = "\\bigdata" + "\\upload";
				System.out.println(realPath);
				String demandPath = realPath;
				File dir = new File(realPath);
				if (!dir.exists() && !dir.isDirectory()) {
					dir.mkdir();
				}
				File dir2 = new File(demandPath);
				if (!dir2.exists() && !dir2.isDirectory()) {
					dir2.mkdir();
				}
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
				try {

					if (fileName == null || fileName.isEmpty()) {
						fileName = myfile.getOriginalFilename();
					} else {
						fileName = fileName + ";" + myfile.getOriginalFilename();
					}
					System.out.println(
							"fileName+++++++++++++++" + demandPath + "/" + new String(fileName.getBytes(), "gbk"));
					// FileUtils.copyInputStreamToFile(myfile.getInputStream(),
					// new File(demandPath, myfile.getOriginalFilename()));
					BufferedReader reader = new BufferedReader(new InputStreamReader(myfile.getInputStream(), "utf-8"));
					BufferedWriter writer = new BufferedWriter(
							new OutputStreamWriter(new FileOutputStream(demandPath + "/" + fileName), "utf-8"));
					String line = null;
					while ((line = reader.readLine()) != null) {
						writer.write(line + "\r\n");
					}
					writer.flush();
					reader.close();
					writer.close();
					i = 1;
				} catch (IOException e) {
					i = 0;
					e.printStackTrace();
				}
				map.put("fileName", fileName);
				map.put("flag", i + "");
				// demand.setDemandFilePath(fileName);
			}
		}
		out.print("文件上传成功");
	}
}
