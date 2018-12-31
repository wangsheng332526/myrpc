
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

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

/**
  * 单独的消费者,但是新增分区时不会自动感应,需要重启或者 加个遍历
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年7月19日  上午11:19:49  
  * @since 2.0
  */
public class KafkaReceiveAlong {
	
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
		List<TopicPartition> partitions = null;
		List<PartitionInfo> partitionInfos = null;
		//不会再均衡,不用手动查找分区,所有要consumer.partitionsFor()方法不断检查是否有新的分区
		partitionInfos = consumer.partitionsFor("CustomerCountry");
		if(partitionInfos!=null){
			for(PartitionInfo partition:partitionInfos){
				partitions.add(new TopicPartition(partition.topic(), partition.partition()));
			}
			consumer.assign(partitions);
			
			//.....
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
