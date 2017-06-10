package com.hawk.ecom.mall.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallUserNotLoginRuntimeException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -819356843163965762L;
	private final static int  code = ErrorCodeAllocation.MALL+6;
	
	public MallUserNotLoginRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
