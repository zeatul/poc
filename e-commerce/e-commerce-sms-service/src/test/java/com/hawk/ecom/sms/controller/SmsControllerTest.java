package com.hawk.ecom.sms.controller;

import org.junit.Test;

import com.hawk.ecom.sms.request.SendVeriCodeParam;

public class SmsControllerTest extends AbstractControllerTest{
	@Test
	public void testVeriCode() {
		String url = getUrl("/sms/veriCode");
		SendVeriCodeParam sendVeriCodeParam = new SendVeriCodeParam();
		sendVeriCodeParam.setMobileNumber("13916082481");
		String result = httpExecutor.post(url, sendVeriCodeParam, null);
		System.out.println("result=" + result);
	}
}
