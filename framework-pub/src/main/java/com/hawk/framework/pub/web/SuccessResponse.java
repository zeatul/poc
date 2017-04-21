package com.hawk.framework.pub.web;

public class SuccessResponse<T extends ResponseData> implements WebResponse<T > {
	
	private T data;
	
	private int code = 1;

	@Override
	public T getData() {
		return this.data;
	}

	@Override
	public int getCode() {
		return this.code;
	}
	
	private SuccessResponse(T data){
		this.data = data;
	}
	
	public static <T extends ResponseData> SuccessResponse<T> build(T data){
		return new SuccessResponse<T>(data);
	}
	
	public static <T extends ResponseData> SuccessResponse<T> build(){
		return new SuccessResponse<T>(null);
	}

}
