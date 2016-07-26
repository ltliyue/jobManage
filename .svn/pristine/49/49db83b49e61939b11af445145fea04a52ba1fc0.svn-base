package inspur.crawl.ruleManage.mapper;

import inspur.crawl.ruleManage.pojo.LoopVarRule;
import inspur.crawl.ruleManage.pojo.LoopVarRuleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoopVarRuleMapper {
    int countByExample(LoopVarRuleCriteria example);

    int deleteByExample(LoopVarRuleCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(LoopVarRule record);

    int insertSelective(LoopVarRule record);
    
    //因为有CLOB字段，所以提供省略的查询方式，前面500个字符+最后50个字符
    List<LoopVarRule> selectAbbrByExample(LoopVarRuleCriteria example);

    List<LoopVarRule> selectByExample(LoopVarRuleCriteria example);

    LoopVarRule selectByPrimaryKey(String id);
    
    //因为有CLOB字段，所以提供省略的查询方式，前面500个字符+最后50个字符
    LoopVarRule selectAbbrByPrimaryKey(String id);


    int updateByExampleSelective(@Param("record") LoopVarRule record, @Param("example") LoopVarRuleCriteria example);

    int updateByExample(@Param("record") LoopVarRule record, @Param("example") LoopVarRuleCriteria example);

    int updateByPrimaryKeySelective(LoopVarRule record);

    int updateByPrimaryKey(LoopVarRule record);
}