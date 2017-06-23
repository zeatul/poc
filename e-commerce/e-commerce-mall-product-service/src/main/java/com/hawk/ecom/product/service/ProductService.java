package com.hawk.ecom.product.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.product.constant.ConstAttr;
import com.hawk.ecom.product.constant.ConstCategory;
import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.exception.AttrNameIsNotUniqueException;
import com.hawk.ecom.product.exception.CategoryIsDifferentRuntimeException;
import com.hawk.ecom.product.exception.CategoryIsNotLeafRuntimeException;
import com.hawk.ecom.product.exception.CategoryNotFoundRuntimeException;
import com.hawk.ecom.product.exception.CategoryVariantStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.DuplicateProductRuntimeException;
import com.hawk.ecom.product.exception.ProductIsNotAcceptableForOnSaleRuntimeException;
import com.hawk.ecom.product.exception.ProductNotFoundRuntimeException;
import com.hawk.ecom.product.exception.ProductStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.persist.domain.AttrNameDomain;
import com.hawk.ecom.product.persist.domain.AttrValueDomain;
import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.persist.domain.ProductAttrDomain;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.mapper.ProductAttrMapper;
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
import com.hawk.framework.utility.tools.CollectionTools;
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
	private AttrValueService attrValueService;
	
	@Autowired
	private AttrNameService attrNameService;
	
	@Autowired
	private ProductAttrMapper productAttrMapper;

	@Autowired
	@Qualifier("smallNumberSequenceService")
	private PkGenService pkGenService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public ProductDomain loadProduct(Integer id) {
		ProductDomain productDomain = null;
		if (id != null) {
			logger.error("product id is null");
			productDomain = productMapper.load(id);
		}
		if (productDomain == null) {
			throw new ProductNotFoundRuntimeException();
		}
		return productDomain;
	}

	/**
	 * 同一个商铺的商品编号不能重复 新建立的商品默认是编辑状态 创建商品时，对应的产品目录变式状态必须是 available
	 * 商品的关键属性和非关键属性只能有一个 商品的SKU属性可以是组合多个
	 * 
	 * @param createProdcutParam
	 * @return
	 */
	@Valid
	@Transactional
	public ProductDomain createProduct(@Valid @NotEmpty("参数") CreateProductParam createProdcutParam) {

		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		/**
		 * 校验,产品目录必须存在，产品目录必须是叶子节点，产品目录变式状态必须是可用的。
		 */
		CategoryDomain category = categoryService.loadCategory(createProdcutParam.getCategoryId());
		if (category == null) {
			throw new CategoryNotFoundRuntimeException();
		}
		if (!ConstBoolean.parse(category.getIsLeaf())) {
			throw new CategoryIsNotLeafRuntimeException();
		}
		// if (category.getCategoryStatus() !=
		// ConstCategory.CategoryStatus.FORBIDDEN){
		// throw new CategoryStatusIsNotAcceptableRuntimeException();
		// }
		if (category.getCategoryVariantStatus() != ConstCategory.CategoryVariantStatus.AVAILABLE) {
			throw new CategoryVariantStatusIsNotAcceptableRuntimeException();
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

		/**
		 * 检查所有输入的属性值对应的属性名Id和sku属性ID 不能出现相同的属性ID
		 */
		Map<Integer, Integer> checkAttrNameMap = new HashMap<Integer, Integer>();
		
		/**
		 * 设置SKU属性名ID集合
		 */
		List<Integer> skuAttrNameIds = createProdcutParam.getProductSkuAttrNameIds();		
		if (CollectionTools.isNotNullOrEmpty(skuAttrNameIds)){
			/**
			 * 必须先排序
			 */
			Collections.sort(skuAttrNameIds);
			/**
			 * 产品目录分类ID
			 */
			Integer categoryId = createProdcutParam.getCategoryId();
			/**
			 * 校验属性名ID必须存在，不能重复，必须有和创建的产品有相同的产品目录
			 */
			for (Integer attrNameId : skuAttrNameIds){
				AttrNameDomain attrNameDomain = attrNameService.loadById(attrNameId);
				if (categoryId.equals(attrNameDomain.getCategoryId())) {
					throw new CategoryIsDifferentRuntimeException();
				}
				
				if (checkAttrNameMap.containsKey(attrNameId)) {
					throw new AttrNameIsNotUniqueException();
				} else {
					checkAttrNameMap.put(attrNameId, attrNameId);
				}
			}
			
			productDomain.setProductSkuAttrNameIds(StringTools.concatWithSymbol(",", skuAttrNameIds));
		}

		/**
		 * 设定关键属性名值ID集合 和 关键属性值值集合
		 */
		List<Integer> keyAttrValueIds = createProdcutParam.getProductKeyAttrValueIds();		
		List<ProductAttrDomain> productAttrDomainList = new ArrayList<ProductAttrDomain>();
		if (CollectionTools.isNotNullOrEmpty(keyAttrValueIds)) {
			/**
			 * 必须先排序
			 */
			Collections.sort(keyAttrValueIds);
			/**
			 * 产品目录分类ID
			 */
			Integer categoryId = createProdcutParam.getCategoryId();
			/**
			 * 校验属性值ID必须存在，属性值对应的属性名ID不能重复，必须有和创建的产品有相同的产品目录
			 */
			List<String> attrValues = new ArrayList<String>();
			for (Integer attrValueId : keyAttrValueIds) {
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(attrValueId);

				Integer attrNameId = attrValueDomain.getAttrNameId();
				if (categoryId.equals(attrValueDomain.getCategoryId())) {
					throw new CategoryIsDifferentRuntimeException();
				}

				if (checkAttrNameMap.containsKey(attrNameId)) {
					throw new AttrNameIsNotUniqueException();
				} else {
					checkAttrNameMap.put(attrNameId, attrNameId);
					attrValues.add(attrValueDomain.getAttrDisplayValue() == null ? attrValueDomain.getAttrValue() : attrValueDomain.getAttrDisplayValue());
				}

				ProductAttrDomain productAttrDomain = new ProductAttrDomain();
				productAttrDomain.setAttrNameId(attrNameId);
				productAttrDomain.setAttrNameType(ConstAttr.AttrNameType.KEY_ATTR);
				productAttrDomain.setAttrValueId(attrValueId);
				productAttrDomainList.add(productAttrDomain);
			}
			
			productDomain.setProductKeyAttrValueIds(StringTools.concatWithSymbol(",", keyAttrValueIds) );
			productDomain.setProductKeyAttrValueValues(StringTools.concatWithSymbol(" ",attrValues));
			
		}
		
		/**
		 * 设定普通属性名值ID集合 和 普通属性值值集合
		 */
		List<Integer> normalAttrValueIds = createProdcutParam.getProductNormalAttrValueIds();	
		if (CollectionTools.isNotNullOrEmpty(normalAttrValueIds)) {
			/**
			 * 必须先排序
			 */
			Collections.sort(normalAttrValueIds);
			/**
			 * 产品目录分类ID
			 */
			Integer categoryId = createProdcutParam.getCategoryId();
			/**
			 * 校验属性值ID必须存在，属性值对应的属性名ID不能重复，必须有和创建的产品有相同的产品目录
			 */
			List<String> attrValues = new ArrayList<String>();
			for (Integer attrValueId : normalAttrValueIds) {
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(attrValueId);

				Integer attrNameId = attrValueDomain.getAttrNameId();
				if (categoryId.equals(attrValueDomain.getCategoryId())) {
					throw new CategoryIsDifferentRuntimeException();
				}

				if (checkAttrNameMap.containsKey(attrNameId)) {
					throw new AttrNameIsNotUniqueException();
				} else {
					checkAttrNameMap.put(attrNameId, attrNameId);
					attrValues.add(attrValueDomain.getAttrDisplayValue() == null ? attrValueDomain.getAttrValue() : attrValueDomain.getAttrDisplayValue());
				}

				ProductAttrDomain productAttrDomain = new ProductAttrDomain();
				productAttrDomain.setAttrNameId(attrNameId);
				productAttrDomain.setAttrNameType(ConstAttr.AttrNameType.KEY_ATTR);
				productAttrDomain.setAttrValueId(attrValueId);
				productAttrDomainList.add(productAttrDomain);
			}
			
		}

		productDomain.setId(pkGenService.genPk());
		
		for (ProductAttrDomain productAttrDomain : productAttrDomainList){
			productAttrDomain.setProductId(productDomain.getId());
			productAttrDomain.setId(pkGenService.genPk());
			productAttrMapper.insert(productAttrDomain);
		}

		try {
			productMapper.insert(productDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateProductRuntimeException();
		}

		return productDomain;
	}

	/**
	 * 被更新的产品必须存在，状态为编辑状态。 更新不能修改关键属性 更新是否可以修改产品编号？ 更新是否要保留上次版本，这个得看产品的销售情况
	 * 不是本用户的商铺的商品，不能修改
	 * 
	 * @param updateProdcutParam
	 */
	@Valid
	@Transactional
	public void updateProduct(@Valid @NotEmpty("参数") UpdateProductParam updateProdcutParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		ProductDomain productDomain = loadProduct(updateProdcutParam.getId());
		if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING) {
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
	 * 只能修改用户自己商铺的产品状态 改为上架状态时，必须填写上架时间，下架时间
	 * 
	 * @param updateProdcutStatusParam
	 */
	@Valid
	@Transactional
	public void updateProductStatus(@Valid @NotEmpty("参数") UpdateProductStatusParam updateProdcutStatusParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		int status = updateProdcutStatusParam.getProductStatus();
		for (Integer id : updateProdcutStatusParam.getIds()) {
			ProductDomain productDomain = loadProduct(id);
			if (status != productDomain.getProductStatus()) {
				ProductDomain updateDomain = new ProductDomain();
				if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING) {
					throw new ProductStatusIsNotAcceptableRuntimeException();
				}

				updateDomain.setProductStatus(status);
				updateDomain.setUpdateDate(new Date());
				updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());

				if (status == ConstProduct.ProductStatus.ON_SALE) {
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
	 * 上架不能删， 不是本用户的商铺的商品不能删 有销售记录，删除要保留快照，保留快照还得看SKU
	 * 
	 * @param removeProductParam
	 */
	@Valid
	@Transactional
	public void removeProduct(@Valid @NotEmpty("参数") RemoveProductParam removeProductParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		for (Integer id : removeProductParam.getIds()) {
			if (id == null) {
				break;
			}

			ProductDomain productDomain = loadProduct(id);
			if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING) {
				throw new ProductStatusIsNotAcceptableRuntimeException();
			}

			if (productDomain.getProductStatus() == ConstProduct.ProductStatus.ON_SALE) {
				throw new ProductStatusIsNotAcceptableRuntimeException();
			}

			/**
			 * TODO:检测是否是本用户的商铺的商品
			 */
			if (!productDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {

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
	public List<ProductDomain> listProduct(@Valid @NotEmpty("参数") ListProductParam listProductParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		if (StringTools.isNullOrEmpty(listProductParam.getOrder())) {
			listProductParam.setOrder("create_date desc");
		}

		MybatisParam params = MybatisTools.page(new MybatisParam(), listProductParam);
		return productMapper.loadDynamicPaging(params);
	}
}
