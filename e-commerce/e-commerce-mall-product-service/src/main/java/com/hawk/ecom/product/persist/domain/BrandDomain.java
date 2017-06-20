package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 产品品牌
 * The class is mapped to the table t_prd_brand 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class BrandDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 品牌中文名 brand_cname
	 */
	private String brandCname;
	
	/**
	 * 品牌英文名 brand_ename
	 */
	private String brandEname;
	
	/**
	 * 品牌主页 brand_home_page
	 */
	private String brandHomePage;
	
	/**
	 * 品牌logo brand_home_logo
	 */
	private String brandHomeLogo;
	
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
	 * @return 品牌中文名 brand_cname
	 */
	public String getBrandCname(){
		return brandCname;
	}
	
	/**
	 * 
	 * @param brandCname 品牌中文名 brand_cname
	 */	
	public void setBrandCname (String brandCname) {
		this.brandCname = brandCname;
	}
	
	/**
	 * 
	 * @return 品牌英文名 brand_ename
	 */
	public String getBrandEname(){
		return brandEname;
	}
	
	/**
	 * 
	 * @param brandEname 品牌英文名 brand_ename
	 */	
	public void setBrandEname (String brandEname) {
		this.brandEname = brandEname;
	}
	
	/**
	 * 
	 * @return 品牌主页 brand_home_page
	 */
	public String getBrandHomePage(){
		return brandHomePage;
	}
	
	/**
	 * 
	 * @param brandHomePage 品牌主页 brand_home_page
	 */	
	public void setBrandHomePage (String brandHomePage) {
		this.brandHomePage = brandHomePage;
	}
	
	/**
	 * 
	 * @return 品牌logo brand_home_logo
	 */
	public String getBrandHomeLogo(){
		return brandHomeLogo;
	}
	
	/**
	 * 
	 * @param brandHomeLogo 品牌logo brand_home_logo
	 */	
	public void setBrandHomeLogo (String brandHomeLogo) {
		this.brandHomeLogo = brandHomeLogo;
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
