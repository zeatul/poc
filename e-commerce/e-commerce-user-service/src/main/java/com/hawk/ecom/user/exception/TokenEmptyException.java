package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class TokenEmptyException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1026310513022851970L;
	private final static int  code = 100005;
	

	public TokenEmptyException( ) {
		super(code,ErrorCode.getErrMsg(code));
	}

}
