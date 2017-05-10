package com.hawk.ecom.svp.job;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.svp.constant.ConstChargeStatus;
import com.hawk.ecom.svp.constant.ConstOrderStatus;
import com.hawk.ecom.svp.persist.domain.MobileDataOrderDetailDomain;
import com.hawk.ecom.svp.persist.domain.OrderDomain;
import com.hawk.ecom.svp.service.MobileDataOrderDetailService;
import com.hawk.ecom.svp.service.OrderService;
import com.hawk.framework.pub.spring.FrameworkContext;

/**
 * 处理充值订单状态
 * @author Administrator
 *
 */
public class MobileDataChargeOrderSubJob implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(MobileDataChargeOrderSubJob.class);
	
	private final static MobileDataOrderDetailService mobileDataOrderDetailService = FrameworkContext.getBean(MobileDataOrderDetailService.class);
	
	private final static OrderService orderService = FrameworkContext.getBean(OrderService.class);
	
	private String orderCode;
	
	public MobileDataChargeOrderSubJob(String orderCode){
		this.orderCode = orderCode;
	}
	

	@Override
	public void run() {
		logger.info("Start MobileDataChargeOrderSubJob with orderCode = {}",orderCode);
		MobileDataOrderDetailDomain mobileDataOrderDetailDomain =  mobileDataOrderDetailService.loadByOrderCode(orderCode);
	
		if (mobileDataOrderDetailDomain == null){
			logger.error("Couldn't find any MobileDataOrderDetailDomain with orderCode = {}",orderCode);
			return ;
		}
		
		OrderDomain orderDomain = orderService.loadByOrderCode(orderCode);
		
		if (orderDomain == null){
			logger.error("Couldn't find the order with orderCode = {}",orderCode);
			return ;
		}
		
		
		int chargeStatus = mobileDataOrderDetailDomain.getChargeStatus();
		if (chargeStatus == ConstChargeStatus.COMPLETE_SUCCESS){
			orderDomain.setOrderStatus(ConstOrderStatus.SUCCESS_COMPLETED);
			orderDomain.setOrderErrorCause(null);
			orderDomain.setUpdateDate(new Date());
			orderService.update(orderDomain);
		}
		
		if (chargeStatus == ConstChargeStatus.COMPLETE_FAILED){
			orderDomain.setOrderStatus(ConstOrderStatus.FAILED_COMPLETED);
			orderDomain.setOrderErrorCause(mobileDataOrderDetailDomain.getLastExecErrMsg());
			orderDomain.setUpdateDate(new Date());
			orderService.update(orderDomain);
		}
		
		
		logger.info("Finish SvpBsiOrderSubJob with orderCode = {}",orderCode);
	}

}
