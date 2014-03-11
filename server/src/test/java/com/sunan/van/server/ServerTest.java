package com.sunan.van.server;

import junit.framework.TestCase;

import org.junit.Test;

import com.sunan.van.core.VanFilterChain;
import com.sunan.van.core.impl.FileStorageFilter;

public class ServerTest extends TestCase {
	
	@Test
	public void testStartup(){
		
		int port = 8080;
		
		Server server = new Server(port);
		VanFilterChain vanFilterChain = new VanFilterChain();
		vanFilterChain.add(new FileStorageFilter());
		server.setVanFilterChain(vanFilterChain);
//		server.setAcceptedManager(new NettyAcceptedManager());
		try {
			server.startup();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}