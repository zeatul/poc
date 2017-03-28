package com.hawk.ecom.svp;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import sun.misc.BASE64Encoder;


/**
 * 测试小宝外部接口
 * @author pzhang1
 *
 */
@SuppressWarnings("restriction")
public class TestBsiService3 {
	 static String para = "{\"OutOrderID\":\"0001\",\"ProductId\":10218,\"GoodId\":51,\"mobile\":\"18694072943\",\"username\":\"同一天\",\"CertiType\":1,\"IDCard\":\"429005199308273048\",\"Birthday\":\"1993-08-27\",\"Sex\":1,\"GoodsSerialNo\":\"231232131231231\"}";
	public static final String KEY_MAC = "HmacMD5";

	public static void main(String[] args) {
		String url = "http://testordersvc.baosm.com/Services/api";
		try {
			doPostClient(url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void doPostClient(String url) throws UnsupportedEncodingException {

		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.setParameter("partnercode", "1213623");
		postMethod.setParameter("version", "1.0");
		postMethod.setParameter("action", "outcreateorder");
		postMethod.setParameter("source", "1");
		postMethod.setParameter("req", para);

		String v = encode(para, "id9R4$jsb0");
		System.out.println("v="+v);
		postMethod.setParameter("reqhash", v);

		HttpClientParams params = new HttpClientParams();
		params.setConnectionManagerTimeout(10000L);
		httpClient.setParams(params);
		try {
			httpClient.executeMethod(postMethod);
			
			byte[] b = postMethod.getResponseBody();
			String str = new String(b, "UTF8");
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
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
}
