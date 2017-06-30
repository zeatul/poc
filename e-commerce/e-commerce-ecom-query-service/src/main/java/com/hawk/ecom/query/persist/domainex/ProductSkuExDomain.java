package com.hawk.ecom.query.persist.domainex;

import java.math.BigDecimal;

import com.hawk.framework.pub.web.ResponseData;

public class ProductSkuExDomain implements ResponseData{
	
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getSkuStockQuantity() {
		return skuStockQuantity;
	}

	public void setSkuStockQuantity(Integer skuStockQuantity) {
		this.skuStockQuantity = skuStockQuantity;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * sku主键 skuId
	 */
	private Integer skuId;
	
	/**
	 * 产品主键 product_id
	 */
	private Integer productId;		
	
	/**
	 * SKU名称 sku_name
	 */
	private String skuName;		
	
	/**
	 * 缩略图 thumbnail
	 */
	private String thumbnail;
	
	
	/**
	 * 销售价 sale_price
	 */
	private BigDecimal salePrice;
	
	
	/**
	 * SKU库存数量 sku_stock_quantity
	 */
	private Integer skuStockQuantity;
	
	
	
	
	
}