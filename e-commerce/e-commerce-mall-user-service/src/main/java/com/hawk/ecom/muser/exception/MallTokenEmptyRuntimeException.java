package com.hawk.ecom.muser.exception;

import com.hawk.ecom.pub.exception.ErrorCodeAllocation;
import com.hawk.framework.pub.exception.BasicRuntimeException;

public class MallTokenEmptyRuntimeException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -665138633836117468L;
	private final static int  code = ErrorCodeAllocation.MALL_USER+5;
	
	public MallTokenEmptyRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}
}
