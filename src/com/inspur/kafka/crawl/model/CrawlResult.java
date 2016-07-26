package com.inspur.kafka.crawl.model;

import java.util.Date;
import java.util.Map;

/**
 * 采集返回结果实体
 * @author sun_haifeng
 *
 */
public class CrawlResult {
	/*采集url*/
	private String url;
	/*采集内容*/
	private String content;
	/*网页头*/
	private Map<String,String> heards;
	/*采集时间*/
	private Date crawlTime;
	/*采集返回状态码*/
	private String statusCode;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCrawlTime() {
		return crawlTime;
	}
	public void setCrawlTime(Date crawlTime) {
		this.crawlTime = crawlTime;
	}
	public Map<String,String> getHeards() {
		return heards;
	}
	public void setHeards(Map<String,String> heards) {
		this.heards = heards;
	}
	
}
