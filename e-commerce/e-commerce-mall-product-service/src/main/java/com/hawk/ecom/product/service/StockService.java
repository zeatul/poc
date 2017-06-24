package com.hawk.ecom.product.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.exception.UnMatchedStoreOperatorException;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.persist.domain.StockDomain;
import com.hawk.ecom.product.request.CreateStockParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;

@Service
public class StockService {
	
	@Autowired
	private SkuService skuService;
	
	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;
	
	@Autowired
	private MallAuthService authService;

	
	@Valid
	public StockDomain createStock(@Valid @NotEmpty("参数") CreateStockParam createStockParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		Integer skuId = createStockParam.getSkuId();
		
		SkuDomain skuDomain = skuService.loadSkuById(skuId);
		
		/**
		 * 检测是否是本用户的商铺的商品
		 */
		if (!skuDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())){
			throw new UnMatchedStoreOperatorException();
		}
		
		if (createStockParam.getStockOperation() == ConstProduct.StockOperation.STOCK_IN){
			return ;
		}

		throw new RuntimeException("Unsupported stockOperation");
	}

}
