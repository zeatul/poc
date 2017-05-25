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
	 * 订单编号 order_code
	 */
	private String orderCode;
	
	/**
	 * 供应商编号 supplier_code
	 */
	private String supplierCode;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * 商品编号 goods_code
	 */
	private String goodsCode;
	
	/**
	 * 商品名称 goods_name
	 */
	private String goodsName;
	
	/**
	 * 商品后处理类别 goods_post_deal
	 */
	private String goodsPostDeal;
	
	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	
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
	 * 促销活动描述 promotion_activity_desc
	 */
	private String promotionActivityDesc;
	
	/**
	 * 促销活动编号
            
             promotion_activity_code
	 */
	private String promotionActivityCode;
	
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
	 * 当前执行机器 current_exec_computer
	 */
	private String currentExecComputer;
	
	/**
	 * 当前执行进程ID current_exec_process_id
	 */
	private String currentExecProcessId;
	
	/**
	 * 当前任务启动时间 current_exec_start_date
	 */
	private Date currentExecStartDate;
	
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
	 * @return 商品编号 goods_code
	 */
	public String getGoodsCode(){
		return goodsCode;
	}
	
	/**
	 * 
	 * @param goodsCode 商品编号 goods_code
	 */	
	public void setGoodsCode (String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	/**
	 * 
	 * @return 商品名称 goods_name
	 */
	public String getGoodsName(){
		return goodsName;
	}
	
	/**
	 * 
	 * @param goodsName 商品名称 goods_name
	 */	
	public void setGoodsName (String goodsName) {
		this.goodsName = goodsName;
	}
	
	/**
	 * 
	 * @return 商品后处理类别 goods_post_deal
	 */
	public String getGoodsPostDeal(){
		return goodsPostDeal;
	}
	
	/**
	 * 
	 * @param goodsPostDeal 商品后处理类别 goods_post_deal
	 */	
	public void setGoodsPostDeal (String goodsPostDeal) {
		this.goodsPostDeal = goodsPostDeal;
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
	 * @return 促销活动描述 promotion_activity_desc
	 */
	public String getPromotionActivityDesc(){
		return promotionActivityDesc;
	}
	
	/**
	 * 
	 * @param promotionActivityDesc 促销活动描述 promotion_activity_desc
	 */	
	public void setPromotionActivityDesc (String promotionActivityDesc) {
		this.promotionActivityDesc = promotionActivityDesc;
	}
	
	/**
	 * 
	 * @return 促销活动编号
            
             promotion_activity_code
	 */
	public String getPromotionActivityCode(){
		return promotionActivityCode;
	}
	
	/**
	 * 
	 * @param promotionActivityCode 促销活动编号
            
             promotion_activity_code
	 */	
	public void setPromotionActivityCode (String promotionActivityCode) {
		this.promotionActivityCode = promotionActivityCode;
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
	 * @return 当前执行机器 current_exec_computer
	 */
	public String getCurrentExecComputer(){
		return currentExecComputer;
	}
	
	/**
	 * 
	 * @param currentExecComputer 当前执行机器 current_exec_computer
	 */	
	public void setCurrentExecComputer (String currentExecComputer) {
		this.currentExecComputer = currentExecComputer;
	}
	
	/**
	 * 
	 * @return 当前执行进程ID current_exec_process_id
	 */
	public String getCurrentExecProcessId(){
		return currentExecProcessId;
	}
	
	/**
	 * 
	 * @param currentExecProcessId 当前执行进程ID current_exec_process_id
	 */	
	public void setCurrentExecProcessId (String currentExecProcessId) {
		this.currentExecProcessId = currentExecProcessId;
	}
	
	/**
	 * 
	 * @return 当前任务启动时间 current_exec_start_date
	 */
	public Date getCurrentExecStartDate(){
		return currentExecStartDate;
	}
	
	/**
	 * 
	 * @param currentExecStartDate 当前任务启动时间 current_exec_start_date
	 */	
	public void setCurrentExecStartDate (Date currentExecStartDate) {
		this.currentExecStartDate = currentExecStartDate;
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
