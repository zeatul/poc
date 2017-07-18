package com.hawk.ecom.outer.service;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.utility.http.HttpClientExecutorImpl;
import com.hawk.framework.utility.http.HttpExecutor;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class ChargeDataService {

	private final static String API_KEY = "tiexie";

	private final static String SECURITY_KEY = "hyzzyyz4p2";

	private final String NOTIFY_URL = "https://www.sina.com.cn";
	
	private final String CHARGE_URL = "http://load.flow.shziyuan.cn:8080/dwi/open-api/rest/recharge";
	private final String QUERY_URL = "http://load.flow.shziyuan.cn:8001/open-api/rest/recharge/status";
	
	@Autowired
	private HttpExecutor httpExecutor ;
	
	public ChargeDataService() throws Exception{
		this.httpExecutor = new HttpClientExecutorImpl();
	}
	

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public interface ConstChargeResponseStatus{
//		提交阶段
//		0000 受理成功
//		0001 受理失败,提交订单错误见msg字段
//		0008 受理失败,提交订单错误见msg字段
		public String SUCCESS = "0000";
		public String FAILURE_1 = "0001";
		public String FAILURE_8 = "0008";

	}
	
	public interface ConstChargeNotifyStatus{
//		状态报告阶段
//		0007 订购成功
//		0008 订购失败,提交订单错误见msg字段
//		0009 订单处理中,等待后续状态推送
		public String  SUCCESS = "0007";
		public String FAILURE = "0008";
		public String PROCESSING = "0009";
	}

	public static class ChargeRequest {
		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

		public String getNotifyUrl() {
			return notifyUrl;
		}

		public void setNotifyUrl(String notifyUrl) {
			this.notifyUrl = notifyUrl;
		}

		public String getCstmOrderNo() {
			return cstmOrderNo;
		}

		public void setCstmOrderNo(String cstmOrderNo) {
			this.cstmOrderNo = cstmOrderNo;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public String getSign() {
			return sign;
		}

		public void setSign(String sign) {
			this.sign = sign;
		}

		public String getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(String timeStamp) {
			this.timeStamp = timeStamp;
		}


		private String apiKey;
		private String notifyUrl;
		private String cstmOrderNo;
		private String phone;
		private String productCode;
		private String sign;
		private String timeStamp;
	}

	
	public static class ChargeResponse{
//		"code": "0000",
//		"msg": "成功",
//		"data": {
//		"status": "0",
//		"orderNo": "12334354545454",
//		"cstmOrderNo": "13512345631",
//		"errorDesc": "受理中"
//		}
		
		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public ChargeResponseData getData() {
			return data;
		}

		public void setData(ChargeResponseData data) {
			this.data = data;
		}

		/**
		 * 接口状态编码0000为成功 0001为失败
		 */
		private String code ;
		/**
		 * 描述
		 */
		private String msg ;
		
		/**
		 * data 返回业务数据code为0001时，无data数据
		 */
		private ChargeResponseData data;
		
	}
	
	
	public static class ChargeResponseData{
		public String getOrderNo() {
			return orderNo;
		}

		
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getCstmOrderNo() {
			return cstmOrderNo;
		}

		public void setCstmOrderNo(String cstmOrderNo) {
			this.cstmOrderNo = cstmOrderNo;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getErrorDesc() {
			return errorDesc;
		}

		public void setErrorDesc(String errorDesc) {
			this.errorDesc = errorDesc;
		}

		/**
		 * 服务方系统订单流水号32位
		 */
		private String orderNo ;
		/**
		 * 调用方流水号
		 */
		private String cstmOrderNo ;
		
		/**
		 * 业务状态0：订单已受理 8失败
		 */
		private String status ;
		
		/**
		 * 错误描述当status为8时不为空
		 */
		private String errorDesc ;
	}
	
	public ChargeRequest buildChargeRequest(String mobileNumber,String productCode, String taskCode) throws Exception{
		ChargeRequest chargeRequest = new ChargeRequest();
		chargeRequest.setApiKey(API_KEY);
		chargeRequest.setCstmOrderNo(taskCode);
		chargeRequest.setNotifyUrl(NOTIFY_URL);
		chargeRequest.setPhone(mobileNumber);
		chargeRequest.setProductCode(productCode);		
		chargeRequest.setTimeStamp(DateTools.convert(new Date(), "yyyyMMddHHmmss"));
		
		chargeRequest.setSign(coumputeSign(chargeRequest));
		return chargeRequest;
	}
	
	private String coumputeSign(Object object) throws Exception{
		String sign = DomainTools.buildSignString(object);
		sign = StringTools.concat(sign,SECURITY_KEY);
		sign = DigestUtils.sha1Hex(sign.getBytes("utf-8"));
		return sign;
	}

	public ChargeResult charge(String mobileNumber, String productCode, String taskCode) throws Exception {
		logger.info("Start charge , mobileNumber={},productCode={},taskCode={}",mobileNumber,productCode,taskCode);
		ChargeRequest chargeRequest = buildChargeRequest(mobileNumber,productCode,taskCode);		
		String jsonStr = httpExecutor.post(CHARGE_URL, chargeRequest, null);
		ChargeResponse chargeResponse = JsonTools.toObject(jsonStr, ChargeResponse.class);
		logger.info("Charge Response : taksCode={},result = {}",taskCode,jsonStr);
		ChargeResult chargeResult = new ChargeResult();
		if (chargeResponse.getCode().equals(ConstChargeResponseStatus.SUCCESS)){
			chargeResult.setSuccess(true);
		}else{
			chargeResult.setSuccess(false);
			chargeResult.setErrMsg(chargeResponse.getMsg());
			chargeResult.setErrCode(chargeResponse.getCode());
		}
		ChargeResponseData chargeResponseData = chargeResponse.getData();
		if (chargeResponseData != null){
			if (chargeResponseData.getStatus().equals("0")){
				chargeResult.setOuterOrderCode(chargeResponseData.getOrderNo());
			}else{
				chargeResult.setSuccess(false);
				
				String errCode = chargeResult.getErrCode();
				String errMsg = chargeResult.getErrMsg();
				
				errCode = errCode == null ? chargeResponseData.getStatus() : StringTools.concatWithSymbol(":", errCode,chargeResponseData.getStatus());
				errMsg = errMsg == null ? chargeResponseData.getErrorDesc() : StringTools.concatWithSymbol(":", errMsg,chargeResponseData.getErrorDesc());
				chargeResult.setErrMsg(errMsg);
				chargeResult.setErrCode(errCode);
			}
		}
		return chargeResult;
	}

	public static class QueryRequest{
		
		public String getApiKey() {
			return apiKey;
		}
		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}
		public String getTimeStamp() {
			return timeStamp;
		}
		public void setTimeStamp(String timeStamp) {
			this.timeStamp = timeStamp;
		}
		public String getOrder_no() {
			return order_no;
		}
		public void setOrder_no(String order_no) {
			this.order_no = order_no;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		private String apiKey;
		private String timeStamp;
		private String order_no;
		private String sign;
		
	}
	

	private  QueryRequest buildQueryRequest(String orderNo) throws Exception{
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setApiKey(API_KEY);
		queryRequest.setOrder_no(orderNo);
		queryRequest.setTimeStamp(DateTools.convert(new Date(), "yyyyMMddHHmmss"));
		queryRequest.setSign(coumputeSign(queryRequest));
		return queryRequest;
	}
	
	
	
	public QueryResult queryChargeResult(String outerOrderNo) throws Exception{
		logger.info("Start queryChargeResult,outerOrderNo={}",outerOrderNo);
		QueryRequest QueryRequest = buildQueryRequest(outerOrderNo);	
		String jsonStr = httpExecutor.post(QUERY_URL, QueryRequest, null);
		logger.info("queryChargeResult Response : outerOrderNo={},result = {}",outerOrderNo,jsonStr);
		QueryResult queryResult = JsonTools.toObject(jsonStr, QueryResult.class);
		
		if (queryResult.getCode().equals(ConstChargeNotifyStatus.SUCCESS)){
			queryResult.setSuccess(true);
			queryResult.setProcessing(false);
		}else if( queryResult.getCode().equals(ConstChargeNotifyStatus.FAILURE)){
			queryResult.setSuccess(false);
			queryResult.setProcessing(false);
		}else {
			queryResult.setSuccess(false);
			queryResult.setProcessing(true);
		}
		
		return queryResult;
	}
	
	
}
