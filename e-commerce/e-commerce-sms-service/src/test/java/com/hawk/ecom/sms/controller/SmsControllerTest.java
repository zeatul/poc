package com.hawk.ecom.sms.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.sms.request.SendVeriCodeParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;

public class SmsControllerTest extends AbstractControllerTest{
	@Test
	public void testVeriCode() {
		String url = getUrl("/sms/veriCode");
		SendVeriCodeParam sendVeriCodeParam = new SendVeriCodeParam();
		sendVeriCodeParam.setMobileNumber("13800000000");
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));		
		
		String result = httpExecutor.post(url, sendVeriCodeParam, params);
		System.out.println("result=" + result);
	}
}
