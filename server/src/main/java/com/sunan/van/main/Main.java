package com.sunan.van.main;

import com.sunan.van.core.impl.ConfigServerFilter;
import com.sunan.van.server.Server;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		int port = 8080;

		Server server = Server.getInstance(port);
		server.getVanFilterChain().add(
				Server.getContext().getBean(ConfigServerFilter.class));
		server.startup();

	}

}
