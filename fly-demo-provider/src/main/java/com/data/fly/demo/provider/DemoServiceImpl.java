
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
