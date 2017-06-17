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

}
