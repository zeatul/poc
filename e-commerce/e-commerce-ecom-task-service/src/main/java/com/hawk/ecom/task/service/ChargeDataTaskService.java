package com.hawk.ecom.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.outer.service.ChargeDataService.ConstChargeNotifyStatus;
import com.hawk.ecom.pub.constant.ConstOrder;
import com.hawk.ecom.task.persist.mapperex.JobExMapper;
import com.hawk.ecom.task.request.ChargeDataNotifyRequest;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.utility.tools.StringTools;

import ch.qos.logback.classic.Logger;

@Service
public class ChargeDataTaskService {

	@Autowired
	private JobExMapper jobExMapper;

	public void notify(ChargeDataNotifyRequest chargeDataNotifyRequest) {

		String taskCode = chargeDataNotifyRequest.getCstmOrderNo();
		String outerOrderCode = chargeDataNotifyRequest.getOrderNo();
		String outerOrderStatus = chargeDataNotifyRequest.getStatus();
		String outerOrderMsg = chargeDataNotifyRequest.getMsg();
		Integer deliveryStatus = null;

		if (StringTools.isNullOrEmpty(taskCode)) {
			throw new RuntimeException("notification cstmOrderNo is null");
		}

		MybatisParam params = new MybatisParam().put("taskCode", taskCode)//
				.put("outerOrderCode", outerOrderCode)//
				.put("outerOrderStatus", outerOrderStatus)//
				.put("outerOrderMsg", outerOrderMsg);

		if (ConstChargeNotifyStatus.SUCCESS.equals(outerOrderStatus)) {
			deliveryStatus = ConstOrder.DeliveryStatus.SUCCESS;
		} else if (ConstChargeNotifyStatus.FAILURE.equals(outerOrderStatus)) {
			deliveryStatus = ConstOrder.DeliveryStatus.FAILURE;
		} else if (ConstChargeNotifyStatus.PROCESSING.equals(outerOrderStatus)) {
			deliveryStatus = ConstOrder.DeliveryStatus.PROCESSING;
		}
		
		params.put("deliveryStatus",deliveryStatus);
		
		/**
		 * 更新交付状态
		 */
		jobExMapper.updateOrderDeliveryDataForNotification(params);
		
		/**
		 * 更新订单明细状态
		 */
		
		/**
		 * 更新订单状态
		 */
		1
	}
}
