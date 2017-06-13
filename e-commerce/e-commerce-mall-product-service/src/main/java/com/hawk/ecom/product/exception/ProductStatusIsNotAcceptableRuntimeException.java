package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class ProductStatusIsNotAcceptableRuntimeException extends BasicRuntimeException {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4053861197589336293L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+8;

	public ProductStatusIsNotAcceptableRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
