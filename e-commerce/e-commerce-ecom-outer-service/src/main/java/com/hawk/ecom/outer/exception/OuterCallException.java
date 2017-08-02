package com.hawk.ecom.outer.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class OuterCallException extends BasicRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1741258104043038274L;

	public OuterCallException(int code, String message) {
		super(code, message);
	}

}
