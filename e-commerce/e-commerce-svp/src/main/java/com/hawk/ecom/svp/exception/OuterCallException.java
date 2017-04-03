package com.hawk.ecom.svp.exception;

import com.hawk.framework.pub.exception.BasicException;

public class OuterCallException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1927035884999815044L;

	public OuterCallException(String code, String message) {
		super(code, message);
	}

}
