package inspur.crawl.taskManage.mapper;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.taskManage.pojo.PartitionGroup;
import inspur.crawl.taskManage.pojo.PartitionGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartitionGroupMapper {
    int countByExample(PartitionGroupCriteria example);

    int deleteByExample(PartitionGroupCriteria example);

    int deleteByPrimaryKey(String groupId);

    int insert(PartitionGroup record);

    int insertSelective(PartitionGroup record);

    List<PartitionGroup> selectByExample(PartitionGroupCriteria example);

    PartitionGroup selectByPrimaryKey(String groupId);

    int updateByExampleSelective(@Param("record") PartitionGroup record, @Param("example") PartitionGroupCriteria example);

    int updateByExample(@Param("record") PartitionGroup record, @Param("example") PartitionGroupCriteria example);

    int updateByPrimaryKeySelective(PartitionGroup record);

    int updateByPrimaryKey(PartitionGroup record);

	List<PartitionGroup> listPageGroup(@Param("group")PartitionGroup group, @Param("page")Page page);

	List<PartitionGroup> selectAll();

}