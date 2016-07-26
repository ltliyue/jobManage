package inspur.crawl.diagnoseCrawl.mapper;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.diagnoseCrawl.pojo.ParseRuleDia;
import inspur.crawl.diagnoseCrawl.pojo.ParseRuleDiaCriteria;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRuleTemp;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParseRuleDiaMapper {
    int countByExample(ParseRuleDiaCriteria example);

    int deleteByExample(ParseRuleDiaCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(ParseRuleDia record);

    int insertSelective(ParseRuleDia record);

    List<ParseRuleDia> selectByExample(ParseRuleDiaCriteria example);

    ParseRuleDia selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ParseRuleDia record, @Param("example") ParseRuleDiaCriteria example);

    int updateByExample(@Param("record") ParseRuleDia record, @Param("example") ParseRuleDiaCriteria example);

    int updateByPrimaryKeySelective(ParseRuleDia record);

    int updateByPrimaryKey(ParseRuleDia record);
    
    List<ParseRuleDia> listPage(ParseRuleDia record);
    
    List<ParseRuleDia> listPageBySearch(@Param("record")ParseRuleDia record, @Param("page")Page page);
}