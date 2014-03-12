package com.sunan.van.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunan.van.core.Message;
import com.sunan.van.core.VanFilter;

public class FileStorageFilter implements VanFilter {
	private static Logger log = LoggerFactory.getLogger(FileStorageFilter.class);
	
	private static Logger acceptedMessageLog = LoggerFactory.getLogger("acceptedMessageLog");

	@Override
	public Message doFilter(Message msg) {
		
		log.info("storage message: id=" + msg.getId() + ",topic=" + msg.getTopic() + ",message=" + msg.getMessage());
		acceptedMessageLog.info(msg.getId() + "," + msg.getMessage());
		return msg;
	}

}
