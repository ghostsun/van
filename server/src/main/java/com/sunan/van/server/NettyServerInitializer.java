package com.sunan.van.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import com.sunan.van.core.VanFilterChain;
import com.sunan.van.server.accepted.impl.NettyAcceptedManager;
import com.sunan.van.server.register.ClientRegister;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel>{

	 private static final StringDecoder DECODER = new StringDecoder();
	    private static final StringEncoder ENCODER = new StringEncoder();
	    private static final NettyAcceptedManager SERVERHANDLER = new NettyAcceptedManager();
	    
	    public NettyServerInitializer(VanFilterChain filterChain, ClientRegister register){
	    	SERVERHANDLER.setFilterChain(filterChain);
	    	SERVERHANDLER.setRegister(register);
	    }

	    @Override
	    public void initChannel(SocketChannel ch) throws Exception {
	        ChannelPipeline pipeline = ch.pipeline();

	        // Add the text line codec combination first,
	        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
	                8192, Delimiters.lineDelimiter()));
	        // the encoder and decoder are static as these are sharable
	        pipeline.addLast("decoder", DECODER);
	        pipeline.addLast("encoder", ENCODER);

	        // and then business logic.
	        pipeline.addLast("handler", SERVERHANDLER);
	    }
}
