package com.hawk.ecom.base.persist.domainex;

import com.hawk.framework.pub.web.ResponseData;

public class MobileNumberSegmentExDomain {
	
	public String getMobileNumberPrefix() {
		return mobileNumberPrefix;
	}

	public void setMobileNumberPrefix(String mobileNumberPrefix) {
		this.mobileNumberPrefix = mobileNumberPrefix;
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

	/**
	 * 手机号码前7位 mobile_number_prefix
	 */
	private String mobileNumberPrefix;
	
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
