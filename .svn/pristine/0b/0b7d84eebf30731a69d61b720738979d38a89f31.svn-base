package inspur.crawl.ruleManage.mapper;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.PageExtractRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;


public interface PageExtractRuleMapper {
    int countByExample(PageExtractRuleCriteria example);

    int deleteByExample(PageExtractRuleCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(PageExtractRule record);

    int insertSelective(PageExtractRule record);

    List<PageExtractRule> selectByExample(PageExtractRuleCriteria example);

    PageExtractRule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PageExtractRule record, @Param("example") PageExtractRuleCriteria example);

    int updateByExample(@Param("record") PageExtractRule record, @Param("example") PageExtractRuleCriteria example);

    int updateByPrimaryKeySelective(PageExtractRule record);

    int updateByPrimaryKey(PageExtractRule record);
    
    List<PageExtractRule> listPageByTaskId(PageExtractRule record);

	List<PageExtractRule> listPage(PageExtractRule record);
	
	List<PageExtractRule> listPageBySearch(@Param("record")PageExtractRule record,@Param("page") Page page);
}