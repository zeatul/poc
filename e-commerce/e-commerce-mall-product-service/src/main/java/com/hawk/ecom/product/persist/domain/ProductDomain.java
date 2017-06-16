package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 产品
 * The class is mapped to the table t_prd_product 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class ProductDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 产品目录主键 category_id
	 */
	private Long categoryId;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * 产品编号 product_code
	 */
	private String productCode;
	
	/**
	 * 产品名称 product_name
	 */
	private String productName;
	
	/**
	 * 产品状态 product_status
	 */
	private Integer productStatus;
	
	/**
	 * 产品主页 product_home_page
	 */
	private String productHomePage;
	
	/**
	 * 产品描述 product_desc
	 */
	private String productDesc;
	
	/**
	 * 产品备注 product_memo
	 */
	private String productMemo;
	
	/**
	 * 上架开始时间 on_sale_stdt
	 */
	private Date onSaleStdt;
	
	/**
	 * 上架结束时间 on_sale_endt
	 */
	private Date onSaleEndt;
	
	/**
	 * 是否为虚拟物品 is_virtual
	 */
	private Integer isVirtual;
	
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
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 产品目录主键 category_id
	 */
	public Long getCategoryId(){
		return categoryId;
	}
	
	/**
	 * 
	 * @param categoryId 产品目录主键 category_id
	 */	
	public void setCategoryId (Long categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * 
	 * @return 商户编号 store_code
	 */
	public String getStoreCode(){
		return storeCode;
	}
	
	/**
	 * 
	 * @param storeCode 商户编号 store_code
	 */	
	public void setStoreCode (String storeCode) {
		this.storeCode = storeCode;
	}
	
	/**
	 * 
	 * @return 产品编号 product_code
	 */
	public String getProductCode(){
		return productCode;
	}
	
	/**
	 * 
	 * @param productCode 产品编号 product_code
	 */	
	public void setProductCode (String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 
	 * @return 产品名称 product_name
	 */
	public String getProductName(){
		return productName;
	}
	
	/**
	 * 
	 * @param productName 产品名称 product_name
	 */	
	public void setProductName (String productName) {
		this.productName = productName;
	}
	
	/**
	 * 
	 * @return 产品状态 product_status
	 */
	public Integer getProductStatus(){
		return productStatus;
	}
	
	/**
	 * 
	 * @param productStatus 产品状态 product_status
	 */	
	public void setProductStatus (Integer productStatus) {
		this.productStatus = productStatus;
	}
	
	/**
	 * 
	 * @return 产品主页 product_home_page
	 */
	public String getProductHomePage(){
		return productHomePage;
	}
	
	/**
	 * 
	 * @param productHomePage 产品主页 product_home_page
	 */	
	public void setProductHomePage (String productHomePage) {
		this.productHomePage = productHomePage;
	}
	
	/**
	 * 
	 * @return 产品描述 product_desc
	 */
	public String getProductDesc(){
		return productDesc;
	}
	
	/**
	 * 
	 * @param productDesc 产品描述 product_desc
	 */	
	public void setProductDesc (String productDesc) {
		this.productDesc = productDesc;
	}
	
	/**
	 * 
	 * @return 产品备注 product_memo
	 */
	public String getProductMemo(){
		return productMemo;
	}
	
	/**
	 * 
	 * @param productMemo 产品备注 product_memo
	 */	
	public void setProductMemo (String productMemo) {
		this.productMemo = productMemo;
	}
	
	/**
	 * 
	 * @return 上架开始时间 on_sale_stdt
	 */
	public Date getOnSaleStdt(){
		return onSaleStdt;
	}
	
	/**
	 * 
	 * @param onSaleStdt 上架开始时间 on_sale_stdt
	 */	
	public void setOnSaleStdt (Date onSaleStdt) {
		this.onSaleStdt = onSaleStdt;
	}
	
	/**
	 * 
	 * @return 上架结束时间 on_sale_endt
	 */
	public Date getOnSaleEndt(){
		return onSaleEndt;
	}
	
	/**
	 * 
	 * @param onSaleEndt 上架结束时间 on_sale_endt
	 */	
	public void setOnSaleEndt (Date onSaleEndt) {
		this.onSaleEndt = onSaleEndt;
	}
	
	/**
	 * 
	 * @return 是否为虚拟物品 is_virtual
	 */
	public Integer getIsVirtual(){
		return isVirtual;
	}
	
	/**
	 * 
	 * @param isVirtual 是否为虚拟物品 is_virtual
	 */	
	public void setIsVirtual (Integer isVirtual) {
		this.isVirtual = isVirtual;
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