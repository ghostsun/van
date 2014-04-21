package com.sunan.van.codec;

/**
 * Codec
 * 编解码接口
 * 序列化
 * @author sunan
 * @Time 2014-4-16 下午6:46:27
 * @param <T>
 */
public interface Codec<T, R> {
	
	/**
	 * 编码，序列化
	 * @param o
	 * @return
	 */
	public T encoder(R r);
	
	/**
	 * 解码，反序列化
	 * @param t
	 * @param clazz
	 * @return
	 */
	public R decoder(T t);
	
	/**
	 * 编码格式化
	 * @param t
	 * @return
	 */
	public T format(T t);

}
