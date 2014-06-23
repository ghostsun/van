package com.sunan.van.core.impl;

import io.netty.channel.Channel;

import java.util.List;
import java.util.Map;

import com.sunan.van.common.message.Message;
import com.sunan.van.core.VanFilter;
import com.sunan.van.core.cache.ClientCache;
import com.sunan.van.server.session.SessionManager;

public class ConfigServerFilter implements VanFilter<Message> {
	
	public final static String OPT_PUB = "PUB";
	public final static String OPT_SUB = "SUB";
	public final static String OPT_STATS = "STATS";

	@Override
	public Message doFilter(Message msg) {
		
		String client = msg.getClient();
//		String[] temp = client.split(":");
//		if(temp != null && temp.length > 1){
//			client = temp[0];
//		}
		String data = new String(msg.getBody());
		String opt = null;
		String dataId = null;
		String[] dataArray = data.split(",");
		if(dataArray.length == 2){
			opt = dataArray[0].toUpperCase();
			dataId = dataArray[1];
		}
		
		if(opt == null || dataId == null){
			//TODO error process
			return msg;
		}
		
		if(OPT_PUB.equals(opt)){
			ClientCache.pub(client, dataId);
			dataIdChange(dataId);
			System.out.println(client + " pub " + dataId + " success");
			msg.setBody("pub success;".getBytes());
		}else if(OPT_SUB.equals(opt)){
			ClientCache.sub(client, dataId);
			/* 推送订阅段 */
			 List<String> pubList = ClientCache.getDataidpubcache().get(dataId);
			 if(pubList != null){
				 StringBuffer sb = new StringBuffer();
				 
				 for(String pub : pubList){
					 sb.append(pub + ",");
				 }
				 System.out.println(client + " sub " + dataId + " success");
				 System.out.println(" dataId list:  " + sb.toString());
			 
				 msg.setBody(("updata#" + dataId + "#" + sb.toString() + ";").getBytes());
			 }else{
				 msg.setBody("sub success;".getBytes());
			 }
		}else if(OPT_STATS.equals(opt)){
			String stats = "";
			Map<String, List<String>> dataIdPubCache = ClientCache.getDataidpubcache();
			for(String dataIdS : dataIdPubCache.keySet()){
				stats += dataIdS + ": ";
				for(String pubItems : dataIdPubCache.get(dataIdS)){
					stats += pubItems + ", ";
				}
				stats += "/r/n";
			}
//			msg.setBody("success".getBytes());
		}
		
//		msg.setBody("success".getBytes());
		
		return msg;
		
	}
	
	
	public void dataIdChange(String dataId){
		List<String> subList = ClientCache.getDataidsubcache().get(dataId);
		List<String> pubList = ClientCache.getDataidpubcache().get(dataId);
		StringBuffer sb = new StringBuffer();
		for(String pub : pubList){
			sb.append(pub + ",");
		}
		String pubs = sb.toString();
		System.out.println("dataId " + dataId + " change");
		System.out.println(" dataId List " + pubs);
		if(subList == null){
			return;
		}
		for(String sub : subList){
			Channel channel = SessionManager.getSession(sub);
			channel.flush();
			channel.writeAndFlush("updata#" + dataId + "#" + pubs + ";\r\n");
			System.out.println("sub " + sub + " flush!");
		}
	}

}
