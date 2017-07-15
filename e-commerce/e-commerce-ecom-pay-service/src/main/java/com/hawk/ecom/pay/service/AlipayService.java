package com.hawk.ecom.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.hawk.ecom.pay.request.AlipayTradeParam;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;

@Service
public class AlipayService {
	
	@Autowired
	private AlipayClient alipayClient;
	
	
	
	@Valid
	public String tradeWap(@NotNull("支付宝支付参数") @Valid AlipayTradeParam alipayTradeWapParam) throws Exception{
		
		AlipayTradeWapPayModel model =new AlipayTradeWapPayModel();
		
		model.setOutTradeNo(alipayTradeWapParam.getOutTradeNo());
		model.setSubject(alipayTradeWapParam.getSubject());
		model.setTotalAmount(alipayTradeWapParam.getTotalAmount().toString());
		model.setBody(alipayTradeWapParam.getBody());
		model.setProductCode("QUICK_WAP_PAY");
		
		AlipayTradeWapPayRequest alipayRequest=new AlipayTradeWapPayRequest();
		
		alipayRequest.setBizModel(model);
	    // 设置异步通知地址
		alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);
	    // 设置同步地址
		alipayRequest.setReturnUrl(AlipayConfig.RETURN_URL);   
		
		String form = alipayClient.pageExecute(alipayRequest).getBody();
		
		return form;
		
	}

}
