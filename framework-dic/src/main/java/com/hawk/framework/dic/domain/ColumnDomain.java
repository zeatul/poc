package com.hawk.framework.dic.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 表字段对象
 * The class is mapped to the table t_dic_column 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class ColumnDomain implements Serializable {

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
	 * 引用的数据类型ID data_definition_object_id
	 */
	private String dataDefinitionObjectId;
	
	/**
	 * 数据库字段编码（为空，则用数据字典名） object_code
	 */
	private String objectCode;
	
	/**
	 * 数据库字段名称 object_name
	 */
	private String objectName;
	
	/**
	 * 数据库字段描述 object_comment
	 */
	private String objectComment;
	
	/**
	 * 数据库字段在表的序号 object_order
	 */
	private Integer objectOrder;
	
	/**
	 * 可否为空(1/0) nullable
	 */
	private Integer nullable;
	
	/**
	 * 是否为主键(1/0) is_pk
	 */
	private Integer isPk;
	
	/**
	 * 支持的运算符，等于默认支持 operators
	 */
	private String operators;
	
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
	 * @return 数据库字段编码（为空，则用数据字典名） object_code
	 */
	public String getObjectCode(){
		return objectCode;
	}
	
	/**
	 * 
	 * @param objectCode 数据库字段编码（为空，则用数据字典名） object_code
	 */	
	public void setObjectCode (String objectCode) {
		this.objectCode = objectCode;
	}
	
	/**
	 * 
	 * @return 数据库字段名称 object_name
	 */
	public String getObjectName(){
		return objectName;
	}
	
	/**
	 * 
	 * @param objectName 数据库字段名称 object_name
	 */	
	public void setObjectName (String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * 
	 * @return 数据库字段描述 object_comment
	 */
	public String getObjectComment(){
		return objectComment;
	}
	
	/**
	 * 
	 * @param objectComment 数据库字段描述 object_comment
	 */	
	public void setObjectComment (String objectComment) {
		this.objectComment = objectComment;
	}
	
	/**
	 * 
	 * @return 数据库字段在表的序号 object_order
	 */
	public Integer getObjectOrder(){
		return objectOrder;
	}
	
	/**
	 * 
	 * @param objectOrder 数据库字段在表的序号 object_order
	 */	
	public void setObjectOrder (Integer objectOrder) {
		this.objectOrder = objectOrder;
	}
	
	/**
	 * 
	 * @return 可否为空(1/0) nullable
	 */
	public Integer getNullable(){
		return nullable;
	}
	
	/**
	 * 
	 * @param nullable 可否为空(1/0) nullable
	 */	
	public void setNullable (Integer nullable) {
		this.nullable = nullable;
	}
	
	/**
	 * 
	 * @return 是否为主键(1/0) is_pk
	 */
	public Integer getIsPk(){
		return isPk;
	}
	
	/**
	 * 
	 * @param isPk 是否为主键(1/0) is_pk
	 */	
	public void setIsPk (Integer isPk) {
		this.isPk = isPk;
	}
	
	/**
	 * 
	 * @return 支持的运算符，等于默认支持 operators
	 */
	public String getOperators(){
		return operators;
	}
	
	/**
	 * 
	 * @param operators 支持的运算符，等于默认支持 operators
	 */	
	public void setOperators (String operators) {
		this.operators = operators;
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
