package com.hawk.ecom.trans.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class OrderDetailDeliveryDataNotFoundRuntimeException extends BasicRuntimeException {

	
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3137963886738685661L;
	
	private final static int  code = ErrorCodeAllocation.TRANSACTION+8;

	public OrderDetailDeliveryDataNotFoundRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
