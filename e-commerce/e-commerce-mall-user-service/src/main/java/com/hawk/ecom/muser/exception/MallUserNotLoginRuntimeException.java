package com.hawk.ecom.muser.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallUserNotLoginRuntimeException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -819356843163965762L;
	private final static int  code = ErrorCodeAllocation.MALL_USER+6;
	
	public MallUserNotLoginRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
