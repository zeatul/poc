package com.hawk.ecom.product.request;

import java.math.BigDecimal;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class UpdateSkuParam {
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getSkuCode() {
		return skuCode;
	}


	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}


	public String getSkuName() {
		return skuName;
	}


	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}


	public BigDecimal getMarketPrice() {
		return marketPrice;
	}


	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}


	public BigDecimal getSalePrice() {
		return salePrice;
	}


	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}


	public Integer getWidth() {
		return width;
	}


	public void setWidth(Integer width) {
		this.width = width;
	}


	public Integer getDepth() {
		return depth;
	}


	public void setDepth(Integer depth) {
		this.depth = depth;
	}


	public Integer getHeigh() {
		return heigh;
	}


	public void setHeigh(Integer heigh) {
		this.heigh = heigh;
	}


	public Integer getLengthUnit() {
		return lengthUnit;
	}


	public void setLengthUnit(Integer lengthUnit) {
		this.lengthUnit = lengthUnit;
	}


	public Integer getWeight() {
		return weight;
	}


	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	public Integer getWeightUnit() {
		return weightUnit;
	}


	public void setWeightUnit(Integer weightUnit) {
		this.weightUnit = weightUnit;
	}


	public String getSkuMemo() {
		return skuMemo;
	}


	public void setSkuMemo(String skuMemo) {
		this.skuMemo = skuMemo;
	}


	public String getOperatorCode() {
		return operatorCode;
	}


	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}


	/**
	 * 主键 id
	 */
	@NotNull
	private Long id;
	
	
	/**
	 * SKU编号 sku_code
	 */
	private String skuCode;
	
	/**
	 * SKU名称 sku_name
	 */
	private String skuName;	

	
	/**
	 * 市场价 market_price
	 */
	private BigDecimal marketPrice;
	
	/**
	 * 销售价 sale_price
	 */
	private BigDecimal salePrice;
	
	/**
	 * 宽度 width
	 */
	private Integer width;
	
	/**
	 * 深度 depth
	 */
	private Integer depth;
	
	/**
	 * 高度 heigh
	 */
	private Integer heigh;
	
	/**
	 * 长度单位 length_unit
	 */
	private Integer lengthUnit;
	
	/**
	 * 重量 weight
	 */
	private Integer weight;
	
	/**
	 * 重量单位 weight_unit
	 */
	private Integer weightUnit;
	
	/**
	 * SKU备注 sku_memo
	 */
	private String skuMemo;
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
