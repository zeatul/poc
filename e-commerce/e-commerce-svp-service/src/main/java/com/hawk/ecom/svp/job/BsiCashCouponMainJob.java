package com.hawk.ecom.svp.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时执行，扫描数据库里处于激活过程中的代金券。发起子作业来检查对应的订单明细状态，来修改自己的状态
 * @author Administrator
 *
 */
@Service
public class BsiCashCouponMainJob {
	
	public BsiCashCouponMainJob(){
		System.out.println("+++++++++++++++++++++++++BsiCashCouponMainJob++++++++++++++++++");
	}
	
	@Scheduled(initialDelay=5000,fixedDelay=20000)  
//	@Scheduled(cron="*/5 * * * * ?") 
    public void test(){  
        System.out.println("BsiCashCouponMainJob "+System.currentTimeMillis());  
    }  
}
