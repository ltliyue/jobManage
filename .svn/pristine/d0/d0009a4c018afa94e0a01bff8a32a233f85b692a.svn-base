package inspur.crawl.requirement.pojo;

import inspur.crawl.common.interceptor.Page;

public class RequireMent {
	private Integer id;
	private String properties;

	private String type;

	private String name;

	private String content;

	private String customer;

	private String proposeUser;

	private String proposeTime;
	private String proposeTimeF;
	private String proposeTimeT;
	
	private String requestTime;
	private String requestTimeF;
	private String requestTimeT;

	private String status;

	private String reason;
	private String enclosure;

	private String demandId;

	public String getDemandId() {
		return demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId == null ? null : demandId.trim();
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
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



	public String getProposeTimeF() {
		return proposeTimeF;
	}

	public void setProposeTimeF(String proposeTimeF) {
		this.proposeTimeF = proposeTimeF;
	}

	public String getProposeTimeT() {
		return proposeTimeT;
	}

	public void setProposeTimeT(String proposeTimeT) {
		this.proposeTimeT = proposeTimeT;
	}

	public String getRequestTimeF() {
		return requestTimeF;
	}

	public void setRequestTimeF(String requestTimeF) {
		this.requestTimeF = requestTimeF;
	}

	public String getRequestTimeT() {
		return requestTimeT;
	}

	public void setRequestTimeT(String requestTimeT) {
		this.requestTimeT = requestTimeT;
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

	
	public String getProposeTime() {
		return proposeTime;
	}

	public void setProposeTime(String proposeTime) {
		this.proposeTime = proposeTime;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	/**
	 * 拷贝，将对象中的字段全部拷贝到子对象中
	 * 
	 * @param bean
	 *            接收对象的子类
	 * @return 拷贝完成后的子类
	 */
	public <T extends RequireMent> T copy(T bean) {
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

	/**
	 * 格式化显示
	 */
	@Override
	public String toString() {
		return "{" + ", id:" + getId() + ", properties:" + getProperties() + ", type:" + getType() + ", name:" + getName() + ", content:" + getContent() + ", customer:" + getCustomer()
				+ ", proposeUser:" + getProposeUser() + ", proposeTime:" + getProposeTime() + ", requestTime:" + getRequestTime() + ", status:" + getStatus() + ", reason:" + getReason() + "}";
	}
}