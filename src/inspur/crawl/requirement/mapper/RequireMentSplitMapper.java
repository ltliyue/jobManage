package inspur.crawl.requirement.mapper;

import inspur.crawl.requirement.pojo.RequireMentSplit;

import java.util.List;

public interface RequireMentSplitMapper {

    int insertRequireMentSplit(RequireMentSplit record);

    List<RequireMentSplit> getAllRequireMentSplit(RequireMentSplit example);

    List<RequireMentSplit> getAllRequireMentSplitByRidlistPage(RequireMentSplit example);
    
    List<RequireMentSplit> getAllRequireMentSplitByRidlistPageCX(RequireMentSplit example);
    
    List<RequireMentSplit> getAllRequireMentSplitFixedlistPage(RequireMentSplit example);
    
    List<RequireMentSplit> getAllRequireMentSplitHistorylistPage(RequireMentSplit example);
    
    public int updateInfo(RequireMentSplit query);
    
    int deleteByPrimaryKey(Integer id);
}