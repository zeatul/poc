package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.ecom.product.request.UpdateProductAttrParam;

public class UpdateProductParam {

	public Integer getId() {
		return id;
	}

	public void setId(Integer  id) {
		this.id = id;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
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

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
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

	public List<UpdateProductAttrParam> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<UpdateProductAttrParam> attrs) {
		this.attrs = attrs;
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
	 * 商户编号 store_code
	 */
	private String storeCode;

	/**
	 * 产品编号 product_code
	 */
	private String productCode;

	/**
	 * 产品名称 product_name
	 */
	private String productName;

	/**
	 * 产品状态 product_status
	 */
	private Integer productStatus;

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

	@Valid
	private List<UpdateProductAttrParam> attrs;

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
