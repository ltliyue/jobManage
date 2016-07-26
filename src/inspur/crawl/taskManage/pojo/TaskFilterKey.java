package inspur.crawl.taskManage.pojo;

public class TaskFilterKey {
    private Long taskId;

    private String filterPattern;

    public TaskFilterKey() {
    	
    }
    
    //构造方法
    public TaskFilterKey(Long taskId, String filter) {
		this.taskId = taskId;
		this.filterPattern = filter;
	}

	public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getFilterPattern() {
        return filterPattern;
    }

    public void setFilterPattern(String filterPattern) {
        this.filterPattern = filterPattern == null ? null : filterPattern.trim();
    }
}