package com.hawk.ecom.sms.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 短信模板
 * The class is mapped to the table t_sms_message_model 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class MessageModelDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 模板编号 sms_model_code
	 */
	private String smsModelCode;
	
	/**
	 * 模板名称 sms_model_name
	 */
	private String smsModelName;
	
	/**
	 * 模板内容 sms_model_content
	 */
	private String smsModelContent;
	
	/**
	 * 版本号 version
	 */
	private Integer version;
	
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
	 * @return 模板编号 sms_model_code
	 */
	public String getSmsModelCode(){
		return smsModelCode;
	}
	
	/**
	 * 
	 * @param smsModelCode 模板编号 sms_model_code
	 */	
	public void setSmsModelCode (String smsModelCode) {
		this.smsModelCode = smsModelCode;
	}
	
	/**
	 * 
	 * @return 模板名称 sms_model_name
	 */
	public String getSmsModelName(){
		return smsModelName;
	}
	
	/**
	 * 
	 * @param smsModelName 模板名称 sms_model_name
	 */	
	public void setSmsModelName (String smsModelName) {
		this.smsModelName = smsModelName;
	}
	
	/**
	 * 
	 * @return 模板内容 sms_model_content
	 */
	public String getSmsModelContent(){
		return smsModelContent;
	}
	
	/**
	 * 
	 * @param smsModelContent 模板内容 sms_model_content
	 */	
	public void setSmsModelContent (String smsModelContent) {
		this.smsModelContent = smsModelContent;
	}
	
	/**
	 * 
	 * @return 版本号 version
	 */
	public Integer getVersion(){
		return version;
	}
	
	/**
	 * 
	 * @param version 版本号 version
	 */	
	public void setVersion (Integer version) {
		this.version = version;
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
