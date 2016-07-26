package inspur.crawl.sysManage.service;



import java.util.List;

import inspur.crawl.sysManage.mapper.AuthorityMapper;
import inspur.crawl.sysManage.pojo.Authority;
import inspur.crawl.sysManage.pojo.AuthorityCriteria;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class DataauthorityService {
	@Resource
	AuthorityMapper am ;
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Authority> queryAll(){
		AuthorityCriteria rc = new AuthorityCriteria();
		List<Authority> list = am.selectByExample(rc);
		return list;
	}
	/**
	 * 查询 父类
	 */
	public List<Authority> queryParent(){
		AuthorityCriteria rc = new AuthorityCriteria();
		rc.createCriteria().andParentidEqualTo("0");
		rc.setOrderByClause("position");
		List<Authority> list = am.selectByExample(rc);
		return list;
	}
	/**
	 * 根据id 查询子类
	 */
	public List<Authority> queryChild(String id){
		AuthorityCriteria rc = new AuthorityCriteria();
		rc.createCriteria().andParentidEqualTo(id);
		rc.setOrderByClause("position");
		List<Authority> list = am.selectByExample(rc);
		return list;
	}
	/**
	 * 根据id 查询所有权限
	 */
	public Authority queryCd(String id){
		AuthorityCriteria rc = new AuthorityCriteria();
		rc.createCriteria().andIdEqualTo(id);
		rc.setOrderByClause("position");
		Authority au = am.selectCd(rc);
		return au;
	}
	/**
	 * 添加 子菜单
	 */
	public int getAddNext(Authority au){
		return am.insert(au);
	}
	public int getPositionUp(String position){
		return am.updatePosition(position);
	}
	public int getUpdate(Authority au){
		AuthorityCriteria rc = new AuthorityCriteria();
		rc.createCriteria().andIdEqualTo(au.getId());
		return am.updateByExampleSelective(au, rc);
	}
	public int getDel(String id){
		AuthorityCriteria rc = new AuthorityCriteria();
		rc.createCriteria().andIdEqualTo(id);
		return am.deleteByExample(rc);
	}
}
