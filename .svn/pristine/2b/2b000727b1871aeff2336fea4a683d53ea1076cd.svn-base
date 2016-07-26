package inspur.crawl.ruleManage.mapper;

import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParseRuleMidMapMapper {
    int countByExample(ParseRuleMidMapCriteria example);

    int deleteByExample(ParseRuleMidMapCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(ParseRuleMidMap record);

    int insertSelective(ParseRuleMidMap record);

    List<ParseRuleMidMap> selectByExample(ParseRuleMidMapCriteria example);

    ParseRuleMidMap selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ParseRuleMidMap record, @Param("example") ParseRuleMidMapCriteria example);

    int updateByExample(@Param("record") ParseRuleMidMap record, @Param("example") ParseRuleMidMapCriteria example);

    int updateByPrimaryKeySelective(ParseRuleMidMap record);

    int updateByPrimaryKey(ParseRuleMidMap record);
    
	List<ParseRuleMidMap> listPage(ParseRuleMidMap record);

	List<ParseRuleMidMap> getMidMapByRule(String id);

}