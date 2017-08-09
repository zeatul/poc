package com.hawk.ecom.trans.response;

import java.util.Date;

import com.hawk.framework.pub.web.ResponseData;

public class OrderDetailDeliveryDataInfoResponse implements ResponseData{
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getOrderDetailId() {
		return orderDetailId;
	}


	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public String getStoreCode() {
		return storeCode;
	}


	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}


	public String getOrderCode() {
		return orderCode;
	}


	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public String getSupplierCode() {
		return supplierCode;
	}


	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}


	public Integer getDeliveryType() {
		return deliveryType;
	}


	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}


	public String getOuterPhoneModelId() {
		return outerPhoneModelId;
	}


	public void setOuterPhoneModelId(String outerPhoneModelId) {
		this.outerPhoneModelId = outerPhoneModelId;
	}


	public String getOuterProductId() {
		return outerProductId;
	}


	public void setOuterProductId(String outerProductId) {
		this.outerProductId = outerProductId;
	}


	public String getImei() {
		return imei;
	}


	public void setImei(String imei) {
		this.imei = imei;
	}


	public String getBenefIdTyp() {
		return benefIdTyp;
	}


	public void setBenefIdTyp(String benefIdTyp) {
		this.benefIdTyp = benefIdTyp;
	}


	public String getBenefIdNumber() {
		return benefIdNumber;
	}


	public void setBenefIdNumber(String benefIdNumber) {
		this.benefIdNumber = benefIdNumber;
	}


	public String getBenefBirthday() {
		return benefBirthday;
	}


	public void setBenefBirthday(String benefBirthday) {
		this.benefBirthday = benefBirthday;
	}


	public String getBenefSex() {
		return benefSex;
	}


	public void setBenefSex(String benefSex) {
		this.benefSex = benefSex;
	}


	public String getBenefName() {
		return benefName;
	}


	public void setBenefName(String benefName) {
		this.benefName = benefName;
	}


	public String getBenefMobileNumber() {
		return benefMobileNumber;
	}


	public void setBenefMobileNumber(String benefMobileNumber) {
		this.benefMobileNumber = benefMobileNumber;
	}


	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}


	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}


	public String getDeliveryStatusMemo() {
		return deliveryStatusMemo;
	}


	public void setDeliveryStatusMemo(String deliveryStatusMemo) {
		this.deliveryStatusMemo = deliveryStatusMemo;
	}


	public String getOuterOrderCode() {
		return outerOrderCode;
	}


	public void setOuterOrderCode(String outerOrderCode) {
		this.outerOrderCode = outerOrderCode;
	}


	public String getOuterOrderStatus() {
		return outerOrderStatus;
	}


	public void setOuterOrderStatus(String outerOrderStatus) {
		this.outerOrderStatus = outerOrderStatus;
	}


	public String getOuterOrderMsg() {
		return outerOrderMsg;
	}


	public void setOuterOrderMsg(String outerOrderMsg) {
		this.outerOrderMsg = outerOrderMsg;
	}


	public String getOuterOrderMsg2() {
		return outerOrderMsg2;
	}


	public void setOuterOrderMsg2(String outerOrderMsg2) {
		this.outerOrderMsg2 = outerOrderMsg2;
	}


	public String getOuterOrderStatus2() {
		return outerOrderStatus2;
	}


	public void setOuterOrderStatus2(String outerOrderStatus2) {
		this.outerOrderStatus2 = outerOrderStatus2;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 订单明细主键 order_detail_id
	 */
	private Integer orderDetailId;
	
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
	 * 供应商编号 supplier_code
	 */
	private String supplierCode;
	
	/**
	 * 交付方式 delivery_type
	 */
	private Integer deliveryType;
	
	/**
	 * 手机型号ID outer_phone_model_id
	 */
	private String outerPhoneModelId;
	
	/**
	 * 供应商产品ID outer_product_id
	 */
	private String outerProductId;
	
	/**
	 * 设备唯一的串号 imei
	 */
	private String imei;
	
	/**
	 * 受益人证件类型 benef_id_typ
	 */
	private String benefIdTyp;
	
	/**
	 * 受益人证件号码 benef_id_number
	 */
	private String benefIdNumber;
	
	/**
	 * 受益人生日 benef_birthday
	 */
	private String benefBirthday;
	
	/**
	 * 受益人性别 benef_sex
	 */
	private String benefSex;
	
	/**
	 * 受益人姓名 benef_name
	 */
	private String benefName;
	
	/**
	 * 受益人手机号 benef_mobile_number
	 */
	private String benefMobileNumber;
	
	/**
	 * 交付状态(最终状态是这里维护的) delivery_status
	 */
	private Integer deliveryStatus;
	
	/**
	 * 交付状态备注 delivery_status_memo
	 */
	private String deliveryStatusMemo;
	
	/**
	 * 外部交易编号(供应商返回的订单编号) outer_order_code
	 */
	private String outerOrderCode;
	
	/**
	 * 外部交易状态 outer_order_status
	 */
	private String outerOrderStatus;
	
	/**
	 * 外部交易描述 outer_order_msg
	 */
	private String outerOrderMsg;
	
	/**
	 * 外部交易描述2(后续描述) outer_order_msg2
	 */
	private String outerOrderMsg2;
	
	/**
	 * 外部交易状态2(后续状态) outer_order_status2
	 */
	private String outerOrderStatus2;
	
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	
	
	
	
}
