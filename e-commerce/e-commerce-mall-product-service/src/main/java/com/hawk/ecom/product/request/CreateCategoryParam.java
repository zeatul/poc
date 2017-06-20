package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

/**
 * 创建产品目录请求参数
 * @author Administrator
 *
 */
public class CreateCategoryParam {
	
	
	
	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
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
	@NotEmpty
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
	 * 是否为最终目录分类，最终目录分类才能有商品模板 is_leaf
	 */
	@NotNull
	private Integer isLeaf;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
	
}
