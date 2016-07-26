package inspur.crawl.ruleManage.mapper;

import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapTemp;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapTempCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParseRuleMidMapTempMapper {
    int countByExample(ParseRuleMidMapTempCriteria example);

    int deleteByExample(ParseRuleMidMapTempCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(ParseRuleMidMapTemp record);

    int insertSelective(ParseRuleMidMapTemp record);

    List<ParseRuleMidMapTemp> selectByExample(ParseRuleMidMapTempCriteria example);

    ParseRuleMidMapTemp selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ParseRuleMidMapTemp record, @Param("example") ParseRuleMidMapTempCriteria example);

    int updateByExample(@Param("record") ParseRuleMidMapTemp record, @Param("example") ParseRuleMidMapTempCriteria example);

    int updateByPrimaryKeySelective(ParseRuleMidMapTemp record);

    int updateByPrimaryKey(ParseRuleMidMapTemp record);
    
    List<ParseRuleMidMap> getMidMapByRuleId(String id);
}