package inspur.crawl.dataManage.pojo;

import java.util.Date;

public class CrawlerDataDeliver extends CrawlerDataDeliverKey {
    private String tableName;

    private String status;

    private Date deliverTime;
    /**
     * 需求名称
     */
    private String demandName;
    
	public String getDemandName() {
		return demandName;
	}

	public void setDemandName(String demandName) {
		 this.demandName = demandName == null ? null : demandName.trim();
	}

	public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends CrawlerDataDeliver> T copy(T bean) {
        bean.setDemandId(getDemandId());
        bean.setPublishId(getPublishId());
        bean.setTableName(getTableName());
        bean.setStatus(getStatus());
        bean.setDeliverTime(getDeliverTime());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", demandId:" + getDemandId() + 
        	", publishId:" + getPublishId() + 
        	", tableName:" + getTableName() + 
        	", status:" + getStatus() + 
        	", deliverTime:" + getDeliverTime() + 
        "}";
    }
}