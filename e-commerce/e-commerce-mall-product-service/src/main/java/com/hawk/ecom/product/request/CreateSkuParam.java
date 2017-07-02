package com.hawk.ecom.product.request;

import java.math.BigDecimal;
import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class CreateSkuParam {
	
	
	

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
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

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public List<Integer> getSkuAttrValueIds() {
		return skuAttrValueIds;
	}

	public void setSkuAttrValueIds(List<Integer> skuAttrValueIds) {
		this.skuAttrValueIds = skuAttrValueIds;
	}

	public String getSkuMemo() {
		return skuMemo;
	}

	public void setSkuMemo(String skuMemo) {
		this.skuMemo = skuMemo;
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
	private Integer width = 0;
	
	/**
	 * 深度 depth
	 */
	@NotNull
	private Integer depth = 0;
	
	/**
	 * 高度 heigh
	 */
	@NotNull
	private Integer height = 0;
	
	/**
	 * 长度单位 length_unit
	 */
	@NotNull
	private Integer lengthUnit = ConstProduct.LengthUnit.MILLIMETER;
	
	/**
	 * 重量 weight
	 */
	@NotNull
	private Integer weight = 0;
	
	/**
	 * 重量单位 weight_unit
	 */
	@NotNull
	private Integer weightUnit = ConstProduct.WeightUnit.GRAM;
	
	/**
	 * SKU备注 sku_memo
	 */
	private String skuMemo;	
	
	
	/**
	 * sku属性值集合
	 */
	List<Integer> skuAttrValueIds;
	
	/**
	 * 市场价 market_price
	 */
	private BigDecimal marketPrice;
	
	/**
	 * 销售价 sale_price
	 */
	private BigDecimal salePrice;
	
	/**
	 * 币种 currency
	 */
	private Integer currency;
	
	/**
	 * 缩略图 thumbnail
	 */
	private String thumbnail;
	
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
