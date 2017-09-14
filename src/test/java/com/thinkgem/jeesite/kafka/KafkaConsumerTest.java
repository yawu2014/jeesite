/**
 * 
 */
package com.thinkgem.jeesite.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

/**
 * <p>
 * 执行会比较慢,是可以成功的,需要先启动消费者再启动生产者
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月7日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class KafkaConsumerTest {
	public static void main(String[] args) {
		Properties props = new Properties();
//        props.put("bootstrap.servers", "10.1.2.182:9092");
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.1.2.182:9092");
//        props.put("group.id", "test");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_DOC, StringDeserializer.class.getName());
		
//        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("session.timeout.ms", "30000");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        Consumer<String,String> consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList("message"));
        while(true){
            ConsumerRecords<String,String> records = consumer.poll(10);
            for(ConsumerRecord<String,String> record : records){
                System.out.println("offset=" + record.offset() + ",--key=" + record.key() + ",--value=" + record.value());
            }
        }
	}
}
