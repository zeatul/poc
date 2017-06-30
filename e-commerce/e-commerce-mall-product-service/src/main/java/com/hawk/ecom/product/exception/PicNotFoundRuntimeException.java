package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class PicNotFoundRuntimeException extends BasicRuntimeException {

	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7217401148254442610L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+734;

	public PicNotFoundRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
