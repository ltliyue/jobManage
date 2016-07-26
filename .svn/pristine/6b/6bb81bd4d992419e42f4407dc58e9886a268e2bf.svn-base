package inspur.crawl.monitor.util;

import java.util.Date;

import com.inspur.bigdata.hbaseio.dao.TimeSeriesDao;
import com.inspur.bigdata.hbaseio.pojo.DataPoint;
import com.inspur.bigdata.hbaseio.pojo.Metric;
import com.inspur.bigdata.hbaseio.pojo.MetricTarget;
import com.inspur.bigdata.hbaseio.pojo.Stage;
import com.inspur.bigdata.hbaseio.pojo.TimeSeriesData;

public class MonitorMathUtil {

	public static Integer incrPercentByIncr(Integer incr, Integer base) {
		return (incr*100)/base;
	}
	
	public static Integer incrPercentByMonthVal(Integer curMonth, Integer lastMonth) {
		if(lastMonth!=null && curMonth!=null) {
			if(curMonth == 0) {
				return 0;
			}
			if(lastMonth == 0) {
				return curMonth*100;
			}
			return (curMonth-lastMonth)*100/lastMonth;
		}
		return 0;
	}
	

	public static Long incrPercentByMonthVal(Long curMonth, Long lastMonth) {
		if(lastMonth!=null && curMonth!=null) {
			if(curMonth == 0) {
				return 0L;
			}
			if(lastMonth == 0) {
				return curMonth*100;
			}
			return (curMonth-lastMonth)*100/lastMonth;
		}
		return 0L;
	}
	public static void main(String[] args) {
		DataPoint dpall = new DataPoint();
		dpall.setMetric(Metric.TASK_UNIT_DOWNLOAD_INCRSUM);
		dpall.setMetricTarget(MetricTarget.TASK);
		dpall.setStage(Stage.FETCH);
		dpall.setTime(new Date());
		dpall.setTargetId("608a5c9d-0bc7-4522-adf2-06172d250b1e");
		//System.out.println(new Date(1460394122687L));
//		TimeSeriesData tsdNew = TimeSeriesDao.getLastestTimeSeries(dpall.getMetric(), dpall.getMetricTarget(), "608a5c9d-0bc7-4522-adf2-06172d250b1e", dpall.getStage());
		TimeSeriesData tsdNew = TimeSeriesDao.getNearestTimeSeries(dpall, true);
		Long curNum = 0L;
		// 全部数据项
		if (tsdNew != null) {
			System.out.println(tsdNew.getData().longValue());
			System.out.println(tsdNew.getTime());
			curNum = tsdNew.getData().longValue();
		}
		
		//当前9316698  昨日92875099316698-9287509=29189
		System.out.println("当前下载总数:"+curNum);
	}
}
