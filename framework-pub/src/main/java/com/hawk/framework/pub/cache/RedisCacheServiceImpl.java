package com.hawk.framework.pub.cache;

public class RedisCacheServiceImpl implements CacheService{
	
	private RedisClient redisClient;
	
	public RedisCacheServiceImpl(RedisClient redisClient){
		this.redisClient = redisClient;
	}

	@Override
	public void put(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void put(String key, Object value, int expire) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T get(String key, Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

}
