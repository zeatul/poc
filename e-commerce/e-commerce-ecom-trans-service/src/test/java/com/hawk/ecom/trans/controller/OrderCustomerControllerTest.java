package com.hawk.ecom.trans.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.request.CreateOrderParam;
import com.hawk.ecom.trans.request.OrderDetailParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class OrderCustomerControllerTest extends AbstractControllerTest {
	
	private String token = "384657bd-bfb0-4fea-aa2b-f25a4e82a412";

	@Test
	public void testCreateChargeOrder(){
		String url = getUrl("/ecom/trans/order/create");
		CreateOrderParam request = new CreateOrderParam();
		request.setOrderCustomerMemo("勇者得钱,冲啊!!!");
		request.setOrderType(ConstOrder.OrderType.CUSTOMER_ONLINE_PC);
		request.setPayType(ConstOrder.PayType.ON_LINE);
		List<OrderDetailParam> orderDetails = new ArrayList<OrderDetailParam>();
		OrderDetailParam orderDetailParam = new OrderDetailParam();
		Map<String,Object> deliveryData = new HashMap<String,Object>();
		deliveryData.put("mobileNumber", "13800000000");
		orderDetailParam.setDeliveryData(deliveryData);
		orderDetailParam.setOrderDetailQuantity(1);
		orderDetailParam.setSkuId(1000022);
		orderDetails.add(orderDetailParam);
		request.setOrderDetails(orderDetails);
		

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
