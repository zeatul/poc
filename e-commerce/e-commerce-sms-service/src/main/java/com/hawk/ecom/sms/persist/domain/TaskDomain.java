package com.hawk.ecom.sms.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 短信发送记录
 * The class is mapped to the table t_sms_task 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class TaskDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 短信运营商编号 sms_operator_code
	 */
	private String smsOperatorCode;
	
	/**
	 * 批次号 sms_batch_no
	 */
	private String smsBatchNo;
	
	/**
	 * 是否是批量发送 sms_is_batch
	 */
	private Integer smsIsBatch;
	
	/**
	 * 手机号码 mobile_number
	 */
	private String mobileNumber;
	
	/**
	 * 短信发送状态 sms_status
	 */
	private Integer smsStatus;
	
	/**
	 * 短信内容 sms_msg
	 */
	private String smsMsg;
	
	/**
	 * 短信发送回执 sms_receipt
	 */
	private String smsReceipt;
	
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
	 * 最后一次执行时间 last_exec_date
	 */
	private Date lastExecDate;
	
	/**
	 * 计划执行时间 schedule_exec_date
	 */
	private Date scheduleExecDate;
	
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
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 短信运营商编号 sms_operator_code
	 */
	public String getSmsOperatorCode(){
		return smsOperatorCode;
	}
	
	/**
	 * 
	 * @param smsOperatorCode 短信运营商编号 sms_operator_code
	 */	
	public void setSmsOperatorCode (String smsOperatorCode) {
		this.smsOperatorCode = smsOperatorCode;
	}
	
	/**
	 * 
	 * @return 批次号 sms_batch_no
	 */
	public String getSmsBatchNo(){
		return smsBatchNo;
	}
	
	/**
	 * 
	 * @param smsBatchNo 批次号 sms_batch_no
	 */	
	public void setSmsBatchNo (String smsBatchNo) {
		this.smsBatchNo = smsBatchNo;
	}
	
	/**
	 * 
	 * @return 是否是批量发送 sms_is_batch
	 */
	public Integer getSmsIsBatch(){
		return smsIsBatch;
	}
	
	/**
	 * 
	 * @param smsIsBatch 是否是批量发送 sms_is_batch
	 */	
	public void setSmsIsBatch (Integer smsIsBatch) {
		this.smsIsBatch = smsIsBatch;
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
	 * @return 短信发送状态 sms_status
	 */
	public Integer getSmsStatus(){
		return smsStatus;
	}
	
	/**
	 * 
	 * @param smsStatus 短信发送状态 sms_status
	 */	
	public void setSmsStatus (Integer smsStatus) {
		this.smsStatus = smsStatus;
	}
	
	/**
	 * 
	 * @return 短信内容 sms_msg
	 */
	public String getSmsMsg(){
		return smsMsg;
	}
	
	/**
	 * 
	 * @param smsMsg 短信内容 sms_msg
	 */	
	public void setSmsMsg (String smsMsg) {
		this.smsMsg = smsMsg;
	}
	
	/**
	 * 
	 * @return 短信发送回执 sms_receipt
	 */
	public String getSmsReceipt(){
		return smsReceipt;
	}
	
	/**
	 * 
	 * @param smsReceipt 短信发送回执 sms_receipt
	 */	
	public void setSmsReceipt (String smsReceipt) {
		this.smsReceipt = smsReceipt;
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
	 * @return 最后一次执行时间 last_exec_date
	 */
	public Date getLastExecDate(){
		return lastExecDate;
	}
	
	/**
	 * 
	 * @param lastExecDate 最后一次执行时间 last_exec_date
	 */	
	public void setLastExecDate (Date lastExecDate) {
		this.lastExecDate = lastExecDate;
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
