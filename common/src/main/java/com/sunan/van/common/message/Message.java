package com.sunan.van.common.message;

/**
 * Message
 * @author sunan
 * @Time 2014-4-16 下午7:05:39
 * @param <T>
 */
public class Message {
	
	private Publisher publisher;
	
	private Subscriber subscriber;
	
	private String id;
	
	private String topic;
	
	private byte[] body;
	
	private Message response;

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}
	
	

	public Message getResponse() {
		return response;
	}

	public void setResponse(Message response) {
		this.response = response;
	}

	public String toString(){
		return "id: " + id + ", topic: " + topic + ", body: " + new String(body) + ", publisher: { " + publisher + "}, subscriber: { " + subscriber + " }";
	}
	
	

}
