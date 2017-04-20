package com.hawk.ecom.user.exception;

import com.hawk.framework.pub.exception.BasicRuntimException;

public class UnMatchedVeriCodeException extends BasicRuntimException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 680466207820870766L;

	public UnMatchedVeriCodeException() {
		super(100003, "验证码不正确");
	}

}
