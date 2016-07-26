package inspur.crawl.codeManage.service;

import inspur.crawl.codeManage.mapper.OralceKeyWordsMapper;
import inspur.crawl.codeManage.mapper.StandardCodeContentMapper;
import inspur.crawl.codeManage.mapper.StandardCodeMapper;
import inspur.crawl.codeManage.pojo.OralceKeyWords;
import inspur.crawl.codeManage.pojo.OralceKeyWordsCriteria;
import inspur.crawl.codeManage.pojo.StandardCode;
import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.codeManage.pojo.StandardCodeContentCriteria;
import inspur.crawl.codeManage.pojo.StandardCodeCriteria;
import inspur.crawl.sysManage.mapper.AccountMapper;
import inspur.crawl.sysManage.pojo.Account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class StandardCodeService {
	@Resource
	StandardCodeMapper scm;
	@Resource
	StandardCodeContentMapper sccmp;
	@Resource
	AccountMapper accountMapper;
	@Resource
	OralceKeyWordsMapper okwm;
	
	public List<StandardCode> queryAll() {
		StandardCodeCriteria scc = new StandardCodeCriteria();
		scc.createCriteria().andValidateFlagEqualTo("1");
		List<StandardCode> list = scm.selectByExample(scc);
		return list;
	}
	
	public List<StandardCodeContent> queryAllField(){
		StandardCodeContentCriteria sccc = new StandardCodeContentCriteria();
		sccc.createCriteria().andValidateFlagEqualTo("1");
		List<StandardCodeContent> list = sccmp.selectByExample(sccc);
		return list;
	}

	public List<StandardCode> queryChild(String parentId) {
		StandardCodeCriteria scc = new StandardCodeCriteria();
		scc.createCriteria().andParentIdEqualTo(parentId).andValidateFlagEqualTo("1");
		List<StandardCode> list = scm.selectByExample(scc);
		return list;
	}
	public List<StandardCodeContent> queryFieldById(String parentId) {
		StandardCodeContentCriteria scc = new StandardCodeContentCriteria();
		scc.createCriteria().andParentIdEqualTo(parentId).andValidateFlagEqualTo("1");
		scc.setOrderByClause("field_name");
		List<StandardCodeContent> list = sccmp.selectByExample(scc);
		return list;
	}

	public List<StandardCode> queryParent() {
		StandardCodeCriteria sccm = new StandardCodeCriteria();
		sccm.createCriteria().andParentIdEqualTo("0").andValidateFlagEqualTo("1");
		List<StandardCode> list = scm.selectByExample(sccm);
		return list;
	}
	
	public int getAddNext(StandardCode sc) {
		return scm.insert(sc);
	}

	public List<String> getName(String parentId) {
		return scm.getName(parentId);
	}

	public int update(StandardCode sc) {
		return scm.updateByPrimaryKey(sc);
	}

	public int delStandard(String id) {
		return scm.updateById(id);
	}

	public int insertField(StandardCodeContent scc) {
		return sccmp.insert(scc);
	}

	public int delFieldById(String id) {
		return sccmp.updateById(id);
	}

	public int updateField(StandardCodeContent scc) {
		return sccmp.updateByPrimaryKey(scc);
	}

	public List<StandardCode> queryParent(int startNum, int endNum) {
		List<StandardCode> list = scm.selectByNum(startNum,endNum);
		return list;
	}

	public int countSandardCode() {
		StandardCodeCriteria sccm = new StandardCodeCriteria();
		sccm.createCriteria().andParentIdEqualTo("0").andValidateFlagEqualTo("1");
		int count = scm.countByExample(sccm);
		return count;
	}

	public Account selectName(String username) {
		Account account = accountMapper.selectByUsername(username);
		return account;
	}
//	public int delField(String parentId, String fieldCode) {
//		return sccmp.deleteByPrimaryKey(key);
//	}

	public List<OralceKeyWords> queryKeyWords() {
		OralceKeyWordsCriteria okwc = new OralceKeyWordsCriteria();
		List<OralceKeyWords> list = okwm.selectByExample(okwc);
		return list;
	}
}
