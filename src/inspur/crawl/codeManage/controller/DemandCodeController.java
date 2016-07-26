package inspur.crawl.codeManage.controller;

import inspur.crawl.codeManage.pojo.DemandCode;
import inspur.crawl.codeManage.pojo.SiteCode;
import inspur.crawl.codeManage.service.DemandCodeService;
import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.sysManage.pojo.Account;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/datademand")
public class DemandCodeController extends BaseController{
	@Resource
	DemandCodeService service;
	@InitBinder("datademand")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("datademand.");  
	}
	/**
	 * 需求状态
	 */
	@RequestMapping(value = "/setdemandcode", method = RequestMethod.GET)
	public ModelAndView setDemand(DemandCode demand) throws Exception {
		return request("sys/codeManage/demandManage");
	}
	/**
	 * 列出所有需求
	 */
	@RequestMapping(value = "/alldemand", method = RequestMethod.POST)
	@ResponseBody
	private void demandQuery(PrintWriter out,int pages) throws Exception {
		int startNum = (pages-1)*20;
		int endNum = pages*20;
		List<DemandCode> list = service.queryAll(startNum,endNum);
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for(DemandCode sc : list){
			i++;
			String formatedDate = DateFormatToYYYY.dateFormate(sc.getPublishTime().toString());
			sb.append("<tr align='left'> <td><input type='checkbox' name='chk_list' value=\"");
			sb.append(sc.getId()+"\"/></td><td>");
			sb.append(i+"</td><td id='statusName"+i+"'>");
			sb.append(sc.getStatusName()+"</td><td id='statusCode"+i+"'>");
			sb.append(sc.getStatusCode()+"</td><td id='publisherId"+i+"'>");
			sb.append(sc.getPublisherId()+"</td><td id='publishTime"+i+"'>");
			sb.append(formatedDate+"</td><td >");
			sb.append("<a type=\"button\" id=\"edit_"+sc.getId()+"\"  style=\"cursor:pointer\"	 data-toggle=\"modal\" data-target=\"#modifyDemand\" onclick=\"editDemand('"+sc.getId()+"','"+sc.getStatusName()+"','"+sc.getStatusCode()+"','"+sc.getPublisherId()+"')\" >编辑</a> <a type=\"button\" style=\"cursor:pointer\" onclick=\"deldemand('"+sc.getId()+"')\">删除</a></td></tr>");
		}
		int zs = service.countDemandCode();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sb", sb.toString());
		map.put("zys", zs);
		map.put("pages", pages);
		JSONArray json = JSONArray.fromObject(map);
		out.print(json);
	}
	
	/**
	 * 添加需求状态
	 */
	@RequestMapping(value = "/adddemand", method = RequestMethod.POST)
	@ResponseBody
	public void addNextDemand(HttpServletRequest request, DemandCode dc, PrintWriter out) throws Exception{
		int i = 0;
		try {  
			//生成19位UUID
			  String id = Numbers.uuid();
			  dc.setId(id);
			  HttpSession session = request.getSession();
			  String username = session.getAttribute(Sessions.SESSION_USER).toString();
			  Account account = service.selectName(username);
			  String name = account.getName();
			  dc.setPublisherId(name);
			  if(i==0){
				  dc.setPublishTime(new Date());
				  dc.setValidateFlag("1");
				  i = service.getAddNext(dc);
			  }
	     } catch (Exception e) {  
	          e.printStackTrace();     
	     } 
		out.print(i);
	}
	/**
	 * 修改需求状态
	 */
	@RequestMapping(value = "/demandupdate", method = RequestMethod.POST)
	@ResponseBody
	public void demandUpdate(HttpServletRequest request, @RequestParam String id,DemandCode dc,PrintWriter out) throws Exception {
		dc.setPublishTime(new Date());
		dc.setId(id);
		dc.setValidateFlag("1");
		HttpSession session = request.getSession();
		String username = session.getAttribute(Sessions.SESSION_USER).toString();
		Account account = service.selectName(username);
		String name = account.getName();
		dc.setPublisherId(name);
		int	i  = service.update(dc);
		if(i==1){
			out.print(1);
		}else{
			out.print(0);
		}
	}
	
	/**
	 * 删除菜单
	 */
	@RequestMapping(value = "/deldemand", method = RequestMethod.POST)
	@ResponseBody
	public int delDemand(@RequestParam String id){
		int	i = service.delDemand(id);
		return i;
	}
}
	
