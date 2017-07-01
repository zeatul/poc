package com.hawk.ecom.trans.request;

import com.hawk.framework.dic.validation.annotation.NotEmpty;

/**
 * 充流量
 * 充话费
 * @author zhangpeng.hawk
 *
 */
public class ChargeMobileParam {
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@NotEmpty
	private String mobileNumber;

}
