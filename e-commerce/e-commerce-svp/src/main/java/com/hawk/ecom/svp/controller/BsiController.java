package com.hawk.ecom.svp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.svp.persist.domain.BsiPhoneModelDomain;
import com.hawk.ecom.svp.persist.domain.BsiProductDomain;
import com.hawk.ecom.svp.request.ActivateCouponParam;
import com.hawk.ecom.svp.request.QueryModelOfBrandParam;
import com.hawk.ecom.svp.request.QueryProductParam;
import com.hawk.ecom.svp.response.MultiBrandResponse;
import com.hawk.ecom.svp.response.MultiPhoneModelResponse;
import com.hawk.ecom.svp.response.SingleProductResponse;
import com.hawk.ecom.svp.service.BsiPhoneModelService;
import com.hawk.ecom.svp.service.BsiService;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/bsi")
public class BsiController {

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
	
	@RequestMapping(value = "/brand/model", method = GET)
	public WebResponse<MultiPhoneModelResponse> modelOfBrand(HttpServletRequest request) throws Exception{
		QueryModelOfBrandParam param = HttpRequestTools.parse(request, QueryModelOfBrandParam.class);
		List<BsiPhoneModelDomain> domainList = bsiPhoneModelService.queryPhoneModel(param.getBrand());
		List<MultiPhoneModelResponse.PhoneModel> phoneModelList = DomainTools.copy(domainList, MultiPhoneModelResponse.PhoneModel.class);
		WebResponse<MultiPhoneModelResponse> response = SuccessResponse.build(new MultiPhoneModelResponse(phoneModelList));
		return response;
	}
	
	@RequestMapping(value = "/product", method = GET)
	public WebResponse<SingleProductResponse> queryProduct(HttpServletRequest request) throws Exception{
		QueryProductParam queryProductParam = HttpRequestTools.parse(request, QueryProductParam.class);
		BsiProductDomain bsiProductDomain =bsiService.queryProduct(queryProductParam);
		SingleProductResponse singleProductResponse = DomainTools.copy(bsiProductDomain, SingleProductResponse.class);
		return SuccessResponse.build(singleProductResponse);
	}
	
	@RequestMapping(value = "/coupon/activate", method = POST)
	public WebResponse<ResponseData> activateCashCoupon(HttpServletRequest request) throws Exception{
		ActivateCouponParam activateCouponParam = HttpRequestTools.parse(request, ActivateCouponParam.class);
	
		return SuccessResponse.build(null);
	}

}
