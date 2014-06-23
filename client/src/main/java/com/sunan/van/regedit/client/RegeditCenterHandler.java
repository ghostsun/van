package com.sunan.van.regedit.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.sunan.van.regedit.client.cache.RegeditCache;

public class RegeditCenterHandler extends SimpleChannelInboundHandler<String> {

	private static final Logger log = Logger
			.getLogger(RegeditCenterHandler.class.getName());
	
	private SubCallback callback;
	
	public RegeditCenterHandler(SubCallback callback){
		this.callback = callback;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// Send greeting for a new connection.
		// RegeditCenter.setCtx(ctx);

		log.info("client channelActive");
		super.channelActive(ctx);
		// ctx.write("I am a client for pub message!");
		// ctx.flush();
		// ctx.write(
		// "Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
		// ctx.write("It is " + new Date() + " now.\r\n");
		// ctx.flush();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
//		System.err.println(msg);

		if (!StringUtils.isBlank(msg)) {
			String[] commands = msg.split(";");
			
			for (String command : commands) {
				
				if (!StringUtils.isBlank(command)) {
//					System.out.println("command: " + command);
					if (command.equals("ping")) {
						ctx.writeAndFlush("ping success;");
					} else if(command.startsWith("pub") || command.startsWith("sub")){
						
					} else if(command.startsWith("updata")){
						List<String> pubList = new ArrayList<String>();
						String[] bodys = command.split("#");
						if (bodys != null && bodys.length == 3) {
//							System.out.println("  --dataId: " + bodys[1]);
//							System.out.println("  --data: " + bodys[2]);
							String[] pubs = bodys[2].split(",");
							String dataId = bodys[1];
							for (String pub : pubs) {
								if (!StringUtils.isBlank(pub)) {
									pubList.add(pub);
									RegeditCache.put(dataId, pub);
//									System.out.println(dataId + " -- " + pub + ", updata!");
								}
							}
							callback.execute(dataId, pubList);

						}
					}
				}
			}
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		log.log(Level.WARNING, "Unexpected exception from downstream.", cause);
		ctx.close();
	}

	public void messageReceived(ChannelHandlerContext ctx, String message)
			throws Exception {
		System.out.println(message);
	}
	//
	// @Override
	// protected void channelRead0(ChannelHandlerContext arg0, String arg1)
	// throws Exception {
	// // TODO Auto-generated method stub
	//
	// }

}
