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
	
	public void delete(String key);
	
	/**
	 * 如果key已经存在就设置失败，不存在就设置成功
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public boolean setnx(String key,String value ,int expire);

}
