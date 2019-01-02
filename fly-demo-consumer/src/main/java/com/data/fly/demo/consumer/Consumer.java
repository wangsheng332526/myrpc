
package com.data.fly.demo.consumer;

import java.lang.reflect.Method;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.data.fly.demo.DemoService;
import com.data.fly.demo.NameService;
import com.data.fly.demo.Person;

/**
  * TODO 请在此处添加注释
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年7月11日  下午6:24:21  
  * @since 2.0
  */
public class Consumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
        
//        Object o = context.getBean("demoService");
//        for(Method m:o.getClass().getMethods()){
//        	System.out.println(m.getName());
//        }
//        DemoService d = (DemoService)o;
//        System.out.println(o);
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
//        String hello = demoService.sayHello("world"); // 执行远程方法
        Person p  = demoService.findById(1005); // 执行远程方法
        System.out.println(p.getName());
        
     // 本端是否为消费端，这里会返回true
//        boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
//        // 获取最后一次调用的提供方IP地址
//        String serverIP = RpcContext.getContext().getRemoteHost();
//        // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
//        String application = RpcContext.getContext().getUrl().getParameter("application");
        
//        System.out.println( hello ); // 显示调用结果
	}
}
