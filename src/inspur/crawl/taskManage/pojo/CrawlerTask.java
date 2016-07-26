package inspur.crawl.taskManage.pojo;

import inspur.crawl.common.interceptor.Page;

import java.util.Date;

public class CrawlerTask {
    private Long taskId;

    private String taskName;

    private String url;

    private Integer status;

    private String currentInstance;

    private Date lastOperateTime;

    private Date createTime;

    private String creator;

    private String taskDescribe;
    Page page ;
    
    public Page getPage() {
		return page;
	}

    private String siteId;

	public void setPage(Page page) {
		this.page = page;
	}

	/**
     * 分区
     */
    private String partitions;
    /**
     * url过滤规则
     */
    private String urlFilters;
    /**
     * 使用分区的分组
     */
    private String groups;
    
    /**
     * 采集频率类型
     */
    private Integer period;
    
    /**
     * 所属需求
     */
    private String demandId;
    
	public String getDemandId() {
		return demandId;
	}

	public void setDemandId(String demandId) {
		this.demandId = demandId;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getPartitions() {
		return partitions;
	}

	public void setPartitions(String partitions) {
		this.partitions = partitions;
	}

	public String getUrlFilters() {
		return urlFilters;
	}

	public void setUrlFilters(String urlFilters) {
		this.urlFilters = urlFilters;
	}
	
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCurrentInstance() {
        return currentInstance;
    }

    public void setCurrentInstance(String currentInstance) {
        this.currentInstance = currentInstance == null ? null : currentInstance.trim();
    }

    public Date getLastOperateTime() {
        return lastOperateTime;
    }

    public void setLastOperateTime(Date lastOperateTime) {
        this.lastOperateTime = lastOperateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getTaskDescribe() {
        return taskDescribe;
    }

    public void setTaskDescribe(String taskDescribe) {
        this.taskDescribe = taskDescribe == null ? null : taskDescribe.trim();
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId == null ? null : siteId.trim();
    }

    /** 
     * 鎷疯礉锛屽皢瀵硅薄涓殑瀛楁鍏ㄩ儴鎷疯礉鍒板瓙瀵硅薄涓�
     * @param bean 鎺ユ敹瀵硅薄鐨勫瓙绫�
     * @return 鎷疯礉瀹屾垚鍚庣殑瀛愮被
     */ 
    public  <T extends CrawlerTask> T copy(T bean) {
        bean.setTaskId(getTaskId());
        bean.setTaskName(getTaskName());
        bean.setUrl(getUrl());
        bean.setStatus(getStatus());
        bean.setCurrentInstance(getCurrentInstance());
        bean.setLastOperateTime(getLastOperateTime());
        bean.setCreateTime(getCreateTime());
        bean.setCreator(getCreator());
        bean.setTaskDescribe(getTaskDescribe());
        bean.setSiteId(getSiteId());
        return bean;
    }

    /** 
     * 鏍煎紡鍖栨樉绀�
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", taskId:" + getTaskId() + 
        	", taskName:" + getTaskName() + 
        	", url:" + getUrl() + 
        	", status:" + getStatus() + 
        	", currentInstance:" + getCurrentInstance() + 
        	", lastOperateTime:" + getLastOperateTime() + 
        	", createTime:" + getCreateTime() + 
        	", creator:" + getCreator() + 
        	", taskDescribe:" + getTaskDescribe() + 
        	", siteId:" + getSiteId() + 
        "}";
    }
}
