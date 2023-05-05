/**   
 * Copyright © 2017 bailianpay.com All rights reserved.
 * 
 * @Title: RedisComponent.java 
 * @Prject: pay_quota_1.0
 * @Package: com.zrj.pay.risk.quota.component 
 * @Description: TODO
 * @author: ningbin   
 * @date: 2017年11月25日 下午4:34:07 
 * @version: V1.0   
 *//*

package com.jili.component;

import com.zrj.pay.core.cache.RedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

*/
/**
 * @ClassName: RedisComponent
 * @Description: 缓存服务组件
 * @author: ningbin
 * @date: 2017年11月25日 下午4:34:07
 *//*

public class RedisComponent {

	private static final Logger logger = LogManager.getLogger(RedisComponent.class);

	private RedisService redisService;

	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}

	private int expireSecond;

	public void setExpireSecond(int expireSecond) {
		this.expireSecond = expireSecond;
	}

	public boolean exists(String key) throws RedisServiceException {
		try {
			boolean flag = this.redisService.exists(key);
			logger.info("缓存key：({})是否存在：({})", key, flag);
			return flag;
		} catch (Exception e) {
			throw new RedisServiceException("缓存服务组件异常", e);
		}
	}
	
	public String set(String key,String json) throws RedisServiceException {
		try {
			String value = this.redisService.set(key, json);
			logger.info("新建缓存：{} = {}", key, value);
			return value;
		} catch (Exception e) {
			throw new RedisServiceException("缓存服务组件异常", e);
		}
	}

	public String hset(String key,String json,String expire) throws RedisServiceException {
		try {
			String value = this.redisService.hset(key, json ,expire);
			logger.info("新建缓存：{} = {}", key, value);
			return value;
		} catch (Exception e) {
			throw new RedisServiceException("缓存服务组件异常", e);
		}
	}
	
	public String query(String key) throws RedisServiceException {
		try {
			String value = this.redisService.get(key);
			if (null == value) {
				logger.warn("未查询到缓存({})", key);
			}
			logger.info("缓存查询：{} = {}", key, value);
			return value;
		} catch (Exception e) {
			throw new RedisServiceException("缓存服务组件异常", e);
		}
	}

	public boolean update(String key, String value) throws RedisServiceException {
		try {
			logger.info("更新缓存： {} = {}", key, value);
			boolean flag = false;
			String res = this.redisService.set(key, value);
			if (res.equals(value)) {
				flag = true;
				if (expireSecond > 0) {
					Long n = this.redisService.expire(key, expireSecond);
					logger.info("设置失效时间 ：{} = {}s", key, n);
				}
			} else {
				boolean f = this.redisService.del(key);
				if (f) {
					logger.warn("缓存更新失败,强制清除({})", key);
				}
			}
			return flag;
		} catch (Exception e) {
			throw new RedisServiceException("缓存服务组件异常", e);
		}
	}

	public boolean update(String key, String value, int t) throws RedisServiceException {
		try {
			logger.info("更新缓存： {} = {}, {}", key, value, t);
			boolean flag = false;
			String res = this.redisService.set(key, value);
			if (res.equals(value)) {
				this.redisService.expire(key, t);
			} else {
				boolean f = this.redisService.del(key);
				if (f) {
					logger.warn("缓存更新失败,强制清除({})", key);
				}
			}
			return flag;
		} catch (Exception e) {
			throw new RedisServiceException("缓存服务组件异常", e);
		}
	}

	public boolean delete(String key) throws RedisServiceException {
		try {
			boolean flag = this.redisService.del(key);
			logger.info("清除缓存({})({})", key, flag);
			return flag;
		} catch (Exception e) {
			throw new RedisServiceException("缓存服务组件异常", e);
		}
	}

	public Long getExpire(String key) throws RedisServiceException {
		try {
			Long d = this.redisService.ttl(key);
			logger.info("查询缓存失效时间 ({})({}s)", key, d);
			return d;
		} catch (Exception e) {
			throw new RedisServiceException("缓存服务组件异常", e);
		}
	}

	public Long setExpire(String key) throws RedisServiceException {
		try {
			Long d = this.redisService.expire(key,expireSecond);
			logger.info("设置缓存失效时间 ({})({}s)", key, d);
			return d;
		} catch (Exception e) {
			throw new RedisServiceException("缓存服务组件异常", e);
		}
	}
}
*/
