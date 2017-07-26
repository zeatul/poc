package com.hawk.ecom.pay.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class PaymentBillNotFoundRuntimeException extends BasicRuntimeException {

	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 965113648502129691L;
	private final static int  code = ErrorCodeAllocation.PAY+0;

	public PaymentBillNotFoundRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
