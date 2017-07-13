package com.hawk.ecom.query.request;

import com.hawk.framework.dic.validation.annotation.NotNull;

public class LoadChargeDataProductParam {
	
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRegionType() {
		return regionType;
	}
	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}
	@NotNull
	private String operator;
	@NotNull
	private Integer size;
	@NotNull
	private String province;
	@NotNull
	private String regionType;

}
