package inspur.crawl.sysManage.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.sysManage.pojo.Account;
import inspur.crawl.sysManage.pojo.Role;
import inspur.crawl.sysManage.service.DataRoleService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/datarole")
public class DataRoleController extends BaseController{
	@Resource
	DataRoleService dataRoleService;
	@InitBinder("datarole")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("datarole.");  
	}
	/**
	 * 角色管理
	 */
	@RequestMapping(value = "/setrole", method = RequestMethod.GET)
	public ModelAndView userManage(HttpServletRequest request, HttpServletResponse response,PrintWriter out) throws Exception {
		return request("sys/role/roleManage");
	}
	/**
	 * 添加 跳转
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public ModelAndView roleAdd(@RequestParam String t,@RequestParam String id) throws Exception {
		if("1".equals(t)){
			return request("sys/role/roleAdd");
		}else if("2".equals(t)){
			return request("sys/role/roleUpdate").addObject("id", id);
		}
		return null;
	}
	/**
	 * 查询所有角色
	 */
	@RequestMapping(value = "/allrole", method = RequestMethod.POST)
	@ResponseBody
	public List<Role> userQuery() throws Exception {
		List<Role> list = dataRoleService.queryAll();
		return list;
	}
	/**
	 * 查询可用角色
	 */
	@RequestMapping(value = "/enablerole", method = RequestMethod.POST)
	@ResponseBody
	public List<Role> userEQuery() throws Exception {
		List<Role> list = dataRoleService.queryEnable();
		return list;
	}
	/**
	 * 角色增加
	 */
	@RequestMapping(value = "/roleadd", method = RequestMethod.POST)
	@ResponseBody
	public int roleAdd(Role role) throws Exception {
		//生成19位UUID
		String id = Numbers.uuid();
		role.setId(id);
		
		int i  = dataRoleService.getAdd(role);
		
		return i;
	}
	/**
	 * id 查询角色
	 */
	@RequestMapping(value = "/roleq", method = RequestMethod.POST)
	@ResponseBody
	public Role roleQ(String id) throws Exception {
		return dataRoleService.getrole(id);
	}
	/**
	 * 角色修改
	 */
	@RequestMapping(value = "/roleup", method = RequestMethod.POST)
	@ResponseBody
	public int  roleUp(Role role) throws Exception {
		
		int i  = dataRoleService.getUp(role);
		
		return i;
	}
	/**
	 * 角色删除
	 */
	@RequestMapping(value = "/roledel", method = RequestMethod.POST)
	@ResponseBody
	public int roleD(String id) throws Exception {
		String[] ids = id.split(";");
		int i = 0;
		for(String d:ids){
			i += dataRoleService.getDel(d);
		}
		return i;
	}
}
