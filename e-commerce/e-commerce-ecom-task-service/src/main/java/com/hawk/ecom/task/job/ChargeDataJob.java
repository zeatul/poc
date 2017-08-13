package com.hawk.ecom.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.task.service.ChargeDataTaskService;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.StringTools;

/**
 * 处理已经支付，还未充值的订单
 * @author zhp
 *
 */
public class ChargeDataJob implements Runnable{
	
	private String taskCode;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ChargeDataTaskService service = FrameworkContext.getBean(ChargeDataTaskService.class);
	
	private CacheService cacheService = FrameworkContext.getBean(CacheService.class);
	
	
	public ChargeDataJob(String taskCode){
		this.taskCode = taskCode;
	}
	
	private String buildChargeTaskKey(){
		return StringTools.concatWithSymbol("_", getClass().getName(),taskCode);
	}

	@Override
	public void run() {
		logger.info("Start to execute charge data job, taskCode={}",taskCode);
		AuthThreadLocal.setUserCode("system");
		/**
		 * 取缓存锁
		 */
		String key = buildChargeTaskKey();
		if (!cacheService.setnx(key, taskCode, 600-5)){
			logger.error("Failed to get the redis lock,key={}",key);
			return ;
		}
		try {
			service.chargeData(taskCode);
			logger.info("Success to execute charge data job, taskCode={}",taskCode);
		} catch (Exception e) {
			logger.error("Failed to execute charge data job, taskCode="+taskCode,e);
		}
		
	}

}
