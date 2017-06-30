package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.pub.sql.PageParam;

public class ListStockParam implements PageParam{

	
	
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

	public Integer getStockOperation() {
		return stockOperation;
	}

	public void setStockOperation(Integer stockOperation) {
		this.stockOperation = stockOperation;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getPageRowCount() {
		return pageRowCount;
	}

	public void setPageRowCount(Integer pageRowCount) {
		this.pageRowCount = pageRowCount;
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
	 * 库存操作类型 stock_operation
	 */
	private Integer stockOperation;

	/**
	 * 页码
	 */
	private Integer pageIndex;
	
	/**
	 * 排序
	 */
	private String order;
	
	/**
	 * 每页数量
	 */
	private Integer pageRowCount;

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
