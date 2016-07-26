package com.inspur.bigdata.partitionConfig;

import java.util.Date;
import java.util.Set;
/**
 * 采集任务配置，分区及采集间隔信息
 */
public class TaskConfig {

	/**
	 * 任务ID
	 */
	private Long taskId;
//	private UUID taskInstanceId;
	/**
	 * 生效时间戳
	 */
	private Date effectiveDate;
	/**
	 * 分区数组,Set集合保存
	 */
	private Set<Integer> partitions;
	/**
	 * 采集间隔
	 */
	private Long interval;
	/**
	 * 采集状态
	 */
	private Integer status;
	/**
	 * 采集方式
	 * 1：httpclient 2：htmlUnit
	 */
	private Integer crawlerType;
	/**
	 * htmlunit下是否执行js
	 */
	private Integer executeJs;
	/**
	 * 采集范围
	 * 0：全量采集 1：增量采集 
	 */
	private Integer crawlerScope;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Set<Integer> getPartitions() {
		return partitions;
	}

	public void setPartitions(Set<Integer> partitions) {
		this.partitions = partitions;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public Integer getCrawlerType() {
		return crawlerType;
	}

	public void setCrawlerType(Integer crawlerType) {
		this.crawlerType = crawlerType;
	}

	public Integer getExecuteJs() {
		return executeJs;
	}

	public void setExecuteJs(Integer executeJs) {
		this.executeJs = executeJs;
	}

	public Integer getCrawlerScope() {
		return crawlerScope;
	}

	public void setCrawlerScope(Integer crawlerScope) {
		this.crawlerScope = crawlerScope;
	}

}
