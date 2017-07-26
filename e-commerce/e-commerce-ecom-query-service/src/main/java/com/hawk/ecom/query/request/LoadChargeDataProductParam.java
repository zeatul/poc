package com.hawk.ecom.query.request;

import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class LoadChargeDataProductParam {
	
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
	@NotEmpty("运营商")
	private String operator;
//	@NotNull
//	private Integer size;
	@NotEmpty("省份")
	private String province;
	@NotEmpty("区域类型")
	private String regionType;

}
