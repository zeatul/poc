package com.hawk.ecom.product.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.product.constant.ConstCategory;
import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.exception.CategoryIsNotLeafRuntimeException;
import com.hawk.ecom.product.exception.CategoryNotFoundRuntimeException;
import com.hawk.ecom.product.exception.CategoryStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.DuplicateProductRuntimeException;
import com.hawk.ecom.product.exception.ProductIsNotAcceptableForOnSaleRuntimeException;
import com.hawk.ecom.product.exception.ProductNotFoundRuntimeException;
import com.hawk.ecom.product.exception.ProductStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.mapper.ProductMapper;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.request.ListProductParam;
import com.hawk.ecom.product.request.RemoveProductParam;
import com.hawk.ecom.product.request.UpdateProductParam;
import com.hawk.ecom.product.request.UpdateProductStatusParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.StringTools;

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
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public ProductDomain loadProduct(Long id){
		ProductDomain productDomain = null;
		if (id != null){
			logger.error("product id is null");
			productDomain =productMapper.load(id);
		}
		if (productDomain == null){
			throw new ProductNotFoundRuntimeException();
		}
		return productDomain;
	}
	
	/**
	 * 同一个商铺的商品编号不能重复
	 * 新建立的商品默认是编辑状态
	 * 商品的关键属性和非关键属性只能有一个
	 * 商品的SKU属性可以是组合多个
	 * @param createProdcutParam
	 * @return
	 */
	@Valid
	@Transactional
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
	
		productDomain.setProductAttrIdComp(null);
		productDomain.setProductAttrValueComp(null);
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
	
	/**
	 * 被更新的产品必须存在，状态为编辑状态。
	 * 更新不能修改关键属性
	 * 更新是否可以修改产品编号？
	 * 更新是否要保留上次版本，这个得看产品的销售情况
	 * 不是本用户的商铺的商品，不能修改
	 * @param updateProdcutParam
	 */
	@Valid
	@Transactional
	public void updateProduct(@Valid @NotEmpty("参数") UpdateProductParam updateProdcutParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		ProductDomain productDomain = loadProduct(updateProdcutParam.getId());
		if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING){
			throw new ProductStatusIsNotAcceptableRuntimeException();
		}
		
		/**
		 * TODO:检测是否是本用户的商铺的商品
		 */
		
		ProductDomain updateDomain = new ProductDomain();
		DomainTools.copy(updateProdcutParam, updateDomain);
		
		/**
		 * TODO:更新是否要保留上次版本,这个得看产品的销售情况,产品快照
		 */
		
		try {
			productMapper.updateWithoutNull(updateDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateProductRuntimeException();
		}
	} 
	
	/**
	 * 只能修改用户自己商铺的产品状态
	 * 改为上架状态时，必须填写上架时间，下架时间 
	 * @param updateProdcutStatusParam
	 */
	@Valid
	@Transactional
	public void updateProductStatus(@Valid @NotEmpty("参数") UpdateProductStatusParam updateProdcutStatusParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		int status = updateProdcutStatusParam.getProductStatus();
		for (Long id : updateProdcutStatusParam.getIds()){
			ProductDomain productDomain = loadProduct(id);
			if (status != productDomain.getProductStatus()){
				ProductDomain updateDomain = new ProductDomain();
				if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING){
					throw new ProductStatusIsNotAcceptableRuntimeException();
				}
				
				updateDomain.setProductStatus(status);
				updateDomain.setUpdateDate(new Date());
				updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
				
				if (status == ConstProduct.ProductStatus.ON_SALE){
					/**
					 * 检测时间
					 */
					Date stdt = updateProdcutStatusParam.getOnSaleStdt();
					Date endt = updateProdcutStatusParam.getOnSaleEndt();
					if (stdt == null || endt == null || stdt.after(endt) || endt.before(new Date()))
						throw new ProductIsNotAcceptableForOnSaleRuntimeException();
					
					/**
					 * TODO:检测是否有上架的产品SKU
					 */
					
					updateDomain.setOnSaleStdt(stdt);
					updateDomain.setOnSaleEndt(endt);
				}
				
				productMapper.updateWithoutNull(updateDomain);
			}
		}
	}
	
	/**
	 * 上架不能删，
	 * 不是本用户的商铺的商品不能删
	 * 有销售记录，删除要保留快照，保留快照还得看SKU
	 * 
	 * @param removeProductParam
	 */
	@Valid
	@Transactional
	public void removeProduct(@Valid @NotEmpty("参数") RemoveProductParam removeProductParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		for (Long id : removeProductParam.getIds()){
			if (id == null){
				break;
			}
			
			ProductDomain productDomain = loadProduct(id);
			if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING){
				throw new ProductStatusIsNotAcceptableRuntimeException();
			}
			
			if (productDomain.getProductStatus() == ConstProduct.ProductStatus.ON_SALE){
				throw new ProductStatusIsNotAcceptableRuntimeException();
			}
			
			/**
			 * TODO:检测是否是本用户的商铺的商品
			 */
			if(!productDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())){
				
			}
			
			/**
			 * TODO:检测有没有销售记录
			 */
			
			/**
			 * TODO:保留快照
			 */
			
			/**
			 * TODO:删除,删除属性，SKU ，库存
			 */
			productMapper.delete(id);
		}
	}
	
	@Valid
	public List<ProductDomain> listProduct(@Valid @NotEmpty("参数") ListProductParam listProductParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		if (StringTools.isNullOrEmpty(listProductParam.getOrder())){
			listProductParam.setOrder("create_date desc");
		}
		
		MybatisParam params = MybatisTools.page(new MybatisParam(), listProductParam);
		return productMapper.loadDynamicPaging(params);
	}
}
