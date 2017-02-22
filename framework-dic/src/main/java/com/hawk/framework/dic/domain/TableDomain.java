package com.hawk.framework.dic.domain;
import java.io.Serializable;




/**
 * 表对象
 * The class is mapped to the table t_dic_table 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class TableDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 对象ID object_id
	 */
	private String objectId;
	
	/**
	 * 表的编码 object_code
	 */
	private String objectCode;
	
	/**
	 * 表的名称 object_name
	 */
	private String objectName;
	
	/**
	 * 表的描述 object_comment
	 */
	private String objectComment;
	
	/**
	 * 表的类型 object_type
	 */
	private String objectType;
	
	
	/**
	 * 
	 * @return 对象ID object_id
	 */
	public String getObjectId(){
		return objectId;
	}
	
	/**
	 * 
	 * @param objectId 对象ID object_id
	 */	
	public void setObjectId (String objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 
	 * @return 表的编码 object_code
	 */
	public String getObjectCode(){
		return objectCode;
	}
	
	/**
	 * 
	 * @param objectCode 表的编码 object_code
	 */	
	public void setObjectCode (String objectCode) {
		this.objectCode = objectCode;
	}
	
	/**
	 * 
	 * @return 表的名称 object_name
	 */
	public String getObjectName(){
		return objectName;
	}
	
	/**
	 * 
	 * @param objectName 表的名称 object_name
	 */	
	public void setObjectName (String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * 
	 * @return 表的描述 object_comment
	 */
	public String getObjectComment(){
		return objectComment;
	}
	
	/**
	 * 
	 * @param objectComment 表的描述 object_comment
	 */	
	public void setObjectComment (String objectComment) {
		this.objectComment = objectComment;
	}
	
	/**
	 * 
	 * @return 表的类型 object_type
	 */
	public String getObjectType(){
		return objectType;
	}
	
	/**
	 * 
	 * @param objectType 表的类型 object_type
	 */	
	public void setObjectType (String objectType) {
		this.objectType = objectType;
	}
	


}
