package com.hawk.ecom.trans.request;

import com.hawk.framework.dic.validation.annotation.Min;
import com.hawk.framework.dic.validation.annotation.NotNull;


public class OrderDetailParam {

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public Integer getSkuQuantity() {
		return skuQuantity;
	}

	public void setSkuQuantity(Integer skuQuantity) {
		this.skuQuantity = skuQuantity;
	}

	@NotNull
	private Integer skuId;
	
	@NotNull
	@Min(minValue="0")
	private Integer skuQuantity;
	
	
}
