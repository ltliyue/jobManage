package inspur.crawl.taskManage.mapper;

import inspur.crawl.taskManage.pojo.TaskFilterKey;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TaskFilterMapper {

    int insert(TaskFilterKey record);

    int insertSelective(TaskFilterKey record);
    
    List<TaskFilterKey> selectByTaskId(@Param("taskId")Long taskId);
    
    String selectStringByTaskId(@Param("taskId")Long taskId);

	Integer deleteByTaskId(Long taskId);
}