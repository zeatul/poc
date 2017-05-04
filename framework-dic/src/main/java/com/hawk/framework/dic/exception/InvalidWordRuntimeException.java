package com.hawk.framework.dic.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class InvalidWordRuntimeException extends BasicRuntimeException{
	
	private final static int code = 10002;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8413050266307073809L;

	public InvalidWordRuntimeException(String message) {
		
		super(code, message);
	}

}
