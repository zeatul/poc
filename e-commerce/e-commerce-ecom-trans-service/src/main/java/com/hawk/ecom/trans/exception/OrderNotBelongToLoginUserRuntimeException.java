package com.hawk.ecom.trans.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class OrderNotBelongToLoginUserRuntimeException extends BasicRuntimeException {

	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3875431958810060473L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+4;

	public OrderNotBelongToLoginUserRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
