package com.hawk.ecom.trans.constant;

public class ConstOrder {
	
	public interface PayType{
		/**
		 * 在线支付
		 */
		public final int ON_LINE = 1;
	}
	
	public interface OrderType{
		/**
		 * 客户移动端订单
		 */
		public final int CUSTOMER_ONLINE_MOBILE = 1;
		/**
		 * 客户PC端订单
		 */
		public final int CUSTOMER_ONLINE_PC = 2;
	}
	
	public interface OrderStatus{
		/**
		 * 待付款
		 */
		public final int UNPAIED = 0;
		/**
		 * 已付款
		 */
		public final int PAIED = 1;
		
		/**
		 * 取消
		 */
		public final int CANCELED = 98;
		/**
		 * 失败
		 */
		public final int FAILURE = 99;
		/**
		 * 已完成
		 */
		public final int SUCCESS = 100;
	}
	
	public interface OrderDetailType{
		/**
		 * 普通
		 */
		public final int NORMAL = 1;
		
		/**
		 * 赠品
		 */
		public final int GIFT = 2;
	}
	
	public interface OrderDetailStatus{
		/**
		 * 处理中
		 */
		public final int PROCESSING= 1; 
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

	/**
	 * 任务状态
	 * @author zhangpeng.hawk
	 *
	 */
	public interface TaskStatus{
		
		/**
		 * 未执行
		 */
		public final int UN_EXECUTE = 0;
		
		/**
		 * 执行中
		 */
		public final int EXECUTING = 1;
		
		/**
		 * 执行失败
		 */
		public final int EXECUTE_FAILED = 10;
		/**
		 * 取消
		 */
		public final int CANCEL_TASK = 98; 
		/**
		 * 失败
		 */
		public final int FAILURE_TASK = 99;
		/**
		 * 成功
		 */
		public final int SUCCESS_TASK = 100;
	}
}
