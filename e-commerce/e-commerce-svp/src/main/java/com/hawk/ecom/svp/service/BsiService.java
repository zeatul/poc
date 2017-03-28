package com.hawk.ecom.svp.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.BsiOrderDomain;
import com.hawk.ecom.svp.persist.domain.BsiProductDomain;
import com.hawk.ecom.svp.request.ActivateCouponParam;
import com.hawk.ecom.svp.request.QueryProductParam;

@Service
public class BsiService {
	
	@Autowired
	private BsiPhoneModelService bsiPhoneModelService;
	
	@Autowired
	private BsiPhoneProdcutMapService bsiPhoneProdcutMapService;
	
	@Autowired
	private BsiProductService bsiProductService;
	
	
	/**
	 * 查询产品信息
	 * @param queryProductParam
	 * @return
	 */
	public BsiProductDomain queryProduct(QueryProductParam queryProductParam){
		
		long phoneModelId = bsiPhoneModelService.queryPhoneModelId(queryProductParam.getBrand(), queryProductParam.getModel());
		
		long productId = bsiPhoneProdcutMapService.queryProductId(phoneModelId, queryProductParam.getPeriod());
		
		return bsiProductService.queryProduct(productId);
	}
	
	/**
	 * 激活代金券
	 * @param activateCouponParam
	 */
	public void activateCoupon(ActivateCouponParam activateCouponParam){
		
		BsiOrderDomain bsiOrderDomain = new BsiOrderDomain();
		bsiOrderDomain.setOrderCode(UUID.randomUUID().toString());
		bsiOrderDomain.setMobileNumber(activateCouponParam.getTicket());
		bsiOrderDomain.setBsiOrderStatus("0");
		bsiOrderDomain.setStoreCode("1");
		bsiOrderDomain.setUserCode(activateCouponParam.getTicket());
		bsiOrderDomain.setCreateDate(new Date());
		bsiOrderDomain.setUpdateDate(bsiOrderDomain.getCreateDate());
		
		bsiOrderDomain.setId(0L);
	}
}
