package inspur.crawl.ruleManage.service;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.DMin;
import org.springframework.stereotype.Service;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.ruleManage.mapper.LoopParseRuleMapper;
import inspur.crawl.ruleManage.mapper.ParseRuleMapper;
import inspur.crawl.ruleManage.mapper.ParseRuleMapper;
import inspur.crawl.ruleManage.pojo.LoopParseRule;
import inspur.crawl.ruleManage.pojo.LoopParseRuleCriteria;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleCriteria;
import inspur.crawl.ruleManage.pojo.ParseRule;

@Service
public class LoopParseRuleService {
	@Resource
	LoopParseRuleMapper rm ;
	
	@Resource
	private HttpSession session;
	
	
	
	/**
	 * 查询所有URL解析抽取规则
	 * @return
	 */
	public List<LoopParseRule> queryAll(){
		LoopParseRuleCriteria rc = new LoopParseRuleCriteria();
		List<LoopParseRule> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 增加URL解析抽取规则
	 */
	public int add(LoopParseRule rule){
		String username = session.getAttribute(Sessions.SESSION_USER).toString();
		return rm.insert(rule);
	}
	/**
	 * id 查询URL解析抽取规则
	 */
	public LoopParseRule get(String id){
		return rm.selectByPrimaryKey(id);
	}
	/**
	 * 修改URL解析抽取规则
	 */
	public int update(LoopParseRule rule){
		LoopParseRuleCriteria rc = new LoopParseRuleCriteria();
		rc.createCriteria().andIdEqualTo(rule.getId());
		return rm.updateByExampleSelective(rule, rc);
	}
	/**
	 * 删除角色
	 */
	public int getDel(String id){
		return rm.deleteByPrimaryKey(id);
	}
	public List<LoopParseRule> getByParseRuleId(String id) {
		LoopParseRuleCriteria rc = new LoopParseRuleCriteria();
		rc.createCriteria().andParseRuleIdEqualTo(id);
		List<LoopParseRule> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 通过外键进行更新
	 * @param rule
	 * @return
	 */
	public int updateByFk(LoopParseRule rule) {
		LoopParseRuleCriteria rc = new LoopParseRuleCriteria();
		rc.createCriteria().andParseRuleIdEqualTo(rule.getParseRuleId());
		return rm.updateByExampleSelective(rule, rc);
	}
}
