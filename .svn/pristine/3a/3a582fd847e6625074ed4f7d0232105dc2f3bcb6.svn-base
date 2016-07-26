package inspur.crawl.taskManage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.ArrayUtil;
import org.springframework.stereotype.Service;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.taskManage.mapper.PartitionGroupMapper;
import inspur.crawl.taskManage.pojo.CrawlerTask;
import inspur.crawl.taskManage.pojo.PartitionGroup;
import inspur.crawl.taskManage.pojo.PartitionGroupCriteria;

@Service
public class PartitionService {

	@Resource
	PartitionGroupMapper groupMapper;
	@Resource
	private HttpSession session;
	
	/**
	 * 查询任务列表(分页)
	 * @param task
	 * @param page
	 * @return
	 */
	public List<PartitionGroup> findGroups(PartitionGroup group, Page page) {
		//TODO 加入查询参数
		return groupMapper.listPageGroup(group, page);
	}

	
	
	
	/**
	 * 新增
	 * @param partitionGroup
	 * @return
	 */
	public int addGroup(PartitionGroup partitionGroup) throws Exception {
		partitionGroup.setGroupId(UUID.randomUUID().toString());
		partitionGroup.setCreator(session.getAttribute(Sessions.SESSION_USER).toString());
		partitionGroup.setCreateTime(new Date());
		return groupMapper.insert(partitionGroup);
	}
	
	/**
	 * 修改
	 * @param partitionGroup
	 * @return
	 */
	public int updateGroup(PartitionGroup partitionGroup) {
		partitionGroup.setModifier(session.getAttribute(Sessions.SESSION_USER).toString());
		partitionGroup.setModifyTime(new Date());
		return groupMapper.updateByPrimaryKeySelective(partitionGroup);
	}

	/**
	 * 获得所有分组
	 * @return
	 */
	public List<PartitionGroup> findAllGroup() {
		return groupMapper.selectAll();
	}

	/**
	 * 获得分组对应的分区
	 * @param groups
	 * @return
	 */
	public String[] getPartitionsFromGroup(String groups) {
		String[] groupArray = groups.split(",");
		List<String> groupIds = new ArrayList<>();
		for(int i=0; i<groupArray.length; i++) {
			if(groupArray[i]!=null 
					&& !"".equals(groupArray[i])) {
				groupIds.add(groupArray[i]);
			}
		}
		PartitionGroupCriteria criteria = new PartitionGroupCriteria();
		criteria.createCriteria().andGroupIdIn(groupIds);
		List<PartitionGroup> pgs = groupMapper.selectByExample(criteria);
		Set<String> partitionIds = new HashSet<String>();
		for(PartitionGroup pg:pgs) {
			if(pg.getPartitionId()!=null && !"".equals(pg.getPartitionId())) {
				String[] pIds = pg.getPartitionId().split(",");
				for(String pId:pIds) {
					partitionIds.add(pId);
				}
			}
		}
		
		return (String[]) partitionIds.toArray(new String[partitionIds.size()]);
	}

	/**
	 * 批量删除分组
	 * @param ids
	 * @return
	 */
	public int delGroups(String ids) {
		String[] groupArray = ids.split(",");
		List<String> groupIds = new ArrayList<>(Arrays.asList(groupArray));
		PartitionGroupCriteria criteria = new PartitionGroupCriteria();
		criteria.createCriteria().andGroupIdIn(groupIds);
		return groupMapper.deleteByExample(criteria);
	}
}
