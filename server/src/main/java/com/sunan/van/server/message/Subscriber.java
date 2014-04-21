package com.sunan.van.server.message;

/**
 * Subscriber
 * @author sunan
 * @Time 2014-4-16 下午7:05:56
 */
public class Subscriber {
	
	private String ip;
	
	private String port;
	
	private String groupId;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String toString(){
		return "ip: " + ip + ", port: " + port + ", groupId: " + groupId;
	}
	
	

}
