package inspur.crawl.requirement.mapper;

import inspur.crawl.requirement.pojo.RequireMent;
import inspur.crawl.requirement.pojo.RequireMentQuery;
import inspur.crawl.sysManage.pojo.Account;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RequireMentMapper {
	public List<RequireMent> listPage(RequireMent query);
	public List<RequireMent> listPageAdmin(RequireMent query);
	
	public List<RequireMent> getResourceById(RequireMentQuery query);
	 
	public int saveInfo(RequireMent query);
	
	public int updateInfo(RequireMent query);

	public RequireMent getResourceById(@Param("id") String id);
//    int countByExample(RequireMentCriteria example);
//
//    int deleteByExample(RequireMentCriteria example);
//
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(RequireMent record);
//
//    int insertSelective(RequireMent record);
//
//    List<RequireMent> selectByExample(RequireMentCriteria example);
//
//    RequireMent selectByPrimaryKey(Integer id);
//
//    int updateByExampleSelective(@Param("record") RequireMent record, @Param("example") RequireMentCriteria example);
//
//    int updateByExample(@Param("record") RequireMent record, @Param("example") RequireMentCriteria example);
//
//    int updateByPrimaryKeySelective(RequireMent record);
//
//    int updateByPrimaryKey(RequireMent record);
}