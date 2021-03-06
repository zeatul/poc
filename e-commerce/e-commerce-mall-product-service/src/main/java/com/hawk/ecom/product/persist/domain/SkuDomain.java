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
	private Integer id;
	
	/**
	 * 产品主键 product_id
	 */
	private Integer productId;
	
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
	 * 产品关键属性值ID集合 product_key_attr_value_ids
	 */
	private String productKeyAttrValueIds;
	
	/**
	 * 产品关键属性值集合 product_key_attr_value_values
	 */
	private String productKeyAttrValueValues;
	
	/**
	 * SKU全部属性值ID集合(关键+SKU) all_attr_value_ids
	 */
	private String allAttrValueIds;
	
	/**
	 * SKU全部属性值集合(关键+SKU) all_attr_value_values
	 */
	private String allAttrValueValues;
	
	/**
	 * SKU状态 sku_status
	 */
	private Integer skuStatus;
	
	/**
	 * 缩略图 thumbnail
	 */
	private String thumbnail;
	
	/**
	 * 市场价 market_price
	 */
	private BigDecimal marketPrice;
	
	/**
	 * 销售价 sale_price
	 */
	private BigDecimal salePrice;
	
	/**
	 * 币种 currency
	 */
	private Integer currency;
	
	/**
	 * SKU库存数量 sku_stock_quantity
	 */
	private Integer skuStockQuantity;
	
	/**
	 * 是否有特价 is_special_price
	 */
	private Integer isSpecialPrice;
	
	/**
	 * 宽度 width
	 */
	private Integer width;
	
	/**
	 * 深度 depth
	 */
	private Integer depth;
	
	/**
	 * 高度 height
	 */
	private Integer height;
	
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
	 * SKU备注 sku_memo
	 */
	private String skuMemo;
	
	/**
	 * SKU快照ID sku_snapshoot_id
	 */
	private Integer skuSnapshootId;
	
	/**
	 * 产品版本号 product_version
	 */
	private Integer productVersion;
	
	/**
	 * sku版本号 sku_version
	 */
	private Integer skuVersion;
	
	/**
	 * 交付方式 delivery_type
	 */
	private Integer deliveryType;
	
	/**
	 * 库存版本号 stock_version
	 */
	private Integer stockVersion;
	
	/**
	 * 产品Sku扩展属性1 sku_extra1
	 */
	private String skuExtra1;
	
	/**
	 * 产品Sku扩展属性2 sku_extra2
	 */
	private String skuExtra2;
	
	/**
	 * 产品Sku扩展属性3 sku_extra3
	 */
	private String skuExtra3;
	
	/**
	 * 产品Sku扩展属性4 sku_extra4
	 */
	private String skuExtra4;
	
	/**
	 * 产品Sku扩展属性5 sku_extra5
	 */
	private String skuExtra5;
	
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
	 * @return SKU全部属性值ID集合(关键+SKU) all_attr_value_ids
	 */
	public String getAllAttrValueIds(){
		return allAttrValueIds;
	}
	
	/**
	 * 
	 * @param allAttrValueIds SKU全部属性值ID集合(关键+SKU) all_attr_value_ids
	 */	
	public void setAllAttrValueIds (String allAttrValueIds) {
		this.allAttrValueIds = allAttrValueIds;
	}
	
	/**
	 * 
	 * @return SKU全部属性值集合(关键+SKU) all_attr_value_values
	 */
	public String getAllAttrValueValues(){
		return allAttrValueValues;
	}
	
	/**
	 * 
	 * @param allAttrValueValues SKU全部属性值集合(关键+SKU) all_attr_value_values
	 */	
	public void setAllAttrValueValues (String allAttrValueValues) {
		this.allAttrValueValues = allAttrValueValues;
	}
	
	/**
	 * 
	 * @return SKU状态 sku_status
	 */
	public Integer getSkuStatus(){
		return skuStatus;
	}
	
	/**
	 * 
	 * @param skuStatus SKU状态 sku_status
	 */	
	public void setSkuStatus (Integer skuStatus) {
		this.skuStatus = skuStatus;
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
	 * @return 币种 currency
	 */
	public Integer getCurrency(){
		return currency;
	}
	
	/**
	 * 
	 * @param currency 币种 currency
	 */	
	public void setCurrency (Integer currency) {
		this.currency = currency;
	}
	
	/**
	 * 
	 * @return SKU库存数量 sku_stock_quantity
	 */
	public Integer getSkuStockQuantity(){
		return skuStockQuantity;
	}
	
	/**
	 * 
	 * @param skuStockQuantity SKU库存数量 sku_stock_quantity
	 */	
	public void setSkuStockQuantity (Integer skuStockQuantity) {
		this.skuStockQuantity = skuStockQuantity;
	}
	
	/**
	 * 
	 * @return 是否有特价 is_special_price
	 */
	public Integer getIsSpecialPrice(){
		return isSpecialPrice;
	}
	
	/**
	 * 
	 * @param isSpecialPrice 是否有特价 is_special_price
	 */	
	public void setIsSpecialPrice (Integer isSpecialPrice) {
		this.isSpecialPrice = isSpecialPrice;
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
	 * @return 高度 height
	 */
	public Integer getHeight(){
		return height;
	}
	
	/**
	 * 
	 * @param height 高度 height
	 */	
	public void setHeight (Integer height) {
		this.height = height;
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
	 * @return SKU快照ID sku_snapshoot_id
	 */
	public Integer getSkuSnapshootId(){
		return skuSnapshootId;
	}
	
	/**
	 * 
	 * @param skuSnapshootId SKU快照ID sku_snapshoot_id
	 */	
	public void setSkuSnapshootId (Integer skuSnapshootId) {
		this.skuSnapshootId = skuSnapshootId;
	}
	
	/**
	 * 
	 * @return 产品版本号 product_version
	 */
	public Integer getProductVersion(){
		return productVersion;
	}
	
	/**
	 * 
	 * @param productVersion 产品版本号 product_version
	 */	
	public void setProductVersion (Integer productVersion) {
		this.productVersion = productVersion;
	}
	
	/**
	 * 
	 * @return sku版本号 sku_version
	 */
	public Integer getSkuVersion(){
		return skuVersion;
	}
	
	/**
	 * 
	 * @param skuVersion sku版本号 sku_version
	 */	
	public void setSkuVersion (Integer skuVersion) {
		this.skuVersion = skuVersion;
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
	 * @return 库存版本号 stock_version
	 */
	public Integer getStockVersion(){
		return stockVersion;
	}
	
	/**
	 * 
	 * @param stockVersion 库存版本号 stock_version
	 */	
	public void setStockVersion (Integer stockVersion) {
		this.stockVersion = stockVersion;
	}
	
	/**
	 * 
	 * @return 产品Sku扩展属性1 sku_extra1
	 */
	public String getSkuExtra1(){
		return skuExtra1;
	}
	
	/**
	 * 
	 * @param skuExtra1 产品Sku扩展属性1 sku_extra1
	 */	
	public void setSkuExtra1 (String skuExtra1) {
		this.skuExtra1 = skuExtra1;
	}
	
	/**
	 * 
	 * @return 产品Sku扩展属性2 sku_extra2
	 */
	public String getSkuExtra2(){
		return skuExtra2;
	}
	
	/**
	 * 
	 * @param skuExtra2 产品Sku扩展属性2 sku_extra2
	 */	
	public void setSkuExtra2 (String skuExtra2) {
		this.skuExtra2 = skuExtra2;
	}
	
	/**
	 * 
	 * @return 产品Sku扩展属性3 sku_extra3
	 */
	public String getSkuExtra3(){
		return skuExtra3;
	}
	
	/**
	 * 
	 * @param skuExtra3 产品Sku扩展属性3 sku_extra3
	 */	
	public void setSkuExtra3 (String skuExtra3) {
		this.skuExtra3 = skuExtra3;
	}
	
	/**
	 * 
	 * @return 产品Sku扩展属性4 sku_extra4
	 */
	public String getSkuExtra4(){
		return skuExtra4;
	}
	
	/**
	 * 
	 * @param skuExtra4 产品Sku扩展属性4 sku_extra4
	 */	
	public void setSkuExtra4 (String skuExtra4) {
		this.skuExtra4 = skuExtra4;
	}
	
	/**
	 * 
	 * @return 产品Sku扩展属性5 sku_extra5
	 */
	public String getSkuExtra5(){
		return skuExtra5;
	}
	
	/**
	 * 
	 * @param skuExtra5 产品Sku扩展属性5 sku_extra5
	 */	
	public void setSkuExtra5 (String skuExtra5) {
		this.skuExtra5 = skuExtra5;
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
