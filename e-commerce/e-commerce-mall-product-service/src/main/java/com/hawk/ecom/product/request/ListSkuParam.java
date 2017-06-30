package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.pub.sql.PageParam;

public class ListSkuParam implements PageParam{


	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getSkuStatus() {
		return skuStatus;
	}

	public void setSkuStatus(Integer skuStatus) {
		this.skuStatus = skuStatus;
	}

	public Integer getIsSpecialPrice() {
		return isSpecialPrice;
	}

	public void setIsSpecialPrice(Integer isSpecialPrice) {
		this.isSpecialPrice = isSpecialPrice;
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
	 * 产品主键 product_id
	 */
	private Integer productId;	
	
	/**
	 * SKU状态 sku_status
	 */
	private Integer skuStatus;	
	
	/**
	 * 是否有特价 is_special_price
	 */
	private Integer isSpecialPrice;
	
	

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
