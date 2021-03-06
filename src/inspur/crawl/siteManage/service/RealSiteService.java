package inspur.crawl.siteManage.service;

import inspur.crawl.codeManage.mapper.StandardCodeContentMapper;
import inspur.crawl.codeManage.mapper.StandardCodeMapper;
import inspur.crawl.codeManage.pojo.StandardCode;
import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.codeManage.pojo.StandardCodeContentCriteria;
import inspur.crawl.codeManage.pojo.StandardCodeCriteria;
import inspur.crawl.siteManage.data_mapper.RealSiteCodeMapper;
import inspur.crawl.siteManage.data_mapper.SiteItemsCodeMapper;
import inspur.crawl.siteManage.pojo.RealSiteCode;
import inspur.crawl.siteManage.pojo.RealSiteCodeCriteria;
import inspur.crawl.siteManage.pojo.SiteItemsCode;
import inspur.crawl.siteManage.pojo.SiteItemsCodeCriteria;
import inspur.crawl.sysManage.mapper.AccountMapper;
import inspur.crawl.sysManage.pojo.Account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class RealSiteService {
	@Resource
	RealSiteCodeMapper rscm;
	@Resource
	SiteItemsCodeMapper sicm;
	@Resource
	StandardCodeMapper scm;
	@Resource
	StandardCodeContentMapper sccm;
	@Resource
	AccountMapper accountMapper;
	
	public List<RealSiteCode> queryAll(){
		RealSiteCodeCriteria rscc = new RealSiteCodeCriteria();
		rscc.createCriteria().andValidateFlagEqualTo("1").andParentIdEqualTo("0");
		List<RealSiteCode> list = rscm.selectByExample(rscc);
		return list;
	}
	
	public List<RealSiteCode> queryAllSite(){
		RealSiteCodeCriteria rscc = new RealSiteCodeCriteria();
		rscc.createCriteria().andValidateFlagEqualTo("1");
		List<RealSiteCode> list = rscm.selectByExample(rscc);
		return list;
	}
	
	public List<RealSiteCode> queryAll(int startNum, int endNum) {
//		List<RealSiteCode> list = rscm.selectByNum(startNum,endNum);
//		return list;
		return null;
	}
	
	public List<RealSiteCode> queryAll(int startNum, int endNum, String name, String type) {
		List<RealSiteCode> list = rscm.selectByNum(startNum,endNum,name,type);
		return list;
	}
	
	public List<RealSiteCode> selectAll() {
		List<RealSiteCode> list = rscm.selectAll();
		return list;
	}

	public List querySiteType(String pid) {
		List list = rscm.selectSiteType(pid);
		return list;
	}

	public int insertOne(RealSiteCode rsc) {
		return rscm.insert(rsc);
	}

	public int updateOne(RealSiteCode rsc) {
		return rscm.updateByPrimaryKey(rsc);
	}

	public int delSiteById(String id) {
		return rscm.updateValidate(id);
	}

	public List<StandardCodeContent> queryAllScc() {
		StandardCodeContentCriteria scc = new StandardCodeContentCriteria();
		scc.createCriteria().andValidateFlagEqualTo("1");
		List<StandardCodeContent> list = sccm.selectByExample(scc);
		return list;
	}

	public List<StandardCode> queryAllSc() {
		StandardCodeCriteria scc = new StandardCodeCriteria();
		scc.createCriteria().andValidateFlagEqualTo("1");
		List<StandardCode> list = scm.selectByExample(scc);
		return list;
	}

	public int getUp(SiteItemsCode sc) {
		return sicm.updateByPrimaryKey(sc);
	}

	public SiteItemsCode querySiteItem(SiteItemsCode sc) {
		SiteItemsCode list = sicm.selectBySiteId(sc.getSiteId());
		return list;
	}

	public int getIn(SiteItemsCode sc) {
		return sicm.insert(sc);
	}

	public SiteItemsCode querySiteItemBySiteId(String id) {
		return sicm.selectBySiteId(id);
	}

	public List<RealSiteCode> queryChild(String parentId) {
		RealSiteCodeCriteria rscc = new RealSiteCodeCriteria();
		rscc.createCriteria().andParentIdEqualTo(parentId).andValidateFlagEqualTo("1");
		return rscm.selectByExample(rscc);
	}

	public int countRealSiteCode(String name, String type) {
		RealSiteCodeCriteria rscc = new RealSiteCodeCriteria();
		rscc.createCriteria().andValidateFlagEqualTo("1").andParentIdEqualTo("0").andNameLike("%"+name+"%").andSiteTypeLike("%"+type+"%");
		int count = rscm.countByExample(rscc);
		return count;
	}
	
	public Account selectName(String username) {
		Account account = accountMapper.selectByUsername(username);
		return account;
	}
	
	public List<RealSiteCode> queryAllEntrance(){
		RealSiteCodeCriteria rscc = new RealSiteCodeCriteria();
		rscc.createCriteria().andValidateFlagEqualTo("1");
		List<RealSiteCode> list = rscm.selectByExample(rscc);
		return list;
	}

	public RealSiteCode querySiteBySiteId(String siteId) {
		RealSiteCode site = rscm.selectByPrimaryKey(siteId);
		return site;
	}

	public List<StandardCode> querySiteSc(String siteId) {
		List<StandardCode> list = scm.selectBySiteId(siteId);
		return list;
	}

	public List<StandardCodeContent> querySiteScc(String siteId) {
		List<StandardCodeContent> list = sccm.selectBySiteId(siteId);
		return list;
	}

}
