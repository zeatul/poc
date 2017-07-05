package com.flow.client;

public class Product {
	private String code;
	private int price;
	private int size;
	private int opPrice;// 运营商标准价格

	// 此产品的下单参数
	private String orderRequest;
	// 此订单的查询参数
	private String queryParams;

	private int type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOpPrice() {
		return opPrice;
	}

	public void setOpPrice(int opPrice) {
		this.opPrice = opPrice;
	}

	public String getOrderRequest() {
		return orderRequest;
	}

	public void setOrderRequest(String orderRequest) {
		this.orderRequest = orderRequest;
	}

	public String getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	/**
	 * 流量包漫游类型：1省内，2省内不漫游，3全国
	 * 
	 * @return
	 */
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}