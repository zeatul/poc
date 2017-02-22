package com.hawk.framework.dic.domain;
import java.io.Serializable;




/**
 * schema拥有的应用
 * The class is mapped to the table t_dic_schema_application 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class SchemaApplicationDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 *  application_object_id
	 */
	private String applicationObjectId;
	
	/**
	 *  schema_object_id
	 */
	private String schemaObjectId;
	
	
	/**
	 * 
	 * @return  application_object_id
	 */
	public String getApplicationObjectId(){
		return applicationObjectId;
	}
	
	/**
	 * 
	 * @param applicationObjectId  application_object_id
	 */	
	public void setApplicationObjectId (String applicationObjectId) {
		this.applicationObjectId = applicationObjectId;
	}
	
	/**
	 * 
	 * @return  schema_object_id
	 */
	public String getSchemaObjectId(){
		return schemaObjectId;
	}
	
	/**
	 * 
	 * @param schemaObjectId  schema_object_id
	 */	
	public void setSchemaObjectId (String schemaObjectId) {
		this.schemaObjectId = schemaObjectId;
	}
	


}
