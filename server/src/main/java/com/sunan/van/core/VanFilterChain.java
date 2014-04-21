package com.sunan.van.core;

import java.util.ArrayList;
import java.util.List;

public class VanFilterChain<T> {

	public List<VanFilter<T>> filterList = new ArrayList<VanFilter<T>>();

	public VanFilterChain<T> add(VanFilter<T> filter) {
		if (filter != null) {
			filterList.add(filter);
		}
		return this;
	}

	public T doFilter(T message){
		for(VanFilter<T> filter : filterList){
			message = filter.doFilter(message);
		}
		return message;
	}

}
