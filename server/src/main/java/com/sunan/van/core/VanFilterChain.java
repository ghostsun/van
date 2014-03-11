package com.sunan.van.core;

import java.util.ArrayList;
import java.util.List;

public class VanFilterChain {

	public List<VanFilter> filterList = new ArrayList<VanFilter>();

	public VanFilterChain add(VanFilter filter) {
		if (filter != null) {
			filterList.add(filter);
		}
		return this;
	}

	public Message doFilter(Message message){
		for(VanFilter filter : filterList){
			message = filter.doFilter(message);
		}
		return message;
	}

}
