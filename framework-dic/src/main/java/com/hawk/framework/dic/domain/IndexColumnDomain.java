package com.hawk.framework.dic.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 索引字段
 * The class is mapped to the table t_dic_index_column 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class IndexColumnDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 object_id
	 */
	private String objectId;
	
	/**
	 * 索引对象ID index_object_id
	 */
	private String indexObjectId;
	
	/**
	 * 表字段对象ID column_object_id
	 */
	private String columnObjectId;
	
	/**
	 * 字段在索引的序号 object_order
	 */
	private Integer objectOrder;
	
	/**
	 * 系统编码(区分不同项目，不同集团) system_code
	 */
	private String systemCode;
	
	/**
	 * 版本号 version
	 */
	private Integer version;
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新日期 update_date
	 */
	private Date updateDate;
	
	/**
	 * 删除日期 delete_date
	 */
	private Date deleteDate;
	
	
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
	 * @return 索引对象ID index_object_id
	 */
	public String getIndexObjectId(){
		return indexObjectId;
	}
	
	/**
	 * 
	 * @param indexObjectId 索引对象ID index_object_id
	 */	
	public void setIndexObjectId (String indexObjectId) {
		this.indexObjectId = indexObjectId;
	}
	
	/**
	 * 
	 * @return 表字段对象ID column_object_id
	 */
	public String getColumnObjectId(){
		return columnObjectId;
	}
	
	/**
	 * 
	 * @param columnObjectId 表字段对象ID column_object_id
	 */	
	public void setColumnObjectId (String columnObjectId) {
		this.columnObjectId = columnObjectId;
	}
	
	/**
	 * 
	 * @return 字段在索引的序号 object_order
	 */
	public Integer getObjectOrder(){
		return objectOrder;
	}
	
	/**
	 * 
	 * @param objectOrder 字段在索引的序号 object_order
	 */	
	public void setObjectOrder (Integer objectOrder) {
		this.objectOrder = objectOrder;
	}
	
	/**
	 * 
	 * @return 系统编码(区分不同项目，不同集团) system_code
	 */
	public String getSystemCode(){
		return systemCode;
	}
	
	/**
	 * 
	 * @param systemCode 系统编码(区分不同项目，不同集团) system_code
	 */	
	public void setSystemCode (String systemCode) {
		this.systemCode = systemCode;
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
	 * @return 创建日期 create_date
	 */
	public Date getCreateDate(){
		return createDate;
	}
	
	/**
	 * 
	 * @param createDate 创建日期 create_date
	 */	
	public void setCreateDate (Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 
	 * @return 更新日期 update_date
	 */
	public Date getUpdateDate(){
		return updateDate;
	}
	
	/**
	 * 
	 * @param updateDate 更新日期 update_date
	 */	
	public void setUpdateDate (Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * 
	 * @return 删除日期 delete_date
	 */
	public Date getDeleteDate(){
		return deleteDate;
	}
	
	/**
	 * 
	 * @param deleteDate 删除日期 delete_date
	 */	
	public void setDeleteDate (Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	


}
