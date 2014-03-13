package com.sunan.van.server.register;

import io.netty.channel.Channel;

public class ClientRegisterBean {
	
	public static final String publisher = "PUBLISHER";
	public static final String subscribers = "SUBSCRIBERS";
	
	private String clientId;
	private String subTopic;
	private Channel clientChannel;
	private String group;
	
	public ClientRegisterBean(String clientId, String subTopic, Channel clientChannel){
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	

}
