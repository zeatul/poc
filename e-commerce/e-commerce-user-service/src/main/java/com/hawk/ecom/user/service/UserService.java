package com.hawk.ecom.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.pub.constant.ConstBoolean;
import com.hawk.ecom.user.persist.domain.UserDomain;
import com.hawk.ecom.user.persist.mapper.UserMapper;
import com.hawk.ecom.user.request.CreateUserParam;
import com.hawk.framework.dic.validation.annotation.Valid;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Valid
	public void createUser(@Valid CreateUserParam createUserParam){
		UserDomain userDomain = new UserDomain();
		Date date = new Date();
		
		userDomain.setCreateDate(date);		
		userDomain.setIsEmailVerified(ConstBoolean.FALSE);
		userDomain.setIsMobileVerified(ConstBoolean.TRUE);
		userDomain.setLastAccessDate(date);
		
		userDomain.setLoginPwd(loginPwd);
		
		userDomain.setMobileNumber(mobileNumber);
		userDomain.setRegisterChannel(registerChannel);
		userDomain.setRegisterIp(registerIp);
		userDomain.setUpdateDate(updateDate);
		userDomain.setUserAccount(userAccount);
		userDomain.setUserActiveness(userActiveness);
		userDomain.setUserAgent(userAgent);
		userDomain.setUserCode(userCode);
		userDomain.setUserEmail(userEmail);
		userDomain.setUserLevel(userLevel);
		userDomain.setUserName(userName);
		userDomain.setUserNickname(userNickname);
		userDomain.setUserSex(userSex);
		userDomain.setUserStatus(userStatus);
		userDomain.setUserStatusChangeDate(userStatusChangeDate);
		
		userDomain.setId(id);
	}

}
