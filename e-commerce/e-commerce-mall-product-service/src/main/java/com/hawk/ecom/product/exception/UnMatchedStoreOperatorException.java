package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class UnMatchedStoreOperatorException extends BasicRuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6618335103259619517L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+10;

	public UnMatchedStoreOperatorException() {
		super(code,ErrorCode.getErrMsg(code));
	}


}
