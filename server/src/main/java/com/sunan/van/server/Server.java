package com.sunan.van.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.sunan.van.core.VanFilterChain;
import com.sunan.van.server.accepted.AcceptedManager;

public class Server {
//	private AcceptedManager acceptedManager;	
	private VanFilterChain vanFilterChain;
	 private final int port;
	 
	    public Server(int port) {
	        this.port = port;
	    }
	
	private void init() throws InterruptedException{
		
//		List<VanFilter> vanFilterList = getVanFilterList();
//		for(VanFilter vanFilter : vanFilterList){
//			vanFilterChain.add(vanFilter);
//		}
		
//		acceptedManager.setFilterChain(vanFilterChain);
		
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new NettyServerInitializer(vanFilterChain));

            b.bind(port).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
		
	}
	
	public void startup() throws InterruptedException{
		init();
	}
	
//	private List<VanFilter> getVanFilterList(){
//		List<VanFilter> result = new ArrayList<VanFilter>();
//		result.add(new FileStorageFilter());
////		result.add(e);
//		
//		return result;
//	}

//	public AcceptedManager getAcceptedManager() {
//		return acceptedManager;
//	}
//
//	public void setAcceptedManager(AcceptedManager acceptedManager) {
//		this.acceptedManager = acceptedManager;
//	}

	public VanFilterChain getVanFilterChain() {
		return vanFilterChain;
	}

	public void setVanFilterChain(VanFilterChain vanFilterChain) {
		this.vanFilterChain = vanFilterChain;
	}

//	    public static void main(String[] args) throws Exception {
//	        int port;
//	        if (args.length > 0) {
//	            port = Integer.parseInt(args[0]);
//	        } else {
//	            port = 8080;
//	        }
//	        new TelnetServer(port).run();
//	    }
	

}
