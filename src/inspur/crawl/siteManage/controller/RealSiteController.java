package inspur.crawl.siteManage.controller;

import inspur.crawl.codeManage.controller.DateFormatToYYYY;
import inspur.crawl.codeManage.pojo.SiteCode;
import inspur.crawl.codeManage.pojo.StandardCode;
import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.siteManage.pojo.RealSiteCode;
import inspur.crawl.siteManage.pojo.SiteItemsCode;
import inspur.crawl.siteManage.service.RealSiteService;
import inspur.crawl.sysManage.pojo.Account;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/datasiteitem")
public class RealSiteController extends BaseController{
	@Resource
	RealSiteService service;
	@InitBinder("datasiteitem")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("datasiteitem.");  
	}
	/**
	 * 站点维护
	 */
	@RequestMapping(value = "/setsiteitem", method = RequestMethod.GET)
	public ModelAndView setSite(SiteCode site) throws Exception {
		return request("sys/siteManage/siteItemManage");
	}
	
	@RequestMapping(value = "/selectAll", method = RequestMethod.POST)
	@ResponseBody
	public List<RealSiteCode> selectAll(){
		List<RealSiteCode> list = service.selectAll();
		return list;
	}
	/**
	 * 查询所有站点
	 */
	@RequestMapping(value = "/allsiteitem", method = RequestMethod.POST)
	@ResponseBody
	private void siteItemQuery(PrintWriter out, int pages, @RequestParam String name, @RequestParam String type) throws Exception {
		int startNum = (pages-1)*10;
		int endNum = pages*10;
		List<RealSiteCode> list = service.queryAll(startNum,endNum,name,type);
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for(RealSiteCode rsc : list){
			i++;
			String siteType = rsc.getSiteType();
			String[] siteTypeA = siteType.split("--");
			String siteType1 = null;
			String siteType2 = null;
			if(siteTypeA.length==2){
				siteType1 = siteTypeA[0];
				siteType2 = siteTypeA[1]; 
			}else{
				siteType1 = siteTypeA[0];
			}
			String parentId = rsc.getId();
			String formatedDate = DateFormatToYYYY.dateFormate(rsc.getPublishTime().toString());
			sb.append("<tr class=\"treegrid-"+rsc.getId()+" active\"><td>");
			sb.append(i+"</td><td><a onclick=\"OpenViewModel('"+rsc.getId()+"','"+rsc.getName()+"','"+rsc.getUrl()+"','"+siteType1+"','"+siteType2+"','"+rsc.getSiteDescription()+"','"+rsc.getPublisherId()+"','"+rsc.getExecutionCycle()+"','"+rsc.getDueTime()+"','"+rsc.getOtherDescription()+"')\" data-toggle=\"modal\" data-target=\"#addModal\">");
			sb.append(rsc.getName()+"</a></td><td  class='content_rwgl_pzurl' style='width:200px'>");
			sb.append(rsc.getUrl()+"</td><td>");
			sb.append(rsc.getSiteType()+"</td><td>");
			sb.append(rsc.getSiteDescription()+"</td><td>");
			sb.append(rsc.getPublisherId()+"</td><td >");
			sb.append(formatedDate+"</td><td >");
			sb.append("<a href=\"\"   data-toggle=\"modal\" data-target=\"#addModal\" id=\"add_"+rsc.getId()+"\" onclick=\"OpenAddModel('"+rsc.getId()+"')\"  >添加入口</a>   <a href=\"\"   data-toggle=\"modal\" id=\"turn_"+rsc.getId()+"\" onclick=\"OpenTurnModel('"+rsc.getId()+"','"+rsc.getName()+"')\"  >数据项维护</a>   <a href=\"\" data-toggle=\"modal\" id=\"edit_"+rsc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenEditModel('"+rsc.getId()+"','"+rsc.getName()+"','"+rsc.getUrl()+"','"+siteType1+"','"+siteType2+"','"+rsc.getSiteDescription()+"','"+rsc.getPublisherId()+"','"+rsc.getExecutionCycle()+"','"+rsc.getDueTime()+"','"+rsc.getOtherDescription()+"','"+rsc.getParentId()+"')\">编辑</a>  <a href=\"\"  onclick=\"del('"+rsc.getId()+"')\">删除</a></td></tr>");
			listC(i+"",sb,parentId);
		}
		int zs = service.countRealSiteCode(name,type);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sb", sb.toString());
		map.put("zys", zs);
		map.put("name", name);
		map.put("pages", pages);
		JSONArray json = JSONArray.fromObject(map);
		out.print(json);
	}
	
	public void listC(String ii,StringBuffer sb,String parentId) throws ParseException{
		List<RealSiteCode> list = service.queryChild(parentId);
		int i = 0;
		for(RealSiteCode rsc : list){
			i++;
			String siteType = rsc.getSiteType();
			String[] siteTypeA = siteType.split("--");
			String siteType1 = null;
			String siteType2 = null;
			if(siteTypeA.length==2){
				siteType1 = siteTypeA[0];
				siteType2 = siteTypeA[1]; 
			}else{
				siteType1 = siteTypeA[0];
			} 
			String formatedDate = DateFormatToYYYY.dateFormate(rsc.getPublishTime().toString());
			sb.append("<tr class=\"treegrid-"+rsc.getId()+" treegrid-parent-"+rsc.getParentId()+" active\"><td>");
			sb.append(ii+"."+i+"</td><td><a onclick=\"OpenViewModel('"+rsc.getId()+"','"+rsc.getName()+"','"+rsc.getUrl()+"','"+siteType1+"','"+siteType2+"','"+rsc.getSiteDescription()+"','"+rsc.getPublisherId()+"','"+rsc.getExecutionCycle()+"','"+rsc.getDueTime()+"','"+rsc.getOtherDescription()+"')\" data-toggle=\"modal\" data-target=\"#addModal\">");
			sb.append(rsc.getName()+"</a></td><td  class='content_rwgl_pzurl' style='width:200px'>");
			sb.append(rsc.getUrl()+"</td><td>");
			sb.append(rsc.getSiteType()+"</td><td>");
			sb.append(rsc.getSiteDescription()+"</td><td>");
			sb.append(rsc.getPublisherId()+"</td><td >");
			sb.append(formatedDate+"</td><td >");
			sb.append("<a href=\"\"   data-toggle=\"modal\" id=\"turn_"+rsc.getId()+"\" onclick=\"OpenTurnModel('"+rsc.getId()+"','"+rsc.getName()+"')\"  >数据项维护</a>   <a href=\"\"  data-toggle=\"modal\" id=\"edit_"+rsc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenEditModel('"+rsc.getId()+"','"+rsc.getName()+"','"+rsc.getUrl()+"','"+siteType1+"','"+siteType2+"','"+rsc.getSiteDescription()+"','"+rsc.getPublisherId()+"','"+rsc.getExecutionCycle()+"','"+rsc.getDueTime()+"','"+rsc.getOtherDescription()+"','"+rsc.getParentId()+"')\">编辑</a>  <a href=\"\"  onclick=\"del('"+rsc.getId()+"')\">删除</a></td></tr>");
			listC(ii+"."+i,sb,rsc.getId());
		}
	}
	/**
	 * 查询一级站点类型
	 */
	@RequestMapping(value = "/querysitetype", method = RequestMethod.POST)
	@ResponseBody
	public List querySite(@RequestParam String pid){
		List list = service.querySiteType(pid);
		return list;
	}
	/**
	 * 添加站点
	 */
	@RequestMapping(value = "/addnextrsc", method = RequestMethod.POST)
	@ResponseBody
	public void addNext(HttpServletRequest request, RealSiteCode rsc,PrintWriter out) throws Exception{
		int i = 0;
		try {  
			//生成19位UUID
			HttpSession session = request.getSession();
			String username = session.getAttribute(Sessions.SESSION_USER).toString();
			Account account = service.selectName(username);
			String id = Numbers.uuid();
			rsc.setId(id);
//			String siteType = Encoding.encoding(siteType1)+"--"+Encoding.encoding(siteType2);
//			rsc.setSiteType(siteType);
			rsc.setPublishTime(new Date());
			rsc.setValidateFlag("1");
			String name = account.getName();
			rsc.setPublisherId(name);
			System.out.println("rsc---------------------:"+rsc);
			i = service.insertOne(rsc);
	     } catch (Exception e) {  
	          e.printStackTrace();     
	     } 
		out.print(i);
	}
	/**
	 * 修改站点
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void updateSite(HttpServletRequest request,RealSiteCode rsc,PrintWriter out) throws Exception{
		int i = 0;
		try {  
			//生成19位UUID
//			String siteType = Encoding.encoding(siteType1)+"--"+Encoding.encoding(siteType2);
//			rsc.setSiteType(siteType);
			rsc.setPublishTime(new Date());
			rsc.setValidateFlag("1");
			HttpSession session = request.getSession();
			String username = session.getAttribute(Sessions.SESSION_USER).toString();
			Account account = service.selectName(username);
			String name = account.getName();
			rsc.setPublisherId(name);
			i = service.updateOne(rsc);
	     } catch (Exception e) {  
	          e.printStackTrace();     
	     } 
		out.print(i);
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delrealsite", method = RequestMethod.POST)
	@ResponseBody
	public int delSite(@RequestParam String id){
		int i = service.delSiteById(id);
		return i;
	}
	@RequestMapping(value = "/turntositeitem", method = RequestMethod.GET)
	public ModelAndView addField(@RequestParam String id,@RequestParam String name) throws Exception {
//		List<StandardCodeContent> fields = service.queryFieldById(id);
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("fields", fields);
		map.put("name", name);
		map.put("id", id);
		return new ModelAndView("sys/siteManage/siteItemAdd",map);//request("sys/codeManage/fieldAdd").addObject("fields",fields).addObject("sname", Encoding.encoding(name));
	}
	/**
	 * 需求分析中使用的数据项维护
	 */
	@RequestMapping(value = "/turntoitemDemand", method = RequestMethod.GET)
	public ModelAndView addFieldInDemand(@RequestParam String id,@RequestParam String name, @RequestParam String demandId) throws Exception {
//		List<StandardCodeContent> fields = service.queryFieldById(id);
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("fields", fields);
		map.put("name", name);
		map.put("id", id);
		map.put("demandId", demandId);
		return new ModelAndView("sys/demandAna/siteItemAdd",map);//request("sys/codeManage/fieldAdd").addObject("fields",fields).addObject("sname", Encoding.encoding(name));
	}
	/**
	 * 查询所有数据项
	 */
	@RequestMapping(value = "/allitem", method = RequestMethod.POST)
	@ResponseBody
	public void itemQuery(PrintWriter out) throws Exception {
		List<StandardCode> list = service.queryAllSc();
		List<StandardCodeContent> slist = service.queryAllScc();
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(StandardCode scc : list){
			sb.append("{id:'"+scc.getId()+"',pId:'"+scc.getParentId()+"',name:'"+scc.getName()+"',flag:'1'"+",checked: false},");
		}
		for(StandardCodeContent sc : slist){
			sb.append("{id:'"+sc.getId()+"',pId:'"+sc.getParentId()+"',name:'"+sc.getFieldName()+"',flag:'2'"+",code:'"+sc.getFieldCode()+"',description:'"+sc.getFieldDescription()+"',checked: false},");
		}
		if(list.size()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		out.print(sb.toString());
	}
	
	/**
	 * 查询与一个site相关的数据项
	 */
	@RequestMapping(value = "/siteitem", method = RequestMethod.POST)
	@ResponseBody
	public void itemQuery(String siteId, PrintWriter out) throws Exception {
		List<StandardCode> list = service.querySiteSc(siteId);
		List<StandardCodeContent> slist = service.querySiteScc(siteId);
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(StandardCode scc : list){
			//sb.append("{id:'"+scc.getId()+"',pId:'"+scc.getParentId()+"',name:'"+scc.getName()+"',flag:'1'"+",checked: false},");
		}
		for(StandardCodeContent sc : slist){
			sb.append("{id:'"+sc.getId()+"',pId:'"+sc.getParentId()+"',name:'"+sc.getFieldName()+"',flag:'2'"+",code:'"+sc.getFieldCode()+"',description:'"+sc.getFieldDescription()+"',checked: false},");
		}
		if(list.size()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		out.print(sb.toString());
	}
	/**
	 * 角色修改
	 */
	@RequestMapping(value = "/itemup", method = RequestMethod.POST)
	@ResponseBody
	public void itemUp(HttpServletRequest request, SiteItemsCode sc,PrintWriter out) throws Exception {
		System.out.println("sc----------:"+sc);
		HttpSession session = request.getSession();
		String username = session.getAttribute(Sessions.SESSION_USER).toString();
		Account account = service.selectName(username);
		String publisherId = account.getName();
		SiteItemsCode list = service.querySiteItem(sc);
		int i = 0;
		if(null == list){
			String id = Numbers.uuid();
			sc.setPublisherId(publisherId);
			sc.setId(id);
			sc.setPublishTime(new Date());
			sc.setValidateFlag("1");
			i = service.getIn(sc);
		}else{
			sc.setPublishTime(new Date());
			sc.setPublisherId(publisherId);
			sc.setValidateFlag("1");
			i = service.getUp(sc);
		}
		out.print(i);
	}
	
	@RequestMapping(value = "/itemupNew", method = RequestMethod.POST)
	@ResponseBody
	public void itemUpNew(HttpServletRequest request, SiteItemsCode sc, @RequestParam String singleSite, PrintWriter out) throws Exception {
		System.out.println("sc----------:"+sc);
		HttpSession session = request.getSession();
		String username = session.getAttribute(Sessions.SESSION_USER).toString();
		Account account = service.selectName(username);
		String publisherId = account.getName();
		sc.setSiteId(singleSite);
		SiteItemsCode list = service.querySiteItem(sc);
		int i = 0;
		if(null == list){
			String id = Numbers.uuid();
			sc.setPublisherId(publisherId);
			sc.setId(id);
			sc.setPublishTime(new Date());
			sc.setValidateFlag("1");
			i = service.getIn(sc);
		}else{
			sc.setId(list.getId());
			sc.setPublishTime(new Date());
			sc.setPublisherId(publisherId);
			sc.setValidateFlag("1");
			i = service.getUp(sc);
		}
		out.print(i);
	}
	
	@RequestMapping(value = "/itemq", method = RequestMethod.POST)
	@ResponseBody
	public SiteItemsCode itemQ(@RequestParam String id) throws Exception {
		return service.querySiteItemBySiteId(id);
	}
	
}
