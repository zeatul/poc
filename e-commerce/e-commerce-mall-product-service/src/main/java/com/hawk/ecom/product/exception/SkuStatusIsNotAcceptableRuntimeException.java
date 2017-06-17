package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class SkuStatusIsNotAcceptableRuntimeException extends BasicRuntimeException {

	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7768760243215990381L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+12;

	public SkuStatusIsNotAcceptableRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
