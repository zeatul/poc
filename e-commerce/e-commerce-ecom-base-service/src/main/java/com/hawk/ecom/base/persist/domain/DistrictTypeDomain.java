package com.hawk.ecom.base.persist.domain;
import java.io.Serializable;




/**
 * 行政区类型
 * The class is mapped to the table t_bas_district_type 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class DistrictTypeDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 行政区类型 district_type
	 */
	private String districtType;
	
	/**
	 * 行政区名称 district_type_name
	 */
	private String districtTypeName;
	
	
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
	
	/**
	 * 
	 * @return 行政区名称 district_type_name
	 */
	public String getDistrictTypeName(){
		return districtTypeName;
	}
	
	/**
	 * 
	 * @param districtTypeName 行政区名称 district_type_name
	 */	
	public void setDistrictTypeName (String districtTypeName) {
		this.districtTypeName = districtTypeName;
	}
	


}
