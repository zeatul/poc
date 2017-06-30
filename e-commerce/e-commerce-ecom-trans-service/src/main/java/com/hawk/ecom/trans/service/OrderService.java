package com.hawk.ecom.trans.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.service.ProductService;
import com.hawk.ecom.product.service.SkuService;
import com.hawk.ecom.trans.persist.domain.OrderDetailDomain;
import com.hawk.ecom.trans.persist.domain.OrderDomain;
import com.hawk.ecom.trans.request.CreateOrderParam;
import com.hawk.ecom.trans.request.OrderDetailParam;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;

@Service
public class OrderService {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SkuService skuService;
	
	@Valid
	@Transactional
	public OrderDomain createOrder(@Valid @NotNull("函数入参") CreateOrderParam createOrderParam){
		
		OrderDomain orderDomain = new OrderDomain();
		Date now = new Date();
		
		
		
		/**
		 * 校验SkU和产品状态为上架状态,
		 * 库存数量不小于购买数量
		 * 锁定库存
		 */		
		for (OrderDetailParam orderDetailParam :createOrderParam.getOrderDetails()){
			Integer skuId = orderDetailParam.getSkuId();
			Integer skuQuantity = orderDetailParam.getSkuQuantity();
			SkuDomain skuDomain = skuService.loadSkuById(skuId);
			
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
			int times = 20;
			while (!isSuccess && times > 0){
				isSuccess = skuService.updateSkuSotckQuantity(skuDomain, skuQuantity*-1, null,null);
				if (!isSuccess){
					skuDomain = skuService.loadSkuById(skuId);
				}
				times--;
			}
			if (!isSuccess){
				throw new RuntimeException();
			}
			
			/**
			 * 校验手机号和产品得匹配关系
			 */
			
			/**
			 * 构造订单明细
			 */
			OrderDetailDomain orderDetailDomain = new OrderDetailDomain();
			
			/**
			 * 构造交付数据
			 */
			
		}
		
		/**
		 * 构造订单,插入订单
		 */
		
		/**
		 * 插入订单明细
		 */
		return null;
	}

}
