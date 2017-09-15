package com.hawk.ecom.pay.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.hawk.ecom.pay.request.TradeParam;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class WXPayService {
	
	public static class WXPayType{
		/**
		 * H5支付(在微信外的浏览器调用)
		 */
		public final static String H5 = "MWEB";
		
		/**
		 * 公众号H5支付(在微信内进行H5支付)
		 */
		public final static String WPA = "JSAPI";
	}
	
	@Autowired
	private WXPay wxpay;
	
	@Autowired
	private WXPayConfig wxpayConfig;
	
	@Valid
	public String pay(@NotNull("微信支付参数") @Valid TradeParam tradeWapParam , String wxPayType,String openid,String ip) throws Exception {
		Map<String,String> reqData = new HashMap<String,String>();
//		reqData.put("appid", wxpayConfig.get);
//		reqData.put("mch_id", value);
//		reqData.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
//		reqData.put("sign", value);
//		reqData.put("sign_type", "MD5");
		
		reqData.put("body", tradeWapParam.getBody());
		reqData.put("out_trade_no", tradeWapParam.getOutTradeNo());
		reqData.put("device_info", "WEB");
		reqData.put("fee_type", "CNY");
		reqData.put("total_fee", tradeWapParam.getTotalAmount().multiply(new BigDecimal("100")).longValueExact() + "");
		reqData.put("spbill_create_ip", ip);
		reqData.put("notify_url", wxpayConfig.getNotifyUrl());		
   
		
		reqData.put("trade_type", wxPayType);		
		if (WXPayType.WPA.equals(wxPayType)){
			reqData.put("openid", wxPayType);
			if (StringTools.isNullOrEmpty(openid)){
				throw new RuntimeException("openid shouldn't be null");
			}
		}
		
				
		Map<String,String> rtn = wxpay.unifiedOrder(reqData);
		
		return x;
	}

}
