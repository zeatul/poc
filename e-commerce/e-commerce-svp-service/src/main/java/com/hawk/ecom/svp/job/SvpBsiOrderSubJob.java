package com.hawk.ecom.svp.job;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.svp.constant.ConstBsiTaskStatus;
import com.hawk.ecom.svp.constant.ConstOrderStatus;
import com.hawk.ecom.svp.persist.domain.BsiOrderDetailDomain;
import com.hawk.ecom.svp.persist.domain.OrderDomain;
import com.hawk.ecom.svp.service.BsiOrderDetailService;
import com.hawk.ecom.svp.service.OrderService;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.CollectionTools;

/**
 * 处理订单状态
 * @author Administrator
 *
 */
public class SvpBsiOrderSubJob implements Runnable{
	
	private final static Logger logger = LoggerFactory.getLogger(SvpBsiOrderSubJob.class);
	
	private Long orderId;
	
	private final static BsiOrderDetailService bsiOrderDetailService = FrameworkContext.getBean(BsiOrderDetailService.class);
	private final static OrderService orderService = FrameworkContext.getBean(OrderService.class);

	public SvpBsiOrderSubJob(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public void run() {
		logger.info("Start SvpBsiOrderSubJob with orderId = {}",orderId);
		List<BsiOrderDetailDomain> bsiOrderDetailDomainList =  bsiOrderDetailService.loadByOrderId(orderId);
		if (CollectionTools.isNullOrEmpty(bsiOrderDetailDomainList)){
			logger.error("Couldn't find any BsiOrderDetailDomain with orderId = {}",orderId);
			return ;
		}
		
		OrderDomain orderDomain = orderService.loadById(orderId);
		
		if (orderDomain == null){
			logger.error("Couldn't find the order with id = {}",orderId);
			return ;
		}
		
		if (bsiOrderDetailDomainList.size()>1){
			logger.error("Only support the  order which has only one detail , orderId = {}",orderId);
			return ;
		}
		
		BsiOrderDetailDomain bsiOrderDetailDomain = bsiOrderDetailDomainList.get(0);
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
		
		
		logger.info("Finish SvpBsiOrderSubJob with orderId = {}",orderId);
	}

}
