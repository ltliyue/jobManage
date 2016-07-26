package com.inspur.bigdata.partitionConfig;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * TaskPartitions缓存，根据taskId为主键，使用带有写入过期和最大存储上限的LRU Cache驱动。
 */
public class TaskConfigCache {

    private LoadingCache<String, TaskConfig> taskPartitionsLRU = null;
    private CassandraTaskConfigOps db_backend = CassandraTaskConfigOps.getDBops();

    //静态缓存
    private static TaskConfigCache cache = null;
    //缓存入口
    public static TaskConfigCache getCache() {
    	if(cache==null) {
    		cache = new TaskConfigCache();
    	}
    	return cache;
    	
    	
    }
    /**
     * 私有构造器
     */
    private TaskConfigCache() {
        Properties prop = new Properties();
        try {
            //load a properties file from class path
            InputStream s = getClass().getResourceAsStream("/cassandra.properties");
            if (s == null) {
                throw new RuntimeException("Cannot find cassandra.properties");
            }
            prop.load(s);
            long max = Long.parseLong(prop.getProperty("task-partitions.maximumSize", "1000"));
            long exp = Long.parseLong(prop.getProperty("task-partitions.expireAfterAccess.seconds", "30"));

            this.taskPartitionsLRU = CacheBuilder
                    .newBuilder()
                    .maximumSize(max)
                    .expireAfterWrite(exp, TimeUnit.SECONDS)
                    .build(new CacheLoader<String, TaskConfig>() {
                        @Override
                        public TaskConfig load(String s) throws Exception {
                        	if(db_backend == null) {
                        		db_backend = CassandraTaskConfigOps.getDBops();
                        	}
                        	db_backend.checkPrepare();
                            if (db_backend != null) {
                            	TaskConfig taskConfig = new TaskConfig();
                            	taskConfig.setTaskId(Long.parseLong(s));
                                if (taskConfig != null) {
                                    TaskConfig newConfig = db_backend.fetchLastByTaskId(Long.parseLong(s));
                                    if (newConfig != null ) {
                                        return newConfig;
                                    }else {
                                    	//当查询结果为空时，表示默认配置，使用全部分区，返回只有taskId的一个TaskPartitions
                                    	return taskConfig;
                                    }
                                }
                            }
                            throw new Exception(String.format("Unable to find history for %s", s));
                        }
                    });
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


//    public void setOnQueryingBackend(Action1 onQueryingBackend) {
//        this.onQueryingBackend = onQueryingBackend;
//    }
//
//    public void setOnUpdatingBackend(Action1 onUpdatingBackend) {
//        this.onUpdatingBackend = onUpdatingBackend;
//    }

    /**
     * 收到分区信息变更的消息后，查询数据库，并插入本地缓存
     * @param history
     */
    public void update(TaskConfig taskConfig) {
        if (this.db_backend != null) {
        	taskConfig = this.db_backend.fetchLastByTaskId(taskConfig.getTaskId());
        }
        this.taskPartitionsLRU.put(taskConfig.getTaskId().toString(), taskConfig);
    }

    /**
     * 查看本地缓存中是否具有历史数据，如有则返回该数据，无则查询后端数据库（如有）并返回，如仍未发现，返回null
     *
     * @param taskId
     * @return
     */
    public TaskConfig getIfPresent(String taskId) throws ExecutionException {
        try {
            return this.taskPartitionsLRU.get(taskId);
        } catch (ExecutionException ee) {
        	ee.printStackTrace();
            if (ee.getMessage().contains("Unable to find history for")) {
                return null;
            } else throw ee;
        }
    }
}
