package com.flow.client.test;

import com.flow.client.OrderClient;
import com.flow.client.OrderResponse;

/**
 * 测试订单查询
 * 
 * @author Administrator
 *
 */
public class TestOrderQuery {

	public static void main(String[] args) throws Exception {
		String host = "120.76.98.114";
		// host = "120.24.175.91";
		OrderClient client = new OrderClient(host, 8080);

		String clientCode = "jdj";
		String clientOrderNo = "F146769976200013949";
		String orderNo = "9vMQfkLj7y4hRYf4ZMe";
		String key = "hello";

		OrderResponse resp = client.queryOrder(clientCode, key, clientOrderNo, orderNo);

		if (resp != null) {
			System.out.println(resp.getResultCode());
		} else {
			System.out.println("查询失败");
		}

		// System.out.println(
		// EncrpytionUtil.str2MD5("clientCode=shyingjclientOrderNo=1phone=18601720625productCode=LLT200020hello"));
	}

}
