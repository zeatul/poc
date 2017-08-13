package com.hawk.ecom.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.trans.service.OrderService;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.StringTools;

/**
 * 处理明细失败的订单
 * @author zhp
 *
 */
public class CheckFailedOrderJob implements Runnable {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private CacheService cacheService = FrameworkContext.getBean(CacheService.class);
	
	private OrderService orderService= FrameworkContext.getBean(OrderService.class);
	
	private Integer orderId;
	
	public CheckFailedOrderJob(Integer orderId){
		this.orderId = orderId;
	}
	
	private String buildKey(){
		return StringTools.concatWithSymbol("_", getClass().getName(),orderId);
	}

	@Override
	public void run() {
		logger.info("Start to execute check failed order job, orderId={}",orderId);
		
		/**
		 * 取缓存锁
		 */
		String key = buildKey();
		if (!cacheService.setnx(key, orderId.toString(), 600-5)){
			logger.error("Failed to get the redis lock,orderId={}",key);
			return ;
		}
		
		try {
			orderService.checkFailedOrder(orderId);
			logger.info("Success to execute check failed order job, orderId={}",orderId);
		} catch (Exception e) {
			logger.error("Failed to execute check failed order job, orderId="+orderId,e);
		}
		
	}

}
