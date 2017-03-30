package com.hawk.ecom.svp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.svp.constant.ConstCouponParameter;
import com.hawk.ecom.svp.constant.ConstOrderStatus;
import com.hawk.ecom.svp.constant.ConstOrderType;
import com.hawk.ecom.svp.constant.ConstStore;
import com.hawk.ecom.svp.persist.domain.BsiCashCouponDomain;
import com.hawk.ecom.svp.persist.domain.BsiOrderDetailDomain;
import com.hawk.ecom.svp.persist.domain.BsiProductDomain;
import com.hawk.ecom.svp.persist.domain.OrderDomain;
import com.hawk.ecom.svp.persist.mapper.BsiCashCouponMapper;
import com.hawk.ecom.svp.persist.mapper.BsiOrderDetailMapper;
import com.hawk.ecom.svp.persist.mapper.OrderMapper;
import com.hawk.ecom.svp.request.ActivateCouponParam;
import com.hawk.ecom.svp.request.QueryProductParam;
import com.hawk.ecom.svp.request.RegisterForCouponParam;
import com.hawk.framework.pub.pk.PkGenService;
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
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private BsiOrderDetailMapper bsiOrderDetailMapper;
	
	@Autowired
	private PkGenService pkGenService;
	
	/**
	 * 查询产品信息
	 * @param queryProductParam
	 * @return
	 */
	public BsiProductDomain queryProduct(QueryProductParam queryProductParam){
		
		int phoneModelId = bsiPhoneModelService.queryPhoneModelId(queryProductParam.getBrand(), queryProductParam.getModel());
		
		int productId = bsiPhoneProdcutMapService.queryProductId(phoneModelId, queryProductParam.getPeriod());
		
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
	@Transactional
	public void activateCoupon(ActivateCouponParam activateCouponParam){
		
		Map<String,Object> params = new HashMap<String,Object>();
		String bsiCashCouponCode = activateCouponParam.getCouponCode();
		params.put("bsiCashCouponCode", bsiCashCouponCode);
		List<BsiCashCouponDomain> list = bsiCashCouponMapper.loadDynamic(params);
		if (list.size() == 0)
			throw new RuntimeException("代金券不存在");
		
		BsiCashCouponDomain bsiCashCouponDomain = list.get(0);
		if (bsiCashCouponDomain.getBsiCashCouponStatus() != ConstCouponParameter.CouopnStatus.USED){
			throw new RuntimeException("代金券 已经使用");
		}
		
		if (bsiCashCouponDomain.getBsiCashCouponStatus()== ConstCouponParameter.CouopnStatus.OUT_OF_DATE || bsiCashCouponDomain.getBsiCashCouponInvalidDate().before(new Date())){
			throw new RuntimeException("代金券 已经过期");
		}
		
		/**
		 * 符合条件,下单
		 */		
		Date currentDate = new Date();
		/**
		 * 订单
		 */
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setMobileNumber(activateCouponParam.getTicket());
		orderDomain.setOrderCode(UUID.randomUUID().toString());
		orderDomain.setOrderStatus(ConstOrderStatus.PAYED);
		orderDomain.setOrderType(ConstOrderType.REGISTER_PRESENT_COUPON);
		orderDomain.setStoreCode(ConstStore.STORE_CODE);
		orderDomain.setUserCode(activateCouponParam.getTicket());
		orderDomain.setCreateDate(currentDate);
		orderDomain.setUpdateDate(currentDate);
		orderDomain.setId(pkGenService.genPk());
		
		/**
		 * 代金券
		 */
		bsiCashCouponDomain.setOrderCode(orderDomain.getOrderCode());
		bsiCashCouponDomain.setBsiCashCouponStatus(ConstCouponParameter.CouopnStatus.USED);
		
		/**
		 * 订单明细
		 */
		BsiOrderDetailDomain bsiOrderDetailDomain = new BsiOrderDetailDomain();
		bsiOrderDetailDomain.setBsiBenefBirthday(activateCouponParam.getBirthday());
		bsiOrderDetailDomain.setBsiBenefIdNumber(activateCouponParam.getIdNumber());
		bsiOrderDetailDomain.setBsiBenefIdTyp(activateCouponParam.getIdType());
		bsiOrderDetailDomain.setBsiBenefMobileNumber(activateCouponParam.getMobileNumber());
		bsiOrderDetailDomain.setBsiBenefName(activateCouponParam.getName());
		bsiOrderDetailDomain.setBsiBenefSex(activateCouponParam.getSex());
		bsiOrderDetailDomain.setBsiPhoneModelId(activateCouponParam.getPhoneModelId());
		bsiOrderDetailDomain.setBsiProductId(activateCouponParam.getProductId());
		bsiOrderDetailDomain.setImei(activateCouponParam.getImei());
		bsiOrderDetailDomain.setOrderId(orderDomain.getId());
		bsiOrderDetailDomain.setCreateDate(currentDate);
		bsiOrderDetailDomain.setUpdateDate(currentDate);
		
		orderMapper.insert(orderDomain);
		bsiCashCouponMapper.update(bsiCashCouponDomain);
		bsiOrderDetailMapper.insert(bsiOrderDetailDomain);
	}
}
