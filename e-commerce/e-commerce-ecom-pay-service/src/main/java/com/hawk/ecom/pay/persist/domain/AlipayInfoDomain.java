package com.hawk.ecom.pay.persist.domain;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;




/**
 * 支付宝支付信息
 * The class is mapped to the table t_pay_alipay_info 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class AlipayInfoDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 通知时间 notify_time
	 */
	private Date notifyTime;
	
	/**
	 * 支付宝分配给开发者的应用Id app_id
	 */
	private String appId;
	
	/**
	 * 支付宝交易凭证号 trade_no
	 */
	private String tradeNo;
	
	/**
	 * 原支付请求的商户订单号  out_trade_no
	 */
	private String outTradeNo;
	
	/**
	 * 商户业务ID out_biz_no
	 */
	private String outBizNo;
	
	/**
	 * 买家支付宝账号对应的支付宝唯一用户号 buyer_id
	 */
	private String buyerId;
	
	/**
	 *  买家支付宝账号 buyer_logon_id
	 */
	private String buyerLogonId;
	
	/**
	 * 卖家支付宝用户号 seller_id
	 */
	private String sellerId;
	
	/**
	 * 卖家支付宝账号 seller_email
	 */
	private String sellerEmail;
	
	/**
	 * 交易状态 trade_status
	 */
	private String tradeStatus;
	
	/**
	 * 本次交易支付的订单金额 total_amount
	 */
	private BigDecimal totalAmount;
	
	/**
	 * 商家在交易中实际收到的款项 receipt_amount
	 */
	private BigDecimal receiptAmount;
	
	/**
	 * 用户在交易中支付的可开发票的金额 invoice_amount
	 */
	private BigDecimal invoiceAmount;
	
	/**
	 * 用户在交易中支付的金额 buyer_pay_amount
	 */
	private BigDecimal buyerPayAmount;
	
	/**
	 * 使用集分宝支付的金额 point_amount
	 */
	private BigDecimal pointAmount;
	
	/**
	 * 总退款金额 refund_fee
	 */
	private BigDecimal refundFee;
	
	/**
	 * 订单标题 subject
	 */
	private String subject;
	
	/**
	 * 商品描述 body
	 */
	private String body;
	
	/**
	 * 交易创建时间 gmt_create
	 */
	private Date gmtCreate;
	
	/**
	 * 交易付款时间 gmt_payment
	 */
	private Date gmtPayment;
	
	/**
	 * 交易退款时间 gmt_refund
	 */
	private Date gmtRefund;
	
	/**
	 * 交易结束时间 gmt_close
	 */
	private Date gmtClose;
	
	/**
	 * 支付金额信息 fund_bill_list
	 */
	private String fundBillList;
	
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
	 * @return 通知时间 notify_time
	 */
	public Date getNotifyTime(){
		return notifyTime;
	}
	
	/**
	 * 
	 * @param notifyTime 通知时间 notify_time
	 */	
	public void setNotifyTime (Date notifyTime) {
		this.notifyTime = notifyTime;
	}
	
	/**
	 * 
	 * @return 支付宝分配给开发者的应用Id app_id
	 */
	public String getAppId(){
		return appId;
	}
	
	/**
	 * 
	 * @param appId 支付宝分配给开发者的应用Id app_id
	 */	
	public void setAppId (String appId) {
		this.appId = appId;
	}
	
	/**
	 * 
	 * @return 支付宝交易凭证号 trade_no
	 */
	public String getTradeNo(){
		return tradeNo;
	}
	
	/**
	 * 
	 * @param tradeNo 支付宝交易凭证号 trade_no
	 */	
	public void setTradeNo (String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	/**
	 * 
	 * @return 原支付请求的商户订单号  out_trade_no
	 */
	public String getOutTradeNo(){
		return outTradeNo;
	}
	
	/**
	 * 
	 * @param outTradeNo 原支付请求的商户订单号  out_trade_no
	 */	
	public void setOutTradeNo (String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	/**
	 * 
	 * @return 商户业务ID out_biz_no
	 */
	public String getOutBizNo(){
		return outBizNo;
	}
	
	/**
	 * 
	 * @param outBizNo 商户业务ID out_biz_no
	 */	
	public void setOutBizNo (String outBizNo) {
		this.outBizNo = outBizNo;
	}
	
	/**
	 * 
	 * @return 买家支付宝账号对应的支付宝唯一用户号 buyer_id
	 */
	public String getBuyerId(){
		return buyerId;
	}
	
	/**
	 * 
	 * @param buyerId 买家支付宝账号对应的支付宝唯一用户号 buyer_id
	 */	
	public void setBuyerId (String buyerId) {
		this.buyerId = buyerId;
	}
	
	/**
	 * 
	 * @return  买家支付宝账号 buyer_logon_id
	 */
	public String getBuyerLogonId(){
		return buyerLogonId;
	}
	
	/**
	 * 
	 * @param buyerLogonId  买家支付宝账号 buyer_logon_id
	 */	
	public void setBuyerLogonId (String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}
	
	/**
	 * 
	 * @return 卖家支付宝用户号 seller_id
	 */
	public String getSellerId(){
		return sellerId;
	}
	
	/**
	 * 
	 * @param sellerId 卖家支付宝用户号 seller_id
	 */	
	public void setSellerId (String sellerId) {
		this.sellerId = sellerId;
	}
	
	/**
	 * 
	 * @return 卖家支付宝账号 seller_email
	 */
	public String getSellerEmail(){
		return sellerEmail;
	}
	
	/**
	 * 
	 * @param sellerEmail 卖家支付宝账号 seller_email
	 */	
	public void setSellerEmail (String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	
	/**
	 * 
	 * @return 交易状态 trade_status
	 */
	public String getTradeStatus(){
		return tradeStatus;
	}
	
	/**
	 * 
	 * @param tradeStatus 交易状态 trade_status
	 */	
	public void setTradeStatus (String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
	/**
	 * 
	 * @return 本次交易支付的订单金额 total_amount
	 */
	public BigDecimal getTotalAmount(){
		return totalAmount;
	}
	
	/**
	 * 
	 * @param totalAmount 本次交易支付的订单金额 total_amount
	 */	
	public void setTotalAmount (BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * 
	 * @return 商家在交易中实际收到的款项 receipt_amount
	 */
	public BigDecimal getReceiptAmount(){
		return receiptAmount;
	}
	
	/**
	 * 
	 * @param receiptAmount 商家在交易中实际收到的款项 receipt_amount
	 */	
	public void setReceiptAmount (BigDecimal receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	
	/**
	 * 
	 * @return 用户在交易中支付的可开发票的金额 invoice_amount
	 */
	public BigDecimal getInvoiceAmount(){
		return invoiceAmount;
	}
	
	/**
	 * 
	 * @param invoiceAmount 用户在交易中支付的可开发票的金额 invoice_amount
	 */	
	public void setInvoiceAmount (BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	
	/**
	 * 
	 * @return 用户在交易中支付的金额 buyer_pay_amount
	 */
	public BigDecimal getBuyerPayAmount(){
		return buyerPayAmount;
	}
	
	/**
	 * 
	 * @param buyerPayAmount 用户在交易中支付的金额 buyer_pay_amount
	 */	
	public void setBuyerPayAmount (BigDecimal buyerPayAmount) {
		this.buyerPayAmount = buyerPayAmount;
	}
	
	/**
	 * 
	 * @return 使用集分宝支付的金额 point_amount
	 */
	public BigDecimal getPointAmount(){
		return pointAmount;
	}
	
	/**
	 * 
	 * @param pointAmount 使用集分宝支付的金额 point_amount
	 */	
	public void setPointAmount (BigDecimal pointAmount) {
		this.pointAmount = pointAmount;
	}
	
	/**
	 * 
	 * @return 总退款金额 refund_fee
	 */
	public BigDecimal getRefundFee(){
		return refundFee;
	}
	
	/**
	 * 
	 * @param refundFee 总退款金额 refund_fee
	 */	
	public void setRefundFee (BigDecimal refundFee) {
		this.refundFee = refundFee;
	}
	
	/**
	 * 
	 * @return 订单标题 subject
	 */
	public String getSubject(){
		return subject;
	}
	
	/**
	 * 
	 * @param subject 订单标题 subject
	 */	
	public void setSubject (String subject) {
		this.subject = subject;
	}
	
	/**
	 * 
	 * @return 商品描述 body
	 */
	public String getBody(){
		return body;
	}
	
	/**
	 * 
	 * @param body 商品描述 body
	 */	
	public void setBody (String body) {
		this.body = body;
	}
	
	/**
	 * 
	 * @return 交易创建时间 gmt_create
	 */
	public Date getGmtCreate(){
		return gmtCreate;
	}
	
	/**
	 * 
	 * @param gmtCreate 交易创建时间 gmt_create
	 */	
	public void setGmtCreate (Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	/**
	 * 
	 * @return 交易付款时间 gmt_payment
	 */
	public Date getGmtPayment(){
		return gmtPayment;
	}
	
	/**
	 * 
	 * @param gmtPayment 交易付款时间 gmt_payment
	 */	
	public void setGmtPayment (Date gmtPayment) {
		this.gmtPayment = gmtPayment;
	}
	
	/**
	 * 
	 * @return 交易退款时间 gmt_refund
	 */
	public Date getGmtRefund(){
		return gmtRefund;
	}
	
	/**
	 * 
	 * @param gmtRefund 交易退款时间 gmt_refund
	 */	
	public void setGmtRefund (Date gmtRefund) {
		this.gmtRefund = gmtRefund;
	}
	
	/**
	 * 
	 * @return 交易结束时间 gmt_close
	 */
	public Date getGmtClose(){
		return gmtClose;
	}
	
	/**
	 * 
	 * @param gmtClose 交易结束时间 gmt_close
	 */	
	public void setGmtClose (Date gmtClose) {
		this.gmtClose = gmtClose;
	}
	
	/**
	 * 
	 * @return 支付金额信息 fund_bill_list
	 */
	public String getFundBillList(){
		return fundBillList;
	}
	
	/**
	 * 
	 * @param fundBillList 支付金额信息 fund_bill_list
	 */	
	public void setFundBillList (String fundBillList) {
		this.fundBillList = fundBillList;
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
