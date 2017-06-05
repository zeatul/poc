package com.hawk.ecom.svp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.pub.job.TaskPool;
import com.hawk.ecom.svp.constant.ConstBsiTaskStatus;
import com.hawk.ecom.svp.constant.ConstCouponParameter;
import com.hawk.ecom.svp.constant.ConstOrderStatus;
import com.hawk.ecom.svp.constant.ConstOrderType;
import com.hawk.ecom.svp.constant.ConstStore;
import com.hawk.ecom.svp.job.BsiOuterCreateOrderJob;
import com.hawk.ecom.svp.persist.domain.BsiCashCouponDomain;
import com.hawk.ecom.svp.persist.domain.BsiOrderDetailDomain;
import com.hawk.ecom.svp.persist.domain.BsiProductDomain;
import com.hawk.ecom.svp.persist.domain.OrderDomain;
import com.hawk.ecom.svp.persist.mapper.BsiCashCouponMapper;
import com.hawk.ecom.svp.persist.mapper.BsiOrderDetailMapper;
import com.hawk.ecom.svp.persist.mapper.OrderMapper;
import com.hawk.ecom.svp.request.ActivateCouponParam;
import com.hawk.ecom.svp.request.ListCouponParam;
import com.hawk.ecom.svp.request.QueryProductParam;
import com.hawk.ecom.svp.request.RegisterPresentCouponParam;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.utility.tools.DateTools;

@Service
public class BsiService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
		
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
	@Qualifier("pkGenService")
	private PkGenService pkGenService;
	
	@Autowired
	private TaskPool taskPool;
	
	@Autowired
	private BsiTalkingDataService bsiTalkingDataService;
	
	@Autowired
	private BsiCashCouponService bsiCashCouponService;
	
	@Autowired
	private BsiOrderDetailService bsiOrderDetailService;
	
	@Autowired
	private CodeService codeService;
	
	/**
	 * 查询产品信息
	 * @param queryProductParam
	 * @return
	 */
	@Valid
	public BsiProductDomain queryProduct(@Valid QueryProductParam queryProductParam){		
		
		int productId = bsiPhoneProdcutMapService.queryProductId(queryProductParam.getModelId(), queryProductParam.getPeriod());
		
		BsiProductDomain bsiProductDomain = bsiProductService.queryProduct(productId);
		if (bsiProductDomain == null)
			throw new RuntimeException("未找到产品");
		return bsiProductDomain;
	}
	
	/**
	 * 注册赠送代金券只有一次机会
	 * @param registerForCouponParam
	 */
	@Valid
	public void rgeisterPresentCoupon(@Valid RegisterPresentCouponParam registerForCouponParam){
		
		logger.info("start rgeisterPresentCoupon!!!");
		
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
		bsiCashCouponDomain.setBsiCashCouponCode(codeService.buildCashCouponCode());
		
		bsiCashCouponDomain.setBsiCashCouponCreateDate(currentDate);
		Date bsiCashCouponInvalidDate = DateTools.addDays(currentDate, 10);
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
		bsiCashCouponDomain.setPromotionActivityCode("00001");
		bsiCashCouponDomain.setPromotionActivityDesc("新用户注册赠送");
		bsiCashCouponDomain.setId(pkGenService.genPk());
		bsiCashCouponMapper.insert(bsiCashCouponDomain);
	}
	
	@Valid
	public List<BsiCashCouponDomain> listCoupon(@Valid ListCouponParam listCouponParam){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("mobileNumber", listCouponParam.getMobileNumber());
		return bsiCashCouponMapper.loadDynamic(params);
	}
	
	/**
	 * 激活代金券
	 * @param activateCouponParam
	 */
	@Transactional
	@Valid
	public void activateCoupon(@Valid ActivateCouponParam activateCouponParam){
		
		long stdt = System.currentTimeMillis();
		
		Map<String,Object> params = new HashMap<String,Object>();
		String bsiCashCouponCode = activateCouponParam.getCouponCode();
		params.put("bsiCashCouponCode", bsiCashCouponCode);
		List<BsiCashCouponDomain> list = bsiCashCouponMapper.loadDynamic(params);
		if (list.size() == 0)
			throw new RuntimeException("代金券不存在");
		
		BsiCashCouponDomain bsiCashCouponDomain = list.get(0);
		
		if (bsiCashCouponDomain.getBsiCashCouponStatus()== ConstCouponParameter.CouopnStatus.ACTIVVATING){
			throw new RuntimeException("代金券 正在激活中，请稍后再查询状态");
		}
		
		if (bsiCashCouponDomain.getBsiCashCouponStatus()== ConstCouponParameter.CouopnStatus.OUT_OF_DATE || bsiCashCouponDomain.getBsiCashCouponInvalidDate().before(new Date())){
			throw new RuntimeException("代金券 已经过期");
		}
		
		if (bsiCashCouponDomain.getBsiCashCouponStatus() != ConstCouponParameter.CouopnStatus.UNUSED && bsiCashCouponDomain.getBsiCashCouponStatus() != ConstCouponParameter.CouopnStatus.ACTIVATE_FAILED){
			throw new RuntimeException("代金券 已经使用");
		}
		
		
		
		
		/**
		 * TODO:检查产品的保险月数和代金券的保险月数是否一致
		 */
		BsiProductDomain bsiProductDomain = bsiProductService.queryProduct(activateCouponParam.getProductId());
		if (bsiProductDomain == null){
			throw new RuntimeException("未找到投保产品");
		}
		
		if (!(bsiProductDomain.getBsiProductValidPeriod().intValue() == bsiCashCouponDomain.getBsiCashCouponPeriod())){
			throw new RuntimeException("代金券保险月份数和产品的保险月份数不符");
		}
		
		/**
		 * 检查手机号和代金券是否匹配
		 */
		if (!bsiCashCouponDomain.getMobileNumber().equals(activateCouponParam.getTicket())){
			throw new RuntimeException("代金券所有者不是当前用户");
		}
		
		/**
		 * 检查手机是否是新机
		 */
		Date first = bsiTalkingDataService.first(activateCouponParam.getImei());
		if (first != null){
			Date curDate = new Date();
			if (DateTools.addDays(first, 10).before(curDate)){
				throw new RuntimeException("当前手机购买超过10天，不符合投保条件");
			}
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
		
		
		orderDomain.setOrderCode(codeService.buildOrderCode());
		
		
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
		bsiCashCouponDomain.setBsiCashCouponStatus(ConstCouponParameter.CouopnStatus.ACTIVVATING);
		
		/**
		 * 订单明细
		 */
		BsiOrderDetailDomain bsiOrderDetailDomain = new BsiOrderDetailDomain();
		bsiOrderDetailDomain.setBsiBenefBirthday(activateCouponParam.getBirthday());
		bsiOrderDetailDomain.setSupplierCode("00001");
		bsiOrderDetailDomain.setBsiBenefIdNumber(activateCouponParam.getIdNumber());
		bsiOrderDetailDomain.setBsiBenefIdTyp(activateCouponParam.getIdType());
		bsiOrderDetailDomain.setBsiBenefMobileNumber(activateCouponParam.getMobileNumber());
		bsiOrderDetailDomain.setBsiBenefName(activateCouponParam.getName());
		bsiOrderDetailDomain.setBsiBenefSex(activateCouponParam.getSex());
		bsiOrderDetailDomain.setBsiPhoneModelId(activateCouponParam.getPhoneModelId());
		bsiOrderDetailDomain.setBsiProductId(activateCouponParam.getProductId());
		bsiOrderDetailDomain.setImei(activateCouponParam.getImei());
		bsiOrderDetailDomain.setOrderCode(orderDomain.getOrderCode());
		bsiOrderDetailDomain.setStoreCode(orderDomain.getStoreCode());
		bsiOrderDetailDomain.setUserCode(orderDomain.getUserCode());
		bsiOrderDetailDomain.setCreateDate(currentDate);
		bsiOrderDetailDomain.setUpdateDate(currentDate);
		bsiOrderDetailDomain.setExecTimes(0);
		bsiOrderDetailDomain.setMaxExecTimes(8); // 最多请求8次
		bsiOrderDetailDomain.setBsiTaskCode(codeService.buildBsiTaskCode());
		bsiOrderDetailDomain.setBsiTaskStatus(ConstBsiTaskStatus.UN_EXEC);
		bsiOrderDetailDomain.setBsiCashCouponCode(bsiCashCouponCode);
		bsiOrderDetailDomain.setPromotionActivityCode("00001");
		bsiOrderDetailDomain.setPromotionActivityDesc("新用户注册赠送");
		
		bsiOrderDetailDomain.setId(pkGenService.genPk());
		
		
		orderMapper.insert(orderDomain);
		bsiCashCouponMapper.update(bsiCashCouponDomain);
		bsiOrderDetailMapper.insert(bsiOrderDetailDomain);
		
		BsiOuterCreateOrderJob job = new BsiOuterCreateOrderJob(bsiOrderDetailDomain);
		taskPool.execute(job);
		
		long endt = System.currentTimeMillis();
		logger.info("++++ BsiService.activateCoupon,cost millseconds {}",(endt-stdt));
	}
	
//	public void activateCashCouponJob(String couponCode){
//		BsiCashCouponSubJob bsiCashCouponSubJob = new BsiCashCouponSubJob(couponCode);
//		taskPool.execute(bsiCashCouponSubJob);
//	}
	
	public BsiOrderDetailDomain queryOrderDetailByCouponCode(String couponCode){
		BsiCashCouponDomain bsiCashCouponDomain = bsiCashCouponService.loadByCode(couponCode);
		if (bsiCashCouponDomain == null){
			throw new RuntimeException("代金券未找到");
		}
		String orderCode = bsiCashCouponDomain.getOrderCode();
		if(orderCode == null){
			throw new RuntimeException("订单号不存在");
		}
		
		BsiOrderDetailDomain bsiOrderDetailDomain = bsiOrderDetailService.loadByOrderCode(orderCode);

		return bsiOrderDetailDomain;
		
	}
}
