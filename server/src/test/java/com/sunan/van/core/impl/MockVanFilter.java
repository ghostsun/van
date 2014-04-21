package com.sunan.van.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunan.van.core.VanFilter;
import com.sunan.van.server.message.Message;

public class MockVanFilter implements VanFilter<Message> {
	
	private static Logger log = LoggerFactory.getLogger(MockVanFilter.class);

	@Override
	public Message doFilter(Message msg) {
		log.info("MockVanFilter.doFilter: " + new String(msg.getBody()));
		Message response = new Message();
		response.setBody(msg.getBody());
		msg.setResponse(response);
		return msg;
	}

}
