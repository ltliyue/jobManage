package inspur.crawl.urlMonitor.service;

import java.util.Date;

/**
 * 爬行历史数据
 */
public class CrawlingHistory {
    /**
     * 被爬行的URL
     */
    public String url;
    /**
     * URL内容请求完成/失败时间
     */
    public Date fetch_complete_time;
    /**
     * HTTP状态码
     */
    public String status_code;
    /**
     * 抓取任务ID
     */
    public String task_id;
    /**
     * 抓取任务实例ID
     */
    public String instance_id;
    /**
     * 抓取进程标识
     */
    public String fetcher_id;
    /**
     * 失败次数
     */
    public int failureCount;
}
