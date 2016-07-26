package inspur.crawl.codeManage.pojo;

import java.util.Date;

public class StandardCodeContent {
    private String id;

    private String fieldCode;

    private String fieldName;

    private String parentId;

    private String fieldDescription;

    private String publisherId;

    private Date publishTime;

    private String validateFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription == null ? null : fieldDescription.trim();
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
    public  <T extends StandardCodeContent> T copy(T bean) {
        bean.setId(getId());
        bean.setFieldCode(getFieldCode());
        bean.setFieldName(getFieldName());
        bean.setParentId(getParentId());
        bean.setFieldDescription(getFieldDescription());
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
        	", fieldCode:" + getFieldCode() + 
        	", fieldName:" + getFieldName() + 
        	", parentId:" + getParentId() + 
        	", fieldDescription:" + getFieldDescription() + 
        	", publisherId:" + getPublisherId() + 
        	", publishTime:" + getPublishTime() + 
        	", validateFlag:" + getValidateFlag() + 
        "}";
    }
}