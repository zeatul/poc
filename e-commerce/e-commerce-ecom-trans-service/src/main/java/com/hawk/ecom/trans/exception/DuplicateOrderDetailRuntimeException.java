package com.hawk.ecom.trans.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class DuplicateOrderDetailRuntimeException extends BasicRuntimeException {

	
	
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1126863810168310200L;
	private final static int  code = ErrorCodeAllocation.TRANSACTION+9;

	public DuplicateOrderDetailRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
