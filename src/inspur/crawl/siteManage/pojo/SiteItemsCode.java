package inspur.crawl.siteManage.pojo;

import java.util.Date;

public class SiteItemsCode {
    private String id;

    private String siteId;

    private String fieldId;

    private String fieldName;

    private String fieldCode;

    private String publisherId;

    private Date publishTime;

    private String validateFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId == null ? null : siteId.trim();
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId == null ? null : fieldId.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
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

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends SiteItemsCode> T copy(T bean) {
        bean.setId(getId());
        bean.setSiteId(getSiteId());
        bean.setFieldId(getFieldId());
        bean.setFieldName(getFieldName());
        bean.setFieldCode(getFieldCode());
        bean.setPublisherId(getPublisherId());
        bean.setPublishTime(getPublishTime());
        bean.setValidateFlag(getValidateFlag());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", siteId:" + getSiteId() + 
        	", fieldId:" + getFieldId() + 
        	", fieldName:" + getFieldName() + 
        	", fieldCode:" + getFieldCode() + 
        	", publisherId:" + getPublisherId() + 
        	", publishTime:" + getPublishTime() + 
        	", validateFlag:" + getValidateFlag() + 
        "}";
    }
}