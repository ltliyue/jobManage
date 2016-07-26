package inspur.crawl.codeManage.pojo;

import java.util.Date;

public class StandardCode {
    private String id;

    private String name;

    private String levelCode;

    private String parentId;

    private String publisherId;

    private Date publishTime;

    private String validateFlag;

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

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode == null ? null : levelCode.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
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
    public  <T extends StandardCode> T copy(T bean) {
        bean.setId(getId());
        bean.setName(getName());
        bean.setLevelCode(getLevelCode());
        bean.setParentId(getParentId());
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
        	", name:" + getName() + 
        	", levelCode:" + getLevelCode() + 
        	", parentId:" + getParentId() + 
        	", publisherId:" + getPublisherId() + 
        	", publishTime:" + getPublishTime() + 
        	", validateFlag:" + getValidateFlag() + 
        "}";
    }
}