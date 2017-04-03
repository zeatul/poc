package com.hawk.framework.dic.persist.domain;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;




/**
 * 测试表的元数据使用,包括字段类型,索引,外键,不维护数据
 * The class is mapped to the table t_dic_model 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class ModelDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 对象ID object_id
	 */
	private String objectId;
	
	/**
	 * 编码 object_code
	 */
	private String objectCode;
	
	/**
	 * 名称 object_name
	 */
	private String objectName;
	
	/**
	 * 描述 object_comment
	 */
	private String objectComment;
	
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
	 * 长整型主键 id
	 */
	private Long id;
	
	/**
	 * 拼首 spell_abbr
	 */
	private String spellAbbr;
	
	/**
	 * 价格 price
	 */
	private BigDecimal price;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * 订单编号 order_number
	 */
	private String orderNumber;
	
	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	
	/**
	 * 手机号 mobile_number
	 */
	private String mobileNumber;
	
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
	 * 
	 * @return 对象ID object_id
	 */
	public String getObjectId(){
		return objectId;
	}
	
	/**
	 * 
	 * @param objectId 对象ID object_id
	 */	
	public void setObjectId (String objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 
	 * @return 编码 object_code
	 */
	public String getObjectCode(){
		return objectCode;
	}
	
	/**
	 * 
	 * @param objectCode 编码 object_code
	 */	
	public void setObjectCode (String objectCode) {
		this.objectCode = objectCode;
	}
	
	/**
	 * 
	 * @return 名称 object_name
	 */
	public String getObjectName(){
		return objectName;
	}
	
	/**
	 * 
	 * @param objectName 名称 object_name
	 */	
	public void setObjectName (String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * 
	 * @return 描述 object_comment
	 */
	public String getObjectComment(){
		return objectComment;
	}
	
	/**
	 * 
	 * @param objectComment 描述 object_comment
	 */	
	public void setObjectComment (String objectComment) {
		this.objectComment = objectComment;
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
	
	/**
	 * 
	 * @return 长整型主键 id
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 长整型主键 id
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 拼首 spell_abbr
	 */
	public String getSpellAbbr(){
		return spellAbbr;
	}
	
	/**
	 * 
	 * @param spellAbbr 拼首 spell_abbr
	 */	
	public void setSpellAbbr (String spellAbbr) {
		this.spellAbbr = spellAbbr;
	}
	
	/**
	 * 
	 * @return 价格 price
	 */
	public BigDecimal getPrice(){
		return price;
	}
	
	/**
	 * 
	 * @param price 价格 price
	 */	
	public void setPrice (BigDecimal price) {
		this.price = price;
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
	 * @return 订单编号 order_number
	 */
	public String getOrderNumber(){
		return orderNumber;
	}
	
	/**
	 * 
	 * @param orderNumber 订单编号 order_number
	 */	
	public void setOrderNumber (String orderNumber) {
		this.orderNumber = orderNumber;
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
	 * @return 手机号 mobile_number
	 */
	public String getMobileNumber(){
		return mobileNumber;
	}
	
	/**
	 * 
	 * @param mobileNumber 手机号 mobile_number
	 */	
	public void setMobileNumber (String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	


}
