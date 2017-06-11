package com.hawk.ecom.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.mapper.ProductMapper;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private CategoryService categoryService;
	
	@Valid
	public ProductDomain createProduct(@Valid @NotEmpty("参数") CreateProductParam createProdcutParam){
		
		/**
		 * 校验
		 */
		CategoryDomain category = categoryService.loadCategory(createProdcutParam.getCategoryId()) ;
	}
}
