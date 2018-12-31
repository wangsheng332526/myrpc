
 /**************************************************************************
 * Copyright (c) 2016-2020 ZheJiang International E-Commerce Services Co.,Ltd. 
 * All rights reserved.
 * 
 * 名称：kafka
 * 版权说明：本软件属于浙江国贸云商企业服务有限公司所有，在未获得浙江国贸云商企业服务有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.data.fly.demo.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;

/**
  * TODO 请在此处添加注释
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年7月11日  下午5:57:48  
  * @since 2.0
  */
public class Provider {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(new String[] {"provider.xml"});
        context.start();
        try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 按任意键退出
	}
}
