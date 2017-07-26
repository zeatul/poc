package com.hawk.ecom.pay.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class IllegalNotificationRuntimeException extends BasicRuntimeException {

	
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1917471131054593786L;
	private final static int  code = ErrorCodeAllocation.PAY+1;

	public IllegalNotificationRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
