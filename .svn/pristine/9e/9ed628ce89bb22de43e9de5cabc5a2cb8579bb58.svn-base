package inspur.crawl.dataManage.controller;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.datasource.DataSourceInstances;
import inspur.crawl.common.interf.Sessions;
import inspur.crawl.common.tools.LoadProperty;
import inspur.crawl.common.tools.OutputExcel;
import inspur.crawl.common.tools.TimeCompare;
import inspur.crawl.dataManage.pojo.CjSysNotes;
import inspur.crawl.dataManage.pojo.DataSource;
import inspur.crawl.dataManage.service.DataSourceService;
import inspur.crawl.ruleManage.pojo.ElementExtractRule;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.service.ElementExtractRuleService;
import inspur.crawl.ruleManage.service.ExtractRuleService;
import inspur.crawl.ruleManage.service.PageExtractRuleService;
import inspur.crawl.taskManage.controller.TaskConstant;
import inspur.crawl.taskManage.pojo.TaskInstance;
import inspur.crawl.taskManage.pojo.TaskInstanceKey;
import inspur.crawl.taskManage.service.TaskInstanceService;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.inspur.avro.model.java.HandleMonitor;
import com.inspur.avro.model.java.PrecMonitor;
import com.inspur.kafka.crawl.model.CrawlResult;
import com.inspur.kafka.main.KafkaProducerClient;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;


@RestController
@RequestMapping("/datasource")
public class DataSourceController extends BaseController{
	@Resource
	DataSourceService dataSourceService;
	@Resource
	ExtractRuleService extractRuleService;
	@Resource
	PageExtractRuleService pageExtractRuleService;
	@Resource
	ElementExtractRuleService elementExtractRuleService;
	@Resource
	TaskInstanceService instanceService;
	@InitBinder("datasource")  
	public void typeBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("datasource.");  
	}
	private static final String TOPIC_DATA_MONITOR = "dataMonitor";
	private static final String PROCESS_STAGE_PREC = "05";
	private static final String PROCESS_STAGE_HANDLE = "06";
	/**
	 * 预处理
	 */
	public static final int PROCESS = 3;
	/**
	 * 清洗处理
	 */
	public static final int HANDLE = 4;
	
	LoadProperty lp = new LoadProperty();
	String logsrc = "/tmp/sqoop-log/sqoop-";
	Properties pro = lp.loadConfiguration("/restful.properties");
	String resturl = pro.getProperty("rest.url");
	/**
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listdata", method = RequestMethod.POST)
	@ResponseBody
	public List<DataSource> listdata() throws Exception {
		List<DataSource> list = dataSourceService.getListData();
		return list;
	}
	/**
	 * 增加
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adddata", method = RequestMethod.POST)
	@ResponseBody
	public int adddata(DataSource ds) throws Exception {
		int i = dataSourceService.getAddData(ds);
		return i;
	}
	/**
	 * 删除
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deldata", method = RequestMethod.POST)
	@ResponseBody
	public int deldata( String id) throws Exception {
		int i = dataSourceService.getDelData(id);
		return i;
	}
	/**
	 *
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(String id) throws Exception {
		String cs = "O"+id.toUpperCase()+"_%";
		List<String> list = dataSourceService.getUserTables(cs);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("bm", ("O"+id+"_"));
		map.put("taskid", id);
		return request("/sys/taskManage/dataDetail",map);
	}
	/**
	 *
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detailclean", method = RequestMethod.GET)
	public ModelAndView returnclean(String id) throws Exception {
		String cs = "O"+id.toUpperCase()+"_%";
		List<String> list = dataSourceService.getUserTables(cs);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("bm", ("O"+id+"_"));
		map.put("taskid", id);
		return request("/sys/dataManage/dataClean",map);
	}
	/**
	 *
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detailimp", method = RequestMethod.GET)
	public ModelAndView returnimp(String id) throws Exception {
		String cs = "O"+id.toUpperCase()+"_%";
		List<String> list = dataSourceService.getUserTables(cs);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("bm", ("O"+id+"_"));
		map.put("taskid", id);
		return request("/sys/dataManage/dataImp",map);
	}
	/**
	 * 增加表名
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addtable", method = RequestMethod.POST)
	@ResponseBody
	public int addtable(String name,String id,String ruleid) throws Exception {
		List<ElementExtractRule> list = elementExtractRuleService.getByPageExtractRule(ruleid);
		String clumnName = "";
		for(ElementExtractRule er:list){
			if(clumnName==""){
				clumnName = ","+er.getStoreColumnName()+" varchar(4000)";
			}else{
				clumnName = clumnName+","+er.getStoreColumnName()+" varchar(4000)";
			}
			
		}
		String creates = name+" (key varchar(400),task_id varchar(300) not null ,extract_time varchar(500),task_url varchar(4000),instance_id varchar(300),FETCH_TIME varchar(300),page_extract_rule_id varchar(300) "+clumnName+")";
		int i = dataSourceService.getAddTable(creates);
		if(i==0){
			dataSourceService.deltable(name.toUpperCase());
			dataSourceService.addSystable(name.toUpperCase(),ruleid,id);
		}
		return i;
	}
	/**
	 * 查询一个任务的规则
	 * @throws Exception 
	 */
	@RequestMapping(value = "/listrule", method = RequestMethod.POST)
	@ResponseBody
	public List<PageExtractRule> listrule(String task_id) throws Exception{
		List<PageExtractRule> list =pageExtractRuleService.queryTaskId(task_id);
		return list;
	}
	/**
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listd", method = RequestMethod.POST)
	@ResponseBody
	@Produces("text/plain;charset=utf-8")
	public Map<String,Object> listd(String bm,int yeshu,@PathParam("where") String where) throws Exception {
		 List<CjSysNotes> listc = dataSourceService.getruleId(bm);
		String w = "";
		if(!"".equals(where)&&where!=null){
			w = "where ";
			String[] ws = where.split(";");
			for(String wf:ws){
				String[] tf = wf.split("=");
				if(tf.length==2){
					if("ts".equals(tf[0])){
						w = w+" to_date(extract_time,'yyyy-MM-dd hh24:mi:ss')>to_date('"+tf[1]+"','yyyy-MM-dd') and";
					}else if("te".equals(tf[0])){
						w = w+" to_date(extract_time,'yyyy-MM-dd hh24:mi:ss')<=to_date('"+tf[1]+"','yyyy-MM-dd') and";
					}else if("tasku".equals(tf[0])){
						w = w+ " task_url like '%"+tf[1]+"%' and";
					}else{
						if("null".equals(tf[1])){
							w = w+ " "+tf[0]+" like '%"+tf[1]+"%' or " +tf[0]+" is null and";
						}else{
							w = w+ " "+tf[0]+" like '%"+tf[1]+"%' and";
						}
					}
				}
				
			}
			w = w.substring(0, w.lastIndexOf("and"));
		}
		String ruleid = "";
		if(listc.size()>0){
			ruleid = listc.get(0).getRuleId();
		}
		List<ElementExtractRule> list = elementExtractRuleService.getByPageExtractRule(ruleid);
		int startnum = (yeshu-1)*10;
		int endnum = (yeshu-1)*10+10;
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb_option = new StringBuffer();
		StringBuffer sb_check = new StringBuffer();
		sb.append("<th>序号</th>");
		sb.append("<th>任务ID</th>");
		sb.append("<th>提取时间</th>");
		sb.append("<th  class=\"content_rwgl_pzurl\">任务URL</th>");
		sb.append("<th>采集时间</th>");
		sb_check.append("<input type='checkbox' id='input-0' name='ischeck' value='task_url'>  <label for='input-0'>任务URL</label></input>&nbsp;&nbsp;&nbsp;&nbsp;");
		List<String> list4 = new ArrayList<String>();
		int j = 1;
		for(ElementExtractRule er:list){
			sb.append("<th>"+er.getStoreColumnComment()+"</th>");
			list4.add(er.getStoreColumnName());
			sb_option.append("<option value='"+er.getStoreColumnName()+"'>"+er.getStoreColumnComment()+"</option>");
			sb_check.append("<input type='checkbox' id='input-"+j+"' name='ischeck' value='"+er.getStoreColumnName()+"'>  <label for='input-"+j+"'>"+er.getStoreColumnComment()+"</label></input>&nbsp;&nbsp;&nbsp;&nbsp;");
			j++;
		}
		List<Map<String,Object>> list1 = dataSourceService.getListTable(bm,startnum,endnum,w);
		int i = 1;
		for(Map<String,Object> map : list1){
			sb2.append(" <tr ><td>"+i+"</td>");
			sb2.append(" <td>"+map.get("TASK_ID")+"</td>");
			sb2.append("<td>"+map.get("EXTRACT_TIME")+"</td>");
			sb2.append("<td  class=\"content_rwgl_pzurl\">"+map.get("TASK_URL")+"</td>");
			sb2.append("<td>"+map.get("FETCH_TIME")+"</td>");
			
			for(String s : list4){
				String ss = map.get(s.toUpperCase())+"";
				if(ss.length()>40){
					sb2.append("<td>"+ss.substring(0,40)+"<a data-toggle=\"tooltip\" data-placement=\"top\" title='"+ss+"'>...</a></td>");
				}else{
					sb2.append("<td>"+ss+"</td>");
				}
			}
			sb2.append(" </tr >");
			i++;
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("td", sb2);
		map.put("th", sb);
		map.put("yeshu", yeshu);
		String n = dataSourceService.getNums(bm.toUpperCase(),w);
		
		int zs = 0 ;
		if(n!=null){
			zs= Integer.valueOf(n);
		}
		map.put("zys",zs);
		map.put("bm", bm);
		map.put("ruleid", ruleid);
		map.put("option", sb_option.toString());
		map.put("check", sb_check.toString());
		return map;
	}
	/**
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hiveshows", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> hiveshows(String bm,String type) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Client c = Client.create();
		String src = "";
		if("main".equals(type)){
			src = "order/show/ext_global_main_*";
		}else{
			src = "order/show/ext_global_extract_*";
		}
		WebResource yr=c.resource(resturl+src);
		String ys = yr.get(String.class);
		String[] yss = ys.split("\r");
		String yrs = "";
		for(String ysr:yss){
			yrs += ysr+";";
		}
		map.put("ybiao", yrs);
		WebResource r=c.resource(resturl+"order/show/hdfs_"+bm.toLowerCase()+"_*");
		String s = r.get(String.class);
		String[] ss = s.split("\r");
		String rs = "";
		for(String sr:ss){
			rs += sr+";\n";
		}
		map.put("pbiao", rs);
	    return  map;
	}
	/**
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hivehbase", method = RequestMethod.POST)
	@ResponseBody
	public String hivehbase(String id,String hbtable) throws Exception {
		Client c = Client.create();
		WebResource r=c.resource(resturl+"order/create?taskId="+id+"&htable="+hbtable);
		String s = r.get(String.class);
	    return  s;
	}
	/**
	 * 导出数据 复制hdfs
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hdfs", method = RequestMethod.POST)
	@ResponseBody
	public String hdfs(String n,String w,final String bm,HttpServletRequest request)  {
		HttpSession session = request.getSession();
		final String username = session.getAttribute(Sessions.SESSION_USER).toString();
		Client c = Client.create();
		String s ="";
		try {
			w = URLEncoder.encode(w, "UTF-8");
			final String time = TimeCompare.turnTime3(new Date());
			WebResource r=c.resource(resturl+"order/paste/"+n+"?timestamp="+time+"&where="+w+"&bm="+bm+"&user="+username);
			s = r.get(String.class);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		c.destroy();
	    return  s;
	}

	/**
	 * 导出主版本库数据
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hdfsm", method = RequestMethod.POST)
	@ResponseBody
	public String hdfsm(String n,String w,final String bm,HttpServletRequest request)  {
		HttpSession session = request.getSession();
		final String username = session.getAttribute(Sessions.SESSION_USER).toString();
		Client c = Client.create();
		String s ="";
		try {
			w = URLEncoder.encode(w, "UTF-8");
			final String time = TimeCompare.turnTime3(new Date());
			WebResource r=c.resource(resturl+"order/pasteM/"+n+"?timestamp="+time+"&where="+w+"&bm="+bm+"&user="+username);
			s = r.get(String.class);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		c.destroy();
	    return  s;
	}
	/**
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sqoop", method = RequestMethod.POST)
	@ResponseBody
	public String sqoop(String n,String o,final String id,final String ruleid) throws Exception {
		String s ="";
		try{
			Client c = Client.create();
			WebResource r=c.resource(resturl+"order/sqoop/"+n+"/"+o+"/"+id);
			s = r.get(String.class);
			System.out.println("结果完成："+s);
			if(s.indexOf("完成")>-1){
				System.out.println("执行select");
				Client cc = Client.create();
				WebResource r1=cc.resource(resturl+"order/select/"+n+"?where=limit%201");
				String ss = r1.get(String.class);
				cc.destroy();
				System.out.println("select:"+ss);
				if(ss!=null&&!"".equals(ss)){
					String[] sss = ss.split("\t");
					String key = sss[0];
					System.out.println("key:"+key);
					final String instansid = key.substring(key.lastIndexOf("_")-36,key.lastIndexOf("_"));
					 //这里用一个线程就是异步，  
			        new Thread(new Runnable() {  
			            @Override  
			            public void run() {  
			            	saveKafka(id,instansid,ruleid);
			            }  
			        }).start(); 
				}
		}
				c.destroy();
		}catch (Exception e){
			return e.getMessage();
		}
	    return  s;
	}
	/**
	 * 将hive 数量存入kafka
	 */
	public void saveKafka(String taskid,String insid,String ruleid){
		
		String r = progress(taskid);
		PrecMonitor pm = new PrecMonitor();
		if(r.indexOf("Export job failed")>-1){
			pm.setExportNum("fail");
		}else if(r.indexOf("Export job failed")==-1&&r.indexOf("Exported")>-1){
			pm.setExportNum("success");
		}else{
			return ;
		}
		pm.setTaskId(taskid);
		pm.setInstanceId(insid);
		pm.setRuleId(ruleid);
		pm.setKafkaTopic(TOPIC_DATA_MONITOR);
		pm.setTimeStamp(System.currentTimeMillis()+"");
		pm.setStage(PROCESS_STAGE_PREC);
		
		int partition = KafkaProducerClient
				.caculatePartition(TOPIC_DATA_MONITOR,
						pm.getKafkaTopic().hashCode(), 0L);
		
		Object obj = KafkaProducerClient.sendToKafka(TOPIC_DATA_MONITOR,
				partition,
				pm.getKafkaTopic()+"|export|"+pm.getStage(),
				pm);
	}
	/**
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/progress", method = RequestMethod.POST)
	@ResponseBody
	@Produces("text/plain;charset=utf-8")
	public String progress(String id) {
		String s ="";
		try{
			Client c = Client.create();
			WebResource r=c.resource(resturl+"order/progress/"+id);
			s = r.get(String.class);
			c.destroy();
		}catch (Exception e){
			return e.getMessage();
		}
	    return  s;
	}
	/**
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dis", method = RequestMethod.POST)
	@ResponseBody
	@Produces("text/plain;charset=utf-8")
	public int distinct(final String bm,String where,final String taskid,final String instanceid,final String ruleid) throws Exception {
		String[] ws = where.split(";");
		String w = "";
		for(String s : ws){
			w += s+"= t."+s+" and ";
		}
		if(!"".equals(w)){
			if(!"".equals(instanceid)){
				w = " where INSTANCE_ID = "+instanceid +" and "+w.substring(0,w.lastIndexOf("and"));
			}else{
				w = " where "+w.substring(0,w.lastIndexOf("and"));
			}
		}
		final int r = dataSourceService.getDis(bm, w);
		
		if(r>-1){
			 //这里用一个线程就是异步，  
	        new Thread(new Runnable() {  
	            @Override  
	            public void run() {  
	            	String zs = dataSourceService.getNums(bm,"");
	            	yuchuli(taskid,instanceid,ruleid,zs,r+"","dis");
	            }  
	        }).start(); 
		}
	    return r;
	}
	/**
	 * 将hive 数量存入kafka
	 */
	public void yuchuli(String taskid,String insid,String ruleid,String zs,String r,String pro){
		
		HandleMonitor hm = new HandleMonitor();
		hm.setHandleNum(r);
		hm.setTableNum(zs);
		hm.setTaskId(taskid);
		hm.setInstanceId(insid);
		hm.setRuleId(ruleid);
		hm.setKafkaTopic(pro);
		hm.setTimeStamp(System.currentTimeMillis()+"");
		hm.setStage(PROCESS_STAGE_HANDLE);
		
		int partition = KafkaProducerClient
				.caculatePartition(TOPIC_DATA_MONITOR,
						hm.getKafkaTopic().hashCode(), 0L);
		
		Object obj = KafkaProducerClient.sendToKafka(TOPIC_DATA_MONITOR,
				partition,
				hm.getKafkaTopic()+"|"+pro+"|"+hm.getStage(),
				hm);
	}
	
	/**
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/plup", method = RequestMethod.POST)
	@ResponseBody
	@Produces("text/plain;charset=utf-8")
	public int plupdate(final String bm,String col,String set,@PathParam("where")String where,final String taskid,final String instanceid,final String ruleid) throws Exception {
		String w = "";
		if(!"".equals(where)&&where!=null){
			w = "where ";
			String[] ws = where.split(";");
			for(String wf:ws){
				String[] tf = wf.split("=");
				if(tf.length==2){
					
					if("null".equals(tf[1])){
						w = w+ " "+tf[0]+" = '"+tf[1]+"' or " +tf[0]+" is null and";
					}else{
						w = w+ " "+tf[0]+" = '"+tf[1]+"' and";
						
					}
				}
				
			}
			w = w.substring(0, w.lastIndexOf("and"));
		}
		String s = "";
		s = col+" = '"+set+"'";
		
		final int r = dataSourceService.getPlupdateTable(bm, s,w);
		
		if(r>-1){
			 //这里用一个线程就是异步，  
	        new Thread(new Runnable() {  
	            @Override  
	            public void run() {  
	            	String zs = dataSourceService.getNums(bm,"");
	            	yuchuli(taskid,instanceid,ruleid,zs,r+"","update");
	            }  
	        }).start(); 
		}
	    return r;
	}
	/**
	 * 
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pldel", method = RequestMethod.POST)
	@ResponseBody
	@Produces("text/plain;charset=utf-8")
	public int pldel(final String bm,String where,final String taskid,final String instanceid,final String ruleid) throws Exception {
		
		String w = "";
		if(!"".equals(where)&&where!=null){
			w = "where ";
			String[] ws = where.split(";");
			for(String wf:ws){
				String[] tf = wf.split("=");
				if(tf.length==2){
					
					if("null".equals(tf[1])){
						w = w+ " "+tf[0]+" = '"+tf[1]+"' or " +tf[0]+" is null and";
					}else{
						w = w+ " "+tf[0]+" = '"+tf[1]+"' and";
						
					}
				}
				
			}
			w = w.substring(0, w.lastIndexOf("and"));
		}
		
		final int r = dataSourceService.getPlDelTable(bm,w);
		if(r>-1){
			 //这里用一个线程就是异步，  
	        new Thread(new Runnable() {  
	            @Override  
	            public void run() {  
	            	String zs = dataSourceService.getNums(bm,"");
	            	yuchuli(taskid,instanceid,ruleid,zs,r+"","del");
	            }  
	        }).start(); 
		}
	    return r;
	}
	/**
	 * ExcelExport
	 */
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	@ResponseBody
	@Produces("text/plain;charset=utf-8")
	public int excelExport(String id,String bm,String we,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String w = "";
		if(!"".equals(we)&&we!=null){
			w = "where ";
			String[] ws = we.split(";");
			for(String wf:ws){
				String[] tf = wf.split("=");
				if(tf.length==2){
					if("ts".equals(tf[0])){
						w = w+" to_date(extract_time,'yyyy-MM-dd hh24:mi:ss')>to_date('"+tf[1]+"','yyyy-MM-dd') and";
					}else if("te".equals(tf[0])){
						w = w+" to_date(extract_time,'yyyy-MM-dd hh24:mi:ss')<=to_date('"+tf[1]+"','yyyy-MM-dd') and";
					}else if("tasku".equals(tf[0])){
						w = w+ " task_url like '%"+tf[1]+"%' and";
					}else{
						if("null".equals(tf[1])){
							w = w+ " "+tf[0]+" like '%"+tf[1]+"%' or " +tf[0]+" is null and";
						}else{
							w = w+ " "+tf[0]+" like '%"+tf[1]+"%' and";
						}
					}
				}
				
			}
			w = w.substring(0, w.lastIndexOf("and"));
		}
		List<CjSysNotes> listc = dataSourceService.getruleId(bm);
		String ruleid = "";
		if(listc.size()>0){
			ruleid = listc.get(0).getRuleId();
		}
		List<ElementExtractRule> list = elementExtractRuleService.getByPageExtractRule(ruleid);
		List<String> listheader = new ArrayList<String>();
		listheader.add("任务ID");
		listheader.add("提取时间");
		listheader.add("任务URL");
		listheader.add("采集时间");
		List<String> list4 = new ArrayList<String>();
		for(ElementExtractRule er:list){
			listheader.add(er.getStoreColumnComment());
			list4.add(er.getStoreColumnName());
		}
		List<Map<String,Object>> list1 = dataSourceService.getListTable(bm,0,1000000000,w);
		
		int r = 0;
		OutputExcel oe = new OutputExcel();
		oe.excel(bm,list1,listheader,list4,request,response);
	    return r;
	}
	/**
	 * 自定义sql
	 */
	@RequestMapping(value = "/zdysql", method = RequestMethod.POST)
	@ResponseBody
	public int zdysql(String sql){
		return dataSourceService.getZdySql(sql);
	}
	/**
	 * 
	 */
	@RequestMapping(value = "/cxbzd", method = RequestMethod.POST)
	@ResponseBody
	public String cxbzd(String bm){
		List<String> list = dataSourceService.getCxzd(bm.toUpperCase());
		String r = "";
		for(String l:list){
			r+=l+";";
		}
		return r;
	}
	@RequestMapping(value = "/cxinstance", method = RequestMethod.POST)
	@ResponseBody
	public List<TaskInstance> cxinstance(String taskid){
		List<TaskInstance> list = dataSourceService.getInstace(taskid);
		return list;
	}
	/**
	 * 删除hive 表
	 */
	@RequestMapping(value = "/drophive", method = RequestMethod.POST)
	@ResponseBody
	public String dropHive(String tables){
		String s ="";
		try{
			Client c = Client.create();
			WebResource r=c.resource(resturl+"order/drop?table="+tables);
			
			s = r.get(String.class);
			c.destroy();
		}catch (Exception e){
			return e.getMessage();
		}
		return s;
	}
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test () {
		try{
			Client c = Client.create();
			String taskid  = "164";
			String insid = "fc37c35b-a3fa-4b97-b1b5-f589cc4438d3";
			int num = 3998;
			WebResource r=c.resource("http://172.22.5.2:7001/rest/order/autoIn?taskid="+taskid+"&insid="+insid+"&sumnum="+num);
			String s = r.get(String.class);
			System.out.println("rr:;"+s);
			c.destroy();
			return s;
		}catch (Exception e){
		}	
		return null;
	}
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();  
        Client client = Client.create(config);  
	    WebResource service = client.resource(getBaseURI()+"list/sssda");
	    String ss = service.get(String.class);
	    System.out.println(ss);
//	     Create 
//	    CrawlRequest crawlq = new CrawlRequest();
//	    String url1 = "http://www.dianping.com/search/category/1/10/g110r5#nav-tab|0|1";
//	    crawlq.setUrl(url1);
//	    crawlq.setTimeout(50000);
//	    crawlq.setChangecrawl(1);
//		crawlq.setRetryHandler(3);
//		crawlq.setDownloadType(0);
//		MultivaluedMapImpl mmi = new MultivaluedMapImpl();
//		mmi.add("crawlq", crawlq);
//	    String response = service.entity(crawlq,MediaType.APPLICATION_JSON).post(String.class);
//	    System.out.println("rr::"+response);
//	    MultivaluedMap<String, String> param = new MultivaluedMapImpl();  
//        param.add("username", "ssss");  
//        System.out.println(service.path("bean").queryParams(param)  
//                .type(MediaType.APPLICATION_FORM_URLENCODED).post(CrawlResult.class));  
  }
	 private static URI getBaseURI() {  
	        return UriBuilder.fromUri(  
	                "http://localhost:7001/crawl/tools/").build();  
	    }  
	/**
	 * 查询预处理当前状态
	 */
	@RequestMapping(value = "/processLook", method = RequestMethod.POST)
	@ResponseBody
	public String processLook(String instanceId){
		TaskInstanceKey instanceKey = new TaskInstanceKey();
		instanceKey.setInstanceId(instanceId);
		instanceKey.setStage(PROCESS);
		TaskInstance instance = instanceService.selectByPrimaryKey(instanceKey);
		if (instance == null)
			return "不存在实例" + instanceId + "的预处理阶段";
		return instance.getStatus()+"";
		
	}
	/**
	 * 预处理监控
	 */
	@RequestMapping(value = "/processMon", method = RequestMethod.POST)
	@ResponseBody
	public String processMon(String instanceId,String s,HttpServletRequest request){
		String user = "";
		HttpSession session = request.getSession();
		 if(session!=null){
	        	Object o = session.getAttribute(Sessions.SESSION_USER);
	        	if(o!=null){
	        		 user = o.toString();
	        	}
	        	
	        }
//		TaskInstanceKey instanceKey = new TaskInstanceKey();
//		instanceKey.setInstanceId(instanceId);
//		instanceKey.setStage(PROCESS);
		TaskInstance instance = new TaskInstance();
//		if (instance == null)
//			return "不存在实例" + instanceId + "的预处理阶段";
		instance.setInstanceId(instanceId);
		if((TaskConstant.TASK_WAIT+"").equals(s)){
			instance.setStatus(TaskConstant.TASK_WAIT);
			instance.setHandledPercent(new BigDecimal(0));
		}else{
			instance.setStatus(TaskConstant.TASK_STOP);
			instance.setHandledPercent(new BigDecimal(100));
		}
		instance.setStage(PROCESS);
		instance.setTotalAmount(new BigDecimal(0));
		instance.setHandledAmount(new BigDecimal(0));
		
		instance.setPublisherId(user);
		instance.setFinishTime(new Date());
		int i = instanceService.update(instance);
		if(i>0){
			return "操作成功!";
		}else{
			return "操作失败!";
		}
	}
	/**
	 * 查询清洗当前状态
	 */
	@RequestMapping(value = "/handleLook", method = RequestMethod.POST)
	@ResponseBody
	public String handleLook(String instanceId){
		TaskInstanceKey instanceKey = new TaskInstanceKey();
		instanceKey.setInstanceId(instanceId);
		instanceKey.setStage(HANDLE);
		TaskInstance instance = instanceService.selectByPrimaryKey(instanceKey);
		if (instance == null)
			return "不存在实例" + instanceId + "的清洗处理阶段";
		return instance.getStatus()+"";
		
	}
	/**
	 * 预处理监控
	 */
	@RequestMapping(value = "/handleMon", method = RequestMethod.POST)
	@ResponseBody
	public String handleMon(String instanceId,String s,HttpServletRequest request){
		String user = "";
		HttpSession session = request.getSession();
		 if(session!=null){
	        	Object o = session.getAttribute(Sessions.SESSION_USER);
	        	if(o!=null){
	        		 user = o.toString();
	        	}
	        	
	        }
//		TaskInstanceKey instanceKey = new TaskInstanceKey();
//		instanceKey.setInstanceId(instanceId);
//		instanceKey.setStage(PROCESS);
		TaskInstance instance = new TaskInstance();
//		if (instance == null)
//			return "不存在实例" + instanceId + "的预处理阶段";
		instance.setInstanceId(instanceId);
		if((TaskConstant.TASK_WAIT+"").equals(s)){
			instance.setStatus(TaskConstant.TASK_WAIT);
			instance.setHandledPercent(new BigDecimal(0));
		}else{
			instance.setStatus(TaskConstant.TASK_STOP);
			instance.setHandledPercent(new BigDecimal(100));
		}
		instance.setStage(HANDLE);
		instance.setTotalAmount(new BigDecimal(0));
		instance.setHandledAmount(new BigDecimal(0));
		
		instance.setPublisherId(user);
		instance.setFinishTime(new Date());
		int i = instanceService.update(instance);
		if(i>0){
			return "操作成功!";
		}else{
			return "操作失败!";
		}
	}
	/**
	 * oracle 导入hbase
	 */
	@RequestMapping(value = "/imports", method = RequestMethod.POST)
	@ResponseBody
	public String imports(String n,final String id,String where) throws Exception {
		System.out.println("nnn::"+n+"   id:"+id+"   where:"+where);
		String s ="";
		try{
			Client c = Client.create();
			where = URLEncoder.encode(where, "UTF-8");
			WebResource r=c.resource(resturl+"order/mainIm?oTable="+n+"&taskid="+id+"&where="+where);
			s = r.get(String.class);
			System.out.println("结果完成："+s);
				c.destroy();
		}catch (Exception e){
			return e.getMessage();
		}
	    return  s;
	}
	/**
	 * 
	 * @param 查询导入hbase 进度
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/catim", method = RequestMethod.POST)
	@ResponseBody
	@Produces("text/plain;charset=utf-8")
	public String catim(String id) {
		String s ="";
		try{
			Client c = Client.create();
			WebResource r=c.resource(resturl+"order/catim/"+id);
			s = r.get(String.class);
			c.destroy();
		}catch (Exception e){
			return e.getMessage();
		}
	    return  s;
	}
	/**
	 *
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mainM", method = RequestMethod.GET)
	public ModelAndView mainM(String id) throws Exception {
		String cs = "Z"+id.toUpperCase()+"_%";
		List<String> list = dataSourceService.getUserTables(cs);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("bm", ("Z"+id+"_"));
		map.put("taskid", id);
		return request("/sys/dataManage/mainClean",map);
	}
	/**
	 * 交付库导入
	 */
	public String importsJ(String n,final String id,String where,String publishId) throws Exception {
		System.out.println("nnn::"+n+"   id:"+id+"   where:"+where);
		String s ="";
		try{
			Client c = Client.create();
			where = URLEncoder.encode(where, "UTF-8");
			WebResource r=c.resource(resturl+"order/deliverIm?oTable="+n+"&taskid="+id+"&where="+where+"&publishId="+publishId);
			s = r.get(String.class);
			System.out.println("结果完成："+s);
				c.destroy();
		}catch (Exception e){
			return e.getMessage();
		}
	    return  s;
	}
	
	/**
	 * hdfs清洗
	 */
	@RequestMapping(value = "/hdfsClean", method = RequestMethod.POST)
	@ResponseBody
	@Produces("text/plain;charset=utf-8")
	public int hdfsClean(String taskId) throws Exception {
		String s ="";
		try{
			Client c = Client.create();
			String url = "";
			if("".equals(taskId)){
				url = resturl+"order/show/hdfs_*";
			}else{
				url = resturl+"order/show/hdfs_o"+taskId+"_*";
			}
			WebResource r=c.resource(url);
			s = r.get(String.class);
			String[] ss = s.split("\r");
			String rs = "";
			for(String sr:ss){
				rs += sr+";";
			}
			WebResource rr=c.resource(resturl+"order/drop?table="+rs);
			s = rr.get(String.class);
			if(s.indexOf("成功")>-1){
				return 1;
			}
			c.destroy();
		}catch (Exception e){
			e.printStackTrace();
		}
	    return  2;
	}
}
