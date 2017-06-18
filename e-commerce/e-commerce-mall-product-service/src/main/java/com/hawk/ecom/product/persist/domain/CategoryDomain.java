package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 产品目录
 * The class is mapped to the table t_prd_category 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class CategoryDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 父ID pid
	 */
	private Integer pid;
	
	/**
	 * 主键PATH id_path
	 */
	private String idPath;
	
	/**
	 * 产品目录序号 object_order
	 */
	private Integer objectOrder;
	
	/**
	 * 产品目录深度 depth
	 */
	private Integer depth;
	
	/**
	 * 产品目录编号 category_code
	 */
	private String categoryCode;
	
	/**
	 * 产品目录名称 category_name
	 */
	private String categoryName;
	
	/**
	 * 产品目录状态 category_status
	 */
	private Integer categoryStatus;
	
	/**
	 * 产品目录描述 category_desc
	 */
	private String categoryDesc;
	
	/**
	 * 产品目录logo category_logo
	 */
	private String categoryLogo;
	
	/**
	 * 产品目录主页 category_home_page
	 */
	private String categoryHomePage;
	
	/**
	 * 是否为最终产品目录分类，最终产品目录分类才能有商品模板 is_leaf
	 */
	private Integer isLeaf;
	
	/**
	 * 最终产品目录模板状态 category_template_status
	 */
	private Integer categoryTemplateStatus;
	
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
	 * @return 父ID pid
	 */
	public Integer getPid(){
		return pid;
	}
	
	/**
	 * 
	 * @param pid 父ID pid
	 */	
	public void setPid (Integer pid) {
		this.pid = pid;
	}
	
	/**
	 * 
	 * @return 主键PATH id_path
	 */
	public String getIdPath(){
		return idPath;
	}
	
	/**
	 * 
	 * @param idPath 主键PATH id_path
	 */	
	public void setIdPath (String idPath) {
		this.idPath = idPath;
	}
	
	/**
	 * 
	 * @return 产品目录序号 object_order
	 */
	public Integer getObjectOrder(){
		return objectOrder;
	}
	
	/**
	 * 
	 * @param objectOrder 产品目录序号 object_order
	 */	
	public void setObjectOrder (Integer objectOrder) {
		this.objectOrder = objectOrder;
	}
	
	/**
	 * 
	 * @return 产品目录深度 depth
	 */
	public Integer getDepth(){
		return depth;
	}
	
	/**
	 * 
	 * @param depth 产品目录深度 depth
	 */	
	public void setDepth (Integer depth) {
		this.depth = depth;
	}
	
	/**
	 * 
	 * @return 产品目录编号 category_code
	 */
	public String getCategoryCode(){
		return categoryCode;
	}
	
	/**
	 * 
	 * @param categoryCode 产品目录编号 category_code
	 */	
	public void setCategoryCode (String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	/**
	 * 
	 * @return 产品目录名称 category_name
	 */
	public String getCategoryName(){
		return categoryName;
	}
	
	/**
	 * 
	 * @param categoryName 产品目录名称 category_name
	 */	
	public void setCategoryName (String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * 
	 * @return 产品目录状态 category_status
	 */
	public Integer getCategoryStatus(){
		return categoryStatus;
	}
	
	/**
	 * 
	 * @param categoryStatus 产品目录状态 category_status
	 */	
	public void setCategoryStatus (Integer categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	
	/**
	 * 
	 * @return 产品目录描述 category_desc
	 */
	public String getCategoryDesc(){
		return categoryDesc;
	}
	
	/**
	 * 
	 * @param categoryDesc 产品目录描述 category_desc
	 */	
	public void setCategoryDesc (String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	/**
	 * 
	 * @return 产品目录logo category_logo
	 */
	public String getCategoryLogo(){
		return categoryLogo;
	}
	
	/**
	 * 
	 * @param categoryLogo 产品目录logo category_logo
	 */	
	public void setCategoryLogo (String categoryLogo) {
		this.categoryLogo = categoryLogo;
	}
	
	/**
	 * 
	 * @return 产品目录主页 category_home_page
	 */
	public String getCategoryHomePage(){
		return categoryHomePage;
	}
	
	/**
	 * 
	 * @param categoryHomePage 产品目录主页 category_home_page
	 */	
	public void setCategoryHomePage (String categoryHomePage) {
		this.categoryHomePage = categoryHomePage;
	}
	
	/**
	 * 
	 * @return 是否为最终产品目录分类，最终产品目录分类才能有商品模板 is_leaf
	 */
	public Integer getIsLeaf(){
		return isLeaf;
	}
	
	/**
	 * 
	 * @param isLeaf 是否为最终产品目录分类，最终产品目录分类才能有商品模板 is_leaf
	 */	
	public void setIsLeaf (Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	/**
	 * 
	 * @return 最终产品目录模板状态 category_template_status
	 */
	public Integer getCategoryTemplateStatus(){
		return categoryTemplateStatus;
	}
	
	/**
	 * 
	 * @param categoryTemplateStatus 最终产品目录模板状态 category_template_status
	 */	
	public void setCategoryTemplateStatus (Integer categoryTemplateStatus) {
		this.categoryTemplateStatus = categoryTemplateStatus;
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
