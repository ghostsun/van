package com.sunan.van.regedit.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestSubClient {
	private final String host;
	private final int port;

	public static final String DEFAULT_HOST = "127.0.0.1";
	public static final int DEFAULT_PORT = 8080;
	
	public static final String DEFAULT_TOPIC = "DEFAULT_TOPIC";

	public TestSubClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	

	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
					.handler(new RegeditCenterInitializer(new TestCallback()));

			// Start the connection attempt.
//			b.connect(host, port);
//			b.option("tcpNoDelay" , true);  
//	        b.option("keepAlive", true);  
	 
//	        bootstrap.connect (new InetSocketAddress(host, port)); 
			
			Channel ch = b.connect(host, port).sync().channel();
//			RegeditCenter.channel = ch;
//			
//			Thread.sleep(1000 * 1000);

			// Read commands from the stdin.
			ChannelFuture lastWriteFuture = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			for (;;) {
				String line = in.readLine();
				if (line == null) {
					break;
				}

				// Sends the received line to the server.
				lastWriteFuture = ch.writeAndFlush(line + "\r\n");
				System.out.println("common: " + line);

				// If user typed the 'bye' command, wait until the server closes
				// the connection.
				if ("bye".equals(line.toLowerCase())) {
					ch.closeFuture().sync();
					break;
				}
			}

			// Wait until all messages are flushed before closing the channel.
			if (lastWriteFuture != null) {
				lastWriteFuture.sync();
			}
		} finally {
			group.shutdownGracefully();
		}
	}
	

	public static void main(String[] args) throws Exception {
		// Print usage if no argument is specified.
		String host = DEFAULT_HOST;
		int port = DEFAULT_PORT;
		if (args.length != 2) {
			System.err.println("Usage: " + TestSubClient.class.getSimpleName()
					+ " <host> <port>");
			// return;
		} else {

			// Parse options.
			host = args[0];
			port = Integer.parseInt(args[1]);
		}
		
//		Runnable sub = new Runnable(){
//
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				RegeditCenter.sub("123");
//				RegeditCenter.sub("456");
//				RegeditCenter.sub("789");
//				
//			}
//			
//		};
//		
//		Thread t = new Thread(sub);
//		t.start();

		new TestSubClient(host, port).run();
		
		
	}
	
}
