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
import com.hawk.ecom.mall.request.MallAddRoleMembersParam;
import com.hawk.ecom.mall.request.MallListRoleMembersParam;
import com.hawk.ecom.mall.request.MallRemoveRoleMembersParam;
import com.hawk.ecom.mall.response.MallUserInfoResponse;
import com.hawk.ecom.mall.response.MallMultiUserInfoResponse;
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

	@RequestMapping(value = "/member/add", method = POST)
	public WebResponse<ResponseData> addAdminMembers(HttpServletRequest request) throws Exception {
		MallAddRoleMembersParam param = HttpRequestTools.parse(request, MallAddRoleMembersParam.class);
		param.setRoleCode("admin");
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		mallRoleService.addRoleMembers(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/member/remove", method = POST)
	public WebResponse<ResponseData> removeAdminMembers(HttpServletRequest request) throws Exception {
		MallRemoveRoleMembersParam param = HttpRequestTools.parse(request, MallRemoveRoleMembersParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		param.setRoleCode("admin");
		mallRoleService.removeRoleMembers(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/member/list", method = POST)
	public WebResponse<ResponseData> listAdminMembers(HttpServletRequest request) throws Exception {
		MallListRoleMembersParam param = HttpRequestTools.parse(request, MallListRoleMembersParam.class);
		param.setRoleCode("admin");
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		List<MallUserDomain> list = mallRoleService.listRoleMembers(param);
		MallMultiUserInfoResponse result = new MallMultiUserInfoResponse(DomainTools.copy(list, MallUserInfoResponse.class));
		return SuccessResponse.build(result);
	}

}
