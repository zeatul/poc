package com.hawk.ecom.pub;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hawk.ecom.pub.srping.config.CacheConfig;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.cache.RedisClient;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CacheConfig.class)
public class CacheServiceTest {

	@Autowired
	private CacheService cacheService;

	@Test
	public void test() {
		cacheService.put("111", "222");
		String s = cacheService.get("111", String.class);
		System.out.println(s);
	}

	// @Test
	public void test2() {
		JedisPoolConfig config = new JedisPoolConfig();

		JedisShardInfo jedisShardInfo = new JedisShardInfo("127.0.0.1", 6379);
		jedisShardInfo.setPassword("#ecomAst11m123!@");

		List<JedisShardInfo> shards = Arrays.asList(jedisShardInfo);

		ShardedJedisPool pool = new ShardedJedisPool(config, shards);

		RedisClient redisClient = new RedisClient(pool);

		redisClient.set("333", "kkk");
		System.out.println(redisClient.get("333"));

		ShardedJedis one = pool.getResource();
		one.set("111", "222");
		System.out.println(one.get("111"));

		one.close();

		pool.destroy();
	}

	// @Test
	public void test3() {

		JedisShardInfo jedisShardInfo = new JedisShardInfo("127.0.0.1", 6379);
		jedisShardInfo.setPassword("#ecoAst11m123!@");
		List<JedisShardInfo> shards = Arrays.asList(jedisShardInfo);

		ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);

		ShardedJedis one = pool.getResource();

		one.set("111", "222");
		System.out.println(one.get("111"));

		// pool.returnResource(one);
		one.close();
		pool.close();

	}
}
