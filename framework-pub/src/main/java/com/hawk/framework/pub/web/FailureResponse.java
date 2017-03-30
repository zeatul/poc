package com.hawk.framework.pub.web;

public class FailureResponse<T extends ResponseData> implements WebResponse<T > {
	
	private T data;
	
	private int code = 0;

	@Override
	public T getData() {
		return this.data;
	}

	@Override
	public int getCode() {
		return this.code;
	}
	
	private FailureResponse(T data){
		this.data = data;
	}
	
	public static <T extends ResponseData> FailureResponse<T> build(T data){
		return new FailureResponse<T>(data);
	}

}
