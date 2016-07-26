package com.inspur.kafka.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

/**
 * Kafka配置生成器。从Properties资源中读取关键配置信息。
 */
public class KafkaConfigBuilder {
    /**
     * 获取生产者相关的配置信息
     *
     * @return 生产者配置
     */
    public Properties getProducerConfiguration(){
        Properties prop = new Properties();
        try {
            //load a properties file from class path
            InputStream s = getClass().getResourceAsStream("/kafka-producer.properties");
           if(s == null){
               throw new RuntimeException("Cannot find kafka-producer.properties");
           }
            prop.load(s);
            return prop;
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 为指定的消费组名称（group.id）获取消费者相关的配置信息
     * @param a_groupId 消费组名称(group.id）
     * @return 消费者配置
     */
    public Properties getConsumerConfig(String a_groupId) {
        Properties prop = new Properties();
        try {
            //load a properties file from class path
            InputStream s = getClass().getResourceAsStream("/kakfa-consumer.properties");
            if(s == null){
                throw new RuntimeException("Cannot find kakfa-consumer.properties");
            }
            prop.load(s);
            prop.put("group.id", a_groupId);
            return prop;
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * 为分区信息监配置消费者相关的信息
     * groupId随机生成
     * auto.offset.reset=largest
     * @return 消费者配置
     */
    public Properties getConsumerConfigForPartition() {
        Properties prop = new Properties();
        try {
            //load a properties file from class path
            InputStream s = getClass().getResourceAsStream("/kakfa-consumer.properties");
            if(s == null){
                throw new RuntimeException("Cannot find kakfa-consumer.properties");
            }
            prop.load(s);
            String groupId = UUID.randomUUID().toString();
            prop.put("group.id", groupId);
            prop.put("auto.offset.reset", "largest");
            return prop;
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
