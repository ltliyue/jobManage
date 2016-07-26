package inspur.crawl.dataManage.data_mapper;

import inspur.crawl.dataManage.pojo.DataSource;
import inspur.crawl.dataManage.pojo.DataSourceCriteria;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DataSourceMapper {
    int countByExample(DataSourceCriteria example);

    int deleteByExample(DataSourceCriteria example);

    int insert(DataSource record);

    int insertSelective(DataSource record);
    int zdysql(@Param("sql") String sql);
    int createTable(@Param("table") String table);
    List<String> selectUserTables(@Param("bm") String bm);
    List<String> selectColumn(@Param("bm") String bm);
    String selectUserTableNum(@Param("bm") String bm,@Param("where") String where);
    int disColumn(@Param("bm") String bm,@Param("where") String where);
    List<Map<String,Object>> selectListTable(@Param("bm") String bm,@Param("startnum") int startnum,@Param("endnum") int endnum,@Param("where") String where);
    int plupdateTable(@Param("bm") String bm,@Param("set") String set,@Param("where") String where);
    int pldelTable(@Param("bm") String bm,@Param("where") String where);
    List<DataSource> selectByExample(DataSourceCriteria example);

    int updateByExampleSelective(@Param("record") DataSource record, @Param("example") DataSourceCriteria example);

    int updateByExample(@Param("record") DataSource record, @Param("example") DataSourceCriteria example);
}