package com.hawk.ecom.svp.request;

import com.hawk.framework.dic.validation.annotation.NotNull;

public class QueryProductParam {
	
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	@NotNull("bsi_phone_model_id")
	private Integer modelId;
	
	@NotNull("bsi_product_valid_period")
	private Integer period;

}
