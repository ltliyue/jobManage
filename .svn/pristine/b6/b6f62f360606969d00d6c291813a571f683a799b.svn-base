package inspur.crawl.sysManage.controller;


import inspur.crawl.common.interf.Sessions;
import inspur.crawl.sysManage.pojo.Account;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
  
/** 
 * Servlet implementation class LogOut 
 */  
public class LoginOut extends HttpServlet {  
  
	 public String doGet(HttpServletRequest request, HttpServletResponse response,List<Account> list) throws ServletException, IOException {  
		 HttpSession session=request.getSession(false);  
	        if(session==null)  
	        {  
	            return "3";  
	        }  
	        session.invalidate();
	        return "1";
	    }    
  
      
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        doGet(request,response);  
    }  
  
}  
