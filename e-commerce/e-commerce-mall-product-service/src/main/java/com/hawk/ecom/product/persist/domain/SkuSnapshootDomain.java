package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




/**
 * 
 * The class is mapped to the table t_prd_sku_snapshoot 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class SkuSnapshootDomain implements Serializable {

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
	 * 产品sku主键 sku_id
	 */
	private Integer skuId;
	
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
	 * 产品备注 product_memo
	 */
	private String productMemo;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * SKU编号 sku_code
	 */
	private String skuCode;
	
	/**
	 * SKU名称 sku_name
	 */
	private String skuName;
	
	/**
	 * SKU属性值ID集合 sku_attr_value_ids
	 */
	private String skuAttrValueIds;
	
	/**
	 * SKU属性值集合 sku_attr_value_values
	 */
	private String skuAttrValueValues;
	
	/**
	 * 市场价 market_price
	 */
	private BigDecimal marketPrice;
	
	/**
	 * 销售价 sale_price
	 */
	private BigDecimal salePrice;
	
	/**
	 * SKU备注 sku_memo
	 */
	private String skuMemo;
	
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
	 * @return 产品sku主键 sku_id
	 */
	public Integer getSkuId(){
		return skuId;
	}
	
	/**
	 * 
	 * @param skuId 产品sku主键 sku_id
	 */	
	public void setSkuId (Integer skuId) {
		this.skuId = skuId;
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
	 * @return SKU编号 sku_code
	 */
	public String getSkuCode(){
		return skuCode;
	}
	
	/**
	 * 
	 * @param skuCode SKU编号 sku_code
	 */	
	public void setSkuCode (String skuCode) {
		this.skuCode = skuCode;
	}
	
	/**
	 * 
	 * @return SKU名称 sku_name
	 */
	public String getSkuName(){
		return skuName;
	}
	
	/**
	 * 
	 * @param skuName SKU名称 sku_name
	 */	
	public void setSkuName (String skuName) {
		this.skuName = skuName;
	}
	
	/**
	 * 
	 * @return SKU属性值ID集合 sku_attr_value_ids
	 */
	public String getSkuAttrValueIds(){
		return skuAttrValueIds;
	}
	
	/**
	 * 
	 * @param skuAttrValueIds SKU属性值ID集合 sku_attr_value_ids
	 */	
	public void setSkuAttrValueIds (String skuAttrValueIds) {
		this.skuAttrValueIds = skuAttrValueIds;
	}
	
	/**
	 * 
	 * @return SKU属性值集合 sku_attr_value_values
	 */
	public String getSkuAttrValueValues(){
		return skuAttrValueValues;
	}
	
	/**
	 * 
	 * @param skuAttrValueValues SKU属性值集合 sku_attr_value_values
	 */	
	public void setSkuAttrValueValues (String skuAttrValueValues) {
		this.skuAttrValueValues = skuAttrValueValues;
	}
	
	/**
	 * 
	 * @return 市场价 market_price
	 */
	public BigDecimal getMarketPrice(){
		return marketPrice;
	}
	
	/**
	 * 
	 * @param marketPrice 市场价 market_price
	 */	
	public void setMarketPrice (BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	/**
	 * 
	 * @return 销售价 sale_price
	 */
	public BigDecimal getSalePrice(){
		return salePrice;
	}
	
	/**
	 * 
	 * @param salePrice 销售价 sale_price
	 */	
	public void setSalePrice (BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	/**
	 * 
	 * @return SKU备注 sku_memo
	 */
	public String getSkuMemo(){
		return skuMemo;
	}
	
	/**
	 * 
	 * @param skuMemo SKU备注 sku_memo
	 */	
	public void setSkuMemo (String skuMemo) {
		this.skuMemo = skuMemo;
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
