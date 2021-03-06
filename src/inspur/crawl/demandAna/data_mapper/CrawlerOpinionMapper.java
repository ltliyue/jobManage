package inspur.crawl.demandAna.data_mapper;

import inspur.crawl.demandAna.pojo.CrawlerOpinion;
import inspur.crawl.demandAna.pojo.CrawlerOpinionCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CrawlerOpinionMapper {
    int countByExample(CrawlerOpinionCriteria example);

    int deleteByExample(CrawlerOpinionCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(CrawlerOpinion record);

    int insertSelective(CrawlerOpinion record);

    List<CrawlerOpinion> selectByExample(CrawlerOpinionCriteria example);

    CrawlerOpinion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CrawlerOpinion record, @Param("example") CrawlerOpinionCriteria example);

    int updateByExample(@Param("record") CrawlerOpinion record, @Param("example") CrawlerOpinionCriteria example);

    int updateByPrimaryKeySelective(CrawlerOpinion record);

    int updateByPrimaryKey(CrawlerOpinion record);

	CrawlerOpinion selectByDemandId(String demandId);

	int updateById(@Param("record") CrawlerOpinion opinion);

	int deleteByDemandId(String id);
}