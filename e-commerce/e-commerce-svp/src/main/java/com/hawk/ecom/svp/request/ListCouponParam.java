package com.hawk.ecom.svp.request;

import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class ListCouponParam {
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@NotEmpty("mobile_number")
	private String mobileNumber;

}
