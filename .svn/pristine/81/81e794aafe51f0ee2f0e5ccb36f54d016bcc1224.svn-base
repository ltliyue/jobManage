package inspur.crawl.siteManage.data_mapper;

import inspur.crawl.siteManage.pojo.SiteItemsCode;
import inspur.crawl.siteManage.pojo.SiteItemsCodeCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SiteItemsCodeMapper {
    int countByExample(SiteItemsCodeCriteria example);

    int deleteByExample(SiteItemsCodeCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SiteItemsCode record);

    int insertSelective(SiteItemsCode record);

    List<SiteItemsCode> selectByExample(SiteItemsCodeCriteria example);

    SiteItemsCode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SiteItemsCode record, @Param("example") SiteItemsCodeCriteria example);

    int updateByExample(@Param("record") SiteItemsCode record, @Param("example") SiteItemsCodeCriteria example);

    int updateByPrimaryKeySelective(SiteItemsCode record);

    int updateByPrimaryKey(SiteItemsCode record);

	SiteItemsCode selectBySiteId(String siteId);
}