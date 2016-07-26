package inspur.crawl.demandAna.controller;

import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.Encoding;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.demandAna.pojo.CrawlerDemand;
import inspur.crawl.demandAna.pojo.CrawlerDemandDelivery;
import inspur.crawl.demandAna.pojo.CrawlerOpinion;
import inspur.crawl.demandAna.pojo.DemandAnalyse;
import inspur.crawl.demandAna.service.DemandAnalyService;
import inspur.crawl.siteManage.pojo.RealSiteCode;
import inspur.crawl.siteManage.service.RealSiteService;
import inspur.crawl.sysManage.pojo.Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/demandAnaly")
public class DemandAnalyController extends BaseController{
	@Resource
	DemandAnalyService service;
	@Resource
	RealSiteService rsservice;
	
	@InitBinder("demandAnaly") 
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("demandAnaly."); 
	}
	/**
	 * 管理
	 */
	@RequestMapping(value = "/setdemand", method = RequestMethod.GET)
	public ModelAndView demandManage(CrawlerDemand demand) throws Exception {
		List<CrawlerDemand> list = service.queryDemand();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("demands", list);
		map.put("page", demand);
		return request("sys/demandAna/demandManage", map);
	}
	/**
	 * 跳转新建需求页面
	 */
	@RequestMapping(value = "/demandAdd", method = RequestMethod.GET)
	public ModelAndView demandAdd() throws Exception {
		return request("sys/demandAna/demandAdd");
	}
	
	/**
	 * 提交新建需求
	 */
	@RequestMapping(value = "/demandAddSub", method = RequestMethod.POST)
	public ModelAndView demandAddSub(HttpServletRequest request, @RequestParam MultipartFile[] demandFile, CrawlerDemand demand){
		int i = 0;
		Map<String,String> mapResult = new HashMap<String,String>();
		try {
			HttpSession session = request.getSession();
			String username = session.getAttribute(Sessions.SESSION_USER).toString();
			Account account = service.selectName(username);
			
			String enc = request.getCharacterEncoding();
			System.out.println("enc+++++++++++++"+enc);
			String demandId = Numbers.uuid();
			demand.setDemandId(demandId);
			demand.setDemandTime(new Date());
			String name = account.getName();
			demand.setDemandCreater(name);
			demand.setDemandStatus("01");
			mapResult = uploadFile(demandFile, demandId, request);
			demand.setDemandFilePath(mapResult.get("fileName"));
			i = service.insertOne(demand);
			if(i==1){
				DemandAnalyse analyse = new DemandAnalyse();
				String id = Numbers.uuid();
				analyse.setDemandId(demandId);
				analyse.setId(id);
				i = service.insertAnalyse(analyse);
				if(i==1){
					CrawlerOpinion opinion = new CrawlerOpinion();
					String opinionId = Numbers.uuid();
					opinion.setDemandId(demandId);
					opinion.setId(opinionId);
					i = service.insertOpinion(opinion);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		List<CrawlerDemand> list = service.queryDemand();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("demands", list);
		map.put("flag", i);
		map.put("fileflag", map.get("flag"));
		return request("sys/demandAna/demandManage", map);
	}
	
	public static Map<String,String> uploadFile(MultipartFile[] demandFile, String demandId, HttpServletRequest request){
		int i = 0;
		String fileName = null;
		Map<String,String> map = new HashMap<String,String>();
		for(MultipartFile myfile : demandFile){
            if(myfile.isEmpty()){  
            	i=3;
                System.out.println("文件未上传");
            }else{
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
                String realPath = request.getSession().getServletContext().getRealPath("/upload").replace("/jobManage", "");  
//                String realPath = "\\bigdata" + "\\upload";
                System.out.println(realPath);
            	String demandPath = realPath+File.separator+demandId;
                File dir = new File(realPath);
                if(!dir .exists()  && !dir .isDirectory()){
                	dir .mkdir();
                }
                File dir2 = new File(demandPath);
            	if(!dir2 .exists()  && !dir2 .isDirectory()){
            		dir2.mkdir();
            	}
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
                try {
                	
                	if(fileName==null||fileName.isEmpty()){
                		fileName=myfile.getOriginalFilename();
                	}else{
                		fileName =fileName + ";" + myfile.getOriginalFilename();
                	}
                	System.out.println("fileName+++++++++++++++"+new String(fileName.getBytes(),"gbk"));
					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(demandPath, myfile.getOriginalFilename()));
					i=1;
                } catch (IOException e) {
					i=0;
					e.printStackTrace();
				}
                map.put("fileName", fileName);
                map.put("flag", i+"");
//                demand.setDemandFilePath(fileName);
            }
        }
		return map;
	}
	
	 @RequestMapping("/download")
	    public String download(String demandId, String fileName, HttpServletRequest request,
	            HttpServletResponse response) throws UnsupportedEncodingException {
	        response.setCharacterEncoding("utf-8");
	        response.setContentType("multipart/form-data");
	        response.setHeader("Content-Disposition", "attachment;fileName="
	                +  java.net.URLEncoder.encode(fileName, "UTF-8"));
	        try {
	            String path = request.getSession().getServletContext().getRealPath("/upload").replace("/crawlManage", "");
//	        	String path = "\\bigdata" + "\\upload";
	        	InputStream inputStream = new FileInputStream(new File(path
	                    + File.separator + demandId + File.separator + fileName));
	 
	            OutputStream os = response.getOutputStream();
	            byte[] b = new byte[2048];
	            int length;
	            while ((length = inputStream.read(b)) > 0) {
	                os.write(b, 0, length);
	            }
	 
	             // 这里主要关闭。
	            os.close();
	 
	            inputStream.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	            //  返回值要注意，要不然就出现下面这句错误！
	            //java+getOutputStream() has already been called for this response
	        return null;
	    }
	
	/**
	 * 跳转到修改需求页面
	 */
	@RequestMapping(value = "/demandupdate", method = RequestMethod.GET)
	public ModelAndView demandUpdate(@RequestParam String id) throws Exception {
		CrawlerDemand demand = service.queryDemandById(id);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("demand", demand);
		return request("sys/demandAna/demandUpdate", map);
	}
	
	/**
	 * 修改提交
	 */
	@RequestMapping(value = "/demandUpdateSub", method = RequestMethod.POST)
	@ResponseBody
	public void demandUpdateSub(HttpServletRequest request, CrawlerDemand demand, PrintWriter out){
		int i = 0;
		try {
			HttpSession session = request.getSession();
			String username = session.getAttribute(Sessions.SESSION_USER).toString();
			Account account = service.selectName(username);
			demand.setDemandTime(new Date());
			String name = account.getName();
			demand.setDemandCreater(name);
			i = service.updateOne(demand);
		}catch(Exception e){
			e.printStackTrace();
		}
		out.print(i);
	}
	
	/**
	 * 删除需求
	 */
	@RequestMapping(value = "/demanddel", method = RequestMethod.POST)
	@ResponseBody
	public void demandDel(@RequestParam String id, PrintWriter out){
		int i = service.delDemandById(id);
		out.print(i);
	}
	/**
	 * 弹出分析页面
	 */
	@RequestMapping(value = "/demandAnalyse", method = RequestMethod.GET)
	public ModelAndView demandAnalyse(@RequestParam String demandId){
		DemandAnalyse analyse = service.queryAnalyseByDemandId(demandId);
		Map<String,Object> map = new HashMap<String, Object>();
		List<RealSiteCode> list = new ArrayList<RealSiteCode>();
		map.put("analyse", analyse);
		if (analyse.getSiteId()!=null) {
			String[] siteId = analyse.getSiteId().split(";");
			for (int i = 0; i < siteId.length; i++) {
				RealSiteCode site = rsservice.querySiteBySiteId(siteId[i]);
				list.add(site);
			}
			map.put("sites", list);
		}
		return 	request("sys/demandAna/demandAnalyse", map);
	}
	
	/**
	 * 列出所有站点
	 */
	@RequestMapping(value = "/listsite", method = RequestMethod.POST)
	@ResponseBody
	public void siteQuery(PrintWriter out) throws Exception {
		List<RealSiteCode> list = rsservice.queryAllSite();
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(RealSiteCode scc : list){
			sb.append("{id:'"+scc.getId()+"',pId:'"+scc.getParentId()+"',url:'"+scc.getUrl()+"',name:'"+scc.getName()+"',type:'"+scc.getSiteType()+"',description:'"+scc.getSiteDescription()+"',publisherId:'"+scc.getPublisherId()+"',executionCycle:'"+scc.getExecutionCycle()+"',dueTime:'"+scc.getDueTime()+"',otherDescription:'"+scc.getOtherDescription()+"',checked: false},");
		}
		if(list.size()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		out.print(sb.toString());
	}
	/**
	 * 查询选中站点
	 */
	@RequestMapping(value = "/siteq", method = RequestMethod.POST)
	@ResponseBody
	public DemandAnalyse siteQ(@RequestParam String id) throws Exception {
		return service.querySiteById(id);
	}
	/**
	 * 设置交付条件
	 */
	@RequestMapping(value = "/setDelivery", method = RequestMethod.POST)
	@ResponseBody
	public List<CrawlerDemandDelivery> setDelivery(@RequestParam String siteId, @RequestParam String demandId) throws Exception {
		List<CrawlerDemandDelivery> list = service.queryDeliveryCondition(siteId,demandId);
		return list;
	}
	/**
	 * 需求分析结果保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, @RequestParam MultipartFile[] anafilePath, DemandAnalyse analyse, @RequestParam String analyseId){
		int i = 0;
		Map<String,String> mapResult = new HashMap<String,String>();
		try {
			HttpSession session = request.getSession();
			String username = session.getAttribute(Sessions.SESSION_USER).toString();
			Account account = service.selectName(username);
			analyse.setPublishTime(new Date());
			analyse.setId(analyseId);
			String name = account.getName();
			analyse.setPublisher(name);
			System.out.println(analyse);
			mapResult = uploadFile(anafilePath, analyseId, request);
			analyse.setFilePath(mapResult.get("fileName"));
			i = service.updateAnalyse(analyse);
			if(i==1){
				i = service.updataDemandStatus("02",analyse.getDemandId());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String, Object>();
//		List<RealSiteCode> list = new ArrayList<RealSiteCode>();
//		String[] siteId = analyse.getSiteId().split(";");
//		for(int j = 0;j<siteId.length;j++){
//			RealSiteCode site = rsservice.querySiteBySiteId(siteId[j]);
//			list.add(site);
//		}
//		map.put("sites", list);
//		map.put("analyse", analyse);
//		map.put("fileflag", mapResult.get("flag"));
//		map.put("flag", i);
		List<CrawlerDemand> list = service.queryDemand();
		map.put("demands", list);
		return request("sys/demandAna/demandManage", map);
	}
	//未修改的save
	public void save1(HttpServletRequest request, DemandAnalyse analyse, @RequestParam String id, PrintWriter out){
		int i = 0;
		try {
			HttpSession session = request.getSession();
			String username = session.getAttribute(Sessions.SESSION_USER).toString();
			Account account = service.selectName(username);
			analyse.setPublishTime(new Date());
			analyse.setId(id);
			String name = account.getName();
			analyse.setPublisher(name);
//			i = service.updateAnalyse(analyse);
		}catch(Exception e){
			e.printStackTrace();
		}
		out.print(i);
	}
	
	/**
	 * 意见反馈页面
	 */
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	@ResponseBody
	public String feedback(@RequestParam String demandId) throws Exception {
		CrawlerOpinion opinion = service.queryOpinion(demandId);
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("feedback", opinion);
//		return request("sys/demandAna/demandFeedBack", map);
		JSONObject json = JSONObject.fromObject(opinion);
		return json.toString();
	}
	/**
	 * 提交交付条件
	 */
	@RequestMapping(value = "/addfieldsub", method = RequestMethod.POST)
	@ResponseBody
	public void addFieldSub(HttpServletRequest request, CrawlerDemandDelivery scc, @RequestParam String demandId, @RequestParam String siteId, @RequestParam String fieldId, @RequestParam String fieldName, PrintWriter out) throws Exception{
		int i = 0;
		try { 
			scc.setDemandId(demandId);
			scc.setSiteId(siteId);
			String[] fieldIdA = fieldId.split(";");
			String[] fieldNameA = fieldName.split(";");
			String conditionDes = scc.getConditionDes();
			String conditionVal = scc.getConditionVal();
			String[] conditionDesA = conditionDes.split(",");
			String[] conditionValA = conditionVal.split(",");
			int k = service.delDeliveryConditionById(demandId,siteId);
			if(k!=0){
				for(int j=0;j<conditionDesA.length;j++){
					scc.setFieldId(fieldIdA[j]);
					scc.setFieldName(fieldNameA[j]);
					scc.setConditionDes(conditionDesA[j]);
					scc.setConditionVal(conditionValA[j]);
					i = service.insertDeliveryCondition(scc);
				}
			}
	     } catch (Exception e) {  
	          e.printStackTrace();     
	     } 
		out.print(i);
	}
	
}
