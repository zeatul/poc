package com.hawk.ecom.svp.job;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.svp.constant.ConstBsiTaskStatus;
import com.hawk.ecom.svp.constant.ConstOrderStatus;
import com.hawk.ecom.svp.persist.domain.BsiOrderDetailDomain;
import com.hawk.ecom.svp.persist.domain.OrderDomain;
import com.hawk.ecom.svp.service.BsiOrderDetailService;
import com.hawk.ecom.svp.service.OrderService;
import com.hawk.framework.pub.spring.FrameworkContext;

/**
 * 处理订单状态
 * @author Administrator
 *
 */
public class SvpBsiOrderSubJob implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(SvpBsiOrderSubJob.class);
	
	private String orderCode;
	
	private final static BsiOrderDetailService bsiOrderDetailService = FrameworkContext.getBean(BsiOrderDetailService.class);
	private final static OrderService orderService = FrameworkContext.getBean(OrderService.class);

	public SvpBsiOrderSubJob(String  orderCode) {
		this.orderCode = orderCode;
	}

	@Override
	public void run() {
		logger.info("Start SvpBsiOrderSubJob with orderId = {}",orderCode);
		BsiOrderDetailDomain bsiOrderDetailDomain =  bsiOrderDetailService.loadByOrderCode(orderCode);
		if (bsiOrderDetailDomain == null){
			logger.error("Couldn't find any BsiOrderDetailDomain with orderCode = {}",orderCode);
			return ;
		}
		
		OrderDomain orderDomain = orderService.loadByOrderCode(orderCode);
		
		if (orderDomain == null){
			logger.error("Couldn't find the order with orderCode = {}",orderCode);
			return ;
		}
		
		
		
		if (bsiOrderDetailDomain.getBsiTaskStatus() == ConstBsiTaskStatus.COMPLETE_SUCCESS){
			orderDomain.setOrderStatus(ConstOrderStatus.SUCCESS_COMPLETED);
			orderDomain.setOrderErrorCause(null);
			orderDomain.setUpdateDate(new Date());
			orderService.update(orderDomain);
		}
		
		if (bsiOrderDetailDomain.getBsiTaskStatus() == ConstBsiTaskStatus.COMPLETE_FAILED){
			orderDomain.setOrderStatus(ConstOrderStatus.FAILED_COMPLETED);
			orderDomain.setOrderErrorCause(bsiOrderDetailDomain.getLastExecErrMsg());
			orderDomain.setUpdateDate(new Date());
			orderService.update(orderDomain);
		}
		
		
		logger.info("Finish SvpBsiOrderSubJob with orderCode = {}",orderCode);
	}

}
