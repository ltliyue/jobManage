package com.inspur.kafka.main;

public class KafkaTopics {

	/**
	 * 增强处理过的urlTask
	 */
	public static final String URL_TASK = "urlFetchRequest";
	/**
	 * 待增强处理的urlTask
	 */
	public static final String URL_TASK_ORIGIN = "urlFetchRequestOrigin";
	/**
	 * 进行消息均衡处理的主题
	 */
	public static final String URL_TASK_REBALANCE = "taskRebalance";
	/**
	 * SIMPLE_COMSUMER客户端
	 */
	public static final String SIMPLE_COSUMER_CLINET = "lookUpMonitor";
	
}
