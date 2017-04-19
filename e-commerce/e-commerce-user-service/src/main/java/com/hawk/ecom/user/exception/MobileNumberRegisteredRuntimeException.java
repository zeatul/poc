package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimException;

public class MobileNumberRegisteredRuntimeException extends BasicRuntimException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4679769313211857373L;

	public MobileNumberRegisteredRuntimeException() {
		super(100000," 手机号已经注册");
	}

}
