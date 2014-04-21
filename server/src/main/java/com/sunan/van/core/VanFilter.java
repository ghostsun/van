package com.sunan.van.core;


public interface VanFilter<T> {
	
	public T doFilter(T msg);

}
