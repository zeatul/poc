package com.hawk.ecom.mall.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.mall.request.CloseUnpaidOrderParam;
import com.hawk.ecom.mall.request.ListOrderDetailDeliveryDataParam;
import com.hawk.ecom.mall.request.ListOrderDetailParam;
import com.hawk.ecom.mall.request.ListOrderParam;
import com.hawk.ecom.mall.request.LoadOrderDetailDeliveryDataParam;
import com.hawk.ecom.mall.request.LoadOrderDetailParam;
import com.hawk.ecom.mall.request.LoadOrderParam;
import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.pay.constant.ConstPay;
import com.hawk.ecom.pay.persist.domain.PaymentBillDomain;
import com.hawk.ecom.pay.service.PaymentService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.exception.OrderNotBelongToLoginUserRuntimeException;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.domain.OrderDetailDomain;
import com.hawk.ecom.trans.persist.domain.OrderDomain;
import com.hawk.ecom.trans.persist.mapper.OrderDetailDeliveryDataMapper;
import com.hawk.ecom.trans.persist.mapper.OrderDetailMapper;
import com.hawk.ecom.trans.persist.mapper.OrderMapper;
import com.hawk.ecom.trans.service.OrderDetailDeliveryDataService;
import com.hawk.ecom.trans.service.OrderDetailService;
import com.hawk.ecom.trans.service.OrderService;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;

@Service
public class OrderAdminService {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderDetailDeliveryDataService orderDetailDeliveryDataService;

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private MallAuthService authService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	@Autowired
	private OrderDetailDeliveryDataMapper orderDetailDeliveryDataMapper;
	
	

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

				if (paymentService.hasPaidSuccessfully(paymentBillDomain)==1){
					throw new RuntimeException("the order is paid successfully,orderId=" + orderId);
				} else if (paymentService.hasPaidSuccessfully(paymentBillDomain)==0){
					/**
					 * 取消支付
					 */
					paymentService.close(paymentBillDomain);
				}
			}
		}

		/**
		 * 未成功支付订单，已经超期，关闭，并且退回库存量
		 */
		orderService.closeUnpaidOrder(orderDomain);
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

	@Valid
	public PagingQueryResultWrap<OrderDomain> listOrder(@Valid @NotNull("函数入参") ListOrderParam listOrderParam){
		listOrderParam.setOrder("create_date desc");

		MybatisParam params = MybatisTools.page(new MybatisParam(), listOrderParam);
		params.put("orderStatus", listOrderParam.getOrderStatus());
		params.put("storeCode", AuthThreadLocal.getStoreCode());
		
		PagingQueryResultWrap<OrderDomain> wrap = new PagingQueryResultWrap<OrderDomain>();
		wrap.setDbCount(orderMapper.count(params));
		if (wrap.getDbCount() > 0){
			wrap.setRecords(orderMapper.loadDynamicPaging(params));
		}

		return wrap;
	}

	@Valid
	public OrderDomain loadOrder (@Valid @NotNull("函数入参") LoadOrderParam loadOrderParam){
		OrderDomain orderDomain = orderService.loadOrder(loadOrderParam.getOrderId());
		
		if (!orderDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())){
			throw new RuntimeException("订单不属于当前登陆用户所属商铺");
		}
		return orderDomain;
	}
	
	@Valid
	public PagingQueryResultWrap<OrderDetailDomain> listOrderDetail(@Valid @NotNull("函数入参") ListOrderDetailParam listOrderDetailParam) {

		listOrderDetailParam.setOrder("sku_id asc");

		MybatisParam params = MybatisTools.page(new MybatisParam(), listOrderDetailParam);
		params.put("orderId", listOrderDetailParam.getOrderId());
		params.put("storeCode", AuthThreadLocal.getStoreCode());

		PagingQueryResultWrap<OrderDetailDomain> wrap = new PagingQueryResultWrap<OrderDetailDomain>();
		wrap.setDbCount(orderDetailMapper.count(params));
		if (wrap.getDbCount() > 0) {
			wrap.setRecords(orderDetailMapper.loadDynamicPaging(params));
		}

		return wrap;
	}
	
	@Valid
	public OrderDetailDomain loadOrderDetail (@Valid @NotNull("函数入参") LoadOrderDetailParam loadOrderDetailParam){
		OrderDetailDomain orderDetailDomain = orderDetailService.load(loadOrderDetailParam.getOrderDetailId());
		
		if (!orderDetailDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())){
			throw new RuntimeException("订单明细不属于当前用户所属商铺");
		}
		
		return orderDetailDomain;
	}
	
	@Valid
	public PagingQueryResultWrap<OrderDetailDeliveryDataDomain> listOrderDetailDeliveryData(@Valid @NotNull("函数入参") ListOrderDetailDeliveryDataParam listOrderDetailDeliveryDataParam){
		MybatisParam params = MybatisTools.page(new MybatisParam(), listOrderDetailDeliveryDataParam);
		params.put("orderId", listOrderDetailDeliveryDataParam.getOrderId());
		params.put("orderDetailId", listOrderDetailDeliveryDataParam.getOrderDetailId());
		params.put("storeCode", AuthThreadLocal.getStoreCode());
		
		PagingQueryResultWrap<OrderDetailDeliveryDataDomain> wrap = new PagingQueryResultWrap<OrderDetailDeliveryDataDomain>();
		wrap.setDbCount(orderDetailDeliveryDataMapper.count(params));
		if (wrap.getDbCount() > 0) {
			wrap.setRecords(orderDetailDeliveryDataMapper.loadDynamicPaging(params));
		}

		return wrap;
	}
	
	@Valid
	public OrderDetailDeliveryDataDomain loadOrderDetailDeliveryData(@Valid @NotNull("函数入参") LoadOrderDetailDeliveryDataParam loadOrderDetailDeliveryDataParam){
		OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain =orderDetailDeliveryDataService.loadById(loadOrderDetailDeliveryDataParam.getOrderDetailDeliveryDataId());
		if (!orderDetailDeliveryDataDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())){
			throw new RuntimeException("订单明细不属于当前用户所属商铺");
		}
		return orderDetailDeliveryDataDomain;
	}
}
