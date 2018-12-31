package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.netty.handler.codec.msgpack.TimeClient;
import io.netty.handler.codec.msgpack.TimeClientHandler;
import test.zk.ZkUtil;


/**
  * TODO 请在此处添加注释
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年11月29日  下午4:39:53  
  * @since 2.0
  */
public class InvokerInvocationHandler implements InvocationHandler {

    private final Object invoker;
    private String serviceName;
    
    public InvokerInvocationHandler(Object handler,String serviceName){
        this.invoker = handler;
        this.serviceName = serviceName;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	
        String methodName = method.getName();
        System.out.println(methodName);
        System.out.println(args[0]);
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(invoker, args);
        }
        if ("toString".equals(methodName) && parameterTypes.length == 0) {
            return invoker.toString();
        }
        if ("hashCode".equals(methodName) && parameterTypes.length == 0) {
            return invoker.hashCode();
        }
        if ("equals".equals(methodName) && parameterTypes.length == 1) {
            return invoker.equals(args[0]);
        }
        //TODO 发送至注册中心和调用 
        RequestMessage msg = new RequestMessage();
        msg.setServiceName(serviceName);
        msg.setMethodName(methodName);
        msg.setArgs(JSON.toJSONString(args));
        List<String> types = new ArrayList<>(); 
        for(Class c:parameterTypes){
        	types.add(c.getName());
        }
        msg.setTypes(types);
        String addr  = ZkUtil.readProviderAddr();
        if(StringUtils.isBlank(addr)){
        	return "zookeeper has no netty server info";
        }
        TimeClientHandler handler = new TimeClientHandler(msg);
        TimeClient t =  new TimeClient(addr.split(":")[0],Integer.valueOf(addr.split(":")[1]) , msg,handler);
        t.run();
        return handler.getData();
    }
    
    public static void main(String[] args) {
		Object[]  o = new Object[]{"你所说的是",1001,8.99};
		String s = JSON.toJSONString(o);
		System.out.println(s);
		JSONArray a = JSONObject.parseArray(s);
		int size = a.size();
		for(int i=0;i<size;i++){
			System.out.println(a.get(i));
		}
	}

}