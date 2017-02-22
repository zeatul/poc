package com.hawk.framework.dic.domain;
import java.io.Serializable;




/**
 * 索引
 * The class is mapped to the table t_dic_index 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class IndexDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 对象ID object_id
	 */
	private String objectId;
	
	/**
	 * 表对象ID table_object_id
	 */
	private String tableObjectId;
	
	/**
	 * 索引编码 object_code
	 */
	private String objectCode;
	
	/**
	 * 索引名称 object_name
	 */
	private String objectName;
	
	/**
	 * 索引描述 object_comment
	 */
	private String objectComment;
	
	/**
	 * 是唯一索引 is_unique
	 */
	private Integer isUnique;
	
	/**
	 * 是主键 is_pk
	 */
	private Integer isPk;
	
	
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
	
	/**
	 * 
	 * @return 索引编码 object_code
	 */
	public String getObjectCode(){
		return objectCode;
	}
	
	/**
	 * 
	 * @param objectCode 索引编码 object_code
	 */	
	public void setObjectCode (String objectCode) {
		this.objectCode = objectCode;
	}
	
	/**
	 * 
	 * @return 索引名称 object_name
	 */
	public String getObjectName(){
		return objectName;
	}
	
	/**
	 * 
	 * @param objectName 索引名称 object_name
	 */	
	public void setObjectName (String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * 
	 * @return 索引描述 object_comment
	 */
	public String getObjectComment(){
		return objectComment;
	}
	
	/**
	 * 
	 * @param objectComment 索引描述 object_comment
	 */	
	public void setObjectComment (String objectComment) {
		this.objectComment = objectComment;
	}
	
	/**
	 * 
	 * @return 是唯一索引 is_unique
	 */
	public Integer getIsUnique(){
		return isUnique;
	}
	
	/**
	 * 
	 * @param isUnique 是唯一索引 is_unique
	 */	
	public void setIsUnique (Integer isUnique) {
		this.isUnique = isUnique;
	}
	
	/**
	 * 
	 * @return 是主键 is_pk
	 */
	public Integer getIsPk(){
		return isPk;
	}
	
	/**
	 * 
	 * @param isPk 是主键 is_pk
	 */	
	public void setIsPk (Integer isPk) {
		this.isPk = isPk;
	}
	


}
