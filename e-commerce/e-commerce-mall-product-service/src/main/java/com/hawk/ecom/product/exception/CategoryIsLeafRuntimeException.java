package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class CategoryIsLeafRuntimeException extends BasicRuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5119215224526316434L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+2;

	public CategoryIsLeafRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
