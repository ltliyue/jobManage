package inspur.crawl.taskManage.pojo;

import java.util.Date;

public class CrawlerTaskConfig {
    private Long taskId;

    private Integer maxDepth;

    private Integer failureCount;

    private String transmitMode;

    private Long interval;

    private Integer period;

    private Date modifyTime;

    private String modifier;

    private Integer crawlerType;

    private Integer executeJs;

    private Integer crawlerScope;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(Integer maxDepth) {
        this.maxDepth = maxDepth;
    }

    public Integer getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(Integer failureCount) {
        this.failureCount = failureCount;
    }

    public String getTransmitMode() {
        return transmitMode;
    }

    public void setTransmitMode(String transmitMode) {
        this.transmitMode = transmitMode == null ? null : transmitMode.trim();
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Integer getCrawlerType() {
        return crawlerType;
    }

    public void setCrawlerType(Integer crawlerType) {
        this.crawlerType = crawlerType;
    }

    public Integer getExecuteJs() {
        return executeJs;
    }

    public void setExecuteJs(Integer executeJs) {
        this.executeJs = executeJs;
    }

    public Integer getCrawlerScope() {
        return crawlerScope;
    }

    public void setCrawlerScope(Integer crawlerScope) {
        this.crawlerScope = crawlerScope;
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends CrawlerTaskConfig> T copy(T bean) {
        bean.setTaskId(getTaskId());
        bean.setMaxDepth(getMaxDepth());
        bean.setFailureCount(getFailureCount());
        bean.setTransmitMode(getTransmitMode());
        bean.setInterval(getInterval());
        bean.setPeriod(getPeriod());
        bean.setModifyTime(getModifyTime());
        bean.setModifier(getModifier());
        bean.setCrawlerType(getCrawlerType());
        bean.setExecuteJs(getExecuteJs());
        bean.setCrawlerScope(getCrawlerScope());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", taskId:" + getTaskId() + 
        	", maxDepth:" + getMaxDepth() + 
        	", failureCount:" + getFailureCount() + 
        	", transmitMode:" + getTransmitMode() + 
        	", interval:" + getInterval() + 
        	", period:" + getPeriod() + 
        	", modifyTime:" + getModifyTime() + 
        	", modifier:" + getModifier() + 
        	", crawlerType:" + getCrawlerType() + 
        	", executeJs:" + getExecuteJs() + 
        	", crawlerScope:" + getCrawlerScope() + 
        "}";
    }
}