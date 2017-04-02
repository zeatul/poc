package com.hawk.ecom.svp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.svp.persist.domain.BsiCashCouponDomain;
import com.hawk.ecom.svp.persist.domain.BsiPhoneModelDomain;
import com.hawk.ecom.svp.persist.domain.BsiProductDomain;
import com.hawk.ecom.svp.request.ActivateCouponParam;
import com.hawk.ecom.svp.request.ListCouponParam;
import com.hawk.ecom.svp.request.QueryModelOfBrandParam;
import com.hawk.ecom.svp.request.QueryProductParam;
import com.hawk.ecom.svp.request.RegisterPresentCouponParam;
import com.hawk.ecom.svp.response.MultiBrandResponse;
import com.hawk.ecom.svp.response.MultiCouponResponse;
import com.hawk.ecom.svp.response.MultiCouponResponse.CashCoupon;
import com.hawk.ecom.svp.response.MultiPhoneModelResponse;
import com.hawk.ecom.svp.response.SingleProductResponse;
import com.hawk.ecom.svp.service.BsiPhoneModelService;
import com.hawk.ecom.svp.service.BsiService;
import com.hawk.ecom.svp.utils.HttpRequestToolsForSignature;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("svp/bsi")
public class BsiController {
	
	@RequestMapping(value="/home",method = GET)
	public String home(){
		return "Welcome to bsi!!!";
	}

	@Autowired
	private BsiService bsiService;
	
	@Autowired
	private BsiPhoneModelService bsiPhoneModelService;

	@RequestMapping(value = "/brand", method = GET)
	public WebResponse<MultiBrandResponse> brand() {
		List<String> brandList = bsiPhoneModelService.queryPhoneBrand();

		WebResponse<MultiBrandResponse> response = SuccessResponse.build(new MultiBrandResponse(brandList));
		
		return response;
	}
	
	@RequestMapping(value = "/brand/{brand}", method = GET)
	public WebResponse<MultiPhoneModelResponse> modelOfBrand(@PathVariable String brand) throws Exception{
		QueryModelOfBrandParam param = new QueryModelOfBrandParam();
		param.setBrand(brand);
		List<BsiPhoneModelDomain> domainList = bsiPhoneModelService.queryPhoneModel(param.getBrand());
		List<MultiPhoneModelResponse.PhoneModel> phoneModelList = DomainTools.copy(domainList, MultiPhoneModelResponse.PhoneModel.class);
		WebResponse<MultiPhoneModelResponse> response = SuccessResponse.build(new MultiPhoneModelResponse(phoneModelList));
		return response;
	}
	
	@RequestMapping(value = "/modelId/{modelId}/period/{period}", method = GET)
	public WebResponse<SingleProductResponse> queryProduct(@PathVariable Integer modelId,@PathVariable Integer period) throws Exception{
		QueryProductParam queryProductParam = new QueryProductParam();
		queryProductParam.setModelId(modelId);
		queryProductParam.setPeriod(period);
		BsiProductDomain bsiProductDomain =bsiService.queryProduct(queryProductParam);
		SingleProductResponse singleProductResponse = DomainTools.copy(bsiProductDomain, SingleProductResponse.class);
		return SuccessResponse.build(singleProductResponse);
	}
	
	@RequestMapping(value = "/coupon/register/present", method = POST)
	public WebResponse<ResponseData> registerPresentCoupon(HttpServletRequest request) throws Exception{
		RegisterPresentCouponParam registerForCouponParam = HttpRequestToolsForSignature.parse(request, RegisterPresentCouponParam.class);
		bsiService.rgeisterPresentCoupon(registerForCouponParam);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/coupon/activate", method = POST)
	public WebResponse<ResponseData> activateCashCoupon(HttpServletRequest request) throws Exception{
		ActivateCouponParam activateCouponParam = HttpRequestTools.parse(request, ActivateCouponParam.class);
		
		bsiService.activateCoupon(activateCouponParam);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/coupon/list", method = POST)
	public WebResponse<MultiCouponResponse> listCashCoupon(HttpServletRequest request) throws Exception{
		ListCouponParam listCouponParam = HttpRequestTools.parse(request, ListCouponParam.class);
		List<BsiCashCouponDomain> list = bsiService.listCoupon(listCouponParam);
		
		List<CashCoupon> c = DomainTools.copy(list, CashCoupon.class);
		
		return SuccessResponse.build(new MultiCouponResponse(c));
	}

}
