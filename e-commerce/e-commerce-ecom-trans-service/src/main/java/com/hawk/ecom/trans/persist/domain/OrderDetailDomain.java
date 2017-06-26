package com.hawk.ecom.trans.persist.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




/**
 * 订单明细
 * The class is mapped to the table t_tra_order_detail 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class OrderDetailDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
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
	 * 订单编号 order_code
	 */
	private String orderCode;
	
	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	
	/**
	 * 产品主键 product_id
	 */
	private Integer productId;
	
	/**
	 * 产品sku主键 sku_id
	 */
	private Integer skuId;
	
	/**
	 * 明细类型 order_detail_type
	 */
	private Integer orderDetailType;
	
	/**
	 * 产品SKU数量 sku_quantity
	 */
	private Integer skuQuantity;
	
	/**
	 * 计价个数 unit_quantity
	 */
	private Integer unitQuantity;
	
	/**
	 * 折扣 discount
	 */
	private Integer discount;
	
	/**
	 * 折扣数量 discount_quantity
	 */
	private Integer discountQuantity;
	
	/**
	 * 显示单价 unit_display_price
	 */
	private BigDecimal unitDisplayPrice;
	
	/**
	 * 单价 unit_price
	 */
	private BigDecimal unitPrice;
	
	/**
	 * 订单明细减免 order_detail_deduction
	 */
	private BigDecimal orderDetailDeduction;
	
	/**
	 * 支付价 pay_price
	 */
	private BigDecimal payPrice;
	
	/**
	 * 备注 order_detail_memo
	 */
	private String orderDetailMemo;
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新日期 update_date
	 */
	private Date updateDate;
	
	/**
	 * 删除日期 delete_date
	 */
	private Date deleteDate;
	
	
	/**
	 * 
	 * @return 主键 id
	 */
	public Integer getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Integer id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 订单主键 order_id
	 */
	public Integer getOrderId(){
		return orderId;
	}
	
	/**
	 * 
	 * @param orderId 订单主键 order_id
	 */	
	public void setOrderId (Integer orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 
	 * @return 商户编号 store_code
	 */
	public String getStoreCode(){
		return storeCode;
	}
	
	/**
	 * 
	 * @param storeCode 商户编号 store_code
	 */	
	public void setStoreCode (String storeCode) {
		this.storeCode = storeCode;
	}
	
	/**
	 * 
	 * @return 订单编号 order_code
	 */
	public String getOrderCode(){
		return orderCode;
	}
	
	/**
	 * 
	 * @param orderCode 订单编号 order_code
	 */	
	public void setOrderCode (String orderCode) {
		this.orderCode = orderCode;
	}
	
	/**
	 * 
	 * @return 用户编号 user_code
	 */
	public String getUserCode(){
		return userCode;
	}
	
	/**
	 * 
	 * @param userCode 用户编号 user_code
	 */	
	public void setUserCode (String userCode) {
		this.userCode = userCode;
	}
	
	/**
	 * 
	 * @return 产品主键 product_id
	 */
	public Integer getProductId(){
		return productId;
	}
	
	/**
	 * 
	 * @param productId 产品主键 product_id
	 */	
	public void setProductId (Integer productId) {
		this.productId = productId;
	}
	
	/**
	 * 
	 * @return 产品sku主键 sku_id
	 */
	public Integer getSkuId(){
		return skuId;
	}
	
	/**
	 * 
	 * @param skuId 产品sku主键 sku_id
	 */	
	public void setSkuId (Integer skuId) {
		this.skuId = skuId;
	}
	
	/**
	 * 
	 * @return 明细类型 order_detail_type
	 */
	public Integer getOrderDetailType(){
		return orderDetailType;
	}
	
	/**
	 * 
	 * @param orderDetailType 明细类型 order_detail_type
	 */	
	public void setOrderDetailType (Integer orderDetailType) {
		this.orderDetailType = orderDetailType;
	}
	
	/**
	 * 
	 * @return 产品SKU数量 sku_quantity
	 */
	public Integer getSkuQuantity(){
		return skuQuantity;
	}
	
	/**
	 * 
	 * @param skuQuantity 产品SKU数量 sku_quantity
	 */	
	public void setSkuQuantity (Integer skuQuantity) {
		this.skuQuantity = skuQuantity;
	}
	
	/**
	 * 
	 * @return 计价个数 unit_quantity
	 */
	public Integer getUnitQuantity(){
		return unitQuantity;
	}
	
	/**
	 * 
	 * @param unitQuantity 计价个数 unit_quantity
	 */	
	public void setUnitQuantity (Integer unitQuantity) {
		this.unitQuantity = unitQuantity;
	}
	
	/**
	 * 
	 * @return 折扣 discount
	 */
	public Integer getDiscount(){
		return discount;
	}
	
	/**
	 * 
	 * @param discount 折扣 discount
	 */	
	public void setDiscount (Integer discount) {
		this.discount = discount;
	}
	
	/**
	 * 
	 * @return 折扣数量 discount_quantity
	 */
	public Integer getDiscountQuantity(){
		return discountQuantity;
	}
	
	/**
	 * 
	 * @param discountQuantity 折扣数量 discount_quantity
	 */	
	public void setDiscountQuantity (Integer discountQuantity) {
		this.discountQuantity = discountQuantity;
	}
	
	/**
	 * 
	 * @return 显示单价 unit_display_price
	 */
	public BigDecimal getUnitDisplayPrice(){
		return unitDisplayPrice;
	}
	
	/**
	 * 
	 * @param unitDisplayPrice 显示单价 unit_display_price
	 */	
	public void setUnitDisplayPrice (BigDecimal unitDisplayPrice) {
		this.unitDisplayPrice = unitDisplayPrice;
	}
	
	/**
	 * 
	 * @return 单价 unit_price
	 */
	public BigDecimal getUnitPrice(){
		return unitPrice;
	}
	
	/**
	 * 
	 * @param unitPrice 单价 unit_price
	 */	
	public void setUnitPrice (BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	/**
	 * 
	 * @return 订单明细减免 order_detail_deduction
	 */
	public BigDecimal getOrderDetailDeduction(){
		return orderDetailDeduction;
	}
	
	/**
	 * 
	 * @param orderDetailDeduction 订单明细减免 order_detail_deduction
	 */	
	public void setOrderDetailDeduction (BigDecimal orderDetailDeduction) {
		this.orderDetailDeduction = orderDetailDeduction;
	}
	
	/**
	 * 
	 * @return 支付价 pay_price
	 */
	public BigDecimal getPayPrice(){
		return payPrice;
	}
	
	/**
	 * 
	 * @param payPrice 支付价 pay_price
	 */	
	public void setPayPrice (BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	
	/**
	 * 
	 * @return 备注 order_detail_memo
	 */
	public String getOrderDetailMemo(){
		return orderDetailMemo;
	}
	
	/**
	 * 
	 * @param orderDetailMemo 备注 order_detail_memo
	 */	
	public void setOrderDetailMemo (String orderDetailMemo) {
		this.orderDetailMemo = orderDetailMemo;
	}
	
	/**
	 * 
	 * @return 创建日期 create_date
	 */
	public Date getCreateDate(){
		return createDate;
	}
	
	/**
	 * 
	 * @param createDate 创建日期 create_date
	 */	
	public void setCreateDate (Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 
	 * @return 更新日期 update_date
	 */
	public Date getUpdateDate(){
		return updateDate;
	}
	
	/**
	 * 
	 * @param updateDate 更新日期 update_date
	 */	
	public void setUpdateDate (Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * 
	 * @return 删除日期 delete_date
	 */
	public Date getDeleteDate(){
		return deleteDate;
	}
	
	/**
	 * 
	 * @param deleteDate 删除日期 delete_date
	 */	
	public void setDeleteDate (Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	


}
