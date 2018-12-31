package io.netty.handler.codec.msgpack;

import org.msgpack.MessagePack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import test.RequestMessage;
/**
 *  解决了半包粘包问题 
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年10月24日  下午4:56:28  
  * @since 2.0
 */
public class TimeClient {
	private final String host;
	private final int port;
	private final RequestMessage requestMessage;
	private TimeClientHandler handler;
	public TimeClient(String host, int port, RequestMessage requestMessage,TimeClientHandler handler) {
		this.host = host;
		this.port = port;
		this.requestMessage = requestMessage;
		this.handler = handler;
	}

	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							// LengthFieldBasedFrameDecoder用于处理半包消息
							// 这样后面的MsgpackDecoder接收的永远是整包消息
							ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
							ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
							// 在ByteBuf之前增加2个字节的消息长度字段
							ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
							ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
//							TimeClientHandler t = new TimeClientHandler(requestMessage);
							ch.pipeline().addLast(handler);
						}
					});
			// 发起异步连接操作
			ChannelFuture f = b.connect(host, port).sync();
			
			if (f.isSuccess()) {
				System.out.println("客户端开始连接");
			}
			// 等待客户端链路关闭
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		/*RequestMessage msg = new RequestMessage();
		msg.setMethodName("process");
//		msg.setArgs(new Object[]{"tom"});
		new TimeClient("127.0.0.1", 8080, msg).run();*/
		MessagePack msgpack = new MessagePack();
		byte[] raw = null;
		RequestMessage msg = new RequestMessage();
		msg.setMethodName("sayHi");
		try {
			raw = msgpack.write(msg);
//			raw = msgpack.write(arg1,ObjectTemplate.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
