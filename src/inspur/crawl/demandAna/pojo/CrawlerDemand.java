package inspur.crawl.demandAna.pojo;

import java.util.Date;

public class CrawlerDemand {
    private String demandId;

    private String demandName;

    private String demandDetail;

    private String demandStatus;

    private Date demandTime;

    private String demandCreater;

    private String demandFilePath;

    private String dataFeedback;

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId == null ? null : demandId.trim();
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName == null ? null : demandName.trim();
    }

    public String getDemandDetail() {
        return demandDetail;
    }

    public void setDemandDetail(String demandDetail) {
        this.demandDetail = demandDetail == null ? null : demandDetail.trim();
    }

    public String getDemandStatus() {
        return demandStatus;
    }

    public void setDemandStatus(String demandStatus) {
        this.demandStatus = demandStatus == null ? null : demandStatus.trim();
    }

    public Date getDemandTime() {
        return demandTime;
    }

    public void setDemandTime(Date demandTime) {
        this.demandTime = demandTime;
    }

    public String getDemandCreater() {
        return demandCreater;
    }

    public void setDemandCreater(String demandCreater) {
        this.demandCreater = demandCreater == null ? null : demandCreater.trim();
    }

    public String getDemandFilePath() {
        return demandFilePath;
    }

    public void setDemandFilePath(String demandFilePath) {
        this.demandFilePath = demandFilePath == null ? null : demandFilePath.trim();
    }

    public String getDataFeedback() {
        return dataFeedback;
    }

    public void setDataFeedback(String dataFeedback) {
        this.dataFeedback = dataFeedback == null ? null : dataFeedback.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends CrawlerDemand> T copy(T bean) {
        bean.setDemandId(getDemandId());
        bean.setDemandName(getDemandName());
        bean.setDemandDetail(getDemandDetail());
        bean.setDemandStatus(getDemandStatus());
        bean.setDemandTime(getDemandTime());
        bean.setDemandCreater(getDemandCreater());
        bean.setDemandFilePath(getDemandFilePath());
        bean.setDataFeedback(getDataFeedback());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", demandId:" + getDemandId() + 
        	", demandName:" + getDemandName() + 
        	", demandDetail:" + getDemandDetail() + 
        	", demandStatus:" + getDemandStatus() + 
        	", demandTime:" + getDemandTime() + 
        	", demandCreater:" + getDemandCreater() + 
        	", demandFilePath:" + getDemandFilePath() + 
        	", dataFeedback:" + getDataFeedback() + 
        "}";
    }
}