package com.sunan.van.server.message;

import com.sunan.van.codec.Codec;

public class MessageBuilder<T> {
	
	private Codec<T, Message> codec;
	
	public Message build(T sourceMessage){
		Message result = new Message();
		result = codec.decoder(sourceMessage);
		return result;
		
	}

}
