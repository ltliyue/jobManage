package inspur.crawl.sysManage.controller;


import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.HttpClientSingle;
import inspur.crawl.common.tools.Tqsz;
import inspur.crawl.sysManage.pojo.Account;
import inspur.crawl.sysManage.pojo.Authority;
import inspur.crawl.sysManage.pojo.Role;
import inspur.crawl.sysManage.service.DataUserService;
import inspur.crawl.sysManage.service.DataauthorityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inspur.kafka.crawl.model.CrawlRequest;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
	@Resource
	DataUserService dataUserService;
	@Resource
	DataauthorityService dataauthorityService;
	int ir = 0;
	/**
	 * 首页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/base", method = RequestMethod.GET)
	public ModelAndView base(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute(Sessions.SESSION_USER);
		if(StringUtils.isEmpty(user)){
			return request("sys/login");
		}else{
			session.removeAttribute(Sessions.SESSION_CAI);
			return request("sys/dataTypeBase");
		}
		
	}
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	@ResponseBody
	public String queryCd(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute(Sessions.SESSION_USER);
		String menu = session.getAttribute(Sessions.SESSION_MENU)+"";
		String ss = "";
		if(menu==null||"null".equals(menu)||"".equals(menu)){
			Role r = usernameq(user).getRole();
			String aIds = "";
			if(r!=null){
				aIds = r.getAuthorityid();
			}
			ss = auParentC(aIds,request);
			session.setAttribute(Sessions.SESSION_MENU, ss);
			List<Authority> list = dataauthorityService.queryAll();
			String all = "";
			for(Authority a : list){
				all=all+"_"+a.getUrl();
			}
			session.setAttribute(Sessions.SESSION_MENUALL, all);
		}else{
			return menu;
		}
		
		return ss;
	}
	/**
	 * 查询父类 子类权限
	 */
	public String auParentC(String ids,HttpServletRequest request) throws Exception {
		List<Authority> listP = dataauthorityService.queryParent();
		StringBuffer sb_1 = new StringBuffer();
		for(Authority au : listP){
			String parentId = au.getId();
			if(ids.indexOf(parentId+";")>-1){
				StringBuffer sb_2 = new StringBuffer();
				sb_1.append("<li class=\"dropdown\">");
				String url = au.getUrl();
				if(url==null||"".equals(url)||"null".equals(url)){
					url="#";
				}else{
					url = request.getContextPath()+url;
				}
				this.ir = 1;
				listC(1,ids,sb_2,parentId);
				int num = this.ir;
				if((num)==1){
					sb_1.append("<a href=\""+url+"\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+au.getName()+"</a>");
					sb_1.append("<dl class=\"nav_erji\" style=\"display:none\"><dt>"+au.getName()+"：</dt></dl></li>");
				}else if((num)==2){
					sb_1.append("<a href=\""+url+"\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+au.getName()+"</a>");
					sb_1.append("<dl class=\"nav_erji\" style=\"display:none\"><dt>"+au.getName()+"：</dt>");
					String[] ss2 = sb_2.toString().split("&2");
					
					for(String s2:ss2){
						String name2 = Tqsz.zhengze1("num2_name:(.*)(;num2_url)", s2);
						String url2 = Tqsz.zhengze1("num2_url:(.*)", s2);
						if(url2==null||"".equals(url2)||"null".equals(url2)){
							url2="#";
						}else{
							url2 = request.getContextPath()+url2;
						}
						if(!"".equals(name2)){
							sb_1.append("<dd ><a href=\""+url2+"\">"+name2+"</a></dd>");
						}
					}
					sb_1.append("</dl>");
				}else if((num)==3){
					sb_1.append("<a href=\""+url+"\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">"+au.getName()+"<b class=\"caret\"></b></a><ul class=\"dropdown-menu\">");
					String[] ss2 = sb_2.toString().split("&2");
					for(String s2:ss2){
						String name2 = Tqsz.zhengze1("num2_name:(.*)(;num2_url)", s2);
						String url2 = Tqsz.zhengze1("num2_url:(.*?)&3", s2);
						if(url2==null){
							url2 = Tqsz.zhengze1("num2_url:(.*)", s2);
						}
						if(url2==null||"".equals(url2)||"null".equals(url2)){
							url2="#";
						}else{
							url2 = request.getContextPath()+url2;
						}
						if(!"".equals(name2)){
							sb_1.append("<li><a href=\""+url2+"\">"+name2+"</a><dl class=\"nav_erji\" style=\"display:none\"> <dt>"+name2+"：</dt>");
						}
						String[] ss3 = s2.split("&3");
						for(String s3:ss3){
							String name3 = Tqsz.zhengze1("num3_name:(.*)(;num3_url)", s3);
							String url3 = Tqsz.zhengze1("num3_url:(.*)", s3);
							if(url3==null||"".equals(url3)||"null".equals(url3)){
								url3="#";
							}else{
								url3= request.getContextPath()+url3;
							}
							if(!"".equals(name3)){
								sb_1.append("<dd ><a href=\""+url3+"\">"+name3+"</a></dd>");
							}
						}
						sb_1.append("</dl></li>");
					}
					sb_1.append("</ul>");
					
				}
				sb_1.append("</li>");
			}
		}
		
		String sr = sb_1.toString();
		System.out.println("menu:"+sr);
		return sr;
	}
	public void listC(Integer num,String ids,StringBuffer sb_2,String parentId){
		List<Authority> list = dataauthorityService.queryChild(parentId);
		if(num==1&&list.size()>0){
			this.ir=2;
			num = 2;
		}else if(num==2&&list.size()>0){
			num = 3;
			this.ir=3;
		}
		for(Authority au : list){
			String id = au.getId();
			if(ids.indexOf(id)>-1){
				String url = au.getUrl();
				url = url!=null?url:"#";
				if(num==2){
					sb_2.append("&2num2_name:"+au.getName()+";num2_url:"+au.getUrl());
				}else if(num==3){
					sb_2.append("&3num3_name:"+au.getName()+";num3_url:"+au.getUrl()+"&3");
				}
				listC(this.ir,ids,sb_2,id);
			}
		}
	}
	@RequestMapping(value = "/usernameq", method = RequestMethod.POST)
	@ResponseBody
	public Account usernameq(String username){
		Account a = dataUserService.getUserName(username);
		 return a;
	}
	@RequestMapping(value = "/setses", method = RequestMethod.POST)
	@ResponseBody
	public void setsession(@PathParam("sess")String sess,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute(Sessions.SESSION_CAI, sess);
	}
	/**
	 * api
	 */
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public ModelAndView api(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CrawlRequest crawlq = new CrawlRequest();
		crawlq.setChangecrawl(2);
		crawlq.setTimeout(3000);
		crawlq.setRetryHandler(3);
		crawlq.setUrl("http://172.22.5.1:7001/openapi/");
		String c = HttpClientSingle.t(crawlq,2).getContent();
		Document doc;
		doc = Jsoup.parse(c,"http://172.22.5.1:7001/openapi/"); 
		Elements linksA = doc.select("a");
		for(Element link:linksA){
			String absHref = link.attr("abs:href"); 
			 link.attr("href",absHref); 
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("turl",doc.html());
		return request("sys/api/apihome",map);
	}
}
