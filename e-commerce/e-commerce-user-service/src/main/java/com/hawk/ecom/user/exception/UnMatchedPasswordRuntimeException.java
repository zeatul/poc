package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class UnMatchedPasswordRuntimeException extends BasicRuntimeException{

	private final static int  code = 100002;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7168722614190918126L;

	public UnMatchedPasswordRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
