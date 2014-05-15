package com.sunan.van.core.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientCache {
	
	private static final Map<String, List<String>> pubClientCache = new HashMap<String, List<String>>();
	
	private static final Map<String, List<String>> subClientCache = new HashMap<String, List<String>>();
	
	private static final Map<String, List<String>> dataIdPubCache = new HashMap<String, List<String>>();
	
	private static final Map<String, List<String>> dataIdSubCache = new HashMap<String, List<String>>();
	
//	public static void initClient(String client){
//		List<String> pubList = pubClientCache.get(client);
//	}
	
	public static void pub(String client, String dataId){
		addClientCache(client, dataId, pubClientCache);
		addDataIdCache(client, dataId, dataIdPubCache);
		
	}
	
	public static void sub(String client, String dataId){
		addClientCache(client, dataId, subClientCache);
		addDataIdCache(client, dataId, dataIdSubCache);
	}
	
	public static void removeClient(String client){
		unpub(client);
		unsub(client);
	}
	
	public static void unpub(String client){
		removeClient(client, pubClientCache, dataIdPubCache);
		
	}
	
	public static void unsub(String client){
		removeClient(client, subClientCache, dataIdSubCache);
	}
	
	private static void removeClient(String client, Map<String, List<String>> clientCache, Map<String, List<String>> dataIdCache){
		List<String> dataIdList = clientCache.get(client);
		for(String dataId: dataIdList){
			removeDataIdCache(client, dataId, dataIdCache);
		}
		clientCache.remove(client);
	}

	private static void addClientCache(String client, String dataId, Map<String, List<String>> cache){
		List<String> pubList = cache.get(client);
		if(pubList == null){
			pubList = new ArrayList<String>();
		}
		if(!pubList.contains(dataId)){
			pubList.add(dataId);
		}
		cache.put(client, pubList);
	}
	
	private static void addDataIdCache(String client, String dataId, Map<String, List<String>> cache){
		List<String> dataIdList = cache.get(dataId);
		if(dataIdList == null){
			dataIdList = new ArrayList<String>();
		}
		
		if(!dataIdList.contains(client)){
			dataIdList.add(client);
		}
		
		cache.put(dataId, dataIdList);
	}

	
	private static void removeDataIdCache(String client, String dataId, Map<String, List<String>> cache){
		List<String> dataIdList = cache.get(dataId);
		if(dataIdList != null){
			dataIdList.remove(client);
		}
	}
}
