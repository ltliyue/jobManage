package inspur.crawl.requirement.pojo;

import inspur.crawl.common.interceptor.Page;

public class RequireMentQuery {
	private Integer id;
	private String properties;

	private String type;

	private String name;

	private String content;

	private String customer;

	private String proposeUser;

	private String proposeTime;

	private String requestTime;

	private String status;

	private String reason;

	private Page page;
	private String demandId;

	public String getDemandId() {
		return demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId == null ? null : demandId.trim();
	}
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	private String enclosure;

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties == null ? null : properties.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer == null ? null : customer.trim();
	}

	public String getProposeUser() {
		return proposeUser;
	}

	public void setProposeUser(String proposeUser) {
		this.proposeUser = proposeUser == null ? null : proposeUser.trim();
	}

	public String getProposeTime() {
		return proposeTime;
	}

	public void setProposeTime(String proposeTime) {
		this.proposeTime = proposeTime == null ? null : proposeTime.trim();
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime == null ? null : requestTime.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason == null ? null : reason.trim();
	}

	/**
	 * 拷贝，将对象中的字段全部拷贝到子对象中
	 * 
	 * @param bean
	 *            接收对象的子类
	 * @return 拷贝完成后的子类
	 */
	public <T extends RequireMentQuery> T copy(T bean) {
		bean.setId(getId());
		bean.setProperties(getProperties());
		bean.setType(getType());
		bean.setName(getName());
		bean.setContent(getContent());
		bean.setCustomer(getCustomer());
		bean.setProposeUser(getProposeUser());
		bean.setProposeTime(getProposeTime());
		bean.setRequestTime(getRequestTime());
		bean.setStatus(getStatus());
		bean.setReason(getReason());
		return bean;
	}

	@Override
	public String toString() {
		return "RequireMentQuery [id=" + id + ", properties=" + properties + ", type=" + type + ", name=" + name + ", content=" + content + ", customer=" + customer + ", proposeUser=" + proposeUser
				+ ", proposeTime=" + proposeTime + ", requestTime=" + requestTime + ", status=" + status + ", reason=" + reason + ", page=" + page + ", enclosure=" + enclosure + "]";
	}

	/**
	 * 格式化显示
	 */

}