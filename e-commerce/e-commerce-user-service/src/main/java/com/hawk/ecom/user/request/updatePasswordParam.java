package com.hawk.ecom.user.request;

import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class updatePasswordParam {

	@NotEmpty(value="loginPwd",name="旧密码")
	private String oldPassword;
	
	@NotEmpty(value="loginPwd",name="新密码")
	private String newPassword;
}
