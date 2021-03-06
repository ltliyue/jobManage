package inspur.crawl.requirement.controller;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.tools.OutputExcel;
import inspur.crawl.common.tools.TimeCompare;
import inspur.crawl.demandAna.pojo.CrawlerDemand;
import inspur.crawl.requirement.pojo.RequireMent;
import inspur.crawl.requirement.pojo.RequireMentSplit;
import inspur.crawl.requirement.service.RequireService;
import inspur.crawl.siteManage.controller.RealSiteController;
import inspur.crawl.siteManage.pojo.RealSiteCode;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import net.sf.json.JSONArray;

import org.apache.http.util.TextUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/requirementSplit")
public class RequireMentSplitController extends BaseController {
	@Resource
	RequireService service;

	@Resource
	RealSiteController realSiteController;

	@InitBinder("requireMentSplit")
	public void typeBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("requireMentSplit.");
	}

	/**
	 * 任务分解
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listContent_split", method = RequestMethod.GET)
	public ModelAndView listContent_split(@RequestParam String id) throws Exception {
		return request("sys/requirement/listContent_split").addObject("id", id);
	}

	/**
	 * 获得所有任务
	 * 
	 * @param id
	 * @param out
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllRequireMentSplit", method = RequestMethod.POST)
	@ResponseBody
	public void getAllRequireMentSplit(String rid, PrintWriter out) throws UnsupportedEncodingException {

		RequireMentSplit query = new RequireMentSplit();
		query.setRid(rid);

		List<RequireMentSplit> list = service.getAllRequireMentSplit(query);
		for (RequireMentSplit requireMentSplit : list) {
			// 是否立即执行
			if (requireMentSplit.getIsalive().equals("1")) {
				requireMentSplit.setIsalive("是");
			} else {
				requireMentSplit.setIsalive("否");
			}
			// 频率
			if (requireMentSplit.getJobFrequency().equals("1")) {
				requireMentSplit.setJobFrequency("一次性");
			} else if (requireMentSplit.getJobFrequency().equals("2")) {
				requireMentSplit.setJobFrequency("每周");
			} else if (requireMentSplit.getJobFrequency().equals("3")) {
				requireMentSplit.setJobFrequency("每月");
			}
			// 优先级
			if (requireMentSplit.getJobDegree().equals("1")) {
				requireMentSplit.setJobDegree("高");
			} else if (requireMentSplit.getJobDegree().equals("2")) {
				requireMentSplit.setJobDegree("中");
			} else {
				requireMentSplit.setJobDegree("低");
			}
			// 是否满足需求
			if (requireMentSplit.getIsmatch().equals("1")) {
				requireMentSplit.setIsmatch("是");
			} else {
				requireMentSplit.setIsmatch("否");
			}
			// 是否数据采集
			if (requireMentSplit.getIscollection().equals("1")) {
				requireMentSplit.setIscollection("是");
			} else {
				requireMentSplit.setIscollection("否");
			}
			// 是否数据报告
			if (requireMentSplit.getIsreport().equals("1")) {
				requireMentSplit.setIsreport("是");
			} else {
				requireMentSplit.setIsreport("否");
			}
		}

		System.out.println("----库存查询结果：" + list);
		JSONArray json = JSONArray.fromObject(list);
		out.print(json);
	}

	/**
	 * ExcelExport
	 */
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	@ResponseBody
	@Produces("text/plain;charset=utf-8")
	public int excelExport(String bm, HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("---->" + bm);
		List<String> listheader = new ArrayList<String>();
		listheader.add("ID");
		listheader.add("任务名称");
		listheader.add("任务内容");
		listheader.add("任务类型");
		listheader.add("本周是否响应");
		listheader.add("任务频率");
		listheader.add("任务优先级");
		listheader.add("任务类型");
		listheader.add("对应客户");
		listheader.add("需求提出人");

		listheader.add("提出时间");
		listheader.add("要求时间");
		listheader.add("负责人");
		listheader.add("任务状态");
		listheader.add("完成时间");

		listheader.add("提取数据范围");
		listheader.add("数据是否满足需求");
		listheader.add("是否进行数据采集");
		listheader.add("是否需要数据报告");
		listheader.add("平台");

		RequireMentSplit requireMent = new RequireMentSplit();

		List<RequireMentSplit> list = null;
		if (bm.equals("运营存续任务")) {
			list = getCXJobs(requireMent);
		} else if (bm.equals("任务进度")) {
			list = doMatchThing(service.getAllRequireMentSplitByRidlistPage(requireMent));
		} else if (bm.equals("固定任务")) {
			list = doMatchThing(service.getAllRequireMentSplitFixedlistPage(requireMent));
		} else if (bm.equals("历史任务")) {
			list = doMatchThing(service.getAllRequireMentSplitHistorylistPage(requireMent));
		}

		OutputExcel oe = new OutputExcel();
		oe.excel_requirmentSplit(bm, list, listheader, request, response);

		return 0;
	}

	/**
	 * 存续任务
	 */

	private List<RequireMentSplit> getCXJobs(RequireMentSplit requireMentSplit) {
		// TODO Auto-generated method stub
		List<RequireMentSplit> list = service.getAllRequireMentSplitByRidlistPageCX(requireMentSplit);

		for (RequireMentSplit requireMentSplit1 : list) {
			// 是否立即执行
			if (requireMentSplit1.getIsalive().equals("1")) {
				requireMentSplit1.setIsalive("是");
			} else {
				requireMentSplit1.setIsalive("否");
			}
			// 频率
			if (requireMentSplit1.getJobFrequency().equals("1")) {
				requireMentSplit1.setJobFrequency("一次性");
			} else if (requireMentSplit1.getJobFrequency().equals("2")) {
				requireMentSplit1.setJobFrequency("每周");
			} else if (requireMentSplit1.getJobFrequency().equals("3")){
				requireMentSplit1.setJobFrequency("每月");
			}
			// 优先级
			if (requireMentSplit1.getJobDegree().equals("1")) {
				requireMentSplit1.setJobDegree("高");
			} else if (requireMentSplit1.getJobDegree().equals("2")) {
				requireMentSplit1.setJobDegree("中");
			} else {
				requireMentSplit1.setJobDegree("低");
			}
			// 是否满足需求
			if (requireMentSplit1.getIsmatch().equals("1")) {
				requireMentSplit1.setIsmatch("是");
			} else {
				requireMentSplit1.setIsmatch("否");
			}
			// 是否数据采集
			if (requireMentSplit1.getIscollection().equals("1")) {
				requireMentSplit1.setIscollection("是");
			} else {
				requireMentSplit1.setIscollection("否");
			}
			// 是否数据报告
			if (requireMentSplit1.getIsreport().equals("1")) {
				requireMentSplit1.setIsreport("是");
			} else {
				requireMentSplit1.setIsreport("否");
			}
			requireMentSplit1.setPlantform("1");
		}

		// 采集平台
		List<RealSiteCode> list_RealSiteCode = realSiteController.selectAll();
		for (RealSiteCode crawlerDemand : list_RealSiteCode) {
			RequireMentSplit requireMentSplit2 = new RequireMentSplit();
			requireMentSplit2.setName(crawlerDemand.getName());
			requireMentSplit2.setContent(crawlerDemand.getSiteDescription());
			requireMentSplit2.setType("数据采集");
			requireMentSplit2.setPlanTime(TimeCompare.turnTime4(crawlerDemand.getPublishTime()));
			requireMentSplit2.setIsalive("是");
			if (crawlerDemand.getExecutionCycle().contains("30") || crawlerDemand.getExecutionCycle().equals("每月")) {
				requireMentSplit2.setJobFrequency("每月");
			} else if (crawlerDemand.getExecutionCycle().contains("周")) {
				requireMentSplit2.setJobFrequency("每周");
			} else if (crawlerDemand.getExecutionCycle().contains("天")) {
				requireMentSplit2.setJobFrequency("每天");
			} else {
				requireMentSplit2.setJobFrequency("一次性");
			}
			requireMentSplit2.setJobDegree("高");
			requireMentSplit2.setJobContent("-");
			requireMentSplit2.setJobRange("-");
			requireMentSplit2.setIsmatch("是");
			requireMentSplit2.setIscollection("是");
			requireMentSplit2.setIsreport("是");
			requireMentSplit2.setRid("@" + crawlerDemand.getId());
			requireMentSplit2.setHeader(crawlerDemand.getPublisherId());
			requireMentSplit2.setStatus("-");
			requireMentSplit2.setDoTime("-");
			requireMentSplit2.setPlantform("2");

			list.add(requireMentSplit2);
		}
		return list;
	}

	@RequestMapping(value = "/listContent_jobs", method = RequestMethod.GET)
	public ModelAndView getAllJobslistPage(RequireMentSplit requireMentSplit) throws Exception {

		int page = 0;
		if (TextUtils.isEmpty(requireMentSplit.getMpage())) {
			requireMentSplit.setMpage("0");
		}
		page = Integer.parseInt(requireMentSplit.getMpage());
		System.out.println("--->getAllJobslistPage 参数：" + requireMentSplit.toString());
		System.out.println("--->page 参数：" + page + "");

		List<RequireMentSplit> requireMentSplits = getCXJobs(requireMentSplit);
		List<RequireMentSplit> requireMentSplits_Page = new ArrayList<>();
		for (int i = page * 10; i < (page + 1) * 10; i++) {
			if (i>=requireMentSplits.size()) {
				break;
			}
			requireMentSplits_Page.add(requireMentSplits.get(i));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accounts", requireMentSplits_Page);
		map.put("page", requireMentSplits.size()/10+1+"");
		map.put("mpage", (page+1)+"");
		return request("sys/requirement/listContent_jobs", map);
	}

	/**
	 * 任务进度
	 */
	@RequestMapping(value = "/listContent_jobstatus", method = RequestMethod.GET)
	public ModelAndView getAllRequireMentSplitByRidlistPage(RequireMentSplit requireMentSplit) throws Exception {

		System.out.println("listContent_jobstatus 参数：" + requireMentSplit.toString());
		List<RequireMentSplit> list = service.getAllRequireMentSplitByRidlistPage(requireMentSplit);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accounts", doMatchThing(list));
		map.put("page", requireMentSplit);
		return request("sys/requirement/listContent_jobstatus", map);
	}

	/**
	 * 固定任务!!!
	 */
	@RequestMapping(value = "/listContent_fixedjob", method = RequestMethod.GET)
	public ModelAndView getAllRequireMentSplitlistPage(RequireMentSplit requireMentSplit) throws Exception {
		List<RequireMentSplit> list = doMatchThing(service.getAllRequireMentSplitFixedlistPage(requireMentSplit));

		// 采集平台
		List<RealSiteCode> list_RealSiteCode = realSiteController.selectAll();
		for (RealSiteCode crawlerDemand : list_RealSiteCode) {
			RequireMentSplit requireMentSplit2 = new RequireMentSplit();
			requireMentSplit2.setName(crawlerDemand.getName());
			requireMentSplit2.setContent(crawlerDemand.getSiteDescription());
			requireMentSplit2.setType("数据采集");
			requireMentSplit2.setPlanTime(TimeCompare.turnTime4(crawlerDemand.getPublishTime()));
			requireMentSplit2.setIsalive("是");
			if (crawlerDemand.getExecutionCycle().contains("30") || crawlerDemand.getExecutionCycle().equals("每月")) {
				requireMentSplit2.setJobFrequency("每月");
			} else if (crawlerDemand.getExecutionCycle().contains("周")) {
				requireMentSplit2.setJobFrequency("每周");
			} else if (crawlerDemand.getExecutionCycle().contains("天")) {
				requireMentSplit2.setJobFrequency("每天");
			} else {
				requireMentSplit2.setJobFrequency("一次性");
			}
			requireMentSplit2.setJobDegree("高");
			requireMentSplit2.setJobContent("-");
			requireMentSplit2.setJobRange("-");
			requireMentSplit2.setIsmatch("是");
			requireMentSplit2.setIscollection("是");
			requireMentSplit2.setIsreport("是");
			requireMentSplit2.setRid("@" + crawlerDemand.getId());
			requireMentSplit2.setHeader(crawlerDemand.getPublisherId());
			requireMentSplit2.setStatus("-");
			requireMentSplit2.setDoTime("-");
			requireMentSplit2.setPlantform("2");

			if (!requireMentSplit2.getJobFrequency().equals("一次性")) {
				list.add(requireMentSplit2);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accounts", list);
		map.put("page", requireMentSplit);
		return request("sys/requirement/listContent_fixedjob", map);
	}

	/**
	 * 历史任务!!!
	 */
	@RequestMapping(value = "/listContent_hisjob", method = RequestMethod.GET)
	public ModelAndView getAllRequireMentSplitHistorylistPage(RequireMentSplit requireMentSplit) throws Exception {
		List<RequireMentSplit> list = service.getAllRequireMentSplitHistorylistPage(requireMentSplit);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accounts", doMatchThing(list));
		map.put("page", requireMentSplit);
		return request("sys/requirement/listContent_hisjob", map);
	}

	@RequestMapping(value = "/jobedit", method = RequestMethod.GET)
	public ModelAndView editjob(@RequestParam String id) throws Exception {
		return request("sys/requirement/jobedit").addObject("id", id);
	}

	/**
	 * 固定任务编辑
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fixedjobedit", method = RequestMethod.GET)
	public ModelAndView fixedjobedit(@RequestParam String id) throws Exception {
		return request("sys/requirement/fixedjobedit").addObject("id", id);
	}

	/**
	 * 分解任务编辑
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/splitjobedit", method = RequestMethod.GET)
	public ModelAndView splitjobedit(@RequestParam String id) throws Exception {
		return request("sys/requirement/splitjobedit").addObject("id", id);
	}

	/**
	 * 查询任务 id
	 */
	@RequestMapping(value = "/requirementsplitq", method = RequestMethod.POST)
	@ResponseBody
	public RequireMentSplit requirementsplitq(String id) {
		RequireMentSplit requireMentSplitQuery = new RequireMentSplit();
		requireMentSplitQuery.setId(Integer.parseInt(id));
		return service.getAllRequireMentSplit(requireMentSplitQuery).get(0);
	}

	/**
	 * 替换非字符
	 * 
	 * @param list
	 * @return
	 */
	private List<RequireMentSplit> doMatchThing(List<RequireMentSplit> list) {
		// TODO Auto-generated method stub
		for (RequireMentSplit require : list) {
			// 是否立即执行
			if (require.getIsalive().equals("1")) {
				require.setIsalive("是");
			} else {
				require.setIsalive("否");
			}
			// 频率
			if (require.getJobFrequency().equals("1")) {
				require.setJobFrequency("一次性");
			} else if (require.getJobFrequency().equals("2")) {
				require.setJobFrequency("每周");
			} else if (require.getJobFrequency().equals("3")){
				require.setJobFrequency("每月");
			}
			// 优先级
			if (require.getJobDegree().equals("1")) {
				require.setJobDegree("高");
			} else if (require.getJobDegree().equals("2")) {
				require.setJobDegree("中");
			} else {
				require.setJobDegree("低");
			}
			// 是否满足需求
			if (require.getIsmatch().equals("1")) {
				require.setIsmatch("是");
			} else {
				require.setIsmatch("否");
			}
			// 是否数据采集
			if (require.getIscollection().equals("1")) {
				require.setIscollection("是");
			} else {
				require.setIscollection("否");
			}
			// 是否数据报告
			if (require.getIsreport().equals("1")) {
				require.setIsreport("是");
			} else {
				require.setIsreport("否");
			}
			require.setPlantform("1");
		}
		return list;
	}

	/**
	 * 删除任务 id
	 */
	@RequestMapping(value = "/deletebyid", method = RequestMethod.POST)
	@ResponseBody
	public int deletebyid(int id) {
		System.out.println("删除：" + id);
		return service.deleteByPrimaryKey(id);
	}

	/**
	 * 新增
	 * 
	 * @param name
	 * @param content
	 * @param type
	 * @param planTime
	 * @param isalive
	 * @param jobFrequency
	 * @param jobDegree
	 * @param jobContent
	 * @param jobRange
	 * @param ismatch
	 * @param iscollection
	 * @param isreport
	 * @param rid
	 * @param out
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/insertRequireMentSplit", method = RequestMethod.POST)
	@ResponseBody
	public void saveInfo(String name, String content, String type, String planTime, String isalive, String jobFrequency, String jobDegree, String jobContent, String jobRange, String ismatch,
			String iscollection, String isreport, int rid, PrintWriter out) throws UnsupportedEncodingException {
		System.out.println("---保存任务");

		RequireMentSplit query = new RequireMentSplit();
		query.setContent(content);
		query.setIsalive(isalive);
		query.setIscollection(iscollection);
		query.setIsmatch(ismatch);
		query.setIsreport(isreport);
		query.setJobContent(jobContent);
		query.setJobDegree(jobDegree);
		query.setJobFrequency(jobFrequency);
		query.setJobRange(jobRange);
		query.setName(name);
		query.setPlanTime(planTime);
		query.setRid(rid + "");
		query.setType(type);
		query.setStatus("未开始");

		System.out.println("----查询条件：" + query.toString());

		int result = service.insertRequireMentSplit(query);
		System.out.println("----保存结果：" + result);
		if (result == 1) {
			System.out.println("----信息保存成功");
			out.print("01");
		} else {
			System.out.println("----信息保存失败");
			out.print("00");
		}
	}

	/**
	 * 更新任务
	 * 
	 * @param id
	 * @param properties
	 * @param type
	 * @param name
	 * @param content
	 * @param customer
	 * @param propose_user
	 * @param propose_time
	 * @param request_time
	 * @param status
	 * @param reason
	 * @param out
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/updatejobInfo", method = RequestMethod.POST)
	@ResponseBody
	public void updatejobInfo(RequireMentSplit query, PrintWriter out) throws UnsupportedEncodingException {
		// System.out.println(":::" + jobDegree);
		// RequireMentSplit query = new RequireMentSplit();
		// query.setId(id);
		// query.setName(name);
		// query.setContent(content);
		// query.setType(type);
		// query.setPlanTime(planTime);
		// query.setIsalive(isalive);
		// query.setJobFrequency(jobFrequency);
		// query.setJobDegree(jobDegree);
		// query.setJobContent(jobContent);
		// query.setJobRange(jobRange);
		// query.setIsmatch(ismatch);
		// query.setIscollection(iscollection);
		// query.setIsreport(isreport);
		// query.setHeader(header);
		// query.setStatus(status);

		System.out.println("----update@@查询条件：" + query.toString());

		int result = service.updateInfo(query);
		System.out.println("----update保存结果：" + result);
		if (result == 1) {
			System.out.println("----update修改成功");
			out.print("01");
		} else {
			System.out.println("----信息修改失败");
			out.print("00");
		}
	}
}
