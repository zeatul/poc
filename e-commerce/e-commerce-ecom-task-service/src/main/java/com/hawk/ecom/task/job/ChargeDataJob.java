package com.hawk.ecom.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.task.service.ChargeDataTaskService;
import com.hawk.framework.pub.spring.FrameworkContext;

public class ChargeDataJob implements Runnable{
	
	private String taskCode;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ChargeDataTaskService service = FrameworkContext.getBean(ChargeDataTaskService.class);
	
	
	
	public ChargeDataJob(String taskCode){
		this.taskCode = taskCode;
	}

	@Override
	public void run() {
		logger.info("Start to execute charge data job, taskCode={}",taskCode);
		service.chargeData(taskCode);
		logger.info("Success to execute charge data job, taskCode={}",taskCode);
	}

}
