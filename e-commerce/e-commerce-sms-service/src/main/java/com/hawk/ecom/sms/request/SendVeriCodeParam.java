package com.hawk.ecom.sms.request;

import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class SendVeriCodeParam {
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@NotEmpty("mobileNumber")
	private String mobileNumber;

}
