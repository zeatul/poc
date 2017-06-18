package com.hawk.framework.pub.cache;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {

	private ShardedJedisPool pool;
	
	public RedisClient(ShardedJedisPool pool){
		this.pool = pool;
	}

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
	public Long  hlen(final String key) {
		return execute(new Executor() {
			@SuppressWarnings("unchecked")
			@Override
			public Long  exec(ShardedJedis shardedJedis) {
				return shardedJedis.hlen(key);
			}

		});
	}

	/**
	 * 返回hashset所有元素值
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
			public <T> T exec(ShardedJedis shardedJedis) {

				shardedJedis.sadd(key, values.toArray(new String[] {}));
				return null;
			}
		});
	}

	/**
	 * 添加set 元素
	 * 
	 * @param key
	 * @param value
	 */
	public void sset(final String key, final String value) {
		execute(new Executor() {

			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.sadd(key, value);
				return null;
			}
		});
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
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.srem(key, values.toArray(new String[] {}));
				return null;
			}
		});

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

		});
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

		});
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
//				pool.returnResource(shardedJedis);
			}

		}
	}

	/**
	 * 删除
	 * @param key
	 */
	public void delete(final String key) {

		execute(new Executor() {

			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.del(key);
				
				return null;
			}
		});

	}
	
	/**
	 * 设置互斥锁
	 * @param key
	 * @param value
	 * @param expire 有效时间
	 * @return true 设置成功 ，false 设置失败
	 */
	public boolean setnx(final String key ,final String value,int expire){
		return execute(new Executor() {

			@SuppressWarnings("unchecked")
			@Override
			public Boolean exec(ShardedJedis shardedJedis) {
				long result = shardedJedis.setnx(key, value);
				if (result == 0){
					return false;
				}else{
					shardedJedis.expire(key, expire);
					return true;
				}
			}

		});
	}

}
