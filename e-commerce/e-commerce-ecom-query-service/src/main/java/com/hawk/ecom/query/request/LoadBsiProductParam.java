package com.hawk.ecom.query.request;

import com.hawk.framework.dic.validation.annotation.NotNull;

public class LoadBsiProductParam {
	
	

	public Integer getBsiPhoneModelId() {
		return bsiPhoneModelId;
	}

	public void setBsiPhoneModelId(Integer bsiPhoneModelId) {
		this.bsiPhoneModelId = bsiPhoneModelId;
	}

	/**
	 * 手机型号ID
	 */
	@NotNull
	private Integer bsiPhoneModelId;	
	

}
