package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class CategoryIsNotLeafRuntimeException extends BasicRuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1635417070737427850L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+4;

	public CategoryIsNotLeafRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
