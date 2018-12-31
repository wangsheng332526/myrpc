
 /**************************************************************************
 * Copyright (c) 2016-2020 ZheJiang International E-Commerce Services Co.,Ltd. 
 * All rights reserved.
 * 
 * 名称：kafka
 * 版权说明：本软件属于浙江国贸云商企业服务有限公司所有，在未获得浙江国贸云商企业服务有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.fly.test.kafka;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

/**
 * 	kafka 消费者
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年7月18日  下午5:08:19  
  * @since 2.0
  */
public class KafkaReceive {
	public static void main(String[] args) {
		Properties pro = new Properties();
		pro.put("bootstrap.servers", "localhost:9092");
		pro.put("group.id", "test");
		/* 关闭自动确认选项,默认是启动的*/
//		pro.put("enable.auto.commit", "false");
//		pro.put("auto.commit.interval.ms", "1000");
//		pro.put("session.timeout.ms", "30000");
		
		pro.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		pro.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(pro);
		consumer.subscribe(Arrays.asList("CustomerCountry"));
		
		//这里的代码从指定的偏移量开始读取
//		consumer.poll(0);
//		for(TopicPartition p : consumer.assignment()){
//			consumer.seek(p, 0);
//		}
		try {
			while(true){
				ConsumerRecords<String, String> records = consumer.poll(100);
				for(ConsumerRecord<String, String> record:records){
					System.out.println(record.topic()+","+record.partition()+","+record.offset()+","+
							record.key()+","+record.value());
					
					
				}
			}
		} catch (Exception e) {
			
		}finally{
			consumer.close();
		}
	}
}
