package inspur.crawl.ruleManage.service;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import inspur.crawl.ruleManage.mapper.ExtractRuleMapper;
import inspur.crawl.ruleManage.pojo.ExtractRule;
import inspur.crawl.ruleManage.pojo.ExtractRuleCriteria;
import inspur.crawl.ruleManage.pojo.ParseRule;

@Service
public class ExtractRuleService {
	@Resource
	ExtractRuleMapper rm ;
	
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<ExtractRule> listPageExtractRule(ExtractRule record) throws Exception {

		List<ExtractRule> List = rm
	            .listPage(record);
	    return List;
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<ExtractRule> listPageExtractRule(ExtractRule record,String taskId) throws Exception {
		record = new ExtractRule();
		record.setTaskId(taskId);
		List<ExtractRule> List = rm
	            .listPageByTaskId(record);
	    return List;
	}
	
	/**
	 * 查询所有内容抽取规则
	 * @return
	 */
	public List<ExtractRule> queryAll(){
		ExtractRuleCriteria rc = new ExtractRuleCriteria();
		List<ExtractRule> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<ExtractRule> queryTaskId(String id) throws Exception {

		ExtractRuleCriteria rc = new ExtractRuleCriteria();
		rc.createCriteria().andTaskIdEqualTo(id);
		List<ExtractRule> list = rm.selectByExample(rc);
	    return list;
	}
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<ExtractRule> queryEnable(){
		ExtractRuleCriteria rc = new ExtractRuleCriteria();
		rc.createCriteria().andEnabledEqualTo((short) 1);
		List<ExtractRule> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 增加内容抽取规则
	 */
	public int getAdd(ExtractRule rule){
		return rm.insert(rule);
	}
	/**
	 * id 查询内容抽取规则
	 */
	public ExtractRule getRule(String id){
		return rm.selectByPrimaryKey(id);
	}
	/**
	 * 修改内容抽取规则
	 */
	public int getUp(ExtractRule rule){
		ExtractRuleCriteria rc = new ExtractRuleCriteria();
		rc.createCriteria().andIdEqualTo(rule.getId());
		return rm.updateByExampleSelective(rule, rc);
	}
	/**
	 * 删除角色
	 */
	public int getDel(String id){
		return rm.deleteByPrimaryKey(id);
	}

	public String getSiteId(String taskId) {
		return rm.getSiteId(taskId);
	}

	public String getTaskId(String id) {
		return rm.getTaskId(id);
	}
}
