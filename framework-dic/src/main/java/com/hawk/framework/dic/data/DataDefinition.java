package com.hawk.framework.dic.data;

import com.hawk.framework.dic.data.type.DataType;

/**
 * 数据定义
 * 名称
 * 类型
 * 描述
 * @author pzhang1
 *
 */
public class DataDefinition {
	
	

	public Integer getUseType() {
		return useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * 名称,唯一,按照数据库命名规则（小写,下划线分割）
	 */
	private String name;

	/**
	 * 类型
	 */
	private DataType dataType;
	
	/**
	 * 描述，需要支持多语言？
	 */
	private String desc;
	
	/**
	 * 显示名称，需要支持多语言
	 */
	private String displayName;
	
	/**
	 * 唯一标识
	 */
	private String objectId;	
	
	/**
	 * 用途类型 , 0:技术数据;1:业务数据
	 */
	private Integer useType;

}
