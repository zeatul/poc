package com.hawk.ecom.svp.constant;

public class ConstCouponParameter {
	
	public final static class CouponType{
		public final String type;
		public final String name;
		public final int period;
		public CouponType(String type, String name, int period) {
			this.type = type;
			this.name = name;
			this.period = period;
		}
	}
	
	public final static class CouopnStatus{
		public final static int UNUSED =  0 ; //未使用
		public final static int  ACTIVVATING = 10; //等待请求小宝
		
		
		
		public final static int OUT_OF_DATE = 90;
		public final static int ACTIVATE_FAILED = 99;
		public final static int ACTIVATE_SUCCESS = 100;
	}
	
	public final static CouponType REGISTER_PRESENT_COUPON = new CouponType("CPT0001","一个月免费代金券",1);

}
