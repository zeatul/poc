package com.hawk.ecom.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.pay.service.PaymentService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.StringTools;

public class CheckUnfinishedPaymentJob implements Runnable {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private CacheService cacheService = FrameworkContext.getBean(CacheService.class);
	
	private PaymentService paymentService = FrameworkContext.getBean(PaymentService.class);
	
	private String buildKey(){
		return StringTools.concatWithSymbol("_", getClass().getName(),paymentBillId);
	}
	
	private Integer paymentBillId;
	
	public CheckUnfinishedPaymentJob(Integer paymentBillId){
		this.paymentBillId = paymentBillId;
	}
	
	@Override
	public void run() {
		logger.info("Start to execute check unfinished payment job, paymentBillId={}",paymentBillId);
		AuthThreadLocal.setUserCode("system");
		
		/**
		 * 取缓存锁
		 */
		String key = buildKey();
		if (!cacheService.setnx(key, paymentBillId.toString(), 600-5)){
			logger.error("Failed to get the redis lock,paymentBillId={}",key);
			return ;
		}
		
		try {
			paymentService.checkUnfinishedPayment(paymentBillId);
			logger.info("Success to execute check unfinished payment job, paymentBillId={}",paymentBillId);
		} catch (Exception e) {
			logger.info("Failed to execute check unfinished payment job, paymentBillId="+paymentBillId,e);
		}
		
		
		
	}

}
