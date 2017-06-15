package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;

public class UpdateCategoryParam {
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Integer getObjectOrder() {
		return objectOrder;
	}



	public void setObjectOrder(Integer objectOrder) {
		this.objectOrder = objectOrder;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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



	public String getOperatorCode() {
		return operatorCode;
	}



	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}



	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 产品目录序号 object_order
	 */
	private Integer objectOrder;
	
	/**
	 * 产品目录名称 category_name
	 */
	private String categoryName;
	
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
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
