package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class TokenInvalidRuntimeException extends BasicRuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8305675771425231231L;
	private final static int  code = 100006;
	
	public TokenInvalidRuntimeException() {
		super(code,ErrorCode.getErrMsg(code));
	}

	
}
