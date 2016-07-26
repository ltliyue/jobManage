package inspur.crawl.ruleManage.mapper;

import inspur.crawl.ruleManage.pojo.LoopParseRule;
import inspur.crawl.ruleManage.pojo.LoopParseRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoopParseRuleMapper {
    int countByExample(LoopParseRuleCriteria example);

    int deleteByExample(LoopParseRuleCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(LoopParseRule record);

    int insertSelective(LoopParseRule record);

    List<LoopParseRule> selectByExample(LoopParseRuleCriteria example);

    LoopParseRule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") LoopParseRule record, @Param("example") LoopParseRuleCriteria example);

    int updateByExample(@Param("record") LoopParseRule record, @Param("example") LoopParseRuleCriteria example);

    int updateByPrimaryKeySelective(LoopParseRule record);

    int updateByPrimaryKey(LoopParseRule record);
}