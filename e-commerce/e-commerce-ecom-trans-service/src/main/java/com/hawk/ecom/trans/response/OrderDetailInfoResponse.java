package com.hawk.ecom.trans.response;

import java.math.BigDecimal;
import java.util.Date;

import com.hawk.framework.pub.web.ResponseData;

public class OrderDetailInfoResponse implements ResponseData{
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public String getOrderDetailName() {
		return orderDetailName;
	}

	public void setOrderDetailName(String orderDetailName) {
		this.orderDetailName = orderDetailName;
	}

	public Integer getOrderDetailQuantity() {
		return orderDetailQuantity;
	}

	public void setOrderDetailQuantity(Integer orderDetailQuantity) {
		this.orderDetailQuantity = orderDetailQuantity;
	}

	public BigDecimal getOrdeDetailTransPrice() {
		return ordeDetailTransPrice;
	}

	public void setOrdeDetailTransPrice(BigDecimal ordeDetailTransPrice) {
		this.ordeDetailTransPrice = ordeDetailTransPrice;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 订单主键 order_id
	 */
	private Integer orderId;

	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * 产品主键 product_id
	 */
	private Integer productId;
	
	/**
	 * 产品sku主键 sku_id
	 */
	private Integer skuId;
	
	/**
	 * 明细名称 order_detail_name
	 */
	private String orderDetailName;
	

	/**
	 * 明细数量 order_detail_quantity
	 */
	private Integer orderDetailQuantity;
	
	
	/**
	 * 订单明细支付总价 orde_detail_trans_price
	 */
	private BigDecimal ordeDetailTransPrice;
	
	/**
	 * 币种 currency
	 */
	private Integer currency;
	
	
	/**
	 * 缩略图 thumbnail
	 */
	private String thumbnail;
	
}
