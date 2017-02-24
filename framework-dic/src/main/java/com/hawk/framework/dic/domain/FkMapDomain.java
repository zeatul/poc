package com.hawk.framework.dic.domain;
import java.io.Serializable;




/**
 * 外键字段匹配
 * The class is mapped to the table t_dic_fk_map 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class FkMapDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 object_id
	 */
	private String objectId;
	
	/**
	 * 外键对象ID fk_object_id
	 */
	private String fkObjectId;
	
	/**
	 * 主表字段对象ID parent_column_object_id
	 */
	private String parentColumnObjectId;
	
	/**
	 * 主表字段对象ID child_column_object_id
	 */
	private String childColumnObjectId;
	
	
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
	 * @return 外键对象ID fk_object_id
	 */
	public String getFkObjectId(){
		return fkObjectId;
	}
	
	/**
	 * 
	 * @param fkObjectId 外键对象ID fk_object_id
	 */	
	public void setFkObjectId (String fkObjectId) {
		this.fkObjectId = fkObjectId;
	}
	
	/**
	 * 
	 * @return 主表字段对象ID parent_column_object_id
	 */
	public String getParentColumnObjectId(){
		return parentColumnObjectId;
	}
	
	/**
	 * 
	 * @param parentColumnObjectId 主表字段对象ID parent_column_object_id
	 */	
	public void setParentColumnObjectId (String parentColumnObjectId) {
		this.parentColumnObjectId = parentColumnObjectId;
	}
	
	/**
	 * 
	 * @return 主表字段对象ID child_column_object_id
	 */
	public String getChildColumnObjectId(){
		return childColumnObjectId;
	}
	
	/**
	 * 
	 * @param childColumnObjectId 主表字段对象ID child_column_object_id
	 */	
	public void setChildColumnObjectId (String childColumnObjectId) {
		this.childColumnObjectId = childColumnObjectId;
	}
	


}
