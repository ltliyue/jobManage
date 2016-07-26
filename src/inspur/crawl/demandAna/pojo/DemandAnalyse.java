package inspur.crawl.demandAna.pojo;

import java.util.Date;

public class DemandAnalyse {
    private String demandId;

    private String id;

    private String resultDescription;

    private String priority;

    private String siteId;

    private String filePath;

    private String publisher;

    private Date publishTime;

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId == null ? null : demandId.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription == null ? null : resultDescription.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId == null ? null : siteId.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
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
    public  <T extends DemandAnalyse> T copy(T bean) {
        bean.setDemandId(getDemandId());
        bean.setId(getId());
        bean.setResultDescription(getResultDescription());
        bean.setPriority(getPriority());
        bean.setSiteId(getSiteId());
        bean.setFilePath(getFilePath());
        bean.setPublisher(getPublisher());
        bean.setPublishTime(getPublishTime());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", demandId:" + getDemandId() + 
        	", id:" + getId() + 
        	", resultDescription:" + getResultDescription() + 
        	", priority:" + getPriority() + 
        	", siteId:" + getSiteId() + 
        	", filePath:" + getFilePath() + 
        	", publisher:" + getPublisher() + 
        	", publishTime:" + getPublishTime() + 
        "}";
    }
}