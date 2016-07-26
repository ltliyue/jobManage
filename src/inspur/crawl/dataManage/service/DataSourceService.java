package inspur.crawl.dataManage.service;



import inspur.crawl.common.tools.Numbers;
import inspur.crawl.dataManage.mapper.CjSysNotesMapper;
import inspur.crawl.dataManage.data_mapper.DataSourceMapper;
import inspur.crawl.dataManage.pojo.CjSysNotes;
import inspur.crawl.dataManage.pojo.CjSysNotesCriteria;
import inspur.crawl.dataManage.pojo.DataSource;
import inspur.crawl.dataManage.pojo.DataSourceCriteria;
import inspur.crawl.taskManage.mapper.TaskInstanceMapper;
import inspur.crawl.taskManage.pojo.TaskInstance;
import inspur.crawl.taskManage.pojo.TaskInstanceCriteria;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class DataSourceService {
	@Resource
	DataSourceMapper dataSourceMapper;
	@Resource
	CjSysNotesMapper cjsysNotesMapper;
	@Resource
	TaskInstanceMapper iMapper;
	
	public List<DataSource> getListData() throws Exception {
		DataSourceCriteria dsc = new DataSourceCriteria();
		dsc.setOrderByClause(" create_time ");
		List<DataSource> list = dataSourceMapper.selectByExample(dsc);
		return list;
	}
	public List<String> getUserTables(String bm) throws Exception {
		return dataSourceMapper.selectUserTables(bm);
	}
	public List<Map<String,Object>> getListTable(String bm,int startnum,int endnum,String where)throws Exception {
		return dataSourceMapper.selectListTable(bm,startnum,endnum,where);
	}
	public int getAddData(DataSource ds)throws Exception{
		//生成19位UUID
		  String id = Numbers.uuid();
		  ds.setId(id);
		  ds.setCreateTime(new Date());
		return dataSourceMapper.insert(ds);
	}
	public int getDelData(String id)throws Exception{
		DataSourceCriteria dsc = new DataSourceCriteria();
		dsc.createCriteria().andIdEqualTo(id);
		return dataSourceMapper.deleteByExample(dsc);
	}
	public int getAddTable(String table)throws Exception{
		
		return dataSourceMapper.createTable(table);
	}
	public String getNums(String bm,String where){
		return dataSourceMapper.selectUserTableNum(bm,where);
	}
	public int getDis(String bm,String where){
		return dataSourceMapper.disColumn(bm,where);
	}
	public int getPlupdateTable(String bm,String set,String where){
		return dataSourceMapper.plupdateTable(bm,set,where);
	}
	public int getPlDelTable(String bm,String where){
		return dataSourceMapper.pldelTable(bm,where);
	}
	public int getZdySql(String sql){
		return dataSourceMapper.zdysql(sql);
	}
	public List<String> getCxzd(String bm){
		return dataSourceMapper.selectColumn(bm);
	}
	public int deltable(String bm){
		CjSysNotesCriteria cc = new CjSysNotesCriteria();
		cc.createCriteria().andTableNameEqualTo(bm);
		return cjsysNotesMapper.deleteByExample(cc);
	}
	public int addSystable(String bm,String ruleid,String taskid){
		CjSysNotes csn = new CjSysNotes();
		csn.setNoteDate(new Date());
		csn.setTableName(bm);
		csn.setRuleId(ruleid);
		csn.setTaskId(taskid);
		return cjsysNotesMapper.insert(csn);
	}
	public List<CjSysNotes> getruleId(String bm){
		CjSysNotesCriteria cc = new CjSysNotesCriteria();
		cc.createCriteria().andTableNameEqualTo(bm);
		 List<CjSysNotes> list = cjsysNotesMapper.selectByExample(cc);
		 return list;
	}
	public List<TaskInstance> getInstace(String taskId)
	{
		TaskInstanceCriteria tic = new TaskInstanceCriteria();
		tic.createCriteria().andTaskIdEqualTo(Long.valueOf(taskId)).andStageEqualTo(0);
		
		tic.setOrderByClause(" publish_time desc");
		return iMapper.selectByExample(tic);
		
	}
}
