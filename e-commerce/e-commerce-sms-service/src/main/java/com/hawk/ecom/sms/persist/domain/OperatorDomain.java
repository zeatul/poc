package com.hawk.ecom.sms.persist.domain;
import java.io.Serializable;




/**
 * 短信运营商
 * The class is mapped to the table t_sms_operator 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class OperatorDomain implements Serializable {

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
	 * 短信运营商编号 sms_operator_name
	 */
	private String smsOperatorName;
	
	
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
	 * @return 短信运营商编号 sms_operator_name
	 */
	public String getSmsOperatorName(){
		return smsOperatorName;
	}
	
	/**
	 * 
	 * @param smsOperatorName 短信运营商编号 sms_operator_name
	 */	
	public void setSmsOperatorName (String smsOperatorName) {
		this.smsOperatorName = smsOperatorName;
	}
	


}
