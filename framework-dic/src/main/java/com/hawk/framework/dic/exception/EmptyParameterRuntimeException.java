package com.hawk.framework.dic.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class EmptyParameterRuntimeException extends BasicRuntimeException{
	private static int code = 10001;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9084306067591543242L;

	public EmptyParameterRuntimeException(String name) {
		super(code, name+"不能为空");
	}

}
