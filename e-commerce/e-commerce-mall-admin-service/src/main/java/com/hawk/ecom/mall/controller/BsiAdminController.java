package com.hawk.ecom.mall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.mall.request.BsiOrderDetailReportParam;
import com.hawk.ecom.mall.request.BsiStatCouponParam;
import com.hawk.ecom.mall.request.BsiStatOrderDetailParam;
import com.hawk.ecom.mall.service.BsiAdminService;
import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

@RestController
@RequestMapping("/mall/admin/svp/bsi")
@CrossOrigin
public class BsiAdminController {

	@Autowired
	private BsiAdminService bsiAdminService;

	@RequestMapping(value = "/report/orderDetail", method = POST)
	public WebResponse<MultiResponse<HashMap<String, Object>>> reportOrderDetail(HttpServletRequest request) throws Exception {

		BsiOrderDetailReportParam param = HttpRequestTools.parse(request, BsiOrderDetailReportParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		List<HashMap<String, Object>> list = bsiAdminService.reportOrderDetail(param);
		return SuccessResponse.build(new MultiResponse<HashMap<String, Object>>(list));
	}

	@RequestMapping(value = "/report/orderDetail/stat", method = POST)
	public WebResponse<MultiResponse<HashMap<String, Object>>> statOrderDetail(HttpServletRequest request) throws Exception {

		BsiStatOrderDetailParam param = HttpRequestTools.parse(request, BsiStatOrderDetailParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		List<HashMap<String, Object>> list = bsiAdminService.statBsiOrderDetail(param);
		return SuccessResponse.build(new MultiResponse<HashMap<String, Object>>(list));
	}

	@RequestMapping(value = "/report/coupon/stat", method = POST)
	public WebResponse<MultiResponse<HashMap<String, Object>>> statCoupon(HttpServletRequest request) throws Exception {

		BsiStatCouponParam param = HttpRequestTools.parse(request, BsiStatCouponParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		List<HashMap<String, Object>> list = bsiAdminService.statCoupon(param);
		return SuccessResponse.build(new MultiResponse<HashMap<String, Object>>(list));

	}
}
