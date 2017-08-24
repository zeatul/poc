package com.hawk.ecom.query.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.base.persist.domainex.BsiProductExDomain;
import com.hawk.ecom.base.persist.mapperex.BsiExMapper;
import com.hawk.ecom.pub.constant.ConstAttrNameCode;
import com.hawk.ecom.query.exception.AttrNameNotFoundRuntimeException;
import com.hawk.ecom.query.exception.AttrValueNotFoundRuntimeException;
import com.hawk.ecom.query.persist.domainex.ProductCategoryExDomain;
import com.hawk.ecom.query.persist.domainex.ProductSkuExDomain;
import com.hawk.ecom.query.persist.mapperex.ProductExMapper;
import com.hawk.ecom.query.request.ListSkuParam;
import com.hawk.ecom.query.request.LoadBsiProductParam;
import com.hawk.ecom.query.request.LoadChargeDataProductParam;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;

@Service
public class ProductQueryService {

	@Autowired
	private ProductExMapper productExMapper;
	
	@Autowired
	private BsiExMapper bsiExMapper;

	public List<ProductCategoryExDomain> listCategory() {
		return productExMapper.listCategory();
	}

	@Valid
	public PagingQueryResultWrap<ProductSkuExDomain> querySku(@NotNull("参数") @Valid ListSkuParam listSkuParam) {
		MybatisParam params = MybatisTools.page(new MybatisParam(), listSkuParam);
		params.put("productStatus", 100);
		params.put("skuStatus", 100);
		params.put("categoryId", listSkuParam.getCategoryId());

		PagingQueryResultWrap<ProductSkuExDomain> wrap = new PagingQueryResultWrap<ProductSkuExDomain>();
		wrap.setDbCount(productExMapper.countSku(params));

		if (wrap.getDbCount() > 0) {
			wrap.setRecords(productExMapper.querySku(params));
		}

		return wrap;
	}

	public ProductSkuExDomain loadSku(int skuId) {
		MybatisParam params = new MybatisParam();
		params.put("productStatus", 100);
		params.put("skuStatus", 100);
		params.put("skuId", skuId);
		return productExMapper.loadSku(params);
	}

	public ProductSkuExDomain loadSkuPriceAndQuantity(int skuId) {
		MybatisParam params = new MybatisParam();
		params.put("productStatus", 100);
		params.put("skuStatus", 100);
		params.put("skuId", skuId);
		return productExMapper.loadSkuPriceAndQuantity(params);
	}

	private Integer findAttrValueId(String attrNameCode, String attrValue) {
		Integer attrValueId = productExMapper.findAttrValueId(attrNameCode, attrValue);
		if (attrValueId == null)
			throw new AttrValueNotFoundRuntimeException();
		return attrValueId;
	}

	private Integer findAttrNameId(String attrNameCode) {
		Integer attrNameId = productExMapper.findAttrNameId(attrNameCode);
		if (attrNameId == null)
			throw new AttrNameNotFoundRuntimeException();
		return attrNameId;
	}

	@Valid
	public List<ProductSkuExDomain> loadChargeDataProduct(@NotNull("参数") @Valid LoadChargeDataProductParam loadChargeDataProductParam) {

		List<Integer> attrValueIdList = new ArrayList<Integer>();
		attrValueIdList.add(findAttrValueId(ConstAttrNameCode.Mobile.OPERATOR, loadChargeDataProductParam.getOperator()));
		attrValueIdList.add(findAttrValueId(ConstAttrNameCode.Mobile.PROVINCE, loadChargeDataProductParam.getProvince()));
		
		if (!loadChargeDataProductParam.getRegionType().equals("all")){
			attrValueIdList.add(findAttrValueId(ConstAttrNameCode.Mobile.REGION_TYPE, loadChargeDataProductParam.getRegionType()));
		}

		Integer dataSizeAttrNameId = findAttrNameId(ConstAttrNameCode.Mobile.DATA_SIZE);
		Integer regionTypeAttrNameId = findAttrNameId(ConstAttrNameCode.Mobile.REGION_TYPE);

		List<ProductSkuExDomain> productSkuExDomainList = productExMapper.loadChargeDataProduct(attrValueIdList, attrValueIdList.size(), dataSizeAttrNameId,regionTypeAttrNameId,100,100);

		return productSkuExDomainList;

	}
	
	@Valid
	public List<ProductSkuExDomain> loadBsiProduct(@NotNull("参数") @Valid LoadBsiProductParam loadBsiProductParam){
		List<BsiProductExDomain> bsiProductExDomainList = bsiExMapper.queryProductByPhoneModelId(loadBsiProductParam.getBsiPhoneModelId());
	
		List<ProductSkuExDomain> result = new ArrayList<ProductSkuExDomain>();
		
		for (BsiProductExDomain bsiProductExDomain : bsiProductExDomainList){
			List<Integer> attrValueIdList = new ArrayList<Integer>();
			attrValueIdList.add(findAttrValueId(ConstAttrNameCode.Bsi.GRADE.toString(), bsiProductExDomain.getBsiGrade().toString()));
			attrValueIdList.add(findAttrValueId(ConstAttrNameCode.Bsi.INSURANCE_PERIOD_MONTH.toString(), bsiProductExDomain.getBsiInsurancePeriodMonth().toString()));
			List<ProductSkuExDomain> productSkuExDomainList = productExMapper.findSkuByAttrValueIds(attrValueIdList, attrValueIdList.size(),100,100);
			
			for ( ProductSkuExDomain productSkuExDomain : productSkuExDomainList){
				productSkuExDomain.setBsiGrade(bsiProductExDomain.getBsiGrade());
				productSkuExDomain.setBsiInsurancePeriodMonth(bsiProductExDomain.getBsiInsurancePeriodMonth());
				result.add(productSkuExDomain);
				
			}
		}
		
		return result;
		
		
	}

}
