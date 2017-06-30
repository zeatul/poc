package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.pub.sql.PageParam;

public class ListProductParam implements PageParam{


	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public Integer getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
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
	 * 产品目录主键 category_id
	 */
	private Integer categoryId;
	
	/**
	 * 产品状态 product_status
	 */
	private Integer productStatus;
	
	/**
	 * 是否为虚拟物品 is_virtual
	 */
	private Integer isVirtual;
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
