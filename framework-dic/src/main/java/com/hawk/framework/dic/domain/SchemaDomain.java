package com.hawk.framework.dic.domain;
import java.io.Serializable;




/**
 * schema代表一个库，一个库可以有很多应用
 * The class is mapped to the table t_dic_schema 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class SchemaDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键ID object_id
	 */
	private String objectId;
	
	/**
	 * 数据库编码 object_label
	 */
	private String objectLabel;
	
	/**
	 * 数据库名称 object_name
	 */
	private String objectName;
	
	/**
	 * 数据库描述 object_comment
	 */
	private String objectComment;
	
	
	/**
	 * 
	 * @return 主键ID object_id
	 */
	public String getObjectId(){
		return objectId;
	}
	
	/**
	 * 
	 * @param objectId 主键ID object_id
	 */	
	public void setObjectId (String objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 
	 * @return 数据库编码 object_label
	 */
	public String getObjectLabel(){
		return objectLabel;
	}
	
	/**
	 * 
	 * @param objectLabel 数据库编码 object_label
	 */	
	public void setObjectLabel (String objectLabel) {
		this.objectLabel = objectLabel;
	}
	
	/**
	 * 
	 * @return 数据库名称 object_name
	 */
	public String getObjectName(){
		return objectName;
	}
	
	/**
	 * 
	 * @param objectName 数据库名称 object_name
	 */	
	public void setObjectName (String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * 
	 * @return 数据库描述 object_comment
	 */
	public String getObjectComment(){
		return objectComment;
	}
	
	/**
	 * 
	 * @param objectComment 数据库描述 object_comment
	 */	
	public void setObjectComment (String objectComment) {
		this.objectComment = objectComment;
	}
	


}
