package com.hawk.ecom.product.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class LackOfSkuAttrNameOfProductRuntimeException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8682973807675832185L;
	private final static int  code = ErrorCodeAllocation.PRODUCT+30;
	public LackOfSkuAttrNameOfProductRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
