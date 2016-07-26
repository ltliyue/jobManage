package inspur.crawl.ruleManage.service;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.DMin;
import org.springframework.stereotype.Service;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.ruleManage.mapper.LoopVarRuleMapper;
import inspur.crawl.ruleManage.mapper.ParseRuleMapper;
import inspur.crawl.ruleManage.mapper.ParseRuleMapper;
import inspur.crawl.ruleManage.pojo.LoopParseRule;
import inspur.crawl.ruleManage.pojo.LoopVarRule;
import inspur.crawl.ruleManage.pojo.LoopVarRuleCriteria;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleCriteria;
import inspur.crawl.ruleManage.pojo.ParseRule;

@Service
public class LoopVarRuleService {
	@Resource
	LoopVarRuleMapper rm ;
	
	@Resource
	private HttpSession session;
	
	
	/**
	 * 查询所有URL解析抽取规则
	 * @return
	 */
	public List<LoopVarRule> queryAll(){
		LoopVarRuleCriteria rc = new LoopVarRuleCriteria();
		List<LoopVarRule> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 增加URL解析抽取规则
	 */
	public int add(LoopVarRule rule){
		return rm.insert(rule);
	}
	/**
	 * id 查询URL解析抽取规则
	 */
	public LoopVarRule getRule(String id){
		return rm.selectByPrimaryKey(id);
	}
	
	/**
	 * 修改URL解析抽取规则
	 */
	public int getUp(LoopVarRule rule){
		LoopVarRuleCriteria rc = new LoopVarRuleCriteria();
		rc.createCriteria().andIdEqualTo(rule.getId());
		return rm.updateByExampleSelective(rule, rc);
	}
	/**
	 * 删除角色
	 */
	public int getDel(String id){
		return rm.deleteByPrimaryKey(id);
	}
	public List<LoopVarRule> getByLoopParseRuleId(String id) {
		LoopVarRuleCriteria rc = new LoopVarRuleCriteria();
		rc.createCriteria().andLoopParseRuleIdEqualTo(id);
		List<LoopVarRule> list = rm.selectByExample(rc);
		return list;
	}
	
	/**
	 * 查询具有缩略的状态
	 * @param id
	 * @return
	 */
	public List<LoopVarRule> getAbbrByLoopParseRuleId(String id) {
		LoopVarRuleCriteria rc = new LoopVarRuleCriteria();
		rc.createCriteria().andLoopParseRuleIdEqualTo(id);
		List<LoopVarRule> list = rm.selectAbbrByExample(rc);
		return list;
	}
}
