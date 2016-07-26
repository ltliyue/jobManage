package inspur.crawl.ruleManage.service;




import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import inspur.crawl.ruleManage.mapper.ParseRuleMidMapMapper;
import inspur.crawl.ruleManage.mapper.ParseRuleMidMapMapper;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMapCriteria;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;

@Service
public class ParseRuleMidMapService {
	@Resource
	ParseRuleMidMapMapper rm ;
	
	/**
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<ParseRuleMidMap> listPageParseRuleMidMap(ParseRuleMidMap record) throws Exception {

		List<ParseRuleMidMap> List = rm
	            .listPage(record);
	    return List;
	}
	
	/**
	 * 查询所有URL解析抽取规则
	 * @return
	 */
	public List<ParseRuleMidMap> queryAll(){
		ParseRuleMidMapCriteria rc = new ParseRuleMidMapCriteria();
		List<ParseRuleMidMap> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 增加URL解析抽取规则
	 */
	public int getAdd(ParseRuleMidMap rule){
		return rm.insert(rule);
	}
	/**
	 * id 查询URL解析抽取规则
	 */
	public ParseRuleMidMap getRule(String id){
		return rm.selectByPrimaryKey(id);
	}
	/**
	 * 修改URL解析抽取规则
	 */
	public int getUp(ParseRuleMidMap rule){
		ParseRuleMidMapCriteria rc = new ParseRuleMidMapCriteria();
		rc.createCriteria().andIdEqualTo(rule.getId());
		return rm.updateByExampleSelective(rule, rc);
	}
	/**
	 * 删除角色
	 */
	public int getDel(String id){
		return rm.deleteByPrimaryKey(id);
	}

	public List<ParseRuleMidMap> getMidMapByRule(String id) {
		List<ParseRuleMidMap> midMapList = rm.getMidMapByRule(id);
		return midMapList;
	}
}
