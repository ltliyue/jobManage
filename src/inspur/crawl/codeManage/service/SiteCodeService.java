package inspur.crawl.codeManage.service;

import inspur.crawl.codeManage.mapper.SiteCodeMapper;
import inspur.crawl.codeManage.pojo.SiteCode;
import inspur.crawl.codeManage.pojo.SiteCodeCriteria;
import inspur.crawl.sysManage.mapper.AccountMapper;
import inspur.crawl.sysManage.pojo.Account;
import inspur.crawl.sysManage.pojo.Authority;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class SiteCodeService {
	
	@Resource
	SiteCodeMapper scm ;
	@Resource
	AccountMapper accountMapper;
	
	public List<SiteCode> queryAll() {
		SiteCodeCriteria scc = new SiteCodeCriteria();
//		scc.createCriteria().andValidateFlagEqualTo("1");
		List<SiteCode> list = scm.selectByExample(scc);
		return list;
	}

	public List<SiteCode> queryChild(String parentId) {
		SiteCodeCriteria scc = new SiteCodeCriteria();
		scc.createCriteria().andParentIdEqualTo(parentId).andValidateFlagEqualTo("1");
		List<SiteCode> list = scm.selectByExample(scc);
		return list;
	}

	public List<SiteCode> queryParent() {
		SiteCodeCriteria scc = new SiteCodeCriteria();
		scc.createCriteria().andParentIdEqualTo("0").andValidateFlagEqualTo("1");
		List<SiteCode> list = scm.selectByExample(scc);
		return list;
	}

	public int getAddNext(SiteCode sc) {
		return scm.insert(sc);
	}

	public List<String> getName(String parentId) {
		return scm.getName(parentId);
	}

	public int delSite(String id) {
		return scm.updateById(id);
	}

	public int update(SiteCode sc) {
		return scm.updateByPrimaryKey(sc);
	}

	public List<SiteCode> queryParent(int startNum, int endNum) {
		List<SiteCode> list = scm.selectByNum(startNum,endNum);
		return list;
	}

	public int countSiteCode() {
		SiteCodeCriteria scc = new SiteCodeCriteria();
		scc.createCriteria().andParentIdEqualTo("0").andValidateFlagEqualTo("1");
		int count = scm.countByExample(scc);
		return count;
	}
	
	public SiteCode querySiteCodeById(String id){
		return scm.selectByPrimaryKey(id);
	}
	
	public SiteCode querySiteCodeByIdLower(String id){
		return scm.selectByPrimaryKeyLower(id);
	}
	
	public Account selectName(String username) {
		Account account = accountMapper.selectByUsername(username);
		return account;
	}

}
