package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class TokenEmptyRuntimeException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1026310513022851970L;
	private final static int  code = 100005;
	

	public TokenEmptyRuntimeException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
