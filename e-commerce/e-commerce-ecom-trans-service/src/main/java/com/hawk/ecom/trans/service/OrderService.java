package com.hawk.ecom.trans.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
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
import com.hawk.ecom.trans.exception.DuplicateOrderDetailRuntimeException;
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
import com.hawk.ecom.trans.persist.mapperex.OrderExMapper;
import com.hawk.ecom.trans.request.CreateOrderParam;
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
	private OrderExMapper orderExMapper;
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	@Autowired
	private OrderDetailDeliveryDataMapper  orderDetailDeliveryDataMapper;
	
	@Autowired
	private OrderOperationMapper orderOperationMapper;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Valid
	@Transactional
	public OrderDomain createOrder(@Valid @NotNull("函数入参") CreateOrderParam createOrderParam) throws Exception{
		
		Date now = new Date();
		String userCode = AuthThreadLocal.getUserCode();
		String storeCode = null;
		
		Date payExpireTime = DateTools.addDays(now, 1);
		
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
			boolean isSuccess = skuService.updateSkuSotckQuantity(skuDomain, orderDetailQuantity*-1, userCode, now, 20);
			
			
			if (!isSuccess){
				throw new RuntimeException("更新库存失败");
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
				
				/**
				 * 特殊交付的订单支付过期时间默认30分钟
				 */
				payExpireTime = computePayExpireTime(payExpireTime ,now,30);
				
				if (orderDetailQuantity != 1){
					throw new UnSupportOrderDeatailQuantityRuntimeException();
				}
				
				OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = buildOrderDetailDeliveryDataDomain(now,skuDomain, productDomain,userCode);
				
				orderDetailMap.put(orderDetailDomain, orderDetailDeliveryDataDomain);
				
				Map<String,Object> map = orderDetailParam.getDeliveryData();
				
				if (productDomain.getDeliveryType()  == ConstProduct.DeliveryType.CHARGE_FLOW_DATA){
					
					/**
					 * 充流量的订单支付过期时间默认15分钟
					 */
					payExpireTime = computePayExpireTime(payExpireTime ,now,15);
					
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
					
					大数据校验imei是否为新机
					
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
		OrderDomain orderDomain = buildOrderDomain(now,payExpireTime,userCode,storeCode,createOrderParam,orderDesc,orderOriginalPrice,orderTransPrice);
		orderMapper.insert(orderDomain);
		
		/**
		 * 插入订单明细
		 * 插入订单明细交付数据
		 */
		for (OrderDetailDomain orderDetailDomain : orderDetailDomainList){
			orderDetailDomain.setId(pkGenService.genPk());
			orderDetailDomain.setOrderCode(orderDomain.getOrderCode());
			orderDetailDomain.setOrderId(orderDomain.getId());
			try {
				orderDetailMapper.insert(orderDetailDomain);
			}  catch (DuplicateKeyException ex) {
				logger.error("DuplicatOrderDetailDomain,orderId={},skuId={},orderDetailType={}",orderDetailDomain.getOrderId(),orderDetailDomain.getSkuId(),orderDetailDomain.getOrderDetailType());
				throw new DuplicateOrderDetailRuntimeException();
			}
			
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
	
	
	private Date computePayExpireTime(Date payExpireTime ,Date now ,int offset){
		Date date  = DateTools.addMinutes(now, offset);
		if (payExpireTime.after(date)){
			payExpireTime = date;
		}
		return payExpireTime;
	}
	
	private String generateTaskCode(Date now){
		String head = DateTools.convert(now, "yyyyMMddHH");
		Integer tail = orderOuterCodeSequenceService.genPk()+1000000;
		
		return StringTools.concat(head,tail);
	}
	
	private OrderDomain buildOrderDomain(Date now,Date payExpireTime,String userCode,String storeCode,CreateOrderParam createOrderParam,String orderDesc,BigDecimal orderOriginalPrice ,
			BigDecimal orderTransPrice){
		OrderDomain orderDomain = new OrderDomain();
		orderDomain.setCreateDate(now);
		orderDomain.setCreateUserCode(null);
		orderDomain.setCurrency(ConstProduct.Currency.RMB);
		orderDomain.setTotalFreightCharge(new BigDecimal(0));
		
		orderDomain.setOrderCustomerMemo(createOrderParam.getOrderCustomerMemo());
		orderDomain.setOrderDesc(orderDesc);
		orderDomain.setOrderOriginalPrice(orderOriginalPrice);		
		
		/**
		 * 支付过期时间
		 */
		orderDomain.setOrderPayExpireTime(payExpireTime);
		
		
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
		 updateOrderStatus(orderDomain,orderStatus,operationDesc,operationMemo);
	}
	
	private  void updateOrderStatus(OrderDomain orderDomain ,Integer orderStatus,String operationDesc,String operationMemo){
		 Date now = new Date();
		 OrderDomain updateDomain = new OrderDomain();
		 if (orderDomain.getOrderStatus() == ConstOrder.OrderStatus.SUCCESS){
			 throw new OrderStatusIsNotAcceptableRuntimeException();
		 }else if (orderDomain.getOrderStatus() == orderStatus){
			 logger.error("orderDomain's current status is same as the new orderStatus,orderId={},orderStatus={}",orderDomain.getId(),orderStatus);
			 return ;
		 }
		 
		 String userCode = AuthThreadLocal.getUserCode();
		 updateDomain.setId(orderDomain.getId());
		 updateDomain.setOrderStatus(orderStatus);
		 updateDomain.setUpdateDate(now);
		 updateDomain.setUpdateUserCode(userCode);
		 orderMapper.updateWithoutNull(updateDomain);
		 OrderOperationDomain operationDomain = new OrderOperationDomain();
		 operationDomain.setCreateDate(now);
		 operationDomain.setCreateUserCode(userCode);
		 operationDomain.setId(pkGenService.genPk());
		 operationDomain.setOperationDesc(operationDesc);
		 operationDomain.setOperationMemo(operationMemo);
		 operationDomain.setOrderCode(orderDomain.getOrderCode());
		 operationDomain.setOrderId(orderDomain.getId());
		 operationDomain.setOrderPreStatus(orderDomain.getOrderStatus());
		 operationDomain.setOrderNextStatus(orderStatus);
		 operationDomain.setStoreCode(orderDomain.getStoreCode());
		 operationDomain.setUpdateDate(now);
		 operationDomain.setUpdateUserCode(userCode);
		 operationDomain.setUserCode(orderDomain.getUserCode());
		 orderOperationMapper.insert(operationDomain);
	}
	
	public  void updateOrderStatus(String orderCode ,Integer orderStatus,String operationDesc,String operationMemo){
		 OrderDomain orderDomain = loadOrder(orderCode);
		 updateOrderStatus(orderDomain,orderStatus,operationDesc,operationMemo);
		
	}
	
	@Valid
	public List<OrderDetailDomain> loadOrderDetailByOrderId(@NotNull Integer orderId){
		MybatisParam params = new MybatisParam().put("orderId", orderId);
		return orderDetailMapper.loadDynamic(params);
	}
	
	@Transactional
	public void closeUnpaidOrder(OrderDomain orderDomain){
		String message = "管理员或用户关闭订单";
		String userCode  =  AuthThreadLocal.getUserCode();
		if (userCode == null || userCode.equalsIgnoreCase("system")){
			message = "订单支付超时自动关闭";
		}
		
		updateOrderStatus(orderDomain,ConstOrder.OrderStatus.CLOSED,message,message);
		/**
		 * 回退库存量
		 */
		List<OrderDetailDomain> orderDetailDomainList =  loadOrderDetailByOrderId(orderDomain.getId());
		
		for (OrderDetailDomain orderDetailDomain : orderDetailDomainList){
			Integer skuId = orderDetailDomain.getSkuId();
			Integer quantity = orderDetailDomain.getOrderDetailQuantity();
			
			skuService.updateSkuSotckQuantity(skuId, quantity, null, new Date(), 20);
		}
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
	
	/**
	 * 查询超过最后支付时间2分钟的数据，最大返回2000条
	 * @return
	 */
	public List<Integer> queryUnpaidOvertimeOrder(){
		Date threshold = DateTools.addMinutes(new Date(), -2);
		return orderExMapper.queryUnpaidOvertimeOrder(ConstOrder.OrderStatus.UNPAIED, threshold, 2000);
	}
	
	/**
	 * 查询订单明细状态都是已经成功的，超过10分钟未处理的数据
	 * @return
	 */
	public List<Integer> queryUncheckedSuccessOrder(){
		final Integer orderStatus = ConstOrder.OrderStatus.PAIED;
		final Integer limit = 3000;
		final List<Integer> orderDetailStatusList = Arrays.asList(ConstOrder.OrderDetailStatus.CANCELED,ConstOrder.OrderDetailStatus.FAILURE,ConstOrder.OrderDetailStatus.PROCESSING);
		Date threshold = DateTools.addMinutes(new Date(), -10);
		return orderExMapper.queryUncheckedSuccessOrder(orderStatus, threshold, orderDetailStatusList, limit);
	}
	
	/**
	 * 查询订单明细状态都是已经成功的，超过10分钟未处理的数据
	 * @return
	 */
	public List<Integer> queryUncheckedFailedOrder(){
		final Integer orderStatus = ConstOrder.OrderStatus.PAIED;
		final Integer limit = 3000;
		final List<Integer> orderDetailStatusList = Arrays.asList(ConstOrder.OrderDetailStatus.FAILURE);
		Date threshold = DateTools.addMinutes(new Date(), -10);
		return orderExMapper.queryUncheckedFailedOrder(orderStatus, threshold, orderDetailStatusList, limit);
	}
	
	/**
	 * 状态为已支付，所有明细都成功交付了，则订单状态变为成功
	 * @param orderId
	 */
	public void checkSuccessOrder(Integer orderId){
		/**
		 * 校验订单状态
		 */
		OrderDomain orderDomain = loadOrder(orderId);
		if (!(orderDomain.getOrderStatus() == ConstOrder.OrderStatus.PAIED)){
			throw new RuntimeException("订单状态不是已支付");
		}
		/**
		 * 校验明细
		 */
		List<OrderDetailDomain> orderDetailDomainList = orderDetailService.queryOrderDetailByOrderId(orderId);
		if (orderDetailDomainList.size() == 0){
			throw new RuntimeException("未发现任何明细，orderId="+orderId);
		}
		
		for (OrderDetailDomain orderDetailDomain : orderDetailDomainList){
			if (!(orderDetailDomain.getOrderDetailStatus()== ConstOrder.OrderDetailStatus.SUCCESS)){
				throw new RuntimeException("发现未完成的订单明细，orderId="+orderId+",orderDeailId="+orderDetailDomain.getId());
			}
		}
		
		updateOrderStatus(orderId, ConstOrder.OrderStatus.SUCCESS, "订单执行完成，明细全部交付", "订单执行完成，明细全部交付");
	}
	
	public void checkFailedOrder(Integer orderId){
		/**
		 * 校验订单状态
		 */
		OrderDomain orderDomain = loadOrder(orderId);
		if (!(orderDomain.getOrderStatus() == ConstOrder.OrderStatus.PAIED)){
			throw new RuntimeException("订单状态不是已支付");
		}
		
		/**
		 * 校验明细
		 */
		List<OrderDetailDomain> orderDetailDomainList = orderDetailService.queryOrderDetailByOrderId(orderId);
		if (orderDetailDomainList.size() == 0){
			throw new RuntimeException("未发现任何明细，orderId="+orderId);
		}
		boolean hasFailure = false;
		for (OrderDetailDomain orderDetailDomain : orderDetailDomainList){
			if (orderDetailDomain.getOrderDetailStatus()== ConstOrder.OrderDetailStatus.FAILURE){
				hasFailure = true;
				break;
			}
		}
		if (!hasFailure){
			throw new RuntimeException("未发现处理失败的订单明细，orderId="+orderId);
		}
		
		updateOrderStatus(orderId, ConstOrder.OrderStatus.FAILURE, "订单执行失败，有明细未能成功处理", "订单执行失败，有明细未能成功处理");
	}
	

	
	public static void main(String[] args){
		System.out.println(new BigDecimal("10.0100").scale());
		System.out.println(new BigDecimal("10.01").toString());
	}
}
