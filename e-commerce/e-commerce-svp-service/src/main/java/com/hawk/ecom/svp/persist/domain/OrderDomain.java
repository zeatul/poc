package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 碎屏险订单
 * The class is mapped to the table t_svp_order 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class OrderDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 订单编号 order_code
	 */
	private String orderCode;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	
	/**
	 * 手机号码 mobile_number
	 */
	private String mobileNumber;
	
	/**
	 * 订单状态 order_status
	 */
	private Integer orderStatus;
	
	/**
	 * 订单类型 order_type
	 */
	private String orderType;
	
	/**
	 * 订单描述 order_desc
	 */
	private String orderDesc;
	
	/**
	 * 订单失败原因 order_error_cause
	 */
	private String orderErrorCause;
	
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
	 * @return 手机号码 mobile_number
	 */
	public String getMobileNumber(){
		return mobileNumber;
	}
	
	/**
	 * 
	 * @param mobileNumber 手机号码 mobile_number
	 */	
	public void setMobileNumber (String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	/**
	 * 
	 * @return 订单状态 order_status
	 */
	public Integer getOrderStatus(){
		return orderStatus;
	}
	
	/**
	 * 
	 * @param orderStatus 订单状态 order_status
	 */	
	public void setOrderStatus (Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * 
	 * @return 订单类型 order_type
	 */
	public String getOrderType(){
		return orderType;
	}
	
	/**
	 * 
	 * @param orderType 订单类型 order_type
	 */	
	public void setOrderType (String orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 
	 * @return 订单描述 order_desc
	 */
	public String getOrderDesc(){
		return orderDesc;
	}
	
	/**
	 * 
	 * @param orderDesc 订单描述 order_desc
	 */	
	public void setOrderDesc (String orderDesc) {
		this.orderDesc = orderDesc;
	}
	
	/**
	 * 
	 * @return 订单失败原因 order_error_cause
	 */
	public String getOrderErrorCause(){
		return orderErrorCause;
	}
	
	/**
	 * 
	 * @param orderErrorCause 订单失败原因 order_error_cause
	 */	
	public void setOrderErrorCause (String orderErrorCause) {
		this.orderErrorCause = orderErrorCause;
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
