package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.ValidCollection;

public class CreateSkuParam {
	
	
	public String getSkuMemo() {
		return skuMemo;
	}

	public void setSkuMemo(String skuMemo) {
		this.skuMemo = skuMemo;
	}

	public List<CreateProductAttrParam> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<CreateProductAttrParam> attrs) {
		this.attrs = attrs;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer  productId) {
		this.productId = productId;
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

	

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	/**
	 * 产品主键 product_id
	 */
	@NotNull
	private Integer productId;
	
	/**
	 * SKU编号 sku_code
	 */
	@NotEmpty
	private String skuCode;
	
	/**
	 * SKU名称 sku_name
	 */
	@NotEmpty
	private String skuName;	
	
	
	/**
	 * 宽度 width
	 */
	@NotNull
	private Integer width;
	
	/**
	 * 深度 depth
	 */
	@NotNull
	private Integer depth;
	
	/**
	 * 高度 heigh
	 */
	@NotNull
	private Integer heigh;
	
	/**
	 * 长度单位 length_unit
	 */
	@NotNull
	private Integer lengthUnit;
	
	/**
	 * 重量 weight
	 */
	@NotNull
	private Integer weight;
	
	/**
	 * 重量单位 weight_unit
	 */
	@NotNull
	private Integer weightUnit;
	
	/**
	 * SKU备注 sku_memo
	 */
	private String skuMemo;	
	
	@ValidCollection
	List<CreateProductAttrParam> attrs;
	
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
