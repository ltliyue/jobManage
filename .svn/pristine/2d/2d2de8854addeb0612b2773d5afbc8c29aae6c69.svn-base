package inspur.crawl.ruleManage.mapper;

import inspur.crawl.ruleManage.pojo.ExtractRule;
import inspur.crawl.ruleManage.pojo.ExtractRuleCriteria;
import inspur.crawl.ruleManage.pojo.ParseRule;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExtractRuleMapper {
    int countByExample(ExtractRuleCriteria example);

    int deleteByExample(ExtractRuleCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(ExtractRule record);

    int insertSelective(ExtractRule record);

    List<ExtractRule> selectByExample(ExtractRuleCriteria example);

    ExtractRule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ExtractRule record, @Param("example") ExtractRuleCriteria example);

    int updateByExample(@Param("record") ExtractRule record, @Param("example") ExtractRuleCriteria example);

    int updateByPrimaryKeySelective(ExtractRule record);

    int updateByPrimaryKey(ExtractRule record);
    
	List<ExtractRule> listPage(ExtractRule record);
	
	List<ExtractRule> listPageByTaskId(ExtractRule recorde);

	String getSiteId(String taskId);

	String getTaskId(String ruleId);
}