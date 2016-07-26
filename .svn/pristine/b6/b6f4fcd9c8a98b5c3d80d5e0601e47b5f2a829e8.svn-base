package inspur.crawl.common.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class RestServiceUtil {

	private static String resturl = null;
	
	private static RestServiceUtil rsu = null;
	
	private RestServiceUtil() {
		
	}
	
	private static void loadProperty() {
		LoadProperty lp = new LoadProperty();
		Properties pro = lp.loadConfiguration("/restful.properties");
		resturl = pro.getProperty("rest.url");
	}
	
	public String getRestUrl() {
		if(rsu==null) {
			rsu = new RestServiceUtil();
		}
		return rsu.resturl;
	}
	
	public static String getUrlContent(String url) {
		if(resturl == null) {
			loadProperty();
		}
		Client c = Client.create();
		WebResource r=c.resource(resturl+url);
		String s = r.get(String.class);
	    return  s;
	}
	
	public static Long getLongUrlContent(String url,String w) {
		try {
			if(resturl == null) {
				loadProperty();
			}
			Client c = Client.create();
			WebResource r;
			if(w==null) {
				r = c.resource(resturl+url);
			}else {
				r = c.resource(resturl+url+URLEncoder.encode(w, "UTF-8"));
			}
			String s = r.get(String.class);
		    return Long.parseLong(s);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return 0L;
	}
}
