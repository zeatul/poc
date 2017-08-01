package com.hawk.ecom.pay.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.DateTools;

public class AlipayControllerTest extends AbstractControllerTest{
	
//	private String token = "";
	
	@Test
	public void testAlipayNotify(){
		String url = getUrl("/ecom/pay/alipay/notify");
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("notify_time", DateTools.convert(new Date(), DateTools.DATETIME_PATTERN)));
		params.add(new HttpParam("app_id", "2017072507887549"));
		params.add(new HttpParam("trade_no", "2013112011001004330000121536"));
		params.add(new HttpParam("out_trade_no", "20170731151010004"));
		params.add(new HttpParam("out_biz_no", "AAAAAAAAAAAAA"));
		params.add(new HttpParam("buyer_id", "111111111111111"));
		params.add(new HttpParam("buyer_logon_id", "www@sina.com"));
		params.add(new HttpParam("seller_id", "20170731151010004"));
		params.add(new HttpParam("seller_email", "seller_email"));
		params.add(new HttpParam("trade_status", "TRADE_SUCCESS"));
		params.add(new HttpParam("total_amount", "99"));
		params.add(new HttpParam("receipt_amount", "98"));
		params.add(new HttpParam("invoice_amount", "97"));
		params.add(new HttpParam("buyer_pay_amount", "97.55"));
		params.add(new HttpParam("point_amount", "0.45"));
		params.add(new HttpParam("refund_fee", "0.00"));
		params.add(new HttpParam("subject", "流量充值"));
		params.add(new HttpParam("body", "广东流量充值100M"));
		params.add(new HttpParam("gmt_create", DateTools.convert(new Date(), DateTools.DATETIME_PATTERN)));
		params.add(new HttpParam("gmt_payment", DateTools.convert(new Date(), DateTools.DATETIME_PATTERN)));
		params.add(new HttpParam("gmt_refund", DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN)));
		params.add(new HttpParam("gmt_close", DateTools.convert(new Date(), DateTools.DATETIME_PATTERN)));
		params.add(new HttpParam("fund_bill_list", "[{\"amount\":\"15.00\",\"fundChannel\":\"ALIPAYACCOUNT\"}]"));		
		
		Object object = null;
		
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

}
