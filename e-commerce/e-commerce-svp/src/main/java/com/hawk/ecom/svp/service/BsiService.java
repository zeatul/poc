package com.hawk.ecom.svp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.constant.ConstCouponParameter;
import com.hawk.ecom.svp.persist.domain.BsiCashCouponDomain;
import com.hawk.ecom.svp.persist.domain.BsiOrderDomain;
import com.hawk.ecom.svp.persist.domain.BsiProductDomain;
import com.hawk.ecom.svp.persist.mapper.BsiCashCouponMapper;
import com.hawk.ecom.svp.request.ActivateCouponParam;
import com.hawk.ecom.svp.request.QueryProductParam;
import com.hawk.ecom.svp.request.RegisterForCouponParam;
import com.hawk.framework.utility.tools.DateTools;

@Service
public class BsiService {
	
	@Autowired
	private BsiPhoneModelService bsiPhoneModelService;
	
	@Autowired
	private BsiPhoneProdcutMapService bsiPhoneProdcutMapService;
	
	@Autowired
	private BsiProductService bsiProductService;
	
	@Autowired
	private BsiCashCouponMapper bsiCashCouponMapper;
	
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
	 * 注册赠送代金券只有一次机会
	 * @param registerForCouponParam
	 */
	public void rgeisterForCoupon(RegisterForCouponParam registerForCouponParam){
		String mobileNumber = registerForCouponParam.getMobileNumber();
		String bsiCashCouponType = ConstCouponParameter.REGISTER_PRESENT_COUPON.type;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("mobileNumber",mobileNumber);
		params.put("bsiCashCouponType",bsiCashCouponType);
		List<BsiCashCouponDomain> list = bsiCashCouponMapper.loadDynamic(params);
		if (list.size() > 0)
			throw new RuntimeException("用户已经赠送过代金券");
		
		Date currentDate = new Date();
		BsiCashCouponDomain bsiCashCouponDomain = new BsiCashCouponDomain();
		bsiCashCouponDomain.setBsiCashCouponCode(UUID.randomUUID().toString());
		bsiCashCouponDomain.setBsiCashCouponCreateDate(currentDate);
		Date bsiCashCouponInvalidDate = DateTools.addMonth(currentDate, 1);
		bsiCashCouponDomain.setBsiCashCouponInvalidDate(bsiCashCouponInvalidDate);
		bsiCashCouponDomain.setBsiCashCouponName(ConstCouponParameter.REGISTER_PRESENT_COUPON.name);
		bsiCashCouponDomain.setBsiCashCouponPeriod(ConstCouponParameter.REGISTER_PRESENT_COUPON.period);
		bsiCashCouponDomain.setBsiCashCouponStatus(ConstCouponParameter.CouopnStatus.UNUSED);
		bsiCashCouponDomain.setBsiCashCouponType(bsiCashCouponType);
		bsiCashCouponDomain.setMobileNumber(mobileNumber);
		bsiCashCouponDomain.setOrderCode(null);
		bsiCashCouponDomain.setUserCode(mobileNumber);
		bsiCashCouponDomain.setCreateDate(currentDate);
		bsiCashCouponDomain.setUpdateDate(currentDate);
		bsiCashCouponMapper.insert(bsiCashCouponDomain);
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
