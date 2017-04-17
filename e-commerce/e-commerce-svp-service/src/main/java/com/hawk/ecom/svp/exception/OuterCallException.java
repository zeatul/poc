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
	
	public OuterCallException(Integer code, String message){
		
		super(toStr(code),message);
	}
	
	private static String toStr(Integer code){
		String strCode = null;
		if (code != null){
			strCode = code.toString();
		}
		return strCode;
	}

}
