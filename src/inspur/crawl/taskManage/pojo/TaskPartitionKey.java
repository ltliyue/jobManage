package inspur.crawl.taskManage.pojo;

public class TaskPartitionKey {
    private Long taskId;

    private Integer partitionId;

    public TaskPartitionKey() {
    	
    }
    
    public TaskPartitionKey(Integer partitionId) {
    	this.partitionId = partitionId;
    }
    
    public TaskPartitionKey(Long taskId,Integer partitionId) {
    	this.taskId = taskId;
    	this.partitionId = partitionId;
    }
    
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }
}