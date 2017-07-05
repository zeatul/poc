package com.flow.client;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 订单客户端
 * 
 * @author Administrator
 *
 */
public class OrderClient {
	private static Log log = LogFactory.getLog(OrderClient.class);
	private String orderUrl, queryUrl;
	private ObjectMapper mapper = new ObjectMapper();
	private HttpClient httpClient = new HttpClient();

	public OrderClient(String host, int port) {
		orderUrl = "http://" + host + ":" + port + "/flowgw/srv/order";
		queryUrl = "http://" + host + ":" + port + "/flowgw/srv/order";
	}

	/**
	 * 发起订单
	 * 
	 * @param clientCode
	 * @param accKey
	 * @param phone
	 * @param productCode
	 * @param clientOrderNo
	 * @param callBackUrl
	 * @return OrderResponse
	 * @throws Exception
	 */
	public OrderResponse order(String clientCode, String accKey, String phone, String productCode, String clientOrderNo,
			String callBackUrl) throws Exception {
		return this.order(clientCode, accKey, phone, productCode, clientOrderNo, callBackUrl, false);
	}

	/**
	 * 
	 * @param clientCode
	 * @param accKey
	 * @param phone
	 * @param productCode
	 * @param clientOrderNo
	 * @param callBackUrl
	 * @param isMock
	 * @return
	 * @throws Exception
	 */
	public OrderResponse order(String clientCode, String accKey, String phone, String productCode, String clientOrderNo,
			String callBackUrl, boolean isMock) throws Exception {
		OrderRequest req = new OrderRequest();
		req.setClientCode(clientCode);
		req.setPhone(phone);
		req.setProductCode(productCode);
		req.setClientOrderNo(clientOrderNo);
		req.setNotifyUrl(callBackUrl);
		req.setSign(req.getSignText(accKey));

		return this.order(req, isMock);
	}

	/**
	 * 发起订单
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public OrderResponse order(OrderRequest req) throws Exception {
		return this.order(req, false);
	}

	/**
	 * 
	 * @param req
	 * @param isMock
	 * @return
	 * @throws Exception
	 */
	public OrderResponse order(OrderRequest req, boolean isMock) throws Exception {
		String url = orderUrl;
		if (isMock) {
			url += "?mock=true";
		}
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type", "application/json;charset=utf-8");

		try {
			post.setRequestBody(mapper.writeValueAsString(req));
			int statusCode = httpClient.executeMethod(post);
			if (statusCode == 200) {
				String resp = post.getResponseBodyAsString();
				return mapper.readValue(resp, OrderResponse.class);
			} else {
				log.error("订单失败，HTTP状态码：" + statusCode + "," + post.getResponseBodyAsString());
			}
		} catch (Exception e) {
			log.error("订单失败", e);
			throw e;
		} finally {
			post.releaseConnection();
		}

		return null;

	}

	/**
	 * 查询订单状态
	 * 
	 * @param clientCode
	 * @param accKey
	 * @param clientOrderNo
	 * @param orderNo
	 * @return OrderResponse
	 */
	public OrderResponse queryOrder(String clientCode, String accKey, String clientOrderNo, String orderNo)
			throws Exception {

		StringBuffer buf = new StringBuffer();
		buf.append("clientCode=").append(clientCode);
		buf.append("clientOrderNo=").append(clientOrderNo);
		if (!StringUtil.isNullOrEmpty(orderNo))
			buf.append("orderNo=").append(orderNo);
		buf.append(accKey);
		String sign = EncrpytionUtil.str2MD5(buf.toString());

		buf = new StringBuffer();
		// buf.append(queryUrl).append("?");
		buf.append("clientCode=").append(clientCode);
		buf.append("&clientOrderNo=").append(clientOrderNo);
		if (!StringUtil.isNullOrEmpty(orderNo))
			buf.append("&orderNo=").append(orderNo);
		buf.append("&sign=").append(sign);

		return this.queryOrder(buf.toString());
	}

	/**
	 * 已构好的URL参数 ：clientCode＝XXX&clientOrderNo=XX&orderNo=XX&sign=XX
	 * 
	 * @param params
	 * @return
	 */
	public OrderResponse queryOrder(String params) throws Exception {
		GetMethod get = new GetMethod(this.queryUrl + "?" + params);

		try {
			int statusCode = httpClient.executeMethod(get);
			if (statusCode == 200) {
				String resp = get.getResponseBodyAsString();
				return mapper.readValue(resp, OrderResponse.class);
			} else {
				log.error("HTTP statusCode=" + statusCode);
			}
		} catch (Exception e) {
			log.error("Order Query Fail", e);
			throw e;
		} finally {
			get.releaseConnection();
		}

		return null;

	}

}
