package com.inspur.kafka.crawl.model;

/**
 * 采集请求实体
 * @author sun_haifeng
 *
 */
public class CrawlRequest {
	/*采集url*/
	private String url;
	/*连接超时*/
	private int timeout;
	/*请求失败后，再次请求次数*/
	private int retryHandler;
	/*选择httpclient（0）或者htmlunit（1）*/
	private int changecrawl;
	/*选择2后，是否启用js，默认false*/
	private boolean enableJs;
	/*httpclient 中 0为get采集，1为post采集*/
	private int downloadType;
	
	public int getDownloadType() {
		return downloadType;
	}
	public void setDownloadType(int downloadType) {
		this.downloadType = downloadType;
	}
	public int getChangecrawl() {
		return changecrawl;
	}
	public void setChangecrawl(int changecrawl) {
		this.changecrawl = changecrawl;
	}
	public boolean isEnableJs() {
		return enableJs;
	}
	public void setEnableJs(boolean enableJs) {
		this.enableJs = enableJs;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public int getRetryHandler() {
		return retryHandler;
	}
	public void setRetryHandler(int retryHandler) {
		this.retryHandler = retryHandler;
	}
	
}