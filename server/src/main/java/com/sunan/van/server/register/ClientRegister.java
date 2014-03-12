package com.sunan.van.server.register;

import org.springframework.util.StringUtils;

import com.sunan.van.topicmanager.SubTopicCache;

public class ClientRegister {

	public boolean register(ClientRegisterBean clientRegister){
		boolean result = false;
		if(clientRegister != null && !StringUtils.isEmpty(clientRegister.getSubTopic()) && !StringUtils.isEmpty(clientRegister.getClientChannel())){
			SubTopicCache.put(clientRegister.getSubTopic(), clientRegister.getClientChannel());
			result = true;
		}
		return result;
	}

}
