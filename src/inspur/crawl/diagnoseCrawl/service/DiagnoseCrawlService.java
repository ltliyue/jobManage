package inspur.crawl.diagnoseCrawl.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloudera.impala.jdbc41.DataSource;

import inspur.crawl.common.interceptor.Page;
import inspur.crawl.diagnoseCrawl.mapper.DiagnoseCrawlMapper;
import inspur.crawl.diagnoseCrawl.mapper.ParseRuleDiaMapper;
import inspur.crawl.diagnoseCrawl.pojo.ParsePUrl;
import inspur.crawl.diagnoseCrawl.pojo.ParseRuleDia;
import inspur.crawl.diagnoseCrawl.pojo.TaskAndTaskInstance;
import inspur.crawl.ruleManage.mapper.ParseRuleMapper;
import inspur.crawl.ruleManage.pojo.ParseRule;

@Service
public class DiagnoseCrawlService {
	public static Connection connection() throws Exception{
		Connection connection=null;
		Class.forName("com.cloudera.impala.jdbc41.Driver");
		DataSource ds=new DataSource();
		ds.setURL("jdbc:impala://qing:21050");
		connection=ds.getConnection();
		return connection;
	}
	@Resource
	DiagnoseCrawlMapper rm;
	@Resource
	ParseRuleDiaMapper pr;
	public List<ParseRuleDia> listPage(ParseRuleDia record){
		List<ParseRuleDia> list = pr.listPage(record);
		return list;
	}
	public List<TaskAndTaskInstance> getInstanceID(String  task_id) throws Exception {
		List<TaskAndTaskInstance> list = rm.getInstanceID(task_id);
	    return list;
	} 
	public List<TaskAndTaskInstance> getRule(String task_id){
		List<TaskAndTaskInstance> list = rm.getRule(task_id);
		return list;		
	}
	public TaskAndTaskInstance getDetail(String rule_id){
		TaskAndTaskInstance tti =rm.getDetail(rule_id);
		return tti;
	}
	public List<ParseRuleDia> listPageBySearch(ParseRuleDia record, Page page){
		List<ParseRuleDia> list = pr.listPageBySearch(record, page);
		return list;
	}
	public List<ParsePUrl> getPUrlList(ParsePUrl record){
		String page=record.getPager();
		String offset=(Integer.parseInt(page)-1)*10+"";
		List<ParsePUrl> list = new ArrayList<ParsePUrl>();
		String sqlStatement="select p_url,count(p_url) p_num from url_relation_partition where task_instance_id='"+record.getTask_instance_id()+"' and ruleid='"+record.getRule_id()+"' group by p_url "
				+ " order by p_num limit 10 offset "+offset+";";
		System.out.println(sqlStatement);
		try {		
				Statement stmt = connection().createStatement();
				ResultSet rs = stmt.executeQuery(sqlStatement);	
				System.out.println("\n== Begin Query Results ======================");	
				while (rs.next()) {
					ParsePUrl ppu = new ParsePUrl();
					ppu.setP_url(rs.getString(1));
					ppu.setNum(rs.getString(2));;
					list.add(ppu);
				}		
				System.out.println("== End Query Results =======================\n\n");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
				} catch (Exception e) {
					// swallow
				}
			}
		return list;	
	}
	public List<ParsePUrl> getPUrlList1(ParsePUrl record){
		String page=record.getPager();
		String offset=(Integer.parseInt(page)-1)*10+"";
		List<ParsePUrl> list = new ArrayList<ParsePUrl>();
		String sqlStatement=null;
		if(record.getP_url() == null)
			sqlStatement="select p_url,c_url from url_relation_partition urp where task_instance_id='"+record.getTask_instance_id()
					+"' and ruleid='"+record.getRule_id()  
					+"' and urp.c_url not in"
					+"(select url from parse_statistics_test6 where taskinstanceid='"+record.getTask_instance_id()
					+"' ) order by p_url limit 10 offset "+offset;
		else
			sqlStatement="select p_url,c_url from url_relation_partition urp where task_instance_id='"+record.getTask_instance_id()
			+"' and ruleid='"+record.getRule_id()+"' and p_url='"+record.getP_url() 
			+"' and urp.c_url not in"
			+"(select url from parse_statistics_test6 where taskinstanceid='"+record.getTask_instance_id()
			+"' ) order by p_url limit 10 offset "+offset;
		System.out.println(sqlStatement);
		try {		
				Statement stmt = connection().createStatement();
				ResultSet rs = stmt.executeQuery(sqlStatement);	
				System.out.println("\n== Begin Query Results ======================");	
				while (rs.next()) {
					ParsePUrl ppu = new ParsePUrl();
					ppu.setP_url(rs.getString(1));
					ppu.setC_url(rs.getString(2));
					list.add(ppu);
				}		
				System.out.println("== End Query Results =======================\n\n");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
				} catch (Exception e) {
					// swallow
				}
			}
		return list;	
	}
	public int getPUrlPage(ParsePUrl record){
		int num=0;
		String page=record.getPager();
		String offset=(Integer.parseInt(page)-1)*10+"";
		List<ParsePUrl> list = new ArrayList<ParsePUrl>();
		String sqlStatement="select count(distinct(p_url)) from url_relation_partition where task_instance_id='"+record.getTask_instance_id()+"' and ruleid='"+record.getRule_id()+"' ;";
		System.out.println(sqlStatement);
		try{
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);	
			while (rs.next()) {
				num=rs.getInt(1);
			}
			System.out.println(num);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		if(num!=0&&num%10==0)
			num/=10;
		else
			num=num/10+1;
		return num;		
	}
	public int getPUrlPage1(ParsePUrl record){
		int num=0;
		String page=record.getPager();
		String offset=(Integer.parseInt(page)-1)*10+"";
		List<ParsePUrl> list = new ArrayList<ParsePUrl>();
		String sqlStatement="select count(*) from  (select p_url,c_url from url_relation_partition urp where task_instance_id='"+record.getTask_instance_id()
		+"' and ruleid='"+record.getRule_id()  
		+"' and urp.c_url not in"
		+"(select url from parse_statistics_test6 where taskinstanceid='"+record.getTask_instance_id()
		+"' ) ) as  t1";
		System.out.println(sqlStatement);
		try{
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);	
			while (rs.next()) {
				num=rs.getInt(1);
			}
			System.out.println(num);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		if(num!=0&&num%10==0)
			num/=10;
		else
			num=num/10+1;
		return num;		
	}
	public List<ParsePUrl> getPUrlListBypurl(ParsePUrl record){
		String page=record.getPager();
		String offset=(Integer.parseInt(page)-1)*10+"";
		List<ParsePUrl> list = new ArrayList<ParsePUrl>();
		String sqlStatement="select p_url,count(p_url) from url_relation_partition "
				+ "where task_instance_id='"+record.getTask_instance_id()+"' and ruleid='"+record.getRule_id()+"' and p_url='"+record.getP_url()+"' group by p_url";
		System.out.println(sqlStatement);
		try {		
				Statement stmt = connection().createStatement();
				ResultSet rs = stmt.executeQuery(sqlStatement);	
				System.out.println("\n== Begin Query Results ======================");	
				while (rs.next()) {
					ParsePUrl ppu = new ParsePUrl();
					ppu.setP_url(rs.getString(1));
					ppu.setNum(rs.getString(2));;
					list.add(ppu);
				}		
				System.out.println("== End Query Results =======================\n\n");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
				} catch (Exception e) {
					// swallow
				}
			}
		return list;	
	}
	public int getPUrlListNum(ParsePUrl record){
		int num=0;
		String sqlStatement="select count(c_url) from url_relation_partition where task_instance_id='"+record.getTask_instance_id() 
				+"' and p_url='"+record.getP_url()+"' "
				+"and ruleid='"+record.getRule_id()+"' "
				+"and c_url not in "
				+"(select url from parse_statistics_test6 where taskinstanceid='"+record.getTask_instance_id()+"' )";
		System.out.println(sqlStatement);
		try{
			Statement stmt = connection().createStatement();
			ResultSet rs = stmt.executeQuery(sqlStatement);	
			while (rs.next()) {
				num=rs.getInt(1);
			}
			System.out.println(num);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				// swallow
			}
		}
		return num;		
	}
}
