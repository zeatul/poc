package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class SkuAttrNameIdsIsNotSameWithProductRuntimeException extends BasicRuntimeException {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2807413836191102335L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+31;

	public SkuAttrNameIdsIsNotSameWithProductRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
