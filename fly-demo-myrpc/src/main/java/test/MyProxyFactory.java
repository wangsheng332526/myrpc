//package test;
//
//import java.lang.reflect.Proxy;
//
///**
//  * TODO 请在此处添加注释
//  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
//  * @version 2018年11月29日  下午1:59:36  
//  * @since 2.0
//  */
//public class MyProxyFactory {
//	private Class c;
//	public MyProxyFactory(Class c){
//		this.c = c;
//	}
//	
//	public <T> T getProxy(Class[] interfaces){
//		
//		return (T)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces,new InvokerInvocationHandler(c));
//	}
//	
//	
//	
//}	
