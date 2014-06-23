package com.sunan.van.server.accepted.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunan.van.common.codec.Codec;
import com.sunan.van.common.message.Message;
import com.sunan.van.core.VanFilterChain;
import com.sunan.van.core.cache.ClientCache;
import com.sunan.van.server.accepted.AcceptedManager;
import com.sunan.van.server.register.RegisterClient;
import com.sunan.van.server.session.SessionManager;

@Sharable
public class NettyAcceptedManager extends SimpleChannelInboundHandler<String>
		implements AcceptedManager {
	private static Logger log = LoggerFactory
			.getLogger(NettyAcceptedManager.class);

	private VanFilterChain<Message> filterChain;
//	private ClientRegister register;
	private Codec<String, Message> codec;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// Send greeting for a new connection.
		log.info("channelActive");
		ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName()
				+ "!\r\n");
		ctx.write("It is " + new Date() + " now.\r\n");
		// add client cache
		SessionManager.addSession(ctx.channel().remoteAddress().toString(), ctx.channel());
		System.out.println(ctx.channel().remoteAddress());
		
		ctx.flush();
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, String request)
			throws Exception {
		
		System.out.println("remoteIP: " + ctx.channel().remoteAddress());
		System.out.println("ctx name: " + ctx.name());

		Message message = null;
		Message result = new Message();
		String client = ctx.channel().remoteAddress().toString();

//		boolean close = false;

		// 判断client是否注册过
		message = codec.decoder(request);
		message.setClient(client);
		
		if (!RegisterClient.isRegister(ctx.channel())) {
			RegisterClient.put(ctx.channel());

		} 

		result = filterChain.doFilter(message);

		ctx.writeAndFlush(new String(result.getBody()) + "\r\n");
		
		
		// We do not need to write a ChannelBuffer here.
		// We know the encoder inserted at TelnetPipelineFactory will do the
		// conversion.
//		ChannelFuture future = null;
//		if(response != null){
		
//		}

		// Close the connection after sending 'Have a good day!'
		// if the client has sent 'bye'.
//		if (close) {
//			future.addListener(ChannelFutureListener.CLOSE);
//		}
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state().equals(IdleState.READER_IDLE)) {
				System.out.println("READER_IDLE");
				// 超时关闭channel
				ctx.close();
			} else if (event.state().equals(IdleState.WRITER_IDLE)) {
				System.out.println("WRITER_IDLE");
			} else if (event.state().equals(IdleState.ALL_IDLE)) {
				System.out.println("ALL_IDLE");
				// 发送心跳
				ctx.channel().writeAndFlush("ping;");
			}
		}
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		log.warn("Unexpected exception from downstream.", cause);
		String client = ctx.channel().remoteAddress().toString();
		ClientCache.removeClient(client);
		ctx.close();
		for(String dataId:ClientCache.getSubclientcache().get(client)){
			dataIdChange(dataId);
		}
	}
	
	private void dataIdChange(String dataId){
		List<String> subList = ClientCache.getDataidsubcache().get(dataId);
		List<String> pubList = ClientCache.getDataidpubcache().get(dataId);
		StringBuffer sb = new StringBuffer();
		for(String pub : pubList){
			sb.append(pub + ",");
		}
		String pubs = sb.toString();
		System.out.println("dataId " + dataId + " change");
		System.out.println(" dataId List " + pubs);
		if(subList == null){
			return;
		}
		for(String sub : subList){
			Channel channel = SessionManager.getSession(sub);
			channel.flush();
			channel.writeAndFlush("updata: " + dataId + ":" + pubs + ";\r\n");
			System.out.println("sub " + sub + " flush!");
		}
	}


	public void setFilterChain(VanFilterChain<Message> filterChain) {
		this.filterChain = filterChain;
	}

	@Override
	public void setCodec(Codec codec) {
		this.codec = codec;
		
	}

//	public void setRegister(ClientRegister register) {
//		this.register = register;
//	}
	
	
}
