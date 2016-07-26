package inspur.crawl.common.tools;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;

/**
 * 转换中文url
 * @author sun_haifeng
 *
 */
public class UrlCompile {
	public static void main(String[] args) throws URIException {
		System.out.println(URIUtil.encodeWithinAuthority(">","utf-8"));
	}
	private String url = "";
	public UrlCompile(){
		
	}
	 public UrlCompile(String url){
//		this.url = url.replace("%3D","=").replace("%3A",":").replace("%2F", "/").replace("%3F", "?").replace("%26", "&").replace("%2B","+").replace("%23", "#").replace("%20"," ").replace("%5C", "\\").replace("%25", "%");
		try {
			this.url = URIUtil.encodePathQuery(url,"utf-8").replace("%25","%");
		} catch (URIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String UrlCompileC(String content){
		try {
			content = URIUtil.encodePathQuery(content,"utf-8").replaceAll("%25","%").replaceAll("%3C", "<").
					replaceAll("%22", "\"").replaceAll("%3E", ">").replaceAll("%0A", "\n").replaceAll("%20", " ")
					.replaceAll("%23", "#").replaceAll("%7C","|");
		} catch (URIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = this.url;
	}
	 
}
