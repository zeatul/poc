package com.hawk.ecom.svp.request;

import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class QueryModelOfBrandParam {
	
	

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@NotEmpty("bsi_phone_brand")
	private String brand;

}
