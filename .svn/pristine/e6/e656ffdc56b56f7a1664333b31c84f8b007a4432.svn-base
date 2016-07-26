package inspur.crawl.ruleManage.service;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.DMin;
import org.springframework.stereotype.Service;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.ruleManage.mapper.ParseRuleMapper;
import inspur.crawl.ruleManage.mapper.ParseRuleMapper;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleCriteria;
import inspur.crawl.ruleManage.pojo.ParseRule;

@Service
public class ParseRuleService {
	@Resource
	ParseRuleMapper rm ;
	
	@Resource
	private HttpSession session;
	
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<ParseRule> listPageParseRule(ParseRule record) throws Exception {

		List<ParseRule> List = rm
	            .listPage(record);
	    return List;
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<ParseRule> listPageParseRule(ParseRule record,String taskId) throws Exception {
		record = new ParseRule();
		record.setTaskId(taskId);
		List<ParseRule> List = rm
	            .listPageByTaskId(record);
	    return List;
	}
	
	/**
	 * 查询所有URL解析抽取规则
	 * @return
	 */
	public List<ParseRule> queryAll(){
		ParseRuleCriteria rc = new ParseRuleCriteria();
		List<ParseRule> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<ParseRule> queryEnable(){
		ParseRuleCriteria rc = new ParseRuleCriteria();
		rc.createCriteria().andEnabledEqualTo((short) 1);
		List<ParseRule> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 增加URL解析抽取规则
	 */
	public int getAdd(ParseRule rule){
		String username = session.getAttribute(Sessions.SESSION_USER).toString();
		rule.setCreator(username);
		return rm.insert(rule);
	}
	/**
	 * id 查询URL解析抽取规则
	 */
	public ParseRule getRule(String id){
		return rm.selectByPrimaryKey(id);
	}
	/**
	 * 修改URL解析抽取规则
	 */
	public int getUp(ParseRule rule){
		ParseRuleCriteria rc = new ParseRuleCriteria();
		rc.createCriteria().andIdEqualTo(rule.getId());
		return rm.updateByExampleSelective(rule, rc);
	}
	/**
	 * 删除角色
	 */
	public int getDel(String id){
		return rm.deleteByPrimaryKey(id);
	}

	public List<PageExtractRule> listPageBySearch(ParseRule record, Page page) {
		return rm.listPageBySearch(record,page);
	}
}
