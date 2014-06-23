package com.sunan.van.server.session;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
	
	private static final Map<String, Channel> sessionMap = new HashMap<String, Channel>();
	
	public static void addSession(String ip, Channel channel){
		sessionMap.put(ip, channel);
	}
	
	public static Channel getSession(String ip){
		return sessionMap.get(ip);
	}

}
