/**
 * 
 */
package com.thinkgem.jeesite.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * <p>
 * 
 * </p>
 * 
 * @author liuyujian
 * @version 1.0
 * @date 2017年9月7日 
 * @since JDK 1.8
 * @copyright Copyright 2017 CLOUD SERVICES.
 */
public class KafkaProducerTest {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "10.1.2.182:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String,String> producer = new KafkaProducer<String,String>(props);

        for(int i=0;i<100;i++){
            ProducerRecord<String,String> r = new ProducerRecord<String,String>("message","key-"+i,"value-"+i);
            producer.send(r);
        }

        producer.close();
	}
}
