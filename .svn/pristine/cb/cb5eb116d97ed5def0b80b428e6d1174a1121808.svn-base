package inspur.crawl.taskManage.mapper;

import inspur.crawl.taskManage.pojo.TaskPartitionKey;
import org.apache.ibatis.annotations.Param;

public interface TaskPartitionMapper {
  
    int insert(TaskPartitionKey record);

    int insertSelective(TaskPartitionKey record);

	String selectByTaskId(@Param("taskId")Long taskId);

	Integer deleteByTaskId(Long taskId);
}