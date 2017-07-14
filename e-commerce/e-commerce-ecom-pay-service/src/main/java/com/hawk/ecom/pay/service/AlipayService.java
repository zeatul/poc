package com.hawk.ecom.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.hawk.ecom.pay.request.AlipayTradeWapParam;
import com.hawk.ecom.trans.service.OrderService;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;

@Service
public class AlipayService {
	
	
	
	@Valid
	public String tradeWap(@NotNull("支付参数") @Valid AlipayTradeWapParam AlipayTradeWapParam){
		
		
//		// 商户订单号，商户网站订单系统中唯一订单号，必填
//	    String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//		// 订单名称，必填
//	    String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
//		System.out.println(subject);
//	    // 付款金额，必填
//	    String total_amount=new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
//	    // 商品描述，可空
//	    String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
//	    // 超时时间 可空
//	   String timeout_express="2m";
//	    // 销售产品码 必填
//	    String product_code="QUICK_WAP_PAY";
		
		AlipayTradeWapPayRequest alipayTradeWapPayRequest = new AlipayTradeWapPayRequest();
		
		AlipayTradeWapPayModel alipayTradeWapPayModel=new AlipayTradeWapPayModel();
		
		alipayTradeWapPayModel.setOutTradeNo(out_trade_no);
		alipayTradeWapPayModel.setSubject(subject);
		alipayTradeWapPayModel.setTotalAmount(total_amount);
		alipayTradeWapPayModel.setBody(body);
		alipayTradeWapPayModel.setProductCode("QUICK_WAP_PAY");
		alipayTradeWapPayModel.setBizModel(model);
	    // 设置异步通知地址
	    alipay_request.setNotifyUrl(AlipayConfig.notify_url);
	    // 设置同步地址
	    alipay_request.setReturnUrl(AlipayConfig.return_url);   
		
	}

}
