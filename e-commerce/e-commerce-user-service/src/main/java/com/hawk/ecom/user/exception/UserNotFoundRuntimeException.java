package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimException;

public class UserNotFoundRuntimeException extends BasicRuntimException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9132375104783197083L;

	public UserNotFoundRuntimeException() {
		super(100001, "用户不存在");
		
	}

}
