package com.hawk.ecom.svp.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class CashCouponNotFoundRuntimeException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7758492666222303758L;
	private final static int  code = 300000;

	
	public CashCouponNotFoundRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
