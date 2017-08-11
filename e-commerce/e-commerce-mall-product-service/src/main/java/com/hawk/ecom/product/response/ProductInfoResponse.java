package com.hawk.ecom.product.response;

import java.util.Date;

import com.hawk.framework.pub.web.ResponseData;

public class ProductInfoResponse implements ResponseData {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public Date getOnSaleStdt() {
		return onSaleStdt;
	}

	public void setOnSaleStdt(Date onSaleStdt) {
		this.onSaleStdt = onSaleStdt;
	}

	public Date getOnSaleEndt() {
		return onSaleEndt;
	}

	public void setOnSaleEndt(Date onSaleEndt) {
		this.onSaleEndt = onSaleEndt;
	}

	public Integer getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
	}

	/**
	 * 主键 id
	 */
	private Integer id;

	/**
	 * 产品目录主键 category_id
	 */
	private Integer categoryId;

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
	 * 上架开始时间 on_sale_stdt
	 */
	private Date onSaleStdt;

	/**
	 * 上架结束时间 on_sale_endt
	 */
	private Date onSaleEndt;

	/**
	 * 是否为虚拟物品 is_virtual
	 */
	private Integer isVirtual;

	/**
	 * 缩略图 thumbnail
	 */
	private String thumbnail;

	/**
	 * 交付方式 delivery_type
	 */
	private Integer deliveryType;
	

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
}
