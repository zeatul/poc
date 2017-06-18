package com.hawk.ecom.sms.persist.domain;
import java.io.Serializable;




/**
 * 批量发送的短信手机号码
 * The class is mapped to the table t_sms_msg_batch_receiver 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class MsgBatchReceiverDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 短信发送记录主键 sms_task_id
	 */
	private Integer smsTaskId;
	
	/**
	 * 批次号 sms_batch_no
	 */
	private String smsBatchNo;
	
	/**
	 * 手机号码 mobile_number
	 */
	private String mobileNumber;
	
	/**
	 * 短信发送回执 sms_receipt
	 */
	private String smsReceipt;
	
	/**
	 * 最后一次执行错误代码 last_exec_err_code
	 */
	private String lastExecErrCode;
	
	/**
	 * 最后一次执行错误原因 last_exec_err_msg
	 */
	private String lastExecErrMsg;
	
	
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
	public void setId (Integer  id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 短信发送记录主键 sms_task_id
	 */
	public Integer getSmsTaskId(){
		return smsTaskId;
	}
	
	/**
	 * 
	 * @param smsTaskId 短信发送记录主键 sms_task_id
	 */	
	public void setSmsTaskId (Integer  smsTaskId) {
		this.smsTaskId = smsTaskId;
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
	


}
