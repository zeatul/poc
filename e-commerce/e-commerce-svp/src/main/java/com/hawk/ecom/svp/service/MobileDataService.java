package com.hawk.ecom.svp.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.svp.constant.ConstChageStatus;
import com.hawk.ecom.svp.constant.ConstOrderStatus;
import com.hawk.ecom.svp.constant.ConstOrderType;
import com.hawk.ecom.svp.constant.ConstStore;
import com.hawk.ecom.svp.persist.domain.MobileDataOrderDetailDomain;
import com.hawk.ecom.svp.persist.domain.OrderDomain;
import com.hawk.ecom.svp.persist.mapper.MobileDataOrderDetailMapper;
import com.hawk.ecom.svp.persist.mapper.OrderMapper;
import com.hawk.ecom.svp.persist.mapperex.OrderMapperEx;
import com.hawk.ecom.svp.request.SignInParam;
import com.hawk.ecom.svp.request.UnicomNotifyParam;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.utility.tools.DateTools;

@Service
public class MobileDataService {

	@Autowired
	private OrderMapperEx orderMapperEx;
	
	@Autowired
	private PkGenService pkGenService;
	
	@Autowired
	private OrderMapper OrderMapper;
	
	@Autowired
	private MobileDataOrderDetailMapper mobileDataOrderDetailMapper;
	
	@Autowired
	private MobileDataOrderDetailService mobileDataOrderDetailService;

	/**
	 * 签到,送流量 每个月可以签到10次，每次签到送10M流量。 只支持流通手机号。 每隔5分钟可以签到一次。 当月签到10次，即送完。
	 * 下个月重新开始签到计算
	 * 
	 * @param signInParam
	 */
	@Transactional
	@Valid
	public void signIn(@Valid SignInParam signInParam) {
		String mobileNumber = signInParam.getMobileNumber();
		String orderType = ConstOrderType.SIGN_IN_MOBILE_DATA_GIFT_10;
		Date currentDt = new Date();
		Date stdt = DateTools.firstDayOfMonth(currentDt);
		Date endt = DateTools.firstDayOfNextMonth(currentDt);
		List<OrderDomain> orderDomainList = orderMapperEx.querySigninOrderOfMonth(mobileNumber, orderType, stdt, endt);
		orderDomainList.sort((a, b) -> {
			if (a.getCreateDate().before(b.getCreateDate()))
				return -1;
			else if (a.getCreateDate().after(b.getCreateDate()))
				return 1;
			else
				return 0;
		});
		
		if (orderDomainList.size() > 9){
			throw new RuntimeException("当月签到不能超过10次");
		}
		
		
		
		if (orderDomainList.size() > 0){
			OrderDomain orderDomain = orderDomainList.get(orderDomainList.size()-1);
			if (currentDt.before(DateTools.addMinutes(orderDomain.getCreateDate(), 5))){
				throw new RuntimeException("签到间隔不能少于5分钟");
			}
		}
		
		
		/**
		 * 符合签到规则,产生订单和订单明细
		 */
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setMobileNumber(mobileNumber);
		orderDomain.setOrderCode(UUID.randomUUID().toString());
		orderDomain.setOrderStatus(ConstOrderStatus.PAYED);
		orderDomain.setOrderType(orderType);
		orderDomain.setStoreCode(ConstStore.STORE_CODE);
		orderDomain.setUserCode(mobileNumber);
		orderDomain.setCreateDate(currentDt);
		orderDomain.setUpdateDate(currentDt);
		orderDomain.setId(pkGenService.genPk());
		
		MobileDataOrderDetailDomain mobileDataOrderDetailDomain = new MobileDataOrderDetailDomain();
		
		mobileDataOrderDetailDomain.setChargeDataSize(10);
		mobileDataOrderDetailDomain.setChargeMobileNumber(mobileNumber);
		mobileDataOrderDetailDomain.setChargeTaskCode(UUID.randomUUID().toString());
		mobileDataOrderDetailDomain.setOrderId(orderDomain.getId());
		mobileDataOrderDetailDomain.setChargeStatus(ConstChageStatus.UNCHARGED);
		mobileDataOrderDetailDomain.setExecTimes(0);
		mobileDataOrderDetailDomain.setCreateDate(currentDt);
		mobileDataOrderDetailDomain.setUpdateDate(currentDt);
		mobileDataOrderDetailDomain.setId(pkGenService.genPk());
		
		OrderMapper.insert(orderDomain);
		mobileDataOrderDetailMapper.insert(mobileDataOrderDetailDomain);
	}
	
	/**
	 * 校验taskId是否存在，状态是否为UNCHARGED
	 * @param taskId
	 */
	@Valid
	public void checkTaskId(@NotEmpty("taskId") String taskId){
		
		MobileDataOrderDetailDomain mobileDataOrderDetailDomain =  mobileDataOrderDetailService.loadByTaskId(taskId);
		
		
		if(mobileDataOrderDetailDomain == null)
			throw new RuntimeException("任务不存在");
		
		if(mobileDataOrderDetailDomain.getChargeStatus() != ConstChageStatus.UNCHARGED)
			throw new RuntimeException("任务状态不是未充值");
	}
	
	@Valid
	public void notify(@Valid UnicomNotifyParam unicomNotifyParam){
		MobileDataOrderDetailDomain mobileDataOrderDetailDomain =  mobileDataOrderDetailService.loadByTaskId(unicomNotifyParam.getTaskId());
		if(mobileDataOrderDetailDomain == null)
			throw new RuntimeException("任务不存在");
		
		if (mobileDataOrderDetailDomain.getChargeStatus() == ConstChageStatus.SUCCESS_COMPLETED)
			return ;
	}

}
