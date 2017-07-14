package com.hawk.ecom.base.persist.domain;
import java.io.Serializable;




/**
 * 移动号码段
 * The class is mapped to the table t_bas_mobile_number_segment 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class MobileNumberSegmentDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 手机号码前7位 mobile_number_prefix
	 */
	private String mobileNumberPrefix;
	
	/**
	 * 市级行政区代码 city_code
	 */
	private String cityCode;
	
	/**
	 * 省级行政区代码 province_code
	 */
	private String provinceCode;
	
	/**
	 * 移动运营商代码 mobile_operator_code
	 */
	private String mobileOperatorCode;
	
	/**
	 * isp isp
	 */
	private String isp;
	
	/**
	 * isp_type isp_type
	 */
	private String ispType;
	
	
	/**
	 * 
	 * @return 手机号码前7位 mobile_number_prefix
	 */
	public String getMobileNumberPrefix(){
		return mobileNumberPrefix;
	}
	
	/**
	 * 
	 * @param mobileNumberPrefix 手机号码前7位 mobile_number_prefix
	 */	
	public void setMobileNumberPrefix (String mobileNumberPrefix) {
		this.mobileNumberPrefix = mobileNumberPrefix;
	}
	
	/**
	 * 
	 * @return 市级行政区代码 city_code
	 */
	public String getCityCode(){
		return cityCode;
	}
	
	/**
	 * 
	 * @param cityCode 市级行政区代码 city_code
	 */	
	public void setCityCode (String cityCode) {
		this.cityCode = cityCode;
	}
	
	/**
	 * 
	 * @return 省级行政区代码 province_code
	 */
	public String getProvinceCode(){
		return provinceCode;
	}
	
	/**
	 * 
	 * @param provinceCode 省级行政区代码 province_code
	 */	
	public void setProvinceCode (String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	/**
	 * 
	 * @return 移动运营商代码 mobile_operator_code
	 */
	public String getMobileOperatorCode(){
		return mobileOperatorCode;
	}
	
	/**
	 * 
	 * @param mobileOperatorCode 移动运营商代码 mobile_operator_code
	 */	
	public void setMobileOperatorCode (String mobileOperatorCode) {
		this.mobileOperatorCode = mobileOperatorCode;
	}
	
	/**
	 * 
	 * @return isp isp
	 */
	public String getIsp(){
		return isp;
	}
	
	/**
	 * 
	 * @param isp isp isp
	 */	
	public void setIsp (String isp) {
		this.isp = isp;
	}
	
	/**
	 * 
	 * @return isp_type isp_type
	 */
	public String getIspType(){
		return ispType;
	}
	
	/**
	 * 
	 * @param ispType isp_type isp_type
	 */	
	public void setIspType (String ispType) {
		this.ispType = ispType;
	}
	


}
