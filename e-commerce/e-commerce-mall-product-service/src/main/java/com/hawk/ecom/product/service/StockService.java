package com.hawk.ecom.product.service;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.exception.ConcurrentChangeStockRuntimeException;
import com.hawk.ecom.product.exception.StockNotFoundRuntimeException;
import com.hawk.ecom.product.exception.UnMatchedStoreOperatorException;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.persist.domain.StockDomain;
import com.hawk.ecom.product.persist.mapper.StockMapper;
import com.hawk.ecom.product.request.CreateStockParam;
import com.hawk.ecom.product.request.ListStockParam;
import com.hawk.ecom.product.request.LoadStockParam;
import com.hawk.ecom.product.request.UpdateStockParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class StockService {

	@Autowired
	private SkuService skuService;

	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;

	@Autowired
	private MallAuthService authService;
	
	@Autowired
	private StockMapper stockMapper;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public StockDomain loadStock(Integer id) {
		StockDomain stockDomain = null;
		if (id != null) {
			stockDomain = stockMapper.load(id);
		}else{
			logger.error("loadStock: id is null");			
		}
		if (stockDomain == null) {
			throw new StockNotFoundRuntimeException();
		}
		return stockDomain;
	}
	

	@Valid
	@Transactional
	public StockDomain createStock(@Valid @NotNull("参数") CreateStockParam createStockParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		Integer skuId = createStockParam.getSkuId();

		String userCode = AuthThreadLocal.getUserCode();
		Date now = new Date();
		int delta = createStockParam.getStockQuantity();
		SkuDomain skuDomain = skuService.loadSkuById(skuId);

		/**
		 * 检测是否是本用户的商铺的商品
		 */
		if (!skuDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
			throw new UnMatchedStoreOperatorException();
		}

		if (createStockParam.getStockOperation() == ConstProduct.StockOperation.STOCK_IN) {
			/**
			 * 乐观锁更新产品Sku的数量,成功后,增加库存条目
			 */
			if (!skuService.updateSkuSotckQuantity(skuDomain, delta, userCode, now)){
				throw new ConcurrentChangeStockRuntimeException();
			}
			
			StockDomain stockDomain = new StockDomain();
			stockDomain.setCreateDate(now);
			stockDomain.setCreateUserCode(userCode);
			stockDomain.setId(pkGenService.genPk());
			stockDomain.setProductId(skuDomain.getProductId());
			stockDomain.setSkuId(skuDomain.getId());
			stockDomain.setStockItemCode(createStockParam.getStockItemCode());
			stockDomain.setStockMemo(createStockParam.getStockMemo());
			stockDomain.setStockOperation(createStockParam.getStockOperation());
			stockDomain.setStockQuantity(createStockParam.getStockQuantity());
			stockDomain.setUpdateDate(now);
			stockDomain.setUpdateUserCode(userCode);
			stockDomain.setWarehouseCode(createStockParam.getWarehouseCode());
			stockDomain.setStoreCode(skuDomain.getStoreCode());
			stockMapper.insert(stockDomain);
			
			return stockDomain;
		}

		throw new RuntimeException("Unsupported stockOperation");
	}
	
	@Valid
	@Transactional
	public void updateStock(@Valid @NotNull("参数") UpdateStockParam updateStockParam) throws Exception{
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		String userCode = AuthThreadLocal.getUserCode();
		Date now = new Date();
		
		StockDomain stockDomain= loadStock(updateStockParam.getId());
		/**
		 * 检测是否是本用户的商铺的商品
		 */
		if (!stockDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
			throw new UnMatchedStoreOperatorException();
		}
		
		
		StockDomain updateDomain = DomainTools.copy(updateStockParam, StockDomain.class);
		updateDomain.setUpdateDate(now);
		updateDomain.setUpdateUserCode(userCode);
		
		stockMapper.update(stockDomain);
	}

	@Valid
	public PagingQueryResultWrap<StockDomain> listStock(@Valid @NotNull("参数") ListStockParam listStockParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		if (StringTools.isNullOrEmpty(listStockParam.getOrder())) {
			listStockParam.setOrder("create_date desc");
		}
		MybatisParam params = MybatisTools.page(new MybatisParam(), listStockParam)//
				.put("productId", listStockParam.getProductId())//
				.put("skuId", listStockParam.getSkuId())//
				.put("stockItemCode", listStockParam.getStockItemCode())//
				.put("stockOperation", listStockParam.getStockOperation())//
				.put("stockOperation", listStockParam.getStockOperation())//
				.put("storeCode", AuthThreadLocal.getStoreCode());
		PagingQueryResultWrap<StockDomain> wrap = new PagingQueryResultWrap<StockDomain>();
		wrap.setDbCount(stockMapper.count(params));
		if (wrap.getDbCount() > 0){
			wrap.setRecords(stockMapper.loadDynamicPaging(params));
		}

		return wrap;
	}
	
	@Valid
	public StockDomain loadStock(@Valid @NotNull("参数") LoadStockParam loadStockParam) {

		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		StockDomain stockDomain = loadStock(loadStockParam.getId());
		/**
		 * 检测是否是本用户的商铺的商品
		 */
		if (!stockDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
			throw new UnMatchedStoreOperatorException();
		}

		return stockDomain;
	}
}
