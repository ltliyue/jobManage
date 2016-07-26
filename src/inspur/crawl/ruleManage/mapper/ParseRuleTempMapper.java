package inspur.crawl.ruleManage.mapper;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleTemp;
import inspur.crawl.ruleManage.pojo.ParseRuleTempCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParseRuleTempMapper {
    int countByExample(ParseRuleTempCriteria example);

    int deleteByExample(ParseRuleTempCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(ParseRuleTemp record);

    int insertSelective(ParseRuleTemp record);

    List<ParseRuleTemp> selectByExample(ParseRuleTempCriteria example);

    ParseRuleTemp selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ParseRuleTemp record, @Param("example") ParseRuleTempCriteria example);

    int updateByExample(@Param("record") ParseRuleTemp record, @Param("example") ParseRuleTempCriteria example);

    int updateByPrimaryKeySelective(ParseRuleTemp record);

    int updateByPrimaryKey(ParseRuleTemp record);
    
    List<ParseRuleTemp> listPage(ParseRuleTemp record);
	
   	List<ParseRuleTemp> listPageByTaskId(ParseRuleTemp recorde);
   	
   	//使用record传递检索参数 ，只支持按照taskId检索和按照规则名称检索
	List<PageExtractRule> listPageBySearch(@Param("record")ParseRuleTemp record, @Param("page")Page page);

}