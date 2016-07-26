package com.inspur.publicTools;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

public class SolrServerHandler {
	
	//MAP集合，针对不同core的server
	private static Map<String, HttpSolrServer> servers = new HashMap<>();
	//core type
	public class CoreType {
		public static final String MAIN = "global_main_version";
		
		public static final String DELIVER = "global_deliver_version"; 
	}
	
	public static HttpSolrServer getServer(String type) {
		if(!servers.containsKey(type)) {
			Properties prop = new Properties();
			InputStream s = SolrServerHandler.class.getResourceAsStream("/solr.properties");
			if (s == null) {
				throw new RuntimeException("Cannot find solr.properties");
			}
			try {
				prop.load(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String defaultUrl = prop.getProperty(type);

			HttpSolrServer server = new HttpSolrServer(defaultUrl);
			server.setMaxRetries(1);
			server.setMaxRetries(1); // defaults to 0. > 1 not recommended.
			server.setConnectionTimeout(5000); // 5 seconds to establish TCP
			// 正常情况下，以下参数无须设置
			// 使用老版本solrj操作新版本的solr时，因为两个版本的javabin incompatible,所以需要设置Parser
			server.setParser(new XMLResponseParser());
			server.setSoTimeout(1000); // socket read timeout
			server.setDefaultMaxConnectionsPerHost(100);
			server.setMaxTotalConnections(100);
			server.setFollowRedirects(false); // defaults to false

			server.setAllowCompression(true);
			// 使用SolrQuery传递参数，SolrQuery的封装性更好
			server.setRequestWriter(new BinaryRequestWriter());
			
			servers.put(type, server);
		}
		return servers.get(type);
	}
}
