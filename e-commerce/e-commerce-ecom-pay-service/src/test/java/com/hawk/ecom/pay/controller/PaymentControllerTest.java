package com.hawk.ecom.pay.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.pay.request.PayParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class PaymentControllerTest extends AbstractControllerTest{
	
	private String token = "";
	
	@Test
	public void testReportOrderDetail(){
		String url = getUrl("/ecom/pay/wap");
		
		
		PayParam request = new PayParam();
		request.setOrderId(11000);
		request.setPaymentCategoryCode("alipay");
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

}
