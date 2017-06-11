package com.hawk.ecom.muser.response;

import com.hawk.framework.pub.web.ResponseData;

public class MallLoginResponse implements ResponseData{
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token;
}
