package io.netty.handler.codec.msgpack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.msgpack.template.Templates;
import org.msgpack.type.ArrayValue;
import org.msgpack.type.FloatValue;
import org.msgpack.type.IntegerValue;
import org.msgpack.type.MapValue;
import org.msgpack.type.RawValue;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import test.RequestMessage;

public class TimeClientHandler extends ChannelHandlerAdapter {
//	private final int sendNumber;
	private int counter;
	private RequestMessage requestMessage;
	private Object data;

//	public TimeClientHandler(int sendNumber) {
//		this.sendNumber = sendNumber;
//	}
	
	public TimeClientHandler(RequestMessage requestMessage) {
		this.requestMessage = requestMessage;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
//		User[] users = getUserArray(1);
//        for (User user : users) {
//            ctx.writeAndFlush(user);
//        }
		ctx.writeAndFlush(requestMessage);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		Value val =(Value)msg;
		setData(toObject(val));
		System.out.println("This is " + ++counter + " times receive server : [" + msg + "]");
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	
	private Object toObject(Value value) throws IOException{
		Converter conv = new Converter(value);
	    if (value.isNilValue()) { // null
	        return null;
	    } else if (value.isRawValue()) { // byte[] or String or maybe Date?
	        // deserialize value to String object
	        RawValue v = value.asRawValue();
	        return conv.read(Templates.TString);
	    } else if (value.isBooleanValue()) { // boolean
	        return conv.read(Templates.TBoolean);
	    } else if (value.isIntegerValue()) { // int or long or BigInteger
	        // deserialize value to int
	        IntegerValue v = value.asIntegerValue();
	        return conv.read(Templates.TInteger);
	    } else if (value.isFloatValue()) { // float or double
	        // deserialize value to double
	        FloatValue v = value.asFloatValue();
	        return conv.read(Templates.TDouble);
	    } else if (value.isArrayValue()) { // List or Set
	        // deserialize value to List object
	        ArrayValue v = value.asArrayValue();
	        List<Object> ret = new ArrayList<Object>(v.size());
	        for (Value elementValue : v) {
	            ret.add(toObject(elementValue));
	        }
	        return ret;
	    } else if (value.isMapValue()) { // Map
	        MapValue v = value.asMapValue();


	        Map map = new HashMap<>(v.size());
	        for (Map.Entry<Value, Value> entry : v.entrySet()) {
	            Value key = entry.getKey();
	            Value val = entry.getValue();
	            map.put(toObject(key), toObject(val));
	        }

	        return map;
	    } else {
	        throw new RuntimeException("fatal error");
	    }
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
