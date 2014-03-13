package com.sunan.van.codec;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public class JsonCodec<T> {
	
	private static final Gson gson = new Gson();
	
	private static final Gson formateGson = new GsonBuilder().setPrettyPrinting().create();
	
	private static final JsonParser parser = new JsonParser();
	
//	private static final Map<Class, Gson> gsonMap = new HashMap<Class, Gson>();
	
	
	
	public static String encoder(Object obj){
		return gson.toJson(obj);
	}
	
	@SuppressWarnings("unchecked")
	public static Object decoder(String json, Class clazz){
//		Gson gson = gsonMap.get(clazz);
//		if(gson == null){
//			gson = new Gson<clazz>();
//		}
		return gson.fromJson(json, clazz);
	}
	
	public static String format(String jsonSrc){
		return formateGson.toJson(parser.parse(jsonSrc));
	}

}
