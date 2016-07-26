package inspur.crawl.sysManage.controller;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.sysManage.pojo.Authority;
import inspur.crawl.sysManage.service.DataauthorityService;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dataauthority")
public class DataAuthorityController extends BaseController{
	@Resource
	DataauthorityService dataauthorityService;
	@InitBinder("dataauthority")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("dataauthority.");  
	}
	/**
	 * 权限管理
	 */
	@RequestMapping(value = "/setauthority", method = RequestMethod.GET)
	public ModelAndView setauthority(HttpServletRequest request, HttpServletResponse response,PrintWriter out) throws Exception {
		return request("sys/authority/authorityManage");
	}
	/**
	 * 查询所有权限
	 */
	@RequestMapping(value = "/allau", method = RequestMethod.POST)
	@ResponseBody
	public void auQuery(PrintWriter out) throws Exception {
		List<Authority> list = dataauthorityService.queryAll();
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(Authority au : list){
			sb.append("{id:'"+au.getId()+"',pId:'"+au.getParentid()+"',name:\""+au.getName()+"\",checked: false},");
		}
		if(list.size()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		out.print(sb.toString());
	}
	/**
	 * 查询父类 子类权限
	 */
	@RequestMapping(value = "/queryau", method = RequestMethod.POST)
	@ResponseBody
	public String auParentC() throws Exception {
		List<Authority> listP = dataauthorityService.queryParent();
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for(Authority au : listP){
			i++;
			String parentId = au.getId();
			sb.append("<tr class=\"treegrid-"+au.getId()+" active\"><td>");
			sb.append(i+"</td><td>");
			sb.append(au.getName()+"</td><td>");
			String url = au.getUrl();
			sb.append(url!=null?url:"");
			sb.append("</td><td>");
			sb.append(au.getPosition()+"</td><td >");
			sb.append("<a href=\"\" data-toggle=\"modal\" id=\"add_"+au.getId()+"\" data-target=\"\" onclick=\"OpenAddModel('"+au.getId()+"')\"  >添加下级</a>  <a href=\"\" data-toggle=\"modal\" id=\"edit_"+au.getId()+"\" data-target=\"\" onclick=\"OpenEditModel('"+au.getId()+"','"+au.getName()+"','"+url+"','"+au.getPosition()+"','"+au.getParentid()+"')\">编辑</a>  <a href=\"\" onclick=\"del('"+au.getId()+"')\">删除</a></td></tr>");
			listC(i+"",sb,parentId);
		}
		return sb.toString();
	}
	public void listC(String ii,StringBuffer sb,String parentId){
		List<Authority> list = dataauthorityService.queryChild(parentId);
		int i = 0;
		for(Authority au : list){
			i++;
			sb.append("<tr class=\"treegrid-"+au.getId()+" treegrid-parent-"+au.getParentid()+" active\"><td>");
			sb.append(ii+"."+i+"</td><td>");
			sb.append(au.getName()+"</td><td>");
			String url = au.getUrl();
			sb.append(url!=null?url:"");
			sb.append("</td><td>");
			sb.append(au.getPosition()+"</td><td>");
			sb.append("<a href=\"\" data-toggle=\"modal\" id=\"add_"+au.getId()+"\" data-target=\"\" onclick=\"OpenAddModel('"+au.getId()+"')\"  >添加下级</a>  <a href=\"\" data-toggle=\"modal\" id=\"edit_"+au.getId()+"\" data-target=\"\" onclick=\"OpenEditModel('"+au.getId()+"','"+au.getName()+"','"+url+"','"+au.getPosition()+"','"+au.getParentid()+"')\">编辑</a>  <a href=\"\" onclick=\"del('"+au.getId()+"')\">删除</a></td></tr>");
			
			listC(ii+"."+i,sb,au.getId());
		}
	}
	/**
	 * 查询父类 子类权限
	 */
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "/addnext", method = RequestMethod.POST)
	@ResponseBody
	public int auAddNext(Authority au ) throws Exception {
		int i = 0;
		try {  
			//生成19位UUID
			  String id = Numbers.uuid();
			  au.setId(id);
			  positionUp(au.getPosition()+"");
			  i = dataauthorityService.getAddNext(au);
	     } catch (Exception e) {  
	          e.printStackTrace();     
	          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//就是这一句了，加上之后，如果doDbStuff2()抛了异常,                                                                                       //doDbStuff1()是会回滚的  
	     }  
		
		return i;
	}
	/**
	 * 修改 position 位置
	 */
	public int positionUp(String position){
		return dataauthorityService.getPositionUp(position);
	}
	/**
	 * 修改
	 */
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public int update(@RequestParam String k,Authority au ){
		int i = 0;
		try {  
			if("1".equals(k)){
				 positionUp(au.getPosition()+"");
			}
			i = dataauthorityService.getUpdate(au);
		 } catch (Exception e) {  
	         e.printStackTrace();     
	         TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//就是这一句了，加上之后，如果doDbStuff2()抛了异常,                                                                                       //doDbStuff1()是会回滚的  
	    }  
		return i;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public int del(@RequestParam String id){
		int i = 0;
		List<Authority> list = queryNext(id);
		if(list.size()>0){
			return -1;
		}else{
			i =dataauthorityService.getDel(id);
		}
		return i;
	}
	/**
	 * 查询下层
	 */
	public List<Authority> queryNext(String id){
		List<Authority> list =dataauthorityService.queryChild(id);
		return list;
	}
}
