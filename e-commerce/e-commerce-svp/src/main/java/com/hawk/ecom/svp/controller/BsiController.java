package com.hawk.ecom.svp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.svp.response.QueryBrandResponse;
import com.hawk.ecom.svp.service.BsiService;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

@RestController
@RequestMapping("/bsi")
public class BsiController {
	
	@Autowired
	private BsiService bsiService;
	
	@RequestMapping(value = "/brand", method = GET)
	public WebResponse<QueryBrandResponse> brand(){
		List<String> brandList =  bsiService.queryBrand();
		
		WebResponse<QueryBrandResponse> response = SuccessResponse.
	}

}
