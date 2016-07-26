package inspur.crawl.codeManage.controller;

import inspur.crawl.codeManage.pojo.SiteCode;
import inspur.crawl.codeManage.pojo.StandardCode;
import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.codeManage.service.SiteCodeService;
import inspur.crawl.codeManage.service.StandardCodeService;
import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.tools.Encoding;
import inspur.crawl.dataManage.pojo.CrawlerDataDeliver;
import inspur.crawl.dataManage.service.DataDeliverService;
import inspur.crawl.demandAna.pojo.CrawlerDemand;
import inspur.crawl.demandAna.service.DemandAnalyService;
import inspur.crawl.siteManage.pojo.RealSiteCode;
import inspur.crawl.taskManage.pojo.CrawlerTask;
import inspur.crawl.taskManage.service.TaskService;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.inspur.publicTools.SolrSearchService;

@RestController
@RequestMapping("/datasearch")
public class DataSearch extends BaseController {

	@Resource
	StandardCodeService standardCodeservice;

	@Resource
	SiteCodeService siteCodeService;

	@Resource
	TaskService taskService;

	@Resource
	DataDeliverService deliverService;
	
	private SolrSearchService solrService = new SolrSearchService();

	/*
	 * @Resource SolrSearchService solrService;
	 */

	@InitBinder("datasearch")
	public void typeBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("datasearch.");
	}
	
	@InitBinder("crawlerDataDeliver")
	public void deliverBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("deliver.");
	}
	
	@InitBinder("page")
	public void pageBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("page.");
	}

	@RequestMapping(value = "/searchResult", method = RequestMethod.POST)
	@ResponseBody
	private JSONArray searchResult(HttpServletRequest request,
			@RequestParam String searcText,@RequestParam String pageno,@RequestParam String siteType,
			@RequestParam String searchTerms,@RequestParam String dataVersion,@RequestParam String searchSetUp, 
			RealSiteCode rsc) throws UnsupportedEncodingException {
		//System.out.println(Encoding.encoding(searcText));
		//String text = Encoding.encoding(searcText);
		String text = searcText;
		String strpageno=Encoding.encoding(pageno);
		int intPageNo=Integer.parseInt(strpageno);		
		String strsiteType = Encoding.encoding(siteType);
		String strsearchTerms=Encoding.encoding(searchTerms);
		String strdataVersion=Encoding.encoding(dataVersion);
		String strsearchSetUp=Encoding.encoding(searchSetUp);
		//System.out.println("uuuuuuuuuuu"+strsearchTerms+"tttttttttt"+strdataVersion+"iiiiiiii"+strsearchSetUp);
		// int i=0;
		String ss = "";
		String strCount="0";
		String strPageCount="0";
		String strFacetResult="";
		try {

			// 开始查找所有的字段
			HashMap hmListField = new HashMap();

			List<StandardCodeContent> listSCC = standardCodeservice
					.queryAllField();
			if (listSCC != null && listSCC.size() > 0) {
				for (StandardCodeContent ee : listSCC) {
					// 将字段和对应的条件组合成 HashMap
					hmListField.put("hbase_" + ee.getFieldCode(), ee
							.getFieldName().toString());
				}
			}

			// i=solrService.solrSearchContent(text);
			// ss = solrService.solrSearchContentReturnString(text,hmListField);
			/*SolrDocumentList docList = solrService
					.solrSearchContentReturn(text);*/
			
			/*SolrDocumentList docList = solrService
					.solrSearchContentReturn(text);*/
			HashMap<Object, Object> solrResultMap=new HashMap<Object, Object>();
			solrResultMap=solrService.solrSearchContentReturn(text,siteType,intPageNo, strsearchTerms, strdataVersion, strsearchSetUp);
			SolrDocumentList docList = (SolrDocumentList) solrResultMap.get("DocList");		
			//每页的数量
			int pagesize=1;
			//当前页
			int PageCurrent=1;
			if(solrResultMap.get("PageSize")!=null)
			{
				pagesize=(int) solrResultMap.get("PageSize");
			}
			if(solrResultMap.get("PageCurrent")!=null)
			{
				PageCurrent=(int) solrResultMap.get("PageCurrent");
			}
			
			if (docList == null || docList.size() < 1) {
				ss = "暂无数据!";
			} else {
				ss = dataSearchDiv(docList, hmListField,pagesize,PageCurrent);
			}
			//数量
			strCount= solrResultMap.get("DocCount").toString();
			strPageCount=solrResultMap.get("PageCount").toString();
			
			StringBuilder sbFacet=new StringBuilder();
			sbFacet.append("<h2>网站类型</h2><dl class=\"nav nav-list\" >");
			
			if(strsiteType.equals("*"))
			{
			//分组统计
			if(solrResultMap.get("DocFacet")!=null)
			{
				HashMap<Object, Object> solrFacetMap=(HashMap<Object, Object>) solrResultMap.get("DocFacet");
				Iterator iter = solrFacetMap.entrySet().iterator();
				while(iter.hasNext())
				{
					Entry obj = (Entry) iter.next();
					//System.out.println(obj.getKey()+"--"+obj.getValue());
					
					SiteCode siteCodeModel = siteCodeService
							.querySiteCodeByIdLower(obj.getKey().toString());
					if (siteCodeModel != null && siteCodeModel.getName() != null) {
						sbFacet.append("<dd><span> "+obj.getValue()+"</span><a href=\"javascript:void(0)\" onclick=\"dataSearch(1,'"+obj.getKey().toString()+"')\">"+siteCodeModel.getName()+"</a></dd>");
					}
					else
					{					
						sbFacet.append("<dd><span> "+obj.getValue()+"</span><a href=\"javascript:void(0)\" onclick=\"dataSearch(1,'"+obj.getKey().toString()+"')\">"+obj.getKey()+"</a></dd>");
					}
					
					//JSONArray json = JSONArray.fromObject(obj.getValue());
					
					//System.out.println(solrFacetMap.get(iter.next()));
					//SiteCode siteCodeModel = siteCodeService.querySiteCodeById(siteTypeID);
				}
				
				
				
			}
			}
			sbFacet.append("</dl>");
			strFacetResult=sbFacet.toString();

		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(ss);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sb", ss);
		map.put("zys", strCount);
		map.put("PageCount", strPageCount);
		map.put("facetResult", strFacetResult);
		//map.put("pages", pages);
		JSONArray json = JSONArray.fromObject(map);
		return json;
		//out.print(json);
		
		//out.print(json);
	}

	// 对返回的搜索记录进行拼装成前台代码
	private String dataSearchDiv(SolrDocumentList solrDocList, HashMap hm,int pageSize,int PageCurrent) {
		int FlagtempName = 0;
		StringBuilder sb = new StringBuilder();
		// 输出结果
		int iRow = (PageCurrent-1)*pageSize+1;
		for (SolrDocument doc : solrDocList) {

			//if (doc.getFieldValue("hbase_corp_name") != null) {
				/*
				 * System.out.println("hbase_corp_name: " +
				 * doc.getFieldValue("hbase_corp_name").toString());
				 */
				// 第一次 显示 企业名称（精确查找）
				/*if (FlagtempName == 0) {
					sb.append("<h2>名称" + ": "
							+ doc.getFieldValue("hbase_corp_name") + "</h2>");
					sb.append("<h2>" + "&nbsp"+ "</h2>");
					FlagtempName = 1;
				}*/
			//}

			if (doc.getFieldValue("hbase_siteType") != null) {
				String siteTypeID = doc.getFieldValue("hbase_siteType")
						.toString();
				SiteCode siteCodeModel = siteCodeService
						.querySiteCodeById(siteTypeID);
				if (siteCodeModel != null && siteCodeModel.getName() != null) {
					sb.append("<h5>" + iRow + ". 网站类型" + ": "
							+ siteCodeModel.getName());
				} else {

					sb.append("<h5>" + iRow + ". 网站类型" + ": "
							+ doc.getFieldValue("hbase_siteType"));
				}
			} else {
				sb.append("<h5>" + iRow + ". 网站类型" + ": 未知");
			}

			String strInstanceId = "未知";
			if (doc.getFieldValue("hbase_instance_id") != null) {
				String siteInstanceID = doc.getFieldValue("hbase_instance_id")
						.toString();
				String strTaskId = taskService
						.getTaskIdByInstaceId(siteInstanceID);
				// System.out.println(strTaskId);
				if (strTaskId != null) {

					CrawlerTask crawlerTaskModel = taskService.getTaskById(Long
							.parseLong(strTaskId));
					if (crawlerTaskModel != null
							&& crawlerTaskModel.getTaskName() != null) {
						strInstanceId = crawlerTaskModel.getTaskName();
					}

				}

				sb.append("    来源于：" + strInstanceId + "</h5>");

			} else {
				sb.append("    来源于：未知" + "</h5>");
			}

			if (doc.getFieldValue("hbase_fetch_time") != null) {
				sb.append("<em>采集时间" + ": "
						+ doc.getFieldValue("hbase_fetch_time") + "</em>");
			} else {
				sb.append("<em>采集时间" + ": XXXXXXXX" + "</em>");
			}

			for (String fieldName : doc.getFieldNames()) {
				
				sb.append("<ul>");
				if (fieldName.equals("id") || fieldName.equals("_version_")
						|| fieldName.equals("hbase_fetch_time")
						|| fieldName.equals("hbase_siteType")
						|| fieldName.equals("hbase_bfWeight")
						|| fieldName.equals("hbase_instance_id")) {
					//System.out.println(fieldName);
				/*} else if (fieldName.equals("hbase_corp_name")) {*/

				} else {
					//System.out.println(fieldName);
					if (hm.get(fieldName) != null) {
						sb.append("<li>" + hm.get(fieldName).toString() + ":<em> "
								+ doc.getFieldValue(fieldName) + "</em></li> ");
					} else {
						String kk = fieldName.substring(6, fieldName.length());
						sb.append("<li>" + kk + ": <em>"
								+ doc.getFieldValue(fieldName) + "</em></li> ");
					}
				}
				sb.append("</ul>");

			}

			iRow++;
		}


		return sb.toString();
	}

	// 返回该企业所有的相关信息
	@RequestMapping(value = "/searchResultEnterInfo", method = RequestMethod.POST)
	@ResponseBody
	private String searchResultEnterInfo(HttpServletRequest request,
			@RequestParam String searcText,@RequestParam String dataVersion, CrawlerDataDeliver deliver, RealSiteCode rsc)
			throws ParseException {
		//System.out.println(Encoding.encoding(searcText));
		String text = Encoding.encoding(searcText);
		// int i=0;
		// String ss="";
		StringBuffer sb = new StringBuffer();

		// 先查找符合条件的企业名称
		try {
			String uuu = solrService.solrSearchReturnAllEnter(text, dataVersion, deliver);
			if (uuu == "") {
				sb.append("<div>没有找到符合条件的企业</div>");
				return sb.toString();
				//out.print(sb.toString());
				//out.close();
			}
			sb.append(uuu);
			sb.append("</br>");
		} catch (SolrServerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// i=solrService.solrSearchContent(text);
			// 查找所有的最高级
			List<StandardCode> list = standardCodeservice.queryParent();

			int i = 0;
			for (StandardCode sc : list) {
				i++;
				String parentId = sc.getId();
				sb.append("<div style=\"border-width:1px;border-color:blue;border-style:dotted\" id=\""
						+ sc.getId() + "\">");
				/*
				 * sb.append("<span>" + i + " " + sc.getName().toString() +
				 * "</span></br>");
				 */

				sb.append("<h2>" + i + " " + sc.getName().toString() + "</h2>");

				// 如果有字段则先把字段都查出来
				// 查找字段
				List<StandardCodeContent> listSCD = standardCodeservice
						.queryFieldById(parentId);

				if (listSCD != null && listSCD.size() > 1) {
					try {
						String sss = solrService
								.solrSearchContentReturnALLInfo(text, parentId,
										listSCD);
						sb.append(sss);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				String tempchild = listC(i + "", sb, parentId, text);

				sb.append(tempchild);
				sb.append("</div>");
			}
			/*
			 * int zs = service.countSiteCode(); Map<String,Object> map = new
			 * HashMap<String,Object>(); map.put("sb", sb.toString());
			 * map.put("zys", zs); //map.put("pages", pages); JSONArray json =
			 * JSONArray.fromObject(map); out.print(json);
			 */

			// ss=solrService.solrSearchContentReturnString(text);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(sb.toString());
		return sb.toString();
		//out.print(sb.toString());
	}

	public String listC(String ii, StringBuffer sb, String parentId, String text)
			throws ParseException, SolrServerException {
		// 拼装子节点的前台展示
		StringBuffer sbchild = new StringBuffer();

		// 查找他的下级
		List<StandardCode> list = standardCodeservice.queryChild(parentId);
		int i = 0;

		for (StandardCode sc : list) {
			i++;

			/*
			 * sbchild.append("<span>" + ii.toString() + "." + i + " " +
			 * sc.getName() + "</span></br>");
			 */
			int j = i + 2;
			sbchild.append("<h" + j + ">" + ii.toString() + "." + i + " "
					+ sc.getName() + "</h" + j + ">");

			// 如果有字段则先把字段都查出来
			// 查找字段
			List<StandardCodeContent> listSCD = standardCodeservice
					.queryFieldById(sc.getId());

			if (listSCD != null && listSCD.size() > 1) {
				try {
					String sss = solrService.solrSearchContentReturnALLInfo(
							text, sc.getId(), listSCD);
					sbchild.append(sss);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			/*
			 * String formatedDate =
			 * DateFormatToYYYY.dateFormate(sc.getPublishTime().toString());
			 * sb.append
			 * ("<tr class=\"treegrid-"+sc.getId()+" treegrid-parent-"+sc
			 * .getParentId()+" active\"><td>");
			 * sb.append(ii+"."+i+"</td><td>");
			 * sb.append(sc.getName()+"</td><td>");
			 * sb.append(sc.getPublisherId()+"</td><td >");
			 * sb.append(formatedDate+"</td><td >"); sb.append(
			 * "<button type=\"button\" class=\"btn btn-primary btn-sm\" data-toggle=\"modal\" id=\"add_"
			 * +
			 * sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenAddModel('"
			 * +sc.getId()+"','"+sc.getLevelCode()+
			 * "')\"  >添加下级</button>  <button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" id=\"edit_"
			 * +
			 * sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenEditModel('"
			 * +sc.getId()+"','"+sc.getName()+"','"+sc.getLevelCode()+"','"+sc.
			 * getPublisherId()+"','"+sc.getParentId()+
			 * "')\">编辑</button>  <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"del('"
			 * +sc.getId()+"')\">删除</button></td></tr>");
			 */

			listC(ii + "." + i, sbchild, sc.getId(), text);
		}

		return sbchild.toString();
	}

	// 返回该企业所有的相关信息方法2
	@RequestMapping(value = "/searchResultEnterInfo2", method = RequestMethod.POST)
	@ResponseBody
	private String searchResultEnterInfo2(HttpServletRequest request,
			@RequestParam String searcText, @RequestParam String dataVersion, CrawlerDataDeliver deliver, RealSiteCode rsc)
			throws ParseException {
		//System.out.println(Encoding.encoding(searcText));
		//String text = Encoding.encoding(searcText);
		String text = searcText;
		// int i=0;
		// String ss="";
		StringBuffer sb = new StringBuffer();
//		sb.append("[");
		// 先查找符合条件的企业名称
		try {
			String uuu = solrService.solrSearchReturnAllEnter(text, dataVersion, deliver);
			if (uuu == "") {
				sb.append("<div>没有找到符合条件的企业</div>");
				/*out.print(sb.toString());
				out.close();*/
				return sb.toString();
			}
			sb.append(uuu);
			sb.append("</br>");
		} catch (SolrServerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			// 开始查找所有的字段
			HashMap hmListField = new HashMap();

			List<StandardCodeContent> listSCC = standardCodeservice
					.queryAllField();
			if (listSCC != null && listSCC.size() > 0) {
				for (StandardCodeContent ss : listSCC) {
					String Ifsearch = "src:global_extract_content AND hbase_corp_name:\""
							+ text + "\" AND hbase_" + ss.getFieldCode() + ":*";
					// 将字段和对应的条件组合成 HashMap
					hmListField.put(ss.getFieldCode(), Ifsearch);
				}
			}

			// 返回的值
			HashMap hmResult = new HashMap();

			hmResult = solrService.searchByListIf(hmListField);
			if (hmResult == null) {
				System.out.println("没有搜索到----------");
			}

			// i=solrService.solrSearchContent(text);
			// 查找所有的最高级
			List<StandardCode> list = standardCodeservice.queryParent();

			int i = 0;
			for (StandardCode sc : list) {
				i++;
				String parentId = sc.getId();
				sb.append("<div style=\"border-width:1px;border-color:blue;border-style:dotted\" id=\""
						+ sc.getId() + "\">");
				/*
				 * sb.append("<span>" + i + " " + sc.getName().toString() +
				 * "</span></br>");
				 */

				sb.append("<h2>" + i + " " + sc.getName().toString() + "</h2>");

				// 如果有字段则先把字段都查出来
				// 查找字段
				List<StandardCodeContent> listSCD = standardCodeservice
						.queryFieldById(parentId);

				if (listSCD != null && listSCD.size() > 1) {
					try {
						for (StandardCodeContent scdObject : listSCD) {
							sb.append("<ul>");
							if (hmResult.get(scdObject.getFieldCode()) != null) {
								sb.append("<li style=\"color:red\">"
										+ scdObject.getFieldName()
										+ ": "
										+ hmResult
												.get(scdObject.getFieldCode())
												.toString() + "</li> ");
							} else {
								sb.append("<li>" + scdObject.getFieldName()
										+ ": " + "暂无" + "</li> ");
							}
							sb.append("</ul>");
						}
						/*
						 * String sss = solrService
						 * .solrSearchContentReturnALLInfo(text, parentId,
						 * listSCD);
						 */
						// sb.append(sss);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				String tempchild = listD(i + "", sb, parentId, text, hmResult);

				sb.append(tempchild);
				sb.append("</div>");
			}
			/*
			 * int zs = service.countSiteCode(); Map<String,Object> map = new
			 * HashMap<String,Object>(); map.put("sb", sb.toString());
			 * map.put("zys", zs); //map.put("pages", pages); JSONArray json =
			 * JSONArray.fromObject(map); out.print(json);
			 */

			// ss=solrService.solrSearchContentReturnString(text);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		sb.append("]");
		System.out.println(sb.toString());
		return sb.toString();
		//out.print(sb.toString());
	}

	public String listD(String ii, StringBuffer sb, String parentId,
			String text, HashMap hmResult) throws ParseException,
			SolrServerException {
		// 拼装子节点的前台展示
		StringBuffer sbchild = new StringBuffer();

		// 查找他的下级
		List<StandardCode> list = standardCodeservice.queryChild(parentId);
		int i = 0;
		if (list == null || list.size() < 1) {
			sb.append("</br>");
		}

		for (StandardCode sc : list) {
			i++;

			/*
			 * sbchild.append("<span>" + ii.toString() + "." + i + " " +
			 * sc.getName() + "</span></br>");
			 */
			int j = i + 2;
			sbchild.append("<h" + j + ">" + ii.toString() + "." + i + " "
					+ sc.getName() + "</h" + j + ">");

			// 如果有字段则先把字段都查出来
			// 查找字段
			List<StandardCodeContent> listSCD = standardCodeservice
					.queryFieldById(sc.getId());

			if (listSCD != null && listSCD.size() > 1) {
				try {
					for (StandardCodeContent scdObject : listSCD) {
						sb.append("<ul>");
						if (hmResult.get(scdObject.getFieldCode()) != null) {
							sb.append("<li style=\"color:red\">"
									+ scdObject.getFieldName()
									+ ": "
									+ hmResult.get(scdObject.getFieldCode())
											.toString() + "</li> ");
						} else {
							sb.append("<li>" + scdObject.getFieldName() + ": "
									+ "暂无" + "</li> ");
						}
						sb.append("</ul>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// sb.append("<ul><li>暂无数据 </li></ul></br>");
			}

			/*
			 * String formatedDate =
			 * DateFormatToYYYY.dateFormate(sc.getPublishTime().toString());
			 * sb.append
			 * ("<tr class=\"treegrid-"+sc.getId()+" treegrid-parent-"+sc
			 * .getParentId()+" active\"><td>");
			 * sb.append(ii+"."+i+"</td><td>");
			 * sb.append(sc.getName()+"</td><td>");
			 * sb.append(sc.getPublisherId()+"</td><td >");
			 * sb.append(formatedDate+"</td><td >"); sb.append(
			 * "<button type=\"button\" class=\"btn btn-primary btn-sm\" data-toggle=\"modal\" id=\"add_"
			 * +
			 * sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenAddModel('"
			 * +sc.getId()+"','"+sc.getLevelCode()+
			 * "')\"  >添加下级</button>  <button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" id=\"edit_"
			 * +
			 * sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenEditModel('"
			 * +sc.getId()+"','"+sc.getName()+"','"+sc.getLevelCode()+"','"+sc.
			 * getPublisherId()+"','"+sc.getParentId()+
			 * "')\">编辑</button>  <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"del('"
			 * +sc.getId()+"')\">删除</button></td></tr>");
			 */

			listD(ii + "." + i, sbchild, sc.getId(), text, hmResult);
		}
		System.out.print(sbchild.toString());
		return sbchild.toString();
	}
	
	@RequestMapping(value = "/getdatalist", method = RequestMethod.POST)
	@ResponseBody
	public String getdatalist(HttpServletRequest request) throws ParseException {
		StringBuilder sb = new StringBuilder();
		// 查找所有的最高级
		List<StandardCode> list = standardCodeservice.queryParent();
		int i = 0;

		for (StandardCode sc : list) {
			i++;
			String parentId = sc.getId();
			sb.append("<h2 class=\"headline-1\">");
			sb.append("<a class=\"anchor-1\" name=\""+i+"\"></a> <span class=\"headline-1-index\">"+i+"&nbsp</span>");
			sb.append("<span class=\"headline-content\">" + sc.getName()
					+ "</span>");
			sb.append("</h2>");
			sb.append("<div class=\"content_qyjyxxnr\" id=\"" + sc.getId()
					+ "\">");

			/*
			 * // 如果有字段则先把字段都查出来 // 查找字段 List<StandardCodeContent> listSCD =
			 * standardCodeservice .queryFieldById(parentId);
			 * 
			 * if (listSCD != null && listSCD.size() > 1) { try {
			 * 
			 * sb.append(""); } catch (Exception e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); } }
			 */

			/*
			 * <ul> <li>公司名称：北京京顺经纪人事务所</li> <li>公司规模：暂无</li> <li>公司行业：杜惠丰</li>
			 * <li>成立日期：2001年12月06日</li> <li>住所：北京市顺义区张桥镇沙浮村大街41号</li> </ul>
			 */

			sb.append("</div>");

			//String tempchild = listF(i + "." + i, sb, sc.getId());
			String tempchild = listF(String.valueOf(i), sb, sc.getId());
		
			sb.append(tempchild);

		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public String listF(String ii, StringBuilder sb, String parentId)
			throws ParseException {
		List<StandardCode> list = standardCodeservice.queryChild(parentId);
		StringBuilder sbchild = new StringBuilder();
		int i = 0;
		for (StandardCode sc : list) {
			i++;
			String iii = ii + "." + i;
			sbchild.append("<h2 class=\"headline-1\">");
			sbchild.append("<a class=\"anchor-1\" name=\""+iii+"\"></a> <span class=\"headline-1-index\">"+iii+"</span>");
			sbchild.append("<span class=\"headline-content\">" + sc.getName()
					+ "</span>");
			sbchild.append("</h2>");
			sbchild.append("<div class=\"content_qyjyxxnr\" id=\"" + sc.getId()
					+ "\">");
			sbchild.append("</div>");
			// String formatedDate =
			// DateFormatToYYYY.dateFormate(sc.getPublishTime().toString());
			// sb.append("<tr class=\"treegrid-"+sc.getId()+" treegrid-parent-"+sc.getParentId()+" active\"><td>");
			// sb.append(ii+"."+i+"</td><td>");
			// sb.append(sc.getName()+"</td><td>");
			// sb.append(sc.getPublisherId()+"</td><td >");
			// sb.append(formatedDate+"</td><td >");
			// sb.append("<button type=\"button\" class=\"btn btn-primary btn-sm\" data-toggle=\"modal\" id=\"addField_"+sc.getId()+"\" data-target=\"#addField\" onclick=\"OpenAddField('"+sc.getId()+"','"+sc.getName()+"')\"  >详细字段</button>  ");
			// sb.append("<button type=\"button\" class=\"btn btn-primary btn-sm\" data-toggle=\"modal\" id=\"add_"+sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenAddModel('"+sc.getId()+"','"+sc.getName()+"','"+sc.getLevelCode()+"')\"  >添加下级</button>  <button type=\"button\" class=\"btn btn-info btn-sm\" data-toggle=\"modal\" id=\"edit_"+sc.getId()+"\" data-target=\"#addModal\" onclick=\"OpenEditModel('"+sc.getId()+"','"+sc.getName()+"','"+sc.getLevelCode()+"','"+sc.getPublisherId()+"','"+sc.getParentId()+"')\">编辑</button>  <button type=\"button\" class=\"btn btn-danger btn-sm\" onclick=\"del('"+sc.getId()+"')\">删除</button></td></tr>");

			listF(ii + "." + i, sbchild, sc.getId());
		}

		return sbchild.toString();
	}

	@RequestMapping(value = "/searchpage", method = RequestMethod.GET)
	public ModelAndView setSite(StandardCodeContent site) throws Exception {
		/* return request("sys/search/searchIndex"); */
		return request("sys/search/searchSimple");
	}

	@RequestMapping(value = "/searchindex", method = RequestMethod.GET)
	public ModelAndView setSiteIndex(StandardCodeContent site) throws Exception {
		return request("sys/search/searchIndex");
		 //return request("sys/search/NewFile1"); 
	}
	
	
	// 返回该企业所有的相关信息方法3
		@RequestMapping(value = "/searchResultEnterInfo3", method = RequestMethod.POST)
		@ResponseBody
		private String searchResultEnterInfo3(HttpServletRequest request,
				@RequestParam String searcText,@RequestParam String dataVersion, CrawlerDataDeliver deliver, RealSiteCode rsc)
				throws ParseException {
			//System.out.println(Encoding.encoding(searcText));
			//String text = Encoding.encoding(searcText);
			String text = searcText;
			// int i=0;
			// String ss="";
			StringBuffer sb = new StringBuffer();
//			sb.append("[");
			// 先查找符合条件的企业名称
			try {
				String uuu = solrService.solrSearchReturnAllEnter(text, dataVersion, deliver);
				if (uuu == "") {
					sb.append("<div>没有找到符合条件的企业</div>");
					/*out.print(sb.toString());
					out.close();*/
					return sb.toString();
				}
				sb.append(uuu);
				sb.append("</br>");
			} catch (SolrServerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {

				// 开始查找所有的字段
				HashMap hmListField = new HashMap();

				List<StandardCodeContent> listSCC = standardCodeservice
						.queryAllField();
				if (listSCC != null && listSCC.size() > 0) {
					for (StandardCodeContent ss : listSCC) {
						String Ifsearch = "src:global_extract_content AND hbase_corp_name:\""
								+ text + "\" AND hbase_" + ss.getFieldCode() + ":*";
						// 将字段和对应的条件组合成 HashMap
						hmListField.put(ss.getFieldCode(), Ifsearch);
					}
				}

				// 返回的值
				HashMap hmResult = new HashMap();

				hmResult = solrService.searchByListIf(hmListField);
				if (hmResult == null) {
					System.out.println("没有搜索到----------");
				}

				// i=solrService.solrSearchContent(text);
				// 查找所有的最高级
				List<StandardCode> list = standardCodeservice.queryParent();

				int i = 0;
				for (StandardCode sc : list) {
					i++;
					String parentId = sc.getId();
					/*sb.append("<div style=\"border-width:1px;border-color:blue;border-style:dotted\" id=\""
							+ sc.getId() + "\">");
					
					 * sb.append("<span>" + i + " " + sc.getName().toString() +
					 * "</span></br>");
					 

					sb.append("<h2>" + i + " " + sc.getName().toString() + "</h2>");*/
					
					sb.append("<h2 class=\"headline-1\">");
					sb.append("<a class=\"anchor-1\" name=\""+i+"\"></a> <span class=\"headline-1-index\">"+i+"&nbsp</span>");
					sb.append("<span class=\"headline-content\">" + sc.getName()
							+ "</span>");
					sb.append("</h2>");
										

					

					// 如果有字段则先把字段都查出来
					// 查找字段
					List<StandardCodeContent> listSCD = standardCodeservice
							.queryFieldById(parentId);

					if (listSCD != null && listSCD.size() > 1) {
						try {
							sb.append("<div class=\"content_qyjyxxnr\" id=\"" + sc.getId()
									+ "\">");
							for (StandardCodeContent scdObject : listSCD) {
								sb.append("<ul>");
								if (hmResult.get(scdObject.getFieldCode()) != null) {
									sb.append("<li style=\"color:red\">"
											+ scdObject.getFieldName()
											+ ": "
											+ hmResult
													.get(scdObject.getFieldCode())
													.toString() + "</li> ");
								} else {
									sb.append("<li>" + scdObject.getFieldName()
											+ ": " + "暂无" + "</li> ");
								}
								sb.append("</ul>");
							}
							/*
							 * String sss = solrService
							 * .solrSearchContentReturnALLInfo(text, parentId,
							 * listSCD);
							 */
							// sb.append(sss);
							sb.append("</div>");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					String tempchild = listG(String.valueOf(i) , sb, parentId, text, hmResult);

					sb.append(tempchild);
					//sb.append("</div>");
				}
				/*
				 * int zs = service.countSiteCode(); Map<String,Object> map = new
				 * HashMap<String,Object>(); map.put("sb", sb.toString());
				 * map.put("zys", zs); //map.put("pages", pages); JSONArray json =
				 * JSONArray.fromObject(map); out.print(json);
				 */

				// ss=solrService.solrSearchContentReturnString(text);
			} catch (SolrServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			sb.append("]");
			System.out.println(sb.toString());
			return sb.toString();
			//out.print(sb.toString());
		}

		public String listG(String ii, StringBuffer sb, String parentId,
				String text, HashMap hmResult) throws ParseException,
				SolrServerException {
			// 拼装子节点的前台展示
			StringBuffer sbchild = new StringBuffer();

			// 查找他的下级
			List<StandardCode> list = standardCodeservice.queryChild(parentId);
			int i = 0;
			if (list == null || list.size() < 1) {
				sb.append("</br>");
			}

			for (StandardCode sc : list) {
				i++;
				String iii = ii + "-" + i;
				/*
				 * sbchild.append("<span>" + ii.toString() + "." + i + " " +
				 * sc.getName() + "</span></br>");
				 */
				//int j = i + 2;
				sbchild.append("<h2 class=\"headline-2\">");
				sbchild.append("<a class=\"anchor-2\" name=\""+iii+"\"></a> <span class=\"headline-2-index\">"+iii+"</span>");
				sbchild.append("<span class=\"headline-content\">" + sc.getName()
						+ "</span>");
				sbchild.append("</h2>");
				
				
				//sbchild.append("<div class=\"content_qyjyxxnr\" id=\"" + sc.getId()	+ "\">");
				/*sbchild.append("<h" + j + ">" + ii.toString() + "." + i + " "
						+ sc.getName() + "</h" + j + ">");*/

				// 如果有字段则先把字段都查出来
				// 查找字段
				List<StandardCodeContent> listSCD = standardCodeservice
						.queryFieldById(sc.getId());

				if (listSCD != null && listSCD.size() > 1) {
					
					try {
						
						for (StandardCodeContent scdObject : listSCD) {
							sbchild.append("<ul>");
							if (hmResult.get(scdObject.getFieldCode()) != null) {
								sbchild.append("<li style=\"color:red\">"
										+ scdObject.getFieldName()
										+ ": "
										+ hmResult.get(scdObject.getFieldCode())
												.toString() + "</li> ");
							} else {
								sbchild.append("<li>" + scdObject.getFieldName() + ": "
										+ "暂无" + "</li> ");
							}
							sbchild.append("</ul>");
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else {
					// sb.append("<ul><li>暂无数据 </li></ul></br>");
				}

				//sbchild.append("</div>");

				listD(ii + "." + i, sbchild, sc.getId(), text, hmResult);
			}
			//System.out.print(sbchild.toString());
			return sbchild.toString();
		}
		
		@Resource
		DemandAnalyService demandService;
		/**
		 * 交付需求和版本
		 * @param deliver
		 * @param page
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/deliverList", method = RequestMethod.GET)
		public ModelAndView deliverList(CrawlerDataDeliver deliver, Page page) throws Exception {
			List<CrawlerDataDeliver> delivers = deliverService.queryDataVersions(deliver, page);
			List<CrawlerDemand> demands = demandService.queryDemand();
			return request("sys/search/deliverList").addObject("delivers", delivers)
					.addObject("demands", demands)
					.addObject("deliver", deliver);
		}
}
