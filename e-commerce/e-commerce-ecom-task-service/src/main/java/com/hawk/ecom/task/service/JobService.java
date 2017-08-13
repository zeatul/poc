package com.hawk.ecom.task.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hawk.ecom.pay.service.PaymentService;
import com.hawk.ecom.pub.job.TaskPool;
import com.hawk.ecom.task.job.BsiJob;
import com.hawk.ecom.task.job.ChargeDataJob;
import com.hawk.ecom.task.job.CheckChargeResultJob;
import com.hawk.ecom.task.job.CheckFailedOrderDetailJob;
import com.hawk.ecom.task.job.CheckFailedOrderJob;
import com.hawk.ecom.task.job.CheckSuccessOrderDetailJob;
import com.hawk.ecom.task.job.CheckSuccessOrderJob;
import com.hawk.ecom.task.job.CheckUnfinishedPaymentJob;
import com.hawk.ecom.task.job.CloseUnpaidOvertimeOrderJob;
import com.hawk.ecom.trans.persist.domainex.OrderDetailDeliveryDataExDomain;
import com.hawk.ecom.trans.service.OrderDetailDeliveryDataService;
import com.hawk.ecom.trans.service.OrderDetailService;
import com.hawk.ecom.trans.service.OrderService;

@Service
public class JobService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderDetailDeliveryDataService orderDetailDeliveryDataService;
	
	@Autowired
	private TaskPool taskPool;
	
	@Autowired
	private OrderService orderService;	
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private OrderDetailService orderDetailService;

	/**
	 * 关闭超过支付时间仍然没有支付记录的订单
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 1000 * 60 * 2)
	public void batchCloseUnpaiedOvertimeOrder() {
		logger.info("++++Start to execute batchCloseUnpaiedOvertimeOrder job");
		List<Integer> orderIdList = orderService.queryUnpaidOvertimeOrder();
		logger.info("Found {} unpaid expired order",orderIdList.size());
		for (Integer orderId : orderIdList){
			CloseUnpaidOvertimeOrderJob job = new CloseUnpaidOvertimeOrderJob(orderId);
			taskPool.execute(job);
		}
		logger.info("++++Success to execute batchCloseUnpaiedOvertimeOrder job");
	}
	
	/**
	 * 查询有支付单，但是状态是待支付，超过10分钟的数据，检测有没有因为没有收到回调通知造成，状态没有变化的已经支付成功的数据，修改状态。
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 1000 * 60 * 5)
	public void batchCheckUnfinishedPayment(){
		logger.info("++++Start to execute batchCheckUnfinishedPayment job");
		List<Integer> paymentBillIds = paymentService.queryUnfinishedPaymentBill();
		logger.info("Found {} unfinishedPayment",paymentBillIds.size());
		for (Integer paymentBillId : paymentBillIds){
			CheckUnfinishedPaymentJob job = new CheckUnfinishedPaymentJob(paymentBillId);
			taskPool.execute(job);
		}
		logger.info("++++Success to execute batchCheckUnfinishedPayment job");
	}
	
	/**
	 * 查询交付状态存在失败记录的数据，修改对应的订单明细状态
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 1000 * 60 * 6)
	public void batchCheckFailedOrderDeatail(){
		logger.info("++++Start to execute batchCheckFailedOrderDeatail job");
		List<Integer> orderDetailIdList = orderDetailService.queryUncheckedFailedOrderDetail();
		logger.info("Found {} unchecked failed orderDetail",orderDetailIdList.size());
		for (Integer orderDetailId : orderDetailIdList){
			CheckFailedOrderDetailJob job = new CheckFailedOrderDetailJob(orderDetailId);
			taskPool.execute(job);
		}
		logger.info("++++Success to execute batchCheckFailedOrderDeatail job");
	}
	
	/**
	 * 查询交付状态全部成功的数据，修改对应的订单明细状态
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 1000 * 60 * 6)
	public void batchCheckSuccessOrderDeatail(){
		logger.info("++++Start to execute batchCheckSuccessOrderDeatail job");
		List<Integer> orderDetailIdList = orderDetailService.queryUncheckedSuccessOrderDetail();
		logger.info("Found {} unchecked success orderDetail",orderDetailIdList.size());
		for (Integer orderDetailId : orderDetailIdList){
			CheckSuccessOrderDetailJob job = new CheckSuccessOrderDetailJob(orderDetailId);
			taskPool.execute(job);
		}
		logger.info("++++Success to execute batchCheckSuccessOrderDeatail job");
	}
	
	/**
	 * 查询订单明细全部成功的数据，修改对应的订单状态
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 1000 * 45 * 6)
	public void batchCheckSuccessOrder(){
		logger.info("++++Start to execute batchCheckSuccessOrder job");
		List<Integer> orderIdList = orderService.queryUncheckedSuccessOrder();
		logger.info("Found {} unchecked success order",orderIdList.size());
		for (Integer orderId : orderIdList){
			CheckSuccessOrderJob job = new CheckSuccessOrderJob(orderId);
			taskPool.execute(job);
		}
		logger.info("++++Success to execute batchCheckSuccessOrder job");
	}
	
	
	
	/**
	 * 查询订单明细存在失败的数据，修改对应的订单状态
	 */
	@Scheduled(initialDelay = 5000, fixedDelay = 1000 * 45 * 6)
	public void batchCheckFailedOrder(){
		logger.info("++++Start to execute batchCheckFailedOrder job");
		List<Integer> orderIdList = orderService.queryUncheckedFailedOrder();
		logger.info("Found {} unchecked failed order",orderIdList.size());
		for (Integer orderId : orderIdList){
			CheckFailedOrderJob job = new CheckFailedOrderJob(orderId);
			taskPool.execute(job);
		}
		logger.info("++++Success to execute batchCheckFailedOrder job");
	}
	
	
	/**
	 * 查询作业状态超过10分钟为未完成的，外部订单号不为空的充值交付明细，调用对应的查询接口
	 */
	@Scheduled(initialDelay = 15000, fixedDelay = 1000 * 60 * 6)
	public void batchCheckChargeResult(){
		logger.info("++++Start to execute batchCheckChargeResult job");
		List<Integer> orderDetailDeliveryDataIdList = orderDetailDeliveryDataService.loadOrderDeliveryDataForCheckChargeResult();
		logger.info("Found {} unchecked chargeResult delivery data",orderDetailDeliveryDataIdList.size());
		for (Integer orderDetailDeliveryDataId : orderDetailDeliveryDataIdList){
			CheckChargeResultJob job = new CheckChargeResultJob(orderDetailDeliveryDataId);
			taskPool.execute(job);
		}
		logger.info("++++Success to execute batchCheckChargeResult job");
	}

	/**
	 * 将已经支付完成，但是充值次数为0的交付单，拿出来充流量
	 */
	@Scheduled(initialDelay = 20000, fixedDelay = 1000 * 60 * 2)
	public void batchCharge() {
		logger.info("-----Start to execute batchCharge");
		List<OrderDetailDeliveryDataExDomain> orderDetailDeliveryDataExDomainList = orderDetailDeliveryDataService.loadOrderDeliveryDataForCharge();
		
		logger.info("Found {} charge jobs",orderDetailDeliveryDataExDomainList.size());
		
		for (OrderDetailDeliveryDataExDomain orderDetailDeliveryDataExDomain : orderDetailDeliveryDataExDomainList){
			ChargeDataJob chargeDataJob = new ChargeDataJob(orderDetailDeliveryDataExDomain.getTaskCode());
			taskPool.execute(chargeDataJob);
		}

		logger.info("Success to execute batchCharge");
	}
	
	/**
	 * 将支付已经完成，但是没有成功交付的小宝交付单,拿出来创建小宝订单
	 */
	@Scheduled(initialDelay = 20000, fixedDelay = 1000 * 60 * 5)
	public void batchBsi(){
		logger.info("-----Start to execute batchBsi");
		List<OrderDetailDeliveryDataExDomain> orderDetailDeliveryDataExDomainList = orderDetailDeliveryDataService.loadOrderDeliveryDataForBsi();
		
		logger.info("Found {} bsi jobs",orderDetailDeliveryDataExDomainList.size());
		
		for (OrderDetailDeliveryDataExDomain orderDetailDeliveryDataExDomain : orderDetailDeliveryDataExDomainList){
			BsiJob job = new BsiJob(orderDetailDeliveryDataExDomain.getTaskCode());
			taskPool.execute(job);
		}

		logger.info("Success to execute batchCharge");
	}

}
