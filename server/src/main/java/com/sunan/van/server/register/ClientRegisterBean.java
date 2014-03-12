package com.sunan.van.server.register;

import io.netty.channel.Channel;

public class ClientRegisterBean {
	
	private String clientId;
	private String subTopic;
	private Channel clientChannel;
	
	public ClientRegisterBean(String clientId, String subTopic, Channel clientChannl){
		this.clientId = clientId;
		this.subTopic = subTopic;
		this.clientChannel = clientChannel;
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSubTopic() {
		return subTopic;
	}
	public void setSubTopic(String subTopic) {
		this.subTopic = subTopic;
	}
	public Channel getClientChannel() {
		return clientChannel;
	}
	public void setClientChannel(Channel clientChannel) {
		this.clientChannel = clientChannel;
	}
	
	

}
