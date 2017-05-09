package com.hawk.ecom.svp.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 扫描 订单表 ，寻找那些已付款 ，但是没有完成的虚拟订单,发起作业来处理
 * @author Administrator
 *
 */
@Service
public class SvpOrderMainJob {

	@Scheduled(initialDelay=15000,fixedRate=5000)
	public void execute(){
		System.out.println("SvpOrderMainJob "+System.currentTimeMillis()); 
	}
}
