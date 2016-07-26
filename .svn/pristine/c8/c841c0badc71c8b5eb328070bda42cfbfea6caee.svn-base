package inspur.crawl.ruleManage.controller;

import java.util.Map;

import com.inspur.avro.model.java.UrlType;

/**
 * 用户自定义的解析程序的接口，返回抽取到的URL和URL的类型，
 * UrlType有几种类型OTHER——其它, NAV——导航, LIST——列表, INFO——详情
 * @author maolh
 *
 */
public interface Parse {
	/**
	 * 
	 * @param content  网页的内容
	 * @return 从网页中解析出来的URL和URL的类型
	 */
	public Map<String, UrlType>  parse(String content);
}
