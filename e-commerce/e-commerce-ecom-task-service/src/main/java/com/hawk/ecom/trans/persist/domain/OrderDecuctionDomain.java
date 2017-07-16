package com.hawk.ecom.trans.persist.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




/**
 * 订单减免
 * The class is mapped to the table t_tra_order_decuction 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class OrderDecuctionDomain implements Serializable {

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
	 * 订单减免 order_deduction
	 */
	private BigDecimal orderDeduction;
	
	/**
	 * 订单减免描述 order_deduction_desc
	 */
	private String orderDeductionDesc;
	
	/**
	 * 订单减免类型 order_deduction_type
	 */
	private Integer orderDeductionType;
	
	/**
	 * 订单减免备注 order_deduction_memo
	 */
	private String orderDeductionMemo;
	
	/**
	 * 订单减免来源主键 order_deduction_source_id
	 */
	private Integer orderDeductionSourceId;
	
	/**
	 * 订单减免来源券号 order_deduction_source_code
	 */
	private String orderDeductionSourceCode;
	
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
	 * @return 订单减免 order_deduction
	 */
	public BigDecimal getOrderDeduction(){
		return orderDeduction;
	}
	
	/**
	 * 
	 * @param orderDeduction 订单减免 order_deduction
	 */	
	public void setOrderDeduction (BigDecimal orderDeduction) {
		this.orderDeduction = orderDeduction;
	}
	
	/**
	 * 
	 * @return 订单减免描述 order_deduction_desc
	 */
	public String getOrderDeductionDesc(){
		return orderDeductionDesc;
	}
	
	/**
	 * 
	 * @param orderDeductionDesc 订单减免描述 order_deduction_desc
	 */	
	public void setOrderDeductionDesc (String orderDeductionDesc) {
		this.orderDeductionDesc = orderDeductionDesc;
	}
	
	/**
	 * 
	 * @return 订单减免类型 order_deduction_type
	 */
	public Integer getOrderDeductionType(){
		return orderDeductionType;
	}
	
	/**
	 * 
	 * @param orderDeductionType 订单减免类型 order_deduction_type
	 */	
	public void setOrderDeductionType (Integer orderDeductionType) {
		this.orderDeductionType = orderDeductionType;
	}
	
	/**
	 * 
	 * @return 订单减免备注 order_deduction_memo
	 */
	public String getOrderDeductionMemo(){
		return orderDeductionMemo;
	}
	
	/**
	 * 
	 * @param orderDeductionMemo 订单减免备注 order_deduction_memo
	 */	
	public void setOrderDeductionMemo (String orderDeductionMemo) {
		this.orderDeductionMemo = orderDeductionMemo;
	}
	
	/**
	 * 
	 * @return 订单减免来源主键 order_deduction_source_id
	 */
	public Integer getOrderDeductionSourceId(){
		return orderDeductionSourceId;
	}
	
	/**
	 * 
	 * @param orderDeductionSourceId 订单减免来源主键 order_deduction_source_id
	 */	
	public void setOrderDeductionSourceId (Integer orderDeductionSourceId) {
		this.orderDeductionSourceId = orderDeductionSourceId;
	}
	
	/**
	 * 
	 * @return 订单减免来源券号 order_deduction_source_code
	 */
	public String getOrderDeductionSourceCode(){
		return orderDeductionSourceCode;
	}
	
	/**
	 * 
	 * @param orderDeductionSourceCode 订单减免来源券号 order_deduction_source_code
	 */	
	public void setOrderDeductionSourceCode (String orderDeductionSourceCode) {
		this.orderDeductionSourceCode = orderDeductionSourceCode;
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
