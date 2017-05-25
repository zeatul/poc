package com.hawk.ecom.mall.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class IllegalAccessRuntimeException extends BasicRuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -323330740975789069L;
	private final static int  code = ErrorCodeAllocation.MALL+8;
	
	public IllegalAccessRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}
}
