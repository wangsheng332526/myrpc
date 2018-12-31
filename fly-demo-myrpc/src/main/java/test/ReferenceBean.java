package test;

import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

/**
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年11月30日  下午2:13:38  
  * @since 2.0
  */
public class ReferenceBean implements FactoryBean{
	private String id;
	private String sid;
	private String interfaceName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	
	@Override
	public Object getObject() throws Exception {
		Class<?> interfaceClass = Class.forName(interfaceName, true, Thread.currentThread()
			        .getContextClassLoader());
		Class<?>[] interfaces = new Class<?>[] {interfaceClass};
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
				interfaces,new InvokerInvocationHandler(interfaceClass,id));
	}
	
	@Override
	public Class getObjectType() {
		Class<?> interfaceClass = null;
		try {
			interfaceClass = Class.forName(interfaceName, true, Thread.currentThread()
			        .getContextClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return interfaceClass;
	}
	
	@Override
	public boolean isSingleton() {
		return true;
	}
	
}
