package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.pub.sql.PageParam;

public class ListCategoryParam implements PageParam{
	public Integer getCategoryVariantStatus() {
		return categoryVariantStatus;
	}

	public void setCategoryVariantStatus(Integer categoryVariantStatus) {
		this.categoryVariantStatus = categoryVariantStatus;
	}

	public Integer getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(Integer categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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
	 * 上级产品目录主键
	 */
	private Integer pid;
	
	/**
	 * 产品目录状态 category_status
	 */
	private Integer categoryStatus;
	
	/**
	 * 是否为最终产品目录分类，最终产品目录分类才能有商品模板 is_leaf
	 */
	private Integer isLeaf;
	
	/**
	 * 最终产品目录变式状态 category_variant_status
	 */
	private Integer categoryVariantStatus;

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
