package com.hawk.ecom.pay.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 微信支付信息
 * The class is mapped to the table t_pay_wxpay_info 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class WxpayInfoDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 公众账号ID appid
	 */
	private String appid;
	
	/**
	 * 商户号 mch_id
	 */
	private String mchId;
	
	/**
	 * 设备号 device_info
	 */
	private String deviceInfo;
	
	/**
	 * 业务结果 result_code
	 */
	private String resultCode;
	
	/**
	 * 错误代码 err_code
	 */
	private String errCode;
	
	/**
	 * 错误代码描述 err_code_des
	 */
	private String errCodeDes;
	
	/**
	 * 用户在商户appid下的唯一标识 openid
	 */
	private String openid;
	
	/**
	 * 交易类型JSAPI JSAPI、NATIVE、APP trade_type
	 */
	private String tradeType;
	
	/**
	 * 付款银行 bank_type
	 */
	private String bankType;
	
	/**
	 * 订单总金额 total_fee
	 */
	private Integer totalFee;
	
	/**
	 * 应结订单金额 settlement_total_fee
	 */
	private Integer settlementTotalFee;
	
	/**
	 * 货币种类 fee_type
	 */
	private String feeType;
	
	/**
	 * 现金支付金额 cash_fee
	 */
	private Integer cashFee;
	
	/**
	 * 现金支付货币类型 cash_fee_type
	 */
	private String cashFeeType;
	
	/**
	 * 总代金券金额 coupon_fee
	 */
	private Integer couponFee;
	
	/**
	 * 代金券使用数量 coupon_count
	 */
	private Integer couponCount;
	
	/**
	 * 微信支付订单号 transaction_id
	 */
	private String transactionId;
	
	/**
	 * 商户订单号 out_trade_no
	 */
	private String outTradeNo;
	
	/**
	 * 支付完成时间 time_end
	 */
	private String timeEnd;
	
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
	 * @return 公众账号ID appid
	 */
	public String getAppid(){
		return appid;
	}
	
	/**
	 * 
	 * @param appid 公众账号ID appid
	 */	
	public void setAppid (String appid) {
		this.appid = appid;
	}
	
	/**
	 * 
	 * @return 商户号 mch_id
	 */
	public String getMchId(){
		return mchId;
	}
	
	/**
	 * 
	 * @param mchId 商户号 mch_id
	 */	
	public void setMchId (String mchId) {
		this.mchId = mchId;
	}
	
	/**
	 * 
	 * @return 设备号 device_info
	 */
	public String getDeviceInfo(){
		return deviceInfo;
	}
	
	/**
	 * 
	 * @param deviceInfo 设备号 device_info
	 */	
	public void setDeviceInfo (String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	/**
	 * 
	 * @return 业务结果 result_code
	 */
	public String getResultCode(){
		return resultCode;
	}
	
	/**
	 * 
	 * @param resultCode 业务结果 result_code
	 */	
	public void setResultCode (String resultCode) {
		this.resultCode = resultCode;
	}
	
	/**
	 * 
	 * @return 错误代码 err_code
	 */
	public String getErrCode(){
		return errCode;
	}
	
	/**
	 * 
	 * @param errCode 错误代码 err_code
	 */	
	public void setErrCode (String errCode) {
		this.errCode = errCode;
	}
	
	/**
	 * 
	 * @return 错误代码描述 err_code_des
	 */
	public String getErrCodeDes(){
		return errCodeDes;
	}
	
	/**
	 * 
	 * @param errCodeDes 错误代码描述 err_code_des
	 */	
	public void setErrCodeDes (String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	
	/**
	 * 
	 * @return 用户在商户appid下的唯一标识 openid
	 */
	public String getOpenid(){
		return openid;
	}
	
	/**
	 * 
	 * @param openid 用户在商户appid下的唯一标识 openid
	 */	
	public void setOpenid (String openid) {
		this.openid = openid;
	}
	
	/**
	 * 
	 * @return 交易类型JSAPI JSAPI、NATIVE、APP trade_type
	 */
	public String getTradeType(){
		return tradeType;
	}
	
	/**
	 * 
	 * @param tradeType 交易类型JSAPI JSAPI、NATIVE、APP trade_type
	 */	
	public void setTradeType (String tradeType) {
		this.tradeType = tradeType;
	}
	
	/**
	 * 
	 * @return 付款银行 bank_type
	 */
	public String getBankType(){
		return bankType;
	}
	
	/**
	 * 
	 * @param bankType 付款银行 bank_type
	 */	
	public void setBankType (String bankType) {
		this.bankType = bankType;
	}
	
	/**
	 * 
	 * @return 订单总金额 total_fee
	 */
	public Integer getTotalFee(){
		return totalFee;
	}
	
	/**
	 * 
	 * @param totalFee 订单总金额 total_fee
	 */	
	public void setTotalFee (Integer totalFee) {
		this.totalFee = totalFee;
	}
	
	/**
	 * 
	 * @return 应结订单金额 settlement_total_fee
	 */
	public Integer getSettlementTotalFee(){
		return settlementTotalFee;
	}
	
	/**
	 * 
	 * @param settlementTotalFee 应结订单金额 settlement_total_fee
	 */	
	public void setSettlementTotalFee (Integer settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}
	
	/**
	 * 
	 * @return 货币种类 fee_type
	 */
	public String getFeeType(){
		return feeType;
	}
	
	/**
	 * 
	 * @param feeType 货币种类 fee_type
	 */	
	public void setFeeType (String feeType) {
		this.feeType = feeType;
	}
	
	/**
	 * 
	 * @return 现金支付金额 cash_fee
	 */
	public Integer getCashFee(){
		return cashFee;
	}
	
	/**
	 * 
	 * @param cashFee 现金支付金额 cash_fee
	 */	
	public void setCashFee (Integer cashFee) {
		this.cashFee = cashFee;
	}
	
	/**
	 * 
	 * @return 现金支付货币类型 cash_fee_type
	 */
	public String getCashFeeType(){
		return cashFeeType;
	}
	
	/**
	 * 
	 * @param cashFeeType 现金支付货币类型 cash_fee_type
	 */	
	public void setCashFeeType (String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	
	/**
	 * 
	 * @return 总代金券金额 coupon_fee
	 */
	public Integer getCouponFee(){
		return couponFee;
	}
	
	/**
	 * 
	 * @param couponFee 总代金券金额 coupon_fee
	 */	
	public void setCouponFee (Integer couponFee) {
		this.couponFee = couponFee;
	}
	
	/**
	 * 
	 * @return 代金券使用数量 coupon_count
	 */
	public Integer getCouponCount(){
		return couponCount;
	}
	
	/**
	 * 
	 * @param couponCount 代金券使用数量 coupon_count
	 */	
	public void setCouponCount (Integer couponCount) {
		this.couponCount = couponCount;
	}
	
	/**
	 * 
	 * @return 微信支付订单号 transaction_id
	 */
	public String getTransactionId(){
		return transactionId;
	}
	
	/**
	 * 
	 * @param transactionId 微信支付订单号 transaction_id
	 */	
	public void setTransactionId (String transactionId) {
		this.transactionId = transactionId;
	}
	
	/**
	 * 
	 * @return 商户订单号 out_trade_no
	 */
	public String getOutTradeNo(){
		return outTradeNo;
	}
	
	/**
	 * 
	 * @param outTradeNo 商户订单号 out_trade_no
	 */	
	public void setOutTradeNo (String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	/**
	 * 
	 * @return 支付完成时间 time_end
	 */
	public String getTimeEnd(){
		return timeEnd;
	}
	
	/**
	 * 
	 * @param timeEnd 支付完成时间 time_end
	 */	
	public void setTimeEnd (String timeEnd) {
		this.timeEnd = timeEnd;
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
