package com.hawk.ecom.product.response;

import java.util.Date;

import com.hawk.framework.pub.web.ResponseData;

public class ProductInfoResponse implements ResponseData{
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer  id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer  categoryId) {
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
}
