package inspur.crawl.siteManage.pojo;

import java.util.Date;

public class RealSiteCode {
    private String id;

    private String name;

    private String url;

    private String siteType;

    private String siteDescription;

    private String publisherId;

    private Date publishTime;

    private String validateFlag;

    private String parentId;

    private String executionCycle;

    private String dueTime;

    private String otherDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType == null ? null : siteType.trim();
    }

    public String getSiteDescription() {
        return siteDescription;
    }

    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription == null ? null : siteDescription.trim();
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId == null ? null : publisherId.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getValidateFlag() {
        return validateFlag;
    }

    public void setValidateFlag(String validateFlag) {
        this.validateFlag = validateFlag == null ? null : validateFlag.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getExecutionCycle() {
        return executionCycle;
    }

    public void setExecutionCycle(String executionCycle) {
        this.executionCycle = executionCycle == null ? null : executionCycle.trim();
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime == null ? null : dueTime.trim();
    }

    public String getOtherDescription() {
        return otherDescription;
    }

    public void setOtherDescription(String otherDescription) {
        this.otherDescription = otherDescription == null ? null : otherDescription.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends RealSiteCode> T copy(T bean) {
        bean.setId(getId());
        bean.setName(getName());
        bean.setUrl(getUrl());
        bean.setSiteType(getSiteType());
        bean.setSiteDescription(getSiteDescription());
        bean.setPublisherId(getPublisherId());
        bean.setPublishTime(getPublishTime());
        bean.setValidateFlag(getValidateFlag());
        bean.setParentId(getParentId());
        bean.setExecutionCycle(getExecutionCycle());
        bean.setDueTime(getDueTime());
        bean.setOtherDescription(getOtherDescription());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", name:" + getName() + 
        	", url:" + getUrl() + 
        	", siteType:" + getSiteType() + 
        	", siteDescription:" + getSiteDescription() + 
        	", publisherId:" + getPublisherId() + 
        	", publishTime:" + getPublishTime() + 
        	", validateFlag:" + getValidateFlag() + 
        	", parentId:" + getParentId() + 
        	", executionCycle:" + getExecutionCycle() + 
        	", dueTime:" + getDueTime() + 
        	", otherDescription:" + getOtherDescription() + 
        "}";
    }
}