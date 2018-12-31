package test;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	static Object o = new Object();
	public static void main(String[] args) {
		
		  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_consumer.xml");
		  
		final UserService u = (UserService)applicationContext.getBean("userService");
		
		System.out.println("调用返回"+u.sayHi("JESSION。。。",2001));	

		
//		ExecutorService e = Executors.newFixedThreadPool(10);
//		for(int i=0;i<100;i++){
//			final int index = i;
//			
//			e.submit(new Runnable() {
//				
//				@Override
//				public void run() {
//					System.out.println(index+"调用返回"+u.sayHi("JESSION。。。",index));		
//				}
//			});
//			
//		}
		
	}
}
