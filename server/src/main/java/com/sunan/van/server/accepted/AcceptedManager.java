package com.sunan.van.server.accepted;

import com.sunan.van.common.codec.Codec;
import com.sunan.van.common.message.Message;
import com.sunan.van.core.VanFilterChain;

public interface AcceptedManager {
	
	public void setFilterChain(VanFilterChain<Message> filterChain);
	
	public void setCodec(Codec codec);
	
}
