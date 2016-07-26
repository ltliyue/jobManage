package inspur.crawl.ruleManage.mapper;

import inspur.crawl.ruleManage.pojo.ElementExtractRule;
import inspur.crawl.ruleManage.pojo.ElementExtractRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ElementExtractRuleMapper {
    int countByExample(ElementExtractRuleCriteria example);

    int deleteByExample(ElementExtractRuleCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(ElementExtractRule record);

    int insertSelective(ElementExtractRule record);

    List<ElementExtractRule> selectByExample(ElementExtractRuleCriteria example);

    ElementExtractRule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ElementExtractRule record, @Param("example") ElementExtractRuleCriteria example);

    int updateByExample(@Param("record") ElementExtractRule record, @Param("example") ElementExtractRuleCriteria example);

    int updateByPrimaryKeySelective(ElementExtractRule record);

    int updateByPrimaryKey(ElementExtractRule record);

	List<ElementExtractRule> getByPageExtractRule(String id);

}