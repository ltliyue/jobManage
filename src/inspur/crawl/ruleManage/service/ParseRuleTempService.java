package inspur.crawl.ruleManage.service;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.DMin;
import org.springframework.stereotype.Service;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.ruleManage.mapper.ParseRuleMapper;
import inspur.crawl.ruleManage.mapper.ParseRuleTempMapper;
import inspur.crawl.ruleManage.mapper.ParseRuleMapper;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleCriteria;
import inspur.crawl.ruleManage.pojo.ParseRuleTemp;
import inspur.crawl.ruleManage.pojo.ParseRuleTempCriteria;
import inspur.crawl.ruleManage.pojo.ParseRule;

@Service
public class ParseRuleTempService {
	@Resource
	ParseRuleTempMapper rm ;
	
	@Resource
	private HttpSession session;
	
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<ParseRuleTemp> listPageParseRule(ParseRuleTemp record) throws Exception {

		List<ParseRuleTemp> List = rm
	            .listPage(record);
	    return List;
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<ParseRuleTemp> listPageParseRule(ParseRuleTemp record,String taskId) throws Exception {
		record = new ParseRuleTemp();
		record.setTaskId(Long.valueOf(taskId));
		List<ParseRuleTemp> List = rm
	            .listPageByTaskId(record);
	    return List;
	}
	
	/**
	 * 查询所有URL解析抽取规则
	 * @return
	 */
	public List<ParseRuleTemp> queryAll(){
		ParseRuleTempCriteria rc = new ParseRuleTempCriteria();
		List<ParseRuleTemp> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 查询所有启用的
	 * @return
	 */
	public List<ParseRuleTemp> queryEnable(){
		ParseRuleTempCriteria rc = new ParseRuleTempCriteria();
		rc.createCriteria().andEnabledEqualTo((short) 1);
		List<ParseRuleTemp> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 增加URL解析抽取规则
	 */
	public int getAdd(ParseRuleTemp rule){
		String username = session.getAttribute(Sessions.SESSION_USER).toString();
		rule.setCreator(username);
		return rm.insert(rule);
	}
	/**
	 * id 查询URL解析抽取规则
	 */
	public ParseRuleTemp getRule(String id){
		return rm.selectByPrimaryKey(id);
	}
	/**
	 * 修改UR
	 */
	public int getUp(ParseRuleTemp rule){
		ParseRuleTempCriteria rc = new ParseRuleTempCriteria();
		rc.createCriteria().andIdEqualTo(rule.getId());
		return rm.updateByExampleSelective(rule, rc);
	}
	/**
	 * 删除
	 */
	public int getDel(String id){
		return rm.deleteByPrimaryKey(id);
	}
	
   	//使用record传递检索参数 ，只支持按照taskId检索和按照规则名称检索
	public List<PageExtractRule> listPageBySearch(ParseRuleTemp record, Page page) {
		return rm.listPageBySearch(record,page);
	}
}
