package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class CategoryHasDifferentParentRuntimeException extends BasicRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1572295537370376658L;
	
	private final static int  code = ErrorCodeAllocation.PRODUCT+24;

	public CategoryHasDifferentParentRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
