package com.sunan.hsnotify.testlogback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogback {
	
	 private static Logger log = LoggerFactory.getLogger(TestLogback.class); 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestLogback test = new TestLogback();
		test.test();
		test.testLoop();

	}
	
	public void test(){
		log.debug("TestA-debug");
        log.info("TestA-info");
        log.warn("TestA-warn");
        log.error("TestA-error");
	}
	
	public void testLoop(){
		long n = 100 * 100;
		long start = System.currentTimeMillis();
		for(long i = 0; i < n; i++){
			log.info(i + "I'am a log! I've just been a penny on the train track my entire life and the 'ole Union Pacific keeps on comin', every day, back and forth, runnin' me over, runnin' me down, but I will not flatten. ");
		}
		long time = System.currentTimeMillis() - start;
		log.error("time: " + time);
		System.out.println(time);
	}

}
