package com.hawk.ecom.trans.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.service.ProductService;
import com.hawk.ecom.product.service.SkuService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.domain.OrderDetailDomain;
import com.hawk.ecom.trans.persist.domain.OrderDomain;
import com.hawk.ecom.trans.request.CreateOrderParam;
import com.hawk.ecom.trans.request.BsiParam;
import com.hawk.ecom.trans.request.ChargeMobileParam;
import com.hawk.ecom.trans.request.OrderDetailParam;
import com.hawk.framework.dic.validation.ValidateService;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@Service
public class OrderService {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SkuService skuService;
	
	@Autowired
	private ValidateService validateService;
	
	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;
	
	@Autowired
	@Qualifier("orderCodeSequenceService")
	private PkGenService orderCodeSequenceService;
	
	@Autowired
	@Qualifier("orderOuterCodeSequenceService")
	private PkGenService orderOuterCodeSequenceService;
	
	@Valid
	@Transactional
	public OrderDomain createOrder(@Valid @NotNull("函数入参") CreateOrderParam createOrderParam) throws Exception{
		
		Date now = new Date();
		String userCode = AuthThreadLocal.getUserCode();
		String storeCode = null;
		
		/**
		 * 校验SkU和产品状态为上架状态,
		 * 库存数量不小于购买数量
		 * 锁定库存
		 */		
		for (OrderDetailParam orderDetailParam :createOrderParam.getOrderDetails()){
			Integer skuId = orderDetailParam.getSkuId();
			Integer skuQuantity = orderDetailParam.getSkuQuantity();
			SkuDomain skuDomain = skuService.loadSkuById(skuId);
			
			if (storeCode = null){
				storeCode = skuDomain.getStoreCode();
			}else{
				if (!storeCode.equals(skuDomain.getStoreCode()))
					throw new 
			}
			
			if (skuDomain.getSkuStatus() != ConstProduct.SkuStatus.ON_SALE){
				throw new RuntimeException();
			}
			
			if (skuDomain.getSkuStockQuantity() < skuQuantity){
				throw new RuntimeException();
			}
			
			Integer productId = skuDomain.getProductId();
			ProductDomain productDomain = productService.loadProduct(productId);
			
			if (productDomain.getProductStatus() != ConstProduct.ProductStatus.ON_SALE){
				throw new RuntimeException();
			}
			
			/**
			 * 是否需要采用死循环，抢占库存 ？？？？？
			 */
			boolean isSuccess = false;
			int times = 5;
			while (!isSuccess && times > 0){
				isSuccess = skuService.updateSkuSotckQuantity(skuDomain, skuQuantity*-1, null,null);
				if (!isSuccess){
					skuDomain = skuService.loadSkuById(skuId);
					if (skuDomain.getSkuStockQuantity() < skuQuantity){
						throw new RuntimeException();
					}
				}
				times--;
			}
			if (!isSuccess){
				throw new RuntimeException();
			}
			
			/**
			 * 构造交付数据
			 */
			if (productDomain.getDeliveryType() >= ConstProduct.DeliveryType.CHARGE){
				
				OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = buildOrderDetailDeliveryDataDomain(now, productDomain,userCode);
				Map<String,Object> map = orderDetailParam.getDeliveryData();
				
				if (productDomain.getDeliveryType()  == ConstProduct.DeliveryType.CHARGE){
					ChargeMobileParam chargeMobileParam = DomainTools.copy(map, ChargeMobileParam.class);
					validateService.validateObject(chargeMobileParam);
					orderDetailDeliveryDataDomain.setBenefMobileNumber(chargeMobileParam.getMobileNumber());
				}else if (productDomain.getDeliveryType()  == ConstProduct.DeliveryType.BSI){
					BsiParam bsiParam = DomainTools.copy(map, BsiParam.class);
					validateService.validateObject(bsiParam);
					
					orderDetailDeliveryDataDomain.setBenefIdNumber(bsiParam.getBenefIdNumber());
					orderDetailDeliveryDataDomain.setBenefIdTyp(bsiParam.getBenefIdTyp().toString());
					orderDetailDeliveryDataDomain.setBenefMobileNumber(bsiParam.getBenefMobileNumber());
					orderDetailDeliveryDataDomain.setBenefName(bsiParam.getBenefName());
					orderDetailDeliveryDataDomain.setImei(bsiParam.getImei());
					orderDetailDeliveryDataDomain.setOuterPhoneModelId(bsiParam.getOuterPhoneModelId().toString());
					orderDetailDeliveryDataDomain.setOuterProductId(bsiParam.getOuterProductId().toString());
					
				}
				
				
			}
			
			/**
			 * 构造订单明细
			 */
			OrderDetailDomain orderDetailDomain = buildOrderDetailDomain(now, orderDetailParam, skuDomain, userCode);
			
			
		}
		
		/**
		 * 构造订单,插入订单
		 */
		OrderDomain orderDomain = buildOrderDomain();
		
		/**
		 * 填写订单明细和顶带你明细用到的订单的数据
		 * 插入订单明细
		 */
		return null;
	}
	
	public OrderDomain billdOrderDomain(Date now,String storeCode,CreateOrderParam createOrderParam,String orderDesc,BigDecimal orderOriginalPrice ,
			BigDecimal orderTransPrice){
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setCreateDate(now);
		orderDomain.setCreateUserCode(null);
		orderDomain.setCurrency(ConstProduct.Currency.RMB);
		orderDomain.setTotalFreightCharge(new BigDecimal(0));
		
		orderDomain.setOrderCustomerMemo(null);
		orderDomain.setOrderDesc(orderDesc);
		orderDomain.setOrderOriginalPrice(orderOriginalPrice);
		orderDomain.setOrderPayExpireTime(DateTools.addMinutes(now,30));
		orderDomain.setOrderStatus(ConstOrder.OrderStatus.UNPAIED);
		orderDomain.setOrderTransPrice(orderTransPrice);
		orderDomain.setOrderType(createOrderParam.getOrderType());
		orderDomain.setOrderVersion(1);
		orderDomain.setPayType(createOrderParam.getPayType());
		orderDomain.setStoreCode(storeCode);
		
		orderDomain.setId(pkGenService.genPk());
		orderDomain.setOrderCode(orderCode);
		
		return orderDomain;
	}
	
	private OrderDetailDeliveryDataDomain buildOrderDetailDeliveryDataDomain(Date now ,ProductDomain productDomain,String userCode){

		OrderDetailDeliveryDataDomain orderDetailDeliveryDtatDomain = new OrderDetailDeliveryDataDomain();
		orderDetailDeliveryDtatDomain.setCreateDate(now );
		orderDetailDeliveryDtatDomain.setCreateUserCode(null);
		orderDetailDeliveryDtatDomain.setExecTimes(0);
//		orderDetailDeliveryDtatDomain.setId(id);
		orderDetailDeliveryDtatDomain.setMaxExecTimes(6);
//		orderDetailDeliveryDtatDomain.setOrderCode(orderCode);
//		orderDetailDeliveryDtatDomain.setOrderDetailId(orderDetailId);
//		orderDetailDeliveryDtatDomain.setOrderId(orderId);
		orderDetailDeliveryDtatDomain.setScheduleExecDate(now);
		orderDetailDeliveryDtatDomain.setStoreCode(productDomain.getStoreCode());
		orderDetailDeliveryDtatDomain.setSupplierCode(null);
//		orderDetailDeliveryDtatDomain.setTaskCode(taskCode);
//		orderDetailDeliveryDtatDomain.setTaskDesc(taskDesc);
//		orderDetailDeliveryDtatDomain.setTaskName(taskName);
//		orderDetailDeliveryDtatDomain.setTaskMemo(taskMemo);
		orderDetailDeliveryDtatDomain.setTaskStatus(ConstOrder.TaskStatus.UN_EXECUTE);
		orderDetailDeliveryDtatDomain.setUpdateDate(now);
		orderDetailDeliveryDtatDomain.setUpdateUserCode(null);
		orderDetailDeliveryDtatDomain.setUserCode(userCode);
		
		return orderDetailDeliveryDtatDomain;
	}
	
	private OrderDetailDomain buildOrderDetailDomain(Date now,OrderDetailParam orderDetailParam,SkuDomain skuDomain ,String userCode){
		Integer skuQuantity = orderDetailParam.getSkuQuantity();
		
		OrderDetailDomain orderDetailDomain = new OrderDetailDomain();
		orderDetailDomain.setCreateDate(now);
		orderDetailDomain.setCreateUserCode(null);
		orderDetailDomain.setCurrency(ConstProduct.Currency.RMB);
//		orderDetailDomain.setId(id);
		orderDetailDomain.setOrdeDetailOriginalPrice(skuDomain.getSalePrice().multiply(new BigDecimal(skuQuantity)) );
		orderDetailDomain.setOrdeDetailTransPrice(skuDomain.getSalePrice().multiply(new BigDecimal(skuQuantity)) );
//		orderDetailDomain.setOrderCode(orderCode);
//		orderDetailDomain.setOrderDetailMemo(orderDetailMemo);
		orderDetailDomain.setOrderDetailType(ConstOrder.OrderDetailType.NORMAL);
//		orderDetailDomain.setOrderId(orderId);
		orderDetailDomain.setOriginalUnitPrice(skuDomain.getSalePrice());
		orderDetailDomain.setProductId(skuDomain.getProductId());
		orderDetailDomain.setSkuId(skuDomain.getId());
		orderDetailDomain.setSkuQuantity(skuQuantity);
		orderDetailDomain.setStoreCode(skuDomain.getStoreCode());
		orderDetailDomain.setTransUnitPrice(skuDomain.getSalePrice());
		orderDetailDomain.setUpdateDate(now );
		orderDetailDomain.setUpdateUserCode(null);
		orderDetailDomain.setUserCode(userCode);
		return orderDetailDomain;
	}

}
