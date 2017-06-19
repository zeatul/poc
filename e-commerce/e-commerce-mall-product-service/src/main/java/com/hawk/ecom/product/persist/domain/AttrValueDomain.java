package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 属性值表
 * The class is mapped to the table t_prd_attr_value 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class AttrValueDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 产品目录主键 category_id
	 */
	private Integer categoryId;
	
	/**
	 * 属性名主键 attr_name_id
	 */
	private Integer attrNameId;
	
	/**
	 * 属性值 attr_value
	 */
	private String attrValue;
	
	/**
	 * 属性值显示名称 attr_display_value
	 */
	private String attrDisplayValue;
	
	/**
	 * 属性值状态 attr_value_status
	 */
	private Integer attrValueStatus;
	
	/**
	 * 创建者 create_user_code
	 */
	private String createUserCode;
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新者 update_user_code
	 */
	private String updateUserCode;
	
	/**
	 * 更新日期 update_date
	 */
	private Date updateDate;
	
	/**
	 * 删除者 delete_user_code
	 */
	private String deleteUserCode;
	
	/**
	 * 删除日期 delete_date
	 */
	private Date deleteDate;
	
	
	/**
	 * 
	 * @return 主键 id
	 */
	public Integer getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Integer id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 产品目录主键 category_id
	 */
	public Integer getCategoryId(){
		return categoryId;
	}
	
	/**
	 * 
	 * @param categoryId 产品目录主键 category_id
	 */	
	public void setCategoryId (Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * 
	 * @return 属性名主键 attr_name_id
	 */
	public Integer getAttrNameId(){
		return attrNameId;
	}
	
	/**
	 * 
	 * @param attrNameId 属性名主键 attr_name_id
	 */	
	public void setAttrNameId (Integer attrNameId) {
		this.attrNameId = attrNameId;
	}
	
	/**
	 * 
	 * @return 属性值 attr_value
	 */
	public String getAttrValue(){
		return attrValue;
	}
	
	/**
	 * 
	 * @param attrValue 属性值 attr_value
	 */	
	public void setAttrValue (String attrValue) {
		this.attrValue = attrValue;
	}
	
	/**
	 * 
	 * @return 属性值显示名称 attr_display_value
	 */
	public String getAttrDisplayValue(){
		return attrDisplayValue;
	}
	
	/**
	 * 
	 * @param attrDisplayValue 属性值显示名称 attr_display_value
	 */	
	public void setAttrDisplayValue (String attrDisplayValue) {
		this.attrDisplayValue = attrDisplayValue;
	}
	
	/**
	 * 
	 * @return 属性值状态 attr_value_status
	 */
	public Integer getAttrValueStatus(){
		return attrValueStatus;
	}
	
	/**
	 * 
	 * @param attrValueStatus 属性值状态 attr_value_status
	 */	
	public void setAttrValueStatus (Integer attrValueStatus) {
		this.attrValueStatus = attrValueStatus;
	}
	
	/**
	 * 
	 * @return 创建者 create_user_code
	 */
	public String getCreateUserCode(){
		return createUserCode;
	}
	
	/**
	 * 
	 * @param createUserCode 创建者 create_user_code
	 */	
	public void setCreateUserCode (String createUserCode) {
		this.createUserCode = createUserCode;
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
	 * @return 更新者 update_user_code
	 */
	public String getUpdateUserCode(){
		return updateUserCode;
	}
	
	/**
	 * 
	 * @param updateUserCode 更新者 update_user_code
	 */	
	public void setUpdateUserCode (String updateUserCode) {
		this.updateUserCode = updateUserCode;
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
	 * @return 删除者 delete_user_code
	 */
	public String getDeleteUserCode(){
		return deleteUserCode;
	}
	
	/**
	 * 
	 * @param deleteUserCode 删除者 delete_user_code
	 */	
	public void setDeleteUserCode (String deleteUserCode) {
		this.deleteUserCode = deleteUserCode;
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
