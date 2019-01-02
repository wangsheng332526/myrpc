
package com.data.fly.demo;


 /**
  * TODO 请在此处添加注释
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年7月11日  下午5:27:39  
  * @since 2.0
  */
public interface DemoService {
	public String sayHello(String name);
	public String doSomething(Integer id,String addr);
	public Person findById(Integer id);
}
