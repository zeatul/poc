package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class DuplicateCategoryRuntimeException extends BasicRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1572295537370376658L;
	
	private final static int  code = ErrorCodeAllocation.PRODUCT+1;

	public DuplicateCategoryRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
