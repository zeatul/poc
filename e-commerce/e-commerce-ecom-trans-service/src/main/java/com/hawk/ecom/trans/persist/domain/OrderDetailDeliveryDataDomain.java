package com.hawk.ecom.trans.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 交付数据
 * The class is mapped to the table t_tra_order_detail_delivery_data 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class OrderDetailDeliveryDataDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
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
	 * 外部交易编号 outer_order_code
	 */
	private String outerOrderCode;
	
	/**
	 * 任务号 task_code
	 */
	private String taskCode;
	
	/**
	 * 任务状态 task_status
	 */
	private Integer taskStatus;
	
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
	 * 最后一次执行错误代码 last_exec_err_code
	 */
	private String lastExecErrCode;
	
	/**
	 * 最后一次执行错误原因 last_exec_err_msg
	 */
	private String lastExecErrMsg;
	
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
	 * @return 订单明细主键 order_detail_id
	 */
	public Integer getOrderDetailId(){
		return orderDetailId;
	}
	
	/**
	 * 
	 * @param orderDetailId 订单明细主键 order_detail_id
	 */	
	public void setOrderDetailId (Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
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
	 * @return 供应商编号 supplier_code
	 */
	public String getSupplierCode(){
		return supplierCode;
	}
	
	/**
	 * 
	 * @param supplierCode 供应商编号 supplier_code
	 */	
	public void setSupplierCode (String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	/**
	 * 
	 * @return 手机型号ID outer_phone_model_id
	 */
	public String getOuterPhoneModelId(){
		return outerPhoneModelId;
	}
	
	/**
	 * 
	 * @param outerPhoneModelId 手机型号ID outer_phone_model_id
	 */	
	public void setOuterPhoneModelId (String outerPhoneModelId) {
		this.outerPhoneModelId = outerPhoneModelId;
	}
	
	/**
	 * 
	 * @return 供应商产品ID outer_product_id
	 */
	public String getOuterProductId(){
		return outerProductId;
	}
	
	/**
	 * 
	 * @param outerProductId 供应商产品ID outer_product_id
	 */	
	public void setOuterProductId (String outerProductId) {
		this.outerProductId = outerProductId;
	}
	
	/**
	 * 
	 * @return 设备唯一的串号 imei
	 */
	public String getImei(){
		return imei;
	}
	
	/**
	 * 
	 * @param imei 设备唯一的串号 imei
	 */	
	public void setImei (String imei) {
		this.imei = imei;
	}
	
	/**
	 * 
	 * @return 受益人证件类型 benef_id_typ
	 */
	public String getBenefIdTyp(){
		return benefIdTyp;
	}
	
	/**
	 * 
	 * @param benefIdTyp 受益人证件类型 benef_id_typ
	 */	
	public void setBenefIdTyp (String benefIdTyp) {
		this.benefIdTyp = benefIdTyp;
	}
	
	/**
	 * 
	 * @return 受益人证件号码 benef_id_number
	 */
	public String getBenefIdNumber(){
		return benefIdNumber;
	}
	
	/**
	 * 
	 * @param benefIdNumber 受益人证件号码 benef_id_number
	 */	
	public void setBenefIdNumber (String benefIdNumber) {
		this.benefIdNumber = benefIdNumber;
	}
	
	/**
	 * 
	 * @return 受益人生日 benef_birthday
	 */
	public String getBenefBirthday(){
		return benefBirthday;
	}
	
	/**
	 * 
	 * @param benefBirthday 受益人生日 benef_birthday
	 */	
	public void setBenefBirthday (String benefBirthday) {
		this.benefBirthday = benefBirthday;
	}
	
	/**
	 * 
	 * @return 受益人性别 benef_sex
	 */
	public String getBenefSex(){
		return benefSex;
	}
	
	/**
	 * 
	 * @param benefSex 受益人性别 benef_sex
	 */	
	public void setBenefSex (String benefSex) {
		this.benefSex = benefSex;
	}
	
	/**
	 * 
	 * @return 受益人姓名 benef_name
	 */
	public String getBenefName(){
		return benefName;
	}
	
	/**
	 * 
	 * @param benefName 受益人姓名 benef_name
	 */	
	public void setBenefName (String benefName) {
		this.benefName = benefName;
	}
	
	/**
	 * 
	 * @return 受益人手机号 benef_mobile_number
	 */
	public String getBenefMobileNumber(){
		return benefMobileNumber;
	}
	
	/**
	 * 
	 * @param benefMobileNumber 受益人手机号 benef_mobile_number
	 */	
	public void setBenefMobileNumber (String benefMobileNumber) {
		this.benefMobileNumber = benefMobileNumber;
	}
	
	/**
	 * 
	 * @return 外部交易编号 outer_order_code
	 */
	public String getOuterOrderCode(){
		return outerOrderCode;
	}
	
	/**
	 * 
	 * @param outerOrderCode 外部交易编号 outer_order_code
	 */	
	public void setOuterOrderCode (String outerOrderCode) {
		this.outerOrderCode = outerOrderCode;
	}
	
	/**
	 * 
	 * @return 任务号 task_code
	 */
	public String getTaskCode(){
		return taskCode;
	}
	
	/**
	 * 
	 * @param taskCode 任务号 task_code
	 */	
	public void setTaskCode (String taskCode) {
		this.taskCode = taskCode;
	}
	
	/**
	 * 
	 * @return 任务状态 task_status
	 */
	public Integer getTaskStatus(){
		return taskStatus;
	}
	
	/**
	 * 
	 * @param taskStatus 任务状态 task_status
	 */	
	public void setTaskStatus (Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	/**
	 * 
	 * @return 计划执行时间 schedule_exec_date
	 */
	public Date getScheduleExecDate(){
		return scheduleExecDate;
	}
	
	/**
	 * 
	 * @param scheduleExecDate 计划执行时间 schedule_exec_date
	 */	
	public void setScheduleExecDate (Date scheduleExecDate) {
		this.scheduleExecDate = scheduleExecDate;
	}
	
	/**
	 * 
	 * @return 已经执行次数 exec_times
	 */
	public Integer getExecTimes(){
		return execTimes;
	}
	
	/**
	 * 
	 * @param execTimes 已经执行次数 exec_times
	 */	
	public void setExecTimes (Integer execTimes) {
		this.execTimes = execTimes;
	}
	
	/**
	 * 
	 * @return 最大允许执行次数 max_exec_times
	 */
	public Integer getMaxExecTimes(){
		return maxExecTimes;
	}
	
	/**
	 * 
	 * @param maxExecTimes 最大允许执行次数 max_exec_times
	 */	
	public void setMaxExecTimes (Integer maxExecTimes) {
		this.maxExecTimes = maxExecTimes;
	}
	
	/**
	 * 
	 * @return 最后一次执行错误代码 last_exec_err_code
	 */
	public String getLastExecErrCode(){
		return lastExecErrCode;
	}
	
	/**
	 * 
	 * @param lastExecErrCode 最后一次执行错误代码 last_exec_err_code
	 */	
	public void setLastExecErrCode (String lastExecErrCode) {
		this.lastExecErrCode = lastExecErrCode;
	}
	
	/**
	 * 
	 * @return 最后一次执行错误原因 last_exec_err_msg
	 */
	public String getLastExecErrMsg(){
		return lastExecErrMsg;
	}
	
	/**
	 * 
	 * @param lastExecErrMsg 最后一次执行错误原因 last_exec_err_msg
	 */	
	public void setLastExecErrMsg (String lastExecErrMsg) {
		this.lastExecErrMsg = lastExecErrMsg;
	}
	
	/**
	 * 
	 * @return 最后一次执行开始时间 last_exec_begin_time
	 */
	public Date getLastExecBeginTime(){
		return lastExecBeginTime;
	}
	
	/**
	 * 
	 * @param lastExecBeginTime 最后一次执行开始时间 last_exec_begin_time
	 */	
	public void setLastExecBeginTime (Date lastExecBeginTime) {
		this.lastExecBeginTime = lastExecBeginTime;
	}
	
	/**
	 * 
	 * @return 最后一次执行完成时间 last_exec_end_time
	 */
	public Date getLastExecEndTime(){
		return lastExecEndTime;
	}
	
	/**
	 * 
	 * @param lastExecEndTime 最后一次执行完成时间 last_exec_end_time
	 */	
	public void setLastExecEndTime (Date lastExecEndTime) {
		this.lastExecEndTime = lastExecEndTime;
	}
	
	/**
	 * 
	 * @return 最后一次执行机器 last_exec_computer
	 */
	public String getLastExecComputer(){
		return lastExecComputer;
	}
	
	/**
	 * 
	 * @param lastExecComputer 最后一次执行机器 last_exec_computer
	 */	
	public void setLastExecComputer (String lastExecComputer) {
		this.lastExecComputer = lastExecComputer;
	}
	
	/**
	 * 
	 * @return 最后一次执行进程ID last_exec_process_id
	 */
	public String getLastExecProcessId(){
		return lastExecProcessId;
	}
	
	/**
	 * 
	 * @param lastExecProcessId 最后一次执行进程ID last_exec_process_id
	 */	
	public void setLastExecProcessId (String lastExecProcessId) {
		this.lastExecProcessId = lastExecProcessId;
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
