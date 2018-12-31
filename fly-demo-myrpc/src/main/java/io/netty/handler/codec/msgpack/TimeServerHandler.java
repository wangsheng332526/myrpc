package io.netty.handler.codec.msgpack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.msgpack.type.ArrayValue;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import test.User;
import test.UserService;
import test.UserServiceImpl;

public class TimeServerHandler extends ChannelHandlerAdapter {
	private ApplicationContext applicationContext;
	public TimeServerHandler(ApplicationContext applicationContext){
		this.applicationContext = applicationContext;
	}
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		System.out.println("接受到的body:"+msg);
		Value v = (Value)msg;
		Converter conv = new Converter( v.asArrayValue().get(0));
		String serviceName = conv.readString();
		conv = new Converter( v.asArrayValue().get(1));
		String method = conv.readString();
		conv = new Converter( v.asArrayValue().get(2));
		String args = conv.readString();
		conv = new Converter( v.asArrayValue().get(3));
		ArrayValue arrays = conv.readValue().asArrayValue();
		List<String> types = new ArrayList<>();
		for(int i =0;i<arrays.size();i++){
			Value val = arrays.get(i);
			conv = new Converter(val);
			types.add(conv.readString());
		}
		Object result = null;
		try {
			Object o =  applicationContext.getBean(serviceName);
			
			for(Method m : o.getClass().getMethods()){
				if(m.getName().equals(method)  
						&& m.getParameterTypes().length ==types.size() && checkMethodParamType(m, types)){
					
					JSONArray jr = JSONObject.parseArray(args);
					int size = jr.size();
					Object[] obj = new Object[size];
					for(int i=0;i<size;i++){
						obj[i] = jr.get(i);
					}
					result = m.invoke(o, obj);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		ctx.write(result);
		ctx.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE);
//		ByteBuf resp=Unpooled.copiedBuffer(str.getBytes());
//		ctx.write(resp);
//		ctx.writeAndFlush(resp);
	}
	
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
		ctx.flush();
	}
	
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception{
		ctx.close();
	}
	
	private boolean checkMethodParamType(Method method,List<String> types){
		int i =0;
		for(Class<?> c:method.getParameterTypes()){
			if(!c.getName().equals(types.get(i))){
				return false;
			}
			i++;
		}
		return true;
	}
	
	public static void main(String[] args) {
		UserService u = new UserServiceImpl();
		for(Method m:u.getClass().getMethods()){
			if("sayHi".equals(m.getName())){
				try {
					Object o = m.invoke(u,"1",1 );
					System.out.println(o);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
}