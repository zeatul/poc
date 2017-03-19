package httpjson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.junit.Test;

import sun.misc.BASE64Encoder;


public class ClientDemo2 {
	static String para = "{\"ProductId\":\"10075\"}";

	
	@Test
	public void test() throws UnsupportedEncodingException{
		String url = "http://testproductsvc.baosm.com/Services/api" ;
		doPostClient(url);
	}
	public static void doPostClient(String url) throws UnsupportedEncodingException{
		
//		  partnercode：商户号，固定值，每个商户会分配一个key，接口加密用 
//		  version：接口版本号，固定值，目前取值1.0 
//		  action：接口名称 
//		  source：接口调用访问来源 （1:电脑，2:客户端（安卓，IOS，H5）） 
//		  req：json业务数据字符串 
//		  reqhash：请求签名 UrlEncode(ToBase64String(md5(req内的json+key)))
		
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.setParameter("partnercode", "1143988");
		postMethod.setParameter("version", "1.0");
		postMethod.setParameter("action", "getproductmobile");
		postMethod.setParameter("source", "1");
		postMethod.setParameter("req", para);
		
		postMethod.setParameter("reqhash", encode(para, "id$339@jIDDV0"));
		
		HttpClientParams params = new HttpClientParams();
		params.setConnectionManagerTimeout(10000L);
		httpClient.setParams(params);
		try {
			httpClient.executeMethod(postMethod);
			//获取二进制的byte流
			byte[] b = postMethod.getResponseBody();
			String str = new String(b,"UTF8");
			System.out.println(str);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
		}
	}
	

	@SuppressWarnings("deprecation")
	public static String encode(String s, String key) {
		String retStr = "";
		try {
			System.out.println();
			retStr = URLEncoder.encode(new BASE64Encoder().encode(Coder.encryptHMAC(s, key)));
		} catch (Exception e) {
		}
		System.out.println(retStr);
		return retStr;
	}
}

