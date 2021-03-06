package com.sunan.van.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunan.van.common.message.Message;
import com.sunan.van.core.VanFilter;

public class MockVanFilter implements VanFilter<Message> {
	
	private static Logger log = LoggerFactory.getLogger(MockVanFilter.class);

	@Override
	public Message doFilter(Message msg) {
		log.info("MockVanFilter.doFilter: " + new String(msg.getBody()));
		Message response = new Message();
		response.setBody(msg.getBody());
		String stringMessage = new String(msg.getBody());
		if("quit".equals(stringMessage)){
			
		}
		msg.setResponse(response);
		return msg;
	}

}
