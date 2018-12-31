package io.netty.handler.codec.msgpack;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
  * msgpack编码器
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年10月24日  下午2:55:17  
  * @since 2.0
  */
public class MsgpackEncoder extends MessageToByteEncoder<Object>{

	@Override
	protected void encode(ChannelHandlerContext arg0, Object arg1, ByteBuf arg2) {
		MessagePack msgpack = new MessagePack();
		byte[] raw = null;
		try {
			raw = msgpack.write(arg1);
//			raw = msgpack.write(arg1,ObjectTemplate.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		arg2.writeBytes(raw);
	}

}

