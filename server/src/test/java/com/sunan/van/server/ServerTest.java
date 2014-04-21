package com.sunan.van.server;

import junit.framework.TestCase;

import org.junit.Test;

import com.sunan.van.codec.StringCodec;
import com.sunan.van.core.VanFilterChain;
import com.sunan.van.core.impl.MockVanFilter;
import com.sunan.van.server.message.Message;
import com.sunan.van.server.register.ClientRegister;

public class ServerTest extends TestCase {
	
	@Test
	public void testStartup(){
		
		int port = 8080;
		
		Server server = Server.getInstance(port);
		VanFilterChain<Message> vanFilterChain = new VanFilterChain<Message>();
		ClientRegister register = new ClientRegister();
//		vanFilterChain.add(new FileStorageFilter());
		vanFilterChain.add(new MockVanFilter());
		server.setVanFilterChain(vanFilterChain);
		server.setRegister(register);
//		server.setCodec(new JsonMessageCodec());
		server.setCodec(new StringCodec());
		
//		server.setAcceptedManager(new NettyAcceptedManager());
		try {
			server.startup();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}