package inspur.crawl.codeManage.mapper;

import inspur.crawl.codeManage.pojo.StandardCode;
import inspur.crawl.codeManage.pojo.StandardCodeCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StandardCodeMapper {
    int countByExample(StandardCodeCriteria example);

    int deleteByExample(StandardCodeCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(StandardCode record);

    int insertSelective(StandardCode record);

    List<StandardCode> selectByExample(StandardCodeCriteria example);

    StandardCode selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StandardCode record, @Param("example") StandardCodeCriteria example);

    int updateByExample(@Param("record") StandardCode record, @Param("example") StandardCodeCriteria example);

    int updateByPrimaryKeySelective(StandardCode record);

    int updateByPrimaryKey(StandardCode record);

	List<String> getName(String parentId);

	int updateById(String id);

	List<StandardCode> selectByNum(@Param("startNum") int startNum, @Param("endNum") int endNum);

	List<StandardCode> selectBySiteId(String siteId);
}