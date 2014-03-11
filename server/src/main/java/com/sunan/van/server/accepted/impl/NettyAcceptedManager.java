package com.sunan.van.server.accepted.impl;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunan.van.core.Message;
import com.sunan.van.core.VanFilterChain;
import com.sunan.van.server.accepted.AcceptedManager;

public class NettyAcceptedManager extends SimpleChannelInboundHandler<String> implements AcceptedManager{
	private static Logger log = LoggerFactory.getLogger(NettyAcceptedManager.class);
	
	private VanFilterChain filterChain;

	    @Override
	    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	        // Send greeting for a new connection.
	    	log.info("channelActive");
	        ctx.write(
	                "Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
	        ctx.write("It is " + new Date() + " now.\r\n");
	        ctx.flush();
	    }

	    @Override
	    public void channelRead0(ChannelHandlerContext ctx, String request) throws Exception {
	    	
	    	Message message = new Message();
	    	Message result;

	    	if(request != null && !request.equals("")){
	    		message.setId("");
	    		message.setMessage(request);
	    		result = filterChain.doFilter(message);
	    	} else{
	    		result = new Message();
	    		result.setMessage("null message error!");
	    	}
	        // Generate and write a response.
	        String response;
	        boolean close = false;
	        if (request.isEmpty()) {
	            response = "Please type something.\r\n";
	        } else if ("bye".equals(request.toLowerCase())) {
	            response = "Have a good day!\r\n";
	            close = true;
	        } else {
	            response = "ok";
	        }

	        // We do not need to write a ChannelBuffer here.
	        // We know the encoder inserted at TelnetPipelineFactory will do the conversion.
	        ChannelFuture future = ctx.write(response);

	        // Close the connection after sending 'Have a good day!'
	        // if the client has sent 'bye'.
	        if (close) {
	            future.addListener(ChannelFutureListener.CLOSE);
	        }
	    }

	    @Override
	    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	        ctx.flush();
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
	    	log.warn("Unexpected exception from downstream.", cause);
	        ctx.close();
	    }

		@Override
		public void setFilterChain(VanFilterChain filterChain) {
			this.filterChain = filterChain;
			
		}
}
