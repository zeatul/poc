package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class ProductIsNotAcceptableForOnSaleRuntimeException extends BasicRuntimeException{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 214048516906214721L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+14;

	public ProductIsNotAcceptableForOnSaleRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}
}
