package com.hawk.ecom.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.trans.service.OrderDetailService;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.StringTools;

public class CheckFailedOrderDetailJob implements Runnable {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private CacheService cacheService = FrameworkContext.getBean(CacheService.class);
	
	private OrderDetailService orderDetailService= FrameworkContext.getBean(OrderDetailService.class);
	
	private Integer orderDetailId;
	
	public CheckFailedOrderDetailJob(Integer orderDetailId){
		this.orderDetailId = orderDetailId;
	}
	
	private String buildKey(){
		return StringTools.concatWithSymbol("_", getClass().getName(),orderDetailId);
	}

	@Override
	public void run() {
		logger.info("Start to execute check failed order detail job, orderDetailId={}",orderDetailId);
		
		/**
		 * 取缓存锁
		 */
		String key = buildKey();
		if (!cacheService.setnx(key, orderDetailId.toString(), 600-5)){
			logger.error("Failed to get the redis lock,orderDetailId={}",key);
			return ;
		}
		
		try {
			orderDetailService.checkFailedOrderDetail(orderDetailId);
			logger.info("Success to execute check failed order detail job, orderDetailId={}",orderDetailId);
		} catch (Exception e) {
			logger.error("Failed to execute check failed order detail job, orderDetailId="+orderDetailId,e);
		}
		
	}

}
