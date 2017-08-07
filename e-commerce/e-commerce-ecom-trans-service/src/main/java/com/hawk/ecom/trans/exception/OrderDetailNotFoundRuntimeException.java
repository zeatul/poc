package com.hawk.ecom.trans.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class OrderDetailNotFoundRuntimeException extends BasicRuntimeException {

	
	
	


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7364576911124534251L;
	private final static int  code = ErrorCodeAllocation.TRANSACTION+10;

	public OrderDetailNotFoundRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
