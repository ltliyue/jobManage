package inspur.crawl.codeManage.pojo;

import java.util.Date;

public class DemandCode {
    private String id;

    private String statusName;

    private String statusCode;

    private String publisherId;

    private Date publishTime;

    private String validateFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName == null ? null : statusName.trim();
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode == null ? null : statusCode.trim();
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
    public  <T extends DemandCode> T copy(T bean) {
        bean.setId(getId());
        bean.setStatusName(getStatusName());
        bean.setStatusCode(getStatusCode());
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
        	", statusName:" + getStatusName() + 
        	", statusCode:" + getStatusCode() + 
        	", publisherId:" + getPublisherId() + 
        	", publishTime:" + getPublishTime() + 
        	", validateFlag:" + getValidateFlag() + 
        "}";
    }
}