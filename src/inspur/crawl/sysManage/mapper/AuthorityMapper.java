package inspur.crawl.sysManage.mapper;

import inspur.crawl.sysManage.pojo.Authority;
import inspur.crawl.sysManage.pojo.AuthorityCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthorityMapper {
    int countByExample(AuthorityCriteria example);

    int deleteByExample(AuthorityCriteria example);

    int insert(Authority record);

    int insertSelective(Authority record);

    List<Authority> selectByExample(AuthorityCriteria example);
    Authority selectCd(AuthorityCriteria example);

    int updateByExampleSelective(@Param("record") Authority record, @Param("example") AuthorityCriteria example);
    int updatePosition(@Param("position") String position);
    int updateByExample(@Param("record") Authority record, @Param("example") AuthorityCriteria example);
}