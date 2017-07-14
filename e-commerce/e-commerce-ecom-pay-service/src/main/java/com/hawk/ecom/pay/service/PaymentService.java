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
		 * 首先插入支付单，如果插入成功，则执行后续的支付逻辑
		 *            如果插入失败，验证当前支付单的状态 ，如果支付成功，直接报错。
		 *                                     如果是等待支付结果返回，则查询该支付单的实际支付结果，根据结果做相应的处理，
		 *                                     				        如果已支付，修改支付单的状态。
		 *                                                     如果未查到，则更改支付类型，将原支付类型的记录复制到历史表里，并且在历史表里记录删除日期，再继续执行后续的支付逻辑。
		 *                                                     如果等待支付结果返回，则直接报错
		 */
		
		/**
		 * 调用支付接口，给前台返回支付界面
		 */
	}
}
