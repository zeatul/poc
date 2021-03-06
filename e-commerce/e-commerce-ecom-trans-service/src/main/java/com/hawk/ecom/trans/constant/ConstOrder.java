package com.hawk.ecom.trans.constant;

public class ConstOrder {
	
	/**
	 * 订单明细处理状态
	 * @author zhangpeng.hawk
	 *
	 */
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
	 * 订单明细交付状态
	 * @author zhangpeng.hawk
	 *
	 */
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
	
	/**
	 * 支付方式
	 * @author zhangpeng.hawk
	 *
	 */
	public interface PayType{
		/**
		 * 在线支付
		 */
		public final int ON_LINE = 1;
	}
	
	/**
	 * 订单类型
	 * @author zhangpeng.hawk
	 *
	 */
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
	
	/**
	 * 订单状态
	 * @author zhangpeng.hawk
	 *
	 */
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
		 * 关闭
		 */
		public final int CLOSED = 97;
		
		/**
		 * 取消
		 */
		public final int CANCELED = 98;
		/**
		 * 失败
		 */
		public final int FAILURE = 99;
		/**
		 * 已成功完成
		 */
		public final int SUCCESS = 100;
		
		
	}
	
	/**
	 * 订单明细类型
	 * @author zhangpeng.hawk
	 *
	 */
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
	
	

	/**
	 *  交付作业任务状态
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
