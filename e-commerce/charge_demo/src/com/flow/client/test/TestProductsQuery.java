package com.flow.client.test;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.flow.client.OrderClient;
import com.flow.client.OrderRequest;
import com.flow.client.OrderResponse;
import com.flow.client.Product;
import com.flow.client.ProductClient;
import com.flow.client.ProductListResult;

/**
 * 产品查询及购买、状态查询代码示例
 * 
 * @author Administrator
 *
 */
public class TestProductsQuery {

	public static void main(String[] args) throws Exception {
		String host = "localhost";
		host = "120.24.175.91";
		ProductClient pc = new ProductClient(host, 8080);
		ObjectMapper mapper = new ObjectMapper();
		ProductListResult result = pc.listProducts("shzy", null, "hello", "13002151234");

		if (result != null) {
			System.out.println("成功?" + result.isSucc());
			if (result.isSucc()) {

				List<Product> ps = result.getProducts();
				System.out.println("发现以下产品：代码,购买参数");
				for (Product m : ps) {
					System.out.println(m.getCode() + "," + m.getOrderRequest());
				}
				// 发起一次购买
				Product p = ps.get(0);
				OrderRequest req = mapper.readValue(p.getOrderRequest(), OrderRequest.class);
				// 购买
				OrderClient orderClient = new OrderClient(host, 8080);
				OrderResponse resp = orderClient.order(req);
				System.out.println("order :orderNo=" + resp.getOrderNo() + ",code=" + resp.getResultCode());
				for (int i = 1; i <= 5; i++) {
					Thread.sleep(2000);
					// 查询订单状态
					OrderResponse queryResp = orderClient.queryOrder(p.getQueryParams());
					System.out.println("Query Order:" + queryResp.getResultCode());
				}

			}
		}

	}

}
