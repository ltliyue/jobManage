package inspur.crawl.codeManage.mapper;

import inspur.crawl.codeManage.pojo.StandardCodeContent;
import inspur.crawl.codeManage.pojo.StandardCodeContentCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StandardCodeContentMapper {
    int countByExample(StandardCodeContentCriteria example);

    int deleteByExample(StandardCodeContentCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(StandardCodeContent record);

    int insertSelective(StandardCodeContent record);
    
    List<StandardCodeContent> selectByNum(@Param("startNum") int startNum, @Param("endNum") int endNum);

    List<StandardCodeContent> selectByExample(StandardCodeContentCriteria example);

    StandardCodeContent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") StandardCodeContent record, @Param("example") StandardCodeContentCriteria example);

    int updateByExample(@Param("record") StandardCodeContent record, @Param("example") StandardCodeContentCriteria example);

    int updateByPrimaryKeySelective(StandardCodeContent record);

    int updateByPrimaryKey(StandardCodeContent record);

	int updateById(String id);

	List<StandardCodeContent> selectBySiteId(String siteId);
}