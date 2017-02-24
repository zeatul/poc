package com.hawk.framework.dic.domain;
import java.io.Serializable;




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
	


}
