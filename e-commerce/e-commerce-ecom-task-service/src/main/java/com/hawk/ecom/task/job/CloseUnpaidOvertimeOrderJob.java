package com.hawk.ecom.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.mall.service.OrderAdminService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.StringTools;

public class CloseUnpaidOvertimeOrderJob implements Runnable{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private CacheService cacheService = FrameworkContext.getBean(CacheService.class);
	
	private OrderAdminService orderAdminService = FrameworkContext.getBean(OrderAdminService.class);
	
	private String buildKey(){
		return StringTools.concatWithSymbol("_", getClass().getName(),orderId);
	}
	
	private Integer orderId;
	
	public CloseUnpaidOvertimeOrderJob(Integer orderId){
		this.orderId = orderId;
	}
	

	@Override
	public void run() {
		logger.info("Start to execute close unpaid overtime job, orderId={}",orderId);
		AuthThreadLocal.setUserCode("system");
		/**
		 * 取缓存锁
		 */
		String key = buildKey();
		if (!cacheService.setnx(key, orderId.toString(), 600-5)){
			logger.error("Failed to get the redis lock,orderId={}",key);
			return ;
		}
		
		try {
			orderAdminService.closeUnpaidOrder(orderId, false);
			logger.info("Success to execute close unpaid overtime job, orderId={}",orderId);
		} catch (Exception e) {
			logger.error("Failed to execute close unapdi overtime job,oreerId="+orderId,e);
		}
		
		
		
	}

}
