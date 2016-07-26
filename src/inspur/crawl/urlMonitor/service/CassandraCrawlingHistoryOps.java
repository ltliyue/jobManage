package inspur.crawl.urlMonitor.service;

import com.datastax.driver.core.*;
import com.inspur.avro.model.java.UrlFetchRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * CrawlingHistoryOps的Apache Cassandra实现
 */
public class CassandraCrawlingHistoryOps extends CassandraOpsBase implements CrawlingHistoryOps {
	
	private static PreparedStatement prepared_instance = null;
	private static PreparedStatement prepared_global = null;
	private static PreparedStatement preparedFetchInstance = null;
	private static PreparedStatement preparedFetchGlobal = null;
	
    public void post(CrawlingHistory history) {

        BatchStatement batchStatement = new BatchStatement();
        
        if(prepared_instance==null) {
        	prepared_instance = session.prepare(
                    "insert into url_instance_history(url_hash, \n" +
                            "    url, \n" +
                            "    fetch_complete_time,\n" +
                            "    fetcher_id,\n" +
                            "    instance_id,\n" +
                            "    status_code,\n" +
                            "    task_id,\n" +
                            "    failure_count\n" +
                            ")\n" +
                            "values(?, ?, ?, ?, ?, ?, ?, ?)");
        }
       

        BoundStatement bound_instance = prepared_instance.bind(history.url.hashCode(),
                history.url,
                history.fetch_complete_time,
                history.fetcher_id,
                history.instance_id,
                history.status_code,
                history.task_id,
                history.failureCount
        );
        
        if(prepared_global == null) {
        	 prepared_global = session.prepare(
                     "insert into url_global_history(url_hash, \n" +
                             "    url, \n" +
                             "    fetch_complete_time,\n" +
                             "    fetcher_id,\n" +
                             "    instance_id,\n" +
                             "    status_code,\n" +
                             "    task_id,\n" +
                             "    failure_count\n" +
                             ")\n" +
                             "values(?, ?, ?, ?, ?, ?, ?, ?)");
        }
       
        BoundStatement bound_global = prepared_global.bind(history.url.hashCode(),
                history.url,
                history.fetch_complete_time,
                history.fetcher_id,
                history.instance_id,
                history.status_code,
                history.task_id,
                history.failureCount
        );
        batchStatement.add(bound_instance);
        batchStatement.add(bound_global);
        this.session.execute(batchStatement);
    }

    public List<CrawlingHistory> fetchRecentGlobal(String url, int limit) {
        ArrayList<CrawlingHistory> list = new ArrayList<CrawlingHistory>();
        PreparedStatement prepareFetchGlobal = session.prepare("SELECT * FROM url_global_history WHERE url_hash=? and url=? LIMIT " + limit);
        BoundStatement boundStatement = prepareFetchGlobal.bind(url.hashCode(), url);
        ResultSet results = session.execute(boundStatement);
        for (Row row : results) {
            CrawlingHistory history = new CrawlingHistory();
            history.fetch_complete_time = row.getDate("fetch_complete_time");
            history.url = row.getString("url");
            history.fetcher_id = row.getString("fetcher_id");
            history.instance_id = row.getString("instance_id");
            history.status_code = row.getString("status_code");
            history.task_id = row.getString("task_id");
            history.failureCount = row.getInt("failure_count");
            list.add(history);
        }
        return list;
    }

    public List<CrawlingHistory> fetchRecentByInstance(String url, String instance_id, String task_id,int failureCount,int limit) {
        ArrayList<CrawlingHistory> list = new ArrayList<CrawlingHistory>();
        if(preparedFetchInstance == null) {
        	preparedFetchInstance = session.prepare("SELECT * FROM url_instance_history WHERE url_hash=? and url=? and instance_id=? and task_id=? and failure_count=? LIMIT ?");
        }
        BoundStatement boundStatement = preparedFetchInstance.bind(url.hashCode(), url, instance_id, task_id,failureCount, limit);
        ResultSet results = session.execute(boundStatement);
        for (Row row : results) {
            CrawlingHistory history = new CrawlingHistory();
            history.fetch_complete_time = row.getDate("fetch_complete_time");
            history.url = row.getString("url");
            history.fetcher_id = row.getString("fetcher_id");
            history.instance_id = row.getString("instance_id");
            history.status_code = row.getString("status_code");
            history.task_id = row.getString("task_id");
            history.failureCount = row.getInt("failure_count");
            list.add(history);
        }
        return list;
    }
    
    public List<CrawlingHistory> fetchRecentByInstance(String url, String instance_id, String task_id) {
        ArrayList<CrawlingHistory> list = new ArrayList<CrawlingHistory>();
        if(preparedFetchInstance == null) {
        	preparedFetchInstance = session.prepare("SELECT * FROM crawler_db.url_instance_history WHERE url_hash=? and url=? and instance_id=? and task_id=?");
        }
        BoundStatement boundStatement = preparedFetchInstance.bind(url.hashCode(), url, instance_id, task_id);
        ResultSet results = session.execute(boundStatement);
        for (Row row : results) {
            CrawlingHistory history = new CrawlingHistory();
            history.fetch_complete_time = row.getDate("fetch_complete_time");
            history.url = row.getString("url");
            history.fetcher_id = row.getString("fetcher_id");
            history.instance_id = row.getString("instance_id");
            history.status_code = row.getString("status_code");
            history.task_id = row.getString("task_id");
            history.failureCount = row.getInt("failure_count");
            list.add(history);
        }
        return list;
    }
    /**
	 *
	 * 是否在全局中存在
	 * @param limit 查询条数
	 */
	//TODO: 在url_global_history表中的partition中增加task_id，在该查询条件上增加task_id
	public boolean hasGlobalHistory(UrlFetchRequest urlFetchRequest) {
		if(preparedFetchGlobal == null) {
			preparedFetchGlobal = session.prepare("SELECT * FROM url_global_history WHERE url_hash=? and url=? and status_code=200 LIMIT 1");
       }
       BoundStatement boundStatement = preparedFetchGlobal.bind(urlFetchRequest.getUrlHash(),
       		urlFetchRequest.getUrl().toString());
       ResultSet results = session.execute(boundStatement);
       if(results!=null && !results.isExhausted()) {
   		return true;
       }
       return false;
	}
}
