package inspur.crawl.monitor.mapper;

import java.util.List;
import java.util.Map;

import inspur.crawl.monitor.pojo.MonitorSourceData;

public interface MonitorDataMapper {
	
	/**
	 * 查询存储和数据量
	 * @return
	 */
	public List<MonitorSourceData> selectLatestData();
	
	public List<Map> selectDemandData();
	
	public List<Map> selectSiteData();
}