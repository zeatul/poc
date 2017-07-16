package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class SkuWasSoldRuntimeException extends BasicRuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3749352275893784750L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+37;

	public SkuWasSoldRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
