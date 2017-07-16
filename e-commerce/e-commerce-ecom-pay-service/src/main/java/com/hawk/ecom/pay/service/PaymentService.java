package com.hawk.ecom.pay.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hawk.ecom.pay.constant.ConstPay;
import com.hawk.ecom.pay.persist.domain.PaymentBillDomain;
import com.hawk.ecom.pay.persist.domain.PaymentBillHistoryDomain;
import com.hawk.ecom.pay.persist.mapper.PaymentBillHistoryMapper;
import com.hawk.ecom.pay.persist.mapper.PaymentBillMapper;
import com.hawk.ecom.pay.request.AlipayTradeParam;
import com.hawk.ecom.pay.request.NotifyParam;
import com.hawk.ecom.pay.request.PayParam;
import com.hawk.ecom.trans.response.OrderPayInfo;
import com.hawk.ecom.trans.service.OrderService;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class PaymentService {
	
	@Autowired
	@Qualifier("paymentBillCodeSequenceService")
	private PkGenService paymentBillCodeSequenceService;
	
	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentBillMapper paymentBillMapper;
	
	@Autowired
	private PaymentBillHistoryMapper paymentBillHistoryMapper;
	
	@Autowired
	private AlipayService alipayService;
	
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
	public void notify(@Valid @NotNull("通知参数") NotifyParam notifyParam){
		
	}
	
	@Valid
	public String pay(@Valid @NotNull("支付参数") PayParam payParam) throws Exception{
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
		Date now = new Date();
		String userCode = orderPayInfo.getUserCode();
		PaymentBillDomain paymentBillDomain = new PaymentBillDomain();
		paymentBillDomain.setApplicationCode(orderPayInfo.getApplicationCode());
		paymentBillDomain.setCreateDate(now);
		paymentBillDomain.setCreateUserCode(null); /*客户生成的支付单*/
		paymentBillDomain.setCurrency(orderPayInfo.getCurrency());
		paymentBillDomain.setId(pkGenService.genPk());
		paymentBillDomain.setOrderBody(orderPayInfo.getBody());
		paymentBillDomain.setOrderCode(orderPayInfo.getOrderCode());
		paymentBillDomain.setOrderDesc(orderPayInfo.getOrderDesc());
		paymentBillDomain.setTotalAmount(orderPayInfo.getTotalAmount());
		paymentBillDomain.setPaymentBillCode(generatePaymentBillCode(now));
		paymentBillDomain.setPaymentBillStatus(ConstPay.PaymentBillStatus.WAITING);
		paymentBillDomain.setPaymentCategoryCode(payParam.getPaymentCategoryCode());
		paymentBillDomain.setStoreCode(orderPayInfo.getStoreCode());
		paymentBillDomain.setUpdateDate(now);
		paymentBillDomain.setUpdateUserCode(null);
		paymentBillDomain.setUserCode(userCode);
		
		try{
			paymentBillMapper.insert(paymentBillDomain);
		}catch (DuplicateKeyException ex) {
			
			MybatisParam params = new MybatisParam().put("applicationCode", paymentBillDomain.getApplicationCode()).put("orderCode", paymentBillDomain.getOrderCode());
			PaymentBillDomain olderPaymentBillDomain = paymentBillMapper.loadDynamic(params).get(0);
			
			if (olderPaymentBillDomain.getPaymentBillStatus() == ConstPay.PaymentBillStatus.FAILURE){
				/**
				 * 支付失败，当前支付单转为历史记录，生成一张新的支付单
				 */
				
				PaymentBillHistoryDomain paymentBillHistoryDomain = DomainTools.copy(olderPaymentBillDomain, PaymentBillHistoryDomain.class);
				paymentBillHistoryDomain.setDeleteDate(now);
				paymentBillHistoryMapper.insert(paymentBillHistoryDomain);	
				
				/**
				 * 生成新的支付单，保留原始的支付单号
				 */
				paymentBillDomain.setPaymentBillCode(olderPaymentBillDomain.getPaymentBillCode());
				paymentBillMapper.insert(paymentBillDomain);
				
				
			}else if (olderPaymentBillDomain.getPaymentBillStatus() == ConstPay.PaymentBillStatus.SUCCESS){
				/**
				 * 支付成功，直接报错
				 */
				throw new RuntimeException("已经支付成功");
			}else if (olderPaymentBillDomain.getPaymentBillStatus() == ConstPay.PaymentBillStatus.WAITING){
				/**
				 * 等待支付结果返回。
				 * 调用对应的接口查询，根据查询结果再做处理，记录备注，修改状态，生成新的支付单，等等。
				 * 如果支付目录不变，直接支付，反正是幂等的。
				 */
				throw new RuntimeException("请等待支付返回通知");
			}
		}
		
		/**
		 * 调用支付接口，给前台返回支付界面
		 */
		String paymentCategoryCode = payParam.getPaymentCategoryCode();
		if (paymentCategoryCode.equals(ConstPay.PayCategoryCode.ALIPAY)){
			AlipayTradeParam alipayTradeParam = new AlipayTradeParam();
			alipayTradeParam.setBody(orderPayInfo.getBody());
			alipayTradeParam.setOutTradeNo(paymentBillDomain.getPaymentBillCode());
			alipayTradeParam.setSubject(orderPayInfo.getOrderDesc());
			alipayTradeParam.setTotalAmount(orderPayInfo.getTotalAmount());
			return alipayService.wapPay(alipayTradeParam);
		}else if (paymentCategoryCode.equals(ConstPay.PayCategoryCode.WXPAY)){
			throw new RuntimeException("暂不支持微信支付");
		}else{
			throw new RuntimeException("未知的支付方式");
		}
	}
}
