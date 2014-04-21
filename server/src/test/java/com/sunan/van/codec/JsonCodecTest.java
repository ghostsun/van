package com.sunan.van.codec;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.sunan.van.server.message.Message;
import com.sunan.van.server.message.Publisher;
import com.sunan.van.server.message.Subscriber;

public class JsonCodecTest {
	
	private Codec<String, Message> jsonCodec = new JsonMessageCodec();
	
	private Message message;
	
	@Before
	public void staUp(){
		message = new Message();
		message.setBody("hahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahaha".getBytes());
		message.setId("test00001");
		Publisher p = new Publisher();
		p.setIp("127.0.0.1");
		p.setPort("123123");
		p.setGroupId("group1");
		message.setPublisher(p);
		Subscriber s = new Subscriber();
		s.setIp("127.0.0.1");
		s.setPort("321321");
		s.setGroupId("group1");
		message.setSubscriber(s);
		message.setTopic("test");
	}
	

	@Test
	public void testEncoder() {
		String json = null;
//		TestBean test = new TestBean();
		json = jsonCodec.encoder(message);
		System.out.println(json);
		System.out.println("format: " + jsonCodec.format(json));
		
	}

	@Test
	public void testDecoder() {
		String json = "{\"publisher\":{\"ip\":\"127.0.0.1\",\"port\":\"123123\",\"groupId\":\"group1\"},\"subscriber\":{\"ip\":\"127.0.0.1\",\"port\":\"321321\",\"groupId\":\"group1\"},\"id\":\"test00001\",\"topic\":\"test\",\"body\":[104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97,104,97]}";
		Message thisMessage = null;
		thisMessage = jsonCodec.decoder(json);
		System.out.println(thisMessage.toString());
	}
	
	class TestBean{
		private String name = "testBean";
		private int age = 21;
		private long createTime = System.currentTimeMillis();
		private Date thisTime = new Date();
		private String info = "hahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahaha";
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public long getCreateTime() {
			return createTime;
		}
		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		
		public Date getThisTime() {
			return thisTime;
		}
		public void setThisTime(Date thisTime) {
			this.thisTime = thisTime;
		}
		public String toString(){
			return "name: " + name + ", age: " + age + ", createTime: " + createTime + ", info: " + info + ", thisTime: " + thisTime;
		}
		
		
	}

}
