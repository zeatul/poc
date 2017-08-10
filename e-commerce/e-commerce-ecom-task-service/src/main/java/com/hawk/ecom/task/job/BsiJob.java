package com.hawk.ecom.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.task.service.BsiTaskService;
import com.hawk.ecom.task.service.ChargeDataTaskService;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.StringTools;

public class BsiJob implements Runnable{
	
	private String taskCode;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private BsiTaskService service = FrameworkContext.getBean(BsiTaskService.class);
	
	private CacheService cacheService = FrameworkContext.getBean(CacheService.class);
	
	
	public BsiJob(String taskCode){
		this.taskCode = taskCode;
	}
	
	private String buildChargeTaskKey(){
		return StringTools.concatWithSymbol("_", getClass().getName(),taskCode);
	}

	@Override
	public void run() {
		logger.info("Start to execute bsi job, taskCode={}",taskCode);
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
			service.buyBsi(taskCode);
			logger.info("Success to execute bsi job, taskCode={}",taskCode);
		} catch (Exception e) {
			logger.error("Failed to execute bsi job, taskCode="+taskCode,e);
		}
		
	}

}
