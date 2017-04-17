package com.hawk.ecom.svp.response;

import java.util.List;

import com.hawk.framework.pub.web.ResponseData;

public class MultiBrandResponse  implements ResponseData{
	
	public List<String> getBrands() {
		return brands;
	}

	public void setBrands(List<String> brands) {
		this.brands = brands;
		this.count = brands == null? 0: brands.size();
	}

	private List<String> brands;
	
	private int count = 0;
	
	public MultiBrandResponse(List<String> brands){
		setBrands(brands);
	}
	
	
	/**
	 * 返回记录数量	
	 * @return
	 */
	public Integer getCount(){
		return this.count;
	}

}
