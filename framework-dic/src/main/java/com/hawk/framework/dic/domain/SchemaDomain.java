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
	
	/*主键ID object_id*/
	private String objectId;
	
	/*标签 object_label*/
	private String objectLabel;
	
	/*中文名 cname*/
	private String cname;
	
	/*描述 description*/
	private String description;
	
	
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
	


}
