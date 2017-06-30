package com.hawk.ecom.query.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.query.persist.domainex.ProductCategoryExDomain;
import com.hawk.ecom.query.persist.domainex.ProductSkuExDomain;
import com.hawk.ecom.query.persist.mapperex.ProductExMapper;
import com.hawk.ecom.query.request.ListSkuParam;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;

@Service
public class ProductService {
	
	@Autowired
	private ProductExMapper productExMapper;
	
	public List<ProductCategoryExDomain> queryCategory(){
		return productExMapper.queryCategory();
	}
	
	public List<ProductSkuExDomain> querySku(ListSkuParam listSkuParam){
		MybatisParam params = MybatisTools.page(new MybatisParam(), listSkuParam);
		params.put("productStatus", 100);
		params.put("skuStatus", 100);
		params.put("categoryId", listSkuParam.getCategoryId());
		return productExMapper.querySku(params);
	}
	
	public ProductSkuExDomain loadSku(int skuId){
		MybatisParam params = new MybatisParam();
		params.put("productStatus", 100);
		params.put("skuStatus", 100);
		params.put("skuId", skuId);
		return productExMapper.loadSku(params);
	}
	
	public ProductSkuExDomain loadSkuPriceAndQuantity(int skuId){
		MybatisParam params = new MybatisParam();
		params.put("productStatus", 100);
		params.put("skuStatus", 100);
		params.put("skuId", skuId);
		return productExMapper.loadSkuPriceAndQuantity(params);
	}

}