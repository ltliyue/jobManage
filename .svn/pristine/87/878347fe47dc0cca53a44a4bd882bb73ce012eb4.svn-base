package com.inspur.kafka.main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import com.inspur.bigdata.partitionConfig.TaskConfig;
import com.inspur.bigdata.partitionConfig.TaskConfigCache;
import com.inspur.kafka.helpers.KafkaConfigBuilder;

import inspur.crawl.taskManage.controller.TaskConstant;

/**
 * 生产者客户端
 * @author zhang_yuchao
 *
 */
public class KafkaProducerClient {

	//producer
	private static KafkaProducer producer = null;
	
	private static final Logger log = Logger.getLogger(KafkaProducerClient.class);
	
	public static KafkaProducer getKafkaProducer() {
		if(producer==null) {
			producer = new KafkaProducer(new KafkaConfigBuilder().getProducerConfiguration());
		}
		return producer;
	}
	
	/**
	 * 计算要发布的分区值
	 * @param topic 待发布主题
	 * @param hashCode url的hash值
	 * @param taskId 任务ID
	 * @return int partition 待发布 的分区值
	 */
	public static int caculatePartition(String topic, int hashCode, Long taskId) {
		//从缓存中查询任务分区信息
		TaskConfig taskConfig = null;
		if(topic.equals(TaskConstant.URL_TASK)) {
			try {
				taskConfig = TaskConfigCache.getCache()
						.getIfPresent(taskId.toString());
			} catch (ExecutionException e) {
				e.printStackTrace();
				log.error("taskId:"+taskId+"endMessage:"+e.getMessage());
			}
		}
		//如果查到分区配置信息，根据配置计算分区，否则使用所有分区
		int partition;
		if(taskConfig != null && taskConfig.getPartitions()!=null
				&& taskConfig.getPartitions().size()>0) {
			Integer[] partitions = taskConfig.getPartitions()
					.toArray(new Integer[taskConfig.getPartitions().size()]);
			partition = partitions[Math.abs(hashCode % partitions.length)];
		}else {
			int partitionSize = KafkaProducerClient.partitionSize(topic);
			partition = Math.abs(hashCode % partitionSize);
		}
		return partition;
	}
	
	/**
	 * 发送消息至主题
	 * @param topic 目标主题
	 * @param partition 目标分区
	 * @param message 消息ID
	 * @param payload 消息
	 */
	public static Object sendToKafka(String topic, int partition, String message, Object avroObj) {
		try {
			producer = getKafkaProducer();
			System.out.println("send message："+message+" to topic: "+topic+" partition:"+partition);
			return producer.send(new ProducerRecord(topic, partition, message, avroObj)).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取topic主题的分区数量
	 * @param topic
	 * @return
	 */
	public static int partitionSize(String topic) {
		if(producer==null) {
			producer = new KafkaProducer(new KafkaConfigBuilder().getProducerConfiguration());
		}
		int size = producer.partitionsFor(topic).size();
		return size;
	}
}
