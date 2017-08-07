package com.hawk.ecom.trans.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.exception.OrderDetailNotFoundRuntimeException;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.domain.OrderDetailDomain;
import com.hawk.ecom.trans.persist.mapper.OrderDetailMapper;
import com.hawk.ecom.trans.persist.mapperex.OrderDetailExMapper;
import com.hawk.framework.utility.tools.DateTools;

@Service
public class OrderDetailService {

	@Autowired
	private OrderDetailExMapper orderDetailExMapper;

	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@Autowired
	private OrderDetailDeliveryDataService orderDetailDeliveryDataService;

	/**
	 * 
	 * @return 超过10分钟未处理的，交付已经全部完成的订单明细
	 */
	public List<Integer> queryUncheckedSuccessOrderDetail() {
		final Integer orderDetailStatus = ConstOrder.OrderDetailStatus.PROCESSING;
		final List<Integer> deliveryStatusList = Arrays.asList(ConstOrder.DeliveryStatus.FAILURE, ConstOrder.DeliveryStatus.CANCELED,
				ConstOrder.DeliveryStatus.PROCESSING, ConstOrder.DeliveryStatus.UN_EXECUTE);
		final Integer limit = 3000;
		Date threshold = DateTools.addMinutes(new Date(), -10);
		return orderDetailExMapper.queryUncheckedSuccessOrderDetail(orderDetailStatus, deliveryStatusList, threshold, limit);
	}

	/**
	 * 
	 * @return 超过10分钟未处理的，交付存在失败的订单明细
	 */
	public List<Integer> queryUncheckedFailedOrderDetail() {
		final Integer orderDetailStatus = ConstOrder.OrderDetailStatus.PROCESSING;
		final List<Integer> deliveryStatusList = Arrays.asList(ConstOrder.DeliveryStatus.FAILURE);
		final Integer limit = 3000;
		Date threshold = DateTools.addMinutes(new Date(), -10);
		return orderDetailExMapper.queryUncheckedFailedOrderDetail(orderDetailStatus, deliveryStatusList, threshold, limit);
	}

	public OrderDetailDomain load(Integer orderDetailId) {
		OrderDetailDomain orderDetailDomain = null;
		if (orderDetailId != null) {
			orderDetailDomain = orderDetailMapper.load(orderDetailId);
		}
		if (orderDetailDomain == null) {
			throw new OrderDetailNotFoundRuntimeException();
		}
		return orderDetailDomain;
	}

	/**
	 * 检查对应的交付清单处理状态，如果有一个失败，则状态改未失败，否则跑异常
	 * 
	 * @param orderDetailId
	 */
	public void checkFailedOrderDetail(Integer orderDetailId) {

		OrderDetailDomain orderDetailDomain = load(orderDetailId);

		if (!(orderDetailDomain.getOrderDetailStatus() == ConstOrder.OrderDetailStatus.PROCESSING)) {
			throw new RuntimeException("订单明细状态不是处理中,orderDetailId=" + orderDetailId);
		}

		/**
		 * 查交付
		 */
		List<OrderDetailDeliveryDataDomain> orderDetailDeliveryDataDomainList = orderDetailDeliveryDataService.queryByOrderDetailId(orderDetailId);
		if (orderDetailDeliveryDataDomainList.size() == 0) {
			throw new RuntimeException("未查到交付信息，orderDetailId=" + orderDetailId);
		}

		boolean hasFailure = false;
		for (OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain : orderDetailDeliveryDataDomainList) {
			if (orderDetailDeliveryDataDomain.getDeliveryStatus() == ConstOrder.DeliveryStatus.FAILURE) {
				hasFailure = true;
				break;
			}
		}

		if (!hasFailure) {
			throw new RuntimeException("订单明细不存在失败的交付,orderDetailId=" + orderDetailId);
		}

		/**
		 * 更改订单明细状态
		 */
		OrderDetailDomain updateDomain = new OrderDetailDomain();
		updateDomain.setId(orderDetailDomain.getId());
		updateDomain.setUpdateDate(new Date());
		updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
		updateDomain.setOrderDetailStatus(ConstOrder.OrderDetailStatus.FAILURE);
	}

	/**
	 * 检查对应的交付清单处理状态，如果交付全部完成，则状态改为完成，如果有一个不是完成，则抛异常
	 * 
	 * @param orderDetailId
	 */
	public void checkSuccessOrderDetail(Integer orderDetailId) {

		OrderDetailDomain orderDetailDomain = load(orderDetailId);

		if (!(orderDetailDomain.getOrderDetailStatus() == ConstOrder.OrderDetailStatus.PROCESSING)) {
			throw new RuntimeException("订单明细状态不是处理中,orderDetailId=" + orderDetailId);
		}

		/**
		 * 查交付
		 */
		List<OrderDetailDeliveryDataDomain> orderDetailDeliveryDataDomainList = orderDetailDeliveryDataService.queryByOrderDetailId(orderDetailId);
		if (orderDetailDeliveryDataDomainList.size() == 0) {
			throw new RuntimeException("未查到交付信息，orderDetailId=" + orderDetailId);
		}

		boolean allSuccess = true;
		for (OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain : orderDetailDeliveryDataDomainList) {
			if (orderDetailDeliveryDataDomain.getDeliveryStatus() != ConstOrder.DeliveryStatus.SUCCESS) {
				allSuccess = false;
				break;
			}
		}

		if (!allSuccess) {
			throw new RuntimeException("订单明细尚有未成功的交付数据,orderDetailId=" + orderDetailId);
		}

		/**
		 * 更改订单明细状态
		 */
		OrderDetailDomain updateDomain = new OrderDetailDomain();
		updateDomain.setId(orderDetailDomain.getId());
		updateDomain.setUpdateDate(new Date());
		updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
		updateDomain.setOrderDetailStatus(ConstOrder.OrderDetailStatus.SUCCESS);
	}
}
