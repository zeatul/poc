package com.hawk.framework.dic.domain;
import java.io.Serializable;




/**
 * 表字段对象
 * The class is mapped to the table t_dic_column
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class ColumnDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/*对象ID object_id*/
	private String objectId;
	
	/*表对象ID table_object_id*/
	private String tableObjectId;
	
	/*引用的数据类型ID data_definition_object_id*/
	private String dataDefinitionObjectId;
	
	/* 字段在表的序号 column_order*/
	private Integer columnOrder;
	
	
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
	 * @return 引用的数据类型ID data_definition_object_id
	 */
	public String getDataDefinitionObjectId(){
		return dataDefinitionObjectId;
	}
	
	/**
	 * 
	 * @param dataDefinitionObjectId 引用的数据类型ID data_definition_object_id
	 */	
	public void setDataDefinitionObjectId (String dataDefinitionObjectId) {
		this.dataDefinitionObjectId = dataDefinitionObjectId;
	}
	
	/**
	 * 
	 * @return  字段在表的序号 column_order
	 */
	public Integer getColumnOrder(){
		return columnOrder;
	}
	
	/**
	 * 
	 * @param columnOrder  字段在表的序号 column_order
	 */	
	public void setColumnOrder (Integer columnOrder) {
		this.columnOrder = columnOrder;
	}
	


}
