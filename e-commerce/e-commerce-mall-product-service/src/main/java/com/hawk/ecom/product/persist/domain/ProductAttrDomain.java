package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 
 * The class is mapped to the table t_prd_product_attr 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class ProductAttrDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 产品主键 product_id
	 */
	private Integer productId;
	
	/**
	 * 产品SKU主键 sku_id
	 */
	private Integer skuId;
	
	/**
	 * 属性名主键 attr_name_id
	 */
	private Integer attrNameId;
	
	/**
	 * 属性值主键 attr_value_id
	 */
	private Integer attrValueId;
	
	/**
	 * 属性类型（关键属性,销售属性,一般属性） attr_name_type
	 */
	private Integer attrNameType;
	
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
	 * @return 产品主键 product_id
	 */
	public Integer getProductId(){
		return productId;
	}
	
	/**
	 * 
	 * @param productId 产品主键 product_id
	 */	
	public void setProductId (Integer productId) {
		this.productId = productId;
	}
	
	/**
	 * 
	 * @return 产品SKU主键 sku_id
	 */
	public Integer getSkuId(){
		return skuId;
	}
	
	/**
	 * 
	 * @param skuId 产品SKU主键 sku_id
	 */	
	public void setSkuId (Integer skuId) {
		this.skuId = skuId;
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
	 * @return 属性值主键 attr_value_id
	 */
	public Integer getAttrValueId(){
		return attrValueId;
	}
	
	/**
	 * 
	 * @param attrValueId 属性值主键 attr_value_id
	 */	
	public void setAttrValueId (Integer attrValueId) {
		this.attrValueId = attrValueId;
	}
	
	/**
	 * 
	 * @return 属性类型（关键属性,销售属性,一般属性） attr_name_type
	 */
	public Integer getAttrNameType(){
		return attrNameType;
	}
	
	/**
	 * 
	 * @param attrNameType 属性类型（关键属性,销售属性,一般属性） attr_name_type
	 */	
	public void setAttrNameType (Integer attrNameType) {
		this.attrNameType = attrNameType;
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
