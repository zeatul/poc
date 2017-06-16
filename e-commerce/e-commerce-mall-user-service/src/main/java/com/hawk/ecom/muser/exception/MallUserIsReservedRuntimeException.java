package com.hawk.ecom.muser.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallUserIsReservedRuntimeException extends BasicRuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1144384894316900890L;
	private final static int  code = ErrorCodeAllocation.MALL_USER+11;
	
	public MallUserIsReservedRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
