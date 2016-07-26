package inspur.crawl.codeManage.service;

import inspur.crawl.codeManage.mapper.DemandCodeMapper;
import inspur.crawl.codeManage.pojo.DemandCode;
import inspur.crawl.codeManage.pojo.DemandCodeCriteria;
import inspur.crawl.codeManage.pojo.SiteCodeCriteria;
import inspur.crawl.sysManage.mapper.AccountMapper;
import inspur.crawl.sysManage.pojo.Account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class DemandCodeService {
	@Resource
	DemandCodeMapper dcm;
	@Resource
	AccountMapper accountMapper;
	
	public List<DemandCode> queryAll() {
		DemandCodeCriteria dcc = new DemandCodeCriteria();
		dcc.createCriteria().andValidateFlagEqualTo("1");
		dcc.setOrderByClause("status_code");
		List<DemandCode> list = dcm.selectByExample(dcc);
		return list;
	}

	public int getAddNext(DemandCode dc) {
		return dcm.insert(dc);
	}

	public int update(DemandCode dc) {
		return dcm.updateByPrimaryKey(dc);
	}

	public int delDemand(String id) {
		return dcm.updateById(id);
	}

	public List<DemandCode> queryAll(int startNum, int endNum) {
		List<DemandCode> list = dcm.selectByNum(startNum,endNum);
		return list;
	}

	public int countDemandCode() {
		DemandCodeCriteria dcc = new DemandCodeCriteria();
		dcc.createCriteria().andValidateFlagEqualTo("1");
		int count = dcm.countByExample(dcc);
		return count;
	}

	public Account selectName(String username) {
		Account account = accountMapper.selectByUsername(username);
		return account;
	}
}
