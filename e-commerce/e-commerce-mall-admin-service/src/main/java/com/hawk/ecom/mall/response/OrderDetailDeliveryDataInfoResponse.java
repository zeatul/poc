package com.hawk.ecom.mall.response;

import java.util.Date;

import com.hawk.framework.pub.web.ResponseData;

public class OrderDetailDeliveryDataInfoResponse implements ResponseData{
	public String getDeliveryStatusMemo() {
		return deliveryStatusMemo;
	}

	public void setDeliveryStatusMemo(String deliveryStatusMemo) {
		this.deliveryStatusMemo = deliveryStatusMemo;
	}

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

	public String getOuterOrderCode() {
		return outerOrderCode;
	}

	public void setOuterOrderCode(String outerOrderCode) {
		this.outerOrderCode = outerOrderCode;
	}

	public String getOuterOrderStatus2() {
		return outerOrderStatus2;
	}

	public void setOuterOrderStatus2(String outerOrderStatus2) {
		this.outerOrderStatus2 = outerOrderStatus2;
	}

	public String getOuterOrderMsg2() {
		return outerOrderMsg2;
	}

	public void setOuterOrderMsg2(String outerOrderMsg2) {
		this.outerOrderMsg2 = outerOrderMsg2;
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

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskMemo() {
		return taskMemo;
	}

	public void setTaskMemo(String taskMemo) {
		this.taskMemo = taskMemo;
	}

	public Date getScheduleExecDate() {
		return scheduleExecDate;
	}

	public void setScheduleExecDate(Date scheduleExecDate) {
		this.scheduleExecDate = scheduleExecDate;
	}

	public Integer getExecTimes() {
		return execTimes;
	}

	public void setExecTimes(Integer execTimes) {
		this.execTimes = execTimes;
	}

	public Integer getMaxExecTimes() {
		return maxExecTimes;
	}

	public void setMaxExecTimes(Integer maxExecTimes) {
		this.maxExecTimes = maxExecTimes;
	}

	public String getLastExecRtnCode() {
		return lastExecRtnCode;
	}

	public void setLastExecRtnCode(String lastExecRtnCode) {
		this.lastExecRtnCode = lastExecRtnCode;
	}

	public String getLastExecRtnMsg() {
		return lastExecRtnMsg;
	}

	public void setLastExecRtnMsg(String lastExecRtnMsg) {
		this.lastExecRtnMsg = lastExecRtnMsg;
	}

	public Date getLastExecBeginTime() {
		return lastExecBeginTime;
	}

	public void setLastExecBeginTime(Date lastExecBeginTime) {
		this.lastExecBeginTime = lastExecBeginTime;
	}

	public Date getLastExecEndTime() {
		return lastExecEndTime;
	}

	public void setLastExecEndTime(Date lastExecEndTime) {
		this.lastExecEndTime = lastExecEndTime;
	}

	public String getLastExecComputer() {
		return lastExecComputer;
	}

	public void setLastExecComputer(String lastExecComputer) {
		this.lastExecComputer = lastExecComputer;
	}

	public String getLastExecProcessId() {
		return lastExecProcessId;
	}

	public void setLastExecProcessId(String lastExecProcessId) {
		this.lastExecProcessId = lastExecProcessId;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUserCode() {
		return updateUserCode;
	}

	public void setUpdateUserCode(String updateUserCode) {
		this.updateUserCode = updateUserCode;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDeleteUserCode() {
		return deleteUserCode;
	}

	public void setDeleteUserCode(String deleteUserCode) {
		this.deleteUserCode = deleteUserCode;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
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
	 * 外部交易编号(供应商返回的订单编号) outer_order_code
	 */
	private String outerOrderCode;
	
	/**
	 * 外部交易状态2(后续状态) outer_order_status2
	 */
	private String outerOrderStatus2;
	
	/**
	 * 外部交易描述2(后续描述) outer_order_msg2
	 */
	private String outerOrderMsg2;
	
	/**
	 * 外部交易状态 outer_order_status
	 */
	private String outerOrderStatus;
	
	/**
	 * 外部交易描述 outer_order_msg
	 */
	private String outerOrderMsg;
	
	/**
	 * 任务号 task_code
	 */
	private String taskCode;
	
	/**
	 * 任务名称 task_name
	 */
	private String taskName;
	
	/**
	 * 任务状态 task_status
	 */
	private Integer taskStatus;
	
	/**
	 * 任务描述 task_desc
	 */
	private String taskDesc;
	
	/**
	 * 任务备注 task_memo
	 */
	private String taskMemo;
	
	/**
	 * 计划执行时间 schedule_exec_date
	 */
	private Date scheduleExecDate;
	
	/**
	 * 已经执行次数 exec_times
	 */
	private Integer execTimes;
	
	/**
	 * 最大允许执行次数 max_exec_times
	 */
	private Integer maxExecTimes;
	
	/**
	 * 最后一次执行返回代码 last_exec_rtn_code
	 */
	private String lastExecRtnCode;
	
	/**
	 * 交付状态备注 delivery_status_memo
	 */
	private String deliveryStatusMemo;
	
	/**
	 * 最后一次执行返回消息 last_exec_rtn_msg
	 */
	private String lastExecRtnMsg;
	
	/**
	 * 最后一次执行开始时间 last_exec_begin_time
	 */
	private Date lastExecBeginTime;
	
	/**
	 * 最后一次执行完成时间 last_exec_end_time
	 */
	private Date lastExecEndTime;
	
	/**
	 * 最后一次执行机器 last_exec_computer
	 */
	private String lastExecComputer;
	
	/**
	 * 最后一次执行进程ID last_exec_process_id
	 */
	private String lastExecProcessId;
	
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
	
}
