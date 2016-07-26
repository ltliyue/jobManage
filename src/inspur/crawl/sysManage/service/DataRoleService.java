package inspur.crawl.sysManage.service;



import java.util.List;

import inspur.crawl.sysManage.mapper.RoleMapper;
import inspur.crawl.sysManage.pojo.Role;
import inspur.crawl.sysManage.pojo.RoleCriteria;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public class DataRoleService {
	@Resource
	RoleMapper rm ;
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> queryAll(){
		RoleCriteria rc = new RoleCriteria();
		List<Role> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> queryEnable(){
		RoleCriteria rc = new RoleCriteria();
		rc.createCriteria().andEnableEqualTo((short)1);
		List<Role> list = rm.selectByExample(rc);
		return list;
	}
	/**
	 * 增加角色
	 */
	public int getAdd(Role role){
		return rm.insert(role);
	}
	/**
	 * id 查询角色
	 */
	public Role getrole(String id){
		return rm.selectByPrimaryKey(id);
	}
	/**
	 * 修改角色
	 */
	public int getUp(Role role){
		RoleCriteria rc = new RoleCriteria();
		rc.createCriteria().andIdEqualTo(role.getId());
		return rm.updateByExampleSelective(role, rc);
	}
	/**
	 * 删除角色
	 */
	public int getDel(String id){
		return rm.deleteByPrimaryKey(id);
	}
}
