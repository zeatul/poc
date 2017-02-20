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
	
	/*主键对象ID object_id*/
	private String objectId;
	
	/*标签 object_label*/
	private String objectLabel;
	
	/*名称 object_name*/
	private String objectName;
	
	/*外键描述 description*/
	private String description;
	
	/*主表对象ID parent_table_object_id*/
	private String parentTableObjectId;
	
	/*子表对象ID child_table_object_id*/
	private String childTableObjectId;
	
	
	/**
	 * 
	 * @return 主键对象ID object_id
	 */
	public String getObjectId(){
		return objectId;
	}
	
	/**
	 * 
	 * @param objectId 主键对象ID object_id
	 */	
	public void setObjectId (String objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 
	 * @return 标签 object_label
	 */
	public String getObjectLabel(){
		return objectLabel;
	}
	
	/**
	 * 
	 * @param objectLabel 标签 object_label
	 */	
	public void setObjectLabel (String objectLabel) {
		this.objectLabel = objectLabel;
	}
	
	/**
	 * 
	 * @return 名称 object_name
	 */
	public String getObjectName(){
		return objectName;
	}
	
	/**
	 * 
	 * @param objectName 名称 object_name
	 */	
	public void setObjectName (String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * 
	 * @return 外键描述 description
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * 
	 * @param description 外键描述 description
	 */	
	public void setDescription (String description) {
		this.description = description;
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
