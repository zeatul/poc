package com.hawk.ecom.trans.service;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.annotations.ConstructorArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.exception.OrderDetailDeliveryDataNotFoundRuntimeException;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.domainex.OrderDetailDeliveryDataExDomain;
import com.hawk.ecom.trans.persist.mapper.OrderDetailDeliveryDataMapper;
import com.hawk.ecom.trans.persist.mapperex.OrderDetailDeliveryDataExMapper;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class OrderDetailDeliveryDataService {
	
	@Autowired
	private OrderDetailDeliveryDataMapper orderDetailDeliveryDataMapper;
	
	@Autowired
	private OrderDetailDeliveryDataExMapper orderDetailDeliveryDataExMapper;
	
	public OrderDetailDeliveryDataDomain loadByTaskCode(String taskCode){
		OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = null;
		if (StringTools.isNotNullOrEmpty(taskCode)){
			MybatisParam params = new MybatisParam().put("taskCode", taskCode);
			orderDetailDeliveryDataDomain = MybatisTools.single(orderDetailDeliveryDataMapper.loadDynamic(params));
		}
		if (orderDetailDeliveryDataDomain == null){
			throw new OrderDetailDeliveryDataNotFoundRuntimeException();
		}
		return orderDetailDeliveryDataDomain;
	}
	
	@Valid
	public List<OrderDetailDeliveryDataDomain> queryByOrderDetailId(@NotNull Integer orderDetailId){
		MybatisParam params = new MybatisParam().put("orderDetailId", orderDetailId);
		return orderDetailDeliveryDataMapper.loadDynamic(params);
	}
	
	/**
	 * 查询已经支付，但是从未执行过的充值作业
	 * @return
	 */
	public List<OrderDetailDeliveryDataExDomain> loadOrderDeliveryDataForCharge(){
		return orderDetailDeliveryDataExMapper.loadOrderDeliveryDataForCharge(ConstProduct.DeliveryType.CHARGE_FLOW_DATA, ConstOrder.TaskStatus.UN_EXECUTE,
				ConstOrder.OrderStatus.PAIED,3000);
	}
	
	public List<OrderDetailDeliveryDataExDomain> loadOrderDeliveryDataForBsi(){
		final List<Integer> taskStatusList = Arrays.asList(ConstOrder.TaskStatus.EXECUTE_FAILED,ConstOrder.TaskStatus.UN_EXECUTE);
		return orderDetailDeliveryDataExMapper.loadOrderDeliveryDataForBsi(ConstProduct.DeliveryType.BSI, taskStatusList, ConstOrder.OrderStatus.PAIED,3000);
	}
}
