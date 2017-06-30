package com.hawk.ecom.product.constant;

public class ConstProduct {
	
	public static interface ProductStatus{
		public final int EDITING = 1;
		public final int ON_SALE = 100;
		public final int OUT_OF_SALE = 99;
	}
	
	public static interface LengthUnit {
		
		/**
		 * 毫米
		 */
		public final int MILLIMETER = 1;
	}
	
	public static interface WeightUnit{
		
		/**
		 * 克
		 */
		public final int GRAM = 1;
	}
	
	public static interface SkuStatus extends ProductStatus{
	}
	
	/**
	 * 币种
	 * @author zhangpeng.hawk
	 *
	 */
	public static interface Currency{
		public final int RMB = 156;
	}
	
	public static interface StockOperation{
		
		/**
		 * 进货
		 */
		public final int STOCK_IN = 1;
	}
	
	/**
	 * 
	 * @author zhangpeng.hawk
	 *
	 */
	public static interface PicType{
		public final int NORMAL = 1;
	}
	
	public static interface DeliveryType{
		/**
		 * 快递
		 */
		public final int EXPRESS = 1;
		
		/**
		 * 充流量
		 */
		public final int CHARGE = 5000;
		
		/**
		 * 碎屏险
		 */
		public final int BSI = 5001;
	}

}
