package com.hawk.framework.dic.domain;
import java.io.Serializable;




/**
 * 应用拥有的表
 * The class is mapped to the table t_dic_application_table 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class ApplicationTableDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 object_id
	 */
	private String objectId;
	
	/**
	 * 应用对象ID application_object_id
	 */
	private String applicationObjectId;
	
	/**
	 * 表对象ID table_object_id
	 */
	private String tableObjectId;
	
	
	/**
	 * 
	 * @return 主键 object_id
	 */
	public String getObjectId(){
		return objectId;
	}
	
	/**
	 * 
	 * @param objectId 主键 object_id
	 */	
	public void setObjectId (String objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 
	 * @return 应用对象ID application_object_id
	 */
	public String getApplicationObjectId(){
		return applicationObjectId;
	}
	
	/**
	 * 
	 * @param applicationObjectId 应用对象ID application_object_id
	 */	
	public void setApplicationObjectId (String applicationObjectId) {
		this.applicationObjectId = applicationObjectId;
	}
	
	/**
	 * 
	 * @return 表对象ID table_object_id
	 */
	public String getTableObjectId(){
		return tableObjectId;
	}
	
	/**
	 * 
	 * @param tableObjectId 表对象ID table_object_id
	 */	
	public void setTableObjectId (String tableObjectId) {
		this.tableObjectId = tableObjectId;
	}
	


}
