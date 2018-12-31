
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
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaSend {
	public static void main(String[] args) {
		Properties pro = new Properties();
		pro.put("bootstrap.servers", "localhost:9092");
		pro.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		pro.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String, String> producer = new KafkaProducer<>(pro);
		ProducerRecord<String, String> record = 
				new ProducerRecord<String, String>("CustomerCountry", "Precision Products","France4");
		try {
//			producer.send(record);//最简单的发送,不关心返回
//			RecordMetadata data = producer.send(record).get();//同步
			Future<RecordMetadata>  f= producer.send(record, new DemoProducerCallback());
			System.out.println(f.get().offset());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class DemoProducerCallback implements Callback{

	@Override
	public void onCompletion(RecordMetadata paramRecordMetadata, Exception paramException) {
		System.out.println("call bask success....");
		if(paramException!=null){
			paramException.printStackTrace();
		}
	}
	
}



















