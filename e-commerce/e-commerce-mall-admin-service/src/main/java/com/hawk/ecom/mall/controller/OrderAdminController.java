package com.hawk.ecom.mall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.mall.request.CloseUnpaidOrderParam;
import com.hawk.ecom.mall.service.OrderAdminService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

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
		
		orderAdminService.closeUnpaidOrder(param);
		
		
		return SuccessResponse.build(null);
	}
	
	//订单列表
	
	//单个订单，订单明细
	
	//订单明细，交付详情
}
