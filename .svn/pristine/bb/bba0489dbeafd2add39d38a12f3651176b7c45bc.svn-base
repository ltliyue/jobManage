package inspur.crawl.codeManage.mapper;

import inspur.crawl.codeManage.pojo.OralceKeyWords;
import inspur.crawl.codeManage.pojo.OralceKeyWordsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OralceKeyWordsMapper {
    int countByExample(OralceKeyWordsCriteria example);

    int deleteByExample(OralceKeyWordsCriteria example);

    int insert(OralceKeyWords record);

    int insertSelective(OralceKeyWords record);

    List<OralceKeyWords> selectByExample(OralceKeyWordsCriteria example);

    int updateByExampleSelective(@Param("record") OralceKeyWords record, @Param("example") OralceKeyWordsCriteria example);

    int updateByExample(@Param("record") OralceKeyWords record, @Param("example") OralceKeyWordsCriteria example);
}