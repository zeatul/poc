package com.hawk.ecom.product.constant;

public class ConstProduct {
	
	/**
	 * 产品状态
	 * @author zhangpeng.hawk
	 *
	 */
	public static interface ProductStatus{
		public final int EDITING = 1;
		public final int ON_SALE = 100;
		public final int OUT_OF_SALE = 99;
	}
	
	/**
	 * 长度单位
	 * @author zhangpeng.hawk
	 *
	 */
	public static interface LengthUnit {
		
		/**
		 * 毫米
		 */
		public final int MILLIMETER = 1;
	}
	
	/**
	 * 重量单位
	 * @author zhangpeng.hawk
	 *
	 */
	public static interface WeightUnit{
		
		/**
		 * 克
		 */
		public final int GRAM = 1;
	}
	
	/**
	 * 产品SKU状态
	 * @author zhangpeng.hawk
	 *
	 */
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
	
	/**
	 * 库存操作类型
	 * @author zhangpeng.hawk
	 *
	 */
	public static interface StockOperation{
		
		/**
		 * 进货
		 */
		public final int STOCK_IN = 1;
	}
	
	/**
	 * 图片类型
	 * @author zhangpeng.hawk
	 *
	 */
	public static interface PicType{
		public final int NORMAL = 1;
	}
	
	
	/**
	 * 产品交付方式
	 * @author zhangpeng.hawk
	 *
	 */
	public static interface DeliveryType{
		/**
		 * 快递
		 */
		public final int EXPRESS = 1;
		
		
	
		/**
		 * 5000以上的需要额外数据
		 */
		
		/**
		 * 充流量
		 */
		public final int CHARGE_FLOW_DATA = 5000;
		
		
		/**
		 * 碎屏险
		 */
		public final int BSI = 5002;
	}

}
