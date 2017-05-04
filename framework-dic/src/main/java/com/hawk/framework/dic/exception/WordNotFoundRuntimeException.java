package com.hawk.framework.dic.exception;

import com.hawk.framework.pub.exception.BasicRuntimeException;

public class WordNotFoundRuntimeException extends BasicRuntimeException{
	
	private final static int  code = 10000;

	/**
	 * 
	 */
	private static final long serialVersionUID = -7779670785562296759L;

	public WordNotFoundRuntimeException(String wordCode) {
		super(code, ErrorCode.getErrMsg(code)+",code="+wordCode);
	}

}
