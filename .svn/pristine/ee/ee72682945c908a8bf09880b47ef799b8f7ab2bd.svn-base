package inspur.crawl.dataManage.controller;

import inspur.crawl.common.controller.BaseController;
import inspur.crawl.common.interceptor.Page;
import inspur.crawl.dataManage.pojo.CjSysNotes;
import inspur.crawl.dataManage.pojo.CrawlerDataDeliver;
import inspur.crawl.dataManage.service.DataDeliverService;
import inspur.crawl.demandAna.pojo.CrawlerDemand;
import inspur.crawl.demandAna.service.DemandAnalyService;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.service.PageExtractRuleService;
import inspur.crawl.taskManage.controller.TaskConstant;
import inspur.crawl.taskManage.pojo.CrawlerTask;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.inspur.avro.model.java.DataDeliver;
import com.inspur.avro.model.java.UrlFetchRequest;
import com.inspur.kafka.main.KafkaProducerClient;

@RestController
@RequestMapping("/datadeliver")
public class DataDeliverController extends BaseController {
	@Resource
	DataDeliverService deliverService;
	@Resource
	DemandAnalyService daService;
	@Resource
	PageExtractRuleService perService;
	@InitBinder("datadeliver")  
	public void taskBinder(WebDataBinder binder) {  
	    binder.setFieldDefaultPrefix("datadeliver.");  
	}
	
	/**
	 * 跳转需求交付页面
	 */
	@RequestMapping(value = "/dataManage", method = RequestMethod.GET)
	public ModelAndView demandAdd() throws Exception {
		return request("sys/dataManage/dataDeliver");
	}
	
	/**
	 * 任务结果
	 */
	@RequestMapping(value = "/datalist", method = RequestMethod.POST)
	@ResponseBody
	public void taskresultimp(CrawlerTask crawlerTask, Page page, PrintWriter out) throws Exception {
		List<CrawlerDemand> demandlist = daService.queryDemand();
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(CrawlerDemand da : demandlist){
			sb.append("{id:'"+da.getDemandId()+"',pId:'demand',"+"name:'"+da.getDemandName()+"',nocheck: true,isParent:true, checked: false},");
			//通过demandId获取task
			List<CrawlerTask> tasklist = deliverService.queryTasks(da.getDemandId());
			for(CrawlerTask ct : tasklist){
				sb.append("{id:'"+ct.getTaskId()+"',pId:'"+da.getDemandId()+"',name:'"+ct.getTaskName()+"',nocheck: true, isParent:true, checked: false},");
				//通过taskId获得rule
				List<PageExtractRule> perlist = perService.queryTaskId(ct.getTaskId().toString());
				for (PageExtractRule per : perlist) {
					sb.append("{id:'"+per.getId()+"',pId:'"+per.getTaskId()+"',name:'"+per.getName()+"',nocheck: true, isParent:true, checked: false},");
					String cs = "Z"+ct.getTaskId().toString().toUpperCase()+"_%";
					//通过ruleId获得tablename
					List<CjSysNotes> cjlist = deliverService.queryTable(per.getId(),cs);
					for(CjSysNotes cj : cjlist){
						sb.append("{id:'"+cj.getTableName()+"',pId:'"+cj.getRuleId()+"',name:'"+cj.getTableName()+"',flag:'1',checked: false},");
					}
				}
			}
		}
		if(demandlist.size()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		out.print(sb.toString());
	}
	/**
	 * 保存交付数据
	 */
	@RequestMapping(value = "/savedata", method = RequestMethod.POST)
	@ResponseBody
	public void saveData(HttpServletRequest request, CrawlerDataDeliver cdd, @RequestParam String demandId, @RequestParam String tableName, PrintWriter out) throws Exception{
		DataSourceController dsc = new DataSourceController();
		int i = 0;
		String current = "";
		try { 
			String[] tableNameArr = tableName.split(";");
			String[] demandIdArr = demandId.split(";");
			List<String> list = new ArrayList<String>();  
		    for(int j = 0; j < demandIdArr.length; j++) {  
		        if(!list.contains(demandIdArr[i])) {  
		            list.add(demandIdArr[i]);  
		        }  
		    }
			for(int j=0;j<list.size();j++){
				String tableNameN = "";
				for (int k = 0; k < tableNameArr.length; k++) {
					if (tableNameArr[k].contains(list.get(j))) {
						String n = tableNameArr[k].replace(list.get(j)+":", "");
						tableNameN += n+";"; 
					}
				}
				current = System.currentTimeMillis() + "";
				cdd.setDemandId(list.get(j));
				cdd.setPublishId(current);
				cdd.setTableName(tableNameN);
				cdd.setDeliverTime(new Date());
				cdd.setStatus("2");
				i = deliverService.saveData(cdd);
				
				DataDeliver deliver = new DataDeliver();
				deliver.setDemandId(list.get(j));
				deliver.setPublishId(current);
				deliver.setTableName(tableNameN);
				deliver.setDeliverStage("2");
				//发送到kafka
				deliverService.sendDataDeliver(deliver);
				String[] tns = tableNameN.split(";");
				for(String tn:tns){
					String ss = dsc.importsJ(tn, tn.split("_")[0].substring(1), "",current);
					System.out.println(ss);
				}
				System.out.println("sendDataDeliver----------------:"+deliver);
			}
			
	     } catch (Exception e) {  
	          e.printStackTrace();     
	     } 
		out.print(i);
	}
	
}
