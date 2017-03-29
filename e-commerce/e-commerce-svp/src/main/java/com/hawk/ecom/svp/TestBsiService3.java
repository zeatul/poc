//package com.hawk.ecom.svp;
//
//import java.io.UnsupportedEncodingException;
//
//import java.net.URLEncoder;
//
//import javax.crypto.Mac;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.params.HttpClientParams;
//
//import sun.misc.BASE64Encoder;
//
//
///**
// * 测试小宝外部接口
// * @author pzhang1
// *
// */
//@SuppressWarnings("restriction")
//public class TestBsiService3 {
//	 static String para = "{\"outOrderID\":\"bab2162\",\"productId\":11248,\"goodId\":120,\"mobile\":\"13916082481\",\"username\":\"隔壁老王\",\"certiType\":\"1\",\"iDCard\":\"320106198801011232\",\"birthday\":\"1988-01-01\",\"sex\":1,\"goodsSerialNo\":\"65904\"}";
//	public static final String KEY_MAC = "HmacMD5";
//
//	public static void main(String[] args) {
//		String url = "http://testordersvc.baosm.com/Services/api";
//		try {
//			doPostClient(url);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public static void doPostClient(String url) throws UnsupportedEncodingException {
//
//		HttpClient httpClient = new HttpClient();
//		PostMethod postMethod = new PostMethod(url);
//		postMethod.setParameter("partnercode", "1213623");
//		postMethod.setParameter("version", "1.0");
//		postMethod.setParameter("action", "outcreateorder");
//		postMethod.setParameter("source", "1");
//		postMethod.setParameter("req", para);
//
//		String v = encode(para, "id9R4$jsb0");
//		System.out.println("v="+v);
//		postMethod.setParameter("reqhash", v);
//
//		HttpClientParams params = new HttpClientParams();
//		params.setConnectionManagerTimeout(10000L);
//		httpClient.setParams(params);
//		try {
//			httpClient.executeMethod(postMethod);
//			
//			byte[] b = postMethod.getResponseBody();
//			String str = new String(b, "UTF8");
//			System.out.println(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			postMethod.releaseConnection();
//		}
//	}
//
//	@SuppressWarnings("deprecation")
//	public static String encode(String s, String key) {
//		String retStr = "";
//		try {
//			System.out.println();
//			retStr = URLEncoder.encode(new BASE64Encoder().encode(encryptHMAC(s, key)));
//		} catch (Exception e) {
//		}
//		System.out.println(retStr);
//		return retStr;
//	}
//	
//	
//
//	/**
//	 * HMAC加密
//	 * 
//	 * @param data
//	 * @param key
//	 * @return
//	 * @throws Exception
//	 */
//	public static byte[] encryptHMAC(String json, String key) throws Exception {
//
//		SecretKey secretKey = new SecretKeySpec(key.getBytes("UTF-8"), KEY_MAC);
//		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
//		mac.init(secretKey);
//
//		return mac.doFinal(json.getBytes("UTF-8"));
//
//	}
//}
