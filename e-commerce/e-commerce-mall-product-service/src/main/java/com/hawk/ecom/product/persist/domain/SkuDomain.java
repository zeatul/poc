package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;




/**
 * 产品SKU
 * The class is mapped to the table t_prd_sku 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class SkuDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 产品主键 product_id
	 */
	private Long productId;
	
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
	 * 市场价 market_price
	 */
	private BigDecimal marketPrice;
	
	/**
	 * 销售价 sale_price
	 */
	private BigDecimal salePrice;
	
	/**
	 * 是否有特价 is_special
	 */
	private BigDecimal isSpecial;
	
	/**
	 * 宽度 width
	 */
	private Integer width;
	
	/**
	 * 深度 depth
	 */
	private Integer depth;
	
	/**
	 * 高度 heigh
	 */
	private Integer heigh;
	
	/**
	 * 长度单位 length_unit
	 */
	private Integer lengthUnit;
	
	/**
	 * 重量 weight
	 */
	private Integer weight;
	
	/**
	 * 重量单位 weight_unit
	 */
	private Integer weightUnit;
	
	/**
	 * SKU备注 SKU备注
	 */
	private String sku备注;
	
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
	 * @return 产品主键 product_id
	 */
	public Long getProductId(){
		return productId;
	}
	
	/**
	 * 
	 * @param productId 产品主键 product_id
	 */	
	public void setProductId (Long productId) {
		this.productId = productId;
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
	 * @return 是否有特价 is_special
	 */
	public BigDecimal getIsSpecial(){
		return isSpecial;
	}
	
	/**
	 * 
	 * @param isSpecial 是否有特价 is_special
	 */	
	public void setIsSpecial (BigDecimal isSpecial) {
		this.isSpecial = isSpecial;
	}
	
	/**
	 * 
	 * @return 宽度 width
	 */
	public Integer getWidth(){
		return width;
	}
	
	/**
	 * 
	 * @param width 宽度 width
	 */	
	public void setWidth (Integer width) {
		this.width = width;
	}
	
	/**
	 * 
	 * @return 深度 depth
	 */
	public Integer getDepth(){
		return depth;
	}
	
	/**
	 * 
	 * @param depth 深度 depth
	 */	
	public void setDepth (Integer depth) {
		this.depth = depth;
	}
	
	/**
	 * 
	 * @return 高度 heigh
	 */
	public Integer getHeigh(){
		return heigh;
	}
	
	/**
	 * 
	 * @param heigh 高度 heigh
	 */	
	public void setHeigh (Integer heigh) {
		this.heigh = heigh;
	}
	
	/**
	 * 
	 * @return 长度单位 length_unit
	 */
	public Integer getLengthUnit(){
		return lengthUnit;
	}
	
	/**
	 * 
	 * @param lengthUnit 长度单位 length_unit
	 */	
	public void setLengthUnit (Integer lengthUnit) {
		this.lengthUnit = lengthUnit;
	}
	
	/**
	 * 
	 * @return 重量 weight
	 */
	public Integer getWeight(){
		return weight;
	}
	
	/**
	 * 
	 * @param weight 重量 weight
	 */	
	public void setWeight (Integer weight) {
		this.weight = weight;
	}
	
	/**
	 * 
	 * @return 重量单位 weight_unit
	 */
	public Integer getWeightUnit(){
		return weightUnit;
	}
	
	/**
	 * 
	 * @param weightUnit 重量单位 weight_unit
	 */	
	public void setWeightUnit (Integer weightUnit) {
		this.weightUnit = weightUnit;
	}
	
	/**
	 * 
	 * @return SKU备注 SKU备注
	 */
	public String getSku备注(){
		return sku备注;
	}
	
	/**
	 * 
	 * @param sku备注 SKU备注 SKU备注
	 */	
	public void setSku备注 (String sku备注) {
		this.sku备注 = sku备注;
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
