package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class CategoryHasChildRuntimeException extends BasicRuntimeException{


	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1565645743486964006L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+3;
	
	public CategoryHasChildRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
