package com.hawk.ecom.product.request;

import java.math.BigDecimal;
import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class UpdateSkuParam {
	


	public String getSkuExtra1() {
		return skuExtra1;
	}

	public void setSkuExtra1(String skuExtra1) {
		this.skuExtra1 = skuExtra1;
	}

	public String getSkuExtra2() {
		return skuExtra2;
	}

	public void setSkuExtra2(String skuExtra2) {
		this.skuExtra2 = skuExtra2;
	}

	public String getSkuExtra3() {
		return skuExtra3;
	}

	public void setSkuExtra3(String skuExtra3) {
		this.skuExtra3 = skuExtra3;
	}

	public String getSkuExtra4() {
		return skuExtra4;
	}

	public void setSkuExtra4(String skuExtra4) {
		this.skuExtra4 = skuExtra4;
	}

	public String getSkuExtra5() {
		return skuExtra5;
	}

	public void setSkuExtra5(String skuExtra5) {
		this.skuExtra5 = skuExtra5;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getSkuMemo() {
		return skuMemo;
	}

	public void setSkuMemo(String skuMemo) {
		this.skuMemo = skuMemo;
	}

	public List<Integer> getAddSkuAttrValueIds() {
		return addSkuAttrValueIds;
	}

	public void setAddSkuAttrValueIds(List<Integer> addSkuAttrValueIds) {
		this.addSkuAttrValueIds = addSkuAttrValueIds;
	}

	public List<Integer> getRemoveSkuAttrValueIds() {
		return removeSkuAttrValueIds;
	}

	public void setRemoveSkuAttrValueIds(List<Integer> removeSkuAttrValueIds) {
		this.removeSkuAttrValueIds = removeSkuAttrValueIds;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getIsSpecialPrice() {
		return isSpecialPrice;
	}

	public void setIsSpecialPrice(Integer isSpecialPrice) {
		this.isSpecialPrice = isSpecialPrice;
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
	private Integer id;
	
	
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
//	
//	/**
//	 * 宽度 width
//	 */
//	private Integer width;
//	
//	/**
//	 * 深度 depth
//	 */
//	private Integer depth;
//	
//	/**
//	 * 高度 heigh
//	 */
//	private Integer height;
//	
//	/**
//	 * 长度单位 length_unit
//	 */
//	private Integer lengthUnit;
//	
//	/**
//	 * 重量 weight
//	 */
//	private Integer weight;
//	
//	/**
//	 * 重量单位 weight_unit
//	 */
//	private Integer weightUnit;
	
	/**
	 * SKU备注 sku_memo
	 */
	private String skuMemo;
	
	/**
	 * 新增Sku属性值
	 */
	private List<Integer> addSkuAttrValueIds;
	
	
	/**
	 * 删除Sku属性值
	 */
	private List<Integer> removeSkuAttrValueIds; 
	
	/**
	 * 币种 currency
	 */
	private Integer currency;
	
	/**
	 * 缩略图 thumbnail
	 */
	private String thumbnail;
	
	/**
	 * 是否有特价 is_special_price
	 */
	private Integer isSpecialPrice;
	

	/**
	 * 产品Sku扩展属性1 sku_extra1
	 */
	private String skuExtra1;
	
	/**
	 * 产品Sku扩展属性2 sku_extra2
	 */
	private String skuExtra2;
	
	/**
	 * 产品Sku扩展属性3 sku_extra3
	 */
	private String skuExtra3;
	
	/**
	 * 产品Sku扩展属性4 sku_extra4
	 */
	private String skuExtra4;
	
	/**
	 * 产品Sku扩展属性5 sku_extra5
	 */
	private String skuExtra5;

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
