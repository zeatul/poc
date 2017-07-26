package com.hawk.ecom.pay.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class PaymentBillStatusIsNotAcceptableRuntimeException extends BasicRuntimeException {

	
	
	


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2484810546956230600L;
	private final static int  code = ErrorCodeAllocation.PAY+2;

	public PaymentBillStatusIsNotAcceptableRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
