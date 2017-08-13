package com.hawk.ecom.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskTimeoutException;

import com.hawk.ecom.pub.job.TaskPool;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.task.service.ChargeDataTaskService;
import com.hawk.ecom.trans.service.OrderDetailDeliveryDataService;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.StringTools;

public class CheckChargeResultJob implements Runnable{
	
	private Integer orderDetailDeliveryDataId;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private OrderDetailDeliveryDataService orderDetailDeliveryDataService = FrameworkContext.getBean(OrderDetailDeliveryDataService.class);
	
	private TaskPool taskPool = FrameworkContext.getBean(TaskPool.class);
	
	
	
	private CacheService cacheService = FrameworkContext.getBean(CacheService.class);
	
	
	public CheckChargeResultJob(Integer orderDetailDeliveryDataId){
		this.orderDetailDeliveryDataId = orderDetailDeliveryDataId;
	}
	
	private String buildChargeTaskKey(){
		return StringTools.concatWithSymbol("_", getClass().getName(),orderDetailDeliveryDataId);
	}

	@Override
	public void run() {
		logger.info("Start to execute check charge result job, orderDetailDeliveryDataId={}",orderDetailDeliveryDataId);
		AuthThreadLocal.setUserCode("system");
		/**
		 * 取缓存锁
		 */
		String key = buildChargeTaskKey();
		if (!cacheService.setnx(key, orderDetailDeliveryDataId.toString(), 600-5)){
			logger.error("Failed to get the redis lock,key={}",key);
			return ;
		}		
		
		try {
			int result = orderDetailDeliveryDataService.checkChargeResult(orderDetailDeliveryDataId);
			if (result == 1){
				CheckSuccessOrderDetailJob job = new CheckSuccessOrderDetailJob(orderDetailDeliveryDataService.loadById(orderDetailDeliveryDataId).getOrderDetailId());
				taskPool.submit(job);
			}else if (result == -1){
				CheckFailedOrderDetailJob job = new CheckFailedOrderDetailJob(orderDetailDeliveryDataService.loadById(orderDetailDeliveryDataId).getOrderDetailId());
				taskPool.submit(job);
			}else{
				logger.info("Charge data is in processing,orderDetailDeliveryDataId={}",orderDetailDeliveryDataId);
			}
			logger.info("Success to check charge result job, orderDetailDeliveryDataId={}",orderDetailDeliveryDataId);
		} catch (Exception e) {
			logger.error("Failed to check charge result job, orderDetailDeliveryDataId="+orderDetailDeliveryDataId,e);
		}
		
	}

}
