package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class DuplicateAttrValueRuntimeException extends BasicRuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2597257678688383306L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+20;

	public DuplicateAttrValueRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
