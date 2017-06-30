package com.hawk.ecom.product.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.product.persist.domain.PicDomain;
import com.hawk.ecom.product.request.CreatePicParam;
import com.hawk.ecom.product.request.ListPicParam;
import com.hawk.ecom.product.request.LoadPicParam;
import com.hawk.ecom.product.request.RemovePicParam;
import com.hawk.ecom.product.response.PicInfoResponse;
import com.hawk.ecom.product.service.PicService;
import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/product/pic")
@CrossOrigin
public class PicAdminController {
	
	@Autowired
	private PicService picService;
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /mall/admin/product/pic controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<PicInfoResponse> createPic(HttpServletRequest request) throws Exception {
		CreatePicParam param = HttpRequestTools.parse(request, CreatePicParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		PicDomain picDomain = picService.createPic(param);
		return SuccessResponse.build(DomainTools.copy(picDomain, PicInfoResponse.class));
	}
	
	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MultiResponse<PicInfoResponse>> ListPic(HttpServletRequest request) throws Exception {
		ListPicParam param = HttpRequestTools.parse(request, ListPicParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		PagingQueryResultWrap<PicDomain> wrap = picService.listPic(param);
		MultiResponse<PicInfoResponse> result = new MultiResponse<PicInfoResponse>(MybatisTools.copy(wrap, PicInfoResponse.class));
		return SuccessResponse.build(result);
	}
	
	@RequestMapping(value = "/load/id/{id}", method = {GET,POST})
	public WebResponse<PicInfoResponse> loadPic (@PathVariable Integer id) throws Exception{
		LoadPicParam param = new LoadPicParam();
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		param.setId(id);
		return SuccessResponse.build(DomainTools.copy(picService.loadPic(param), PicInfoResponse.class));
	}
	
	@RequestMapping(value = "/remove", method = POST)
	public WebResponse<ResponseData> removePic(HttpServletRequest request) throws Exception {
		RemovePicParam param = HttpRequestTools.parse(request, RemovePicParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		picService.removePic(param);
		return SuccessResponse.build(null);
	}
}
