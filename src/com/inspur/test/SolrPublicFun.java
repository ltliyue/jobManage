package com.inspur.test;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SolrPublicFun {

	public static final String SOLR_URL = "http://172.22.4.17:8983/solr/collection1";
	//http\://172.22.6.11\:8983/solr/collection1/

	public static void main(String[] args) throws SolrServerException {
		HttpSolrServer server = new HttpSolrServer(SOLR_URL);
	    server.setMaxRetries(1);
	    server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
	    server.setConnectionTimeout(5000); // 5 seconds to establish TCP
	    //正常情况下，以下参数无须设置
	    //使用老版本solrj操作新版本的solr时，因为两个版本的javabin incompatible,所以需要设置Parser
	    server.setParser(new XMLResponseParser());
	    server.setSoTimeout(1000); // socket read timeout
	    server.setDefaultMaxConnectionsPerHost(100);
	    server.setMaxTotalConnections(100);
	    server.setFollowRedirects(false); // defaults to false
	    // allowCompression defaults to false.
	    // Server side must support gzip or deflate for this to have any effect.
	    server.setAllowCompression(true);

	    //使用ModifiableSolrParams传递参数
//			ModifiableSolrParams params = new ModifiableSolrParams();
//			// 192.168.230.128:8983/solr/select?q=video&fl=id,name,price&sort=price asc&start=0&rows=2&wt=json
//			// 设置参数，实现上面URL中的参数配置
//			// 查询关键词
//			params.set("q", "video");
//			// 返回信息
//			params.set("fl", "id,name,price,score");
//			// 排序
//			params.set("sort", "price asc");
//			// 分页,start=0就是从0开始,rows=5当前返回5条记录,第二页就是变化start这个值为5就可以了
//			params.set("start", 2);
//			params.set("rows", 2);
//			// 返回格式
//			params.set("wt", "javabin");
//			QueryResponse response = server.query(params);

	    //使用SolrQuery传递参数，SolrQuery的封装性更好
	    server.setRequestWriter(new BinaryRequestWriter());
	    for(int i=0;i<10;i++)
	    {
	    SolrQuery query = new SolrQuery();
	    query.setQuery("src:global_extract_content");
	    //query.setFields("hbase_title","id","hbase_url","hbase_websource");
	    query.setFields("id","hbase_corp_ind","hbase_corp_type","hbase_corp_name","hbase_corp_reg_cap","hbase_corp_start_date","hbase_corp_reg_institution","hbase_corp_create_date","hbase_corp_address");
	    query.setSort("hbase_corp_start_date", ORDER.asc);
	    query.setStart(0);
	    query.setRows(200);
//			query.setRequestHandler("/select");
	    QueryResponse response = server.query( query );   
	    
	    
	    // 搜索得到的结果数
	    System.out.println("Find:" + response.getResults().getNumFound());
	   
	    // 输出结果
	    int iRow = 1;
	    for (SolrDocument doc : response.getResults()) {
	      System.out.println("----------" + iRow + "------------");
	      System.out.println("id: " + doc.getFieldValue("id").toString());
	      if(doc.getFieldValue("hbase_corp_name")!=null)
	      {
	    	  System.out.println("hbase_corp_name: " + doc.getFieldValue("hbase_corp_name").toString());
	      }
	      //System.out.println("price: "  + doc.getFieldValue("price").toString());
	      if(doc.getFieldValue("hbase_corp_ind")!=null)
	      {
	      System.out.println("hbase_corp_ind: " + doc.getFieldValue("hbase_corp_ind"));
	      }
	      
	      
	      iRow++;
	    }
	    }
	  }

}
