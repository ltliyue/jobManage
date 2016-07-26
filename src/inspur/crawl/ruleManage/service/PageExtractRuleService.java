package inspur.crawl.ruleManage.service;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.ruleManage.mapper.ExtractRuleMapper;
import inspur.crawl.ruleManage.mapper.PageExtractRuleMapper;
import inspur.crawl.ruleManage.pojo.ExtractRule;
import inspur.crawl.ruleManage.pojo.ExtractRuleCriteria;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.PageExtractRuleCriteria;
import inspur.crawl.ruleManage.pojo.ParseRule;

@Service
public class PageExtractRuleService {
	@Resource
	PageExtractRuleMapper rm ;
	
	@Resource
	private HttpSession session;
	
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<PageExtractRule> listPageExtractRule(PageExtractRule record) throws Exception {

		List<PageExtractRule> List = rm
	            .listPage(record);
	    return List;
	}
	
	
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<PageExtractRule> listPageBySearch(PageExtractRule record,Page page) throws Exception {

		List<PageExtractRule> List = rm
	            .listPageBySearch(record,page);
	    return List;
	}
	
	
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<PageExtractRule> listPageExtractRule(PageExtractRule record,String taskId) throws Exception {
		record = new PageExtractRule();
		record.setTaskId(taskId);
		List<PageExtractRule> List = rm
	            .listPageByTaskId(record);
	    return List;
	}
	
	/**
	 * 查询所有内容抽取规则
	 * @return
	 */
	public List<PageExtractRule> queryAll(){
		PageExtractRuleCriteria rc = new PageExtractRuleCriteria();
		List<PageExtractRule> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<PageExtractRule> queryTaskId(String id) throws Exception {

		PageExtractRuleCriteria rc = new PageExtractRuleCriteria();
		rc.createCriteria().andTaskIdEqualTo(id);
		List<PageExtractRule> list = rm.selectByExample(rc);
	    return list;
	}
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<PageExtractRule> queryEnable(){
		PageExtractRuleCriteria rc = new PageExtractRuleCriteria();
		rc.createCriteria().andEnabledEqualTo((short) 1);
		List<PageExtractRule> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 增加内容抽取规则
	 */
	public int getAdd(PageExtractRule rule){
		String username = session.getAttribute(Sessions.SESSION_USER).toString();
		rule.setCreator(username);
		return rm.insert(rule);
	}
	/**
	 * id 查询内容抽取规则
	 */
	public PageExtractRule getRule(String id){
		return rm.selectByPrimaryKey(id);
	}
	/**
	 * 修改内容抽取规则
	 */
	public int getUp(PageExtractRule rule){
		PageExtractRuleCriteria rc = new PageExtractRuleCriteria();
		rc.createCriteria().andIdEqualTo(rule.getId());
		return rm.updateByExampleSelective(rule, rc);
	}
	/**
	 * 删除角色
	 */
	public int getDel(String id){
		return rm.deleteByPrimaryKey(id);
	}
}
