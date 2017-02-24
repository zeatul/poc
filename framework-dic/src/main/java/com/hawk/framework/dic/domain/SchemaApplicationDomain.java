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
	 * 主键 object_id
	 */
	private String objectId;
	
	/**
	 * 应用对象id application_object_id
	 */
	private String applicationObjectId;
	
	/**
	 * 数据库对象Id schema_object_id
	 */
	private String schemaObjectId;
	
	
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
	 * @return 应用对象id application_object_id
	 */
	public String getApplicationObjectId(){
		return applicationObjectId;
	}
	
	/**
	 * 
	 * @param applicationObjectId 应用对象id application_object_id
	 */	
	public void setApplicationObjectId (String applicationObjectId) {
		this.applicationObjectId = applicationObjectId;
	}
	
	/**
	 * 
	 * @return 数据库对象Id schema_object_id
	 */
	public String getSchemaObjectId(){
		return schemaObjectId;
	}
	
	/**
	 * 
	 * @param schemaObjectId 数据库对象Id schema_object_id
	 */	
	public void setSchemaObjectId (String schemaObjectId) {
		this.schemaObjectId = schemaObjectId;
	}
	


}
