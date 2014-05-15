package com.sunan.van.core.impl;

import com.sunan.van.common.message.Message;
import com.sunan.van.core.VanFilter;
import com.sunan.van.core.cache.ClientCache;

public class ConfigServerFilter implements VanFilter<Message> {
	
	public final static String OPT_PUB = "PUB";
	public final static String OPT_SUB = "SUB";

	@Override
	public Message doFilter(Message msg) {
		
		String client = msg.getClient();
		String data = new String(msg.getBody());
		String opt = null;
		String dataId = null;
		String[] dataArray = data.split(",");
		if(dataArray.length == 2){
			opt = dataArray[0];
			dataId = dataArray[1];
		}
		
		if(opt == null || dataId == null){
			//TODO error process
			return msg;
		}
		
		if(OPT_PUB.equals(opt)){
			ClientCache.pub(client, dataId);
		}else if(OPT_SUB.equals(opt)){
			ClientCache.sub(client, dataId);
		}
		
		msg.setBody("success".getBytes());
		
		return msg;
		
	}

}
