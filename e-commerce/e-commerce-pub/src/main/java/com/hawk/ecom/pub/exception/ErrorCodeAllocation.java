package com.hawk.ecom.pub.exception;

public class ErrorCodeAllocation {
//	framework < 100000
//	sms  200000
//	svp  300000
//	mall 400000
	
	public final static int FRAMEWORK_DIC = 10000;
	
	public final static int USER = 110000;
	public final static int SMS = 120000;
	public final static int SVP = 130000;
	
	public final static int MALL_USER  = 400000;
	public final static int MALL_ADMIN = 410000;	
	public final static int PRODUCT = 420000;
	public final static int TRANSACTION = 430000;
	public final static int ECOM_QUERY = 440000;
	public final static int PAY = 450000;

}
