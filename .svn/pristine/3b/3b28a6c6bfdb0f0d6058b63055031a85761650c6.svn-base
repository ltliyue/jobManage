package inspur.crawl.codeManage.mapper;

import inspur.crawl.codeManage.pojo.DemandCode;
import inspur.crawl.codeManage.pojo.DemandCodeCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DemandCodeMapper {
    int countByExample(DemandCodeCriteria example);

    int deleteByExample(DemandCodeCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(DemandCode record);

    int insertSelective(DemandCode record);

    List<DemandCode> selectByExample(DemandCodeCriteria example);

    DemandCode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DemandCode record, @Param("example") DemandCodeCriteria example);

    int updateByExample(@Param("record") DemandCode record, @Param("example") DemandCodeCriteria example);

    int updateByPrimaryKeySelective(DemandCode record);

    int updateByPrimaryKey(DemandCode record);

	int updateById(String id);

	List<DemandCode> selectByNum(@Param("startNum") int startNum, @Param("endNum") int endNum);
}