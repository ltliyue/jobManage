package com.inspur.bigdata.partitionConfig;

import java.util.List;

/**
 * 任务分区配置信息访问接口
 */
public interface TaskConfigOps {

    /**
     * 返回指定taskId的最新配置信息
     * @param taskId
     */
    TaskConfig fetchLastByTaskId(Long taskId);
    
    /**
     * 返回指定taskId的最近{limit}条信息
     * @param taskId
     * @param limit 查询条数
     */
    List<TaskConfig> fetchByTaskId(Long taskId, int limit);
    
    void postConfig(TaskConfig taskConfig);
}
