package com.flow.client.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class TestRequeryOrders {

	public static void main(String[] args) throws Exception {
		
		String url = "http://localhost:8088/flowmgr/srv/fp/rq?fp=mock";

		HttpClient httpClient = new HttpClient();
		GetMethod get = new GetMethod(url);

		int statusCode = httpClient.executeMethod(get);

		System.out.println(statusCode + "," + get.getResponseBodyAsString());

	}

}
