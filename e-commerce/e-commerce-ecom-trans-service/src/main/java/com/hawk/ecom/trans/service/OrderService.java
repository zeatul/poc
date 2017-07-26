package com.hawk.ecom.trans.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.hawk.ecom.trans.exception.DiffrentStoreProductInOneOrderRuntimeException;
import com.hawk.ecom.trans.exception.OrderNotBelongToLoginUserRuntimeException;
import com.hawk.ecom.trans.exception.OrderNotFoundRuntimeException;
import com.hawk.ecom.trans.exception.OrderPayExpireRuntimeException;
import com.hawk.ecom.trans.exception.OrderStatusIsNotAcceptableRuntimeException;
import com.hawk.ecom.trans.exception.ProductIsNotOnSaleRuntimeException;
import com.hawk.ecom.trans.exception.StockQuantityIsNotEnoughException;
import com.hawk.ecom.trans.exception.UnSupportOrderDeatailQuantityRuntimeException;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.domain.OrderDetailDomain;
import com.hawk.ecom.trans.persist.domain.OrderDomain;
import com.hawk.ecom.trans.persist.domain.OrderOperationDomain;
import com.hawk.ecom.trans.persist.mapper.OrderDetailDeliveryDataMapper;
import com.hawk.ecom.trans.persist.mapper.OrderDetailMapper;
import com.hawk.ecom.trans.persist.mapper.OrderMapper;
import com.hawk.ecom.trans.persist.mapper.OrderOperationMapper;
import com.hawk.ecom.trans.request.CreateOrderParam;
import com.hawk.ecom.trans.request.ListOrderDetailParam;
import com.hawk.ecom.trans.request.ListOrderParam;
import com.hawk.ecom.trans.request.LoadOrderParam;
import com.hawk.ecom.trans.request.BsiParam;
import com.hawk.ecom.trans.request.ChargeMobileParam;
import com.hawk.ecom.trans.request.OrderDetailParam;
import com.hawk.ecom.trans.response.OrderPayInfo;
import com.hawk.framework.dic.validation.ValidateService;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;
import com.hawk.framework.utility.tools.CollectionTools;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.StringTools;

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
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	@Autowired
	private OrderDetailDeliveryDataMapper  orderDetailDeliveryDataMapper;
	
	@Autowired
	private OrderOperationMapper orderOperationMapper;
	
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
		BigDecimal orderOriginalPrice = new BigDecimal(0);//订单原价
		StringBuilder sb = new StringBuilder();
		Map<OrderDetailDomain,OrderDetailDeliveryDataDomain> orderDetailMap = new HashMap<OrderDetailDomain,OrderDetailDeliveryDataDomain>();
		List<OrderDetailDomain> orderDetailDomainList = new ArrayList<OrderDetailDomain>();
		for (OrderDetailParam orderDetailParam :createOrderParam.getOrderDetails()){
			Integer skuId = orderDetailParam.getSkuId();
			Integer orderDetailQuantity = orderDetailParam.getOrderDetailQuantity();
			SkuDomain skuDomain = skuService.loadSkuById(skuId);
			
			if (storeCode == null){
				storeCode = skuDomain.getStoreCode();
			}else{
				if (!storeCode.equals(skuDomain.getStoreCode()))
					throw new DiffrentStoreProductInOneOrderRuntimeException();
			}
			
			if (skuDomain.getSkuStatus() != ConstProduct.SkuStatus.ON_SALE){
				throw new ProductIsNotOnSaleRuntimeException();
			}
			
			if (skuDomain.getSkuStockQuantity() < orderDetailQuantity){
				throw new StockQuantityIsNotEnoughException();
			}
			
			Integer productId = skuDomain.getProductId();
			ProductDomain productDomain = productService.loadProduct(productId);
			
			if (productDomain.getProductStatus() != ConstProduct.ProductStatus.ON_SALE){
				throw new ProductIsNotOnSaleRuntimeException();
			}
			
			/**
			 * 是否需要采用死循环，抢占库存 ？？？？？
			 */
			boolean isSuccess = false;
			int times = 5;
			while (!isSuccess && times > 0){
				isSuccess = skuService.updateSkuSotckQuantity(skuDomain, orderDetailQuantity*-1, null,null);
				if (!isSuccess){
					skuDomain = skuService.loadSkuById(skuId);
					if (skuDomain.getSkuStockQuantity() < orderDetailQuantity){
						throw new RuntimeException();
					}
				}
				times--;
			}
			if (!isSuccess){
				throw new RuntimeException();
			}
			
			orderOriginalPrice = orderOriginalPrice.add(skuDomain.getSalePrice().multiply(new BigDecimal(orderDetailQuantity)));
			sb.append(",").append(skuDomain.getSkuName());
			
			/**
			 * 构造订单明细
			 */
			OrderDetailDomain orderDetailDomain = buildOrderDetailDomain(now, orderDetailParam, skuDomain, userCode); 
			orderDetailDomainList.add(orderDetailDomain);
			
			/**
			 * 构造交付数据
			 */
			if (productDomain.getDeliveryType() >= ConstProduct.DeliveryType.CHARGE_FLOW_DATA){
				
				if (orderDetailQuantity != 1){
					throw new UnSupportOrderDeatailQuantityRuntimeException();
				}
				
				OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = buildOrderDetailDeliveryDataDomain(now,skuDomain, productDomain,userCode);
				
				orderDetailMap.put(orderDetailDomain, orderDetailDeliveryDataDomain);
				
				Map<String,Object> map = orderDetailParam.getDeliveryData();
				
				if (productDomain.getDeliveryType()  == ConstProduct.DeliveryType.CHARGE_FLOW_DATA){
					ChargeMobileParam chargeMobileParam = DomainTools.copy(map, ChargeMobileParam.class);
					validateService.validateObject(chargeMobileParam);
					orderDetailDeliveryDataDomain.setBenefMobileNumber(chargeMobileParam.getMobileNumber());
					orderDetailDeliveryDataDomain.setMaxExecTimes(1); //充流量只可以执行一次
					orderDetailDeliveryDataDomain.setOuterProductId(skuDomain.getSkuCode()); /*默认skucode存放产品编号*/
					
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
			
			
			
			
			
			
		}
		
		/**
		 * 订单成交价 = 订单原价+运费+活动减免
		 */
		BigDecimal orderTransPrice = orderOriginalPrice ;
		
		/**
		 * 订单描述为商品的名称相连接
		 */
		String orderDesc = createOrderParam.getOrderDesc();
		if (StringTools.isNullOrEmpty(orderDesc)){
			orderDesc = sb.substring(1);
			orderDesc = orderDesc.substring(0, orderDesc.length()>450?450:orderDesc.length());
		}
		
		
		/**
		 * 构造订单,插入订单
		 */
		OrderDomain orderDomain = buildOrderDomain(now,userCode,storeCode,createOrderParam,orderDesc,orderOriginalPrice,orderTransPrice);
		orderMapper.insert(orderDomain);
		
		/**
		 * 插入订单明细
		 * 插入订单明细交付数据
		 */
		for (OrderDetailDomain orderDetailDomain : orderDetailDomainList){
			orderDetailDomain.setId(pkGenService.genPk());
			orderDetailDomain.setOrderCode(orderDomain.getOrderCode());
			orderDetailDomain.setOrderId(orderDomain.getId());
			orderDetailMapper.insert(orderDetailDomain);
			
			OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = orderDetailMap.get(orderDetailDomain);
			if (orderDetailDeliveryDataDomain != null){
				orderDetailDeliveryDataDomain.setId(pkGenService.genPk());
				orderDetailDeliveryDataDomain.setOrderDetailId(orderDetailDomain.getId());
				orderDetailDeliveryDataDomain.setOrderCode(orderDomain.getOrderCode());
				orderDetailDeliveryDataDomain.setOrderId(orderDomain.getId());
				orderDetailDeliveryDataDomain.setTaskCode(generateTaskCode(now));
				
				orderDetailDeliveryDataMapper.insert(orderDetailDeliveryDataDomain);
			}
		}
		
		return orderDomain;
	}
	
	private String generateTaskCode(Date now){
		String head = DateTools.convert(now, "yyyyMMddHH");
		Integer tail = orderOuterCodeSequenceService.genPk()+1000000;
		
		return StringTools.concat(head,tail);
	}
	
	private OrderDomain buildOrderDomain(Date now,String userCode,String storeCode,CreateOrderParam createOrderParam,String orderDesc,BigDecimal orderOriginalPrice ,
			BigDecimal orderTransPrice){
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setCreateDate(now);
		orderDomain.setCreateUserCode(null);
		orderDomain.setCurrency(ConstProduct.Currency.RMB);
		orderDomain.setTotalFreightCharge(new BigDecimal(0));
		
		orderDomain.setOrderCustomerMemo(createOrderParam.getOrderCustomerMemo());
		orderDomain.setOrderDesc(orderDesc);
		orderDomain.setOrderOriginalPrice(orderOriginalPrice);
		orderDomain.setOrderPayExpireTime(DateTools.addMinutes(now,30));
		orderDomain.setOrderStatus(ConstOrder.OrderStatus.UNPAIED);
		orderDomain.setOrderTransPrice(orderTransPrice);
		orderDomain.setOrderType(createOrderParam.getOrderType());
		orderDomain.setOrderVersion(1);
		orderDomain.setPayType(createOrderParam.getPayType());
		orderDomain.setStoreCode(storeCode);
		orderDomain.setUserCode(userCode);
		orderDomain.setUpdateDate(now);
		orderDomain.setUpdateUserCode(null);
		
		orderDomain.setId(pkGenService.genPk());
		orderDomain.setOrderCode(generateOrderCode(now));
		
		
		return orderDomain;
	}
	
	/**
	 * 
	 * @param now
	 * @return
	 */
	private String generateOrderCode(Date now){
		String head = DateTools.convert(now, "yyyyMMddHH");
		Integer tail = orderCodeSequenceService.genPk()+1000000;
		
		return StringTools.concat(head,tail);
	}
	
	private OrderDetailDeliveryDataDomain buildOrderDetailDeliveryDataDomain(Date now ,SkuDomain skuDomain,ProductDomain productDomain,String userCode){

		OrderDetailDeliveryDataDomain orderDetailDeliveryDtatDomain = new OrderDetailDeliveryDataDomain();
		orderDetailDeliveryDtatDomain.setCreateDate(now );
		orderDetailDeliveryDtatDomain.setCreateUserCode(null);
		orderDetailDeliveryDtatDomain.setDeliveryType(productDomain.getDeliveryType());
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
		orderDetailDeliveryDtatDomain.setTaskName(skuDomain.getSkuName());
//		orderDetailDeliveryDtatDomain.setTaskMemo(taskMemo);
		orderDetailDeliveryDtatDomain.setTaskStatus(ConstOrder.TaskStatus.UN_EXECUTE);
		orderDetailDeliveryDtatDomain.setUpdateDate(now);
		orderDetailDeliveryDtatDomain.setUpdateUserCode(null);
		orderDetailDeliveryDtatDomain.setUserCode(userCode);
		orderDetailDeliveryDtatDomain.setDeliveryStatus(ConstOrder.DeliveryStatus.UN_EXECUTE);
		
		return orderDetailDeliveryDtatDomain;
	}
	
	private OrderDetailDomain buildOrderDetailDomain(Date now,OrderDetailParam orderDetailParam,SkuDomain skuDomain ,String userCode){
		Integer orderDetailQuantity = orderDetailParam.getOrderDetailQuantity();
		
		OrderDetailDomain orderDetailDomain = new OrderDetailDomain();
		orderDetailDomain.setCreateDate(now);
		orderDetailDomain.setCreateUserCode(null);
		orderDetailDomain.setCurrency(ConstProduct.Currency.RMB);
//		orderDetailDomain.setId(id);
		orderDetailDomain.setOrdeDetailOriginalPrice(skuDomain.getSalePrice().multiply(new BigDecimal(orderDetailQuantity)) );
		orderDetailDomain.setOrdeDetailTransPrice(skuDomain.getSalePrice().multiply(new BigDecimal(orderDetailQuantity)) );
//		orderDetailDomain.setOrderCode(orderCode);
//		orderDetailDomain.setOrderDetailMemo(orderDetailMemo);
		orderDetailDomain.setOrderDetailType(ConstOrder.OrderDetailType.NORMAL);
		orderDetailDomain.setOrderDetailStatus(ConstOrder.OrderDetailStatus.PROCESSING);
		orderDetailDomain.setOrderDetailName(skuDomain.getSkuName());
//		orderDetailDomain.setOrderId(orderId);
		orderDetailDomain.setOriginalUnitPrice(skuDomain.getSalePrice());
		orderDetailDomain.setProductId(skuDomain.getProductId());
		orderDetailDomain.setSkuId(skuDomain.getId());
		orderDetailDomain.setThumbnail(skuDomain.getThumbnail());
		orderDetailDomain.setOrderDetailQuantity(orderDetailQuantity);
		orderDetailDomain.setStoreCode(skuDomain.getStoreCode());
		orderDetailDomain.setTransUnitPrice(skuDomain.getSalePrice());
		orderDetailDomain.setUpdateDate(now );
		orderDetailDomain.setUpdateUserCode(null);
		orderDetailDomain.setUserCode(userCode);
		
		return orderDetailDomain;
	}

	@Valid
	public PagingQueryResultWrap<OrderDomain> listOrder(@Valid @NotNull("函数入参") ListOrderParam listOrderParam){
		listOrderParam.setOrder("create_date desc");

		MybatisParam params = MybatisTools.page(new MybatisParam(), listOrderParam);
		params.put("orderStatus", listOrderParam.getOrderStatus());
		params.put("userCode", AuthThreadLocal.getUserCode());
		
		PagingQueryResultWrap<OrderDomain> wrap = new PagingQueryResultWrap<OrderDomain>();
		wrap.setDbCount(orderMapper.count(params));
		if (wrap.getDbCount() > 0){
			wrap.setRecords(orderMapper.loadDynamicPaging(params));
		}

		return wrap;
	}
	
	@Valid
	public PagingQueryResultWrap<OrderDetailDomain> listOrderDetail(@Valid @NotNull("函数入参") ListOrderDetailParam listOrderDetailParam){
		
		listOrderDetailParam.setOrder("sku_id asc");
		
		MybatisParam params = MybatisTools.page(new MybatisParam(), listOrderDetailParam);
		params.put("orderId", listOrderDetailParam.getOrderId());
		params.put("userCode", AuthThreadLocal.getUserCode());
		
		PagingQueryResultWrap<OrderDetailDomain> wrap = new PagingQueryResultWrap<OrderDetailDomain>();
		wrap.setDbCount(orderDetailMapper.count(params));
		if (wrap.getDbCount() > 0){
			wrap.setRecords(orderDetailMapper.loadDynamicPaging(params));
		}

		return wrap;
	}
	
	public OrderDomain loadOrder (Integer orderId){
		OrderDomain orderDomain = null;
		if (orderId != null){
			orderDomain = orderMapper.load(orderId);
		}
		if (orderDomain == null){
			throw new OrderNotFoundRuntimeException();
		}
		return orderDomain;
	}
	
	public OrderDomain loadOrder(String orderCode){
		OrderDomain orderDomain = null;
		if (StringTools.isNotNullOrEmpty(orderCode)){
			MybatisParam params = new MybatisParam().put("orderCode", orderCode);
			orderDomain = MybatisTools.single(orderMapper.loadDynamic(params));
		}
		if (orderDomain == null){
			throw new OrderNotFoundRuntimeException();
		}
		return orderDomain;
	}
	
	@Valid
	public OrderDomain loadOrder (@Valid @NotNull("函数入参") LoadOrderParam loadOrderParam){
		OrderDomain orderDomain = loadOrder(loadOrderParam.getOrderId());
		if (!orderDomain.getUserCode().equals(AuthThreadLocal.getUserCode())){
			throw new OrderNotBelongToLoginUserRuntimeException();
		}
		return orderDomain;
	}

	/**
	 * 获取订单支付用的信息并校验订单
	 * 订单用户和当前用户一致
	 * 订单状态为未支付，支付类型为在线支付
	 * 订单的支付截止日期未到
	 * 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位
	 * 订单subject ，支付宝用,不可以为空
	 * 订单body,支付宝用,可以为空
	 * @param orderId
	 * @return
	 */
	public OrderPayInfo computeOrderPayInfo(Integer orderId){
		
		/**
		 * 加载订单
		 */
		OrderDomain orderDomain = loadOrder(orderId);
		/**
		 * 订单用户和当前用户一致
		 */
		if (!orderDomain.getUserCode().equals(AuthThreadLocal.getUserCode())){
			throw new OrderNotBelongToLoginUserRuntimeException();
		}
		
		/**
		 * 订单状态为未支付，支付类型为在线支付
		 * 订单的支付截止日期未到
		 */
		if (!orderDomain.getOrderStatus().equals(ConstOrder.OrderStatus.UNPAIED)){
			throw new OrderStatusIsNotAcceptableRuntimeException();
		}
		
		if (orderDomain.getOrderPayExpireTime().before(new Date())){
			throw new OrderPayExpireRuntimeException();
		}
		
		/**
		 * 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位
		 */
		BigDecimal transPrice = orderDomain.getOrderTransPrice();
		if (transPrice.compareTo(new BigDecimal("0.01")) < 0){
			throw new RuntimeException("支付价格不合法");
		}
		if (transPrice.compareTo(new BigDecimal("100000000.00")) > 0){
			throw new RuntimeException();
		} 
		
		
		OrderPayInfo orderPayInfo = new OrderPayInfo();
		orderPayInfo.setApplicationCode("SVP");
		orderPayInfo.setBody(null);
		orderPayInfo.setOrderCode(orderDomain.getOrderCode());
		orderPayInfo.setOrderDesc(orderDomain.getOrderDesc());
		orderPayInfo.setStoreCode(orderDomain.getStoreCode());
		orderPayInfo.setTotalAmount(orderDomain.getOrderTransPrice());
		orderPayInfo.setUserCode(orderDomain.getUserCode());
		orderPayInfo.setCurrency(orderDomain.getCurrency());
		return orderPayInfo;
	}
	
	public  void updateOrderStatus(Integer orderId ,Integer orderStatus,String operationDesc,String operationMemo){
		 OrderDomain orderDomain = loadOrder(orderId);
		 updateOrderStatus(orderId,orderStatus,operationDesc,operationMemo);
	}
	
	private  void updateOrderStatus(OrderDomain orderDomain ,Integer orderStatus,String operationDesc,String operationMemo){
		 Date now = new Date();
		 OrderDomain updateDomain = new OrderDomain();
		 if (orderDomain.getOrderStatus() == ConstOrder.OrderStatus.SUCCESS){
			 throw new OrderStatusIsNotAcceptableRuntimeException();
		 }
		 updateDomain.setId(orderDomain.getCurrency());
		 updateDomain.setOrderStatus(orderStatus);
		 updateDomain.setUpdateDate(now);
		 orderMapper.update(updateDomain);
		 OrderOperationDomain operationDomain = new OrderOperationDomain();
		 operationDomain.setCreateDate(now);
		 operationDomain.setCreateUserCode(null);
		 operationDomain.setId(pkGenService.genPk());
		 operationDomain.setOperationDesc(operationDesc);
		 operationDomain.setOperationMemo(operationMemo);
		 operationDomain.setOrderCode(orderDomain.getOrderCode());
		 operationDomain.setOrderId(orderDomain.getId());
		 operationDomain.setOrderPreStatus(orderDomain.getOrderStatus());
		 operationDomain.setOrderNextStatus(orderStatus);
		 operationDomain.setStoreCode(orderDomain.getStoreCode());
		 operationDomain.setUpdateDate(now);
		 operationDomain.setUpdateUserCode(null);
		 operationDomain.setUserCode(orderDomain.getUserCode());
		 orderOperationMapper.insert(operationDomain);
	}
	
	public  void updateOrderStatus(String orderCode ,Integer orderStatus,String operationDesc,String operationMemo){
		 OrderDomain orderDomain = loadOrder(orderCode);
		 updateOrderStatus(orderDomain,orderStatus,operationDesc,operationMemo);
		
	}
	
	/**
	 * 订单是否全部明细都成功完成
	 * @param orderId
	 * @return
	 */
	@Valid
	public boolean isAllOrderDetailSuccess(@NotNull Integer orderId){
		MybatisParam params = new MybatisParam().put("orderId", orderId);
		List<OrderDetailDomain> list = orderDetailMapper.loadDynamic(params);
		if (CollectionTools.isNullOrEmpty(list)){
			throw new RuntimeException("Hadn't find any orderDetail of Order,orderId="+orderId);
		}
		for (OrderDetailDomain orderDetailDomain : list){
			if (!orderDetailDomain.getOrderDetailStatus().equals(ConstOrder.OrderDetailStatus.SUCCESS)){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args){
		System.out.println(new BigDecimal("10.0100").scale());
		System.out.println(new BigDecimal("10.01").toString());
	}
}
