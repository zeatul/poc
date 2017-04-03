package com.hawk.framework.pub.exception;

public abstract class BasicException extends Exception implements Error{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3188534024400225388L;
	public BasicException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	private String code;
	private String message;

}
