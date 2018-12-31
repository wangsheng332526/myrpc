package io.netty.handler.codec.msgpack;

import java.util.List;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

/**
  * messagepack解码
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年10月24日  下午2:57:37  
  * @since 2.0
  */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf>{
	@Override
	protected void decode(ChannelHandlerContext arg0, ByteBuf arg1, List<Object> arg2) throws Exception {
		final byte[] array;
		final int length=arg1.readableBytes();
		array=new byte[length];
		arg1.getBytes(arg1.readerIndex(), array,0,length);
		MessagePack msgpack = new MessagePack();
		arg2.add(msgpack.read(array));
	}
	

}
