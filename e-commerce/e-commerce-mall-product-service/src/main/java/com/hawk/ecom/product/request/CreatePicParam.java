package com.hawk.ecom.product.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

/**
 * 创建产品属性
 * @author Administrator
 *
 */
public class CreatePicParam {


	public Integer getSkuId() {
		return skuId;
	}


	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public String getPicName() {
		return picName;
	}


	public void setPicName(String picName) {
		this.picName = picName;
	}


	public String getPicMemo() {
		return picMemo;
	}


	public void setPicMemo(String picMemo) {
		this.picMemo = picMemo;
	}


	public String getPicOuterId() {
		return picOuterId;
	}


	public void setPicOuterId(String picOuterId) {
		this.picOuterId = picOuterId;
	}


	public String getPicUrl() {
		return picUrl;
	}


	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


	public Integer getPicType() {
		return picType;
	}


	public void setPicType(Integer picType) {
		this.picType = picType;
	}


	public Integer getObjectOrder() {
		return objectOrder;
	}


	public void setObjectOrder(Integer objectOrder) {
		this.objectOrder = objectOrder;
	}


	public String getOperatorCode() {
		return operatorCode;
	}


	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}


	/**
	 * 产品SKU主键 sku_id
	 */
	private Integer skuId;
	
	/**
	 * 产品主键 product_id
	 */
	private Integer productId;
	
	/**
	 * 图片名称 pic_name
	 */
	private String picName;
	
	/**
	 * 图片备注 pic_memo
	 */
	private String picMemo;
	
	/**
	 * 图片外部主键 pic_outer_id
	 */
	private String picOuterId;
	
	/**
	 * 图片地址 pic_url
	 */
	@NotEmpty
	private String picUrl;
	
	/**
	 * 图片类型 pic_type
	 */
	@NotNull
	private Integer picType;
	
	/**
	 * 图片序号 object_order
	 */
	private Integer objectOrder;
	
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
