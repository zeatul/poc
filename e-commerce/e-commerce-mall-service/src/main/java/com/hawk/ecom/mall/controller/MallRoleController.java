package com.hawk.ecom.mall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.mall.persist.domain.MallUserDomain;
import com.hawk.ecom.mall.request.MallAddAdminMembersParam;
import com.hawk.ecom.mall.request.MallListAdminMembersParam;
import com.hawk.ecom.mall.request.MallRemoveAdminMembersParam;
import com.hawk.ecom.mall.response.MultiUserInfoResponse;
import com.hawk.ecom.mall.response.MultiUserInfoResponse.UserInfo;
import com.hawk.ecom.mall.service.MallRoleService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/role")
@CrossOrigin
public class MallRoleController {

	@Autowired
	private MallRoleService mallRoleService;

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to mall role controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}

	@RequestMapping(value = "/member/admin/add", method = POST)
	public WebResponse<ResponseData> addAdminMembers(HttpServletRequest request) throws Exception {
		MallAddAdminMembersParam param = HttpRequestTools.parse(request, MallAddAdminMembersParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		mallRoleService.addAdminMembers(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/member/admin/remove", method = POST)
	public WebResponse<ResponseData> removeAdminMembers(HttpServletRequest request) throws Exception {
		MallRemoveAdminMembersParam param = HttpRequestTools.parse(request, MallRemoveAdminMembersParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		mallRoleService.removeAdminMembers(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/member/admin/list", method = POST)
	public WebResponse<ResponseData> listAdminMembers(HttpServletRequest request) throws Exception {
		MallListAdminMembersParam param = HttpRequestTools.parse(request, MallListAdminMembersParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		List<MallUserDomain> list = mallRoleService.listAdminMembers(param);
		MultiUserInfoResponse result = new MultiUserInfoResponse(DomainTools.copy(list, UserInfo.class));
		return SuccessResponse.build(result);
	}

}
