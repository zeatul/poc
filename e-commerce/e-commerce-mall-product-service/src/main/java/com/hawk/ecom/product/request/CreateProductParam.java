package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;

public class CreateProductParam {
	
	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductHomePage() {
		return productHomePage;
	}

	public void setProductHomePage(String productHomePage) {
		this.productHomePage = productHomePage;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductMemo() {
		return productMemo;
	}

	public void setProductMemo(String productMemo) {
		this.productMemo = productMemo;
	}

	public Integer getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
	}

	public List<CreateProductAttrParam> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<CreateProductAttrParam> attrs) {
		this.attrs = attrs;
	}

	/**
	 * 产品目录主键 category_id
	 */
	@NotNull
	private Long categoryId;

	/**
	 * 产品编号 product_code
	 */
	@NotEmpty
	private String productCode;

	/**
	 * 产品名称 product_name
	 */
	@NotEmpty
	private String productName;
	

	/**
	 * 产品主页 product_home_page
	 */
	private String productHomePage;

	/**
	 * 产品描述 product_desc
	 */
	private String productDesc;

	/**
	 * 产品备注 product_memo
	 */
	private String productMemo;

	
	/**
	 * 是否为虚拟物品 is_virtual
	 */
	@NotNull
	private Integer isVirtual;
	
	@Valid
	private List<CreateProductAttrParam> attrs;
	
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
	
}
