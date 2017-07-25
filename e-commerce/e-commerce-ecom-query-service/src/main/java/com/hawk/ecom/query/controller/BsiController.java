package com.hawk.ecom.query.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.base.persist.domain.BsiPhoneModelDomain;
import com.hawk.ecom.base.service.BsiService;
import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.query.response.PhoneModel;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/ecom/query/bsi")
@CrossOrigin
@Controller
/**
 * 碎屏险
 * 
 * @author zhangpeng.hawk
 *
 */
public class BsiController {

	@Autowired
	private BsiService bsiService;

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/query/bsi controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}

	/**
	 * 
	 * @return 所有手机型号
	 */
	@RequestMapping(value = "/phone/brand", method = {GET,POST})
	public WebResponse<MultiResponse<String>> brand() {
		List<String> brandList = bsiService.queryAllPhoneBrand();

		return SuccessResponse.build(new MultiResponse<String>(brandList));
	}
	
	@RequestMapping(value = "/phone/model/brand/{brand}", method = {GET,POST})
	public WebResponse<MultiResponse<PhoneModel>> modelOfBrand(@PathVariable String brand) throws Exception{
		
		List<BsiPhoneModelDomain> domainList = bsiService.queryPhoneModel(brand);
		List<PhoneModel> phoneModelList = DomainTools.copy(domainList, PhoneModel.class);
		WebResponse<MultiResponse<PhoneModel>> response = SuccessResponse.build(new MultiResponse<PhoneModel>(phoneModelList));
		return response;
		
	}

}
