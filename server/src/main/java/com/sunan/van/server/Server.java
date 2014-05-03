package com.sunan.van.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import com.sunan.van.common.codec.Codec;
import com.sunan.van.common.message.Message;
import com.sunan.van.core.VanFilterChain;
import com.sunan.van.server.config.VanServerConfig;
import com.sunan.van.server.register.ClientRegister;

public class Server {
//	private AcceptedManager acceptedManager;
	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	private static Server instance = new Server();
	@SuppressWarnings("unchecked")
	private VanFilterChain<Message> vanFilterChain = context.getBean(VanFilterChain.class);
	VanServerConfig vanServerconfig = context.getBean(VanServerConfig.class);
//	private ClientRegister register;
	private Codec codec = context.getBean(Codec.class);
//	private int port;
	 
//	    private Server(int port) {
//	        this.port = port;
//	    }
	    
	private Server(){
		
	}
	    public static Server getInstance(int port){
	    	return instance;
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
             .childHandler(new NettyServerInitializer(vanFilterChain, null, codec));

            b.bind(vanServerconfig.getPort()).sync().channel().closeFuture().sync();
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

	public VanFilterChain<Message> getVanFilterChain() {
		return vanFilterChain;
	}

	public void setVanFilterChain(VanFilterChain<Message> vanFilterChain) {
		this.vanFilterChain = vanFilterChain;
	}

//	public ClientRegister getRegister() {
//		return register;
//	}
//
//	public void setRegister(ClientRegister register) {
//		this.register = register;
//	}

	public Codec getCodec() {
		return codec;
	}

	public void setCodec(Codec codec) {
		this.codec = codec;
	}
	protected VanServerConfig getVanServerconfig() {
		return vanServerconfig;
	}
	protected void setVanServerconfig(VanServerConfig vanServerconfig) {
		this.vanServerconfig = vanServerconfig;
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
