package com.hawk.ecom.pub.srping.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.cache.RedisCacheServiceImpl;
import com.hawk.framework.pub.cache.RedisClient;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
@PropertySource("classpath:/com/hawk/ecom/pub/env/redis.properties")
public class CacheConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		int maxIdle = env.getProperty("redis.pool.maxIdle", Integer.class);
		int maxTotal = env.getProperty("redis.pool.maxTotal", Integer.class);
		int maxWaitMillis = env.getProperty("redis.pool.maxWaitMillis", Integer.class);
		boolean testOnBorrow = env.getProperty("redis.pool.testOnBorrow", Boolean.class);
		boolean testOnReturn = env.getProperty("redis.pool.testOnReturn", Boolean.class);
		
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		jedisPoolConfig.setTestOnBorrow(testOnBorrow);
		jedisPoolConfig.setTestOnReturn(testOnReturn);
		
		return jedisPoolConfig;
	}
	
	@Bean
	public ShardedJedisPool shardedJedisPool(JedisPoolConfig jedisPoolConfig){
		List<JedisShardInfo> JedisShardInfoList = new ArrayList<JedisShardInfo>();
		
		String nodes = env.getProperty("redis.nodes", String.class);
		String[] nodeStrArray = nodes.split(";");
		for (String nodeStr : nodeStrArray){
			String[] strArray = nodeStr.split(":");
			
			JedisShardInfo jedisShardInfo = new JedisShardInfo(strArray[0], strArray[1]);
			if (strArray.length >2){
				jedisShardInfo.setPassword(strArray[2]);
			}
			
			JedisShardInfoList.add(jedisShardInfo);
		}

		
		
		
		ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, JedisShardInfoList);	
		
		
		return shardedJedisPool;
	}
	
	@Bean
	public RedisClient redisClient(ShardedJedisPool pool){
		return new RedisClient(pool);		
	}
	
	@Bean
	public CacheService cacheService(RedisClient redisClient){
		return new RedisCacheServiceImpl(redisClient);
	}
}