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
import com.hawk.ecom.product.exception.AttrNameIsUsedByProductRuntimeException;
import com.hawk.ecom.product.exception.AttrNameIsNotUsedByProductRuntimeException;
import com.hawk.ecom.product.exception.CategoryIsDifferentRuntimeException;
import com.hawk.ecom.product.exception.CategoryIsNotLeafRuntimeException;
import com.hawk.ecom.product.exception.CategoryNotFoundRuntimeException;
import com.hawk.ecom.product.exception.CategoryVariantStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.DuplicateProductRuntimeException;
import com.hawk.ecom.product.exception.ProductIsNotAcceptableForOnSaleRuntimeException;
import com.hawk.ecom.product.exception.ProductNotFoundRuntimeException;
import com.hawk.ecom.product.exception.ProductStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.UnMatchedStoreOperatorException;
import com.hawk.ecom.product.persist.domain.AttrNameDomain;
import com.hawk.ecom.product.persist.domain.AttrValueDomain;
import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.persist.domain.ProductAttrDomain;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.domain.ProductHistoryDomain;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.persist.domain.SkuHistoryDomain;
import com.hawk.ecom.product.persist.domain.StockDomain;
import com.hawk.ecom.product.persist.domain.StockHistoryDomain;
import com.hawk.ecom.product.persist.mapper.ProductAttrMapper;
import com.hawk.ecom.product.persist.mapper.ProductHistoryMapper;
import com.hawk.ecom.product.persist.mapper.ProductMapper;
import com.hawk.ecom.product.persist.mapper.SkuHistoryMapper;
import com.hawk.ecom.product.persist.mapper.SkuMapper;
import com.hawk.ecom.product.persist.mapper.StockHistoryMapper;
import com.hawk.ecom.product.persist.mapper.StockMapper;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.request.ListProductParam;
import com.hawk.ecom.product.request.LoadProductParam;
import com.hawk.ecom.product.request.RemoveProductParam;
import com.hawk.ecom.product.request.UpdateProductParam;
import com.hawk.ecom.product.request.UpdateProductStatusParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;
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
	private ProductHistoryMapper productHistoryMapper;

	@Autowired
	private SkuService skuService;

	@Autowired
	private SkuMapper skuMapper;

	@Autowired
	private SkuHistoryMapper skuHistoryMapper;

	@Autowired
	private StockMapper stockMapper;

	@Autowired
	private StockHistoryMapper stockHistoryMapper;

	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public final static String ATTR_NAME_ID_SPLITTER = ",";
	public final static String ATTR_DISPLAY_VALUE_SPLITTER = " ";

	public ProductDomain loadProduct(Integer id) {
		ProductDomain productDomain = null;
		if (id != null) {
			productDomain = productMapper.load(id);
		}else{
			logger.error("loadProduct: id is null");
			
		}
		if (productDomain == null) {
			throw new ProductNotFoundRuntimeException();
		}
		return productDomain;
	}

	@Valid
	public ProductDomain loadProduct(@Valid @NotNull("参数") LoadProductParam loadProdcutParam) {

		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		ProductDomain productDomain = loadProduct(loadProdcutParam.getId());
		/**
		 * 检测是否是本用户的商铺的商品
		 */
		if (!productDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
			throw new UnMatchedStoreOperatorException();
		}

		return productDomain;
	}

	/**
	 * 产品是否有上架状态的sku存在
	 * 
	 * @param productId
	 * @return
	 */
	public boolean hasAnyOnSaleSku(Integer productId) {
		if (productId == null) {
			logger.error("hasAnyOnSaleSku: productId is null");
			return false;
		}
		MybatisParam params = new MybatisParam().put("productId", productId).put("skuStatus", ConstProduct.SkuStatus.ON_SALE);
		return skuMapper.count(params) > 0;
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
	public ProductDomain createProduct(@Valid @NotNull("参数") CreateProductParam createProductParam) {

		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		/**
		 * 校验,产品目录必须存在，产品目录必须是叶子节点，产品目录变式状态必须是可用的。
		 */
		CategoryDomain category = categoryService.loadCategory(createProductParam.getCategoryId());
		if (category == null) {
			throw new CategoryNotFoundRuntimeException();
		}
		if (!ConstBoolean.parse(category.getIsLeaf())) {
			throw new CategoryIsNotLeafRuntimeException();
		}

		if (category.getCategoryVariantStatus() != ConstCategory.CategoryVariantStatus.AVAILABLE) {
			throw new CategoryVariantStatusIsNotAcceptableRuntimeException();
		}

		Date now = new Date();
		ProductDomain productDomain = new ProductDomain();
		productDomain.setCategoryId(category.getId());
		productDomain.setCreateDate(now);
		productDomain.setCreateUserCode(AuthThreadLocal.getUserCode());

		productDomain.setIsVirtual(createProductParam.getIsVirtual());

		productDomain.setProductCode(createProductParam.getProductCode());
		
		
		productDomain.setProductDesc(createProductParam.getProductDesc());
		productDomain.setProductHomePage(createProductParam.getProductHomePage());
		productDomain.setProductMemo(createProductParam.getProductMemo());
		productDomain.setProductName(createProductParam.getProductName());
		productDomain.setProductStatus(ConstProduct.ProductStatus.EDITING);
		productDomain.setStoreCode(AuthThreadLocal.getStoreCode());
		productDomain.setThumbnail(createProductParam.getThumbnail());
		productDomain.setDeliveryType(createProductParam.getDeliveryType());
		productDomain.setUpdateDate(now);
		productDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());

		/**
		 * 检查所有输入的属性值对应的属性名Id和sku属性ID 不能出现相同的属性ID
		 */
		Map<Integer, Integer> checkAttrNameMap = new HashMap<Integer, Integer>();

		/**
		 * 设置SKU属性名ID集合
		 */
		List<Integer> skuAttrNameIds = createProductParam.getProductSkuAttrNameIds();
		if (CollectionTools.isNotNullOrEmpty(skuAttrNameIds)) {
			/**
			 * 必须先排序
			 */
			Collections.sort(skuAttrNameIds);
			/**
			 * 产品目录分类ID
			 */
			Integer categoryId = createProductParam.getCategoryId();
			/**
			 * 校验属性名ID必须存在，不能重复，必须有和创建的产品有相同的产品目录
			 */
			for (Integer attrNameId : skuAttrNameIds) {
				AttrNameDomain attrNameDomain = attrNameService.loadAttrNameById(attrNameId);
				if (!categoryId.equals(attrNameDomain.getCategoryId())) {
					throw new CategoryIsDifferentRuntimeException();
				}

				if (checkAttrNameMap.containsKey(attrNameId)) {
					throw new AttrNameIsUsedByProductRuntimeException();
				} else {
					checkAttrNameMap.put(attrNameId, attrNameId);
				}
			}

			productDomain.setProductSkuAttrNameIds(StringTools.concatWithSymbol(ATTR_NAME_ID_SPLITTER, skuAttrNameIds));
		}

		/**
		 * 设定关键属性名值ID集合 和 关键属性值值集合
		 */
		List<Integer> keyAttrValueIds = createProductParam.getProductKeyAttrValueIds();
		List<ProductAttrDomain> productAttrDomainList = new ArrayList<ProductAttrDomain>();
		if (CollectionTools.isNotNullOrEmpty(keyAttrValueIds)) {
			/**
			 * 必须先排序
			 */
			Collections.sort(keyAttrValueIds);
			/**
			 * 产品目录分类ID
			 */
			Integer categoryId = createProductParam.getCategoryId();
			/**
			 * 校验属性值ID必须存在，属性值对应的属性名ID不能重复，必须有和创建的产品有相同的产品目录
			 */
			List<String> attrValues = new ArrayList<String>();
			for (Integer attrValueId : keyAttrValueIds) {
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(attrValueId);

				Integer attrNameId = attrValueDomain.getAttrNameId();
				if (!categoryId.equals(attrValueDomain.getCategoryId())) {
					throw new CategoryIsDifferentRuntimeException();
				}

				if (checkAttrNameMap.containsKey(attrNameId)) {
					throw new AttrNameIsUsedByProductRuntimeException();
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

			productDomain.setProductKeyAttrValueIds(StringTools.concatWithSymbol(ATTR_NAME_ID_SPLITTER, keyAttrValueIds));
			productDomain.setProductKeyAttrValueValues(StringTools.concatWithSymbol(ATTR_DISPLAY_VALUE_SPLITTER, attrValues));

		}

		/**
		 * 设定普通属性名值ID集合 和 普通属性值值集合
		 */
		List<Integer> normalAttrValueIds = createProductParam.getProductNormalAttrValueIds();
		if (CollectionTools.isNotNullOrEmpty(normalAttrValueIds)) {

			/**
			 * 产品目录分类ID
			 */
			Integer categoryId = createProductParam.getCategoryId();
			/**
			 * 校验属性值ID必须存在，属性值对应的属性名ID不能重复，必须有和创建的产品有相同的产品目录
			 */
			for (Integer attrValueId : normalAttrValueIds) {
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(attrValueId);

				Integer attrNameId = attrValueDomain.getAttrNameId();
				if (!categoryId.equals(attrValueDomain.getCategoryId())) {
					throw new CategoryIsDifferentRuntimeException();
				}

				if (checkAttrNameMap.containsKey(attrNameId)) {
					throw new AttrNameIsUsedByProductRuntimeException();
				} else {
					checkAttrNameMap.put(attrNameId, attrNameId);
				}

				ProductAttrDomain productAttrDomain = new ProductAttrDomain();
				productAttrDomain.setAttrNameId(attrNameId);
				productAttrDomain.setAttrNameType(ConstAttr.AttrNameType.NORMAL_DESC_ATTR);
				productAttrDomain.setAttrValueId(attrValueId);
				productAttrDomainList.add(productAttrDomain);
			}

		}

		productDomain.setProductVersion(1);
		productDomain.setId(pkGenService.genPk());

		for (ProductAttrDomain productAttrDomain : productAttrDomainList) {
			productAttrDomain.setProductId(productDomain.getId());
			productAttrDomain.setId(pkGenService.genPk());
			productAttrDomain.setSkuId(0);
			productAttrDomain.setCreateDate(productDomain.getCreateDate());
			productAttrDomain.setCreateUserCode(productDomain.getCreateUserCode());
			productAttrDomain.setUpdateDate(productDomain.getUpdateDate());
			productAttrDomain.setDeleteUserCode(productDomain.getUpdateUserCode());
			productAttrMapper.insert(productAttrDomain);
		}

		if (StringTools.isNullOrEmpty(productDomain.getProductCode())){
			productDomain.setProductCode(productDomain.getId().toString());
		}
		try {
			productMapper.insert(productDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateProductRuntimeException();
		}

		return productDomain;
	}

	/**
	 * 被更新的产品必须存在，状态为编辑状态。 
	 * 修改关键属性值时，各种校验
	 * 修改普通属性值时，各种校验
	 * 修改SKU属性名时，各种校验
	 * 更新是否可以修改产品编号？
	 * 属性值发生改变时，如何通知商品修改关键属性值集合显示内容？ 
	 * 更新是否要保留上次版本，这个得看产品的销售情况
	 * 不是本用户的商铺的商品，不能修改
	 * 
	 * @param updateProdcutParam
	 * @throws Exception 
	 */
	@Valid
	@Transactional
	public void updateProduct(@Valid @NotNull("参数") UpdateProductParam updateProdcutParam) throws Exception {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		Integer productId = updateProdcutParam.getId();
		ProductDomain productDomain = loadProduct(productId);
		if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING) {
			throw new ProductStatusIsNotAcceptableRuntimeException();
		}

		/**
		 * 检测是否是本用户的商铺的商品
		 */
		if (!productDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())){
			throw new UnMatchedStoreOperatorException();
		}
		
		Date now = new Date();
		String userCode = AuthThreadLocal.getUserCode();
		/**
		 * 复制要更新的基本属性
		 */
		ProductDomain updateDomain = new ProductDomain();
		DomainTools.copy(updateProdcutParam, updateDomain);
		updateDomain.setUpdateDate(now);
		updateDomain.setUpdateUserCode(userCode);
		
		/**
		 * 加载所有已经用到的属性名ID,
		 * 先加载关键属性和非关键属性
		 * 再加载SKU属性
		 */
		MybatisParam params = new MybatisParam().put("productId", productDomain.getId()).put("skuId", 0);
		List<ProductAttrDomain> productAttrDomainList = productAttrMapper.loadDynamic(params);
		Map<Integer,Integer> usedAttrNameIdMap = new HashMap<Integer,Integer>();
		Map<Integer,Integer> usedKeyAttrNameIdMap = new HashMap<Integer,Integer>();
		Map<Integer,Integer> usedKeyAttrValueIdMap = new HashMap<Integer,Integer>();
		Map<Integer,Integer> usedSkuAttrNameIdMap = new HashMap<Integer,Integer>();
		productAttrDomainList.forEach(e->{
			usedAttrNameIdMap.put(e.getAttrNameId(), e.getAttrNameId());
			if (e.getAttrNameType().equals(ConstAttr.AttrNameType.KEY_ATTR)){
				usedKeyAttrNameIdMap.put(e.getAttrNameId(), e.getAttrNameId());
				usedKeyAttrValueIdMap.put(e.getAttrValueId(), e.getAttrValueId());
			}
		});
		String skuAttrNameIdsStr = productDomain.getProductSkuAttrNameIds();
		if (StringTools.isNotNullOrEmpty(skuAttrNameIdsStr)){
			String[] strs = skuAttrNameIdsStr.split(ATTR_NAME_ID_SPLITTER);
			for(String str : strs){
				Integer attrNameId = Integer.parseInt(str);
				usedAttrNameIdMap.put(attrNameId, attrNameId);
				usedSkuAttrNameIdMap.put(attrNameId, attrNameId);
			}
		}
		
		/**
		 * 首先计算要删除的关键属性值，非关键属性值，sku属性
		 */
		/**
		 * 删除关键属性值ID
		 */			
		List<Integer> removeKeyAttrValueIds = updateProdcutParam.getRemoveKeyAttrValueIds();
		if (CollectionTools.isNotNullOrEmpty(removeKeyAttrValueIds)){
			for (Integer removeKeyAttrValueId : removeKeyAttrValueIds){
				
				
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(removeKeyAttrValueId);
				Integer attrNameId = attrValueDomain.getAttrNameId();
				
				/**
				 * 要删除的关键属性值ID，必须已经被引用
				 */
				if  (!usedKeyAttrNameIdMap.containsKey(attrNameId) || !usedAttrNameIdMap.containsKey(attrNameId)){
					throw new AttrNameIsNotUsedByProductRuntimeException();
				}
				
				params = new MybatisParam().put("productId", productDomain.getId()).put("attrNameId", attrNameId);
				ProductAttrDomain productAttrDomain = MybatisTools.single(productAttrMapper.loadDynamic(params));
				productAttrMapper.delete(productAttrDomain.getId());
				usedAttrNameIdMap.remove(attrNameId);
				usedKeyAttrNameIdMap.remove(attrNameId);
				usedKeyAttrValueIdMap.remove(removeKeyAttrValueId);
			}
		}
		/**
		 * 删除非关键属性值ID
		 */
		List<Integer> removeNormalKeyAttrValueIds = updateProdcutParam.getRemoveNormalAttrValueIds();
		if (CollectionTools.isNotNullOrEmpty(removeNormalKeyAttrValueIds)){
			for (Integer removeNormalAttrValueId : removeNormalKeyAttrValueIds){
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(removeNormalAttrValueId);
				Integer attrNameId = attrValueDomain.getAttrNameId();
				/**
				 * 要删除的非关键属性值ID，必须已经被引用
				 */
				if  (!usedAttrNameIdMap.containsKey(attrNameId)){
					throw new AttrNameIsNotUsedByProductRuntimeException();
				}
				params = new MybatisParam().put("productId", productDomain.getId()).put("attrNameId", attrNameId);
				ProductAttrDomain productAttrDomain = MybatisTools.single(productAttrMapper.loadDynamic(params));
				productAttrMapper.delete(productAttrDomain.getId());
				usedAttrNameIdMap.remove(attrNameId);
			}
		}
		/**
		 * 删除Sku属性名ID
		 */
		List<Integer> removeSkuAttrNameIds = updateProdcutParam.getRemoveSkuAttrNameIds();
		if (CollectionTools.isNotNullOrEmpty(removeSkuAttrNameIds)){
			for (Integer removeSkuAttrNameId : removeSkuAttrNameIds){
				/**
				 * 要删除的Sku属性名ID，必须已经被引用
				 */
				if (!usedAttrNameIdMap.containsKey(removeSkuAttrNameId) || !usedSkuAttrNameIdMap.containsKey(removeSkuAttrNameId)){
					throw new AttrNameIsNotUsedByProductRuntimeException();
				}
				
				usedAttrNameIdMap.remove(removeSkuAttrNameId);
				usedSkuAttrNameIdMap.remove(removeSkuAttrNameId);
			}
		}
		
		/**
		 * 收集新增加的关键属性和非关键属性，在最后插入数据库
		 */
		List<ProductAttrDomain> addProductAttrDomainList = new ArrayList<ProductAttrDomain>();
		/**
		 * 新增关键属性
		 */
		List<Integer> addKeyAttrValueIds  = updateProdcutParam.getAddKeyAttrValueIds();
		if(CollectionTools.isNotNullOrEmpty(addKeyAttrValueIds)){
			for (Integer addAttrValueId : addKeyAttrValueIds){
				
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(addAttrValueId);

				Integer attrNameId = attrValueDomain.getAttrNameId();
				if (!productDomain.getCategoryId().equals(attrValueDomain.getCategoryId())) {
					throw new CategoryIsDifferentRuntimeException();
				}
				
				/**
				 * 要新增的关键属性值对应的属性名ID，必须未被引用
				 */
				if  (usedKeyAttrNameIdMap.containsKey(attrNameId) || usedAttrNameIdMap.containsKey(attrNameId)){
					throw new AttrNameIsUsedByProductRuntimeException();
				}
				
				/**
				 * 维护map
				 */
				usedKeyAttrNameIdMap.put(attrNameId, attrNameId);
				usedAttrNameIdMap.put(attrNameId, attrNameId);
				usedKeyAttrValueIdMap.put(addAttrValueId, addAttrValueId);
				
				/**
				 * 创建插入值
				 */
				ProductAttrDomain productAttrDomain = new ProductAttrDomain();
				productAttrDomain.setAttrNameId(attrNameId);
				productAttrDomain.setAttrNameType(ConstAttr.AttrNameType.KEY_ATTR);
				productAttrDomain.setAttrValueId(addAttrValueId);					
				addProductAttrDomainList.add(productAttrDomain);
			}
			
			/**
			 * 合成
			 */
			if(usedKeyAttrNameIdMap.size() == 0){
				updateDomain.setProductKeyAttrValueIds("");
				updateDomain.setProductKeyAttrValueValues("");
			}else{
				List<Integer> keyAttrValueIds = new ArrayList<Integer>();
				usedKeyAttrValueIdMap.keySet().forEach(e->{
					keyAttrValueIds.add(e);
				});
				Collections.sort(keyAttrValueIds);
				List<String> keyAttrValues = new ArrayList<String>();
				keyAttrValueIds.forEach(e->{
					AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(e);
					keyAttrValues.add(attrValueDomain.getAttrDisplayValue() == null ? attrValueDomain.getAttrValue() : attrValueDomain.getAttrDisplayValue());
				});	
				
				updateDomain.setProductKeyAttrValueIds(StringTools.concatWithSymbol(ATTR_NAME_ID_SPLITTER, keyAttrValueIds) );
				updateDomain.setProductKeyAttrValueValues(StringTools.concatWithSymbol(ATTR_DISPLAY_VALUE_SPLITTER,keyAttrValues));
			}
			
		}
		
		/**
		 * 新增非关键属性
		 */
		List<Integer> addNormalAttrValueIds  = updateProdcutParam.getAddNormalAttrValueIds();
		if(CollectionTools.isNotNullOrEmpty(addNormalAttrValueIds)){
			for (Integer addAttrValueId : addNormalAttrValueIds){
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(addAttrValueId);

				Integer attrNameId = attrValueDomain.getAttrNameId();
				if (!productDomain.getCategoryId().equals(attrValueDomain.getCategoryId())) {
					throw new CategoryIsDifferentRuntimeException();
				}
				
				/**
				 * 要新增的非关键属性值对应的属性名ID，必须未被引用
				 */
				if  (usedAttrNameIdMap.containsKey(attrNameId)){
					throw new AttrNameIsUsedByProductRuntimeException();
				}
				
				/**
				 * 维护map
				 */
				usedAttrNameIdMap.put(attrNameId, attrNameId);
				
				/**
				 * 创建插入值
				 */
				ProductAttrDomain productAttrDomain = new ProductAttrDomain();
				productAttrDomain.setAttrNameId(attrNameId);
				productAttrDomain.setAttrNameType(ConstAttr.AttrNameType.NORMAL_DESC_ATTR);
				productAttrDomain.setAttrValueId(addAttrValueId);					
				addProductAttrDomainList.add(productAttrDomain);
			}
		}
		
		
		/**
		 * 新增SKU属性
		 */
		List<Integer> addSkuAttrNameIds = updateProdcutParam.getAddSkuAttrNameIds();
		for (Integer attrNameId : addSkuAttrNameIds){
			/**
			 * 要新增的Sku属性名ID，必须未被引用
			 */
			if  (usedSkuAttrNameIdMap.containsKey(attrNameId) || usedAttrNameIdMap.containsKey(attrNameId)){
				throw new AttrNameIsUsedByProductRuntimeException();
			}
			
			usedSkuAttrNameIdMap.put(attrNameId, attrNameId);
			usedAttrNameIdMap.put(attrNameId, attrNameId);
		}
		/*合成SkU*/
		if (usedSkuAttrNameIdMap.size() == 0){
			updateDomain.setProductSkuAttrNameIds("");
		}else{
			List<Integer> skuAttrNameIds = new ArrayList<Integer>();
			usedSkuAttrNameIdMap.keySet().forEach(e->{
				skuAttrNameIds.add(e);
			});
			Collections.sort(skuAttrNameIds);
			updateDomain.setProductSkuAttrNameIds(StringTools.concatWithSymbol(ATTR_NAME_ID_SPLITTER, skuAttrNameIds));
		}
		
		
		/**
		 * 是否需要变更版本号
		 */
		if (needChangeVersion(productDomain,updateDomain)){
			updateDomain.setProductVersion(productDomain.getProductVersion()+1);
			/*保留历史*/
			ProductHistoryDomain productHistoryDomain = DomainTools.copy(productDomain, ProductHistoryDomain.class);
			productHistoryMapper.insert(productHistoryDomain);
			
			/**
			 * 产品发生重大变化，强迫产品sku进入编辑状态,
			 * 后续sku上架或更新的时候，会校验对应的sku属性名集合是否一致，强迫更新成一致，才能上架
			 */
			forceSkuEditing(productId,now,userCode);
		}
		

		try {
			productMapper.updateWithoutNull(updateDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateProductRuntimeException();
		}
		
		for (ProductAttrDomain productAttrDomain:addProductAttrDomainList){
			productAttrDomain.setCreateDate(now);
			productAttrDomain.setCreateUserCode(userCode);
			productAttrDomain.setId(pkGenService.genPk());
			productAttrDomain.setProductId(productDomain.getId());
			productAttrDomain.setSkuId(0);
			productAttrDomain.setUpdateDate(now);
			productAttrDomain.setUpdateUserCode(userCode);
			productAttrMapper.insert(productAttrDomain);
		}
	}
	
	private void forceSkuEditing(int productId,Date now, String userCode){
		MybatisParam params = new MybatisParam()
				.put("productId", productId)
				.put("skuStatus", ConstProduct.SkuStatus.ON_SALE);
		List<SkuDomain> skuDomainList = skuMapper.loadDynamic(params);
		for (SkuDomain skuDomain : skuDomainList){
			SkuDomain updateDomain = new SkuDomain();
			updateDomain.setId(skuDomain.getId());
			updateDomain.setSkuStatus(ConstProduct.SkuStatus.EDITING);
			updateDomain.setUpdateDate(now);
			updateDomain.setUpdateUserCode(userCode);
		}
	}

	public boolean needChangeVersion(ProductDomain pre, ProductDomain next) {
		if (next.getProductName() != null && !next.getProductName().equalsIgnoreCase(pre.getProductName())) {
			return true;
		}
		
		if (next.getProductCode() != null && !next.getProductName().equalsIgnoreCase(pre.getProductCode())) {
			return true;
		}

		if (next.getProductKeyAttrValueValues() != null && !next.getProductKeyAttrValueValues().equalsIgnoreCase(pre.getProductKeyAttrValueValues())) {
			return true;
		}
		
		if (next.getProductSkuAttrNameIds() != null && ! next.getProductSkuAttrNameIds().equalsIgnoreCase(pre.getProductSkuAttrNameIds())){
			return true;
		}		
		
		return false;
	}

	/**
	 * 只能修改用户自己商铺的产品状态 
	 * 改为上架状态时，必须填写上架时间，下架时间
	 * 改为上架状态时，必须有上架的Sku
	 * 
	 * @param updateProdcutStatusParam
	 */
	@Valid
	@Transactional
	public void updateProductStatus(@Valid @NotNull("参数") UpdateProductStatusParam updateProductStatusParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		int status = updateProductStatusParam.getProductStatus();		
		
		for (Integer productId : updateProductStatusParam.getIds()) {
			ProductDomain productDomain = loadProduct(productId);
			/**
			 * 检测是否是本用户的商铺的商品
			 */
			if (!productDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())){
				throw new UnMatchedStoreOperatorException();
			}
			
			if (status != productDomain.getProductStatus()) {
				ProductDomain updateDomain = new ProductDomain();				

				updateDomain.setProductStatus(status);
				updateDomain.setUpdateDate(new Date());
				updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
				updateDomain.setId(productId);

				if (status == ConstProduct.ProductStatus.ON_SALE) {
					/**
					 * 检测时间
					 */
					Date stdt = updateProductStatusParam.getOnSaleStdt();
					if (stdt == null){
						stdt = productDomain.getOnSaleStdt();
					}
					Date endt = updateProductStatusParam.getOnSaleEndt();
					if (endt == null){
						endt = productDomain.getOnSaleEndt();
					}
					if (stdt == null || endt == null || stdt.after(endt) || endt.before(new Date()))
						throw new ProductIsNotAcceptableForOnSaleRuntimeException();

					/**
					 * 检测是否有上架的产品SKU
					 */
					if (!hasAnyOnSaleSku(productDomain.getId())){
						throw new ProductIsNotAcceptableForOnSaleRuntimeException();
					}
					
					

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
	 * 有销售记录，不能删
	 * 
	 * @param removeProductParam
	 * @throws Exception 
	 */
	@Valid
	@Transactional
	public void removeProduct(@Valid @NotNull("参数") RemoveProductParam removeProductParam) throws Exception {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		
		Date now = new Date();
		String userCode = AuthThreadLocal.getUserCode();

		for (Integer productId : removeProductParam.getIds()) {
			if (productId == null) {
				break;
			}

			ProductDomain productDomain = loadProduct(productId);
			
			if (productDomain.getProductStatus() == ConstProduct.ProductStatus.ON_SALE) {
				throw new ProductStatusIsNotAcceptableRuntimeException();
			}

			/**
			 * TODO:检测是否是本用户的商铺的商品
			 */
			if (!productDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {

			}

			/**
			 * TODO:检测有没有销售记录,有不能删除
			 */

			
			/**
			 * TODO:删除属性,SKU属性,图片
			 *
			 */
			
			/**
			 * TODO:保留产品SkU库存历史记录，并删除产品SKU库存
			 */
			MybatisParam params = new MybatisParam().put("productId", productId);
			List<StockDomain> stockDomainList = stockMapper.loadDynamic(params);
			for (StockDomain stockDomain : stockDomainList){
				StockHistoryDomain stockHistoryDomain = DomainTools.copy(stockDomain, StockHistoryDomain.class);
				stockHistoryDomain.setDeleteDate(now);
				stockHistoryDomain.setDeleteUserCode(userCode);
				stockHistoryMapper.insert(stockHistoryDomain);
				stockMapper.delete(stockDomain.getId());
			}		
			
			
			/**
			 * 保留产品sku历史记录并删除产品Sku
			 */
			List<SkuDomain> skuDomainList = skuService.querySkuOfProduct(productId, null);
			for (SkuDomain skuDomain : skuDomainList){
				SkuHistoryDomain skuHistoryDomain = DomainTools.copy(skuDomain, SkuHistoryDomain.class);
				skuHistoryDomain.setDeleteDate(now);
				skuHistoryDomain.setDeleteUserCode(userCode);
				skuHistoryMapper.insert(skuHistoryDomain);
				skuMapper.delete(skuDomain.getId());
			}
			
			
			/**
			 * 保留产品历史记录并删除产品
			 */
			ProductHistoryDomain productHistoryDomain = DomainTools.copy(productDomain, ProductHistoryDomain.class);
			productHistoryDomain.setDeleteDate(now);
			productHistoryDomain.setDeleteUserCode(userCode);
			productHistoryMapper.insert(productHistoryDomain);			
			productMapper.delete(productId);
		}
	}

	@Valid
	public PagingQueryResultWrap<ProductDomain> listProduct(@Valid @NotNull("参数") ListProductParam listProductParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		
		

		if (StringTools.isNullOrEmpty(listProductParam.getOrder())) {
			listProductParam.setOrder("create_date desc");
		}

		MybatisParam params = MybatisTools.page(new MybatisParam(), listProductParam);
		params.put("categoryId", listProductParam.getCategoryId());
		params.put("isVirtual", listProductParam.getIsVirtual());
		params.put("productStatus", listProductParam.getProductStatus());
		params.put("deliveryType", listProductParam.getDeliveryType());
		
		PagingQueryResultWrap<ProductDomain> wrap = new PagingQueryResultWrap<ProductDomain>();
		wrap.setDbCount(productMapper.count(params));
		if (wrap.getDbCount() > 0){
			wrap.setRecords(productMapper.loadDynamicPaging(params));
		}

		return wrap;
	}
}
