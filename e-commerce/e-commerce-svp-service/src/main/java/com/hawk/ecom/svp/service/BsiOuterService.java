package com.hawk.ecom.svp.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.svp.exception.OuterCallException;
import com.hawk.framework.utility.http.HttpClientExecutorImpl;
import com.hawk.framework.utility.http.HttpExecutor;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;




/**
 * 小宝的外调接口
 * 在SvpOutCall里配置bean
 * @author zhp
 *
 */
public class BsiOuterService {
	
	private final Logger logger = LoggerFactory.getLogger(BsiOuterService.class);
	
	
	public BsiOuterService(String partnercode, String key, String url) throws Exception {
		this.partnercode = partnercode;
		this.key = key;
		this.url = url;
		this.httpExecutor = new HttpClientExecutorImpl(); 
	}
	private final String partnercode;
	private final String key;
	private final String url;
	private final HttpExecutor httpExecutor ; 
	

	/**
	 * 创建小宝订单
	 * @param order
	 * @return 小宝内部订单编号
	 */
	public String outCreateOrder(Order order) throws OuterCallException{
		String req = JsonTools.toJsonString(order);
		
		logger.info("outCreateOrder_request={}",req);
		
		String reqhash = encode(req);
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("partnerCode", this.partnercode));	
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("action", "outcreateorder"));
		params.add(new HttpParam("source", "1"));
		params.add(new HttpParam("req", req));
		params.add(new HttpParam("reqhash", reqhash));
		
		String response = httpExecutor.postParamInBody(url,"", params) ;
		
		logger.info("outCreateOrder response={} , outOrderId={}",response,order.getOutOrderID());
		
		@SuppressWarnings("rawtypes")
		Map map = JsonTools.toObject(response, HashMap.class);
		
		String result = (String)map.get("result");
		
		logger.info("outCreateOrder_response={}",result);
		
		if ("0".equals(result)){
			throw new OuterCallException(-1,(String)map.get("notice"));
		}else if ("1".equals(result)){
			
			String orderNo =  (String)map.get("orderid");
			return orderNo;
			
		}else{
			throw new RuntimeException("result code is illegal");
		}

		
//		result={
//				  "outorderid": "",
//				  "orderid": "",
//				  "result": "0",
//				  "notice": "操作失败！产品碎屏保·A+款每个手机号最多可以投保俩份"
//				}
	}
	
	public void outQueryOrder(String taskCode) throws OuterCallException{
		
		Map<String, String> reqMap = new HashMap<String,String>();
		reqMap.put("outOrderID",taskCode);
		String req = JsonTools.toJsonString(reqMap);
		logger.info("outQueryOrder req={}",req);
		String reqhash = encode(req);
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("partnerCode", this.partnercode));	
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("action", "outqueryorder"));
		params.add(new HttpParam("source", "1"));
		params.add(new HttpParam("req", req));
		params.add(new HttpParam("reqhash", reqhash));
		
		String response = httpExecutor.postParamInBody(url,"", params) ;
		
		logger.info("outCreateOrder response={} , taskCode={}",response,taskCode);
		
		@SuppressWarnings("rawtypes")
		Map map = JsonTools.toObject(response, HashMap.class);
		
		String result = (String)map.get("result");
		
		if ("0".equals(result)){
			throw new OuterCallException(-1,(String)map.get("notice"));
		}else if ("1".equals(result)){
//			{
//				"orderlist": [
//					{
//						"orderid": 467243,
//						"orderno": "301678451",
//						"productname": "12个月碎屏保A+（联通）",
//						"productpartnername": null,
//						"goodsname": "Mate S",
//						"startdate": "2017-04-06",
//						"enddate": "2018-04-06",
//						"status": 2,
//						"mobile": "13916082482",
//						"username": "隔壁老王",
//						"certitype": 1,
//						"idcard": "320106198801011232",
//						"birthday": "1988/1/1 0:00:00",
//						"sex": 1,
//						"insuranceno": "",
//						"goodsserialno": "615190"
//					}
//				],
//				"result": "1",
//				"notice": "操作成功"
//			}
			List<?> list =  (List<?>)map.get("orderList");
			@SuppressWarnings("rawtypes")
			Map orderMap = (Map)list.get(0);
			String orderNo =  (String)orderMap.get("orderno");
//			return orderNo;
		}else{
			throw new RuntimeException("result code is illegal");
		}
	}
	
	public static void main(String[] args) throws Exception{
		BsiOuterService service = new BsiOuterService("1213623","id9R4$jsb0","http://testordersvc.baosm.com/Services/api");
		
//		service.outQueryOrder("dab2166");
		
		int goodId = 120;
		int productId = 11248;
		
		Order order = new Order();
//		order.setBirthday("1988-01-01");
		order.setCertiType(1);
		order.setGoodId(goodId);
		order.setGoodsSerialNo(UUID.randomUUID().toString());
		order.setIdCard("320106198801011232");
		order.setMobile(13916082484L);
		order.setOutOrderID("132234567");
		order.setProductId(productId);
//		order.setSex(1);
		order.setUsername("隔壁老王");
		
		service.outCreateOrder(order);
	}

	
	
	private  String encode(String s) {
		String retStr = "";
		try {
			System.out.println();
			retStr = URLEncoder.encode( Base64.getEncoder().encodeToString(encryptHMAC(s, this.key)),"utf-8");
		} catch (Exception e) {
		}
		System.out.println(retStr);
		return retStr;
	}
	
	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private static byte[] encryptHMAC(String json, String key) throws Exception {
		final String KEY_MAC = "HmacMD5";

		SecretKey secretKey = new SecretKeySpec(key.getBytes("UTF-8"), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(json.getBytes("UTF-8"));

	}
	
	
	public static class Order{

		public String getOutOrderID() {
			return outOrderID;
		}
		public void setOutOrderID(String outOrderID) {
			this.outOrderID = outOrderID;
		}
		public Integer getProductId() {
			return productId;
		}
		public void setProductId(Integer productId) {
			this.productId = productId;
		}
		public Integer getGoodId() {
			return goodId;
		}
		public void setGoodId(Integer goodId) {
			this.goodId = goodId;
		}
		public Long getMobile() {
			return mobile;
		}
		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Integer getCertiType() {
			return certiType;
		}
		public void setCertiType(Integer certiType) {
			this.certiType = certiType;
		}
		public String getIdCard() {
			return idCard;
		}
		public void setIdCard(String idCard) {
			this.idCard = idCard;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public Integer getSex() {
			return sex;
		}
		public void setSex(Integer sex) {
			this.sex = sex;
		}
		public String getGoodsSerialNo() {
			return goodsSerialNo;
		}
		public void setGoodsSerialNo(String goodsSerialNo) {
			this.goodsSerialNo = goodsSerialNo;
		}
		/**
		 * 必填	字符	100	外部订单号，必须保合作方系统中唯一，后续对账唯一凭证
		 */
		private String outOrderID ;	
		/**
		 * 必填	数字	5	产品ID(固定值)
		 */
		private Integer productId ;	
		/**
		 * 必填	数字	5	手机型号ID
		 */
		private Integer goodId	;
		/**
		 * 必填	数字	24	投保人手机号
		 */
		private Long mobile ;
		/**
		 * 必填	字符	128	投保人姓名
		 */
		private String username	;
		/**
		 * 必填	数字	1	证件类型		[身份证:1, 护照:2, 军官证:3, 港澳回乡证或台胞证:5,其他:7]
		 */
		private Integer certiType ; 
		/**
		 * 字符	100	证件号码
		 */
		private String idCard ;
		/**
		 * 必填	字符	10	生日（1985-01-06）
		 */
		private String birthday	; 
		/**
		 * 必填	数字	1	性别[男:1,女:2]
		 */
		private Integer sex ; 
		/**
		 * 必填	字符	100	手机IMEI号
		 */
		private String goodsSerialNo ; 	
	}
}
