package com.hawk.ecom.query.response;

import com.hawk.framework.pub.web.ResponseData;

public class MobileNumberSegmentResponse implements ResponseData{
	
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getMobileOperatorCode() {
		return mobileOperatorCode;
	}

	public void setMobileOperatorCode(String mobileOperatorCode) {
		this.mobileOperatorCode = mobileOperatorCode;
	}

	public String getMobileOperator() {
		return mobileOperator;
	}

	public void setMobileOperator(String mobileOperator) {
		this.mobileOperator = mobileOperator;
	}

	private String mobileNumber;
	
	
	/**
	 * 省份
	 */
	private String province;
	
	/**
	 * 省级行政区代码 province_code
	 */
	private String provinceCode;
	
	/**
	 * 移动运营商代码 mobile_operator_code
	 */
	private String mobileOperatorCode;
	
	/**
	 * 移动运营商 mobile_operator
	 */
	private String mobileOperator;

}
