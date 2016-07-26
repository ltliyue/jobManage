package inspur.crawl.ruleManage.service;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import inspur.crawl.ruleManage.mapper.ElementExtractRuleMapper;
import inspur.crawl.ruleManage.mapper.ExtractRuleMapper;
import inspur.crawl.ruleManage.pojo.ElementExtractRule;
import inspur.crawl.ruleManage.pojo.ElementExtractRuleCriteria;
import inspur.crawl.ruleManage.pojo.ExtractRule;
import inspur.crawl.ruleManage.pojo.ExtractRuleCriteria;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;

@Service
public class ElementExtractRuleService {
	@Resource
	ElementExtractRuleMapper rm ;
	
	
	/**
	 * 查询所有内容抽取规则
	 * @return
	 */
	public List<ElementExtractRule> queryAll(){
		ElementExtractRuleCriteria rc = new ElementExtractRuleCriteria();
		List<ElementExtractRule> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<ElementExtractRule> queryTaskId(String id) throws Exception {

		ElementExtractRuleCriteria rc = new ElementExtractRuleCriteria();
		//rc.createCriteria().andTaskIdEqualTo(id);
		List<ElementExtractRule> list = rm.selectByExample(rc);
	    return list;
	}
	/**
	 * 增加内容抽取规则
	 */
	public int getAdd(ElementExtractRule rule){
		return rm.insert(rule);
	}
	/**
	 * id 查询内容抽取规则
	 */
	public ElementExtractRule getRule(String id){
		return rm.selectByPrimaryKey(id);
	}
	/**
	 * 修改内容抽取规则
	 */
	public int getUp(ElementExtractRule rule){
		ElementExtractRuleCriteria rc = new ElementExtractRuleCriteria();
		rc.createCriteria().andIdEqualTo(rule.getId());
		return rm.updateByExampleSelective(rule, rc);
	}
	/**
	 * 删除角色
	 */
	public int getDel(String id){
		return rm.deleteByPrimaryKey(id);
	}
	
	
	public List<ElementExtractRule> getByPageExtractRule(String id) {
		List<ElementExtractRule> ruleList = rm.getByPageExtractRule(id);
		return ruleList;
	}
	public synchronized void deleteByTypeAndId(String pageExtractRuleId, String extractType) {
		ElementExtractRuleCriteria rc = new ElementExtractRuleCriteria();
		rc.createCriteria().andPageExtractRuleIdEqualTo(pageExtractRuleId).andExtractTypeEqualTo(extractType);
		rm.deleteByExample(rc);
	}
}
