package com.hawk.framework.dic.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 一张表缓存所有的数据字典，用来保留历史版本
 * The class is mapped to the table t_dic_history 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class HistoryDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 数据字典的表的名称 table_name
	 */
	private String tableName;
	
	/**
	 * 版本号 version
	 */
	private Integer version;
	
	/**
	 * 记录主键 object_id
	 */
	private String objectId;
	
	/**
	 * 记录(json格式) object_content
	 */
	private String objectContent;
	
	/**
	 * 版本创建日期 create_date
	 */
	private Date createDate;
	
	
	/**
	 * 
	 * @return 数据字典的表的名称 table_name
	 */
	public String getTableName(){
		return tableName;
	}
	
	/**
	 * 
	 * @param tableName 数据字典的表的名称 table_name
	 */	
	public void setTableName (String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * 
	 * @return 版本号 version
	 */
	public Integer getVersion(){
		return version;
	}
	
	/**
	 * 
	 * @param version 版本号 version
	 */	
	public void setVersion (Integer version) {
		this.version = version;
	}
	
	/**
	 * 
	 * @return 记录主键 object_id
	 */
	public String getObjectId(){
		return objectId;
	}
	
	/**
	 * 
	 * @param objectId 记录主键 object_id
	 */	
	public void setObjectId (String objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 
	 * @return 记录(json格式) object_content
	 */
	public String getObjectContent(){
		return objectContent;
	}
	
	/**
	 * 
	 * @param objectContent 记录(json格式) object_content
	 */	
	public void setObjectContent (String objectContent) {
		this.objectContent = objectContent;
	}
	
	/**
	 * 
	 * @return 版本创建日期 create_date
	 */
	public Date getCreateDate(){
		return createDate;
	}
	
	/**
	 * 
	 * @param createDate 版本创建日期 create_date
	 */	
	public void setCreateDate (Date createDate) {
		this.createDate = createDate;
	}
	


}
