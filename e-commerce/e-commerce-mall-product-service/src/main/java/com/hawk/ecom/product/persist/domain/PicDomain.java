package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 图片表
 * The class is mapped to the table t_prd_pic 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class PicDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 产品SKU主键 sku_id
	 */
	private Integer skuId;
	
	/**
	 * 产品主键 product_id
	 */
	private Integer productId;
	
	/**
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * 图片名称 pic_name
	 */
	private String picName;
	
	/**
	 * 图片备注 pic_memo
	 */
	private String picMemo;
	
	/**
	 * 图片外部主键 pic_outer_id
	 */
	private String picOuterId;
	
	/**
	 * 图片地址 pic_url
	 */
	private String picUrl;
	
	/**
	 * 图片类型 pic_type
	 */
	private Integer picType;
	
	/**
	 * 图片序号 object_order
	 */
	private Integer objectOrder;
	
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
	 * @return 图片名称 pic_name
	 */
	public String getPicName(){
		return picName;
	}
	
	/**
	 * 
	 * @param picName 图片名称 pic_name
	 */	
	public void setPicName (String picName) {
		this.picName = picName;
	}
	
	/**
	 * 
	 * @return 图片备注 pic_memo
	 */
	public String getPicMemo(){
		return picMemo;
	}
	
	/**
	 * 
	 * @param picMemo 图片备注 pic_memo
	 */	
	public void setPicMemo (String picMemo) {
		this.picMemo = picMemo;
	}
	
	/**
	 * 
	 * @return 图片外部主键 pic_outer_id
	 */
	public String getPicOuterId(){
		return picOuterId;
	}
	
	/**
	 * 
	 * @param picOuterId 图片外部主键 pic_outer_id
	 */	
	public void setPicOuterId (String picOuterId) {
		this.picOuterId = picOuterId;
	}
	
	/**
	 * 
	 * @return 图片地址 pic_url
	 */
	public String getPicUrl(){
		return picUrl;
	}
	
	/**
	 * 
	 * @param picUrl 图片地址 pic_url
	 */	
	public void setPicUrl (String picUrl) {
		this.picUrl = picUrl;
	}
	
	/**
	 * 
	 * @return 图片类型 pic_type
	 */
	public Integer getPicType(){
		return picType;
	}
	
	/**
	 * 
	 * @param picType 图片类型 pic_type
	 */	
	public void setPicType (Integer picType) {
		this.picType = picType;
	}
	
	/**
	 * 
	 * @return 图片序号 object_order
	 */
	public Integer getObjectOrder(){
		return objectOrder;
	}
	
	/**
	 * 
	 * @param objectOrder 图片序号 object_order
	 */	
	public void setObjectOrder (Integer objectOrder) {
		this.objectOrder = objectOrder;
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
