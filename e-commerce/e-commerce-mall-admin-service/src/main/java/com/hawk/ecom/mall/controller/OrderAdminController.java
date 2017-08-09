package com.hawk.ecom.mall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.mall.request.CloseUnpaidOrderParam;
import com.hawk.ecom.mall.request.ListOrderDetailDeliveryDataParam;
import com.hawk.ecom.mall.request.ListOrderDetailParam;
import com.hawk.ecom.mall.request.ListOrderParam;
import com.hawk.ecom.mall.request.LoadOrderParam;
import com.hawk.ecom.mall.response.OrderDetailDeliveryDataInfoResponse;
import com.hawk.ecom.mall.response.OrderInfoResponse;
import com.hawk.ecom.mall.service.OrderAdminService;
import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.domain.OrderDetailDomain;
import com.hawk.ecom.trans.persist.domain.OrderDomain;
import com.hawk.ecom.trans.response.OrderDetailInfoResponse;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/order")
@CrossOrigin
public class OrderAdminController {
	
	@Autowired
	private OrderAdminService orderAdminService;

	
	/**
	 * 关闭未支付的订单
	 * @param request
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/closeUnpaidOrder/order/{orderId}", method = {POST,GET})
	public WebResponse<ResponseData> closeUnpaidOrder(HttpServletRequest request,@PathVariable Integer orderId) throws Exception {
		
		CloseUnpaidOrderParam param = new CloseUnpaidOrderParam();
		param.setOrderId(orderId);
		param.setUserCode(AuthThreadLocal.getUserCode());
		param.setStoreCode(AuthThreadLocal.getStoreCode());
		
		orderAdminService.closeUnpaidOrder(param);
		
		
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MultiResponse<OrderInfoResponse>> ListOrder(HttpServletRequest request) throws Exception {
		ListOrderParam param = HttpRequestTools.parse(request, ListOrderParam.class);
		param.setUserCode(AuthThreadLocal.getUserCode());
		param.setStoreCode(AuthThreadLocal.getStoreCode());
		PagingQueryResultWrap<OrderDomain> wrap = orderAdminService.listOrder(param);

		MultiResponse<OrderInfoResponse> result = new MultiResponse<OrderInfoResponse>(MybatisTools.copy(wrap, OrderInfoResponse.class));
		return SuccessResponse.build(result);
	}
	
	@RequestMapping(value = "/load/id/{orderId}", method = {GET,POST})
	public WebResponse<OrderInfoResponse> loadOrder (@PathVariable Integer orderId) throws Exception{
		LoadOrderParam param = new  LoadOrderParam();
		param.setUserCode(AuthThreadLocal.getUserCode());
		param.setOrderId(orderId);
		param.setStoreCode(AuthThreadLocal.getStoreCode());
		return SuccessResponse.build(DomainTools.copy(orderAdminService.loadOrder(param), OrderInfoResponse.class));
	}
	
	@RequestMapping(value = "/detail/list", method = {POST})
	public WebResponse<MultiResponse<OrderDetailInfoResponse>> ListOrderDetail(HttpServletRequest request) throws Exception {
		ListOrderDetailParam param = HttpRequestTools.parse(request, ListOrderDetailParam.class);
		param.setUserCode(AuthThreadLocal.getUserCode());
		PagingQueryResultWrap<OrderDetailDomain> wrap = orderAdminService.listOrderDetail(param);

		MultiResponse<OrderDetailInfoResponse> result = new MultiResponse<OrderDetailInfoResponse>(MybatisTools.copy(wrap, OrderDetailInfoResponse.class));
		return SuccessResponse.build(result);
	} 
	
	//订单明细，交付详情
	@RequestMapping(value = "/detail/delivery/list", method = {POST})
	public WebResponse<MultiResponse<OrderDetailDeliveryDataInfoResponse>> ListOrderDetailDeliveryData(HttpServletRequest request) throws Exception {
		ListOrderDetailDeliveryDataParam param = HttpRequestTools.parse(request, ListOrderDetailDeliveryDataParam.class);
		param.setUserCode(AuthThreadLocal.getUserCode());
		param.setStoreCode(AuthThreadLocal.getStoreCode());
		PagingQueryResultWrap<OrderDetailDeliveryDataDomain> wrap = orderAdminService.listOrderDetailDeliveryData(param);

		MultiResponse<OrderDetailDeliveryDataInfoResponse> result = new MultiResponse<OrderDetailDeliveryDataInfoResponse>(MybatisTools.copy(wrap, OrderDetailDeliveryDataInfoResponse.class));
		return SuccessResponse.build(result);
	} 
}
