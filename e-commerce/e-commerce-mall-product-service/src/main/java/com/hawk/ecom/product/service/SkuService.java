package com.hawk.ecom.product.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.exception.DuplicateSkuRuntimeException;
import com.hawk.ecom.product.exception.ProductStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.SkuIsNotAcceptableForSaleRuntimeException;
import com.hawk.ecom.product.exception.SkuNotFoundRuntimeException;
import com.hawk.ecom.product.exception.SkuStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.exception.UnMatchedStoreOperatorException;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.persist.mapper.SkuMapper;
import com.hawk.ecom.product.request.CreateSkuParam;
import com.hawk.ecom.product.request.ListSkuOfProductParam;
import com.hawk.ecom.product.request.ListSkuParam;
import com.hawk.ecom.product.request.LoadSkuParam;
import com.hawk.ecom.product.request.RemoveSkuParam;
import com.hawk.ecom.product.request.UpdateSkuParam;
import com.hawk.ecom.product.request.UpdateSkuStatusParam;
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
public class SkuService {

	@Autowired
	@Qualifier("smallNumberSequenceService")
	private PkGenService pkGenService;

	@Autowired
	private MallAuthService authService;

	@Autowired
	private ProductService productService;

	@Autowired
	private SkuMapper skuMapper;

	public SkuDomain loadSkuById(Integer  id) {
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
	 * 产品主键必须存在,产品必须是编辑状态 非虚拟产品的尺寸重量不能为空 SKU属性组合，一个属性都不能少，SKU属性组合不能重复出现
	 * 新创建的产品SKU为编辑状态
	 * 
	 * @param createSkuParam
	 * @return
	 */
	@Valid
	@Transactional
	public SkuDomain createSku(@Valid @NotEmpty("参数") CreateSkuParam createSkuParam) {

		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		/**
		 * 产品主键必须存在,产品必须是编辑状态
		 */
		ProductDomain productDomain = productService.loadProduct(createSkuParam.getProductId());

		if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING) {
			throw new ProductStatusIsNotAcceptableRuntimeException();
		}

		/**
		 * TODO:商品所属商铺号，必须和当前用户的商铺号 一致
		 */
		if (!productDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
			throw new UnMatchedStoreOperatorException();
		}

		/**
		 * TODO:SKU属性组合，一个属性都不能少，SKU属性组合不能重复出现
		 */

		Date now = new Date();
		SkuDomain skuDomain = new SkuDomain();
		skuDomain.setCreateDate(now);
		skuDomain.setCreateUserCode(AuthThreadLocal.getUserCode());

		if (ConstBoolean.parse(productDomain.getIsVirtual())) {
			skuDomain.setDepth(0);
			skuDomain.setHeigh(0);
			skuDomain.setWidth(0);
			skuDomain.setLengthUnit(ConstProduct.LengthUnit.MILLIMETER);

			skuDomain.setWeight(0);
			skuDomain.setWeightUnit(ConstProduct.WeightUnit.GRAM);
		} else {
			skuDomain.setDepth(createSkuParam.getDepth());
			skuDomain.setHeigh(createSkuParam.getHeigh());
			skuDomain.setWidth(createSkuParam.getWidth());
			skuDomain.setLengthUnit(createSkuParam.getLengthUnit());

			skuDomain.setWeight(createSkuParam.getWeight());
			skuDomain.setWeightUnit(createSkuParam.getWeightUnit());
		}

		skuDomain.setIsSpecial(ConstBoolean.FALSE);

		skuDomain.setProductId(createSkuParam.getProductId());

		skuDomain.setSkuCode(createSkuParam.getSkuCode());
		skuDomain.setSkuMemo(createSkuParam.getSkuMemo());
		skuDomain.setSkuName(createSkuParam.getSkuName());
		/**
		 * 新创建的产品SKU为编辑状态
		 */
		skuDomain.setSkuStatus(ConstProduct.SkuStatus.EDITING);
		/**
		 * 新创建的产品SKU库存数量为0
		 */
		skuDomain.setSkuStockAmount(0);
		skuDomain.setStoreCode(productDomain.getStoreCode());
		skuDomain.setUpdateDate(now);
		skuDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());

		/**
		 * 设置SkU的属性名Id集合和属性值Id集合 设置SKU的属性值集合
		 */

		skuDomain.setSkuAttrIdComp(null);
		skuDomain.setSkuAttrValueComp(null);

		/**
		 * 设置主键
		 */
		skuDomain.setId(pkGenService.genPk());

		try {
			skuMapper.insert(skuDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateSkuRuntimeException();
		}

		return skuDomain;

	}

	/**
	 * 
	 * @param updateSkuParam
	 */
	@Valid
	@Transactional
	public void updateSku(@Valid @NotEmpty("参数") UpdateSkuParam updateSkuParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		SkuDomain skuDomain = loadSkuById(updateSkuParam.getId());
		/**
		 * 产品Sku状态必须是编辑状态
		 */
		if (skuDomain.getSkuStatus() != ConstProduct.SkuStatus.EDITING) {
			throw new SkuStatusIsNotAcceptableRuntimeException();
		}

		/**
		 * 产品主键必须存在,产品必须是编辑状态
		 */
		ProductDomain productDomain = productService.loadProduct(skuDomain.getProductId());

		if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING) {
			throw new ProductStatusIsNotAcceptableRuntimeException();
		}

		SkuDomain updateDomain = new SkuDomain();
		updateDomain.setUpdateDate(new Date());
		updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());

		DomainTools.copy(updateSkuParam, updateDomain);

		if (ConstBoolean.parse(productDomain.getIsVirtual())) {
			skuDomain.setDepth(0);
			skuDomain.setHeigh(0);
			skuDomain.setWidth(0);
			skuDomain.setLengthUnit(ConstProduct.LengthUnit.MILLIMETER);

			skuDomain.setWeight(0);
			skuDomain.setWeightUnit(ConstProduct.WeightUnit.GRAM);
		}

		try {
			skuMapper.updateWithoutNull(updateDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateSkuRuntimeException();
		}
	}

	/**
	 * 只能修改用户自己商店的产品SKU状态
	 * 
	 * @param updateSkuStatusParam
	 */
	@Valid
	@Transactional
	public void updateSkuStatus(@Valid @NotEmpty("参数") UpdateSkuStatusParam updateSkuStatusParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		int status = updateSkuStatusParam.getSkuStatus();
		for (Integer  id : updateSkuStatusParam.getIds()) {
			SkuDomain skuDomain = loadSkuById(id);

			if (status != skuDomain.getSkuStatus()) {
				if (!skuDomain.getStoreCode().equals(AuthThreadLocal.getUserCode())) {
					throw new UnMatchedStoreOperatorException();
				}
				
				if (status  == ConstProduct.SkuStatus.ON_SALE){
					/**
					 * SKU上架时，销售价格要存在且>0
					 */
					if (skuDomain.getSalePrice() == null || skuDomain.getSalePrice().doubleValue() <=0 ){
						throw new SkuIsNotAcceptableForSaleRuntimeException();
					}
					
					
				}
			}
		}
	}

	@Valid
	public List<SkuDomain> listSkuOfProduct(@Valid @NotEmpty("参数") ListSkuOfProductParam listSkuOfProductParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		Integer  productId = listSkuOfProductParam.getProductId();
		ProductDomain productDomain = productService.loadProduct(productId);

		if (!productDomain.getStoreCode().equals(AuthThreadLocal.getUserCode())) {
			throw new UnMatchedStoreOperatorException();
		}

		MybatisParam params = new MybatisParam().put("productId", productId);

		return skuMapper.loadDynamic(params);
	}

	@Valid
	public List<SkuDomain> listSku(@Valid @NotEmpty("参数") ListSkuParam listSkuParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		if (StringTools.isNullOrEmpty(listSkuParam.getOrder())) {
			listSkuParam.setOrder("create_date desc");
		}

		if (StringTools.isNullOrEmpty(listSkuParam.getOrder())){
			listSkuParam.setOrder("create_date desc");
		}
		
		MybatisParam params = MybatisTools.page(new MybatisParam().put("storeCode", AuthThreadLocal.getStoreCode()), listSkuParam);
		return skuMapper.loadDynamicPaging(params);
	}

	@Valid
	public SkuDomain loadSku(@Valid @NotEmpty("参数") LoadSkuParam loadSkuParam) {
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
	 */
	@Valid
	@Transactional
	public void removeSku(@Valid @NotEmpty("参数") RemoveSkuParam removeSkuParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		for (Integer  id : removeSkuParam.getIds()) {
			SkuDomain skuDomain = loadSkuById(id);
			if (skuDomain.getSkuStatus() != ConstProduct.SkuStatus.EDITING) {
				throw new SkuStatusIsNotAcceptableRuntimeException();
			}

			if (skuDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
				throw new UnMatchedStoreOperatorException();
			}

			ProductDomain productDomain = productService.loadProduct(skuDomain.getProductId());
			if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING) {
				throw new ProductStatusIsNotAcceptableRuntimeException();
			}

			/**
			 * TODO:检测是否有销售记录
			 * 
			 */

			/**
			 * TODO:有销售记录，做快照
			 */

			/**
			 * 通过检测
			 */
			/**
			 * TODO:删除SKU属性
			 */
			/**
			 * TODO:删除库存
			 */
			/**
			 * 删除SKU
			 */
			skuMapper.delete(id);
		}
	}

}
