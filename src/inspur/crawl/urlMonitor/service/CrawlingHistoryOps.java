package inspur.crawl.urlMonitor.service;

import java.util.Date;
import java.util.List;

/**
 * 爬行历史数据访问接口
 */
public interface CrawlingHistoryOps {
    /**
     * 存储一条新的历史记录
     * @param history
     */
    void post(CrawlingHistory history);

    /**
     * 返回指定URL最近的全局历史记录
     * @param url
     * @param limit
     * @return
     */
    List<CrawlingHistory> fetchRecentGlobal(String url, int limit);
    /**
     * 返回指定URL最近的任务实例历史记录
     * @param url
     * @param instance_id
     * @param task_id
     * @param limit
     * @return
     */
    List<CrawlingHistory> fetchRecentByInstance(String url, String instance_id, String task_id,int failureCount, int limit);
}
