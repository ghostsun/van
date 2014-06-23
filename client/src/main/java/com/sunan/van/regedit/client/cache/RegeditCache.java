package com.sunan.van.regedit.client.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegeditCache {
	
	private static final Map<String, List<String>> regeditCache = new HashMap<String, List<String>>();
	
	private static final List<String> subCache = new ArrayList<String>();
	
	public static void put(String dataId, String ip){
		List<String> dataIdList = regeditCache.get(dataId);
		if(dataIdList == null){
			dataIdList = new ArrayList<String>();
		}
		if(!dataIdList.contains(ip)){
			dataIdList.add(ip);
		}
	}
	
	public static List<String> get(String dataId){
		return regeditCache.get(dataId);
	}
	
	public static void addSub(String dataId){
		if(!subCache.contains(dataId)){
			subCache.add(dataId);
		}
	}
	
	public static List<String> getSubList(){
		return subCache;
	}

}
