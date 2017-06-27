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
import com.hawk.ecom.product.exception.PicNotFoundRuntimeException;
import com.hawk.ecom.product.exception.UnMatchedStoreOperatorException;
import com.hawk.ecom.product.persist.domain.PicDomain;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.persist.mapper.PicMapper;
import com.hawk.ecom.product.request.CreatePicParam;
import com.hawk.ecom.product.request.ListPicParam;
import com.hawk.ecom.product.request.LoadPicParam;
import com.hawk.ecom.product.request.RemovePicParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class PicService {
	

	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;
	
	@Autowired
	private PicMapper picMapper;
	
	@Autowired
	private MallAuthService authService;
	
	@Autowired
	private SkuService skuService;
	
	@Autowired
	private ProductService productService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public PicDomain loadPic(Integer id) {
		PicDomain picDomain = null;
		if (id != null) {
			logger.error("loadPic: id is null");
			picDomain = picMapper.load(id);
		}
		if (picDomain == null) {
			throw new PicNotFoundRuntimeException();
		}
		return picDomain;
	}
	
	@Valid
	public PicDomain createPic(@Valid @NotNull("参数") CreatePicParam createPicParam) throws Exception{
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		Integer skuId = createPicParam.getSkuId();
		Integer productId = createPicParam.getProductId();
		
		PicDomain picDomain = DomainTools.copy(createPicParam, PicDomain.class);
		
		if (skuId != null && skuId != 0){
			SkuDomain skuDomain = skuService.loadSkuById(skuId);
			/**
			 * 检测是否是本用户的商铺的商品
			 */
			if (!skuDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
				throw new UnMatchedStoreOperatorException();
			}
			picDomain.setProductId(skuDomain.getId());
		}else if (productId != null && productId != 0){
			ProductDomain productDomain = productService.loadProduct(productId);
			/**
			 * 检测是否是本用户的商铺的商品
			 */
			if (!productDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
				throw new UnMatchedStoreOperatorException();
			}
			picDomain.setProductId(productDomain.getId());
			picDomain.setSkuId(0);
		}else{
			throw new RuntimeException("产品主键或者产品SKU主键至少存在一个");
		}
		Date now = new Date();
		String userCode = AuthThreadLocal.getUserCode();
		picDomain.setCreateDate(now);
		picDomain.setCreateUserCode(userCode);
		picDomain.setUpdateDate(now);
		picDomain.setUpdateUserCode(userCode);
		picDomain.setId(pkGenService.genPk());
		
		if (picDomain.getObjectOrder() == null){
			picDomain.setObjectOrder(100);
		}
		
		picMapper.insert(picDomain);
		
		return picDomain;
	}
	
	@Valid
	public PagingQueryResultWrap<PicDomain> listPic(@Valid @NotNull("参数") ListPicParam listPicParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		if (StringTools.isNullOrEmpty(listPicParam.getOrder())) {
			listPicParam.setOrder("create_date desc");
		}
		MybatisParam params = MybatisTools.page(new MybatisParam(), listPicParam)//
				.put("storeCode", AuthThreadLocal.getStoreCode())//
				.put("storeCode", listPicParam.getPicType())//
				.put("storeCode", listPicParam.getProductId())//
				.put("storeCode", listPicParam.getSkuId());
		PagingQueryResultWrap<PicDomain> wrap = new PagingQueryResultWrap<PicDomain>();
		wrap.setDbCount(picMapper.count(params));
		if (wrap.getDbCount() > 0){
			wrap.setRecords(picMapper.loadDynamicPaging(params));
		}

		return wrap;
	}

	@Valid
	@Transactional
	public void removePic(@Valid @NotNull("参数") RemovePicParam removePicParam) {
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}
		for (Integer picId : removePicParam.getIds()){
			PicDomain picDomain = loadPic(picId);
			picDomain.getSkuId();
			/**
			 * TODO:上架状态是否禁止修改图片
			 */
			picMapper.delete(picId);
		}
	}
	
	@Valid
	public PicDomain loadPic(@Valid @NotNull("参数") LoadPicParam loadPicParam) {

		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))) {
			throw new IllegalAccessRuntimeException();
		}

		PicDomain picDomain = loadPic(loadPicParam.getId());
		/**
		 * 检测是否是本用户的商铺的商品
		 */
		if (!picDomain.getStoreCode().equals(AuthThreadLocal.getStoreCode())) {
			throw new UnMatchedStoreOperatorException();
		}

		return picDomain;
	}
}
