package com.sunan.van.core.cache;

import java.util.HashMap;
import java.util.Map;

import com.sunan.van.server.message.Message;

public class MessageCache<T> {
	
	@SuppressWarnings("rawtypes")
	private static final Map<String, Message> messageCache = new HashMap<String, Message>();
	
	public void put(String messageId, Message message){
		messageCache.put(messageId, message);
	}
	
	public Message get(String messageId){
		return messageCache.get(messageId);
	}
	
	public void remove(String messageId){
		Message message = messageCache.get(messageId);
		if(message != null){
			messageCache.remove(message);
		}
	}

}
