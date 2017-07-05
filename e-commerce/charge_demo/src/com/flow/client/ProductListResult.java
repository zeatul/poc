package com.flow.client;

import java.util.List;

/**
 * 产品查询列表
 * 
 * @author Administrator
 *
 */
public class ProductListResult extends BizResult {
	private List<Product> products=null;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
