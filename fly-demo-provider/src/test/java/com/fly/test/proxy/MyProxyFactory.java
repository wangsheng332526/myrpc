
 /**************************************************************************
 * Copyright (c) 2016-2020 ZheJiang International E-Commerce Services Co.,Ltd. 
 * All rights reserved.
 * 
 * 名称：kafka
 * 版权说明：本软件属于浙江国贸云商企业服务有限公司所有，在未获得浙江国贸云商企业服务有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.fly.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
  * TODO 请在此处添加注释
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年7月16日  下午1:52:05  
  * @since 2.0
  */
public class MyProxyFactory {
	private Object obj;
	public MyProxyFactory(Object obj){
		this.obj = obj;
	}
	
	public Object getProxy(){
		
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("处理前执行.....");
				Object r = method.invoke(obj, args);
				System.out.println("处理后执行.....");
				return r;
			}
		});
		
		
		
	}
}
