package com.flow.client.test;

import java.util.UUID;

import com.flow.client.OrderClient;
import com.flow.client.OrderResponse;

/**
 * 测试发起订单
 * 
 * @author Administrator
 *
 */
public class TestOrder {

	public static void main(String[] args) throws Exception {

		String clientCode = "test";
		String phone = "13052376285";
		String productCode = "LLT200010";
		String orderNo = "2015070209362594MOCK";
		String callBackUrl = "";// "http://125.83.167.236:7892/";
		String accKey = "test";
		String host = "120.24.175.91";
		int port = 8080;
		// clientCode=testclientOrderNo=2016070609362594phone=13052376285productCode=LLT200010notifyUrl=http://125.83.167.236:7892/hello

		clientCode = "shdy";
		phone = "13675165257";
		productCode = "10002";
		accKey = "hello";
		host = "localhost";
		callBackUrl = "http://www.baidu.com";

		// 18620893125
		clientCode = "shdy";
		phone = "18620893125";
		productCode = "LLT200050";
		accKey = "hello";
		host = "120.76.98.114";
		// callBackUrl = "http://cz.9dian9.cn/u/zxusup/notify/34.htm";
		OrderClient client = new OrderClient(host, port);
		for (int i = 1; i <= 1; i++) {
			orderNo = UUID.randomUUID().toString().replace("-", "");
			OrderResponse resp = client.order(clientCode, accKey, phone, productCode, orderNo, callBackUrl, true);
			if (resp != null) {
				System.out.println(resp.getResultCode());
			} else {
				System.out.println("查询失败");
			}
			Thread.sleep(5000);
		}
	}

}
