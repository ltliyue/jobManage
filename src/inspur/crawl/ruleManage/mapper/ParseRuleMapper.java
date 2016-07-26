package inspur.crawl.ruleManage.mapper;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParseRuleMapper {
    int countByExample(ParseRuleCriteria example);

    int deleteByExample(ParseRuleCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(ParseRule record);

    int insertSelective(ParseRule record);

    List<ParseRule> selectByExample(ParseRuleCriteria example);

    ParseRule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ParseRule record, @Param("example") ParseRuleCriteria example);

    int updateByExample(@Param("record") ParseRule record, @Param("example") ParseRuleCriteria example);

    int updateByPrimaryKeySelective(ParseRule record);

    int updateByPrimaryKey(ParseRule record);
    
    List<ParseRule> listPage(ParseRule record);
	
	List<ParseRule> listPageByTaskId(ParseRule recorde);

	List<PageExtractRule> listPageBySearch(@Param("record")ParseRule record, @Param("page")Page page);

}