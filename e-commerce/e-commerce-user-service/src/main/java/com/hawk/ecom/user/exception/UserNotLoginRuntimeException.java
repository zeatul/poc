package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class UserNotLoginRuntimeException extends BasicRuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8804272418110321921L;
	private final static int  code = 100007;

	public UserNotLoginRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

}
