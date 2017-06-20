package com.hawk.ecom.svp;

import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.hawk.framework.utility.http.HttpClientExecutorImpl;
import com.hawk.framework.utility.http.HttpExecutor;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import sun.misc.BASE64Encoder;

public class TestBsiService2 {
	
//	正在 Ping testordersvc.baosm.com [123.56.87.26] 具有 32 字节的数据:
	
	private String partnercode = "1213623";
	private String password = "id9R4$jsb0";
	
	private HttpExecutor httpExecutor;
	
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
		public void setProductId(Integer  productId) {
			this.productId = productId;
		}
		public Integer getGoodId() {
			return goodId;
		}
		public void setGoodId(Integer  goodId) {
			this.goodId = goodId;
		}
		public Long getMobile() {
			return mobile;
		}
		public void setMobile(Long  mobile) {
			this.mobile = mobile;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getCertiType() {
			return certiType;
		}
		public void setCertiType(String certiType) {
			this.certiType = certiType;
		}
		public String getiDCard() {
			return iDCard;
		}
		public void setiDCard(String iDCard) {
			this.iDCard = iDCard;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public long getSex() {
			return sex;
		}
		public void setSex(long sex) {
			this.sex = sex;
		}
		public String getGoodsSerialNo() {
			return goodsSerialNo;
		}
		public void setGoodsSerialNo(String goodsSerialNo) {
			this.goodsSerialNo = goodsSerialNo;
		}
		private String outOrderID ;//	必填	字符	100	外部订单号，必须保合作方系统中唯一，后续对账唯一凭证
		private Integer productId ;//	必填	数字	5	产品ID(固定值)
		private Integer goodId	;//必填	数字	5	手机型号ID
		private Long mobile ;//	必填	数字	24	投保人手机号
		private String username	;//必填	字符	128	投保人姓名
		private String certiType ; //	必填	数字	1	证件类型		[身份证:1, 护照:2, 军官证:3, 港澳回乡证或台胞证:5,其他:7]
		private String iDCard ; //必填	字符	100	证件号码
		private String birthday	; //必填	字符	10	生日（1985-01-06）
		private long sex ; //	必填	数字	1	性别[男:1,女:2]
		private String goodsSerialNo ; //	必填	字符	100	手机IMEI号
	}
	
	public Order createOrder(){
		
		Integer  goodId = 304;
		Integer  productId = 10260;
		
		Order order = new Order();
		order.setBirthday("1988-01-01");
		order.setCertiType("1");
		order.setGoodId(goodId);
		order.setGoodsSerialNo(UUID.randomUUID().toString());
		order.setiDCard("320106198801011232");
		order.setMobile(13916082481L);
		order.setOutOrderID(UUID.randomUUID().toString());
		order.setProductId(productId);
		order.setSex(1);
		order.setUsername("隔壁老王");
		
		

		
		return order;
	}
	
	public TestBsiService2() throws Exception{
		httpExecutor = new HttpClientExecutorImpl();
		
		
		
		
	}
	

	@SuppressWarnings("deprecation")
	public static String encode(String s, String key) {
		String retStr = "";
		try {
			System.out.println();
			retStr = URLEncoder.encode(new BASE64Encoder().encode(encryptHMAC(s, key)));
		} catch (Exception e) {
		}
		System.out.println(retStr);
		return retStr;
	}
	
	
	public static final String KEY_MAC = "HmacMD5";

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(String json, String key) throws Exception {

		SecretKey secretKey = new SecretKeySpec(key.getBytes("UTF-8"), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(json.getBytes("UTF-8"));

	}
	
	public void testQueryOrder(){
		String url = "http://testordersvc.baosm.com/Services/api";
		String req = "{\"OutOrderID\":\"dab2166\"}";
		String reqhash = encode(req,"id9R4$jsb0");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("PartnerCode", "1213623"));	
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("action", "outqueryorder"));
		params.add(new HttpParam("source", "1"));
		params.add(new HttpParam("password", "id9R4$jsb0"));
		params.add(new HttpParam("req", req));
		params.add(new HttpParam("reqhash", reqhash));
		String result = httpExecutor.postParamInBody(url,"", params) ;//.post(url, "", params);
		
		System.out.println("result="+result);
	}
	
	public void testCreateOrder(){
		Order order = createOrder();
		String url = "http://testordersvc.baosm.com/Services/api";		
		
		String x = "\"";
		String req = "{\"outOrderID\":\"dab2166\",\"productId\":11248,\"goodId\":120,\"mobile\":\"13916082482\",\"username\":\"隔壁老王\",\"certiType\":\"1\",\"iDCard\":\"320106198801011232\",\"birthday\":\"1988-01-01\",\"sex\":1,\"goodsSerialNo\":\"615190\"}";
		

		System.out.println("req="+req);
		String reqhash = encode(req,"id9R4$jsb0");
		System.out.println("reqhash="+reqhash);
		
	
		System.out.println("right=sGdpsIFA%2be%2bErieZ9f70Zg%3d%3d");
		

		
//		partnercode=1001&version=1.0&action=customerlogin&source=1
//				&req={"mobile":"13912345678","password":"123456"}
//		&reqhash=3WPccIkeJpMAGj7jD0mmJQ%3d%3d
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("PartnerCode", "1213623"));	
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("action", "outcreateorder"));
		params.add(new HttpParam("source", "1"));
		params.add(new HttpParam("password", "id9R4$jsb0"));
		params.add(new HttpParam("req", req));
		params.add(new HttpParam("reqhash", reqhash));
		
		
//		postMethod.setParameter("partnercode", "1213623");
//		postMethod.setParameter("version", "1.0");
//		postMethod.setParameter("action", "outcreateorder");
//		postMethod.setParameter("source", "1");
//		postMethod.setParameter("req", para);
//		postMethod.setParameter("reqhash", v);

		
		System.out.println(httpExecutor.buildUrl(url, params));

		String result = httpExecutor.postParamInBody(url,"", params) ;//.post(url, "", params);
		
		System.out.println("result="+result);
	}
	
	public static void main(String[] args) throws Exception{
		new TestBsiService2().testQueryOrder();
	}
}
