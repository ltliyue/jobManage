package inspur.crawl.taskManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.taskManage.pojo.PartitionGroup;
import inspur.crawl.taskManage.service.PartitionService;

@RestController
@RequestMapping("/partition")
public class PartitionGroupController extends BaseController{

	@InitBinder("partitionGroup")  
	public void taskBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("partitionGroup.");  
	}
	
	@InitBinder("page")  
	public void pageBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("page.");  
	}
	
	@Resource
	PartitionService partitionService;
	
	/**
	 * 任务管理
	 */
	@RequestMapping(value = "/manage", method = RequestMethod.GET)
	public ModelAndView manage(PartitionGroup partitionGroup, Page page) throws Exception {
		List<PartitionGroup> list = partitionService.findGroups(partitionGroup, page);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("groups", list);
		map.put("page", page);
		return request("sys/taskManage/partitionManage",map);
	}
	
	/**
	 * 任务新增
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(PartitionGroup partitionGroup) throws Exception {
		try {
			int result = partitionService.addGroup(partitionGroup);
			if(result==1) {
				return "新增成功!";
			}else {
				return "新增失败!";
			}
		} catch (Exception e) {
			return "操作异常:"+e.getMessage();
		}
		
	}
	
//	/**
//	 * 任务修改
//	 */
//	@RequestMapping(value = "/goUpdate", method = RequestMethod.GET)
//	@ResponseBody
//	public String goUpdate(PartitionGroup partitionGroup) throws Exception {
//		try {
//			int result = partitionService.updateGroup(partitionGroup);
//			if(result==1) {
//				return "修改成功!";
//			}else {
//				return "修改失败!";
//			}
//		} catch (Exception e) {
//			return "操作异常:"+e.getMessage();
//		}
//		
//	}
	
	/**
	 * 分组修改
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public String update(PartitionGroup partitionGroup) throws Exception {
		try {
			int result = partitionService.updateGroup(partitionGroup);
			if(result==1) {
				return "修改成功!";
			}else {
				return "修改失败!";
			}
		} catch (Exception e) {
			return "操作异常:"+e.getMessage();
		}
		
	}
	
	/**
	 * 分组删除
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public String del(String ids) throws Exception {
		try {
			int result = partitionService.delGroups(ids);
			if(result==1) {
				return "修改成功!";
			}else {
				return "修改失败!";
			}
		} catch (Exception e) {
			return "操作异常:"+e.getMessage();
		}
		
	}
}
