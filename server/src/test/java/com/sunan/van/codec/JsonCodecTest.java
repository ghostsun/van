package com.sunan.van.codec;

import java.util.Date;

import org.junit.Test;

public class JsonCodecTest {
	
	

	@Test
	public void testEncoder() {
		String json = null;
		TestBean test = new TestBean();
		json = JsonCodec.encoder(test);
		System.out.println(json);
		System.out.println("format: " + JsonCodec.format(json));
		
	}

	@Test
	public void testDecoder() {
		String json = "{\"name\":\"testBean\",\"age\":21,\"createTime\":1394705189413,\"thisTime\":\"Mar 13, 2014 6:22:03 PM\",\"info\":\"hahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahahaha\"}";
		TestBean test = null;
		test = (TestBean) JsonCodec.decoder(json, TestBean.class);
		System.out.println(test.toString());
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
