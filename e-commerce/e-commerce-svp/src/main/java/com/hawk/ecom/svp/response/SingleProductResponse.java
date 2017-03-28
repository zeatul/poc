package com.hawk.ecom.svp.response;

import java.math.BigDecimal;

import com.hawk.framework.pub.web.ResponseData;

public class SingleProductResponse implements ResponseData{
	
	public Long getBsiProductId() {
		return bsiProductId;
	}



	public void setBsiProductId(Long bsiProductId) {
		this.bsiProductId = bsiProductId;
	}



	public String getBsiProductName() {
		return bsiProductName;
	}



	public void setBsiProductName(String bsiProductName) {
		this.bsiProductName = bsiProductName;
	}



	public Integer getBsiProductValidPeriod() {
		return bsiProductValidPeriod;
	}



	public void setBsiProductValidPeriod(Integer bsiProductValidPeriod) {
		this.bsiProductValidPeriod = bsiProductValidPeriod;
	}



	public BigDecimal getBsiDisplayPrice() {
		return bsiDisplayPrice;
	}



	public void setBsiDisplayPrice(BigDecimal bsiDisplayPrice) {
		this.bsiDisplayPrice = bsiDisplayPrice;
	}



	/**
	 * 碎屏险产品ID bsi_product_id
	 */
	private Long bsiProductId;
	
	/**
	 * 产品名称 bsi_product_name
	 */
	private String bsiProductName;
	
	/**
	 * 有效期(月) bsi_product_valid_period
	 */
	private Integer bsiProductValidPeriod;
	
	
	
	/**
	 * 显示价格 bsi_display_price
	 */
	private BigDecimal bsiDisplayPrice;

}
