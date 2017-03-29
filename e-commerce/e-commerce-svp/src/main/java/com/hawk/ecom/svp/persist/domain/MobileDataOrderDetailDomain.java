package com.hawk.ecom.svp.persist.domain;
import java.io.Serializable;




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
	private String chargeStatus;
	
	/**
	 * 充值任务号 charge_task_id
	 */
	private String chargeTaskId;
	
	
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
	public String getChargeStatus(){
		return chargeStatus;
	}
	
	/**
	 * 
	 * @param chargeStatus 充值状态 charge_status
	 */	
	public void setChargeStatus (String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	
	/**
	 * 
	 * @return 充值任务号 charge_task_id
	 */
	public String getChargeTaskId(){
		return chargeTaskId;
	}
	
	/**
	 * 
	 * @param chargeTaskId 充值任务号 charge_task_id
	 */	
	public void setChargeTaskId (String chargeTaskId) {
		this.chargeTaskId = chargeTaskId;
	}
	


}
