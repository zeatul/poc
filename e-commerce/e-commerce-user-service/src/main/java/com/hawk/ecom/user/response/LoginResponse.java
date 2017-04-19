package com.hawk.ecom.user.response;

import com.hawk.framework.pub.web.ResponseData;

public class LoginResponse implements ResponseData {
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token;

}
