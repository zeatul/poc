package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class DuplicateAttrNameRuntimeException extends BasicRuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2597257678688383306L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+16;

	public DuplicateAttrNameRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
