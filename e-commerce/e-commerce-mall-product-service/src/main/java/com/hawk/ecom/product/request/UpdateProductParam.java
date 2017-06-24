package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;

public class UpdateProductParam {

	public List<Integer> getAddKeyAttrValueIds() {
		return addKeyAttrValueIds;
	}

	public void setAddKeyAttrValueIds(List<Integer> addKeyAttrValueIds) {
		this.addKeyAttrValueIds = addKeyAttrValueIds;
	}

	public List<Integer> getRemoveKeyAttrValueIds() {
		return removeKeyAttrValueIds;
	}

	public void setRemoveKeyAttrValueIds(List<Integer> removeKeyAttrValueIds) {
		this.removeKeyAttrValueIds = removeKeyAttrValueIds;
	}

	public List<Integer> getAddNormalAttrValueIds() {
		return addNormalAttrValueIds;
	}

	public void setAddNormalAttrValueIds(List<Integer> addNormalAttrValueIds) {
		this.addNormalAttrValueIds = addNormalAttrValueIds;
	}

	public List<Integer> getRemoveNormalAttrValueIds() {
		return removeNormalAttrValueIds;
	}

	public void setRemoveNormalAttrValueIds(List<Integer> removeNormalAttrValueIds) {
		this.removeNormalAttrValueIds = removeNormalAttrValueIds;
	}

	public List<Integer> getAddSkuAttrNameIds() {
		return addSkuAttrNameIds;
	}

	public void setAddSkuAttrNameIds(List<Integer> addSkuAttrNameIds) {
		this.addSkuAttrNameIds = addSkuAttrNameIds;
	}

	public List<Integer> getRemoveSkuAttrNameIds() {
		return removeSkuAttrNameIds;
	}

	public void setRemoveSkuAttrNameIds(List<Integer> removeSkuAttrNameIds) {
		this.removeSkuAttrNameIds = removeSkuAttrNameIds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer  id) {
		this.id = id;
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
	 * 产品编号 product_code
	 */
	private String productCode;

	/**
	 * 产品名称 product_name
	 */
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
	 *  新增关键属性值
	 */
	private List<Integer> addKeyAttrValueIds;
	
	/**
	 * 删除关键属性值
	 */
	private List<Integer> removeKeyAttrValueIds;
	
	/**
	 * 新增非关键属性值
	 */
	private List<Integer> addNormalAttrValueIds;
	
	
	/**
	 * 删除非关键属性值
	 */
	private List<Integer> removeNormalAttrValueIds;
	
	/**
	 * 新增SKU属性名
	 */
	private List<Integer> addSkuAttrNameIds;
	
	/**
	 * 删除SKU属性名
	 */
	private List<Integer> removeSkuAttrNameIds;

	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;

}
