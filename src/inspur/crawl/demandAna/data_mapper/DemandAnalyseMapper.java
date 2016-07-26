package inspur.crawl.demandAna.data_mapper;

import inspur.crawl.demandAna.pojo.DemandAnalyse;
import inspur.crawl.demandAna.pojo.DemandAnalyseCriteria;
import inspur.crawl.taskManage.pojo.CrawlerTask;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DemandAnalyseMapper {
    int countByExample(DemandAnalyseCriteria example);

    int deleteByExample(DemandAnalyseCriteria example);

    int insert(DemandAnalyse record);

    int insertSelective(DemandAnalyse record);

    List<DemandAnalyse> selectByExample(DemandAnalyseCriteria example);

    int updateByExampleSelective(@Param("record") DemandAnalyse record, @Param("example") DemandAnalyseCriteria example);

    int updateByExample(@Param("record") DemandAnalyse record, @Param("example") DemandAnalyseCriteria example);

	DemandAnalyse selectByDemandId(String demandId);

	int updateById(@Param("record") DemandAnalyse analyse);

	int deleteByDemandId(String id);

	List<CrawlerTask> selectTasksByDemand(String demandId);
}