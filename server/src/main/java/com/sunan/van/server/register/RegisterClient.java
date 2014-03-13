package com.sunan.van.server.register;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class RegisterClient {
	
	private static final Map<Channel, Boolean> registerdClient = new HashMap<Channel, Boolean>();
	
	
	public static void put(Channel channel){
		if(channel != null){
			registerdClient.put(channel, true);
		}
	}
	
	public static boolean isRegister(Channel channel){
		boolean result = false;
		if(registerdClient.get(channel) != null){
			result = true;
		}
		return result;
	}

}
