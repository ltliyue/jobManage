package inspur.crawl.taskManage.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TaskInstance extends TaskInstanceKey {
    private Long taskId;

    private String publisherId;

    private Date publishTime;

    private Integer status;

    private Date finishTime;

    private BigDecimal totalAmount;

    private BigDecimal handledAmount;

    private BigDecimal handledPercent;

    
    /**
     * 任务名称
     */
    private String taskName;
    
    public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getHandledAmount() {
        return handledAmount;
    }

    public void setHandledAmount(BigDecimal handledAmount) {
        this.handledAmount = handledAmount;
    }

    public BigDecimal getHandledPercent() {
        return handledPercent;
    }

    public void setHandledPercent(BigDecimal handledPercent) {
        this.handledPercent = handledPercent;
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends TaskInstance> T copy(T bean) {
        bean.setInstanceId(getInstanceId());
        bean.setStage(getStage());
        bean.setTaskId(getTaskId());
        bean.setPublisherId(getPublisherId());
        bean.setPublishTime(getPublishTime());
        bean.setStatus(getStatus());
        bean.setFinishTime(getFinishTime());
        bean.setTotalAmount(getTotalAmount());
        bean.setHandledAmount(getHandledAmount());
        bean.setHandledPercent(getHandledPercent());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", instanceId:" + getInstanceId() + 
        	", stage:" + getStage() + 
        	", taskId:" + getTaskId() + 
        	", publisherId:" + getPublisherId() + 
        	", publishTime:" + getPublishTime() + 
        	", status:" + getStatus() + 
        	", finishTime:" + getFinishTime() + 
        	", totalAmount:" + getTotalAmount() + 
        	", handledAmount:" + getHandledAmount() + 
        	", handledPercent:" + getHandledPercent() + 
        "}";
    }
}