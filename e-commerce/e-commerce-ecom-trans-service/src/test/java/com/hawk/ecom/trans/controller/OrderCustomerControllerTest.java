package com.hawk.ecom.trans.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.request.CreateOrderParam;
import com.hawk.ecom.trans.request.ListOrderDetailParam;
import com.hawk.ecom.trans.request.ListOrderParam;
import com.hawk.ecom.trans.request.OrderDetailParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class OrderCustomerControllerTest extends AbstractControllerTest {
	
	private String token = "64ae0773-5d4d-4a50-8e49-98f08b937cc4";

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
		deliveryData.put("mobileNumber", "13910002481");
		orderDetailParam.setDeliveryData(deliveryData);
		orderDetailParam.setOrderDetailQuantity(1);
		orderDetailParam.setSkuId(1001312);
		orderDetails.add(orderDetailParam);
		request.setOrderDetails(orderDetails);
		request.setOrderDesc("流量充值");
		

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testListOrder(){
		String url = getUrl("/ecom/trans/order/list");
		ListOrderParam request = new ListOrderParam();
//		request.setOrder("create_date desc");
		request.setOrderStatus(ConstOrder.OrderStatus.UNPAIED);
		request.setPageIndex(1);
		request.setPageRowCount(100);		

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testLoadOrder() {
		String url = getUrl("/ecom/trans/order/load/id/1000013");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testListOrderDetail(){
		String url = getUrl("/ecom/trans/order/detail/list");
		ListOrderDetailParam request = new ListOrderDetailParam();
		request.setOrderId(1000013);
		request.setPageIndex(1);
		request.setPageRowCount(100);		

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
