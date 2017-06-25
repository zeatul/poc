package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;

public class UpdateStockParam {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	/**
	 * 主键 id
	 */
	private Integer id;

	
	
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
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
