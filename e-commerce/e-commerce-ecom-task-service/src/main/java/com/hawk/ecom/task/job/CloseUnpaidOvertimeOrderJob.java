package com.hawk.ecom.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.StringTools;

public class CloseUnpaidOvertimeOrderJob implements Runnable{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private CacheService cacheService = FrameworkContext.getBean(CacheService.class);
	
	private String buildKey(Integer orderId){
		return StringTools.concatWithSymbol("_", ChargeDataJob.class.getName(),orderId);
	}
	
	private Integer orderId;
	
	public CloseUnpaidOvertimeOrderJob(Integer orderId){
		this.orderId = orderId;
	}
	

	@Override
	public void run() {
		logger.info("Start to execute close unpaid overtime job, orderId={}",orderId);
		/**
		 * 取缓存锁
		 */
		String key = buildKey(orderId);
		if (!cacheService.setnx(key, orderId.toString(), 600-5)){
			logger.error("Failed to get the redis lock,orderId={}",key);
			return ;
		}
		
		logger.info("Success to execute close unpaid overtime job, orderId={}",orderId);
		
	}

}
