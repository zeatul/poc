package com.hawk.ecom.pay.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 支付应用
 * The class is mapped to the table t_pay_payment_application 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class PaymentApplicationDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 应用编号 application_code
	 */
	private String applicationCode;
	
	/**
	 * 应用名称 application_name
	 */
	private String applicationName;
	
	/**
	 * 应用状态 application_status
	 */
	private Integer applicationStatus;
	
	/**
	 * 应用描述 application_desc
	 */
	private String applicationDesc;
	
	/**
	 * 应用备注 application_memo
	 */
	private String applicationMemo;
	
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
	 * @return 应用编号 application_code
	 */
	public String getApplicationCode(){
		return applicationCode;
	}
	
	/**
	 * 
	 * @param applicationCode 应用编号 application_code
	 */	
	public void setApplicationCode (String applicationCode) {
		this.applicationCode = applicationCode;
	}
	
	/**
	 * 
	 * @return 应用名称 application_name
	 */
	public String getApplicationName(){
		return applicationName;
	}
	
	/**
	 * 
	 * @param applicationName 应用名称 application_name
	 */	
	public void setApplicationName (String applicationName) {
		this.applicationName = applicationName;
	}
	
	/**
	 * 
	 * @return 应用状态 application_status
	 */
	public Integer getApplicationStatus(){
		return applicationStatus;
	}
	
	/**
	 * 
	 * @param applicationStatus 应用状态 application_status
	 */	
	public void setApplicationStatus (Integer applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	/**
	 * 
	 * @return 应用描述 application_desc
	 */
	public String getApplicationDesc(){
		return applicationDesc;
	}
	
	/**
	 * 
	 * @param applicationDesc 应用描述 application_desc
	 */	
	public void setApplicationDesc (String applicationDesc) {
		this.applicationDesc = applicationDesc;
	}
	
	/**
	 * 
	 * @return 应用备注 application_memo
	 */
	public String getApplicationMemo(){
		return applicationMemo;
	}
	
	/**
	 * 
	 * @param applicationMemo 应用备注 application_memo
	 */	
	public void setApplicationMemo (String applicationMemo) {
		this.applicationMemo = applicationMemo;
	}
	
	/**
	 * 
	 * @return 创建者 create_user_code
	 */
	public String getCreateUserCode(){
		return createUserCode;
	}
	
	/**
	 * 
	 * @param createUserCode 创建者 create_user_code
	 */	
	public void setCreateUserCode (String createUserCode) {
		this.createUserCode = createUserCode;
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
	 * @return 更新者 update_user_code
	 */
	public String getUpdateUserCode(){
		return updateUserCode;
	}
	
	/**
	 * 
	 * @param updateUserCode 更新者 update_user_code
	 */	
	public void setUpdateUserCode (String updateUserCode) {
		this.updateUserCode = updateUserCode;
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
	 * @return 删除者 delete_user_code
	 */
	public String getDeleteUserCode(){
		return deleteUserCode;
	}
	
	/**
	 * 
	 * @param deleteUserCode 删除者 delete_user_code
	 */	
	public void setDeleteUserCode (String deleteUserCode) {
		this.deleteUserCode = deleteUserCode;
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
