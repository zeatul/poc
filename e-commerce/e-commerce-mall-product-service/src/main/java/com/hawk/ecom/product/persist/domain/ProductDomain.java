package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.math.BigDecimal;
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
	private Integer id;
	
	/**
	 * 产品目录主键 category_id
	 */
	private Integer categoryId;
	
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
	 * 产品关键属性值ID集合 product_key_attr_value_ids
	 */
	private String productKeyAttrValueIds;
	
	/**
	 * 产品关键属性值集合 product_key_attr_value_values
	 */
	private String productKeyAttrValueValues;
	
	/**
	 * 产品SKU属性名ID集合 product_sku_attr_name_ids
	 */
	private String productSkuAttrNameIds;
	
	/**
	 * 产品状态 product_status
	 */
	private Integer productStatus;
	
	/**
	 * 产品主页 product_home_page
	 */
	private String productHomePage;
	
	/**
	 * 缩略图 thumbnail
	 */
	private String thumbnail;
	
	/**
	 * 产品描述 product_desc
	 */
	private String productDesc;
	
	/**
	 * 产品备注 product_memo
	 */
	private String productMemo;
	
	/**
	 * 产品最低价格 product_min_price
	 */
	private BigDecimal productMinPrice;
	
	/**
	 * 产品最高价格 product_max_price
	 */
	private BigDecimal productMaxPrice;
	
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
	 * 交付方式 delivery_type
	 */
	private Integer deliveryType;
	
	/**
	 *  产品版本号 product_version
	 */
	private Integer productVersion;
	
	/**
	 * 产品扩展属性1 product_extra1
	 */
	private String productExtra1;
	
	/**
	 * 产品扩展属性2 product_extra2
	 */
	private String productExtra2;
	
	/**
	 * 产品扩展属性3 product_extra3
	 */
	private String productExtra3;
	
	/**
	 * 产品扩展属性4 product_extra4
	 */
	private String productExtra4;
	
	/**
	 * 产品扩展属性5 product_extra5
	 */
	private String productExtra5;
	
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
	 * @return 产品关键属性值ID集合 product_key_attr_value_ids
	 */
	public String getProductKeyAttrValueIds(){
		return productKeyAttrValueIds;
	}
	
	/**
	 * 
	 * @param productKeyAttrValueIds 产品关键属性值ID集合 product_key_attr_value_ids
	 */	
	public void setProductKeyAttrValueIds (String productKeyAttrValueIds) {
		this.productKeyAttrValueIds = productKeyAttrValueIds;
	}
	
	/**
	 * 
	 * @return 产品关键属性值集合 product_key_attr_value_values
	 */
	public String getProductKeyAttrValueValues(){
		return productKeyAttrValueValues;
	}
	
	/**
	 * 
	 * @param productKeyAttrValueValues 产品关键属性值集合 product_key_attr_value_values
	 */	
	public void setProductKeyAttrValueValues (String productKeyAttrValueValues) {
		this.productKeyAttrValueValues = productKeyAttrValueValues;
	}
	
	/**
	 * 
	 * @return 产品SKU属性名ID集合 product_sku_attr_name_ids
	 */
	public String getProductSkuAttrNameIds(){
		return productSkuAttrNameIds;
	}
	
	/**
	 * 
	 * @param productSkuAttrNameIds 产品SKU属性名ID集合 product_sku_attr_name_ids
	 */	
	public void setProductSkuAttrNameIds (String productSkuAttrNameIds) {
		this.productSkuAttrNameIds = productSkuAttrNameIds;
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
	 * @return 缩略图 thumbnail
	 */
	public String getThumbnail(){
		return thumbnail;
	}
	
	/**
	 * 
	 * @param thumbnail 缩略图 thumbnail
	 */	
	public void setThumbnail (String thumbnail) {
		this.thumbnail = thumbnail;
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
	 * @return 产品最低价格 product_min_price
	 */
	public BigDecimal getProductMinPrice(){
		return productMinPrice;
	}
	
	/**
	 * 
	 * @param productMinPrice 产品最低价格 product_min_price
	 */	
	public void setProductMinPrice (BigDecimal productMinPrice) {
		this.productMinPrice = productMinPrice;
	}
	
	/**
	 * 
	 * @return 产品最高价格 product_max_price
	 */
	public BigDecimal getProductMaxPrice(){
		return productMaxPrice;
	}
	
	/**
	 * 
	 * @param productMaxPrice 产品最高价格 product_max_price
	 */	
	public void setProductMaxPrice (BigDecimal productMaxPrice) {
		this.productMaxPrice = productMaxPrice;
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
	 * @return 交付方式 delivery_type
	 */
	public Integer getDeliveryType(){
		return deliveryType;
	}
	
	/**
	 * 
	 * @param deliveryType 交付方式 delivery_type
	 */	
	public void setDeliveryType (Integer deliveryType) {
		this.deliveryType = deliveryType;
	}
	
	/**
	 * 
	 * @return  产品版本号 product_version
	 */
	public Integer getProductVersion(){
		return productVersion;
	}
	
	/**
	 * 
	 * @param productVersion  产品版本号 product_version
	 */	
	public void setProductVersion (Integer productVersion) {
		this.productVersion = productVersion;
	}
	
	/**
	 * 
	 * @return 产品扩展属性1 product_extra1
	 */
	public String getProductExtra1(){
		return productExtra1;
	}
	
	/**
	 * 
	 * @param productExtra1 产品扩展属性1 product_extra1
	 */	
	public void setProductExtra1 (String productExtra1) {
		this.productExtra1 = productExtra1;
	}
	
	/**
	 * 
	 * @return 产品扩展属性2 product_extra2
	 */
	public String getProductExtra2(){
		return productExtra2;
	}
	
	/**
	 * 
	 * @param productExtra2 产品扩展属性2 product_extra2
	 */	
	public void setProductExtra2 (String productExtra2) {
		this.productExtra2 = productExtra2;
	}
	
	/**
	 * 
	 * @return 产品扩展属性3 product_extra3
	 */
	public String getProductExtra3(){
		return productExtra3;
	}
	
	/**
	 * 
	 * @param productExtra3 产品扩展属性3 product_extra3
	 */	
	public void setProductExtra3 (String productExtra3) {
		this.productExtra3 = productExtra3;
	}
	
	/**
	 * 
	 * @return 产品扩展属性4 product_extra4
	 */
	public String getProductExtra4(){
		return productExtra4;
	}
	
	/**
	 * 
	 * @param productExtra4 产品扩展属性4 product_extra4
	 */	
	public void setProductExtra4 (String productExtra4) {
		this.productExtra4 = productExtra4;
	}
	
	/**
	 * 
	 * @return 产品扩展属性5 product_extra5
	 */
	public String getProductExtra5(){
		return productExtra5;
	}
	
	/**
	 * 
	 * @param productExtra5 产品扩展属性5 product_extra5
	 */	
	public void setProductExtra5 (String productExtra5) {
		this.productExtra5 = productExtra5;
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
