package inspur.crawl.urlMonitor.pojo;

import java.util.Date;

public class UrlRelationHistory {
	//父URL
	private String parentUrl;
	//子URL
	private String subUrl;
	//子URL的深度
	private int depth;
	//发布时间
	private Date publishTime;
	//实例ID
	private String instanceId;
	//解析规则ID
	private String parseRuleId;
	//子URL的下载状态
	private String statusCode;
	
	public String getParentUrl() {
		return parentUrl;
	}
	public void setParentUrl(String parentUrl) {
		this.parentUrl = parentUrl;
	}
	public String getSubUrl() {
		return subUrl;
	}
	public void setSubUrl(String subUrl) {
		this.subUrl = subUrl;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getParseRuleId() {
		return parseRuleId;
	}
	public void setParseRuleId(String parseRuleId) {
		this.parseRuleId = parseRuleId;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "UrlRelationHistory [parentUrl=" + parentUrl + ", subUrl=" + subUrl + ", depth=" + depth
				+ ", publishTime=" + publishTime + ", instanceId=" + instanceId + ", parseRuleId=" + parseRuleId +
				", statusCode=" + statusCode +"]";
	}
}
