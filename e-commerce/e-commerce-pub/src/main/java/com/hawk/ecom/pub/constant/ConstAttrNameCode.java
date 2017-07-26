package com.hawk.ecom.pub.constant;

public class ConstAttrNameCode {

	public static interface Mobile {

		/**
		 * 运营商
		 */
		public static String OPERATOR = "mobile_operator";

		/**
		 * 流量大小
		 */
		public static String DATA_SIZE = "mobile_data_size";

		/**
		 * 号码所在省份
		 */
		public static String PROVINCE = "mobile_province";

		/**
		 * 充值类型，省内，省漫，全国
		 */
		public static String REGION_TYPE = "mobile_region_type";
	}

	public static interface Bsi {

		/**
		 * 保险月数
		 */
		public static String INSURANCE_PERIOD_MONTH = "bsi_insurance_period_month";

		/**
		 * 保险档次
		 */
		public static String GRADE = "bsi_grade";
	}
}