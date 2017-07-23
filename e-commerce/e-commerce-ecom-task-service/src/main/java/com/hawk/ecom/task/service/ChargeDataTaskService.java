package com.hawk.ecom.task.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.outer.service.ChargeDataService;
import com.hawk.ecom.outer.service.ChargeDataService.ConstChargeNotifyStatus;
import com.hawk.ecom.outer.service.ChargeResult;
import com.hawk.ecom.task.request.ChargeDataNotifyRequest;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.domainex.OrderDetailDeliveryDataExDomain;
import com.hawk.ecom.trans.persist.mapperex.OrderDetailDeliveryDataExMapper;
import com.hawk.ecom.trans.persist.mapperex.OrderDetailExMapper;
import com.hawk.ecom.trans.service.OrderDetailDeliveryDataService;
import com.hawk.ecom.trans.service.OrderService;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;



@Service
public class ChargeDataTaskService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderDetailDeliveryDataExMapper orderDetailDeliveryDataExMapper;
	
	@Autowired
	private OrderDetailExMapper orderDetailExMapper;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private OrderDetailDeliveryDataService orderDetailDeliveryDataService;
	
	@Autowired
	private ChargeDataService chargeDataService;
	
	private String buildChargeTaskKey(String taskCode){
		return StringTools.concatWithSymbol("_", ChargeDataTaskService.class.getSimpleName(),taskCode);
	}
	
	public void chargeData(String taskCode) {
		if (StringTools.isNullOrEmpty(taskCode)){
			logger.error("taskCode is empty");
			return ;
		}
		/**
		 * 取缓存锁
		 */
		if (!cacheService.setnx(buildChargeTaskKey(taskCode), taskCode, 600-5)){
			logger.error("Failed to get the redis lock,taskCode={}",taskCode);
			return ;
		}
		OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = orderDetailDeliveryDataService.loadByTaskCode(taskCode);
		String mobileNumber = orderDetailDeliveryDataDomain.getBenefMobileNumber();
		String productCode = orderDetailDeliveryDataDomain.getOuterProductId();
		if (orderDetailDeliveryDataDomain.getExecTimes() != 0){
			logger.error("The task({}) has been executed before. The charge data task only allow execute once!!!",taskCode);
			return ;
		}
		try {
			OrderDetailDeliveryDataDomain updateDomain = new OrderDetailDeliveryDataDomain();
			updateDomain.setId(orderDetailDeliveryDataDomain.getId());
			updateDomain.setExecTimes(1);
			updateDomain.setLastExecBeginTime(new Date());
			ChargeResult chargeResult = chargeDataService.charge(mobileNumber, productCode, taskCode);
			updateDomain.setTaskStatus(chargeResult.isSuccess()?ConstOrder.TaskStatus.SUCCESS_TASK:ConstOrder.TaskStatus.FAILURE_TASK);
			updateDomain.setLastExecComputer(lastExecComputer);
			updateDomain.setLastExecProcessId(lastExecProcessId);
			updateDomain.setLastExecRtnCode(chargeResult.getErrCode());
			updateDomain.setLastExecRtnMsg(chargeResult.getErrMsg());

			
			
			updateDomain.setLastExecEndTime(new Date());
		} catch (Exception e) {
			logger.error("chargeDataService.charge() failed",e); 
		}
	}

	public void notify(ChargeDataNotifyRequest chargeDataNotifyRequest) {
		
		logger.info("recieve chargeData notification , param={}",JsonTools.toJsonString(chargeDataNotifyRequest));

		String taskCode = chargeDataNotifyRequest.getCstmOrderNo();
		String outerOrderCode = chargeDataNotifyRequest.getOrderNo();
		String outerOrderStatus = chargeDataNotifyRequest.getStatus();
		String outerOrderMsg = chargeDataNotifyRequest.getMsg();
		Integer deliveryStatus = null;
		Integer orderDetailStatus = null;

		if (StringTools.isNullOrEmpty(taskCode)) {
			throw new RuntimeException("notification cstmOrderNo(taskCode) is null");
		}

		

		if (ConstChargeNotifyStatus.SUCCESS.equals(outerOrderStatus)) {
			deliveryStatus = ConstOrder.DeliveryStatus.SUCCESS;
			orderDetailStatus = ConstOrder.OrderDetailStatus.SUCCESS;
		} else if (ConstChargeNotifyStatus.FAILURE.equals(outerOrderStatus)) {
			deliveryStatus = ConstOrder.DeliveryStatus.FAILURE;
			orderDetailStatus = ConstOrder.OrderDetailStatus.FAILURE;
		} else if (ConstChargeNotifyStatus.PROCESSING.equals(outerOrderStatus)) {
			deliveryStatus = ConstOrder.DeliveryStatus.PROCESSING;
			orderDetailStatus = ConstOrder.OrderDetailStatus.PROCESSING;
		}else{
			throw new RuntimeException("unknown outerOrderStatus="+outerOrderStatus);
		}
		
		
		
		/**
		 * 更新交付状态
		 */
		MybatisParam params = new MybatisParam().put("taskCode", taskCode)//
				.put("outerOrderCode", outerOrderCode)//
				.put("outerOrderStatus", outerOrderStatus)//
				.put("outerOrderMsg", outerOrderMsg);
		params.put("deliveryStatus",deliveryStatus);
		if (orderDetailDeliveryDataExMapper.updateOrderDeliveryDataForNotification(params) != 1){
			throw new RuntimeException("Failed to execute updateOrderDeliveryDataForNotification,taskCode="+taskCode);
		}
		
		logger.info("Success to execute  updateOrderDeliveryDataForNotification ,taskCode={}",taskCode);
		
		/**
		 * 更新订单明细状态
		 */
		OrderDetailDeliveryDataExDomain orderDetailDeliveryDataExDomain = orderDetailDeliveryDataExMapper.loadOrderDeliveryDataByTaskCode(taskCode);
		if (orderDetailDeliveryDataExDomain == null){
			throw new RuntimeException("Failed to find orderDetailDeliveryDataExDomain,taskCode="+taskCode);
		}
		Integer orderDetailId = orderDetailDeliveryDataExDomain.getOrderDetailId();
		Integer orderId = orderDetailDeliveryDataExDomain.getOrderId();
		if (orderDetailExMapper.updateOrderDetailStatus(orderDetailId, orderDetailStatus) != 1){
			throw new RuntimeException("Failed to execute updateOrderDetailStatus,orderDetailId="+orderDetailId);
		}
		
		logger.info("Success to execute  updateOrderDetailStatus ,taskCode={},orderDetailId={},orderId={}",taskCode,orderDetailId,orderId);
		
		/**
		 * 更新订单状态,订单状态变更表
		 * 如果订单明细是失败，则订单改成失败
		 * 如果订单明细是成功，则检查订单的全部明细，是否都成功，如果是，则改为成功。
		 */
		 Integer orderStatus = null;
		 if (ConstChargeNotifyStatus.FAILURE.equals(outerOrderStatus)){
			 orderService.updateOrderStatus(orderId,ConstOrder.OrderStatus.FAILURE,"充流量失败","充流量失败");
			 orderStatus = ConstOrder.OrderStatus.FAILURE;
		 }else if (ConstChargeNotifyStatus.SUCCESS.equals(outerOrderStatus)) {
			 if (orderService.isAllOrderDetailSuccess(orderId)){
				 orderService.updateOrderStatus(orderId,ConstOrder.OrderStatus.SUCCESS,"充流量成功","充流量成功");
				 orderStatus = ConstOrder.OrderStatus.SUCCESS;
			 }
		 }
		 
		 logger.info("Success to execute  updateOrderDetailStatus ,taskCode={},orderId={},orderStatus={}",taskCode,orderDetailId,orderId,orderStatus);
		
	}
}
