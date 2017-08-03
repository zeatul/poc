package com.hawk.ecom.pay.persist.mapperex;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * table = t_pay_payment_bill
 * desc = 支付单
 * 
 * @author Gen
 */
public interface PaymentBillExMapper  {

	/**
	 * 查询超时未被处理的支付单
	 * @param paymentBillStatus
	 * @param threashold
	 * @param limit
	 * @return
	 */
	public List<Integer> queryUnfinishedPaymentBill(@Param("paymentBillStatus") Integer paymentBillStatus,@Param("threashold") Date threashold,
			@Param("limit") Integer limit);

}