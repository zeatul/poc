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
	
	/*对象ID object_id*/
	private String objectId;
	
	/*表对象ID table_object_id*/
	private String tableObjectId;
	
	/*标签 object_label*/
	private String objectLabel;
	
	/*中文名 cname*/
	private String cname;
	
	/*描述 description*/
	private String description;
	
	/*是唯一索引 is_unique*/
	private Integer isUnique;
	
	/*是主键 is_pk*/
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
	 * @return 中文名 cname
	 */
	public String getCname(){
		return cname;
	}
	
	/**
	 * 
	 * @param cname 中文名 cname
	 */	
	public void setCname (String cname) {
		this.cname = cname;
	}
	
	/**
	 * 
	 * @return 描述 description
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * 
	 * @param description 描述 description
	 */	
	public void setDescription (String description) {
		this.description = description;
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
