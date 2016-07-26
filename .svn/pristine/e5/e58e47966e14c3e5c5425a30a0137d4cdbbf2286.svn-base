package inspur.crawl.codeManage.mapper;

import inspur.crawl.codeManage.pojo.SiteCode;
import inspur.crawl.codeManage.pojo.SiteCodeCriteria;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SiteCodeMapper {
    int countByExample(SiteCodeCriteria example);

    int deleteByExample(SiteCodeCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(SiteCode record);

    int insertSelective(SiteCode record);

    List<SiteCode> selectByExample(SiteCodeCriteria example);

    SiteCode selectByPrimaryKey(String id);
    
    SiteCode selectByPrimaryKeyLower (String id);

    int updateByExampleSelective(@Param("record") SiteCode record, @Param("example") SiteCodeCriteria example);

    int updateByExample(@Param("record") SiteCode record, @Param("example") SiteCodeCriteria example);

    int updateByPrimaryKeySelective(SiteCode record);

    int updateByPrimaryKey(SiteCode record);

	List<String> getName(String parentId);

	int updateById(String id);

	List<SiteCode> selectByNum(@Param("startNum") int startNum, @Param("endNum") int endNum);
	
	List<Map> groupCountByTime();
}