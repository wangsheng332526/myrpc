package io.netty.handler.codec.msgpack;

import java.net.InetAddress;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import test.zk.ZkUtil;
/**
 *  解决了半包粘包问题 
  * @author <a href="mailto:"wangsheng"@zjiec.com”>"wangsheng"</a>
  * @version 2018年10月24日  下午4:56:04  
  * @since 2.0
 */
public class TimeServer {
	public void bind(int port) throws Exception{
		EventLoopGroup bossGroup=new NioEventLoopGroup();
		EventLoopGroup workerGroup=new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 1024).
				childHandler(new ChildChannelHandler());
			//绑定端口,同步等待成功
			ChannelFuture f = b.bind(port).sync();
			if(f.isSuccess()){
				System.out.println("netty服务启动成功");
				//注册到zookeeper
				ZkUtil.registerProvider(InetAddress.getLocalHost().getHostAddress(), port);
				System.out.println("注册到zookeeper");
			}
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			System.out.println("netty服务启动异常");
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel ch) throws Exception {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_provider.xml");
			ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
			ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
			// 在ByteBuf之前增加2个字节的消息长度字段
			ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
			ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
			
			ch.pipeline().addLast(new TimeServerHandler(applicationContext));
			
//			// 添加MesspagePack解码器
//            ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
//            // 添加MessagePack编码器
//            ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
//			ch.pipeline().addLast(new TimeServerHandler());
		}
		
	}
	public static void main(String[] args) throws Exception {
		
		int port=8080;
		TimeServer t = new TimeServer();
		//启动服务
		t.bind(port);
		
		
	}
}
