package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class AttrNameIsNotUniqueException extends BasicRuntimeException {

	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2485129340592632601L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+27;

	public AttrNameIsNotUniqueException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
