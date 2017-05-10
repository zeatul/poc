package com.hawk.ecom.svp.job;



import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hawk.ecom.pub.job.TaskPool;
import com.hawk.ecom.svp.service.BsiOrderDetailService;
import com.hawk.framework.utility.tools.DateTools;

/**
 * 定时执行，扫描数据库里处于激活过程中的代金券, 发起子作业来检查对应的订单明细状态，来修改自己的状态
 * @author Administrator
 *
 */
@Service
public class BsiCashCouponMainJob {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BsiOrderDetailService bsiOrderDetailService;
	
	@Autowired
	private TaskPool taskPool;
	
	@Scheduled(initialDelay=5000,fixedDelay=1000*60*5)  
//	@Scheduled(cron="*/5 * * * * ?") 
    public void execute(){  
        logger.info("Start BsiCashCouponMainJob at " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN));
       1
        
        logger.info("Finish BsiCashCouponMainJob at " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN));
    }  
}
