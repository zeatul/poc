package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimException;

public class UnMatchedPasswordRuntimeException extends BasicRuntimException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7168722614190918126L;

	public UnMatchedPasswordRuntimeException( ) {
		super(100002, "账号密码不匹配");
		// TODO Auto-generated constructor stub
	}

}
