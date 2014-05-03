package com.sunan.van.common.codec;

import com.sunan.van.common.message.Message;


public class StringCodec implements Codec<String, Message>{

	@Override
	public String encoder(Message message) {
		return new String(message.getBody());
	}

	@Override
	public Message decoder(String t) {
		Message message = new Message();
		message.setBody(t.getBytes());
		return message;
	}

	@Override
	public String format(String t) {
		// TODO Auto-generated method stub
		return t;
	}

}
