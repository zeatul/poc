package com.hawk.ecom.pay.constant;

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

}
