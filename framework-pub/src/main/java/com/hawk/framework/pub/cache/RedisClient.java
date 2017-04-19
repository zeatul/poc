package com.hawk.framework.pub.cache;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {

	@Autowired
	private ShardedJedisPool pool;

	/**
	 * 加入缓存,无限期
	 * 
	 * @param key
	 * @param value
	 */
	public void set(final String key, final String value) {

		execute(new Executor() {
			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.set(key, value);
				return null;
			}
		});

	}

	/**
	 * 加入缓存
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 *            有效期，单位秒
	 */
	public void set(final String key, final String value, final int expire) {

		execute(new Executor() {
			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.set(key, value);
				shardedJedis.expire(key, expire);
				return null;
			}
		});

	}

	/**
	 * 从缓存取值
	 * 
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		return execute(new Executor() {
			
			@SuppressWarnings("unchecked")
			@Override
			public String exec(ShardedJedis shardedJedis) {

				return shardedJedis.get(key);
			}

		});
	}

	/**
	 * 添加hashset元素
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hset(final String key, final String field, final String value) {
		execute(new Executor() {
			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.hset(key, field, value);
				return null;
			}
			
		});
	}

	/**
	 * 删除hashset元素
	 * 
	 * @param key
	 * @param field
	 */
	public void hdel(final String key, final String field) {
		execute(new Executor() {
			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.hdel(key, field);
				return null;
			}			
		});
	}

	/**
	 * 查询hashset元素个数
	 * 
	 * @param key
	 * @return
	 */
	public Long hlen(final String key) {
		return execute(new Executor() {
			@SuppressWarnings("unchecked")
			@Override
			public Long exec(ShardedJedis shardedJedis) {
				return shardedJedis.hlen(key);
			}
			
		});
	}

	/**
	 * 返回hset所有元素值
	 * 
	 * @param key
	 * @return
	 */
	public List<String> hvals(final String key) {
		return execute(new Executor() {
			@SuppressWarnings("unchecked")
			@Override
			public List<String> exec(ShardedJedis shardedJedis) {
				return shardedJedis.hvals(key);
			}

		});
	}

	/**
	 * 添加set 元素
	 * 
	 * @param key
	 *            set 的 key
	 * @param values
	 *            set 内元素
	 */
	public void sset(final String key, final List<String> values) {
		execute(new Executor() {

			@Override
			public <T> T exec(ShardedJedisPipeline pipeline) {
				for (String value : values) {
					pipeline.sadd(key, value);
				}
				return null;
			}

			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				for (String value : values) {
					shardedJedis.sadd(key, value);
				}
				return null;
			}
		}, false);
	}

	/**
	 * 添加set 元素
	 * 
	 * @param key
	 *            set 的 key
	 * @param values
	 *            set 内元素
	 */
	public void sset(final String key, final String value) {
		execute(new Executor() {

			@Override
			public <T> T exec(ShardedJedisPipeline pipeline) {
				pipeline.sadd(key, value);
				return null;
			}

			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.sadd(key, value);
				return null;
			}
		}, false);
	}

	/**
	 * 删除set元素
	 * 
	 * @param key
	 * @param values
	 */
	public void sdel(final String key, final List<String> values) {
		execute(new Executor() {

			@Override
			public <T> T exec(ShardedJedisPipeline pipeline) {
				for (String value : values) {
					pipeline.srem(key, value);
				}
				return null;
			}

			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				for (String value : values) {
					shardedJedis.srem(key, value);
				}
				return null;
			}
		}, false);

	}

	/**
	 * 判断value 是否是 key 对应的set 成员
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean sexist(final String key, final String value) {
		return execute(new Executor() {

			@SuppressWarnings("unchecked")
			@Override
			public Boolean exec(ShardedJedis shardedJedis) {
				// TODO Auto-generated method stub
				return shardedJedis.sismember(key, value);
			}

			@SuppressWarnings("unchecked")
			@Override
			public Boolean exec(ShardedJedisPipeline pipeline) {
				// TODO Auto-generated method stub
				return pipeline.sismember(key, value).get();
			}

		}, false);
	}

	/**
	 * 返回key 对应的set的全部成员
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> sget(final String key) {
		return execute(new Executor() {

			@SuppressWarnings("unchecked")
			@Override
			public Set<String> exec(ShardedJedis shardedJedis) {

				return shardedJedis.smembers(key);
			}

			@SuppressWarnings("unchecked")
			@Override
			public Set<String> exec(ShardedJedisPipeline pipeline) {
				return pipeline.smembers(key).get();
			}
		}, false);
	}

	/**
	 * 判断key值是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean exist(final String key) {
		return execute(new Executor() {

			@SuppressWarnings("unchecked")
			@Override
			public Boolean exec(ShardedJedis shardedJedis) {
				// TODO Auto-generated method stub
				return shardedJedis.exists(key);
			}

			@SuppressWarnings("unchecked")
			@Override
			public Boolean exec(ShardedJedisPipeline pipeline) {
				// TODO Auto-generated method stub
				return pipeline.exists(key).get();
			}

		}, false);
	}

	private interface Executor {
		<T> T exec(ShardedJedis shardedJedis);
	}

	private <T> T execute(Executor executor) {

		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = pool.getResource();
			return executor.exec(shardedJedis);
		} finally {
			if (shardedJedis != null) {
				shardedJedis.close();
			}

		}
	}

	/**
	 * 删除可以
	 * 
	 * @param key
	 * @param async
	 */
	public void delete(final String key) {

		execute(new Executor() {

			@Override
			public <T> T exec(ShardedJedisPipeline pipeline) {
				pipeline.del(key);
				return null;
			}

			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.del(key);
				return null;
			}
		}, false);

	}

}
