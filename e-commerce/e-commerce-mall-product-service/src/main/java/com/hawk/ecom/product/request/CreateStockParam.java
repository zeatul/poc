package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class CreateStockParam {
	
	
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


	public String getOperatorCode() {
		return operatorCode;
	}


	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}


	/**
	 * 产品SKU主键 sku_id
	 */
	@NotNull
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
	@NotNull
	private Integer stockQuantity;
	
	/**
	 * 备注 stock_memo
	 */
	private String stockMemo;
	
	/**
	 * 库存操作类型 stock_operation
	 */
	@NotNull
	private Integer stockOperation;
	
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
