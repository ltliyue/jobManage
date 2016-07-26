package com.inspur.kafka.crawl.model;

import java.util.List;

/**
 * 采集规则
 * @author sun_haifeng
 *
 */
public class CrawlRules {
	private String taskId;
	private List<String> filter;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public List<String> getFilter() {
		return filter;
	}
	public void setFilter(List<String> filter) {
		this.filter = filter;
	}
	
}
