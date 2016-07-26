package inspur.crawl.codeManage.controller;

import inspur.crawl.codeManage.pojo.SiteCode;
import inspur.crawl.codeManage.service.SiteCodeService;
import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.sysManage.pojo.Account;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
@RequestMapping("/datacode")
public class SiteCodeController extends BaseController{
	@Resource
	SiteCodeService service;
	@InitBinder("datacode")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("datacode.");  
	}
	/**
	 * 代码维护
	 */
	@RequestMapping(value = "/setsitecode", method = RequestMethod.GET)
	public ModelAndView setSite(SiteCode site) throws Exception {
		return request("sys/codeManage/siteManage");
	}
	/**
	 * 查询所有站点类型
	 */
	@RequestMapping(value = "/allsite", method = RequestMethod.POST)
	@ResponseBody
	private void siteQuery(PrintWriter out,int pages) throws Exception {
		int startNum = (pages-1)*10;
		int endNum = pages*10;
		List<SiteCode> list = service.queryParent(startNum,endNum);
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for(SiteCode sc : list){
			i++;
			String formatedDate = DateFormatToYYYY.dateFormate(sc.getPublishTime().toString());
			String parentId = sc.getId();
			sb.append("<tr class=\"treegrid-"+sc.getId()+" active\"><td >");
			sb.append(i+"</td><td>");
			sb.append(sc.getName()+"</td><td>");
			sb.append(sc.getPublisherId()+"</td><td >");
			sb.append(formatedDate+"</td><td >");
			sb.append("<a href=\"\" data-toggle=\"modal\" id=\"add_"+sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenAddModel('"+sc.getId()+"','"+sc.getLevelCode()+"')\"  >添加下级</a>  <a href=\"\" data-toggle=\"modal\" id=\"edit_"+sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenEditModel('"+sc.getId()+"','"+sc.getName()+"','"+sc.getLevelCode()+"','"+sc.getPublisherId()+"','"+sc.getParentId()+"')\">编辑</a>  <a href=\"\" onclick=\"del('"+sc.getId()+"')\">删除</a></td></tr>");
			listC(i+"",sb,parentId);
		}
		int zs = service.countSiteCode();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sb", sb.toString());
		map.put("zys", zs);
		map.put("pages", pages);
		JSONArray json = JSONArray.fromObject(map);
		out.print(json);
	}
	
	public void listC(String ii,StringBuffer sb,String parentId) throws ParseException{
		List<SiteCode> list = service.queryChild(parentId);
		int i = 0;
		for(SiteCode sc : list){
			i++;
			String formatedDate = DateFormatToYYYY.dateFormate(sc.getPublishTime().toString());
			sb.append("<tr class=\"treegrid-"+sc.getId()+" treegrid-parent-"+sc.getParentId()+" active\"><td>");
			sb.append(ii+"."+i+"</td><td>");
			sb.append(sc.getName()+"</td><td>");
			sb.append(sc.getPublisherId()+"</td><td >");
			sb.append(formatedDate+"</td><td >");
			sb.append("<a href=\"\" data-toggle=\"modal\" id=\"add_"+sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenAddModel('"+sc.getId()+"','"+sc.getLevelCode()+"')\"  >添加下级</a>  <a href=\"\" data-toggle=\"modal\" id=\"edit_"+sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenEditModel('"+sc.getId()+"','"+sc.getName()+"','"+sc.getLevelCode()+"','"+sc.getPublisherId()+"','"+sc.getParentId()+"')\">编辑</a>  <a href=\"\" onclick=\"del('"+sc.getId()+"')\">删除</a></td></tr>");
			
			listC(ii+"."+i,sb,sc.getId());
		}
	}
	
	/**
	 * 添加下级菜单
	 */
	@RequestMapping(value = "/addnextsc", method = RequestMethod.POST)
	@ResponseBody
	public void addNext(HttpServletRequest request, @RequestParam String levelCode,@RequestParam String parentId,SiteCode sc,PrintWriter out) throws Exception{
		int i = 0;
		try {  
			//生成19位UUID
			  String id = Numbers.uuid();
			  sc.setId(id);
			  HttpSession session = request.getSession();
			  String username = session.getAttribute(Sessions.SESSION_USER).toString();
			  Account account = service.selectName(username);
			  String name = account.getName();
			  sc.setPublisherId(name);
			  sc.setParentId(parentId);
			  List<String> list = service.getName(parentId);
			  for(String s:list){
				  if(s.equals(sc.getName())){
					  i = 2;
				  }
			  }
			  if(i==0){
				  int level = Integer.parseInt(levelCode);
				  sc.setLevelCode(level+1+"");
				  sc.setPublishTime(new Date());
				  sc.setValidateFlag("1");
				  i = service.getAddNext(sc);
			  }
	     } catch (Exception e) {  
	          e.printStackTrace();     
	     } 
		out.print(i);
	}
	
	/**
	 * 修改站点类型
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void demandUpdate(HttpServletRequest request, SiteCode sc,PrintWriter out) throws Exception {
		int i = 0;
		sc.setPublishTime(new Date());
		sc.setValidateFlag("1");
		HttpSession session = request.getSession();
	    String username = session.getAttribute(Sessions.SESSION_USER).toString();
	    Account account = service.selectName(username);
	    String name = account.getName();
	    sc.setPublisherId(name);
	    List<String> list = service.getName(sc.getParentId());
		  for(String s:list){
			  if(s.equals(sc.getName())){
				  i = 2;
			  }
		  }
		if(i==0){
			i = service.update(sc);
		}
		out.print(i);
	}
	
	/**
	 * 删除菜单
	 */
	@RequestMapping(value = "/delsite", method = RequestMethod.POST)
	@ResponseBody
	public int delSite(@RequestParam String id){
		int i = 0;
		List<SiteCode> list = queryNext(id);
		if(list.size()>0){
			return -1;
		}else{
			i = service.delSite(id);
		}
		return i;
	}
	private List<SiteCode> queryNext(String id) {
		List<SiteCode> list = service.queryChild(id);
		return list;
	}
}
