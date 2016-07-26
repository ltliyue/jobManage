package com.inspur.publicTools;

import inspur.crawl.codeManage.mapper.StandardCodeContentMapper;
import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.codeManage.pojo.StandardCodeContentCriteria;
import inspur.crawl.codeManage.service.StandardCodeService;
import inspur.crawl.dataManage.pojo.CrawlerDataDeliver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.hadoop.record.Buffer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer.RemoteSolrException;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;


public class SolrSearchService {

	//private static HttpSolrServer server;
	
	//每页数量
	private static int PageSize=10;

	// 初始化Solr服务器句柄

	// private SolrServerHandler solrServerHandler;
	// public static final String SOLR_URL =
	// "http://172.22.4.17:8983/solr/collection1";

//	public void destory() {
//		server = null;
//		System.runFinalization();
//		System.gc();
//	}

	public int solrSearchContent(String searchText) throws SolrServerException {
		HttpSolrServer server = SolrServerHandler.getServer(SolrServerHandler.CoreType.MAIN);
		SolrQuery query = new SolrQuery();

		// SolrQuery query = new SolrQuery();
		if (searchText.trim() == "") {
			query.setQuery("src:global_extract_content");
		} else {
			query.setQuery("src:global_extract_content AND (\"" + searchText
					+ "\" OR" + searchText + ")");
		}
		// query.setFields("hbase_title","id","hbase_url","hbase_websource");
		/*
		 * query.setFields("id", "hbase_corp_ind", "hbase_corp_type",
		 * "hbase_corp_name", "hbase_corp_reg_cap", "hbase_corp_start_date",
		 * "hbase_corp_reg_institution", "hbase_corp_create_date",
		 * "hbase_corp_address");
		 */
		// query.setSort("hbase_corp_start_date", ORDER.asc);
		query.setStart(0);
		query.setRows(20);
		// query.setRequestHandler("/select");
		QueryResponse response = server.query(query);
		// 总页数

		// 搜索得到的结果数
		System.out.println("Find:" + response.getResults().getNumFound());
		// 输出结果
		int iRow = 1;
		for (SolrDocument doc : response.getResults()) {

			for (String fieldName : doc.getFieldNames()) {
				System.out.println(fieldName + " : "
						+ doc.getFieldValue(fieldName) + "  ");
			}

			System.out
					.println("------------------------Next Document--------------------------------");
			// Collection<String> sss=doc.getFieldNames();

			// Iterator<String> it = sss.iterator();

			// System.out.println("----------" + iRow + "------------");

			// while (it.hasNext()) {
			// System.out.println(">>>>"+it.toString()
			// +doc.getFieldValue(it.toString()));
			// doc.getFieldValue(sss.toString());

			// }

			System.out.println("id: " + doc.getFieldValue("id").toString());
			if (doc.getFieldValue("hbase_corp_name") != null) {
				System.out.println("hbase_corp_name: "
						+ doc.getFieldValue("hbase_corp_name").toString());
			}
			// System.out.println("price: " +
			// doc.getFieldValue("price").toString());
			if (doc.getFieldValue("hbase_corp_ind") != null) {
				System.out.println("hbase_corp_ind: "
						+ doc.getFieldValue("hbase_corp_ind"));
			}

			iRow++;
		}
		return 1;
	}

	//返回 SolrDocumentList对象
	//searchText 搜索文本
	//intPageNo 当前页
	//siteType 网站类型
	//strsearchTerms 搜索条件
	//strdataVersion 数据版本
	//strsearchSetUp 搜索设置
	public HashMap<Object, Object> solrSearchContentReturn(String searchText,String siteType,int intPageNo,String strsearchTerms,String strdataVersion,String strsearchSetUp) throws SolrServerException {
		

		HashMap<Object, Object> solrMap=new HashMap<Object, Object>();

		HttpSolrServer server = SolrServerHandler.getServer(strdataVersion);
		SolrQuery query = new SolrQuery();
		
		StringBuilder sbSearchText=new StringBuilder();
		//搜索词
		if (!searchText.trim().isEmpty()&&!searchText.equals("*")) {
			
			//strsearchTerms 搜索条件
			//strdataVersion 数据版本
			//strsearchSetUp 搜索设置
			switch (strsearchSetUp) {
			case "1":
				// 精确搜索
				sbSearchText.append("src:" + strdataVersion + " AND "
						+ strsearchTerms + ":\"" + searchText + "\"");
				break;
			case "2":
				// 模糊搜索
				sbSearchText.append("src:" + strdataVersion + " AND "
						+ strsearchTerms + ":" + searchText);
				break;
			case "3":
				// 先精确再模糊
				sbSearchText.append("(src:" + strdataVersion + " AND "
						+ strsearchTerms + ":\"" + searchText + "\")");
				sbSearchText.append(" OR " + "(src:" + strdataVersion + " AND "
						+ strsearchTerms + ":" + searchText + ")");
				break;
			}
			
			//sbSearchText.append("src:global_extract_content AND hbase_corp_name:\""	+ searchText + "\"");
			
			/*query.setQuery("src:global_extract_content AND hbase_corp_name:\""
					+ searchText + "\"");*/

			/*
			 * query.setQuery("src:global_extract_content AND (hbase_corp_name:\""
			 * + searchText + "\" OR hbase_corp_name:" + searchText + ")");
			 */

		} else {	
			sbSearchText.append("src:" + strdataVersion );			
			//sbSearchText.append("src:global_extract_content AND hbase_corp_name:*");
			//query.setQuery("src:global_extract_content AND hbase_corp_name:*");
			// 排序			

		}
		//网站类型
		if(!siteType.equals("*"))
		{
			sbSearchText.append(" AND hbase_siteType:"+siteType);
		}
		
		query.setQuery(sbSearchText.toString());
		System.out.println("搜索语句："+sbSearchText.toString());
		
		//精确搜索和全搜索的时候按采集时间先后 其他的按匹配
		if(strsearchSetUp.equals("1") || searchText.equals("*") )
		{
			query.set("sort", "hbase_bfWeight desc");
		}
		
		//System.out.println(query.getQuery());
		if(intPageNo>0)
		{
			int intStart=PageSize*(intPageNo-1);
			query.setStart(intStart);
			query.setRows(PageSize);
		}
		else
		{
			query.setStart(0);
			query.setRows(PageSize);
		}
        

		//
		query.setFacet(true);
		query.addFacetField("hbase_siteType");
		
		
		 //设置高亮度显示 
		//query.setParam("h1", "true");
		query.setHighlight(true);
		query.setParam("hl.fl","hbase_corp_name"); 
		query.setHighlightSimplePre("<font color='red'>");
		query.setHighlightSimplePost("</font>"); // 分片设定
		query.setHighlightSnippets(5); // 每个分片的最大长度
		query.setHighlightFragsize(1000);	
		
		
		

		// query.setRequestHandler("/select");
		QueryResponse response = server.query(query);

		if (response.getResults().getNumFound() < 1) {
			solrMap.put("DocList", null);
			solrMap.put("DocCount", "0");
			solrMap.put("PageCount", "0");
			solrMap.put("PageCurrent", 0);
			return solrMap;
			// return "暂无数据";
		}
		//存储数量
		int PageCount=(int) (response.getResults().getNumFound()/PageSize+1);
		solrMap.put("PageCount", String.valueOf(PageCount));
		solrMap.put("DocCount", response.getResults().getNumFound());
		solrMap.put("PageSize", PageSize);
		solrMap.put("PageCurrent", intPageNo);
		// 分組統計
		List<FacetField> facetList = response.getFacetFields();
		HashMap<String, Object> facetMap = null;
		if (facetList != null) {
			facetMap = new HashMap<String, Object>();
			HashMap<String, Object> facetFieldMap = new HashMap<String, Object>();
			for (FacetField f : facetList) {
				List<Count> couList = f.getValues();
				
				for (Count cou : couList) {
					facetFieldMap.put(cou.getName(), cou.getCount());
				}
				//facetMap.put(f.getName(), facetFieldMap);
			}
			//System.out.println(facetMap);
			solrMap.put("DocFacet",facetFieldMap );
		}

		// 搜索得到的结果数
		// 返回结果List
		SolrDocumentList searchContentList = response.getResults();
		
		
		SolrDocumentList endSearchContentList = new SolrDocumentList(); ;
		//高亮度结果
		for(SolrDocument solrContentDoc:searchContentList){
			String id=(String)solrContentDoc.getFieldValue("id");	
			if(response.getHighlighting()!=null)
			{
				if(response.getHighlighting().get(id)!=null){
					String filedName=strsearchTerms;
					if(response.getHighlighting().get(id).get(filedName)!=null){
						String highlightFiledValue=response.getHighlighting().get(id).get(filedName).get(0);
						solrContentDoc.setField("H"+filedName, highlightFiledValue);
					//高亮字段修正——没有高亮时，赋予原有字段
					}
					/*else {
						solrContentDoc.setField("H"+filedName, solrContentDoc.get(filedName));
					}*/
				}
			}
			endSearchContentList.add(solrContentDoc);
		}	
		
		
		solrMap.put("DocList", endSearchContentList);
		//solrMap.put("DocList", searchContentList);
		//System.out.println("Find:" + response.getResults().getNumFound());
		//return searchContentList;
		return solrMap;

	}

	// 返回拼接 DIV数据
	public String solrSearchContentReturnString(String searchText, HashMap hm)
			throws SolrServerException {
		int FlagtempName = 0;
		// HttpSolrServer server = solrServerHandler.getServer();
		StringBuilder sb = new StringBuilder();
		HttpSolrServer server = SolrServerHandler.getServer(SolrServerHandler.CoreType.MAIN);
		SolrQuery query = new SolrQuery();

		if (!searchText.trim().isEmpty()) {
			query.setQuery("src:global_extract_content AND hbase_corp_name:\""
					+ searchText + "\"");

			/*
			 * query.setQuery("src:global_extract_content AND (hbase_corp_name:\""
			 * + searchText + "\" OR hbase_corp_name:" + searchText + ")");
			 */

		} else {
			query.setQuery("src:global_extract_content AND hbase_corp_name:*");
			// 排序
			query.set("sort", "hbase_bfWeight desc");

		}
		System.out.println(query.getQuery());

		// query.setQuery("src:global_extract_content");
		// query.setFields("hbase_title","id","hbase_url","hbase_websource");
		/*
		 * query.setFields("id", "hbase_corp_ind", "hbase_corp_type",
		 * "hbase_corp_name", "hbase_corp_reg_cap", "hbase_corp_start_date",
		 * "hbase_corp_reg_institution", "hbase_corp_create_date",
		 * "hbase_corp_address", "hbase_corp_end_date","hbase_recruit_position",
		 * "hbase_corp_approval_date","hbase_corp_reg_state");
		 */
		// query.setSort("hbase_corp_start_date", ORDER.asc);
		query.setStart(0);
		query.setRows(10);

		//
		query.setFacet(true);
		query.addFacetField("hbase_siteType");

		// query.setRequestHandler("/select");
		QueryResponse response = server.query(query);

		if (response.getResults().getNumFound() < 1) {
			return "暂无数据";
		}

		// 分組統計
		List<FacetField> facetList = response.getFacetFields();
		HashMap<String, Object> facetMap = null;
		if (facetList != null) {
			facetMap = new HashMap<String, Object>();
			for (FacetField f : facetList) {
				List<Count> couList = f.getValues();
				HashMap<String, Object> facetFieldMap = new HashMap<String, Object>();
				for (Count cou : couList) {
					facetFieldMap.put(cou.getName(), cou.getCount());
				}
				facetMap.put(f.getName(), facetFieldMap);
			}
			//System.out.println(facetMap);
		}

		// 搜索得到的结果数
		//System.out.println("Find:" + response.getResults().getNumFound());
		// 输出结果
		int iRow = 1;
		for (SolrDocument doc : response.getResults()) {

			if (doc.getFieldValue("hbase_corp_name") != null) {
				/*
				 * System.out.println("hbase_corp_name: " +
				 * doc.getFieldValue("hbase_corp_name").toString());
				 */
				// 第一次 显示 企业名称（精确查找）
				if (FlagtempName == 0) {
					sb.append("<h2>名称" + ": "
							+ doc.getFieldValue("hbase_corp_name") + "</h2>");
					FlagtempName = 1;
				}
			}

			if (doc.getFieldValue("hbase_siteType") != null) {
				sb.append("<h3>" + iRow + ". 网站类型" + ": "
						+ doc.getFieldValue("hbase_siteType") + "    来源于：天猫网站"
						+ "</h3>");
			} else {
				sb.append("<h3>" + iRow + ". 网站类型" + ": 未知" + "    来源于：天猫网站"
						+ "</h3>");
			}

			if (doc.getFieldValue("hbase_fetch_time") != null) {
				sb.append("<p>采集时间" + ": "
						+ doc.getFieldValue("hbase_fetch_time") + "</p>");
			} else {
				sb.append("<p>采集时间" + ": XXXXXXXX" + "</p>");
			}

			for (String fieldName : doc.getFieldNames()) {
				// System.out.println(fieldName + " : " +
				// doc.getFieldValue(fieldName) + "  ");
				sb.append("<ul>");
				if (fieldName.equals("id") || fieldName.equals("_version_")
						|| fieldName.equals("hbase_fetch_time")
						|| fieldName.equals("hbase_siteType")
						|| fieldName.equals("hbase_bfWeight")
						|| fieldName.equals("hbase_instance_id")) {
					//System.out.println(fieldName);
				} else if (fieldName.equals("hbase_corp_name")) {

				} else {
					//System.out.println(fieldName);
					if (hm.get(fieldName) != null) {
						sb.append("<li>" + hm.get(fieldName).toString() + ": "
								+ doc.getFieldValue(fieldName) + "</li> ");
					} else {
						String kk = fieldName.substring(6, fieldName.length());
						sb.append("<li>" + kk + ": "
								+ doc.getFieldValue(fieldName) + "</li> ");
					}
				}
				sb.append("</ul>");

			}

			/*
			 * sb.append("<span>" +
			 * "---------------------------------------------------------------------"
			 * + "</span> <br>");
			 */
			// System.out.println("------------------------Next Document--------------------------------");

			/*
			 * System.out.println("id: " + doc.getFieldValue("id").toString());
			 * if (doc.getFieldValue("hbase_corpeeme") != null) {
			 * System.out.println("hbase_corp_name: " +
			 * doc.getFieldValue("hbase_corp_name").toString()); }
			 * 
			 * if (doc.getFieldValue("hbase_corp_name") != null) {
			 * System.out.println("hbase_corp_name: " +
			 * doc.getFieldValue("hbase_corp_name").toString()); } //
			 * System.out.println("price: " + //
			 * doc.getFieldValue("price").toString()); if
			 * (doc.getFieldValue("hbase_corp_ind") != null) {
			 * System.out.println("hbase_corp_ind: " +
			 * doc.getFieldValue("hbase_corp_ind")); }
			 */

			iRow++;
		}

		// sb.append("</div>");
		return sb.toString();
	}

	// 返回拼接企业全部信息数据
	public String solrSearchContentReturnALLInfo(String searchText,
			String siteType, List<StandardCodeContent> list)
			throws SolrServerException {

		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		HttpSolrServer server = SolrServerHandler.getServer(SolrServerHandler.CoreType.MAIN);
		SolrQuery query = new SolrQuery();

		if (!searchText.trim().isEmpty()) {
			query.setQuery("src:global_extract_content AND hbase_corp_name:\""
					+ searchText + "\"");
		} else {
			query.setQuery("src:global_extract_content AND hbase_corp_name:*");
			// 排序
			query.set("sort", "hbase_bfWeight desc");
		}
		System.out.println(query.getQuery());

		/*
		 * //设置高亮度显示 query.setParam("h1", "true"); query.setParam("hl.fl",
		 * "hbase_corp_ind,hbase_corp_type,hbase_corp_name, hbase_corp_reg_cap,base_corp_start_date, hbase_corp_reg_institution,hbase_corp_create_date, hbase_corp_address,hbase_corp_end_date,hbase_recruit_position,hbase_corp_approval_date,hbase_corp_reg_state"
		 * ); query.setHighlightSimplePre("<font color='red'>");
		 * query.setHighlightSimplePost("</font>"); // 分片设定
		 * query.setHighlightSnippets(1); // 每个分片的最大长度
		 * query.setHighlightFragsize(1000);
		 */

		// query.setQuery("src:global_extract_content");
		// query.setFields("hbase_title","id","hbase_url","hbase_websource");
		/*
		 * query.setFields("id", "hbase_corp_ind", "hbase_corp_type",
		 * "hbase_corp_name", "hbase_corp_reg_cap", "hbase_corp_start_date",
		 * "hbase_corp_reg_institution", "hbase_corp_create_date",
		 * "hbase_corp_address", "hbase_corp_end_date","hbase_recruit_position",
		 * "hbase_corp_approval_date","hbase_corp_reg_state");
		 */
		// query.setSort("hbase_corp_start_date", ORDER.asc);
		query.setStart(0);
		query.setRows(1);

		// query.setRequestHandler("/select");
		QueryResponse response = server.query(query);

		if (response.getResults().getNumFound() > 0) {
			for (SolrDocument doc : response.getResults()) {

				for (StandardCodeContent scdObject : list) {
					String temp = "hbase_" + scdObject.getFieldCode();
					if (doc.getFieldValue(temp) != null) {
						System.out.println(scdObject.getFieldName()
								+ doc.getFieldValue(temp).toString());
					}
					if (doc.getFieldValue(temp) != null) {
						sb.append("<span style=\"color:red\">"
								+ scdObject.getFieldName() + ": "
								+ doc.getFieldValue(temp) + "</span> <br>");
					} else {
						sb.append("<span>" + scdObject.getFieldName() + ": "
								+ "暂无" + "</span> <br>");
					}
				}
			}
		} else {
			sb.append("<span>暂无数据</span> <br>");
		}

		sb.append("</div>");

		return sb.toString();
	}

	// 按照企业名称查出所有的匹配企业名称
	public String solrSearchReturnAllEnter(String searchText, String dataVersion, CrawlerDataDeliver deliver)
			throws SolrServerException {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		HttpSolrServer server = SolrServerHandler.getServer(dataVersion);
		SolrQuery query = new SolrQuery();
		
		if (!searchText.trim().isEmpty()) {
			query.setQuery("src:"+dataVersion+" AND hbase_corp_name:\""
					+ searchText + "\"");
		} else {
			query.setQuery("src:"+dataVersion+" AND hbase_corp_name:*");
			// 排序
			query.set("sort", "hbase_bfWeight desc");

		}
		

		query.set("fq", "{!collapse field=hbase_corp_name}");

		if(deliver!=null) {
			if(deliver.getDemandId()!=null && !"".equals(deliver.getDemandId())) {
				query.addFilterQuery("demand_id:"+deliver.getDemandId());
			}
			if(deliver.getPublishId()!=null && !"".equals(deliver.getPublishId())) {
				query.addFilterQuery("publish_id:"+deliver.getPublishId());
			}
		}
		
		query.setStart(0);
		query.setRows(10);
		System.out.println(query.getQuery());
		// query.setRequestHandler("/select");
		QueryResponse response = server.query(query);
		// 搜索得到的结果数
		//System.out.println("Find:" + response.getResults().getNumFound());
		// 没有搜到
		if (response.getResults().getNumFound() < 1) {
			return "";
		}

		// 输出结果
		int iRow = 1;
		for (SolrDocument doc : response.getResults()) {

			/*
			 * for (String fieldName : doc.getFieldNames()) { //
			 * System.out.println(fieldName + " : " + //
			 * doc.getFieldValue(fieldName) + "  ");
			 * 
			 * sb.append("<span>" + fieldName + ": " +
			 * doc.getFieldValue(fieldName) + "</span> <br>");
			 * 
			 * }
			 */
			/*
			 * sb.append("<span>" +
			 * "---------------------------------------------------------------------"
			 * + "</span> <br>");
			 */
			// System.out.println("------------------------Next Document--------------------------------");

			System.out.println("id: " + doc.getFieldValue("id").toString());

			if (doc.getFieldValue("hbase_corp_name") != null) {
				System.out.println("hbase_corp_name: "
						+ doc.getFieldValue("hbase_corp_name").toString());
				// 需要实现链接代码
				// "<%=path%>/datasearch/searchResultEnterInfo?searcText="
				sb.append("<a href=\"javascript:void(0)\" onclick=\"dataSearch2('"
						+ doc.getFieldValue("hbase_corp_name").toString()
						+ "')\">"
						+ doc.getFieldValue("hbase_corp_name").toString()
						+ "  </a><br>");

				// sb.append("<label onclick=\"dataSearch2('"+doc.getFieldValue("hbase_corp_name").toString()+"')\">"+doc.getFieldValue("hbase_corp_name")+"</label><br>"
				// );
			}
			// System.out.println("price: " +
			// doc.getFieldValue("price").toString());
			/*
			 * if (doc.getFieldValue("hbase_corp_ind") != null) {
			 * System.out.println("hbase_corp_ind: " +
			 * doc.getFieldValue("hbase_corp_ind")); }
			 */

			iRow++;
		}

		sb.append("</div>");
		return sb.toString();
	}

	// KEY：标准化数据类型所有字段
	// value：对应的查询条件
	// 返回所有字段查询对应的最新的值
	public HashMap searchByListIf(HashMap listHMif) {
		// 返回使用
		HashMap hmResult = new HashMap();

		HttpSolrServer server = SolrServerHandler.getServer(SolrServerHandler.CoreType.MAIN);

		Set set = listHMif.entrySet();
		Iterator i = set.iterator();
		// 循环查询
		while (i.hasNext()) {

			SolrQuery query = new SolrQuery();

			// SolrQuery query = new SolrQuery();
			Map.Entry me = (Map.Entry) i.next();
			query.setQuery(me.getValue().toString());

			// 排序
			query.set("sort", "hbase_bfWeight desc");

			query.setStart(0);
			query.setRows(1);

			// query.setRequestHandler("/select");

			QueryResponse response;
			try {
				response = server.query(query);
				// 搜索得到的结果数
				System.out.println("Find:"
						+ response.getResults().getNumFound());

				if (response.getResults().getNumFound() > 0) {
					for (SolrDocument doc : response.getResults()) {

						String temp = "hbase_" + me.getKey().toString();
						if (doc.getFieldValue(temp) != null) {
							System.out.println(me.getKey().toString()
									+ doc.getFieldValue(temp).toString());
							hmResult.put(me.getKey().toString(), doc
									.getFieldValue(temp).toString());
						}

					}
				}
			} catch (SolrServerException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				continue;
			} catch (RemoteSolrException e) {
				continue;
			}

		}

		return hmResult;

	}
	
}
