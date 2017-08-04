package com.hawk.ecom.pay.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.hawk.ecom.pay.constant.ConstPay;
import com.hawk.ecom.pay.exception.IllegalNotificationRuntimeException;
import com.hawk.ecom.pay.exception.PaymentBillNotFoundRuntimeException;
import com.hawk.ecom.pay.exception.PaymentBillStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.pay.persist.domain.PaymentBillDomain;
import com.hawk.ecom.pay.persist.domain.PaymentBillHistoryDomain;
import com.hawk.ecom.pay.persist.mapper.PaymentBillHistoryMapper;
import com.hawk.ecom.pay.persist.mapper.PaymentBillMapper;
import com.hawk.ecom.pay.persist.mapperex.PaymentBillExMapper;
import com.hawk.ecom.pay.request.AlipayTradeParam;
import com.hawk.ecom.pay.request.NotifyParam;
import com.hawk.ecom.pay.request.PayParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.response.OrderPayInfo;
import com.hawk.ecom.trans.service.OrderService;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
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
	private PaymentBillExMapper paymentBillExMapper;

	@Autowired
	private PaymentBillHistoryMapper paymentBillHistoryMapper;

	@Autowired
	private AlipayService alipayService;

	public int hasPaidSuccessfully(PaymentBillDomain paymentBillDomain) throws Exception {
		if (paymentBillDomain.getPaymentCategoryCode() == ConstPay.PayCategoryCode.ALIPAY) {
			AlipayTradeQueryResponse alipayTradeQueryResponse = alipayService.query(paymentBillDomain.getPaymentBillCode());
			if (alipayTradeQueryResponse == null) {
				return -1;
			}
			String tradeStatus = alipayTradeQueryResponse.getTradeStatus();
			if (tradeStatus.equalsIgnoreCase(ConstPay.AlipayTradeStatus.TRADE_SUCCESS)
					|| tradeStatus.equalsIgnoreCase(ConstPay.AlipayTradeStatus.TRADE_FINISHED)) {
				return 1;
			} else if (tradeStatus.equalsIgnoreCase(ConstPay.AlipayTradeStatus.WAIT_BUYER_PAY)) {
				return 0;
			} else if (tradeStatus.equalsIgnoreCase(ConstPay.AlipayTradeStatus.TRADE_CLOSED)) {
				return -1;
			} else {
				throw new RuntimeException("unknown alipay trade status = " + tradeStatus);
			}

		}else{

			throw new RuntimeException("unsupported pay category");
		}
	}

	/**
	 * 
	 * @param now
	 * @return
	 */
	private String generatePaymentBillCode(Date now) {
		String head = DateTools.convert(now, "yyyyMMddHH");
		Integer tail = paymentBillCodeSequenceService.genPk() + 1000000;

		return StringTools.concat(head, tail);
	}

	public PaymentBillDomain loadPaymentBill(String paymentBillCode) {
		PaymentBillDomain paymentBillDomain = null;
		if (StringTools.isNotNullOrEmpty(paymentBillCode)) {
			MybatisParam params = new MybatisParam().put("paymentBillCode", paymentBillCode);
			paymentBillDomain = MybatisTools.single(paymentBillMapper.loadDynamic(params));
		}
		if (paymentBillDomain == null) {
			throw new PaymentBillNotFoundRuntimeException();
		}

		return paymentBillDomain;
	}

	public PaymentBillDomain loadPaymentBill(Integer paymentBillId) {
		PaymentBillDomain paymentBillDomain = null;
		if (paymentBillId != null) {
			paymentBillDomain = paymentBillMapper.load(paymentBillId);
		}
		if (paymentBillDomain == null) {
			throw new PaymentBillNotFoundRuntimeException();
		}

		return paymentBillDomain;
	}

	@Valid
	public PaymentBillDomain queryPaymentBill(@NotEmpty("orderCode") String orderCode, @NotEmpty("applicationCode") String applicationCode) {

		MybatisParam params = new MybatisParam().put("orderCode", orderCode).put("applicationCode", applicationCode);

		return MybatisTools.single(paymentBillMapper.loadDynamic(params));
	}
	
	private void updatePaymentBillStatus(PaymentBillDomain paymentBillDomain,int paymentBillStatus){
		PaymentBillDomain updateDomain = new PaymentBillDomain();
		if (paymentBillDomain.getPaymentBillStatus() == paymentBillStatus){
			return ;
		}
		
		updateDomain.setId(paymentBillDomain.getId());
		updateDomain.setUpdateDate(new Date());
		updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
		updateDomain.setPaymentBillStatus(paymentBillStatus);
		paymentBillMapper.updateWithoutNull(updateDomain);
	}

	@Valid
	@Transactional
	public void notifySuccess(@Valid @NotNull("通知参数") NotifyParam notifyParam) {
		PaymentBillDomain paymentBillDomain = loadPaymentBill(notifyParam.getPaymentBillCode());
		if (paymentBillDomain.getTotalAmount().compareTo(notifyParam.getTotalAmount()) != 0) {
			throw new IllegalNotificationRuntimeException();
		}

		if (paymentBillDomain.getPaymentBillStatus() != ConstPay.PaymentBillStatus.WAITING_PAY) {
			throw new PaymentBillStatusIsNotAcceptableRuntimeException();
		}

		/**
		 * 更改支付单状态
		 */
		updatePaymentBillStatus(paymentBillDomain,ConstPay.PaymentBillStatus.SUCCESS);

		/**
		 * 更改订单状态
		 */
		orderService.updateOrderStatus(paymentBillDomain.getOrderCode(), ConstOrder.OrderStatus.PAIED, "支付成功", "支付成功");

	}

	@Valid
	public String pay(@Valid @NotNull("支付参数") PayParam payParam) throws Exception {
		/**
		 * 获取支付信息
		 */
		OrderPayInfo orderPayInfo = orderService.computeOrderPayInfo(payParam.getOrderId());
		/**
		 * 生成支付单 首先插入支付单，如果插入成功，则执行后续的支付逻辑 如果插入失败，验证当前支付单的状态 ，如果支付成功，直接报错。
		 * 如果是等待支付结果返回，则查询该支付单的实际支付结果，根据结果做相应的处理， 如果已支付，修改支付单的状态。
		 * 如果未查到，则更改支付类型，将原支付类型的记录复制到历史表里，并且在历史表里记录删除日期，再继续执行后续的支付逻辑。
		 * 如果等待支付结果返回，则直接报错
		 */
		Date now = new Date();
		String userCode = orderPayInfo.getUserCode();
		PaymentBillDomain paymentBillDomain = new PaymentBillDomain();
		paymentBillDomain.setApplicationCode(orderPayInfo.getApplicationCode());
		paymentBillDomain.setCreateDate(now);
		paymentBillDomain.setCreateUserCode(null); /* 客户生成的支付单 */
		paymentBillDomain.setCurrency(orderPayInfo.getCurrency());
		paymentBillDomain.setId(pkGenService.genPk());
		paymentBillDomain.setOrderBody(orderPayInfo.getBody());
		paymentBillDomain.setOrderCode(orderPayInfo.getOrderCode());
		paymentBillDomain.setOrderDesc(orderPayInfo.getOrderDesc());
		paymentBillDomain.setTotalAmount(orderPayInfo.getTotalAmount());
		paymentBillDomain.setPaymentBillCode(generatePaymentBillCode(now));
		paymentBillDomain.setPaymentBillStatus(ConstPay.PaymentBillStatus.WAITING_PAY);
		paymentBillDomain.setPaymentCategoryCode(payParam.getPaymentCategoryCode());
		paymentBillDomain.setStoreCode(orderPayInfo.getStoreCode());
		paymentBillDomain.setUpdateDate(now);
		paymentBillDomain.setUpdateUserCode(null);
		paymentBillDomain.setUserCode(userCode);

		try {
			paymentBillMapper.insert(paymentBillDomain);
		} catch (DuplicateKeyException ex) {

			MybatisParam params = new MybatisParam().put("applicationCode", paymentBillDomain.getApplicationCode()).put("orderCode",
					paymentBillDomain.getOrderCode());
			PaymentBillDomain olderPaymentBillDomain = paymentBillMapper.loadDynamic(params).get(0);

			if (olderPaymentBillDomain.getPaymentBillStatus() == ConstPay.PaymentBillStatus.FAILURE) {
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

			} else if (olderPaymentBillDomain.getPaymentBillStatus() == ConstPay.PaymentBillStatus.SUCCESS) {
				/**
				 * 支付成功，，直接报错
				 */
				orderService.updateOrderStatus(paymentBillDomain.getOrderCode(), ConstOrder.OrderStatus.PAIED, "支付成功", "支付成功");

				throw new RuntimeException("已经支付成功");
			} else if (olderPaymentBillDomain.getPaymentBillStatus() == ConstPay.PaymentBillStatus.WAITING_PAY) {
				/**
				 * 等待支付结果返回。 调用对应的接口查询，根据查询结果再做处理，记录备注，修改状态，生成新的支付单，等等。
				 * 如果支付目录不变，直接支付，反正是幂等的。
				 */
				throw new RuntimeException("请等待支付返回通知");
			}
		}

		/**
		 * 调用支付接口，给前台返回支付界面
		 */
		String paymentCategoryCode = payParam.getPaymentCategoryCode();
		if (paymentCategoryCode.equals(ConstPay.PayCategoryCode.ALIPAY)) {
			AlipayTradeParam alipayTradeParam = new AlipayTradeParam();
			alipayTradeParam.setBody(orderPayInfo.getBody());
			alipayTradeParam.setOutTradeNo(paymentBillDomain.getPaymentBillCode());
			alipayTradeParam.setSubject(orderPayInfo.getOrderDesc());
			alipayTradeParam.setTotalAmount(orderPayInfo.getTotalAmount());
			return alipayService.wapPay(alipayTradeParam);
		} else if (paymentCategoryCode.equals(ConstPay.PayCategoryCode.WXPAY)) {
			throw new RuntimeException("暂不支持微信支付");
		} else {
			throw new RuntimeException("未知的支付方式");
		}
	}

	/**
	 * 查询等待超过10分钟的支付单
	 * 
	 * @return
	 */
	public List<Integer> queryUnfinishedPaymentBill() {
		return paymentBillExMapper.queryUnfinishedPaymentBill(ConstPay.PaymentBillStatus.WAITING_PAY, DateTools.addMinutes(new Date(), -10), 2000);
	}
	
	@Valid
	public void close(@NotNull PaymentBillDomain paymentBillDomain) throws Exception{
		if (paymentBillDomain.getPaymentCategoryCode().equalsIgnoreCase(ConstPay.PayCategoryCode.ALIPAY)){
			alipayService.close(paymentBillDomain.getPaymentBillCode());
		}else{
			throw new RuntimeException("unsupported paymentCategoryCode="+paymentBillDomain.getPaymentCategoryCode());
		}
	}

	/**
	 * 查询支付的单的支付状态，如果已经支付去修改订单的状态 ，如果支付失败或未支付，修改支付单的状态，
	 * 
	 * @param paymentBillId
	 * @throws Exception
	 */
	@Valid
	@Transactional
	public void checkUnfinishedPayment(@NotNull Integer paymentBillId) throws Exception {
		PaymentBillDomain paymentBillDomain = loadPaymentBill(paymentBillId);

		if (paymentBillDomain.getPaymentBillStatus() == ConstPay.PaymentBillStatus.WAITING_PAY) {

			int hasPaid = hasPaidSuccessfully(paymentBillDomain);

			if (hasPaid == 1) {
				/**
				 * 支付成功
				 */

				/**
				 * 更改支付单状态
				 */
				updatePaymentBillStatus(paymentBillDomain,ConstPay.PaymentBillStatus.SUCCESS);

				/**
				 * 更改订单状态
				 */
				orderService.updateOrderStatus(paymentBillDomain.getOrderCode(), ConstOrder.OrderStatus.PAIED, "支付成功", "支付成功");
			} else if (hasPaid == -1) {
				/**
				 * 支付失败
				 */
				/**
				 * 更改支付单状态
				 */
				updatePaymentBillStatus(paymentBillDomain,ConstPay.PaymentBillStatus.FAILURE);
			}else{
				/**
				 * 等待支付,Do Nothing,客户可能通过支付宝，微信客户端直接查询账单支付，所以只能等待。
				 */
			}
		}
	}
}
