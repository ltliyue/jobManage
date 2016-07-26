package inspur.crawl.demandAna.service;

import inspur.crawl.codeManage.mapper.StandardCodeContentMapper;
import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.demandAna.data_mapper.CrawlerDemandDeliveryMapper;
import inspur.crawl.demandAna.data_mapper.CrawlerDemandMapper;
import inspur.crawl.demandAna.data_mapper.CrawlerOpinionMapper;
import inspur.crawl.demandAna.data_mapper.DemandAnalyseMapper;
import inspur.crawl.demandAna.pojo.CrawlerDemand;
import inspur.crawl.demandAna.pojo.CrawlerDemandCriteria;
import inspur.crawl.demandAna.pojo.CrawlerDemandDelivery;
import inspur.crawl.demandAna.pojo.CrawlerOpinion;
import inspur.crawl.demandAna.pojo.DemandAnalyse;
import inspur.crawl.demandAna.pojo.DemandAnalyseCriteria;
import inspur.crawl.siteManage.data_mapper.SiteItemsCodeMapper;
import inspur.crawl.siteManage.pojo.RealSiteCode;
import inspur.crawl.siteManage.pojo.SiteItemsCode;
import inspur.crawl.sysManage.mapper.AccountMapper;
import inspur.crawl.sysManage.pojo.Account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class DemandAnalyService {
	@Resource
	CrawlerDemandMapper cdm ;
	
	@Resource
	DemandAnalyseMapper dam ;
	
	@Resource
	CrawlerOpinionMapper com;
	
	@Resource
	AccountMapper accountMapper ;
	
	@Resource
	CrawlerDemandDeliveryMapper cddm;
	
	@Resource
	StandardCodeContentMapper sccm;

	public List<CrawlerDemand> queryDemand() {
		CrawlerDemandCriteria cdc = new CrawlerDemandCriteria();
		List<CrawlerDemand> list = cdm.selectByExample(cdc);
		return list;
	}
	
	public Account selectName(String username) {
		Account account = accountMapper.selectByUsername(username);
		return account;
	}
	
	public int insertOne(CrawlerDemand demand) {
		return cdm.insert(demand);
	}

	public int delDemandById(String id) {
		int i = cdm.deleteByPrimaryKey(id);
		if(i==1){
			i = dam.deleteByDemandId(id);
			if(i==1){
				i = com.deleteByDemandId(id);
			}
		}
		return i;
	}

	public CrawlerDemand queryDemandById(String id) {
		CrawlerDemand demand = cdm.selectByPrimaryKey(id);
		return demand;
	}

	public int updateOne(CrawlerDemand demand) {
		return cdm.updateByPrimaryKeySelective(demand);
	}

	public DemandAnalyse queryAnalyseByDemandId(String demandId) {
		DemandAnalyse analyse = dam.selectByDemandId(demandId);
		return analyse;
	}

	public int insertAnalyse(DemandAnalyse analyse) {
		return dam.insert(analyse);
	}

	public DemandAnalyse querySiteById(String demandId) {
		return dam.selectByDemandId(demandId);
	}

	public int updateAnalyse(DemandAnalyse analyse) {
		return dam.updateById(analyse);
	}

	public int insertOpinion(CrawlerOpinion opinion) {
		return com.insert(opinion);
	}

	public CrawlerOpinion queryOpinion(String demandId) {
		return com.selectByDemandId(demandId);
	}

	public int updateOpinion(CrawlerOpinion opinion) {
		return com.updateById(opinion);
	}

	public int updataDemandStatus(String demandStatus, String demandId) {
		return cdm.updateDemandStatus(demandStatus,demandId);
	}

	public List<CrawlerDemandDelivery> queryDeliveryCondition(String siteId, String demandId) {
		return cddm.selectBySiteIdAndDemandId(siteId,demandId);
	}

	public int insertDeliveryCondition(CrawlerDemandDelivery scc) {
		return cddm.insert(scc);
	}

	public int delDeliveryConditionById(String demandId, String siteId) {
		return cddm.deleteByDemandAndSiteId(demandId,siteId);
	}

	public List<StandardCodeContent> querySiteItem(String siteId) {
		List<StandardCodeContent> list = sccm.selectBySiteId(siteId);
		return list;
	}

	public int updateDemandDataFeedback(CrawlerDemand demand) {
		int i = cdm.updateByPrimaryKeySelective(demand);
		return i;
	}

	/*public void queryItemById(String string) {
		sccm.selectBySiteId(siteId);
	}*/
}
