package com.hawk.ecom.product.response;

import com.hawk.framework.pub.web.ResponseData;

public class PicInfoResponse implements ResponseData{
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	/**
	 * 主键 id
	 */
	private Integer id;
	
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
	private String picUrl;
	
	/**
	 * 图片类型 pic_type
	 */
	private Integer picType;
	
	/**
	 * 图片序号 object_order
	 */
	private Integer objectOrder;
	
}
