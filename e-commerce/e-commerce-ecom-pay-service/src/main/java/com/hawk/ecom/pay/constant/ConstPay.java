package com.hawk.ecom.pay.constant;

import java.util.UUID;

public class ConstPay {
	
	public static interface PaymentBillStatus {
		
		/**
		 * 等待支付
		 */
		public final int WAITING_PAY = 0;
		
		
		
		/**
		 * 没有支付成功
		 */
		public final int FAILURE = 99;
		
		/**
		 * 支付成功
		 */
		public final int SUCCESS = 100;
	}
	
	public static interface PayCategoryCode{
		
		/**
		 * 支付宝手机网页支付
		 */
		public final String  ALIPAY = "alipay";
		
		/**
		 * 微信手机网页支付
		 */
		public final String  WXPAY = "wxpay";
	}
	
	public static interface AlipayTradeStatus{
//		交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
		public final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
		public final String TRADE_CLOSED = "TRADE_CLOSED";
		public final String TRADE_SUCCESS = "TRADE_SUCCESS";
		public final String TRADE_FINISHED = "TRADE_FINISHED";
	}
	
	public static void main(String[] args){
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
	}

}
