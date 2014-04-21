package com.sunan.van.server.accepted;

import com.sunan.van.codec.Codec;
import com.sunan.van.core.VanFilterChain;
import com.sunan.van.server.message.Message;

public interface AcceptedManager {
	
	public void setFilterChain(VanFilterChain<Message> filterChain);
	
	public void setCodec(Codec codec);
	
}
