package inspur.crawl.ruleManage.pojo;

import java.util.Date;

import inspur.crawl.common.interceptor.Page;

public class ExtractRule {
	private String id;

	private String taskId;

	private String urlPattern;

	private String extractType;

	private String regex;

	private String regexGroupId;

	private String xpath;

	private Date time;

	private Short enabled;

	private String name;

	private Short containHtml;

	private String storeColumnName;

	private String storeColumnComment;

	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId == null ? null : taskId.trim();
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern == null ? null : urlPattern.trim();
	}

	public String getExtractType() {
		return extractType;
	}

	public void setExtractType(String extractType) {
		this.extractType = extractType == null ? null : extractType.trim();
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex == null ? null : regex.trim();
	}

	public String getRegexGroupId() {
		return regexGroupId;
	}

	public void setRegexGroupId(String regexGroupId) {
		this.regexGroupId = regexGroupId == null ? null : regexGroupId.trim();
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath == null ? null : xpath.trim();
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Short getEnabled() {
		return enabled;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Short getContainHtml() {
		return containHtml;
	}

	public void setContainHtml(Short containHtml) {
		this.containHtml = containHtml;
	}

	public String getStoreColumnName() {
		return storeColumnName;
	}

	public void setStoreColumnName(String storeColumnName) {
		this.storeColumnName = storeColumnName == null ? null : storeColumnName.trim();
	}

	public String getStoreColumnComment() {
		return storeColumnComment;
	}

	public void setStoreColumnComment(String storeColumnComment) {
		this.storeColumnComment = storeColumnComment == null ? null : storeColumnComment.trim();
	}

	/**
	 * 拷贝，将对象中的字段全部拷贝到子对象中
	 * 
	 * @param bean
	 *            接收对象的子类
	 * @return 拷贝完成后的子类
	 */
	public <T extends ExtractRule> T copy(T bean) {
		bean.setId(getId());
		bean.setTaskId(getTaskId());
		bean.setUrlPattern(getUrlPattern());
		bean.setExtractType(getExtractType());
		bean.setRegex(getRegex());
		bean.setRegexGroupId(getRegexGroupId());
		bean.setXpath(getXpath());
		bean.setTime(getTime());
		bean.setEnabled(getEnabled());
		bean.setName(getName());
		bean.setContainHtml(getContainHtml());
		bean.setStoreColumnName(getStoreColumnName());
		bean.setStoreColumnComment(getStoreColumnComment());
		return bean;
	}

	/**
	 * 格式化显示
	 */
	@Override
	public String toString() {
		return "{" + ", id:" + getId() + ", taskId:" + getTaskId() + ", urlPattern:" + getUrlPattern()
				+ ", extractType:" + getExtractType() + ", regex:" + getRegex() + ", regexGroupId:" + getRegexGroupId()
				+ ", xpath:" + getXpath() + ", time:" + getTime() + ", enabled:" + getEnabled() + ", name:" + getName()
				+ ", containHtml:" + getContainHtml() + ", storeColumnName:" + getStoreColumnName()
				+ ", storeColumnComment:" + getStoreColumnComment() + "}";
	}
}