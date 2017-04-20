package com.hawk.framework.pub.cache;

import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

public class RedisCacheServiceImpl implements CacheService{
	
	private RedisClient redisClient;
	
	public RedisCacheServiceImpl(RedisClient redisClient){
		this.redisClient = redisClient;
	}

	@Override
	public void put(String key, Object value) {
		if (StringTools.isNullOrEmpty(key)){
			return;
		}
		if (value == null){
			return ;
		}
		if (value instanceof String){
			redisClient.set(key,(String) value);
		}else{
			redisClient.set(key, JsonTools.toJsonString(value));
		}
	}

	@Override
	public void put(String key, Object value, int expire) {
		if (StringTools.isNullOrEmpty(key)){
			return;
		}
		if (value == null){
			return ;
		}
		if (value instanceof String){
			redisClient.set(key,(String) value,expire);
		}else{
			redisClient.set(key, JsonTools.toJsonString(value),expire);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String key, Class<T> clazz) {
		if (StringTools.isNullOrEmpty(key)){
			return null;
		}
		
		String value = redisClient.get(key);
		if (value == null){
			return null;
		}
		
		if (clazz.equals(String.class)){
			return (T)value;
		}
		
		return JsonTools.toObject(value, clazz);
	}

}
