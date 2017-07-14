package com.flow.client.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class TestPrePay {

	public static void main(String[] args) {

		String clientCode = "tb";
		String phone = "13675165257";
		String prePayNo = "xxxxx";

		String url = "http://120.76.98.114:8080/flowgw/srv/order/prepay?clientCode=%s&phone=%s&prePayNo=%s";
		url = "http://120.24.175.91:8080/flowgw/srv/order/prepay?clientCode=%s&phone=%s&prePayNo=%s";
		url = "http://localhost:8080/flowgw/srv/order/prepay?clientCode=%s&phone=%s&prePayNo=%s";
		url = String.format(url, clientCode, phone, prePayNo);

		HttpClient httpClient = new HttpClient();
		PostMethod post = new PostMethod(url);
		// post.setRequestHeader("Content-Type",
		// "application/json;charset=utf-8");

		try {

			int statusCode = httpClient.executeMethod(post);
			if (statusCode == 200) {
				String resp = post.getResponseBodyAsString();

				System.out.println(resp + "\t" + post.getResponseHeader("Access-Control-Allow-Origin"));
			} else {
				System.out.println("订单失败，HTTP状态码：" + statusCode + "," + post.getResponseBodyAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
	}
}
