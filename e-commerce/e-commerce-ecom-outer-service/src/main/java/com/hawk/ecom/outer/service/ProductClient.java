package com.flow.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class ProductClient {
	private static Log log = LogFactory.getLog(OrderClient.class);
	private ObjectMapper mapper = new ObjectMapper();
	private HttpClient httpClient = new HttpClient();
	private String productUrl;

	public ProductClient(String host, int port) {
		productUrl = "http://" + host + ":" + port + "/flowgw/srv/prods";
	}

	/**
	 * 获得产品列表
	 * 
	 * @param clientCode
	 * @param subClientCode
	 * @param accKey
	 * @param phone
	 * @return
	 */
	public ProductListResult listProducts(String clientCode, String subClientCode, String accKey, String phone)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("clientCode=").append(clientCode);
		if (!StringUtil.isNullOrEmpty(subClientCode))
			buf.append("subClientCode=").append(subClientCode);
		buf.append("phone=").append(phone);
		buf.append(accKey);
		String sign = EncrpytionUtil.str2MD5(buf.toString());

		buf = new StringBuffer();
		buf.append(productUrl).append("?");
		buf.append("clientCode=").append(clientCode);
		if (!StringUtil.isNullOrEmpty(subClientCode))
			buf.append("&subClientCode=").append(subClientCode);
		buf.append("&phone=").append(phone);
		buf.append("&sign=").append(sign);

		System.out.println("查询产品列表：" + buf.toString());

		GetMethod get = new GetMethod(buf.toString());

		try {
			int statusCode = httpClient.executeMethod(get);
			if (statusCode == 200) {
				String resp = get.getResponseBodyAsString();
				return mapper.readValue(resp, ProductListResult.class);
			} else {
				log.error("HTTP statusCode=" + statusCode);
			}
		} catch (Exception e) {
			log.error("List Product Fail", e);
			throw e;
		} finally {
			get.releaseConnection();
		}

		return null;
	}
}
