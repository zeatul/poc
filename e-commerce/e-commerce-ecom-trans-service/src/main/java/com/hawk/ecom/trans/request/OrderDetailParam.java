package com.hawk.ecom.trans.request;

import java.util.Map;

import com.hawk.framework.dic.validation.annotation.Min;
import com.hawk.framework.dic.validation.annotation.NotNull;


public class OrderDetailParam {

	public Map<String, Object> getDeliveryData() {
		return deliveryData;
	}

	public void setDeliveryData(Map<String, Object> deliveryData) {
		this.deliveryData = deliveryData;
	}

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
	
	private Map<String,Object> deliveryData;
	
	
}
