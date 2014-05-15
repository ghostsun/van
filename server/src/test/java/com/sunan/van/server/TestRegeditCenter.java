package com.sunan.van.server;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sunan.van.core.impl.ConfigServerFilter;

public class TestRegeditCenter  extends TestCase {
	
	 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-regeditCenter.xml");
	
	@Test
	public void testStartup(){
		
		int port = 8080;
		
		Server server = Server.getInstance(port);
		server.getVanFilterChain().add(context.getBean(ConfigServerFilter.class));
//		VanFilterChain<Message> vanFilterChain = new VanFilterChain<Message>();
//		ClientRegister register = new ClientRegister();
//		vanFilterChain.add(new FileStorageFilter());
//		vanFilterChain.add(new MockVanFilter());
//		server.setVanFilterChain(vanFilterChain);
//		server.setRegister(register);
//		server.setCodec(new JsonMessageCodec());
//		server.setCodec(new StringCodec());
		
//		server.setAcceptedManager(new NettyAcceptedManager());
		try {
			server.startup();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
