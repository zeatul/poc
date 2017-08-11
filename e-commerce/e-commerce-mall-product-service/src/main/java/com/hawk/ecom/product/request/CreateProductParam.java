package com.hawk.ecom.product.request;

import java.util.List;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class CreateProductParam {
	

	

	public String getProductExtra1() {
		return productExtra1;
	}

	public void setProductExtra1(String productExtra1) {
		this.productExtra1 = productExtra1;
	}

	public String getProductExtra2() {
		return productExtra2;
	}

	public void setProductExtra2(String productExtra2) {
		this.productExtra2 = productExtra2;
	}

	public String getProductExtra3() {
		return productExtra3;
	}

	public void setProductExtra3(String productExtra3) {
		this.productExtra3 = productExtra3;
	}

	public String getProductExtra4() {
		return productExtra4;
	}

	public void setProductExtra4(String productExtra4) {
		this.productExtra4 = productExtra4;
	}

	public String getProductExtra5() {
		return productExtra5;
	}

	public void setProductExtra5(String productExtra5) {
		this.productExtra5 = productExtra5;
	}

	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public List<Integer> getProductNormalAttrValueIds() {
		return productNormalAttrValueIds;
	}

	public void setProductNormalAttrValueIds(List<Integer> productNormalAttrValueIds) {
		this.productNormalAttrValueIds = productNormalAttrValueIds;
	}

	public List<Integer> getProductKeyAttrValueIds() {
		return productKeyAttrValueIds;
	}

	public void setProductKeyAttrValueIds(List<Integer> productKeyAttrValueIds) {
		this.productKeyAttrValueIds = productKeyAttrValueIds;
	}

	public List<Integer> getProductSkuAttrNameIds() {
		return productSkuAttrNameIds;
	}

	public void setProductSkuAttrNameIds(List<Integer> productSkuAttrNameIds) {
		this.productSkuAttrNameIds = productSkuAttrNameIds;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer  categoryId) {
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
	
	/**
	 * 缩略图 thumbnail
	 */
	private String thumbnail;

	
	/**
	 * 产品目录主键 category_id
	 */
	@NotNull
	private Integer categoryId;

	/**
	 * 产品编号 product_code
	 */	
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
	
	
	/**
	 * 交付方式 delivery_type
	 */
	@NotNull
	private Integer deliveryType;
	
	
	/**
	 * 产品关键属性值ID集合 product_key_attr_value_ids
	 */
	private List<Integer> productKeyAttrValueIds;
	
	
	/**
	 * 产品普通属性值ID集合 product_key_attr_value_ids
	 */
	private List<Integer> productNormalAttrValueIds;
	
	
	
	/**
	 * 产品SKU属性名ID集合 product_sku_attr_name_ids
	 */
	private List<Integer> productSkuAttrNameIds;
	
	

	/**
	 * 产品扩展属性1 product_extra1
	 */
	private String productExtra1;
	
	/**
	 * 产品扩展属性2 product_extra2
	 */
	private String productExtra2;
	
	/**
	 * 产品扩展属性3 product_extra3
	 */
	private String productExtra3;
	
	/**
	 * 产品扩展属性4 product_extra4
	 */
	private String productExtra4;
	
	/**
	 * 产品扩展属性5 product_extra5
	 */
	private String productExtra5;
	
	
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
	
}
