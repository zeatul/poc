package com.hawk.framework.pub.exception;

public class BasicRuntimeException extends RuntimeException implements Error{

	/**
	 * 
	 */
	private static final long serialVersionUID = 405559310327867242L;
	
	public BasicRuntimeException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	private int code;
	private String message;

}
