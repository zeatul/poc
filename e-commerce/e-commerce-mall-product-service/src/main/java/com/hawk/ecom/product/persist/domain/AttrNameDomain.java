package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 属性名表
 * The class is mapped to the table t_prd_attr_name 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class AttrNameDomain implements Serializable {

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
	 * 父属性名主键 pid
	 */
	private Integer pid;
	
	/**
	 * 父属性值主键 pvid
	 */
	private Integer pvid;
	
	/**
	 * 属性名业务功能分类(品牌,供应商,其它) attr_name_business_type
	 */
	private Integer attrNameBusinessType;
	
	/**
	 * 属性值类型 attr_value_type
	 */
	private Integer attrValueType;
	
	/**
	 * 属性名名称 attr_name
	 */
	private String attrName;
	
	/**
	 * 属性名状态 attr_name_status
	 */
	private Integer attrNameStatus;
	
	/**
	 * 是否搜索 is_search
	 */
	private Integer isSearch;
	
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
	 * @return 父属性名主键 pid
	 */
	public Integer getPid(){
		return pid;
	}
	
	/**
	 * 
	 * @param pid 父属性名主键 pid
	 */	
	public void setPid (Integer pid) {
		this.pid = pid;
	}
	
	/**
	 * 
	 * @return 父属性值主键 pvid
	 */
	public Integer getPvid(){
		return pvid;
	}
	
	/**
	 * 
	 * @param pvid 父属性值主键 pvid
	 */	
	public void setPvid (Integer pvid) {
		this.pvid = pvid;
	}
	
	/**
	 * 
	 * @return 属性名业务功能分类(品牌,供应商,其它) attr_name_business_type
	 */
	public Integer getAttrNameBusinessType(){
		return attrNameBusinessType;
	}
	
	/**
	 * 
	 * @param attrNameBusinessType 属性名业务功能分类(品牌,供应商,其它) attr_name_business_type
	 */	
	public void setAttrNameBusinessType (Integer attrNameBusinessType) {
		this.attrNameBusinessType = attrNameBusinessType;
	}
	
	/**
	 * 
	 * @return 属性值类型 attr_value_type
	 */
	public Integer getAttrValueType(){
		return attrValueType;
	}
	
	/**
	 * 
	 * @param attrValueType 属性值类型 attr_value_type
	 */	
	public void setAttrValueType (Integer attrValueType) {
		this.attrValueType = attrValueType;
	}
	
	/**
	 * 
	 * @return 属性名名称 attr_name
	 */
	public String getAttrName(){
		return attrName;
	}
	
	/**
	 * 
	 * @param attrName 属性名名称 attr_name
	 */	
	public void setAttrName (String attrName) {
		this.attrName = attrName;
	}
	
	/**
	 * 
	 * @return 属性名状态 attr_name_status
	 */
	public Integer getAttrNameStatus(){
		return attrNameStatus;
	}
	
	/**
	 * 
	 * @param attrNameStatus 属性名状态 attr_name_status
	 */	
	public void setAttrNameStatus (Integer attrNameStatus) {
		this.attrNameStatus = attrNameStatus;
	}
	
	/**
	 * 
	 * @return 是否搜索 is_search
	 */
	public Integer getIsSearch(){
		return isSearch;
	}
	
	/**
	 * 
	 * @param isSearch 是否搜索 is_search
	 */	
	public void setIsSearch (Integer isSearch) {
		this.isSearch = isSearch;
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
