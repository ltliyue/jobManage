package inspur.crawl.ruleManage.service;




import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import inspur.crawl.ruleManage.mapper.ParseRuleMidMapMapper;
import inspur.crawl.ruleManage.mapper.ParseRuleMidMapTempMapper;
import inspur.crawl.ruleManage.mapper.ParseRuleMidMapMapper;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapCriteria;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapTemp;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapTempCriteria;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;

@Service
public class ParseRuleMidMapTempService {
	@Resource
	ParseRuleMidMapTempMapper rm ;
	
	
	/**
	 * 查询所有URL解析抽取规则
	 * @return
	 */
	public List<ParseRuleMidMapTemp> queryAll(){
		ParseRuleMidMapTempCriteria rc = new ParseRuleMidMapTempCriteria();
		List<ParseRuleMidMapTemp> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 增加URL解析抽取规则
	 */
	public int getAdd(ParseRuleMidMapTemp rule){
		return rm.insert(rule);
	}
	/**
	 * id 查询URL解析抽取规则
	 */
	public ParseRuleMidMapTemp getRule(String id){
		return rm.selectByPrimaryKey(id);
	}
	/**
	 * 修改URL解析抽取规则
	 */
	public int getUp(ParseRuleMidMapTemp rule){
		ParseRuleMidMapTempCriteria rc = new ParseRuleMidMapTempCriteria();
		rc.createCriteria().andIdEqualTo(rule.getId());
		return rm.updateByExampleSelective(rule, rc);
	}
	/**
	 * 删除角色
	 */
	public int getDel(String id){
		return rm.deleteByPrimaryKey(id);
	}

	/**
	 * 根据RuleId查找
	 * @param id
	 * @return
	 */
	public List<ParseRuleMidMapTemp> getMidMapByRuleId(String id) {
		ParseRuleMidMapTempCriteria rc = new ParseRuleMidMapTempCriteria();
		rc.createCriteria().andRuleIdEqualTo(id);
		return rm.selectByExample(rc);
	}
}
