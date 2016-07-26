package inspur.crawl.demandAna.controller;

import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.demandAna.pojo.CrawlerDemand;
import inspur.crawl.demandAna.pojo.CrawlerOpinion;
import inspur.crawl.demandAna.pojo.DemandAnalyse;
import inspur.crawl.demandAna.service.DemandAnalyService;
import inspur.crawl.siteManage.pojo.RealSiteCode;
import inspur.crawl.siteManage.pojo.SiteItemsCode;
import inspur.crawl.siteManage.service.RealSiteService;
import inspur.crawl.sysManage.pojo.Account;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/demandConfirm")
public class DemandConfirmController extends BaseController{
	@Resource
	DemandAnalyService service;
	@Resource
	RealSiteService rsservice;
	
	@InitBinder("demandConfirm") 
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("demandConfirm."); 
	}
	/**
	 * 管理
	 */
	@RequestMapping(value = "/setconfirm", method = RequestMethod.GET)
	public ModelAndView demandManage(CrawlerDemand demand) throws Exception {
		List<CrawlerDemand> list = service.queryDemand();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("demands", list);
		map.put("page", demand);
		return request("sys/demandConfirm/demandConfirm", map);
	}
	/**
	 * 弹出分析页面
	 */
	@RequestMapping(value = "/demandAnalyse", method = RequestMethod.GET)
	public ModelAndView demandAnalyse(@RequestParam String demandId){
		DemandAnalyse analyse = service.queryAnalyseByDemandId(demandId);
		System.out.println("analyse++++++++++++++++++++++++++++++:"+analyse);
		Map<String,Object> map = new HashMap<String, Object>();
		List<RealSiteCode> list = new ArrayList<RealSiteCode>();
		map.put("analyse", analyse);
		String[] siteId = analyse.getSiteId().split(";");
		for(int i = 0;i<siteId.length;i++){
			RealSiteCode site = rsservice.querySiteBySiteId(siteId[i]);
			list.add(site);
		}
		map.put("sites", list);
		return 	request("sys/demandConfirm/demandAnalyse", map);
	}
	/**
	 * 意见反馈页面
	 */
	@RequestMapping(value = "/feedback", method = RequestMethod.GET)
	public ModelAndView feedback(@RequestParam String demandId) throws Exception {
		CrawlerOpinion opinion = service.queryOpinion(demandId);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("feedback", opinion);
		return request("sys/demandConfirm/demandFeedBack", map);
	}
	/**
	 * 数据使用反馈保存
	 * @param demandId
	 * @param dataFeedback
	 * @param out
	 */
	@RequestMapping(value = "/datafeedback", method = RequestMethod.POST)
	@ResponseBody
	public void dataFeedback(@RequestParam String demandId, @RequestParam String dataFeedback, PrintWriter out){
		CrawlerDemand demand = new CrawlerDemand();
		demand.setDemandId(demandId);
		demand.setDataFeedback(dataFeedback);
		int i = service.updateDemandDataFeedback(demand);
		out.print(i);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/getItem", method = RequestMethod.POST)
	@ResponseBody
	public List<StandardCodeContent> getItem(@RequestParam String siteId) throws Exception {
		//使用唐璇的select方法
		List<StandardCodeContent> item = service.querySiteItem(siteId);
		return item;
	}
	
	/**
	 * 需求分析结果保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, @RequestParam MultipartFile[] feedfile, CrawlerOpinion opinion, @RequestParam String demand, @RequestParam String idN, @RequestParam String methodNew){
		int i = 0;
		Map<String,String> mapResult = new HashMap<String,String>();
		try {
			HttpSession session = request.getSession();
			String username = session.getAttribute(Sessions.SESSION_USER).toString();
			Account account = service.selectName(username);
			opinion.setPublishTime(new Date());
			opinion.setId(idN);
			opinion.setDemandId(demand);
			opinion.setType(methodNew);
			String name = account.getName();
			opinion.setPublisherId(name);
			mapResult = DemandAnalyController.uploadFile(feedfile, idN, request);
			opinion.setFilePath(mapResult.get("fileName"));
			System.out.println("opinion--------------------"+opinion);
			i = service.updateOpinion(opinion);
			if(i==1){
				i = service.updataDemandStatus("03",opinion.getDemandId());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("feedback", opinion);
//		map.put("flag", i);
//		map.put("flagfile", mapResult.get("flag"));
		List<CrawlerDemand> list = service.queryDemand();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("demands", list);
		map.put("page", demand);
		return 	request("sys/demandAna/demandManage", map);
	}
}
