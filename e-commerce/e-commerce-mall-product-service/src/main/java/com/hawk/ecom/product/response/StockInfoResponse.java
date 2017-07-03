package com.hawk.ecom.product.response;

import com.hawk.framework.pub.web.ResponseData;

public class StockInfoResponse implements ResponseData{
	
	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
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

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getStockItemCode() {
		return stockItemCode;
	}

	public void setStockItemCode(String stockItemCode) {
		this.stockItemCode = stockItemCode;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getStockMemo() {
		return stockMemo;
	}

	public void setStockMemo(String stockMemo) {
		this.stockMemo = stockMemo;
	}

	public Integer getStockOperation() {
		return stockOperation;
	}

	public void setStockOperation(Integer stockOperation) {
		this.stockOperation = stockOperation;
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
	 * 产品SKU主键 sku_id
	 */
	private Integer skuId;
	
	/**
	 * 仓库编号 warehouse_code
	 */
	private String warehouseCode;
	
	/**
	 * 仓库货物编号 stock_item_code
	 */
	private String stockItemCode;
	
	/**
	 * 库存数量 stock_quantity
	 */
	private Integer stockQuantity;
	
	/**
	 * 备注 stock_memo
	 */
	private String stockMemo;
	
	/**
	 * 库存操作类型 stock_operation
	 */
	private Integer stockOperation;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * SKU名称 sku_name
	 */
	private String skuName;

}
