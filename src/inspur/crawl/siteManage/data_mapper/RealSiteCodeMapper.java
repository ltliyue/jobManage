package inspur.crawl.siteManage.data_mapper;

import inspur.crawl.siteManage.pojo.RealSiteCode;
import inspur.crawl.siteManage.pojo.RealSiteCodeCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RealSiteCodeMapper {
    int countByExample(RealSiteCodeCriteria example);

    int deleteByExample(RealSiteCodeCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(RealSiteCode record);

    int insertSelective(RealSiteCode record);

    List<RealSiteCode> selectByExample(RealSiteCodeCriteria example);

    RealSiteCode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RealSiteCode record, @Param("example") RealSiteCodeCriteria example);

    int updateByExample(@Param("record") RealSiteCode record, @Param("example") RealSiteCodeCriteria example);

    int updateByPrimaryKeySelective(RealSiteCode record);

    int updateByPrimaryKey(RealSiteCode record);
    
    List<?> selectSiteType(String pid);

	int updateValidate(String id);

	List<RealSiteCode> selectByNum(@Param("startNum") int startNum, @Param("endNum") int endNum, @Param("name") String name, @Param("type") String type);
	
	List<RealSiteCode> selectAll();

//	List<RealSiteCode> selectByNum(int startNum, int endNum, String string);
}