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
	 * 明细名称 order_detail_name
	 */
	private String orderDetailName;
	
	/**
	 * 缩略图 thumbnail
	 */
	private String thumbnail;
	
	/**
	 * 明细类型 order_detail_type
	 */
	private Integer orderDetailType;
	
	/**
	 * 明细状态 order_detail_status
	 */
	private Integer orderDetailStatus;
	
	/**
	 * 明细数量 order_detail_quantity
	 */
	private Integer orderDetailQuantity;
	
	/**
	 * 原单价 original_unit_price
	 */
	private BigDecimal originalUnitPrice;
	
	/**
	 * 成交单价 trans_unit_price
	 */
	private BigDecimal transUnitPrice;
	
	/**
	 * 订单明细支付原总价 orde_detail_original_price
	 */
	private BigDecimal ordeDetailOriginalPrice;
	
	/**
	 * 订单明细支付总价 orde_detail_trans_price
	 */
	private BigDecimal ordeDetailTransPrice;
	
	/**
	 * 币种 currency
	 */
	private Integer currency;
	
	/**
	 * 备注 order_detail_memo
	 */
	private String orderDetailMemo;
	
	/**
	 * 创建者 create_user_code
	 */
	private String createUserCode;
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新者 update_user_code
	 */
	private String updateUserCode;
	
	/**
	 * 更新日期 update_date
	 */
	private Date updateDate;
	
	/**
	 * 删除者 delete_user_code
	 */
	private String deleteUserCode;
	
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
	 * @return 明细名称 order_detail_name
	 */
	public String getOrderDetailName(){
		return orderDetailName;
	}
	
	/**
	 * 
	 * @param orderDetailName 明细名称 order_detail_name
	 */	
	public void setOrderDetailName (String orderDetailName) {
		this.orderDetailName = orderDetailName;
	}
	
	/**
	 * 
	 * @return 缩略图 thumbnail
	 */
	public String getThumbnail(){
		return thumbnail;
	}
	
	/**
	 * 
	 * @param thumbnail 缩略图 thumbnail
	 */	
	public void setThumbnail (String thumbnail) {
		this.thumbnail = thumbnail;
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
	 * @return 明细状态 order_detail_status
	 */
	public Integer getOrderDetailStatus(){
		return orderDetailStatus;
	}
	
	/**
	 * 
	 * @param orderDetailStatus 明细状态 order_detail_status
	 */	
	public void setOrderDetailStatus (Integer orderDetailStatus) {
		this.orderDetailStatus = orderDetailStatus;
	}
	
	/**
	 * 
	 * @return 明细数量 order_detail_quantity
	 */
	public Integer getOrderDetailQuantity(){
		return orderDetailQuantity;
	}
	
	/**
	 * 
	 * @param orderDetailQuantity 明细数量 order_detail_quantity
	 */	
	public void setOrderDetailQuantity (Integer orderDetailQuantity) {
		this.orderDetailQuantity = orderDetailQuantity;
	}
	
	/**
	 * 
	 * @return 原单价 original_unit_price
	 */
	public BigDecimal getOriginalUnitPrice(){
		return originalUnitPrice;
	}
	
	/**
	 * 
	 * @param originalUnitPrice 原单价 original_unit_price
	 */	
	public void setOriginalUnitPrice (BigDecimal originalUnitPrice) {
		this.originalUnitPrice = originalUnitPrice;
	}
	
	/**
	 * 
	 * @return 成交单价 trans_unit_price
	 */
	public BigDecimal getTransUnitPrice(){
		return transUnitPrice;
	}
	
	/**
	 * 
	 * @param transUnitPrice 成交单价 trans_unit_price
	 */	
	public void setTransUnitPrice (BigDecimal transUnitPrice) {
		this.transUnitPrice = transUnitPrice;
	}
	
	/**
	 * 
	 * @return 订单明细支付原总价 orde_detail_original_price
	 */
	public BigDecimal getOrdeDetailOriginalPrice(){
		return ordeDetailOriginalPrice;
	}
	
	/**
	 * 
	 * @param ordeDetailOriginalPrice 订单明细支付原总价 orde_detail_original_price
	 */	
	public void setOrdeDetailOriginalPrice (BigDecimal ordeDetailOriginalPrice) {
		this.ordeDetailOriginalPrice = ordeDetailOriginalPrice;
	}
	
	/**
	 * 
	 * @return 订单明细支付总价 orde_detail_trans_price
	 */
	public BigDecimal getOrdeDetailTransPrice(){
		return ordeDetailTransPrice;
	}
	
	/**
	 * 
	 * @param ordeDetailTransPrice 订单明细支付总价 orde_detail_trans_price
	 */	
	public void setOrdeDetailTransPrice (BigDecimal ordeDetailTransPrice) {
		this.ordeDetailTransPrice = ordeDetailTransPrice;
	}
	
	/**
	 * 
	 * @return 币种 currency
	 */
	public Integer getCurrency(){
		return currency;
	}
	
	/**
	 * 
	 * @param currency 币种 currency
	 */	
	public void setCurrency (Integer currency) {
		this.currency = currency;
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
	 * @return 创建者 create_user_code
	 */
	public String getCreateUserCode(){
		return createUserCode;
	}
	
	/**
	 * 
	 * @param createUserCode 创建者 create_user_code
	 */	
	public void setCreateUserCode (String createUserCode) {
		this.createUserCode = createUserCode;
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
	 * @return 更新者 update_user_code
	 */
	public String getUpdateUserCode(){
		return updateUserCode;
	}
	
	/**
	 * 
	 * @param updateUserCode 更新者 update_user_code
	 */	
	public void setUpdateUserCode (String updateUserCode) {
		this.updateUserCode = updateUserCode;
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
	 * @return 删除者 delete_user_code
	 */
	public String getDeleteUserCode(){
		return deleteUserCode;
	}
	
	/**
	 * 
	 * @param deleteUserCode 删除者 delete_user_code
	 */	
	public void setDeleteUserCode (String deleteUserCode) {
		this.deleteUserCode = deleteUserCode;
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
