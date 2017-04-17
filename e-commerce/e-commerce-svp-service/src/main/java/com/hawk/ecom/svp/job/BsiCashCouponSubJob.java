package com.hawk.ecom.svp.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.svp.constant.ConstBsiTaskStatus;
import com.hawk.ecom.svp.constant.ConstCouponParameter;
import com.hawk.ecom.svp.persist.domain.BsiCashCouponDomain;
import com.hawk.ecom.svp.persist.domain.BsiOrderDetailDomain;
import com.hawk.ecom.svp.service.BsiCashCouponService;
import com.hawk.ecom.svp.service.BsiOrderDetailService;
import com.hawk.framework.pub.spring.FrameworkContext;

/**
 * 处理代金券状态
 * @author Administrator
 *
 */
public class BsiCashCouponSubJob implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(BsiCashCouponSubJob.class);
	
	


	public BsiCashCouponSubJob(String bsiCashCouponCode) {
		this.bsiCashCouponCode = bsiCashCouponCode;
	}


	private final static BsiCashCouponService bsiCashCouponService = FrameworkContext.getBean(BsiCashCouponService.class);
	private final static BsiOrderDetailService bsiOrderDetailService = FrameworkContext.getBean(BsiOrderDetailService.class);


	private String bsiCashCouponCode ;
	
	
	
	
	
	@Override
	public void run() {
		
		logger.info("Start BsiCashCouponSubJob with bsiCashCouponCode = {}",bsiCashCouponCode);
		
		
		
		BsiCashCouponDomain bsiCashCouponDomain = null;
		bsiCashCouponDomain = bsiCashCouponService.loadByCode(bsiCashCouponCode);
		
		
		if (bsiCashCouponDomain == null){
			logger.error("Couldn't find the BsiCashCouponDomain withd code = {}",bsiCashCouponCode);
			return;
		}
		
		if (bsiCashCouponDomain.getBsiCashCouponStatus() != ConstCouponParameter.CouopnStatus.ACTIVVATING)
			return ;
		
		/**
		 * 查询小宝订单激活结果
		 */
		BsiOrderDetailDomain bsiOrderDetailDomain = bsiOrderDetailService.loadByCouponCode(bsiCashCouponCode); 
		
		/**
		 * 激活成功
		 */
		if (bsiOrderDetailDomain.getBsiTaskStatus() == ConstBsiTaskStatus.COMPLETE_SUCCESS){
			bsiCashCouponDomain.setBsiCashCouponStatus(ConstCouponParameter.CouopnStatus.ACTIVATE_SUCCESS);
			bsiCashCouponDomain.setUpdateDate(new Date());
			bsiCashCouponService.update(bsiCashCouponDomain);
		}
		
		/**
		 * 激活失败
		 */
		if (bsiOrderDetailDomain.getBsiTaskStatus() == ConstBsiTaskStatus.COMPLETE_FAILED){
			bsiCashCouponDomain.setBsiCashCouponStatus(ConstCouponParameter.CouopnStatus.ACTIVATE_FAILED);
			bsiCashCouponDomain.setBsiCashCouponActivateError(bsiOrderDetailDomain.getLastExecErrMsg());
			bsiCashCouponDomain.setUpdateDate(new Date());
			bsiCashCouponService.update(bsiCashCouponDomain);
		}
		
		logger.info("Finish BsiCashCouponSubJob with bsiCashCouponCode = {}",bsiCashCouponCode);
		
	}

}
