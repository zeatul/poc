package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class CategoryIsDifferentRuntimeException extends BasicRuntimeException{


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3439461963834029480L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+25;
	
	public CategoryIsDifferentRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
