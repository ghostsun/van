package com.sunan.van.codec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.sunan.van.server.message.Message;

public class JsonMessageCodec implements Codec<String, Message> {
	
	private static final Gson gson = new Gson();
	
	private static final Gson formateGson = new GsonBuilder().setPrettyPrinting().create();
	
	private static final JsonParser parser = new JsonParser();
	
	public String encoder(Message obj){
		return gson.toJson(obj);
	}
	
	public Message decoder(String json){
//		gson.
		return gson.fromJson(json, Message.class);
	}
	
	public String format(String jsonSrc){
		return formateGson.toJson(parser.parse(jsonSrc));
	}

}
