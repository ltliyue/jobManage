package inspur.crawl.common.tools;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.inspur.kafka.crawl.model.CrawlRequest;
import com.inspur.kafka.crawl.model.CrawlResult;

public class HtmlUnitSingle {
	//private static SetLog log = new SetLogImpl();
	private static int timeout ;
	private static boolean enableJs= false ;
	public static CrawlResult t(CrawlRequest crawlq) throws Exception {
		
		CrawlResult cresult = new CrawlResult();
		timeout = crawlq.getTimeout();
		enableJs = crawlq.isEnableJs();
		UrlCompile uc= new UrlCompile(crawlq.getUrl());
		WebClient wc = webClient();
		try {
			WebResponse wr = wc.getPage(uc.getUrl()).getWebResponse();
			String result = wr.getContentAsString();
			Integer statusCode;
		
			statusCode = wr.getStatusCode();
			/* 4 判断访问的状态码 */
			if (statusCode.intValue() != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						);
			}
			List<NameValuePair> headers =wr.getResponseHeaders();
			Map<String, String> mapHeader = new HashMap<>();
			for (NameValuePair h : headers){
				mapHeader.put(h.getName(), h.getValue());
			}
			System.out.println("采集执行完成："+crawlq.getUrl());
			cresult.setUrl(crawlq.getUrl());
			cresult.setContent(result);
			cresult.setHeards(mapHeader);
			cresult.setStatusCode(statusCode+"");;
			cresult.setCrawlTime(new Date());
			Map map = new HashMap();
			map.put("url", crawlq.getUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			cresult = null;
			System.out.println("采集失败");
			throw e;
		} finally{
			/* 6 .释放连接 */
			wc.close();
		}
		
		return cresult;
	}
	
	public static WebClient webClient() {
		WebClient client = new WebClient();
		client.getOptions().setJavaScriptEnabled(enableJs);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setTimeout(timeout);
		// client.getCookieManager().setCookiesEnabled(false);
		client.addRequestHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 5.2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
		Cookie cookie = new Cookie("search.51job.com", "Cookie", "guid=14617426504358260015; guide=1; 51job=cenglish%3D0; search=jobarea%7E%60070900%7C%21ord_field%7E%600%7C%21list_type%7E%600%7C%21recentSearch0%7E%602%A1%FB%A1%FA070900%2C00%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA%BA%C7%BA%C7%A1%FB%A1%FA0%A1%FB%A1%FA%A1%FB%A1%FA-1%A1%FB%A1%FA1461804981%A1%FB%A1%FA0%A1%FB%A1%FA%7C%21recentSearch1%7E%602%A1%FB%A1%FA070900%2C00%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%A1%FB%A1%FA-1%A1%FB%A1%FA1461742665%A1%FB%A1%FA0%A1%FB%A1%FA%7C%21");
		client.getCookieManager().addCookie(cookie);
		//client.addRequestHeader("Host", "search.51job.com");
		return client;
	}
	
}
