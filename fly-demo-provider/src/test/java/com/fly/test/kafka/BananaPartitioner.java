
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
import java.util.Map;
import java.util.Random;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

/**
  * 自定义分区
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年7月18日  下午4:43:18  
  * @since 2.0
  */
public class BananaPartitioner implements Partitioner{

	@Override
	public void configure(Map<String, ?> paramMap) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value,
			byte[] valueBytes, Cluster cluster) {
		List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
		int numPartitions = partitions.size();
		if(keyBytes ==null || !(key instanceof String)){
			//抛异常
		}
		if(key.toString().equals("Banana") ){
			//如果是banana,分配到同一个
			return numPartitions;
		}else{
			//其他记录分散
			return new Random(numPartitions-1).nextInt();
		}
		
	}

	
	
	
	
	
	
	
	
	
}
