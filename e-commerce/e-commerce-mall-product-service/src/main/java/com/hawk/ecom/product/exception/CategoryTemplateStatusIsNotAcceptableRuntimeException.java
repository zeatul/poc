package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class CategoryTemplateStatusIsNotAcceptableRuntimeException extends BasicRuntimeException {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4053861197589336293L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+15;

	public CategoryTemplateStatusIsNotAcceptableRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
