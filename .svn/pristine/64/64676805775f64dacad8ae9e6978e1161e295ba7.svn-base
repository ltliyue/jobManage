package inspur.crawl.taskManage.mapper;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.taskManage.pojo.CrawlerTask;
import inspur.crawl.taskManage.pojo.TaskInstance;
import inspur.crawl.taskManage.pojo.TaskInstanceCriteria;
import inspur.crawl.taskManage.pojo.TaskInstanceKey;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TaskInstanceMapper {
	int countByExample(TaskInstanceCriteria example);

	int deleteByExample(TaskInstanceCriteria example);

	int deleteByPrimaryKey(TaskInstanceKey key);

	int insert(TaskInstance record);

	int insertSelective(TaskInstance record);

	List<TaskInstance> selectByExample(TaskInstanceCriteria example);
	
	
	TaskInstance selectByPrimaryKey(TaskInstanceKey key);

	int updateByExampleSelective(@Param("record") TaskInstance record, @Param("example") TaskInstanceCriteria example);

	int updateByExample(@Param("record") TaskInstance record, @Param("example") TaskInstanceCriteria example);

	int updateByPrimaryKeySelective(TaskInstance record);

	int updateByPrimaryKey(TaskInstance record);

	List<TaskInstance> listPageInstance(@Param("instance") TaskInstance instance, @Param("page") Page page);

	List<TaskInstance> listPageForSelect(@Param("task") CrawlerTask crawlerTask, @Param("page") Page page);

	List<Map<String, BigDecimal>> countByStatus(@Param("task") CrawlerTask crawlerTask);

	List<Map<String, BigDecimal>> countByPeriod(@Param("task") CrawlerTask crawlerTask);
}