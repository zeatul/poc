package com.hawk.ecom.product.constant;

public class ConstAttr {
	
	/**
	 * 属性名业务功能分类(品牌,供应商,其它) attr_name_business_type
	 * @author Administrator
	 *
	 */
	public static interface AttrNameBusinessType{
		
		/**
		 * 品牌
		 */
		public static int BRAND = 1;
		/**
		 * 供应商
		 */
		public static int SUPPLIER = 2;
		
		/**
		 * 规格
		 */
		public static int SPECIFICATION = 3;
		
		/**
		 * 其它
		 */
		public static int OTHERS = 999;
	}
	
	/**
	 * 属性类型（关键属性,销售属性,一般属性） attr_name_type
	 * @author Administrator
	 *
	 */
	public static interface AttrNameType{
		/**
		 * 关键属性
		 */
		public static int KEY_ATTR = 1;
		
		
		/**
		 * 普通描述属性
		 */
		public static int NORMAL_DESC_ATTR = 2;
		
		/**
		 * 销售属性SKU
		 */
		public static int SKU_ATTR = 9;
	}
	
	
	/**
	 * 属性值类型 attr_value_type
	 * @author Administrator
	 *
	 */
	public static interface AttrValueType{
		
		/**
		 * 字符串
		 */
		public static int STRING = 1;
		/**
		 * 整型
		 */
		public static int INTEGER = 2;
		/**
		 * 数值型
		 */
		public static int DECIMAL = 3;
		
		/**
		 * 颜色
		 */
		public static int COLOR = 4;
	}
	
	public static interface AttrNameStatus{
		
		/**
		 * 禁用
		 */
		public static int FORBIDDEN = 0;
		/**
		 * 启用
		 */
		public static int AVAIlABLE = 1;
	}
	
	public static interface AttrValueStatus extends AttrNameStatus{
		
	}
	
	public static interface AttrNameCodeForChargeData{
		/**
		 * 运营商
		 */
		public static String OPERATOR = "mobile_operator";
		
		/**
		 * 流量大小
		 */
		public static String SIZE = "charge_data_size";
		
		/**
		 * 号码所在省份
		 */
		public static String PROVINCE = "charge_data_province";
		
		/**
		 * 充值类型，省内，省漫，全国
		 */
		public static String REGION_TYPE = "charge_data_region_type";
	}

}
