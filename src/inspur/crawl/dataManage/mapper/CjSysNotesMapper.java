package inspur.crawl.dataManage.mapper;

import inspur.crawl.dataManage.pojo.CjSysNotes;
import inspur.crawl.dataManage.pojo.CjSysNotesCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CjSysNotesMapper {
    int countByExample(CjSysNotesCriteria example);

    int deleteByExample(CjSysNotesCriteria example);

    int insert(CjSysNotes record);

    int insertSelective(CjSysNotes record);

    List<CjSysNotes> selectByExample(CjSysNotesCriteria example);

    int updateByExampleSelective(@Param("record") CjSysNotes record, @Param("example") CjSysNotesCriteria example);

    int updateByExample(@Param("record") CjSysNotes record, @Param("example") CjSysNotesCriteria example);

	List<CjSysNotes> selectTableByRule(@Param("ruleId") String ruleId, @Param("cs") String cs);
}