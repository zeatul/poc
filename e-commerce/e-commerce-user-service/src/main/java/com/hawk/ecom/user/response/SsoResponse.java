package com.hawk.ecom.user.response;

import com.hawk.framework.pub.web.ResponseData;

public class SsoResponse implements ResponseData {
	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token;
	
	private int expire = 60 * 240 ;
}
