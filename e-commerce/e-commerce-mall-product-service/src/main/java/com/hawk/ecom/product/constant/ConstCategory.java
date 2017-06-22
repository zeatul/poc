package com.hawk.ecom.product.constant;

public class ConstCategory {
	
	public static interface CategoryStatus{
		
		/**
		 * 禁用
		 */
		public final int FORBIDDEN = 99;
		/**
		 * 发布状态
		 */
		public final int AVAILABLE = 100;		
		
		
	}
	
	public static interface CategoryVariantStatus extends CategoryStatus{
		
		/**
		 * 编辑
		 */
		
		public final int EDITING = 1;
		
		
	}

}
