package inspur.crawl.sysManage.controller;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.datasource.DataSourceInstances;
import inspur.crawl.common.datasource.DataSourceSwitch;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.Numbers;
import inspur.crawl.ruleManage.controller.StringUtils;
import inspur.crawl.sysManage.pojo.Account;
import inspur.crawl.sysManage.pojo.Authority;
import inspur.crawl.sysManage.pojo.Role;
import inspur.crawl.sysManage.service.DataUserService;
import inspur.crawl.sysManage.service.DataauthorityService;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Result;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/datauser")
public class DataUserController extends BaseController{
	@Resource
	DataUserService dataUserService;
	@Resource
	DataauthorityService dataauthorityService;
	@InitBinder("datauser")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("datauser.");  
	}
	/**
	 * 登录
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public void login(HttpServletRequest request, HttpServletResponse response,PrintWriter out) throws Exception {
		
		LoginUser loginUser = new LoginUser();
		String islogin = loginUser.doGet(request, response,dataUserService.getListData() );
		out.print(islogin);
	}
	/**
	 * 登出
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public ModelAndView loginout(HttpServletRequest request, HttpServletResponse response,PrintWriter out) throws Exception {
		LoginOut loginout = new LoginOut();
		String islogin = loginout.doGet(request, response,null);
		return request("sys/login");
	}
	/**
	 * 用户管理
	 */
	@RequestMapping(value = "/setuser", method = RequestMethod.GET)
	public ModelAndView userManage(Account record) throws Exception {
		List<Account> list = userQuery(record);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("accounts", list);
		map.put("page", record);
		return request("sys/user/userManage",map);
	}
	/**
	 * 添加_跳转
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user(@RequestParam String t,@RequestParam String id) throws Exception {
		
		if("1".equals(t)){
			return request("sys/user/userAdd");
		}else if("2".equals(t)){
			return request("sys/user/userUpdate").addObject("id",id);
		}
		return null;
	}
	/**
	 * 查询
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/alluser", method = RequestMethod.POST)
//	@ResponseBody
	public List<Account> userQuery(Account record) throws Exception {
		
		List<Account> page = dataUserService.listPageUser(record);
		
		return page;
	}
	/**
	 * 用户 增加
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/useradd", method = RequestMethod.POST)
	@ResponseBody
	public String userAdd(Account record) throws Exception {
		//生成19位UUID
		String id = Numbers.uuid();
		record.setId(id);
		record.setRegistertime(new Date());
		record.setPassword(StringUtils.md5(record.getPassword()));
		int i  = dataUserService.getAdd(record);
		
		if(i==1){
			return id;
		}else{
			return "";
		}
	}
	/**
	 * 用户 删除
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userdel", method = RequestMethod.POST)
	@ResponseBody
	public int userDel(String id) throws Exception {
		String[] ids = id.split(";");
		int i = 0;
		for(String d:ids){
			i += dataUserService.getDel(d);
		}
		return i;
	}
	/**
	 * 用户 绑定 角色
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userrole", method = RequestMethod.GET)
	public ModelAndView userrole(String id,String name,String email) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id );
		map.put("name",name );
		map.put("email",email );
		return request("sys/user/userAddrole").addObject("map",map);
	}
	/**
	 * 资料修改
	 */
	@RequestMapping(value = "/setdata", method = RequestMethod.GET)
	public ModelAndView setdata(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String username = session.getAttribute(Sessions.SESSION_USER).toString();
		return request("sys/user/setdata").addObject("username",username);
	}
	/**
	 * 用户 绑定 角色1
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userupdate", method = RequestMethod.POST)
	@ResponseBody
	public int userupdate(Account record) throws Exception {
		
		int i = dataUserService.getUpdate(record.getId(),record.getRoleid());
		return i;
	}
	/**
	 * 查询用户
	 * id
	 */
	@RequestMapping(value = "/userq", method = RequestMethod.POST)
	@ResponseBody
	public Account userq(String id){
		return dataUserService.getAccount(id);
	}
	
	/**
	 * 用户 修改
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/useru", method = RequestMethod.POST)
	@ResponseBody
	public int useru(Account record,String i) throws Exception {
		if("1".equals(i)){
			String n = querypw(record.getUsername(),StringUtils.md5(record.getYpassword()));
			if(!"1".equals(n)){
				return 2;
			}
		}else{
			record.setPassword(null);
		}
		record.setPassword(StringUtils.md5(record.getPassword()));
		int ii = dataUserService.getUpdate1(record);
		return ii;
	}
	/**
	 * 查看密码
	 */
	public String querypw(String userName,String password) throws Exception {
		List<Account> list = dataUserService.getListData();
		if(!"".equals(userName)&&userName!=null&&!"".equals(password)&&password!=null){
	        for(Account user:list)  
	        {  
	            if(user.getUsername().equals(userName)&&user.getPassword().equals(password))  
	            {  
	                return "1";  
	            }
	              
	        }  
    	}
		return "2";
	}
}
