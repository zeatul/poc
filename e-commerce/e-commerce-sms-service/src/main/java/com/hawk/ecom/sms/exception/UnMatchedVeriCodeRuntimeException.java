package com.hawk.ecom.sms.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class UnMatchedVeriCodeRuntimeException extends BasicRuntimeException{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6249886100744802715L;

	public UnMatchedVeriCodeRuntimeException() {
		super(200003, "验证码不正确");
	}

}
