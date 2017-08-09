package com.hawk.ecom.trans.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.trans.persist.domain.OrderDetailDomain;
import com.hawk.ecom.trans.persist.domain.OrderDomain;
import com.hawk.ecom.trans.request.CreateOrderParam;
import com.hawk.ecom.trans.request.ListOrderDetailParam;
import com.hawk.ecom.trans.request.ListOrderParam;
import com.hawk.ecom.trans.request.LoadOrderParam;
import com.hawk.ecom.trans.response.OrderDetailInfoResponse;
import com.hawk.ecom.trans.response.OrderInfoResponse;
import com.hawk.ecom.trans.service.OrderDetailService;
import com.hawk.ecom.trans.service.OrderService;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/ecom/trans/order")
@CrossOrigin
@Service
public class OrderCustomerController {
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/trans/order controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;

	@RequestMapping(value = "/create", method = POST)
	public WebResponse<OrderInfoResponse> createOrder(HttpServletRequest request) throws Exception {
		CreateOrderParam param = HttpRequestTools.parse(request, CreateOrderParam.class);
		param.setUserCode(AuthThreadLocal.getUserCode());
		OrderDomain orderDomain = orderService.createOrder(param);
		return SuccessResponse.build(DomainTools.copy(orderDomain, OrderInfoResponse.class));
	}
	
	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MultiResponse<OrderInfoResponse>> ListOrder(HttpServletRequest request) throws Exception {
		ListOrderParam param = HttpRequestTools.parse(request, ListOrderParam.class);
		param.setUserCode(AuthThreadLocal.getUserCode());
		PagingQueryResultWrap<OrderDomain> wrap = orderService.listOrder(param);

		MultiResponse<OrderInfoResponse> result = new MultiResponse<OrderInfoResponse>(MybatisTools.copy(wrap, OrderInfoResponse.class));
		return SuccessResponse.build(result);
	}
	
	@RequestMapping(value = "/load/id/{orderId}", method = {GET,POST})
	public WebResponse<OrderInfoResponse> loadOrder (@PathVariable Integer orderId) throws Exception{
		LoadOrderParam param = new  LoadOrderParam();
		param.setUserCode(AuthThreadLocal.getUserCode());
		param.setOrderId(orderId);
		return SuccessResponse.build(DomainTools.copy(orderService.loadOrder(param), OrderInfoResponse.class));
	}
	
	@RequestMapping(value = "/detail/list", method = POST)
	public WebResponse<MultiResponse<OrderDetailInfoResponse>> ListOrderDetail(HttpServletRequest request) throws Exception {
		ListOrderDetailParam param = HttpRequestTools.parse(request, ListOrderDetailParam.class);
		param.setUserCode(AuthThreadLocal.getUserCode());
		PagingQueryResultWrap<OrderDetailDomain> wrap = orderDetailService.listOrderDetail(param);

		MultiResponse<OrderDetailInfoResponse> result = new MultiResponse<OrderDetailInfoResponse>(MybatisTools.copy(wrap, OrderDetailInfoResponse.class));
		return SuccessResponse.build(result);
	} 
}
