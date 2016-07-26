package inspur.crawl.demandAna.pojo;

import java.util.Date;

public class CrawlerOpinion {
    private String id;

    private String demandId;

    private String type;

    private String feedback;

    private String filePath;

    private String publisherId;

    private Date publishTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId == null ? null : demandId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback == null ? null : feedback.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
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

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends CrawlerOpinion> T copy(T bean) {
        bean.setId(getId());
        bean.setDemandId(getDemandId());
        bean.setType(getType());
        bean.setFeedback(getFeedback());
        bean.setFilePath(getFilePath());
        bean.setPublisherId(getPublisherId());
        bean.setPublishTime(getPublishTime());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", demandId:" + getDemandId() + 
        	", type:" + getType() + 
        	", feedback:" + getFeedback() + 
        	", filePath:" + getFilePath() + 
        	", publisherId:" + getPublisherId() + 
        	", publishTime:" + getPublishTime() + 
        "}";
    }
}