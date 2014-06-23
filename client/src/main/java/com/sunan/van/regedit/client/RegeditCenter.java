package com.sunan.van.regedit.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class RegeditCenter {
	
	private static final Logger log = Logger.getLogger(RegeditCenter.class.getName());
	
	private Channel channel = null;
	
	private ClientConfig config = null;
	
	private SubCallback callback = null;

	public RegeditCenter(ClientConfig config, SubCallback callback) throws InterruptedException{
		this.config = config;
		this.callback = callback;
		init();
	}
	
	public void init() throws InterruptedException{
		EventLoopGroup group = new NioEventLoopGroup();
		String host = config.getHost();
		int port = config.getPort();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					.handler(new RegeditCenterInitializer(callback));
			
			channel = b.connect(host, port).sync().channel();

//			ChannelFuture lastWriteFuture = null;
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					System.in));
//			for (;;) {
//				String line = in.readLine();
//				if (line == null) {
//					break;
//				}
//
//				// Sends the received line to the server.
//				lastWriteFuture = ch.writeAndFlush(line + "\r\n");
//				System.out.println("common: " + line);
//
//				// If user typed the 'bye' command, wait until the server closes
//				// the connection.
//				if ("bye".equals(line.toLowerCase())) {
//					ch.closeFuture().sync();
//					break;
//				}
//			}
//
//			// Wait until all messages are flushed before closing the channel.
//			if (lastWriteFuture != null) {
//				lastWriteFuture.sync();
//			}
		} finally {
//			group.shutdownGracefully();
		}
	}
	
	public void pub(String dataId){
		channel.writeAndFlush("pub," + dataId);
		log.info("pub dataId: " + dataId + " success!");
	}
	
	
	public void sub(String dataId){
		channel.writeAndFlush("sub," + dataId);
		log.info("sub dataId: " + dataId + " success!");
	}

}
