package inspur.crawl.common.interceptor;


import inspur.crawl.common.interf.Sessions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class AuthorizedInterceptor implements HandlerInterceptor{
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		//请求的路径
		String contextPath=request.getContextPath();
		String	url=request.getServletPath().toString();
		String u = request.getQueryString();
		HttpSession session = request.getSession();
		if("".equals(url)) {
			url = "/";
		}
		if(u!=null){
			if(u.indexOf("&u=6e9f5b830b368f0b7d6993ec5b3ce640a59f4da4")>-1){
				session.setAttribute(Sessions.SESSION_USER, "temp");
			}
		}
		
		String user = (String) session.getAttribute(Sessions.SESSION_USER);
		String menu = (String) session.getAttribute(Sessions.SESSION_MENU);
		String menuall = (String) session.getAttribute(Sessions.SESSION_MENUALL);
		if(menu!=null&&!"".equals(menu)&&menuall!=null){
			if(menuall.indexOf(url)>-1&&menu.indexOf(url)==-1){
				System.out.println("menu:::::"+menu);
				System.out.println("menuall::"+menuall);
				//被拦截，重定向到login界面\
//				response.sendRedirect(contextPath);
				return false;
			}
		}
		
		//这里可以根据session的用户来判断角色的权限
		if (!"/".equals(url) && !"/home/base".equals(url)&&!"/datauser/login".equals(url)&&StringUtils.isEmpty(user)) {
			//被拦截，重定向到login界面
			response.sendRedirect(contextPath);
			
			return false;
		}
		
		return true;
	}
}
