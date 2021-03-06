package com.hawk.ecom.product.response;

import java.math.BigDecimal;

import com.hawk.framework.pub.web.ResponseData;

public class SkuInfoResponse implements ResponseData{


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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
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

	public String getSkuAttrValueIds() {
		return skuAttrValueIds;
	}

	public void setSkuAttrValueIds(String skuAttrValueIds) {
		this.skuAttrValueIds = skuAttrValueIds;
	}

	public String getSkuAttrValueValues() {
		return skuAttrValueValues;
	}

	public void setSkuAttrValueValues(String skuAttrValueValues) {
		this.skuAttrValueValues = skuAttrValueValues;
	}

	public Integer getSkuStatus() {
		return skuStatus;
	}

	public void setSkuStatus(Integer skuStatus) {
		this.skuStatus = skuStatus;
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

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Integer getSkuStockAmount() {
		return skuStockAmount;
	}

	public void setSkuStockAmount(Integer skuStockAmount) {
		this.skuStockAmount = skuStockAmount;
	}

	public Integer getIsSpecialPrice() {
		return isSpecialPrice;
	}

	public void setIsSpecialPrice(Integer isSpecialPrice) {
		this.isSpecialPrice = isSpecialPrice;
	}

	public String getSkuMemo() {
		return skuMemo;
	}

	public void setSkuMemo(String skuMemo) {
		this.skuMemo = skuMemo;
	}

	public Integer getSkuSnapshootId() {
		return skuSnapshootId;
	}

	public void setSkuSnapshootId(Integer skuSnapshootId) {
		this.skuSnapshootId = skuSnapshootId;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	

	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 产品主键 product_id
	 */
	private Integer productId;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * SKU编号 sku_code
	 */
	private String skuCode;
	
	/**
	 * SKU名称 sku_name
	 */
	private String skuName;
	
	/**
	 * SKU属性值ID集合 sku_attr_value_ids
	 */
	private String skuAttrValueIds;
	
	/**
	 * SKU属性值集合 sku_attr_value_values
	 */
	private String skuAttrValueValues;
	
	/**
	 * SKU状态 sku_status
	 */
	private Integer skuStatus;
	
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
	 * SKU库存数量 sku_stock_amount
	 */
	private Integer skuStockAmount;
	
	/**
	 * 是否有特价 is_special_price
	 */
	private Integer isSpecialPrice;
	
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
//	 * 高度 height
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
	 * SKU快照ID sku_snapshoot_id
	 */
	private Integer skuSnapshootId;
	
//	/**
//	 * 产品版本号 product_version
//	 */
//	private Integer productVersion;
	
	/**
	 * 缩略图 thumbnail
	 */
	private String thumbnail;
	
//	/**
//	 * sku版本号 sku_version
//	 */
//	private Integer skuVersion;
	
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
	
}
