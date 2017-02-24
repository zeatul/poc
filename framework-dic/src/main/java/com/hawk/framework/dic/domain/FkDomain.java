package com.hawk.framework.dic.domain;
import java.io.Serializable;




/**
 * 外键对象
 * The class is mapped to the table t_dic_fk 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class FkDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 对象ID object_id
	 */
	private String objectId;
	
	/**
	 * 外键编码 object_code
	 */
	private String objectCode;
	
	/**
	 * 外键名称 object_name
	 */
	private String objectName;
	
	/**
	 * 外键描述 object_comment
	 */
	private String objectComment;
	
	/**
	 * 主表对象ID parent_table_object_id
	 */
	private String parentTableObjectId;
	
	/**
	 * 子表对象ID child_table_object_id
	 */
	private String childTableObjectId;
	
	
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
	 * @return 外键编码 object_code
	 */
	public String getObjectCode(){
		return objectCode;
	}
	
	/**
	 * 
	 * @param objectCode 外键编码 object_code
	 */	
	public void setObjectCode (String objectCode) {
		this.objectCode = objectCode;
	}
	
	/**
	 * 
	 * @return 外键名称 object_name
	 */
	public String getObjectName(){
		return objectName;
	}
	
	/**
	 * 
	 * @param objectName 外键名称 object_name
	 */	
	public void setObjectName (String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * 
	 * @return 外键描述 object_comment
	 */
	public String getObjectComment(){
		return objectComment;
	}
	
	/**
	 * 
	 * @param objectComment 外键描述 object_comment
	 */	
	public void setObjectComment (String objectComment) {
		this.objectComment = objectComment;
	}
	
	/**
	 * 
	 * @return 主表对象ID parent_table_object_id
	 */
	public String getParentTableObjectId(){
		return parentTableObjectId;
	}
	
	/**
	 * 
	 * @param parentTableObjectId 主表对象ID parent_table_object_id
	 */	
	public void setParentTableObjectId (String parentTableObjectId) {
		this.parentTableObjectId = parentTableObjectId;
	}
	
	/**
	 * 
	 * @return 子表对象ID child_table_object_id
	 */
	public String getChildTableObjectId(){
		return childTableObjectId;
	}
	
	/**
	 * 
	 * @param childTableObjectId 子表对象ID child_table_object_id
	 */	
	public void setChildTableObjectId (String childTableObjectId) {
		this.childTableObjectId = childTableObjectId;
	}
	


}
