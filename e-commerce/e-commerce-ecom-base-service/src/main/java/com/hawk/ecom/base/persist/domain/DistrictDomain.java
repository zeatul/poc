package com.hawk.ecom.base.persist.domain;
import java.io.Serializable;




/**
 * 行政区
 * The class is mapped to the table t_bas_district 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class DistrictDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 行政区国标码 district_code
	 */
	private String districtCode;
	
	/**
	 * 行政区名 district
	 */
	private String district;
	
	/**
	 * 上级行政区国标码 parent_district_code
	 */
	private String parentDistrictCode;
	
	/**
	 * 行政区类型 district_type
	 */
	private String districtType;
	
	
	/**
	 * 
	 * @return 行政区国标码 district_code
	 */
	public String getDistrictCode(){
		return districtCode;
	}
	
	/**
	 * 
	 * @param districtCode 行政区国标码 district_code
	 */	
	public void setDistrictCode (String districtCode) {
		this.districtCode = districtCode;
	}
	
	/**
	 * 
	 * @return 行政区名 district
	 */
	public String getDistrict(){
		return district;
	}
	
	/**
	 * 
	 * @param district 行政区名 district
	 */	
	public void setDistrict (String district) {
		this.district = district;
	}
	
	/**
	 * 
	 * @return 上级行政区国标码 parent_district_code
	 */
	public String getParentDistrictCode(){
		return parentDistrictCode;
	}
	
	/**
	 * 
	 * @param parentDistrictCode 上级行政区国标码 parent_district_code
	 */	
	public void setParentDistrictCode (String parentDistrictCode) {
		this.parentDistrictCode = parentDistrictCode;
	}
	
	/**
	 * 
	 * @return 行政区类型 district_type
	 */
	public String getDistrictType(){
		return districtType;
	}
	
	/**
	 * 
	 * @param districtType 行政区类型 district_type
	 */	
	public void setDistrictType (String districtType) {
		this.districtType = districtType;
	}
	


}
