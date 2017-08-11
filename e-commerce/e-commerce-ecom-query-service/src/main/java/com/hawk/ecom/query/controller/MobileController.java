package com.hawk.ecom.query.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.base.persist.domainex.MobileNumberSegmentExDomain;
import com.hawk.ecom.base.service.MobileNumberSegmentService;
import com.hawk.ecom.query.request.LoadChargeDataProductParam;
import com.hawk.ecom.query.response.LoadChargeDataProductResponse;
import com.hawk.ecom.query.response.MobileNumberSegmentResponse;
import com.hawk.ecom.query.service.ProductQueryService;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

@RestController
@RequestMapping("/ecom/query/mobile")
@CrossOrigin
/**
 * 移动相关
 * @author zhangpeng.hawk
 *
 */
public class MobileController {
	
	@Autowired
	private MobileNumberSegmentService mobileNumberSegmentService;
	
	@Autowired
	private ProductQueryService productService;
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/query/mobile controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	
	

	@RequestMapping(value = "/segment/mobileNumber/{mobileNumber}", method = {POST,GET})
	public WebResponse<MobileNumberSegmentResponse> loadMobileNumberSegment(@PathVariable String mobileNumber) throws Exception{
		MobileNumberSegmentExDomain mobileNumberSegmentExDomain = mobileNumberSegmentService.queryMobileNumberSegment(mobileNumber);
		if (mobileNumberSegmentExDomain == null){
			throw new RuntimeException("未找到手机号对应的运营商与地区信息");
		}		
		MobileNumberSegmentResponse result = DomainTools.copy(mobileNumberSegmentExDomain, MobileNumberSegmentResponse.class);
		result.setMobileNumber(mobileNumber);
		return SuccessResponse.build(result);
	}
	
	
	/**
	 * 查询流量充值产品
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sku/chargeData/mobileNumber/{mobileNumber}/regionType/{regionType}", method = {POST,GET})
	public WebResponse<LoadChargeDataProductResponse> loadChargeDataProduct(@PathVariable String mobileNumber,@PathVariable String regionType) throws Exception{
		LoadChargeDataProductParam param = new LoadChargeDataProductParam();
		MobileNumberSegmentExDomain mobileNumberSegmentExDomain = mobileNumberSegmentService.queryMobileNumberSegment(mobileNumber);
		if (mobileNumberSegmentExDomain == null){
			throw new RuntimeException("未找到手机号对应的运营商与地区信息");
		}
		param.setRegionType(regionType);
		param.setOperator(mobileNumberSegmentExDomain.getMobileOperatorCode());
		param.setProvince(mobileNumberSegmentExDomain.getProvinceCode());
		
		LoadChargeDataProductResponse loadChargeDataProductResponse = new LoadChargeDataProductResponse();
		loadChargeDataProductResponse.setMobileOperator(mobileNumberSegmentExDomain.getMobileOperator());
		loadChargeDataProductResponse.setMobileOperatorCode(mobileNumberSegmentExDomain.getMobileOperatorCode());
		loadChargeDataProductResponse.setProvince(mobileNumberSegmentExDomain.getProvince());
		loadChargeDataProductResponse.setProvinceCode(mobileNumberSegmentExDomain.getProvinceCode());
		loadChargeDataProductResponse.setSkus(productService.loadChargeDataProduct(param));
		
		return SuccessResponse.build(loadChargeDataProductResponse);
	}
}
