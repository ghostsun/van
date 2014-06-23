package com.sunan.van.regedit.client;

import org.junit.Test;

public class RegeditCenterTest {
	String host = "127.0.0.1";
	int port = 8080;
	String pubDataId = "testPub";
	String subDataId =  "testSub";

	@Test
	public void testPub() throws InterruptedException {

		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				ClientConfig cc = new ClientConfig();
				cc.setHost(host);
				cc.setPort(port);
				RegeditCenter rc = null;
				try {
					rc = new RegeditCenter(cc, new TestCallback());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(rc != null){
					rc.pub(pubDataId);
					rc.sub(pubDataId);
				}
				
			}
			
		});
		
		t.run();
		
		Thread.sleep(5 * 1000);
	}

	@Test
	public void testSub() throws InterruptedException {
		ClientConfig cc = new ClientConfig();
		cc.setHost(host);
		cc.setPort(port);
		RegeditCenter rc = new RegeditCenter(cc, new TestCallback());
		rc.sub(subDataId);
		rc.pub(subDataId);
		
		Thread.sleep(5 * 1000);
	}

}
