package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 联通流量订单明细
 * The class is mapped to the table t_svp_mobile_data_order_detail 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class MobileDataOrderDetailDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 碎屏险订单id order_id
	 */
	private Long orderId;
	
	/**
	 * 手机号码 charge_mobile_number
	 */
	private String chargeMobileNumber;
	
	/**
	 * 充值流量 charge_data_size
	 */
	private Integer chargeDataSize;
	
	/**
	 * 充值状态 charge_status
	 */
	private Integer chargeStatus;
	
	/**
	 * 充值任务号 charge_task_code
	 */
	private String chargeTaskCode;
	
	/**
	 * 请求次数 exec_times
	 */
	private Integer execTimes;
	
	/**
	 * 最大请求次数 max_exec_times
	 */
	private Integer maxExecTimes;
	
	/**
	 * 最后一次请求返回错误代码 last_exec_err_code
	 */
	private String lastExecErrCode;
	
	/**
	 * 最后一次请求返回错误原因 last_exec_err_msg
	 */
	private String lastExecErrMsg;
	
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
	 * @return 碎屏险订单id order_id
	 */
	public Long getOrderId(){
		return orderId;
	}
	
	/**
	 * 
	 * @param orderId 碎屏险订单id order_id
	 */	
	public void setOrderId (Long orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 
	 * @return 手机号码 charge_mobile_number
	 */
	public String getChargeMobileNumber(){
		return chargeMobileNumber;
	}
	
	/**
	 * 
	 * @param chargeMobileNumber 手机号码 charge_mobile_number
	 */	
	public void setChargeMobileNumber (String chargeMobileNumber) {
		this.chargeMobileNumber = chargeMobileNumber;
	}
	
	/**
	 * 
	 * @return 充值流量 charge_data_size
	 */
	public Integer getChargeDataSize(){
		return chargeDataSize;
	}
	
	/**
	 * 
	 * @param chargeDataSize 充值流量 charge_data_size
	 */	
	public void setChargeDataSize (Integer chargeDataSize) {
		this.chargeDataSize = chargeDataSize;
	}
	
	/**
	 * 
	 * @return 充值状态 charge_status
	 */
	public Integer getChargeStatus(){
		return chargeStatus;
	}
	
	/**
	 * 
	 * @param chargeStatus 充值状态 charge_status
	 */	
	public void setChargeStatus (Integer chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	
	/**
	 * 
	 * @return 充值任务号 charge_task_code
	 */
	public String getChargeTaskCode(){
		return chargeTaskCode;
	}
	
	/**
	 * 
	 * @param chargeTaskCode 充值任务号 charge_task_code
	 */	
	public void setChargeTaskCode (String chargeTaskCode) {
		this.chargeTaskCode = chargeTaskCode;
	}
	
	/**
	 * 
	 * @return 请求次数 exec_times
	 */
	public Integer getExecTimes(){
		return execTimes;
	}
	
	/**
	 * 
	 * @param execTimes 请求次数 exec_times
	 */	
	public void setExecTimes (Integer execTimes) {
		this.execTimes = execTimes;
	}
	
	/**
	 * 
	 * @return 最大请求次数 max_exec_times
	 */
	public Integer getMaxExecTimes(){
		return maxExecTimes;
	}
	
	/**
	 * 
	 * @param maxExecTimes 最大请求次数 max_exec_times
	 */	
	public void setMaxExecTimes (Integer maxExecTimes) {
		this.maxExecTimes = maxExecTimes;
	}
	
	/**
	 * 
	 * @return 最后一次请求返回错误代码 last_exec_err_code
	 */
	public String getLastExecErrCode(){
		return lastExecErrCode;
	}
	
	/**
	 * 
	 * @param lastExecErrCode 最后一次请求返回错误代码 last_exec_err_code
	 */	
	public void setLastExecErrCode (String lastExecErrCode) {
		this.lastExecErrCode = lastExecErrCode;
	}
	
	/**
	 * 
	 * @return 最后一次请求返回错误原因 last_exec_err_msg
	 */
	public String getLastExecErrMsg(){
		return lastExecErrMsg;
	}
	
	/**
	 * 
	 * @param lastExecErrMsg 最后一次请求返回错误原因 last_exec_err_msg
	 */	
	public void setLastExecErrMsg (String lastExecErrMsg) {
		this.lastExecErrMsg = lastExecErrMsg;
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
