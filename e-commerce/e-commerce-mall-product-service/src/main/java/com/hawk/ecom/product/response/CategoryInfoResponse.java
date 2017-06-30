package com.hawk.ecom.product.response;

import com.hawk.framework.pub.web.ResponseData;

public class CategoryInfoResponse implements ResponseData{
	
	
	

	public Integer getCategoryVariantStatus() {
		return categoryVariantStatus;
	}

	public void setCategoryVariantStatus(Integer categoryVariantStatus) {
		this.categoryVariantStatus = categoryVariantStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer  id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer  pid) {
		this.pid = pid;
	}

	public Integer getObjectOrder() {
		return objectOrder;
	}

	public void setObjectOrder(Integer objectOrder) {
		this.objectOrder = objectOrder;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(Integer categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getCategoryLogo() {
		return categoryLogo;
	}

	public void setCategoryLogo(String categoryLogo) {
		this.categoryLogo = categoryLogo;
	}

	public String getCategoryHomePage() {
		return categoryHomePage;
	}

	public void setCategoryHomePage(String categoryHomePage) {
		this.categoryHomePage = categoryHomePage;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 父ID pid
	 */
	private Integer pid;	
	
	/**
	 * 产品目录序号 object_order
	 */
	private Integer objectOrder;	

	
	/**
	 * 产品目录编号 category_code
	 */
	private String categoryCode;
	
	/**
	 * 产品目录名称 category_name
	 */
	private String categoryName;
	
	/**
	 * 产品目录状态 category_status
	 */
	private Integer categoryStatus;
	
	/**
	 * 产品目录描述 category_desc
	 */
	private String categoryDesc;
	
	/**
	 * 产品目录logo category_logo
	 */
	private String categoryLogo;
	
	/**
	 * 产品目录主页 category_home_page
	 */
	private String categoryHomePage;
	
	/**
	 * 是否为最终目录分类，最终目录分类才能有商品模板 is_leaf
	 */
	private Integer isLeaf;
	
	/**
	 * 最终产品目录变式状态 category_variant_status
	 */
	private Integer categoryVariantStatus;
	
	
}
