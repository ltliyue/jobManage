package com.inspur.kafka.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.inspur.kafka.main.KafkaTopics;

import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.TopicAndPartition;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.consumer.SimpleConsumer;

public class CalLargestOffset {

	private Map<String, String> topicGroup = new HashMap<String, String>();
	/**
	 * kafka端口，从资源文件更新
	 */
	private int port = 9092;
	/**
	 * broker地址列表，从资源文件获得
	 */
	private List<String> seedBrokers = new ArrayList<String>();

	public CalLargestOffset(Map<String, String> topicGroup) {
		this.topicGroup.putAll(topicGroup);
		loadConfigs();
	}
	
	public CalLargestOffset() {
		loadConfigs();
	}
	
	public void loadConfigs() {
		Properties prop = new Properties();
		try {
			// load a properties file from class path
			InputStream s = getClass().getResourceAsStream("/kafka-client.properties");
			if (s == null) {
				throw new RuntimeException("Cannot find kafka-producer.properties");
			}
			prop.load(s);
			this.port = Integer.parseInt(prop.getProperty("port", "9092"));
			String[] hosts = prop.getProperty("brokers").split(",");
			this.seedBrokers.addAll(Arrays.asList(hosts));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public long findGroupTopicOffsetInfo(int partition) {
		for (String seed : seedBrokers) {
			SimpleConsumer consumer = null;
			try {
				// SimpleConsumer客户端
				consumer = new SimpleConsumer(seed, port, 100000, 64 * 1024, KafkaTopics.SIMPLE_COSUMER_CLINET);
				long lastOffset = getLastOffset(consumer, KafkaTopics.URL_TASK_REBALANCE, partition, kafka.api.OffsetRequest.LatestTime(), KafkaTopics.SIMPLE_COSUMER_CLINET);
				if (lastOffset!=-1) {
					return lastOffset;
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error communicating with Broker [" + seed + "] to find topicInfo Reason: " + e);
			} finally {
				if (consumer != null)
					consumer.close();
			}
		}
		return 0;
	}

	public static long getLastOffset(SimpleConsumer consumer, String topic, int partition,
            long whichTime, String clientName) {
		TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
		Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
		requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
		kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo, kafka.api.OffsetRequest.CurrentVersion(),clientName);
		OffsetResponse response = consumer.getOffsetsBefore(request);

		if (response.hasError()) {
			System.out.println("Error fetching data Offset Data the Broker. Reason: " + response.errorCode(topic, partition) );
			return -1;
		}
		long[] offsets = response.offsets(topic, partition);
		return offsets[0];
	}
	

}