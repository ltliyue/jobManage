package inspur.crawl.codeManage.controller;

import inspur.crawl.codeManage.pojo.OralceKeyWords;
import inspur.crawl.codeManage.pojo.StandardCode;
import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.codeManage.service.StandardCodeService;
import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.Numbers;
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
@RequestMapping("/datastandard")
public class StandardCodeController extends BaseController{
	@Resource
	StandardCodeService service;
	@InitBinder("datastandard")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("datastandard.");  
	}
	
	/**
	 * 查询所有标准化
	 */
	@RequestMapping(value = "/setstandardcode", method = RequestMethod.GET)
	public ModelAndView setStandard(StandardCode standard) throws Exception {
		return request("sys/codeManage/standardManage");
	}
	
	/**
	 * 查询所有站点类型
	 */
	@RequestMapping(value = "/allstandard", method = RequestMethod.POST)
	@ResponseBody
	private void standardQuery(PrintWriter out, int pages) throws Exception {
		int startNum = (pages-1)*10;
		int endNum = pages*10;
		List<StandardCode> list = service.queryParent(startNum,endNum);
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for(StandardCode sc : list){
			i++;
			String formatedDate = DateFormatToYYYY.dateFormate(sc.getPublishTime().toString());
			String parentId = sc.getId();
			sb.append("<tr class=\"treegrid-"+sc.getId()+" active\"><td>");
			sb.append(i+"</td><td>");
			sb.append(sc.getName()+"</td><td>");
			sb.append(sc.getPublisherId()+"</td><td >");
			sb.append(formatedDate+"</td><td >");
			sb.append("<a href=\"\" data-toggle=\"modal\" id=\"addField_"+sc.getId()+"\" data-target=\"#addField\" onclick=\"OpenAddField('"+sc.getId()+"','"+sc.getName()+"')\"  >详细字段</a>  ");
			sb.append("<a href=\"\" data-toggle=\"modal\" id=\"add_"+sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenAddModel('"+sc.getId()+"','"+sc.getName()+"','"+sc.getLevelCode()+"')\"  >添加下级</a>  <a href=\"\" data-toggle=\"modal\" id=\"edit_"+sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenEditModel('"+sc.getId()+"','"+sc.getName()+"','"+sc.getLevelCode()+"','"+sc.getPublisherId()+"','"+sc.getParentId()+"')\">编辑</a>  <a href=\"\" onclick=\"del('"+sc.getId()+"')\">删除</a></td></tr>");
			listC(i+"",sb,parentId);
		}
		int zs = service.countSandardCode();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sb", sb.toString());
		map.put("zys", zs);
		map.put("pages", pages);
		JSONArray json = JSONArray.fromObject(map);
		out.print(json);
	}
	
	public void listC(String ii,StringBuffer sb,String parentId) throws ParseException{
		List<StandardCode> list = service.queryChild(parentId);
		int i = 0;
		for(StandardCode sc : list){
			i++;
			String formatedDate = DateFormatToYYYY.dateFormate(sc.getPublishTime().toString());
			sb.append("<tr class=\"treegrid-"+sc.getId()+" treegrid-parent-"+sc.getParentId()+" active\"><td>");
			sb.append(ii+"."+i+"</td><td>");
			sb.append(sc.getName()+"</td><td>");
			sb.append(sc.getPublisherId()+"</td><td >");
			sb.append(formatedDate+"</td><td >");
			sb.append("<a href=\"\" data-toggle=\"modal\" id=\"addField_"+sc.getId()+"\" data-target=\"#addField\" onclick=\"OpenAddField('"+sc.getId()+"','"+sc.getName()+"')\"  >详细字段</a>  ");
			sb.append("<a href=\"\" data-toggle=\"modal\" id=\"add_"+sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenAddModel('"+sc.getId()+"','"+sc.getName()+"','"+sc.getLevelCode()+"')\"  >添加下级</a>  <a href=\"\" data-toggle=\"modal\" id=\"edit_"+sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenEditModel('"+sc.getId()+"','"+sc.getName()+"','"+sc.getLevelCode()+"','"+sc.getPublisherId()+"','"+sc.getParentId()+"')\">编辑</a>  <a href=\"\" onclick=\"del('"+sc.getId()+"')\">删除</a></td></tr>");
			
			listC(ii+"."+i,sb,sc.getId());
		}
	}
	
	/**
	 * 添加下级菜单
	 */
	@RequestMapping(value = "/addnextsc", method = RequestMethod.POST)
	@ResponseBody
	public void addNext(HttpServletRequest request, @RequestParam String levelCode,@RequestParam String parentId,StandardCode sc,PrintWriter out) throws Exception{
		int i = 0;
		try {  
			//生成19位UUID
			  String id = Numbers.uuid();
			  sc.setId(id);
			  sc.setParentId(parentId);
			  HttpSession session = request.getSession();
			  String username = session.getAttribute(Sessions.SESSION_USER).toString();
			  Account account = service.selectName(username);
			  String name = account.getName();
			  sc.setPublisherId(name);
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
	 * 修改标准化
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void demandUpdate(HttpServletRequest request,StandardCode sc,PrintWriter out) throws Exception {
		int i = 0;
		sc.setPublishTime(new Date());
//		sc.setId(id);
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
	
	@RequestMapping(value = "/addfield", method = RequestMethod.GET)
	public ModelAndView addField(@RequestParam String id,@RequestParam String name) throws Exception {
		List<StandardCodeContent> fields = service.queryFieldById(id);
		List<OralceKeyWords> list = service.queryKeyWords();
		String keyWords = "";
		for(OralceKeyWords keyword : list){
			keyWords = keyWords+","+keyword.getKeyword().toLowerCase();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("fields", fields);
		map.put("sname", name);
		map.put("parentId", id);
//		System.out.println(keyWords);
		map.put("oraKeyWords", keyWords);
		return new ModelAndView("sys/codeManage/fieldAdd",map);//request("sys/codeManage/fieldAdd").addObject("fields",fields).addObject("sname", Encoding.encoding(name));
	}
	
	/**
	 * 添加需求状态
	 */
	@RequestMapping(value = "/addfieldsub", method = RequestMethod.POST)
	@ResponseBody
	public void addFieldSub(HttpServletRequest request, StandardCodeContent scc, PrintWriter out) throws Exception{
		int i = 0;
		try { 
			HttpSession session = request.getSession();
			String username = session.getAttribute(Sessions.SESSION_USER).toString();
			Account account = service.selectName(username);
			String name = account.getName();
			String fieldCodeN = scc.getFieldCode();
			String[] fieldCodeC = fieldCodeN.split(",");
			String fieldNameN = scc.getFieldName();
			String[] fieldNameC = fieldNameN.split(",");
			String fieldDescriptionN = scc.getFieldDescription();
			String[] fieldDescriptionC = fieldDescriptionN.split(","); 
			for(int j = 0;i<fieldCodeC.length;j++){
				String id = Numbers.uuid();
				scc.setId(id);
				String fieldCode = fieldCodeC[j];
				scc.setFieldCode(fieldCode);
				String fieldName = fieldNameC[j];
				scc.setFieldName(fieldName);
				String fieldDescription = fieldDescriptionC[j];
				scc.setFieldDescription(fieldDescription);
				String publisherId = name;
				scc.setPublisherId(publisherId);
				scc.setPublishTime(new Date());
				scc.setValidateFlag("1");
				i = service.insertField(scc);
			}
	     } catch (Exception e) {  
	          e.printStackTrace();     
	     } 
		out.print(i);
	}
	
	/**
	 * 删除菜单
	 */
	@RequestMapping(value = "/delstandard", method = RequestMethod.POST)
	@ResponseBody
	public int delSite(@RequestParam String id){
		int i = 0;
		List<StandardCode> list = queryNext(id);
		if(list.size()>0){
			return -1;
		}else{
			i = service.delStandard(id);
		}
		return i;
	}
	private List<StandardCode> queryNext(String id) {
		List<StandardCode> list = service.queryChild(id);
		return list;
	}
	
	/**
	 * 删除菜单
	 */
	@RequestMapping(value = "/delfield", method = RequestMethod.POST)
	@ResponseBody
	public int delField(@RequestParam String id){
		int i = service.delFieldById(id);
		return i;
	}
	/**
	 * 列出所有字段信息
	 */
	@RequestMapping(value = "/editfield", method = RequestMethod.POST)
	@ResponseBody
	public int editField(HttpServletRequest request, @RequestParam String id,@RequestParam String fieldDescription,@RequestParam String parentId,@RequestParam String fieldCode,@RequestParam String fieldName){
		HttpSession session = request.getSession();
		String username = session.getAttribute(Sessions.SESSION_USER).toString();
		Account account = service.selectName(username);
		String publisherId = account.getName();
		StandardCodeContent scc = new StandardCodeContent();
		scc.setId(id);
		scc.setFieldDescription(fieldDescription);
		scc.setFieldCode(fieldCode);
		scc.setFieldName(fieldName);
		scc.setParentId(parentId);
		scc.setPublisherId(publisherId);
		scc.setPublishTime(new Date());
		scc.setValidateFlag("1");
		int i = service.updateField(scc);
		return i;
	}
//	@RequestMapping(value = "/allfield", method = RequestMethod.POST)
//	@ResponseBody
//	private List<StandardCodeContent> standardContentQuery(@RequestParam String id) throws Exception {
//		List<StandardCodeContent> fields = service.queryFieldById(id);
//		return fields;
//	}
	
}
