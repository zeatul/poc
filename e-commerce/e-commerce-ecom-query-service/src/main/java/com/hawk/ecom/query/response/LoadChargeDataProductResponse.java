package com.hawk.ecom.query.response;

import java.util.List;

import com.hawk.ecom.query.persist.domainex.ProductSkuExDomain;
import com.hawk.framework.pub.web.ResponseData;

public class LoadChargeDataProductResponse implements ResponseData{
	
	public List<ProductSkuExDomain> getSkus() {
		return skus;
	}

	public void setSkus(List<ProductSkuExDomain> skus) {
		this.skus = skus;
	}

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
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
	
	private String regionType;
	
	/**
	 * 流量产品Sku集合
	 */
	private List<ProductSkuExDomain> skus;

}
