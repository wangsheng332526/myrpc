package test;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.msgpack.annotation.Message;

/**
  * TODO 请在此处添加注释
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年12月28日  上午11:18:47  
  * @since 2.0
  */
@Message
public class RequestMessage implements Serializable{
	
	 /**    */
	private static final long serialVersionUID = -4868930635840083598L;
	private String serviceName;
	private String methodName;
	private String args;
	private List<String> types;
	
	
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	
	
}
