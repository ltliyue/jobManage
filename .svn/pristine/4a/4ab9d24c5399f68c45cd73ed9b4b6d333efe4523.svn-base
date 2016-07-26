package inspur.crawl.common.controller;

import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.DateEditor;
import inspur.crawl.sysManage.pojo.Authority;
import inspur.crawl.sysManage.service.DataauthorityService;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BaseController {
	protected static final Logger LOG=Logger.getLogger(BaseController.class);	
	@Resource
	DataauthorityService dataauthorityService;
	@InitBinder  
	public final void initBinder(WebDataBinder binder) throws Exception {  
	    //注册自定义的属性编辑器  
	    //表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换 
		binder.registerCustomEditor(Date.class, new DateEditor());   
	}
	
	/**
	 * 首页跳转
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView base(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("redirect:/home/base");
	}
	
	/**
	 * 
	 * @param path
	 * @return
	 */
	public ModelAndView request(String path) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
			      .getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		String titles = (String) session.getAttribute(Sessions.SESSION_TITLE);
		String title = "";
		 String path2 =request.getServletPath();
		if(titles==null||"".equals(titles)){
			 List<Authority>  list = dataauthorityService.queryAll();
			 for(Authority au:list){
				 titles += au.getName()+"~"+au.getUrl()+"|"; 
			 }
			 session.setAttribute(Sessions.SESSION_TITLE, titles);
			 String[] ss = titles.split("\\|");
				for(String s:ss){
					String[] ns = s.split("~");
					if(ns.length==2){
						if(path2.equals(ns[1])){
							title=ns[0];
							return new ModelAndView(path).addObject("webRoot",  request.getContextPath()).addObject("title",title);
						}
					}
				}
		}else{
			String[] ss = titles.split("\\|");
			for(String s:ss){
				String[] ns = s.split("~");
				if(ns.length==2){
					if(path2.equals(ns[1])){
						title=ns[0];
						return new ModelAndView(path).addObject("webRoot",  request.getContextPath()).addObject("title",title);
					}
				}
			}
		}
		
		return new ModelAndView(path).addObject("webRoot", request.getContextPath() ).addObject("title",title);
    }
	
	/**
	 * 
	 * @param path
	 * @param map
	 * @return
	 */
	public ModelAndView request(String path, Map map) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
			      .getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		String titles = (String) session.getAttribute(Sessions.SESSION_TITLE);
		String title = "";
		 String path2 =request.getServletPath();
		if(titles==null||"".equals(titles)){
			 List<Authority>  list = dataauthorityService.queryAll();
			 for(Authority au:list){
				 titles += au.getName()+"~"+au.getUrl()+"|"; 
			 }
			 session.setAttribute(Sessions.SESSION_TITLE, titles);
			 String[] ss = titles.split("\\|");
				for(String s:ss){
					String[] ns = s.split("~");
					if(ns.length==2){
						if(path2.equals(ns[1])){
							title=ns[0];
							return new ModelAndView(path, map).addObject("webRoot",  request.getContextPath()).addObject("title",title);
						}
					}
				}
		}else{
			String[] ss = titles.split("\\|");
			for(String s:ss){
				String[] ns = s.split("~");
				if(ns.length==2){
					if(path2.equals(ns[1])){
						title=ns[0];
						return new ModelAndView(path, map).addObject("webRoot",  request.getContextPath()).addObject("title",title);
					}
				}
			}
		}
		return new ModelAndView(path, map).addObject("webRoot",  request.getContextPath()).addObject("title",title);
    }
	/**
	 * 重定向
	 * @param redirectView
	 * @return
	 */
	public ModelAndView request(RedirectView redirectView) {
		return new ModelAndView(redirectView);
	}
	
	/**
	 * 异常跳转
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public ModelAndView exp(Exception ex) {
		ex.printStackTrace();
		if(ex instanceof FileNotFoundException) {
			return new ModelAndView("error/fileError").addObject("ex", ex);
		}else if(ex instanceof BindException) {
			return new ModelAndView("error/hiddenAlertError").addObject("ex", new Exception(((BindException) ex).getAllErrors().get(0).getDefaultMessage()));
		}else {
			return new ModelAndView("error/error").addObject("ex", ex);
		}
	}
}
