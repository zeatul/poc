package com.hawk.ecom.pay.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hawk.ecom.pay.request.PayParam;
import com.hawk.ecom.trans.response.OrderPayInfo;
import com.hawk.ecom.trans.service.OrderService;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class PaymentService {
	
	@Autowired
	@Qualifier("paymentBillCodeSequenceService")
	private PkGenService paymentBillCodeSequenceService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * @param now
	 * @return
	 */
	private String generatePaymentBillCode(Date now){
		String head = DateTools.convert(now, "yyyyMMddHH");
		Integer tail = paymentBillCodeSequenceService.genPk()+1000000;
		
		return StringTools.concat(head,tail);
	}
	
	@Valid
	public void trade(@Valid @NotNull PayParam payParam){
		/**
		 * 获取支付信息
		 */
		OrderPayInfo orderPayInfo = orderService.computeOrderPayInfo(payParam.getOrderId());
		orderPayInfo.setApplicationCode("SVP");
		/**
		 * 生成支付单
		 */
	}
}
