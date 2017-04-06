package com.hawk.framework.dic.validation;

import com.hawk.framework.pub.exception.BasicException;

public class ValidateException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9084306067591543242L;

	public ValidateException(String code, String message) {
		super(code, message);
	}

}
