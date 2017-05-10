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
import com.hawk.framework.utility.tools.JsonTools;

/**
 * 处理代金券状态
 * @author Administrator
 *
 */
public class BsiCashCouponSubJob implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(BsiCashCouponSubJob.class);
	
	


	public BsiCashCouponSubJob(BsiOrderDetailDomain bsiOrderDetailDomain) {
		this.bsiOrderDetailDomain = bsiOrderDetailDomain;
	}


	private final static BsiCashCouponService bsiCashCouponService = FrameworkContext.getBean(BsiCashCouponService.class);
	private final static BsiOrderDetailService bsiOrderDetailService = FrameworkContext.getBean(BsiOrderDetailService.class);


	private BsiOrderDetailDomain bsiOrderDetailDomain ;
	
	
	
	
	
	@Override
	public void run() {
		
		logger.info("Start BsiCashCouponSubJob with bsiOrderDetailDomain = {}",JsonTools.toJsonString(bsiOrderDetailDomain));
		
		if (!(bsiOrderDetailDomain.getBsiTaskStatus() >= ConstBsiTaskStatus.COMPLETE_FAILED)) {
			logger.error("订单未完成");
		}
		
		String bsiCashCouponCode = bsiOrderDetailDomain.getBsiCashCouponCode();
		BsiCashCouponDomain bsiCashCouponDomain = null;
		bsiCashCouponDomain = bsiCashCouponService.loadByCode(bsiOrderDetailDomain.getBsiCashCouponCode());
		
		
		if (bsiCashCouponDomain == null){
			logger.error("Couldn't find the BsiCashCouponDomain withd code = {}",bsiCashCouponCode);
			return;
		}
		
		if (bsiCashCouponDomain.getBsiCashCouponStatus() != ConstCouponParameter.CouopnStatus.ACTIVVATING){
			logger.error("代金券状态不是激活中！");
			return ;
		}
		
		
		
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
