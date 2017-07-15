package com.hawk.ecom.pay.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 支付分类
 * The class is mapped to the table t_pay_payment_category 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class PaymentCategoryDomain implements Serializable {

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
	 * 树深度 depth
	 */
	private Integer depth;
	
	/**
	 * 支付分类编号 payment_category_code
	 */
	private String paymentCategoryCode;
	
	/**
	 * 支付分类名称 payment_category_name
	 */
	private String paymentCategoryName;
	
	/**
	 * 支付分类状态 payment_category_status
	 */
	private Integer paymentCategoryStatus;
	
	/**
	 * 支付分类描述 payment_category_desc
	 */
	private String paymentCategoryDesc;
	
	/**
	 * 支付分类备注 payment_category_memo
	 */
	private String paymentCategoryMemo;
	
	/**
	 * 支付分类Logo payment_category_logo
	 */
	private String paymentCategoryLogo;
	
	/**
	 * 是否为叶子节点 is_leaf
	 */
	private Integer isLeaf;
	
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
	 * @return 树深度 depth
	 */
	public Integer getDepth(){
		return depth;
	}
	
	/**
	 * 
	 * @param depth 树深度 depth
	 */	
	public void setDepth (Integer depth) {
		this.depth = depth;
	}
	
	/**
	 * 
	 * @return 支付分类编号 payment_category_code
	 */
	public String getPaymentCategoryCode(){
		return paymentCategoryCode;
	}
	
	/**
	 * 
	 * @param paymentCategoryCode 支付分类编号 payment_category_code
	 */	
	public void setPaymentCategoryCode (String paymentCategoryCode) {
		this.paymentCategoryCode = paymentCategoryCode;
	}
	
	/**
	 * 
	 * @return 支付分类名称 payment_category_name
	 */
	public String getPaymentCategoryName(){
		return paymentCategoryName;
	}
	
	/**
	 * 
	 * @param paymentCategoryName 支付分类名称 payment_category_name
	 */	
	public void setPaymentCategoryName (String paymentCategoryName) {
		this.paymentCategoryName = paymentCategoryName;
	}
	
	/**
	 * 
	 * @return 支付分类状态 payment_category_status
	 */
	public Integer getPaymentCategoryStatus(){
		return paymentCategoryStatus;
	}
	
	/**
	 * 
	 * @param paymentCategoryStatus 支付分类状态 payment_category_status
	 */	
	public void setPaymentCategoryStatus (Integer paymentCategoryStatus) {
		this.paymentCategoryStatus = paymentCategoryStatus;
	}
	
	/**
	 * 
	 * @return 支付分类描述 payment_category_desc
	 */
	public String getPaymentCategoryDesc(){
		return paymentCategoryDesc;
	}
	
	/**
	 * 
	 * @param paymentCategoryDesc 支付分类描述 payment_category_desc
	 */	
	public void setPaymentCategoryDesc (String paymentCategoryDesc) {
		this.paymentCategoryDesc = paymentCategoryDesc;
	}
	
	/**
	 * 
	 * @return 支付分类备注 payment_category_memo
	 */
	public String getPaymentCategoryMemo(){
		return paymentCategoryMemo;
	}
	
	/**
	 * 
	 * @param paymentCategoryMemo 支付分类备注 payment_category_memo
	 */	
	public void setPaymentCategoryMemo (String paymentCategoryMemo) {
		this.paymentCategoryMemo = paymentCategoryMemo;
	}
	
	/**
	 * 
	 * @return 支付分类Logo payment_category_logo
	 */
	public String getPaymentCategoryLogo(){
		return paymentCategoryLogo;
	}
	
	/**
	 * 
	 * @param paymentCategoryLogo 支付分类Logo payment_category_logo
	 */	
	public void setPaymentCategoryLogo (String paymentCategoryLogo) {
		this.paymentCategoryLogo = paymentCategoryLogo;
	}
	
	/**
	 * 
	 * @return 是否为叶子节点 is_leaf
	 */
	public Integer getIsLeaf(){
		return isLeaf;
	}
	
	/**
	 * 
	 * @param isLeaf 是否为叶子节点 is_leaf
	 */	
	public void setIsLeaf (Integer isLeaf) {
		this.isLeaf = isLeaf;
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
