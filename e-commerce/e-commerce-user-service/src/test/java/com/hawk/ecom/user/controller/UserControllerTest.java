package com.hawk.ecom.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.hawk.ecom.user.request.RegisterUserParam;
import com.hawk.ecom.user.request.ResetPasswordParam;
import com.hawk.ecom.user.request.SsoParam;
import com.hawk.ecom.user.request.UpdatePasswordParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class UserControllerTest extends AbstractControllerTest {

	// @Test
	public void testHome() {
		String url = getUrl("/user/home");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));

		String result = httpExecutor.get(url, params);

		System.out.println("result=" + result);
	}

	@Test
	public void testRegister() {
		String mobileNumber = "13800000000";
		String veriCode = "7332";
		RegisterUserParam registerUserParam = new RegisterUserParam();
		registerUserParam.setMobileNumber(mobileNumber);
		registerUserParam.setVeriCode(veriCode);
		registerUserParam.setLoginPwd("123456");

		String url = getUrl("/user/register");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));

		System.out.println(JsonTools.toJsonString(registerUserParam));

		String result = httpExecutor.post(url, registerUserParam, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testUpdatePassword() {
		UpdatePasswordParam updatePasswordParam = new UpdatePasswordParam();
		String mobileNumber = "13411082481";
		String oldPassword = "123456";
		String newPassword = "124321";
		updatePasswordParam.setMobileNumber(mobileNumber);
		updatePasswordParam.setOldPassword(oldPassword);
		updatePasswordParam.setNewPassword(newPassword);

		String url = getUrl("/user/pwd/update");
		System.out.println(JsonTools.toJsonString(updatePasswordParam));

		String result = httpExecutor.post(url, updatePasswordParam, null);
		System.out.println("result=" + result);
	}

	// @Test
	public void testResetPassword() {
		ResetPasswordParam param = new ResetPasswordParam();
		String mobileNumber = "13411082481";
		String veriCode = "123456";
		String newPassword = "654321";
		param.setMobileNumber(mobileNumber);
		param.setNewPassword(newPassword);
		param.setVeriCode(veriCode);

		String url = getUrl("/user/pwd/reset");
		System.out.println(JsonTools.toJsonString(param));

		String result = httpExecutor.post(url, param, null);
		System.out.println("result=" + result);
	}

	// @Test
	public void testSso() {
		SsoParam ssoParam = new SsoParam();
		String appid = "580fb1dcd2ee427387cc580d545a6405";
		String appkey = "0865098d17844bf5978afafa8dc3d058";
		String mobileNumber = "13311658155";
		String version = "1.0";
		String timestamp = new Long(System.currentTimeMillis()).toString();
		ssoParam.setAppid(appid);
		ssoParam.setMobileNumber(mobileNumber);

		String url = getUrl("/user/sso");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("appid", appid));
		params.add(new HttpParam("version", version));
		params.add(new HttpParam("timestamp", timestamp));

		String reqhash = JsonTools.toJsonString(ssoParam) + version + timestamp + appid + appkey;
		reqhash = DigestUtils.md5Hex(reqhash);
		params.add(new HttpParam("reqhash", reqhash));

		System.out.println(JsonTools.toJsonString(ssoParam));

		String result = httpExecutor.post(url, ssoParam, params);
		System.out.println("result=" + result);
	}
}
