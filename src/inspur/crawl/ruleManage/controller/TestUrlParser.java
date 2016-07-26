package inspur.crawl.ruleManage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.inspur.kafka.crawl.model.CrawlRequest;
import com.sun.jersey.api.client.WebResource;

import inspur.crawl.common.tools.HttpClientSingle;
import inspur.crawl.ruleManage.service.HtmlUtils;
import inspur.crawl.ruleManage.service.RemoveSlibingProcessor;

public class TestUrlParser {

	
	public void jsonParse() throws Exception{
		
		WebResource wr;
		
		CrawlRequest crawlRequest = new CrawlRequest();
		crawlRequest.setChangecrawl(2);
		crawlRequest.setDownloadType(2);
		crawlRequest.setEnableJs(false);
		crawlRequest.setUrl("https://s.taobao.com/list?style=grid&seller_type=taobao&cps=yes&cat=162104%2C50000671&bcoffset=0&s=180");
		crawlRequest.setUrl("http://sh.meituan.com/category/meishi");
		String content = HttpClientSingle.t(crawlRequest,3).getContent();
		
		//content="poiidList\\\":[263972,4955158,281704,41257810,6399484,4394539,41597231,4113420,41138144,4181190,40138171],wegweeg,w23,332,242523";
		System.out.println(content);
		//Pattern pattern = Pattern.compile("(?<=(poiidList\\\\\":\\[|,))\\d+");
		System.out.println("poiidList\\\\\":\\[\\d+(,\\d+)*]");
		Pattern pattern = Pattern.compile("poiidList\\\\\":\\[\\d+(,\\d+)*]");
		Matcher matcher = pattern.matcher(content);
		int i = 0;
		while(matcher.find()){
			System.out.println(i+" "+matcher.group());
			i++;
		}
	}
	
	
	public void testXpath() throws Exception{
		String url = "http://t.dianping.com/deal/17177362";
		CrawlRequest crawlRequest = new CrawlRequest();
		crawlRequest.setChangecrawl(2);
		crawlRequest.setDownloadType(2);
		crawlRequest.setEnableJs(false);
		crawlRequest.setUrl(url);
		String content = HttpClientSingle.t(crawlRequest,3).getContent();
		
		System.out.println(content);
		
		HtmlPage page = null;
		String extract_content = null;
		try {
			if(page==null){
				page = (HtmlPage) HtmlUtils.getPage(content, url);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//System.out.println(page.asXml());
		if (page != null) {
			List<DomNode> nodeList = new ArrayList<DomNode>();
			try{
				nodeList=(List<DomNode>) page.getByXPath("//LINK[@rel='canonical']/@href");
			}catch(Exception e){
				e.printStackTrace();
			}
			StringBuffer buffer = new StringBuffer();
			if (nodeList != null) {
					for (int i = 0; i < nodeList.size(); i++) {
						Node node = nodeList.get(i);
						buffer.append("\r\n" + HtmlUtils.getTextContent(node)+"\t"+node.getTextContent());
						System.out.println("\r\n" + HtmlUtils.getTextContent(node)+"\t"+node.getTextContent());
					}
					extract_content = buffer.toString().trim();
			}
		}
		
		System.out.println(extract_content);
	}
	
	public void testGetLoopSample(){
		String url = "http://www.{{shenme}}.hello.{{namli}}ddd.com";
		Map<String, List<String>> map = new HashMap<>();
		List<String> sheme = new ArrayList<>();
		sheme.add("jn");
		sheme.add("nj");
		map.put("shenme", sheme);
		
		List<String> namli = new ArrayList<>(); 
		namli.add("qd");
		namli.add("dq");
		map.put("namli", namli);
		
		url = UrlParser.getLoopSample(url, map);
		System.out.println(url);
	}

	public static void main(String[] args) throws Exception {
		new TestUrlParser().testXpath();
	}
}