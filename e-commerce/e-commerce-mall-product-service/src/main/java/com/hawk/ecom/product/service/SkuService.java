package com.hawk.ecom.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.exception.DuplicateSkuRuntimeException;
import com.hawk.ecom.product.exception.ProductStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.persist.mapper.SkuMapper;
import com.hawk.ecom.product.request.CreateSkuParam;
import com.hawk.framework.pub.pk.PkGenService;

@Service
public class SkuService {
	
	@Autowired
	@Qualifier("smallNumberSequenceService")
	private PkGenService pkGenService;
	
	@Autowired
	private ProductService produtService;
	
	@Autowired
	private SkuMapper skuMapper;
	
	/**
	 * 产品主键必须存在,产品必须是编辑状态
	 * 非虚拟产品的尺寸重量不能为空
	 * SKU属性组合，一个属性都不能少，SKU属性组合不能重复出现
	 * @param createSkuParam
	 * @return
	 */
	public SkuDomain createSku(CreateSkuParam createSkuParam ){
		
		/**
		 * 产品主键必须存在,产品必须是编辑状态
		 */
		ProductDomain productDomain = produtService.loadProduct(createSkuParam.getProductId());
			
		if (productDomain.getProductStatus() != ConstProduct.ProductStatus.EDITING){
			throw new ProductStatusIsNotAcceptableRuntimeException();
		}
		
		/**
		 * TODO:非虚拟产品的 尺寸 重量 ,单位不能为空
		 */
		
		/**
		 * TODO:SKU属性组合，一个属性都不能少，SKU属性组合不能重复出现
		 */
		
		SkuDomain skuDomain = new SkuDomain();
//		skuDomain.setCreateDate(createDate);
//		skuDomain.setCreateUserCode(createUserCode);
//		skuDomain.setDepth(depth);
//		skuDomain.setHeigh(heigh);
//		
//		skuDomain.setIsSpecial(isSpecial);
//		skuDomain.setLengthUnit(lengthUnit);
//		skuDomain.setMarketPrice(marketPrice);
//		skuDomain.setProductId(productId);
//		skuDomain.setSalePrice(salePrice);
//		
//		skuDomain.setSkuCode(skuCode);
//		skuDomain.setSkuMemo(skuMemo);
//		skuDomain.setSkuName(skuName);
//		skuDomain.setSkuStatus(skuStatus);
//		skuDomain.setStoreCode(storeCode);
//		skuDomain.setUpdateDate(updateDate);
//		skuDomain.setUpdateUserCode(updateUserCode);
//		skuDomain.setWeight(weight);
//		skuDomain.setWeightUnit(weightUnit);
//		skuDomain.setWidth(width);
		
		
		
		/**
		 * 设置SkU的属性名Id集合和属性值Id集合 
		 * 设置SKU的属性值集合
		 */
		
//		skuDomain.setSkAttrIdComp(skAttrIdComp);
//		skuDomain.setSkuAttrValueComp(skuAttrValueComp);
		
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

}
