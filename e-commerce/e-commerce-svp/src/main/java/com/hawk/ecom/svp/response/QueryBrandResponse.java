package com.hawk.ecom.svp.response;

import java.util.List;

import com.hawk.framework.pub.web.ResponseData;

public class QueryBrandResponse implements ResponseData{
	
	public List<String> getBrands() {
		return brands;
	}

	public void setBrands(List<String> brands) {
		this.brands = brands;
	}

	private List<String> brands;

}
