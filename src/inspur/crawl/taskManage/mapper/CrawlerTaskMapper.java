package inspur.crawl.taskManage.mapper;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.taskManage.pojo.CrawlerTask;
import inspur.crawl.taskManage.pojo.CrawlerTaskCriteria;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CrawlerTaskMapper {
    int countByExample(CrawlerTaskCriteria example);
    
    String getTaskIdByInstaceId(@Param("InstanceId")String InstanceId);

    int deleteByExample(CrawlerTaskCriteria example);

    int deleteByPrimaryKey(Long taskId);

    int insert(CrawlerTask record);

    int insertSelective(CrawlerTask record);

    List<CrawlerTask> selectByExample(CrawlerTaskCriteria example);

    CrawlerTask selectByPrimaryKey(Long taskId);

    int updateByExampleSelective(@Param("record") CrawlerTask record, @Param("example") CrawlerTaskCriteria example);

    int updateByExample(@Param("record") CrawlerTask record, @Param("example") CrawlerTaskCriteria example);

    int updateByPrimaryKeySelective(CrawlerTask record);

    int updateByPrimaryKey(CrawlerTask record);

	int abolishTask(@Param("taskId")Long taskId, @Param("status")int taskAbolishStatus);
    
    List<CrawlerTask> listPageTask(@Param("task")CrawlerTask record,@Param("page")Page page);
    
    List<Map<String, BigDecimal>> countByStatus(@Param("task")CrawlerTask crawlerTask);
    
    List<Map<String, BigDecimal>> countByPeriod(@Param("task")CrawlerTask crawlerTask);
    
    List<CrawlerTask> selectScheduledTask();
}