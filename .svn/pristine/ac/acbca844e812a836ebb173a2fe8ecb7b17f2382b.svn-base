package inspur.crawl.sysManage.controller;

import inspur.crawl.common.interf.Sessions;
import inspur.crawl.ruleManage.controller.StringUtils;
import inspur.crawl.sysManage.pojo.Account;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUser extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
     
    public String doGet(HttpServletRequest request, HttpServletResponse response,List<Account> list) throws ServletException, IOException {  
    	HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        
        String sessionuser = "";
        if(session!=null){
        	Object o = session.getAttribute(Sessions.SESSION_USER);
        	if(o!=null){
        		 sessionuser = o.toString();
        	}
        	
        }
       
        String userName=request.getParameter("userName");  
        String password=StringUtils.md5(request.getParameter("password"));  
        String r = "2";
        if("".equals(sessionuser)||sessionuser==null){  
        	if(!"".equals(userName)&&userName!=null&&!"".equals(password)&&password!=null){
		        for(Account user:list)  
		        {  
		            if(user.getUsername().equals(userName)&&user.getPassword().equals(password))  
		            {  
		            	session.setAttribute(Sessions.SESSION_USER, userName);
		            	r = "1";
		                return r;  
		            }
		              
		        }  
        	}else{
        		r = "3";
        		return r;
        	}
        }
		return r;  
          
    }  
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        doGet(request,response);  
    }  
  
}  
