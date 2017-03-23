package com.hawk.framework.pub.web;

public interface WebResponse<T extends ResponseData> {
	
	public T getData();
	public int getCode();

}
