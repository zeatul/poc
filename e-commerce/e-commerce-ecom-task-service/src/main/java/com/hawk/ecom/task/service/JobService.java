package com.hawk.ecom.task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class JobService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 关闭超过支付时间仍然没有支付记录的订单
	 */
	@Scheduled(initialDelay=5000,fixedDelay=1000*60*5)  
	public void closeOrder(){
		logger.info("Start to close expired orders which where not paied");
		
		logger.info("Success to close expired orders which where not paied");
	}

}
