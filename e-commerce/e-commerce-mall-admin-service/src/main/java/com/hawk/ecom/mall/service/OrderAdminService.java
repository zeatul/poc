package com.hawk.ecom.mall.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.mall.request.CloseUnpaidOrderParam;
import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.pay.constant.ConstPay;
import com.hawk.ecom.pay.persist.domain.PaymentBillDomain;
import com.hawk.ecom.pay.service.PaymentService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.persist.domain.OrderDomain;
import com.hawk.ecom.trans.service.OrderService;
import com.hawk.framework.dic.validation.annotation.Valid;

@Service
public class OrderAdminService {
	@Autowired
	private OrderService orderService;

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MallAuthService authService;

	/**
	 * 关闭过期的未支付订单 订单状态必需是未支付 订单过期时间必需在当前时间之前 订单如果没有对应的支付单，则 关闭
	 * ,如果有对应的支付单，则查询该支付单的支付情况，如果未支付，则关闭，如果已支付，则停止处理
	 * 
	 * @param orderId
	 * @param allowPaidNotOvertime 允许支付未超时的订单被关闭
	 * @throws Exception
	 */
	public void closeUnpaidOrder(Integer orderId, boolean allowPaidNotOvertime) throws Exception {
		OrderDomain orderDomain = orderService.loadOrder(orderId);
		closeUnpaidOrder(orderDomain,allowPaidNotOvertime);
		
	}
	
	private void closeUnpaidOrder(OrderDomain orderDomain , boolean allowPaidNotOvertime) throws Exception{
		
		Integer orderId = orderDomain.getId();
		
		if (orderDomain.getOrderStatus() != ConstOrder.OrderStatus.UNPAIED) {
			throw new RuntimeException("The order status is not unpaid,orderId=" + orderId);
		}
		if (!allowPaidNotOvertime) {
			if (orderDomain.getOrderPayExpireTime().after(new Date())) {
				throw new RuntimeException("The order pay time isn't overtime,orderId=" + orderId);
			}
		}

		/**
		 * 检查支付单的执行情况
		 */
		PaymentBillDomain paymentBillDomain = paymentService.queryPaymentBill(orderDomain.getOrderCode(), "SVP");
		if (paymentBillDomain != null) {
			if (paymentBillDomain.getPaymentBillStatus() == ConstPay.PaymentBillStatus.SUCCESS) {
				throw new RuntimeException("the order is paid successfully,orderId=" + orderId);
			}
			if (paymentBillDomain.getPaymentBillStatus() == ConstPay.PaymentBillStatus.WAITING_PAY) {

				if (paymentService.hasPaidSuccessfully(paymentBillDomain))
					throw new RuntimeException("the order is paid successfully,orderId=" + orderId);
			}
		}

		/**
		 * 未成功支付订单，已经超期，关闭，并且退回库存量
		 */
		orderService.closeUnpaidOrder(orderDomain,AuthThreadLocal.getUserCode());
	}

	@Valid
	public void closeUnpaidOrder(@Valid CloseUnpaidOrderParam closeUnpaiOrderParam) throws Exception {

		/**
		 * 校验权限，管理员，自己商店的订单
		 */
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		
		OrderDomain orderDomain = orderService.loadOrder(closeUnpaiOrderParam.getOrderId());
		
		if (!orderDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())){
			throw new RuntimeException("用户不能管理非自己所属商店的订单");
		}
		
		closeUnpaidOrder(orderDomain,true);
	}
}
