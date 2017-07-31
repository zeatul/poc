package com.hawk.ecom.task.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hawk.ecom.mall.service.OrderAdminService;
import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.pub.job.TaskPool;
import com.hawk.ecom.task.job.ChargeDataJob;
import com.hawk.ecom.task.job.CloseUnpaidOvertimeOrderJob;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.persist.domainex.OrderDetailDeliveryDataExDomain;
import com.hawk.ecom.trans.persist.mapperex.OrderDetailDeliveryDataExMapper;
import com.hawk.ecom.trans.service.OrderService;

@Service
public class JobService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderDetailDeliveryDataExMapper orderDetailDeliveryDataExMapper;
	
	@Autowired
	private TaskPool taskPool;
	
	@Autowired
	private OrderAdminService orderAdminService;
	
	@Autowired
	private OrderService orderService;
	
	

	/**
	 * 关闭超过支付时间仍然没有支付记录的订单
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 1000 * 60 * 2)
	public void closeUnpaiedOvertimeOrder() {
		logger.info("++++Start to execute closeUnpaiedOvertimeOrder job");
		List<Integer> orderIdList = orderService.queryUnpaidOvertimeOrder();
		logger.info("Found {} unpaid expired order",orderIdList.size());
		for (Integer orderId : orderIdList){
			CloseUnpaidOvertimeOrderJob job = new CloseUnpaidOvertimeOrderJob(orderId);
			taskPool.execute(job);
		}
		logger.info("++++Success to execute closeUnpaiedOvertimeOrder job");
	}

	/**
	 * 将已经支付完成，但是充值次数为0的交付单，拿出来充流量
	 */
	@Scheduled(initialDelay = 20000, fixedDelay = 1000 * 60 * 5)
	public void batchCharge() {
		logger.info("-----Start to execute batchCharge");
		List<OrderDetailDeliveryDataExDomain> jobList = orderDetailDeliveryDataExMapper.loadOrderDeliveryDataForCharge(ConstProduct.DeliveryType.CHARGE_FLOW_DATA, ConstOrder.TaskStatus.UN_EXECUTE,
				ConstOrder.OrderStatus.PAIED);
		
		logger.info("Found {} charge jobs",jobList.size());
		
		for (OrderDetailDeliveryDataExDomain orderDetailDeliveryDataExDomain : jobList){
			ChargeDataJob chargeDataJob = new ChargeDataJob(orderDetailDeliveryDataExDomain.getTaskCode());
			taskPool.execute(chargeDataJob);
		}

		logger.info("Success to execute batchCharge");
	}

}
