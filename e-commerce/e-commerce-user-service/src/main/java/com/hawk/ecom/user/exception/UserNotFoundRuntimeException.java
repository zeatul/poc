package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class UserNotFoundRuntimeException extends BasicRuntimeException{
	private final static int  code = 100001;
	/**
	 * 
	 */
	private static final long serialVersionUID = 9132375104783197083L;

	public UserNotFoundRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
		
	}

}
