package inspur.crawl.dataManage.data_mapper;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.dataManage.pojo.CrawlerDataDeliver;
import inspur.crawl.dataManage.pojo.CrawlerDataDeliverCriteria;
import inspur.crawl.dataManage.pojo.CrawlerDataDeliverKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrawlerDataDeliverMapper {
    int countByExample(CrawlerDataDeliverCriteria example);

    int deleteByExample(CrawlerDataDeliverCriteria example);

    int deleteByPrimaryKey(CrawlerDataDeliverKey key);

    int insert(CrawlerDataDeliver record);

    int insertSelective(CrawlerDataDeliver record);

    List<CrawlerDataDeliver> selectByExample(CrawlerDataDeliverCriteria example);

    CrawlerDataDeliver selectByPrimaryKey(CrawlerDataDeliverKey key);

    int updateByExampleSelective(@Param("record") CrawlerDataDeliver record, @Param("example") CrawlerDataDeliverCriteria example);

    int updateByExample(@Param("record") CrawlerDataDeliver record, @Param("example") CrawlerDataDeliverCriteria example);

    int updateByPrimaryKeySelective(CrawlerDataDeliver record);

    int updateByPrimaryKey(CrawlerDataDeliver record);

	List<CrawlerDataDeliver> listPageForDelvierVersion(@Param("deliver")CrawlerDataDeliver deliver, @Param("page")Page page);
}