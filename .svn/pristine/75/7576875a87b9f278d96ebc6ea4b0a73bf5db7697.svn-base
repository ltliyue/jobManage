package inspur.crawl.sysManage.service;



import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interceptor.PageHelper;
import inspur.crawl.sysManage.mapper.AccountMapper;
import inspur.crawl.sysManage.pojo.Account;
import inspur.crawl.sysManage.pojo.AccountCriteria;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class DataUserService {
	@Resource
	AccountMapper accountMapper;
	
	public List<Account> listPageUser(Account record) throws Exception {

		List<Account> List = accountMapper
	            .listPage(record);
	    return List;
	}
	public List<Account> getListData() throws Exception {
		AccountCriteria dataUserC = new AccountCriteria();
		dataUserC.setOrderByClause("registertime desc");
		List<Account> list = accountMapper.selectUser(dataUserC);
		return list;
	}
	public int getAdd(Account record) throws Exception {
		return accountMapper.insert(record);
	}
	public int getDel(String id) throws Exception {
		AccountCriteria ac = new AccountCriteria();
		ac.createCriteria().andIdEqualTo(id);
		return accountMapper.deleteByExample(ac);
	}
	public int getUpdate(String id,String roleid){
		AccountCriteria ac = new AccountCriteria();
		ac.createCriteria().andIdEqualTo(id);
		Account a = new Account();
		a.setRoleid(roleid);
		return accountMapper.updateByExampleSelective(a, ac);
	}
	public Account getAccount(String id){
		Account a = accountMapper.selectByAccount(id);
		return a;
	}
	public Account getUserName(String username){
		Account a = accountMapper.selectByUsername(username);
		return a;
	}
	public int getUpdate1(Account a){
		AccountCriteria ac = new AccountCriteria();
		ac.createCriteria().andIdEqualTo(a.getId());
		return accountMapper.updateByExampleSelective(a, ac);
	}
}
