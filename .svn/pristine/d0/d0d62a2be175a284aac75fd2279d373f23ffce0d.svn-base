package inspur.crawl.taskManage.service;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import inspur.crawl.common.tools.LoadProperty;
import inspur.crawl.dataManage.controller.DataSourceController;
import inspur.crawl.taskManage.mapper.CrawlerTaskMapper;
import inspur.crawl.taskManage.pojo.CrawlerTask;

/**
 * 周期检查执行采集任务的类
 * @author zhangyc
 */
@Service
public class TaskScheduleService {

	@Resource
	CrawlerTaskMapper taskMapper;
	@Resource
	TaskService taskService;
	
	/**
	 * 定时执行方法根据spring-servlet配置定期执行，每小时一次
	 */
	public void checkTaskSchedule() {
		List<CrawlerTask> tasks = taskMapper.selectScheduledTask();
		for(CrawlerTask task:tasks) {
			System.out.println("执行定期任务："+task.getTaskId()+" at "+new Date());
			taskService.startTask(task.getTaskId(), "admin");
		}
		
	}
	/**
	 * 定时执行方法根据spring-servlet配置定期执行，每天23点执行 清除hdfs
	 * @throws Exception 
	 */
	public void dropHdfsSchedule() throws Exception {
		System.out.println("执行定期任务：dropHdfs");
		DataSourceController dsc = new DataSourceController();
		dsc.hdfsClean("");
	}
	LoadProperty lp = new LoadProperty();
	Properties pro = lp.loadConfiguration("/restful.properties");
	String resturl = pro.getProperty("rest.url");
	/**
	 * 定时执行方法根据spring-servlet配置定期执行，每天24点执行 清除hdfs
	 * @throws Exception 
	 */
	public void insertHbaseSchedule() throws Exception {
		try{
			System.out.println("更新hbase开始");
			Client c = Client.create();
			WebResource r=c.resource(resturl+"order/onlineIm");
			r.get(String.class);
			WebResource r1=c.resource(resturl+"order/impUp");
			r1.get(String.class);
			c.destroy();
		}catch (Exception e){
		}
	}
	/**
	 * 定时执行方法根据spring-servlet配置定期执行，每天24点执行 清除hdfs
	 * @throws Exception 
	 */
	public void insertGoodsSchedule() throws Exception {
		try{
			System.out.println("更新goods开始");
			Client c = Client.create();
			WebResource r=c.resource(resturl+"order/impGoods");
			r.get(String.class);
			c.destroy();
		}catch (Exception e){
		}
	}
}
