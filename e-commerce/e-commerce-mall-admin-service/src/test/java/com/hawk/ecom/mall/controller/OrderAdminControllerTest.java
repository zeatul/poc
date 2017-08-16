package com.hawk.ecom.mall.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.mall.request.ListOrderDetailDeliveryDataParam;
import com.hawk.ecom.mall.request.ListOrderDetailParam;
import com.hawk.ecom.mall.request.ListOrderParam;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class OrderAdminControllerTest extends AbstractControllerTest {

	private String token = "200f27ec-bb87-4a69-8515-8bfe17634d65";

	// @Test
	public void testCloseUnpaidOrder() {
		String url = getUrl("/mall/admin/order/closeUnpaidOrder/order/1004920");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));

		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testListOrder() {
		String url = getUrl("/mall/admin/order/list");

		ListOrderParam request = new ListOrderParam();

		request.setUserCode("usr100001");
		request.setOrderStatus(ConstOrder.OrderStatus.UNPAIED);

		request.setPageRowCount(100);
		request.setPageIndex(1);
		request.setOrder("create_date desc");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));

		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testLoadOrder() {
		String url = getUrl("/mall/admin/order/load/orderId/1004926");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));

		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testListOrderDetail() {
		String url = getUrl("/mall/admin/order/detail/list");

		ListOrderDetailParam request = new ListOrderDetailParam();
		request.setOrderId(1004920);

		request.setPageRowCount(100);
		request.setPageIndex(1);
		request.setOrder("create_date desc");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));

		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testLoadOrderDetail() {
		String url = getUrl("/mall/admin/order/detail/load/orderDetailId/1004921");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));

		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

//	@Test
	public void testListOrderDetailDeliveryData() {
		String url = getUrl("/mall/admin/order/detail/deliveryData/list");

		ListOrderDetailDeliveryDataParam request = new ListOrderDetailDeliveryDataParam();
		request.setOrderDetailId(1004921);
		request.setOrderId(1004920);

		request.setPageRowCount(100);
		request.setPageIndex(1);
		request.setOrder("create_date desc");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));

		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	@Test
	public void testLoadOrderDetailDeliveryData(){
		String url = getUrl("/mall/admin/order/detail/deliveryData/load/deliveryDataId/1004922");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));

		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}
}
