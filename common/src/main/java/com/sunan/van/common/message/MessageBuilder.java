package com.sunan.van.common.message;

import com.sunan.van.common.codec.Codec;

public class MessageBuilder<T> {
	
	private Codec<T, Message> codec;
	
	public Message build(T sourceMessage){
		Message result = new Message();
		result = codec.decoder(sourceMessage);
		return result;
		
	}

}
