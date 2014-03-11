package com.sunan.van.topicmanager;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class SubTopicCache {
	
	public static final Map<String, Channel> topicCache = new HashMap<String, Channel>();
	
	public static void put(String topic, Channel subChannel){
		topicCache.put(topic, subChannel);
	}
	
	public static Channel get(String topic){
		return topicCache.get(topic);
	}
	
	public static void remove(String topic){
		Channel sub = get(topic);
		if(sub != null){
			topicCache.remove(sub);
		}
	}

}
