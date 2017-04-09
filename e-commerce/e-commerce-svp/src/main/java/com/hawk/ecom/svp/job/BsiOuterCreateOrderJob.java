package com.hawk.ecom.svp.job;

import java.util.Date;

import com.hawk.ecom.svp.constant.ConstBsiTaskStatus;
import com.hawk.ecom.svp.exception.OuterCallException;
import com.hawk.ecom.svp.persist.domain.BsiOrderDetailDomain;
import com.hawk.ecom.svp.persist.mapper.BsiOrderDetailMapper;
import com.hawk.ecom.svp.service.BsiOuterService;
import com.hawk.ecom.svp.service.BsiOuterService.Order;
import com.hawk.framework.pub.spring.FrameworkContext;

/**
 *  创建碎屏险外部订单job
 * @author Administrator
 *
 */
public class BsiOuterCreateOrderJob implements Runnable{
	
	private BsiOrderDetailDomain bsiOrderDetailDomain ;
	
	public BsiOuterCreateOrderJob(BsiOrderDetailDomain bsiOrderDetailDomain){
		this.bsiOrderDetailDomain = bsiOrderDetailDomain;
	}
	
	
	private static BsiOuterService bsiOuterService = FrameworkContext.getBean(BsiOuterService.class);
	private static BsiOrderDetailMapper bsiOrderDetailMapper = FrameworkContext.getBean(BsiOrderDetailMapper.class);
	
	
	private static Order buildOrder(BsiOrderDetailDomain bsiOrderDetailDomain){		
		Order order = new Order();
		order.setBirthday(bsiOrderDetailDomain.getBsiBenefBirthday());
		order.setCertiType(bsiOrderDetailDomain.getBsiBenefIdTyp());		
		order.setGoodId(bsiOrderDetailDomain.getBsiPhoneModelId());
		order.setGoodsSerialNo(bsiOrderDetailDomain.getImei());
		order.setIdCard(bsiOrderDetailDomain.getBsiBenefIdNumber());
		order.setMobile(Long.parseLong(bsiOrderDetailDomain.getBsiBenefMobileNumber()));
		order.setOutOrderID(bsiOrderDetailDomain.getBsiTaskCode());
		order.setProductId(bsiOrderDetailDomain.getBsiProductId());
		order.setSex(bsiOrderDetailDomain.getBsiBenefSex());
		order.setUsername(bsiOrderDetailDomain.getBsiBenefName());
		
		return order;
	}

	@Override
	public void run() {
		
		/**
		 * 数据库增加 最后执行时间 ，下次执行时间 ，
		 */
		
		Order order = buildOrder(bsiOrderDetailDomain);
		int execTimes = bsiOrderDetailDomain.getExecTimes() ;
		
		try {
			bsiOrderDetailDomain.setUpdateDate(new Date());
			bsiOrderDetailDomain.setExecTimes(execTimes + 1);
			
			/**
			 * 用乐观锁卡住只能有一个执行
			 */
			
			String bsiInsuranceCode = bsiOuterService.outCreateOrder(order);
			bsiOrderDetailDomain.setBsiInsuranceCode(bsiInsuranceCode);
			bsiOrderDetailDomain.setBsiTaskStatus(ConstBsiTaskStatus.COMPLETE_SUCCESS);
		} catch (OuterCallException e) {
			bsiOrderDetailDomain.setLastExecErrCode(e.getCode());
			bsiOrderDetailDomain.setLastExecErrMsg(e.getMessage());
			bsiOrderDetailDomain.setBsiTaskStatus(ConstBsiTaskStatus.COMPLETE_FAILED);
		}catch (Exception e){
			
			if (execTimes >= bsiOrderDetailDomain.getMaxExecTimes()){
				bsiOrderDetailDomain.setBsiTaskStatus(ConstBsiTaskStatus.COMPLETE_FAILED);
				bsiOrderDetailDomain.setLastExecErrCode("overtimes");
				bsiOrderDetailDomain.setLastExecErrMsg("达到最大执行次数");
			}else{
				bsiOrderDetailDomain.setBsiTaskStatus(ConstBsiTaskStatus.EXEC_FAILED);
				bsiOrderDetailDomain.setLastExecErrCode(e.getClass().getName());
				bsiOrderDetailDomain.setLastExecErrMsg(e.getMessage());
				bsiOrderDetailDomain. // 设置下次执行时间  scheduleTime
			}
			
			
			
			
			//大于执行次数，更新代金券状态(添加失败原因) ，更新订单状态  
		}finally{
			/**
			 * 更新订单明细，更新订单  更新 代金券状态
			 */
			bsiOrderDetailMapper.update(bsiOrderDetailDomain);
			
			dispatchServlet 拦截器 ,前后执行 ,mybatis cursor, spring batch , quartz
			
		}
	}

}
