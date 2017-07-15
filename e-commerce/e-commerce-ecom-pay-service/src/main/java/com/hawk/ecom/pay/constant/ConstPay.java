package com.hawk.ecom.pay.constant;

public class ConstPay {
	
	public static interface PaymentBillStatus {
		
		/**
		 * 等待支付
		 */
		public final int WAITING = 0;
		
		
		/**
		 * 等待支付
		 */
		public final int UNPAY = 98;
		
		/**
		 * 支付失败
		 */
		public final int FAILURE = 99;
		
		/**
		 * 支付成功
		 */
		public final int SUCCESS = 100;
	}

}
