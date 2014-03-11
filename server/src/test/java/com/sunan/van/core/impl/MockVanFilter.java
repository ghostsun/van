package com.sunan.van.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunan.van.core.Message;
import com.sunan.van.core.VanFilter;

public class MockVanFilter implements VanFilter {
	
	private static Logger log = LoggerFactory.getLogger(MockVanFilter.class);

	@Override
	public Message doFilter(Message msg) {
		log.info("MockVanFilter.doFilter");
		return msg;
	}

}
