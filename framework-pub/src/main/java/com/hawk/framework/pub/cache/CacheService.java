package com.hawk.framework.pub.cache;

public interface CacheService {
	
	public void put(String key ,Object value);
	
	public void put(String key ,Object value ,int expire);
	
	public <T> T get(String key ,Class<T> clazz);

}
