package inspur.crawl.urlMonitor.controller;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.urlMonitor.pojo.TaskNameId;
import inspur.crawl.urlMonitor.service.CrawlInstationService;
import net.sf.json.JSONArray;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/urlMonitor")
public class urlMonitorController extends BaseController{
	static int alltask;
	@Resource
	CrawlInstationService service;
	@InitBinder("urlMonitor")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("urlMonitor.");  
	}
	/**
	 * 任务url采集结果监控
	 */
	@RequestMapping(value = "/taskUrlMonitor", method = RequestMethod.GET)
	public ModelAndView taskUrlMonitor() throws Exception {
		return request("sys/urlMonitor/taskUrlMonitor");
	}
	/**
	 * 根据页数获取任务列表
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTaskListByPageNo", method = RequestMethod.POST)
	@ResponseBody
	public void getTaskListByPageNo(PrintWriter out,Parameter pa){
		System.err.println(pa.getPage());
		StringBuffer sb = new StringBuffer();
		Object taskid = null;
		List<TaskNameId> ll = service.getNameId();
		int i=0;
		Map<String,String> map=service.queryTaskId(pa);
		Set<String> set = map.keySet();
		Iterator ite = set.iterator();			
		while(ite.hasNext()){
			i++;
			taskid=ite.next();
			String taskName = "";
			boolean flag=false;
			for(TaskNameId t : ll){
				if(t.getTask_id().equals(taskid)){
					taskName = t.getTask_name();
					System.out.println(taskName);
					sb.append("<tr id="+taskid+" class='treegrid-"+taskid+" active' onclick='getTaskInstanceByTaskId("+i+","+taskid+")'>");
					sb.append("<td>"+i+"</td>");
					sb.append("<td><a id=\""+taskid+"\" onclick=\"taskDetail(this.id)\">"+taskName+"</a></td>");
					sb.append("<td>"+map.get(taskid)+"</td></tr>");
					flag=true;
					break;
				}
			}
			if(flag==false){
				sb.append("<tr id="+taskid+" class='treegrid-"+taskid+" active' onclick='getTaskInstanceByTaskId("+i+","+taskid+")'>");
				sb.append("<td>"+i+"</td>");
				sb.append("<td><a id=\""+taskid+"\" onclick=\"taskDetail(this.id)\">"+taskid+"</a></td>");
				sb.append("<td>"+map.get(taskid)+"</td></tr>");
			}				
		}
		if(pa.getPage().equals("1"))
			alltask = service.queryTaskIdNum();
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("sb", sb.toString());
		mapJson.put("zys", alltask);
		mapJson.put("pages", pa.getPage());
		JSONArray json = JSONArray.fromObject(mapJson);
		out.print(json);
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTaskInstanceByTaskIdAndNo", method = RequestMethod.POST)
	@ResponseBody
	public void getTaskInstanceByTaskIdAndNo(String ii,String taskId,PrintWriter out){
		Object taskInstanceId = null;
		int i=0;
		Map<String,String> map = service.queryTaskInstance(taskId);
		Set<String> set = map.keySet();
		Iterator ite = set.iterator();
		StringBuffer sb = new StringBuffer();
		while(ite.hasNext()){
			i++;
			taskInstanceId=ite.next();
			sb.append("<tr name=\""+taskId+"_li\" style=\"display:line\"class=\"treegrid-"+taskInstanceId+" treegrid-parent-"+taskId+" active\">");
			sb.append("<td>"+ii+"."+i+"</td><td>");
			sb.append("<a id=\""+taskInstanceId+"\" onclick=\"taskInstanceDetail(this.id)\">"+taskInstanceId+"</td>");
			sb.append("<td>"+map.get(taskInstanceId)+"</td></tr>");
		}
		out.print(sb);
	}
	/**
	 * 任务实例详情页面
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/taskInstanceDetail", method = RequestMethod.GET)
	public ModelAndView taskInstanceDetail(String taskInstanceId) throws Exception {
		Map map = new HashMap();
		map.put("taskInstanceId", taskInstanceId);
		return request("sys/urlMonitor/taskInstanceDetail",map);
	}
	/**
	 * 任务详情页面
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/taskDetail", method = RequestMethod.GET)
	public ModelAndView taskDetail(String taskId) throws Exception {
		Map map = new HashMap();
		map.put("taskId", taskId);
		return request("sys/urlMonitor/taskDetail",map);
	}
	/**
	 * 任务实例详情页根据实例id获取任务实例信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTaskInstanceInfoByTaskInstanceId", method = RequestMethod.POST)
	public void getTaskInstanceInfoByTaskInstanceId(String taskInstanceId,PrintWriter out){
		Map<String,String> map=service.queryTaskInstanceAll(taskInstanceId);
		Map map1 = new HashMap();
		if(!map.containsKey("OTHER")){
			map1.put("other", "0");
		}else{
			map1.put("other", map.get("OTHER"));
		}
		if(!map.containsKey("NAV")){
			map1.put("nav", "0");
		}else{
			map1.put("nav", map.get("NAV"));
		}
		if(!map.containsKey("LIST")){
			map1.put("list", "0");
		}else{
			map1.put("list", map.get("LIST"));
		}
		if(!map.containsKey("INFO")){
			map1.put("info", "0");
		}else{
			map1.put("info", map.get("INFO"));
		}

		JSONArray json = JSONArray.fromObject(map1);
		out.print(json);
	}
	/**
	 * 任务详情页根据任务id获取任务实例信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTaskInfoByTaskId", method = RequestMethod.POST)
	public void getTaskInfoByTaskId(String taskId,PrintWriter out){
		Map<String,String> map=service.queryTaskList(taskId);
		Map map1 = new HashMap();
		if(!map.containsKey("OTHER")){
			map1.put("other", "0");
		}else{
			map1.put("other", map.get("OTHER"));
		}
		if(!map.containsKey("NAV")){
			map1.put("nav", "0");
		}else{
			map1.put("nav", map.get("NAV"));
		}
		if(!map.containsKey("LIST")){
			map1.put("list", "0");
		}else{
			map1.put("list", map.get("LIST"));
		}
		if(!map.containsKey("INFO")){
			map1.put("info", "0");
		}else{
			map1.put("info", map.get("INFO"));
		}
		JSONArray json = JSONArray.fromObject(map1);
		out.print(json);
	}	
	/**
	 * 任务详情页根据任务id获取任务实例下载量形成柱状图
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTaskInstanceInfoByTaskId", method = RequestMethod.POST)
	public void getTaskInstanceInfoByTaskId(String taskId,PrintWriter out){
		Map<String,String> map=service.queryTaskInstance(taskId);
		Set<String> list = map.keySet();
		JSONArray json = JSONArray.fromObject(list);		
		Map map1 = new HashMap();
		Map map2 = new HashMap();
		for(int i=0;i<json.size();i++){
			if(!map.containsKey(json.get(i))){
				map1.put(json.get(i), "0");
			}else{
				map1.put(""+map.get(json.get(i))+"",null);
				map2.put("实例"+json.get(i).toString().substring(0,7)+"",null);
			}
		}
		
		Set<String> list1 = map1.keySet();
		Set<String> list2 = map2.keySet();
		
		Map map3 = new HashMap();
		map3.put("xdata", list2);
		map3.put("ydata", list1);
		JSONArray json1 = JSONArray.fromObject(map3);		
		out.print(json1);
	}
	/**
	 * 根据页数、实例id获取URL详情
	 */
	@RequestMapping(value = "/getTaskInstanceUrlListByPageNo", method = RequestMethod.POST)
	@ResponseBody
	public void getTaskInstanceUrlListByPageNo(String page,String taskInstanceId,PrintWriter out){
		StringBuffer sb = new StringBuffer();
		List<Map<String, String>> list=service.queryTaskInstanceList(taskInstanceId,page);	
		for(int i=0 ;i<list.size();i++){
			String url = list.get(i).keySet().toString().substring(1,list.get(i).keySet().toString().length()-1);
			System.out.println("URL:"+url);
			String[] info = list.get(i).get(url).toString().split("\\|");
			sb.append("<tr class='treegrid-"+taskInstanceId+" active'>");
			sb.append("<td>"+url+"</td>");
			sb.append("<td>"+info[0]+"</td>");
			sb.append("<td>"+info[1]+"</td></tr>");
		}
		int zs = service.querydetailNumTaskInstanceId(taskInstanceId);
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("sb", sb.toString());
		mapJson.put("zys", zs);
		mapJson.put("pages", page);
		JSONArray json = JSONArray.fromObject(mapJson);
		out.print(json);
	}
	/**
	 * 根据任务实例ID获取任务起止时间
	 * @param taskInstanceId
	 * @param out
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getTaskTimeByTaskInstanceId", method = RequestMethod.POST)
	@ResponseBody
	public void getTaskTimeByTaskInstanceId(String taskInstanceId,PrintWriter out){
		TaskNameId time = service.getTime(taskInstanceId);	
		String publishTime = "";
		String finishTime = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		try{
			publishTime = time.getPublish_time();
			if(publishTime.equals("null")){				
				publishTime = df.format(new Date(System.currentTimeMillis())).toString();					 
			}
		}catch(NullPointerException e){
			publishTime = df.format(new Date(System.currentTimeMillis())).toString();		
		}
		try{
			finishTime = time.getFinish_time();
			if(finishTime.equals("null")){
				finishTime = df.format(new Date(System.currentTimeMillis())).toString();					 
			}
		}catch(NullPointerException e){			
			finishTime = df.format(new Date(System.currentTimeMillis())).toString();
		}

		Map map = new HashMap();
		String[] publish = publishTime.split(" ");
		String[] finish = finishTime.split(" ");
		map.put("startTime", publish[0]);
		map.put("endTime", finish[0]);
		JSONArray json = JSONArray.fromObject(map);
		out.print(json);
	}
	/**
	 * 根据实例id\type获取各类型URL不同时间粒度下的下载量
	 * @throws ParseException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getUrlDownloadListByTaskInstancceIdAndType", method = RequestMethod.POST)
	@ResponseBody
	public void getUrlDownloadListByTaskInstancceIdAndType(String taskInstanceId,
			String startTime,String endTime,String type,PrintWriter out) throws ParseException{
		taskInstanceId = taskInstanceId.replace(" ", "");
		java.util.List<Map<String, String>> list =service.queryTaskInstanceLineChart(taskInstanceId,startTime,endTime,type);
		Map map = new HashMap();
		ArrayList list2x = new ArrayList();
		ArrayList list2y = new ArrayList();
		ArrayList list3x = new ArrayList();
		ArrayList list3y = new ArrayList();
		ArrayList list4x = new ArrayList();
		ArrayList list4y = new ArrayList();
		int length = list.size();
		for(int i=0;i<length;i++){
			String[] data = null,data1 = null,data2 = null;
			if(list.get(i).containsKey("NAV")){
				data = list.get(i).get("NAV").split("\\|");
				list2x.add(data[0]);
				list2y.add(data[1]);
			}
			if(list.get(i).containsKey("LIST")){
				data1 = list.get(i).get("LIST").split("\\|");
				list3x.add(data1[0]);
				list3y.add(data1[1]);
			}
			if(list.get(i).containsKey("INFO")){
				data2 = list.get(i).get("INFO").split("\\|");
				list4x.add(data2[0]);
				list4y.add(data2[1]);
			}
		}
		selectSort(list2x,list2y,type);
		selectSort(list3x,list3y,type);
		selectSort(list4x,list4y,type);
		
		list2x = changeListX(list2x);
		list3x = changeListX(list3x);
		list4x = changeListX(list4x);
		
		map.put("list2x",list2x);
		map.put("list2y",list2y);
		map.put("list3x",list3x);
		map.put("list3y",list3y);
		map.put("list4x",list4x);
		map.put("list4y",list4y);
		JSONArray json = JSONArray.fromObject(map);
		System.out.println(json);
		out.print(json);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList changeListX(ArrayList list) {
		for(int i=0;i<=list.size();i++){
			list.set(i, list.get(i).toString().replace("/"," ")+":00:00");
		}
		return list;
	}
	/** 
	 * 数组的最小值 
	 * 
	 * @return int 数组的键值 
	 * @throws ParseException 
	 */  
	@SuppressWarnings("rawtypes")
	int SelectMinKey(List list, int i,String type) throws ParseException  
	{  
	    int k = i;  
	    for(int j=i+1 ;j< list.size(); ++j) {
			SimpleDateFormat df = null;			
			if(type.equals("0")){
				df = new SimpleDateFormat("yyyy-MM-dd/hh");//设置日期格式
			}
			if(type.equals("1")){
				df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			}
	    	String date1 = list.get(k).toString();
			String date2 = list.get(j).toString();
			java.util.Date date11= df.parse(date1);
			java.util.Date date22= df.parse(date2);
			if(date11.after(date22)){
				k = j;
			} 
	    }  
	    return k;  
	}  
	/** 
	 * 选择排序 
	 * @throws ParseException 
	 * 
	 */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void selectSort(List xlist, List ylist, String type) throws ParseException{  
	    int key;
		String tmp;  
	    for(int i = 0; i< xlist.size(); ++i) {  
	        key = SelectMinKey(xlist,i,type);           //选择最小的元素  
	        if(key != i){  
	            tmp = xlist.get(i).toString();  
	            xlist.set(i,xlist.get(key)); 
	            xlist.set(key,tmp); //最小元素与第i位置元素互换  
	            
	            tmp = ylist.get(i).toString();  
	            ylist.set(i,ylist.get(key)); 
	            ylist.set(key,tmp); //最小元素与第i位置元素互换 
	        }  
	        //print(a,  n , i);  
	    }  
	} 
}
