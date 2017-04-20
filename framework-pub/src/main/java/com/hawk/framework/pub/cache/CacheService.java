package com.hawk.framework.pub.cache;

public interface CacheService {
	
	public void put(String key ,Object value);
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param expire 缓存数据时间，单位秒
	 */
	public void put(String key ,Object value ,int expire);
	
	public <T> T get(String key ,Class<T> clazz);

}
