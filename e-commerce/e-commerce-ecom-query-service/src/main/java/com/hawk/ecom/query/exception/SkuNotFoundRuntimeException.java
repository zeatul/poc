package com.hawk.ecom.query.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class SkuNotFoundRuntimeException extends BasicRuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5455719263161132847L;
	private final static int  code = ErrorCodeAllocation.ECOM_QUERY+1;

	public SkuNotFoundRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}
}
