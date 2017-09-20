package com.hawk.ecom.pay.service;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.hawk.ecom.pay.request.TradeParam;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.utility.tools.JsonTools;
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
	public String pay(@NotNull("微信支付参数") @Valid TradeParam tradeParam , String wxPayType,String openid,String ip) throws Exception {
		Map<String,String> reqData = new HashMap<String,String>();
//		reqData.put("appid", wxpayConfig.get);
//		reqData.put("mch_id", value);
//		reqData.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
//		reqData.put("sign", value);
//		reqData.put("sign_type", "MD5");
		
		reqData.put("body", StringTools.concatWithSymbol("-","飞到家", tradeParam.getSubject()));
		reqData.put("out_trade_no", tradeParam.getOutTradeNo());
		reqData.put("device_info", "WEB");
		reqData.put("fee_type", "CNY");
		reqData.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		reqData.put("total_fee", tradeParam.getTotalAmount().multiply(new BigDecimal("100")).longValueExact() + "");
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
		
		System.out.println(JsonTools.toJsonString(rtn));
		
		return rtn.get("mweb_url");	
		
//		StringBuilder sb =StringTools.getThreadSafeStringBuilder();
//		
//		sb.append("weixin://wap/pay?prepayid=");
//		sb.append(rtn.get("prepay_id"));
//		sb.append("&package=");		
//		String meeb_url = rtn.get("mweb_url");		
//		for (NameValuePair nameValuePair :URLEncodedUtils.parse(meeb_url, Charset.forName("UTF-8"))){
//			String name = nameValuePair.getName();			
//			if (name.equals("package")){
//				String value = nameValuePair.getValue();
//				sb.append(value);
//				break;
//			}
//		}
//		sb.append("&noncestr=");	
//		sb.append(rtn.get("nonce_str"));
//		sb.append("&sign=");
//		sb.append(rtn.get("sign"));
//		return sb.toString();
	}

}
