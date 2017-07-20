package com.hawk.ecom.pub.constant;

public class ConstOrder {
	
	
	
	public interface DeliveryStatus{
		/*
		 * 未执行
		 */
		public final int UN_EXECUTE = 0;
		
		/**
		 * 处理中
		 */
		public final int PROCESSING = 1;
		/**
		 * 取消
		 */
		public final int CANCELED = 98; 
		/**
		 * 失败
		 */
		public final int FAILURE = 99;
		/**
		 * 成功
		 */
		public final int SUCCESS = 100;
	}
}
