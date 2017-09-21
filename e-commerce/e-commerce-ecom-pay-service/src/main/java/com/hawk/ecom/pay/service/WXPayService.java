package com.hawk.ecom.pay.service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayUtil;
import com.hawk.ecom.pay.persist.domain.WxpayInfoDomain;
import com.hawk.ecom.pay.persist.mapper.WxpayInfoMapper;
import com.hawk.ecom.pay.request.NotifyParam;
import com.hawk.ecom.pay.request.TradeParam;
import com.hawk.ecom.pay.response.WXPayQueryResponse;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class WXPayService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private WxpayInfoMapper wxpayInfoMapper;
	
	@Autowired
	private PaymentService paymentService;

	public static class WXPayType {
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
	public String pay(@NotNull("微信支付参数") @Valid TradeParam tradeParam, String wxPayType, String openid, String ip) throws Exception {
		Map<String, String> reqData = new HashMap<String, String>();

		reqData.put("body", StringTools.concatWithSymbol("-", "飞到家", tradeParam.getSubject()));
		reqData.put("out_trade_no", tradeParam.getOutTradeNo());
		reqData.put("device_info", "WEB");
		reqData.put("fee_type", "CNY");
		reqData.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		reqData.put("total_fee", tradeParam.getTotalAmount().multiply(new BigDecimal("100")).longValueExact() + "");
		reqData.put("spbill_create_ip", ip);
		reqData.put("notify_url", wxpayConfig.getNotifyUrl());

		reqData.put("trade_type", wxPayType);
		if (WXPayType.WPA.equals(wxPayType)) {
			reqData.put("openid", wxPayType);
			if (StringTools.isNullOrEmpty(openid)) {
				throw new RuntimeException("openid shouldn't be null");
			}
		}

		Map<String, String> r = wxpay.unifiedOrder(reqData);

		logger.info("WXPayService.pay.response={}",JsonTools.toJsonString(r));
		
		String return_code = r.get("return_code");
		String return_msg = r.get("return_msg");
		if (!"SUCCESS".equalsIgnoreCase(return_code)) {
			throw new RuntimeException(return_msg);
		}
		String result_code = r.get("result_code");
		if (!"SUCCESS".equalsIgnoreCase(result_code)) {
			String err_code = r.get("err_code");
			String err_code_des = r.get("err_code_des");
			throw new RuntimeException(err_code_des+"("+err_code+")");
		}

		String url = r.get("mweb_url");
		return StringTools.concat(url,"&redirect_url=",URLEncoder.encode(url, "utf-8"));

	}

	/**
	 * 关闭未支付的订单
	 * 
	 * @param outTradeCode
	 * @throws Exception
	 */
	@Valid
	public void close(@NotEmpty String outTradeNo) throws Exception {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("out_trade_no", outTradeNo);
		Map<String, String> r = wxpay.closeOrder(data);
		logger.info("WXPayService.close.response={}", JsonTools.toJsonString(r));
		String return_code = r.get("return_code");
		String return_msg = r.get("return_msg");
		if (!"SUCCESS".equalsIgnoreCase(return_code)) {
			throw new RuntimeException(return_msg);
		}

		String result_code = r.get("result_code");
		if ("SUCCESS".equalsIgnoreCase(result_code)) {
			return;
		}

		String err_code = r.get("err_code");
		String err_code_des = r.get("err_code_des");

		if ("ORDERCLOSED".equalsIgnoreCase(err_code)) {
			return;
		} else {
			throw new RuntimeException(err_code_des);
		}

	}

	public WXPayQueryResponse query(@NotEmpty String outTradeNo) throws Exception {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("out_trade_no", outTradeNo);
		Map<String, String> r = wxpay.orderQuery(data);
		logger.info("WXPayService.query.response={}", JsonTools.toJsonString(r));
		String return_code = r.get("return_code");
		String return_msg = r.get("return_msg");
		if (!"SUCCESS".equalsIgnoreCase(return_code)) {
			throw new RuntimeException(return_msg);
		}
		
		String result_code = r.get("result_code");
		if (!"SUCCESS".equalsIgnoreCase(result_code)) {
			String err_code = r.get("err_code");
			if ("ORDERNOTEXIST".equals(err_code)){
				return null;
			}
			String err_code_des = r.get("err_code_des");
			throw new RuntimeException(err_code_des);
		}

		String trade_state  =  r.get("trade_state");
		

		WXPayQueryResponse response = new WXPayQueryResponse();
		response.setTradeState(trade_state);
		
		return response;
		
	}
	
	@Valid
	public int hasPaidSuccessfully(@NotEmpty String outTradeNo) throws Exception {
		WXPayQueryResponse wxpayQueryResponse = query(outTradeNo);
		if (wxpayQueryResponse == null){
			return -1;
		}
		
		String tradeState = wxpayQueryResponse.getTradeState();
		if ("SUCCESS".equalsIgnoreCase(tradeState)){
			return 1;
		}else if ("USERPAYING".equalsIgnoreCase(tradeState)){
			return 0;
		}else{
			return -1;
		}
	}
	
	private void notifyp(String notifyXml) throws Exception{
		Map<String,String> notifyMap = wxpay.processResponseXml(notifyXml);
		String return_code = notifyMap.get("return_code");
		String return_msg = notifyMap.get("return_msg");
		if (!"SUCCESS".equalsIgnoreCase(return_code)){
			throw new RuntimeException(return_msg);
		}
		
		WxpayInfoDomain wxpayInfoDomain = new WxpayInfoDomain();
		
//		公众账号ID appid 是 String(32) wx8888888888888888 微信分配的公众账号ID（企业号corpid即为此appId） 
		String appid = notifyMap.get("appid");
		wxpayInfoDomain.setAppid(appid);
		
//		商户号 mch_id 是 String(32) 1900000109 微信支付分配的商户号 
		String mch_id = notifyMap.get("mch_id");
		wxpayInfoDomain.setMchId(mch_id);
		
//		设备号 device_info 否 String(32) 013467007045764 微信支付分配的终端设备号， 
		String device_info = notifyMap.get("device_info");
		wxpayInfoDomain.setDeviceInfo(device_info);
		
//		随机字符串 nonce_str 是 String(32) 5K8264ILTKCH16CQ2502SI8ZNMTM67VS 随机字符串，不长于32位 
		
//		签名 sign 是 String(32) C380BEC2BFD727A4B6845133519F3AD6 签名，详见签名算法 
		
//		签名类型 sign_type 否 String(32) HMAC-SHA256 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5 
		
//		业务结果 result_code 是 String(16) SUCCESS SUCCESS/FAIL 
		String result_code = notifyMap.get("result_code");
		wxpayInfoDomain.setResultCode(result_code);
		
//		错误代码 err_code 否 String(32) SYSTEMERROR 错误返回的信息描述 
		String err_code = notifyMap.get("err_code");
		wxpayInfoDomain.setErrCode(err_code);
		
//		错误代码描述 err_code_des 否 String(128) 系统错误 错误返回的信息描述 
		String err_code_des = notifyMap.get("err_code_des");
		wxpayInfoDomain.setErrCodeDes(err_code_des);
		
//		用户标识 openid 是 String(128) wxd930ea5d5a258f4f 用户在商户appid下的唯一标识 
		String openid = notifyMap.get("openid");
		wxpayInfoDomain.setOpenid(openid);
		
//		是否关注公众账号 is_subscribe 否 String(1) Y 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效 
		
//		交易类型 trade_type 是 String(16) JSAPI JSAPI、NATIVE、APP 
		String trade_type = notifyMap.get("trade_type");
		wxpayInfoDomain.setTradeType(trade_type);
		
//		付款银行 bank_type 是 String(16) CMC 银行类型，采用字符串类型的银行标识，银行类型见银行列表 
		String bank_type = notifyMap.get("bank_type"); 
		wxpayInfoDomain.setBankType(bank_type);
		
//		订单金额 total_fee 是 Int 100 订单总金额，单位为分 
		String total_fee = notifyMap.get("total_fee"); 
		wxpayInfoDomain.setTotalFee(total_fee==null?null:new Integer(total_fee));
		
//		应结订单金额 settlement_total_fee  否 Int 100  应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。  
		String settlement_total_fee = notifyMap.get("settlement_total_fee");
		wxpayInfoDomain.setSettlementTotalFee(settlement_total_fee==null?null:new Integer(settlement_total_fee));
		
//		货币种类 fee_type 否 String(8) CNY 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型 
		String fee_type = notifyMap.get("fee_type");
		wxpayInfoDomain.setFeeType(fee_type);
		
//		现金支付金额 cash_fee 是 Int 100 现金支付金额订单现金支付金额，详见支付金额 
		String cash_fee = notifyMap.get("cash_fee");
		wxpayInfoDomain.setCashFee(cash_fee==null?null:new Integer(cash_fee));
		
//		现金支付货币类型 cash_fee_type 否 String(16) CNY 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型 
		String cash_fee_type = notifyMap.get("cash_fee_type");
		wxpayInfoDomain.setFeeType(cash_fee_type);
		
//		总代金券金额 coupon_fee 否 Int 10 代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额 
		String coupon_fee = notifyMap.get("coupon_fee");
		wxpayInfoDomain.setCouponFee(coupon_fee==null?null:new Integer(coupon_fee));
		
//		代金券使用数量 coupon_count 否 Int 1 代金券使用数量 
		String coupon_count = notifyMap.get("coupon_count");
		wxpayInfoDomain.setCouponCount(coupon_count==null?null:new Integer(coupon_count));
		
//		代金券类型  coupon_type_$n  否  String CASH 
//		CASH--充值代金券 
//		NO_CASH---非充值代金券 
//		订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
		 
//		代金券ID coupon_id_$n 否 String(20)  10000 代金券ID,$n为下标，从0开始编号 
		
//		单个代金券支付金额 coupon_fee_$n 否 Int 100 单个代金券支付金额,$n为下标，从0开始编号 
		
//		微信支付订单号 transaction_id 是 String(32) 1217752501201407033233368018 微信支付订单号 
		String transaction_id = notifyMap.get("transaction_id");
		wxpayInfoDomain.setTransactionId(transaction_id);
		
//		商户订单号 out_trade_no 是 String(32) 1212321211201407033568112322 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。 
		String out_trade_no = notifyMap.get("out_trade_no");
		wxpayInfoDomain.setOutTradeNo(out_trade_no);
		
//		商家数据包 attach 否 String(128) 123456 商家数据包，原样返回 
		
//		支付完成时间 time_end 是 String(14) 20141030133525 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则 
		String time_end = notifyMap.get("time_end");
		wxpayInfoDomain.setTimeEnd(time_end);
		
		wxpayInfoDomain.setCreateDate(new Date());
		wxpayInfoDomain.setUpdateDate(wxpayInfoDomain.getCreateDate());
		
		wxpayInfoMapper.insert(wxpayInfoDomain);
		
		
		if ("SUCCESS".equals(result_code)){
			NotifyParam notifyParam = new NotifyParam();
			notifyParam.setPaymentBillCode(wxpayInfoDomain.getOutTradeNo());
			notifyParam.setTotalAmount(new BigDecimal(""+wxpayInfoDomain.getTotalFee()*0.01));
			paymentService.notifySuccess(notifyParam);
		}
	}

	public String notify(String notifyXml) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		try{
			notifyp(notifyXml);
			map.put("return_code", "SUCCESS");
			map.put("return_msg", "OK");
		}catch(Exception ex){
			map.put("return_code", "FAIL");
			map.put("return_msg", ex.getMessage()+ex.getClass().getSimpleName());
		}

		return WXPayUtil.mapToXml(map);

		
	}
}
