package inspur.crawl.requirement.service;

import inspur.crawl.requirement.mapper.RequireMentMapper;
import inspur.crawl.requirement.mapper.RequireMentSplitMapper;
import inspur.crawl.requirement.pojo.RequireMent;
import inspur.crawl.requirement.pojo.RequireMentQuery;
import inspur.crawl.requirement.pojo.RequireMentSplit;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class RequireService {

	@Resource
	RequireMentMapper requireMentMapper;
	@Resource
	RequireMentSplitMapper requireMentSplitMapper;

	public List<RequireMent> getResourceAll(RequireMent query) {
		List<RequireMent> list = requireMentMapper.listPage(query);
		return list;
	}
	
	public List<RequireMent> getResourceAllAdmin(RequireMent query) {
		List<RequireMent> list = requireMentMapper.listPageAdmin(query);
		return list;
	}

	public int saveInfo(RequireMent query) {
		return requireMentMapper.saveInfo(query);
	}

	public int updateInfo(RequireMent query) {
		return requireMentMapper.updateInfo(query);
	}

	public RequireMent getRequireMent(String id) {
		RequireMent a = requireMentMapper.getResourceById(id);
		return a;
	}

	// ----------------------

	public List<RequireMentSplit> getAllRequireMentSplit(RequireMentSplit query) {
		List<RequireMentSplit> list = requireMentSplitMapper.getAllRequireMentSplit(query);
		return list;
	}
	/**
	 * 存续任务
	 * @param query
	 * @return
	 */
	public List<RequireMentSplit> getAllRequireMentSplitByRidlistPageCX(RequireMentSplit query) {
		List<RequireMentSplit> list = requireMentSplitMapper.getAllRequireMentSplitByRidlistPageCX(query);
		return list;
	}
	/**
	 * 任务状态
	 * @param query
	 * @return
	 */
	public List<RequireMentSplit> getAllRequireMentSplitByRidlistPage(RequireMentSplit query) {
		List<RequireMentSplit> list = requireMentSplitMapper.getAllRequireMentSplitByRidlistPage(query);
		return list;
	}

	/**
	 * 固定任务
	 */
	public List<RequireMentSplit> getAllRequireMentSplitFixedlistPage(RequireMentSplit query) {
		List<RequireMentSplit> list = requireMentSplitMapper.getAllRequireMentSplitFixedlistPage(query);
		return list;
	}

	/**
	 * 历史任务
	 */
	public List<RequireMentSplit> getAllRequireMentSplitHistorylistPage(RequireMentSplit query) {
		List<RequireMentSplit> list = requireMentSplitMapper.getAllRequireMentSplitHistorylistPage(query);
		return list;
	}

	public int insertRequireMentSplit(RequireMentSplit query) {
		return requireMentSplitMapper.insertRequireMentSplit(query);
	}

	public int updateInfo(RequireMentSplit query) {
		return requireMentSplitMapper.updateInfo(query);
	}

	public int deleteByPrimaryKey(Integer id) {
		return requireMentSplitMapper.deleteByPrimaryKey(id);
	};
}
