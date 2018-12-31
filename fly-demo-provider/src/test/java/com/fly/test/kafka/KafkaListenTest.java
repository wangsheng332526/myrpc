
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

/**
  * 再均衡器监听
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年7月19日  上午9:53:19  
  * @since 2.0
  */
public class KafkaListenTest {
	//TODO
	private static KafkaConsumer<String, String> consumer;
	private static Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
	public KafkaListenTest(KafkaConsumer<String, String> consumer){
		this.consumer = consumer;
	}
	
	public static void main(String[] args) {
		class HandleRebalance implements ConsumerRebalanceListener{

			public void onPartitionsAssigned(Collection<TopicPartition> arg0) {
				
			}
			public void onPartitionsRevoked(Collection<TopicPartition> arg0) {
				consumer.commitSync(currentOffsets);
			}
			
		}
		consumer.subscribe(Arrays.asList(""), new HandleRebalance());
		try{
			while(true){
				ConsumerRecords<String, String> records = consumer.poll(100);
				for(ConsumerRecord<String,String> record:records){
					currentOffsets.put(new TopicPartition(record.topic(), record.partition()), 
							new OffsetAndMetadata(record.offset()+1,"no metadata"));
				}
				consumer.commitAsync(currentOffsets, null);
			}
		}catch(Exception e){
			
		}finally{
			try {
				consumer.commitSync(currentOffsets);
			} finally {
				consumer.close();
			}
		}
	}
	
	
	
	

	
	
}


































