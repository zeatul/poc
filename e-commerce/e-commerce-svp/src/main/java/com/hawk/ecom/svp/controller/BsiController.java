package com.hawk.ecom.svp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.svp.persist.domain.BsiPhoneModelDomain;
import com.hawk.ecom.svp.response.QueryPhoneBrandResponse;
import com.hawk.ecom.svp.response.QueryPhoneModelResponse;
import com.hawk.ecom.svp.service.BsiService;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/bsi")
public class BsiController {

	@Autowired
	private BsiService bsiService;

	@RequestMapping(value = "/brand", method = GET)
	public WebResponse<QueryPhoneBrandResponse> brand() {
		List<String> brandList = bsiService.queryPhoneBrand();

		WebResponse<QueryPhoneBrandResponse> response = SuccessResponse.build(new QueryPhoneBrandResponse(brandList));
		
		return response;
	}
	
	@RequestMapping(value = "/brand/{brand}", method = GET)
	public WebResponse<QueryPhoneModelResponse> modelOfBrand(@PathVariable String brand){
		List<BsiPhoneModelDomain> domainList = bsiService.queryPhoneModel(brand);
		List<QueryPhoneModelResponse.PhoneModel> phoneModelList = DomainTools.copy(domainList, QueryPhoneModelResponse.PhoneModel.class);
		WebResponse<QueryPhoneModelResponse> response = SuccessResponse.build(new QueryPhoneModelResponse(phoneModelList));
		return response;
	}

}
