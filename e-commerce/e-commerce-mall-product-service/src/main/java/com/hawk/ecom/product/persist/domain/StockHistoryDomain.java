package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 库存
 * The class is mapped to the table t_prd_stock_history 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class StockHistoryDomain implements Serializable {

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
	 * 商户编号 store_code
	 */
	private String storeCode;
	
	/**
	 * 仓库编号 warehouse_code
	 */
	private String warehouseCode;
	
	/**
	 * 仓库货物编号 stock_item_code
	 */
	private String stockItemCode;
	
	/**
	 * 库存数量 stock_quantity
	 */
	private Integer stockQuantity;
	
	/**
	 * 备注 stock_memo
	 */
	private String stockMemo;
	
	/**
	 * 库存操作类型 stock_operation
	 */
	private Integer stockOperation;
	
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
	 * @return 仓库编号 warehouse_code
	 */
	public String getWarehouseCode(){
		return warehouseCode;
	}
	
	/**
	 * 
	 * @param warehouseCode 仓库编号 warehouse_code
	 */	
	public void setWarehouseCode (String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	
	/**
	 * 
	 * @return 仓库货物编号 stock_item_code
	 */
	public String getStockItemCode(){
		return stockItemCode;
	}
	
	/**
	 * 
	 * @param stockItemCode 仓库货物编号 stock_item_code
	 */	
	public void setStockItemCode (String stockItemCode) {
		this.stockItemCode = stockItemCode;
	}
	
	/**
	 * 
	 * @return 库存数量 stock_quantity
	 */
	public Integer getStockQuantity(){
		return stockQuantity;
	}
	
	/**
	 * 
	 * @param stockQuantity 库存数量 stock_quantity
	 */	
	public void setStockQuantity (Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	/**
	 * 
	 * @return 备注 stock_memo
	 */
	public String getStockMemo(){
		return stockMemo;
	}
	
	/**
	 * 
	 * @param stockMemo 备注 stock_memo
	 */	
	public void setStockMemo (String stockMemo) {
		this.stockMemo = stockMemo;
	}
	
	/**
	 * 
	 * @return 库存操作类型 stock_operation
	 */
	public Integer getStockOperation(){
		return stockOperation;
	}
	
	/**
	 * 
	 * @param stockOperation 库存操作类型 stock_operation
	 */	
	public void setStockOperation (Integer stockOperation) {
		this.stockOperation = stockOperation;
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
