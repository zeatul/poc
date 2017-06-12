package com.hawk.ecom.product.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.product.constant.ConstCategory;
import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.exception.CategoryIsNotLeafRuntimeException;
import com.hawk.ecom.product.exception.CategoryNotFoundRuntimeException;
import com.hawk.ecom.product.exception.CategoryStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.DuplicateProductRuntimeException;
import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.mapper.ProductMapper;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.pub.pk.PkGenService;

@Service
public class ProductService {
	
	@Autowired
	private MallAuthService authService;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	@Qualifier("smallNumberSequenceService")
	private PkGenService pkGenService;
	
	@Valid
	public ProductDomain createProduct(@Valid @NotEmpty("参数") CreateProductParam createProdcutParam){
		
		
		
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		/**
		 * 校验
		 */
		CategoryDomain category = categoryService.loadCategory(createProdcutParam.getCategoryId()) ;
		if (category == null){
			throw new CategoryNotFoundRuntimeException();
		}
		if (!ConstBoolean.parse(category.getIsLeaf())){
			throw new CategoryIsNotLeafRuntimeException();
		}
		if (category.getCategoryStatus() == ConstCategory.CategoryStatus.FORBIDDEN){
			throw new CategoryStatusIsNotAcceptableRuntimeException();
		}
		
		Date now = new Date();
		ProductDomain productDomain = new ProductDomain();
		productDomain.setCategoryId(category.getId());
		productDomain.setCreateDate(now);
		productDomain.setCreateUserCode(AuthThreadLocal.getUserCode());
		
		productDomain.setIsVirtual(createProdcutParam.getIsVirtual());
	
		productDomain.setProductCode(createProdcutParam.getProductCode());
		productDomain.setProductDesc(createProdcutParam.getProductDesc());
		productDomain.setProductHomePage(createProdcutParam.getProductHomePage());
		productDomain.setProductMemo(createProdcutParam.getProductMemo());
		productDomain.setProductName(createProdcutParam.getProductName());
		productDomain.setProductStatus(ConstProduct.ProductStatus.EDITING);
		productDomain.setStoreCode(AuthThreadLocal.getStoreCode());
		productDomain.setUpdateDate(now);
		productDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
		
		productDomain.setId(pkGenService.genPk());
		
		try {
			productMapper.insert(productDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateProductRuntimeException();
		}

		return productDomain;
	}
	
	@Valid
	public void updateProduct(@Valid @NotEmpty("参数") UpdateProductParam updateProdcutParam){
		
	}
}
