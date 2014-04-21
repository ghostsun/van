package com.sunan.van.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sunan.van.core.VanFilter;
import com.sunan.van.server.message.Message;

public class FileStorageFilter implements VanFilter<Message> {
	private static Logger log = LoggerFactory.getLogger(FileStorageFilter.class);
	
	private static Logger acceptedMessageLog = LoggerFactory.getLogger("acceptedMessageLog");

	@Override
	public Message doFilter(Message msg) {
		
		log.info("storage message: id=" + msg.getId() + ",topic=" + msg.getTopic() + ",body=" + new String(msg.getBody()));
		acceptedMessageLog.info(msg.getId() + "," + new String(msg.getBody()));
		return msg;
	}

}
