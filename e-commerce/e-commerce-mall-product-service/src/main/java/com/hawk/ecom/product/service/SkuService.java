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
import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.exception.AttrNameIsNotUsedByProductRuntimeException;
import com.hawk.ecom.product.exception.AttrNameIsUsedByProductRuntimeException;
import com.hawk.ecom.product.exception.CategoryIsDifferentRuntimeException;
import com.hawk.ecom.product.exception.DuplicateSkuRuntimeException;
import com.hawk.ecom.product.exception.LackOfSkuAttrNameOfProductRuntimeException;
import com.hawk.ecom.product.exception.ProductStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.SkuAttrNameIdsIsNotSameWithProductRuntimeException;
import com.hawk.ecom.product.exception.SkuAttrNameIsNotDesignedByProductRuntimeException;
import com.hawk.ecom.product.exception.SkuIsNotAcceptableForSaleRuntimeException;
import com.hawk.ecom.product.exception.SkuNotFoundRuntimeException;
import com.hawk.ecom.product.exception.SkuStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.UnMatchedStoreOperatorException;
import com.hawk.ecom.product.persist.domain.AttrValueDomain;
import com.hawk.ecom.product.persist.domain.ProductAttrDomain;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.persist.domain.SkuHistoryDomain;
import com.hawk.ecom.product.persist.domain.SkuSnapshootDomain;
import com.hawk.ecom.product.persist.domain.StockDomain;
import com.hawk.ecom.product.persist.domain.StockHistoryDomain;
import com.hawk.ecom.product.persist.mapper.ProductAttrMapper;
import com.hawk.ecom.product.persist.mapper.SkuHistoryMapper;
import com.hawk.ecom.product.persist.mapper.SkuMapper;
import com.hawk.ecom.product.persist.mapper.SkuSnapshootMapper;
import com.hawk.ecom.product.persist.mapper.StockHistoryMapper;
import com.hawk.ecom.product.persist.mapper.StockMapper;
import com.hawk.ecom.product.request.CreateSkuParam;
import com.hawk.ecom.product.request.ListSkuParam;
import com.hawk.ecom.product.request.LoadSkuParam;
import com.hawk.ecom.product.request.RemoveSkuParam;
import com.hawk.ecom.product.request.UpdateSkuParam;
import com.hawk.ecom.product.request.UpdateSkuStatusParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.CollectionTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class SkuService {

	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;

	@Autowired
	private MallAuthService authService;

	@Autowired
	private AttrValueService attrValueService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductAttrMapper productAttrMapper;
	
	@Autowired
	private SkuSnapshootMapper skuSnapshootMapper;

	@Autowired
	private SkuMapper skuMapper;
	
	@Autowired
	private StockMapper stockMapper;
	
	@Autowired
	private StockHistoryMapper stockHistoryMapper;
	
	@Autowired
	private SkuHistoryMapper skuHistoryMapper;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public SkuDomain loadSkuById(Integer id) {
		SkuDomain skuDomain = null;
		if (id != null) {
			skuDomain = skuMapper.load(id);
		}
		if (skuDomain == null) {
			throw new SkuNotFoundRuntimeException();
		}
		return skuDomain;
	}
	
	/**
	 * 乐观锁更新产品SkU库存
	 * @param skuDomain
	 * @param delta
	 * @param userCode
	 * @param now
	 * @return
	 */
	public boolean updateSkuSotckQuantity(SkuDomain skuDomain, int delta,String userCode,Date now){
		/**
		 * 乐观锁更新产品Sku的数量,成功后,增加库存条目
		 */
		MybatisParam params = new MybatisParam()//
				.put("old_skuStockQuantity", skuDomain.getSkuStockQuantity())//
				.put("old_updateDate", skuDomain.getUpdateDate())//
				.put("old_id", skuDomain.getId())//
				.put("old_updateUserCode", skuDomain.getUpdateUserCode())//
				.put("skuStockQuantity", skuDomain.getSkuStockQuantity() + delta)//
				.put("updateUserCode", userCode)//
				.put("updateDate", now);
		int updatedRowCount = skuMapper.updateDynamic(params);
		if (updatedRowCount != 1){
			return false;
		}
		
		return true;
	}

	/**
	 * 产品主键必须存在,产品必须是编辑状态 非虚拟产品的尺寸重量不能为空 SKU属性组合，一个属性都不能少，SKU属性组合不能重复出现
	 * 新创建的产品SKU为编辑状态
	 * 
	 * @param createSkuParam
	 * @return
	 */
	@Valid
	@Transactional
	public SkuDomain createSku(@Valid @NotNull("参数") CreateSkuParam createSkuParam) {

		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		/**
		 * 产品主键必须存在,产品必须是编辑状态
		 */
		Integer productId = createSkuParam.getProductId();
		ProductDomain productDomain = productService.loadProduct(productId);

		if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING) {
			throw new ProductStatusIsNotAcceptableRuntimeException();
		}

		/**
		 * 商品所属商铺号，必须和当前用户的商铺号 一致
		 */
		if (!productDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
			throw new UnMatchedStoreOperatorException();
		}

		String skuAttrNameIdsStr = productDomain.getProductSkuAttrNameIds();
		/**
		 * 产品定义用到的Sku属性
		 */
		Map<Integer, Integer> designedSkuAttrNameIdMap = new HashMap<Integer, Integer>();
		if (StringTools.isNotNullOrEmpty(skuAttrNameIdsStr)) {
			String[] strs = skuAttrNameIdsStr.split(ProductService.ATTR_NAME_ID_SPLITTER);
			for (String str : strs) {
				Integer attrNameId = Integer.parseInt(str);
				designedSkuAttrNameIdMap.put(attrNameId, attrNameId);
			}
		}

		Date now = new Date();
		String userCode = AuthThreadLocal.getUserCode();
		SkuDomain skuDomain = new SkuDomain();

		/**
		 * TODO:SKU属性组合，一个属性都不能少，SKU属性组合不能重复出现 设置SkU的属性名Id集合和属性值Id集合 设置SKU的属性值集合
		 */
		List<ProductAttrDomain> productAttrDomainList = new ArrayList<ProductAttrDomain>(); // 要添加的属性记录
		List<Integer> attrValueIds = createSkuParam.getSkuAttrValueIds();
		if (CollectionTools.isNotNullOrEmpty(attrValueIds)) {
			/* 记录实际值 */
			List<String> attrValues = new ArrayList<String>();
			/* 必须先排序 */
			Collections.sort(attrValueIds);
			for (Integer attrValueId : attrValueIds) {
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(attrValueId);
				Integer attrNameId = attrValueDomain.getAttrNameId();
				if (designedSkuAttrNameIdMap.containsKey(attrNameId)) {
					designedSkuAttrNameIdMap.remove(attrNameId);
					attrValues.add(attrValueDomain.getAttrDisplayValue() == null ? attrValueDomain.getAttrValue() : attrValueDomain.getAttrDisplayValue());

					ProductAttrDomain productAttrDomain = new ProductAttrDomain();
					productAttrDomain.setAttrNameId(attrNameId);
					productAttrDomain.setAttrValueId(attrValueId);
					productAttrDomainList.add(productAttrDomain);

				} else {
					throw new SkuAttrNameIsNotDesignedByProductRuntimeException();
				}
			}
			if (designedSkuAttrNameIdMap.size() > 0) {
				/* Sku属性不全 */
				throw new LackOfSkuAttrNameOfProductRuntimeException();
			}

			/**
			 * 合并
			 */
			skuDomain.setSkuAttrValueIds(StringTools.concatWithSymbol(ProductService.ATTR_NAME_ID_SPLITTER, attrValueIds));
			skuDomain.setSkuAttrValueValues(StringTools.concatWithSymbol(ProductService.ATTR_DISPLAY_VALUE_SPLITTER, attrValues));
		}

		if (ConstBoolean.parse(productDomain.getIsVirtual())) {
			virtualSku(skuDomain);
		} else {
			skuDomain.setDepth(createSkuParam.getDepth());
			skuDomain.setHeight(createSkuParam.getHeight());
			skuDomain.setWidth(createSkuParam.getWidth());
			skuDomain.setLengthUnit(createSkuParam.getLengthUnit());

			skuDomain.setWeight(createSkuParam.getWeight());
			skuDomain.setWeightUnit(createSkuParam.getWeightUnit());
		}

		skuDomain.setIsSpecialPrice(ConstBoolean.FALSE);

		skuDomain.setProductId(createSkuParam.getProductId());

		skuDomain.setSkuCode(createSkuParam.getSkuCode());
		skuDomain.setSkuMemo(createSkuParam.getSkuMemo());
		skuDomain.setSkuName(createSkuParam.getSkuName());
		
		skuDomain.setMarketPrice(createSkuParam.getMarketPrice());
		skuDomain.setSalePrice(createSkuParam.getSalePrice());
		/**
		 * TODO:修改币种 ,写死为RMB
		 */
		skuDomain.setCurrency(ConstProduct.Currency.RMB);
		skuDomain.setDeliveryType(productDomain.getDeliveryType());
		
		/**
		 * 新创建的产品SKU为编辑状态
		 */
		skuDomain.setSkuStatus(ConstProduct.SkuStatus.EDITING);
		/**
		 * 新创建的产品SKU库存数量为0
		 */
		skuDomain.setSkuStockQuantity(0);
		skuDomain.setStoreCode(productDomain.getStoreCode());
		skuDomain.setUpdateDate(now);
		skuDomain.setUpdateUserCode(userCode);
		skuDomain.setCreateDate(now);
		skuDomain.setCreateUserCode(userCode);

		/**
		 * 设置主键和版本号
		 */
		skuDomain.setSkuVersion(0);
		skuDomain.setProductVersion(productDomain.getProductVersion());
		skuDomain.setId(pkGenService.genPk());

		try {
			skuMapper.insert(skuDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateSkuRuntimeException();
		}

		/**
		 * 插入SKU属性
		 */
		insertSkuAttr(productAttrDomainList, now, userCode, productId, skuDomain.getId());

		return skuDomain;

	}

	private void insertSkuAttr(List<ProductAttrDomain> productAttrDomainList, Date now, String userCode, Integer productId, Integer skuId) {
		for (ProductAttrDomain productAttrDomain : productAttrDomainList) {
			productAttrDomain.setAttrNameType(ConstAttr.AttrNameType.SKU_ATTR);
			productAttrDomain.setCreateDate(now);
			productAttrDomain.setCreateUserCode(userCode);
			productAttrDomain.setProductId(productId);
			productAttrDomain.setSkuId(skuId);
			productAttrDomain.setUpdateDate(now);
			productAttrDomain.setUpdateUserCode(userCode);
			productAttrDomain.setId(pkGenService.genPk());
			productAttrMapper.insert(productAttrDomain);
		}
	}

	/**
	 * 
	 * @param updateSkuParam
	 * @throws Exception 
	 */
	@Valid
	@Transactional
	public void updateSku(@Valid @NotNull("参数") UpdateSkuParam updateSkuParam) throws Exception {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		Integer skuId = updateSkuParam.getId();
		SkuDomain skuDomain = loadSkuById(skuId);
		/**
		 * 产品Sku状态必须是编辑状态
		 */
		if (skuDomain.getSkuStatus() != ConstProduct.SkuStatus.EDITING) {
			throw new SkuStatusIsNotAcceptableRuntimeException();
		}

		/**
		 * 产品主键必须存在,产品必须是编辑状态
		 */
		Integer productId = skuDomain.getProductId();
		ProductDomain productDomain = productService.loadProduct(productId);

		if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING) {
			throw new ProductStatusIsNotAcceptableRuntimeException();
		}

		/**
		 * sku定义用到的Sku属性
		 */
		String skuAttrNameIdsStr = productDomain.getProductSkuAttrNameIds();
		Map<Integer, Integer> designedSkuAttrNameIdMap = new HashMap<Integer, Integer>();
		if (StringTools.isNotNullOrEmpty(skuAttrNameIdsStr)) {
			String[] strs = skuAttrNameIdsStr.split(ProductService.ATTR_NAME_ID_SPLITTER);
			for (String str : strs) {
				Integer attrNameId = Integer.parseInt(str);
				designedSkuAttrNameIdMap.put(attrNameId, attrNameId);
			}
		}

		/**
		 * 加载所有SKU属性
		 */
		MybatisParam params = new MybatisParam().put("productId", productDomain.getId()).put("skuId", skuId);
		List<ProductAttrDomain> productAttrDomainList = productAttrMapper.loadDynamic(params);
		Map<Integer, Integer> usedSkuAttrValueIdMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> usedSkuAttrNameIdMap = new HashMap<Integer, Integer>();
		productAttrDomainList.forEach(e -> {
			usedSkuAttrValueIdMap.put(e.getAttrValueId(), e.getAttrValueId());
			usedSkuAttrNameIdMap.put(e.getAttrNameId(), e.getAttrNameId());
		});

		/**
		 * 删除sku属性
		 */
		List<Integer> removeSkuAttrValueIds = updateSkuParam.getRemoveSkuAttrValueIds();
		if (CollectionTools.isNotNullOrEmpty(removeSkuAttrValueIds)) {
			for (Integer removeAttrValueId : removeSkuAttrValueIds) {

				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(removeAttrValueId);
				Integer attrNameId = attrValueDomain.getAttrNameId();
				/**
				 * 要删除的SKU属性值ID，必须已经被引用
				 */
				if (!usedSkuAttrNameIdMap.containsKey(attrNameId)) {
					throw new AttrNameIsNotUsedByProductRuntimeException();
				}

				params = new MybatisParam().put("productId", productDomain.getId()).put("attrNameId", attrNameId).put("attrValueId", removeAttrValueId);
				ProductAttrDomain productAttrDomain = MybatisTools.single(productAttrMapper.loadDynamic(params));
				productAttrMapper.delete(productAttrDomain.getId());
				usedSkuAttrNameIdMap.remove(attrNameId);
				usedSkuAttrValueIdMap.remove(removeAttrValueId);
			}

		}

		/**
		 * 新增SkU属性
		 */
		List<ProductAttrDomain> addProductAttrDomainList = new ArrayList<ProductAttrDomain>();
		List<Integer> addSkuAttrValueIds = updateSkuParam.getAddSkuAttrValueIds();
		if (CollectionTools.isNotNullOrEmpty(addSkuAttrValueIds)) {
			for (Integer addAttrValueId : addSkuAttrValueIds) {
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(addAttrValueId);
				Integer attrNameId = attrValueDomain.getAttrNameId();
				if (!productDomain.getCategoryId().equals(attrValueDomain.getCategoryId())) {
					throw new CategoryIsDifferentRuntimeException();
				}

				/**
				 * 要新增的关键属性值对应的属性名ID，必须未被引用
				 */
				if (usedSkuAttrNameIdMap.containsKey(attrNameId)) {
					throw new AttrNameIsUsedByProductRuntimeException();
				}

				/**
				 * 维护map
				 */
				usedSkuAttrNameIdMap.put(attrNameId, attrNameId);
				usedSkuAttrValueIdMap.put(addAttrValueId, addAttrValueId);

				/**
				 * 创建插入值
				 */
				ProductAttrDomain productAttrDomain = new ProductAttrDomain();
				productAttrDomain.setAttrNameId(attrNameId);
				productAttrDomain.setAttrValueId(addAttrValueId);
				addProductAttrDomainList.add(productAttrDomain);
			}
		}

		/**
		 * 校验最终剩余的sku属性集合必须和产品定义里的属性集合一致 designedSkuAttrNameIdMap
		 * usedSkuAttrNameIdMap 必须一致
		 */
		usedSkuAttrNameIdMap.keySet().forEach(e -> {
			if (!designedSkuAttrNameIdMap.containsKey(e)) {
				throw new SkuAttrNameIsNotDesignedByProductRuntimeException();
			}
		});

		if (designedSkuAttrNameIdMap.size() != usedSkuAttrNameIdMap.size()) {
			throw new LackOfSkuAttrNameOfProductRuntimeException();
		}

		Date now = new Date();
		String userCode = AuthThreadLocal.getUserCode();
		SkuDomain updateDomain = new SkuDomain();

		/**
		 * 合并
		 */
		if (usedSkuAttrNameIdMap.size() == 0) {
			updateDomain.setSkuAttrValueIds("");
			updateDomain.setSkuAttrValueValues("");
		} else {
			List<Integer> skuAttrValueIds = new ArrayList<Integer>();
			usedSkuAttrValueIdMap.keySet().forEach(e -> {
				skuAttrValueIds.add(e);
			});
			Collections.sort(skuAttrValueIds);
			List<String> skuAttrValues = new ArrayList<String>();
			skuAttrValueIds.forEach(e -> {
				AttrValueDomain attrValueDomain = attrValueService.loadAttrValue(e);
				skuAttrValues.add(attrValueDomain.getAttrDisplayValue() == null ? attrValueDomain.getAttrValue() : attrValueDomain.getAttrDisplayValue());
			});

			updateDomain.setSkuAttrValueIds(StringTools.concatWithSymbol(ProductService.ATTR_NAME_ID_SPLITTER, skuAttrValueIds));
			updateDomain.setSkuAttrValueValues(StringTools.concatWithSymbol(ProductService.ATTR_DISPLAY_VALUE_SPLITTER, skuAttrValues));
		}

		updateDomain.setUpdateDate(now);
		updateDomain.setUpdateUserCode(userCode);

		DomainTools.copy(updateSkuParam, updateDomain);
		/**
		 * 写死币种
		 */
		updateDomain.setCurrency(ConstProduct.Currency.RMB);

		if (ConstBoolean.parse(productDomain.getIsVirtual())) {
			virtualSku(updateDomain);
		}
		
		/**
		 * 是否变更版本号
		 */
		if(needChangeSkuVersion(skuDomain, updateDomain)){
			updateDomain.setSkuVersion(skuDomain.getSkuVersion() +1);
			/**
			 * 保留历史
			 */
			SkuHistoryDomain skuHistoryDomain = DomainTools.copy(skuDomain, SkuHistoryDomain.class);
			skuHistoryMapper.insert(skuHistoryDomain);
		}
		
		updateDomain.setProductVersion(productDomain.getProductVersion());

		try {
			skuMapper.updateWithoutNull(updateDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateSkuRuntimeException();
		}

		/**
		 * 插入Sku属性
		 */
		insertSkuAttr(productAttrDomainList, now, userCode, productId, skuDomain.getId());

	}
	
	private boolean needChangeSkuVersion(SkuDomain pre ,SkuDomain next){
		if (next.getSkuName() != null && !next.getSkuName().equals(pre.getSkuName())){
			return true;
		}
		
		if (next.getSkuCode() != null && !next.getSkuName().equals(pre.getSkuCode())){
			return true;
		}
		
		if (next.getSkuAttrValueValues() != null && !next.getSkuName().equals(pre.getSkuAttrValueValues())){
			return true;
		}
		
		return false;
	}

	private void virtualSku(SkuDomain skuDomain) {
		skuDomain.setDepth(0);
		skuDomain.setHeight(0);
		skuDomain.setWidth(0);
		skuDomain.setLengthUnit(ConstProduct.LengthUnit.MILLIMETER);

		skuDomain.setWeight(0);
		skuDomain.setWeightUnit(ConstProduct.WeightUnit.GRAM);
	}

	/**
	 * 只能修改用户自己商店的产品SKU状态
	 * 
	 * @param updateSkuStatusParam
	 */
	@Valid
	@Transactional
	public void updateSkuStatus(@Valid @NotNull("参数") UpdateSkuStatusParam updateSkuStatusParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		int status = updateSkuStatusParam.getSkuStatus();
		Date now = new Date();
		String userCode = AuthThreadLocal.getUserCode();
		for (Integer id : updateSkuStatusParam.getIds()) {
			SkuDomain skuDomain = loadSkuById(id);
			ProductDomain productDomain = productService.loadProduct(skuDomain.getProductId());

			if (status != skuDomain.getSkuStatus()) {
				if (!skuDomain.getStoreCode().equals(AuthThreadLocal.getUserCode())) {
					throw new UnMatchedStoreOperatorException();
				}
				SkuDomain updateDomain = new SkuDomain();

				if (status == ConstProduct.SkuStatus.ON_SALE) {
					/**
					 * SKU上架时，销售价格要存在且>=0
					 */
					if (skuDomain.getSalePrice() == null || skuDomain.getSalePrice().doubleValue() <= 0) {
						throw new SkuIsNotAcceptableForSaleRuntimeException();
					}
					
					/**
					 * SkU上架时，sku属性值对应的属性名Id集合要和产品定义的属性名Id集合完全一致
					 */
					if (!compareSkuAttrOfProductAndSKu(productDomain,skuDomain)){
						throw new SkuAttrNameIdsIsNotSameWithProductRuntimeException();
					}

					/**
					 * 记录快照
					 */
					try {
						SkuSnapshootDomain skuSnapshootDomain = buildSnapshoot(skuDomain,productDomain,now,userCode);
						skuSnapshootMapper.insert(skuSnapshootDomain);
						updateDomain.setSkuSnapshootId(skuSnapshootDomain.getId());
					} catch (DuplicateKeyException e) {
						logger.error("最新快照和当前版本内容一致，无需建立快照");
					}
					
				}
				/**
				 * 产品版本号保持一致
				 */
				updateDomain.setProductVersion(productDomain.getProductVersion());
				updateDomain.setSkuStatus(status);
				updateDomain.setUpdateDate(now);
				updateDomain.setUpdateUserCode(userCode);
				skuMapper.update(updateDomain);
			}
		}
	}
	
	/**
	 * 比较产品定义的sku属性集合和实际的sku属性集合是否一致
	 * @param productDomain
	 * @param skuDomain
	 * @return
	 */
	private boolean compareSkuAttrOfProductAndSKu(ProductDomain productDomain , SkuDomain skuDomain){
		String skuAttrValueIdsStr = skuDomain.getSkuAttrValueIds();
		String productSkuAttrNameIds = productDomain.getProductSkuAttrNameIds();					
		if (StringTools.isNotNullOrEmpty(skuAttrValueIdsStr) && StringTools.isNullOrEmpty(productSkuAttrNameIds) ){
			return false;
		}
		if (StringTools.isNullOrEmpty(skuAttrValueIdsStr) && StringTools.isNotNullOrEmpty(productSkuAttrNameIds) ){
			return false;
		}
		
		String[] strs = skuAttrValueIdsStr.split(ProductService.ATTR_NAME_ID_SPLITTER);
		List<Integer> skuAttrNameIdList = new ArrayList<Integer>();
		for (String str : strs){
			Integer attrValueId  = Integer.parseInt(str);
			AttrValueDomain  attrValueDomain = attrValueService.loadAttrValue(attrValueId);
			skuAttrNameIdList.add(attrValueDomain.getAttrNameId());					
		}
		
		Collections.sort(skuAttrNameIdList);
		
		if (StringTools.concatWithSymbol(ProductService.ATTR_NAME_ID_SPLITTER, skuAttrNameIdList).equals(productSkuAttrNameIds)){
			return true;
		}
		
		return false;
	}
	
	private SkuSnapshootDomain buildSnapshoot(SkuDomain skuDomain ,ProductDomain productDomain,Date now ,String userCode){
		
		SkuSnapshootDomain skuSnapShootDomain = new SkuSnapshootDomain();
		skuSnapShootDomain.setCreateDate(now);
		skuSnapShootDomain.setCreateUserCode(userCode);
		skuSnapShootDomain.setId(pkGenService.genPk());
		skuSnapShootDomain.setMarketPrice(skuDomain.getMarketPrice());
		skuSnapShootDomain.setProductCode(productDomain.getProductCode());
		skuSnapShootDomain.setProductId(skuDomain.getProductId());
		skuSnapShootDomain.setProductKeyAttrValueIds(productDomain.getProductKeyAttrValueIds());
		skuSnapShootDomain.setProductKeyAttrValueValues(productDomain.getProductKeyAttrValueValues());
		skuSnapShootDomain.setProductMemo(productDomain.getProductMemo());
		skuSnapShootDomain.setProductName(productDomain.getProductName());
		skuSnapShootDomain.setProductSkuAttrNameIds(productDomain.getProductSkuAttrNameIds());
		skuSnapShootDomain.setSalePrice(skuDomain.getSalePrice());
		skuSnapShootDomain.setSkuAttrValueIds(skuDomain.getSkuAttrValueIds());
		skuSnapShootDomain.setSkuAttrValueValues(skuDomain.getSkuAttrValueValues());
		skuSnapShootDomain.setSkuCode(skuDomain.getSkuCode());
		skuSnapShootDomain.setSkuId(skuDomain.getId());
		skuSnapShootDomain.setSkuMemo(skuDomain.getSkuMemo());
		skuSnapShootDomain.setSkuName(skuDomain.getSkuName());
		skuSnapShootDomain.setStoreCode(productDomain.getStoreCode());
		skuSnapShootDomain.setUpdateUserCode(userCode);
		skuSnapShootDomain.setUpdateDate(now);
		/**
		 * 产品版本号必须取 产品对象的数据，因为sku的产品版本更新有延迟
		 */
		skuSnapShootDomain.setProductVersion(productDomain.getProductVersion());
		skuSnapShootDomain.setSkuVersion(skuDomain.getSkuVersion());
		return skuSnapShootDomain;
	}

	public List<SkuDomain> querySkuOfProduct(Integer productId, Integer skuStatus) {
		if (productId == null) {
			logger.error("querySkuOfProduct:productId is null!");
			return new ArrayList<SkuDomain>();
		}

		MybatisParam params = new MybatisParam().put("productId", productId).put("skuStatus", skuStatus);

		return skuMapper.loadDynamic(params);
	}
	

	@Valid
	public List<SkuDomain> listSku(@Valid @NotNull("参数") ListSkuParam listSkuParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		if (StringTools.isNullOrEmpty(listSkuParam.getOrder())) {
			listSkuParam.setOrder("create_date desc");
		}

		MybatisParam params = MybatisTools.page(new MybatisParam().put("storeCode", AuthThreadLocal.getStoreCode()), listSkuParam);
		
		params.put("isSpecialPrice", listSkuParam.getIsSpecialPrice());
		params.put("productId",listSkuParam.getProductId());
		params.put("skuStatus", listSkuParam.getSkuStatus());
		
		return skuMapper.loadDynamicPaging(params);
	}

	@Valid
	public SkuDomain loadSku(@Valid @NotNull("参数") LoadSkuParam loadSkuParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		SkuDomain skuDomain = loadSkuById(loadSkuParam.getId());

		if (!skuDomain.getStoreCode().equals(AuthThreadLocal.getUserCode())) {
			throw new UnMatchedStoreOperatorException();
		}

		return skuDomain;

	}

	/**
	 * 处于编辑状态的SKU才能删除 处于编辑状态的产品的产品SKU才能删除 不是当前用户所属商店的不能删 处于下级状态的SKU如何处理
	 * 已经有销售记录的SKU不能删除，或者保留快照 已经有库存记录的SKU如何处理
	 * 
	 * @param removeSkuParam
	 * @throws Exception 
	 */
	@Valid
	@Transactional
	public void removeSku(@Valid @NotNull("参数") RemoveSkuParam removeSkuParam) throws Exception {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		
		Date now = new Date();
		String userCode = AuthThreadLocal.getUserCode();

		for (Integer skuId : removeSkuParam.getIds()) {
			
			SkuDomain skuDomain = loadSkuById(skuId);
			Integer productId = skuDomain.getProductId();
			if (skuDomain.getSkuStatus() == ConstProduct.SkuStatus.ON_SALE) {
				throw new SkuStatusIsNotAcceptableRuntimeException();
			}
			
		
			if (skuDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
				throw new UnMatchedStoreOperatorException();
			}

			ProductDomain productDomain = productService.loadProduct(productId);
			if (productDomain.getProductStatus() != ConstProduct.ProductStatus.ON_SALE) {
				throw new ProductStatusIsNotAcceptableRuntimeException();
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
			MybatisParam params = new MybatisParam().put("productId", productId).put("skuId",skuId);
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
			SkuHistoryDomain skuHistoryDomain = DomainTools.copy(skuDomain, SkuHistoryDomain.class);
			skuHistoryDomain.setDeleteDate(now);
			skuHistoryDomain.setDeleteUserCode(userCode);
			skuHistoryMapper.insert(skuHistoryDomain);
			skuMapper.delete(skuDomain.getId());
			
		}
	}

}
