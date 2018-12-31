
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

import com.alibaba.dubbo.rpc.RpcContext;
import com.data.fly.demo.DemoService;
import com.data.fly.demo.Person;

/**
 * TODO 请在此处添加注释
 * 
 * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
 * @version 2018年7月11日 下午5:30:45
 * @since 2.0
 */
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
//        // 本端是否为提供端，这里会返回true
//        boolean isProviderSide = RpcContext.getContext().isProviderSide();
//        // 获取调用方IP地址
//        String clientIP = RpcContext.getContext().getRemoteHost();
//        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
//        String application = RpcContext.getContext().getUrl().getParameter("application");
//        System.out.println(application);
		System.out.println("被调用");
		return "Hello123:" + name;
	}

	@Override
	public String doSomething(Integer id, String addr) {
		return id+":"+addr;
	}

	
	/**
	 * @param id
	 * @return
	 * @see com.data.fly.demo.DemoService#findById(java.lang.Integer)
	 */
	@Override
	public Person findById(Integer id) {
		Person p = new Person();
		p.setName("康熙");
		p.setAge(25);
		p.setSex(1);
		return p;
	}

}
