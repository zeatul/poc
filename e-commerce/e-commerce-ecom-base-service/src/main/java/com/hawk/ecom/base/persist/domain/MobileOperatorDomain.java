package com.hawk.ecom.base.persist.domain;
import java.io.Serializable;




/**
 * 移动运营商
 * The class is mapped to the table t_bas_mobile_operator 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class MobileOperatorDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 移动运营商编码 mobile_operator_code
	 */
	private String mobileOperatorCode;
	
	/**
	 * 移动运营商 mobile_operator
	 */
	private String mobileOperator;
	
	
	/**
	 * 
	 * @return 移动运营商编码 mobile_operator_code
	 */
	public String getMobileOperatorCode(){
		return mobileOperatorCode;
	}
	
	/**
	 * 
	 * @param mobileOperatorCode 移动运营商编码 mobile_operator_code
	 */	
	public void setMobileOperatorCode (String mobileOperatorCode) {
		this.mobileOperatorCode = mobileOperatorCode;
	}
	
	/**
	 * 
	 * @return 移动运营商 mobile_operator
	 */
	public String getMobileOperator(){
		return mobileOperator;
	}
	
	/**
	 * 
	 * @param mobileOperator 移动运营商 mobile_operator
	 */	
	public void setMobileOperator (String mobileOperator) {
		this.mobileOperator = mobileOperator;
	}
	


}
