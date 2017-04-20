package com.hawk.ecom.user.service;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.user.constant.ConstLoginType;
import com.hawk.ecom.user.exception.UnMatchedPasswordRuntimeException;
import com.hawk.ecom.user.exception.UserNotFoundRuntimeException;
import com.hawk.ecom.user.persist.domain.LoginDomain;
import com.hawk.ecom.user.persist.domain.UserDomain;
import com.hawk.ecom.user.persist.mapper.LoginMapper;
import com.hawk.ecom.user.request.LoginParam;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class LoginService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LoginMapper loginMapper;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CacheService cacheService;
	
		
	@Valid
	public String login(@Valid LoginParam loginParam){
		return login(loginParam.getMobileNumber(),loginParam.getLoginPwd());
	}
	
	private String buildLongTokenKey(String token){
		return StringTools.concat("_","user","token",token);
	}
	
	public String login(String mobileNumber, String password){
		UserDomain userDomain = userService.queryUserByMobileNumber(mobileNumber);
		if (userDomain == null){
			throw new UserNotFoundRuntimeException();
		}
		password = userService.password(password, userDomain.getUserCode(), userDomain.getCreateDate());
		if (!password.equals(userDomain.getLoginPwd())){
			throw new UnMatchedPasswordRuntimeException();
		}
		
		LoginDomain loginDomain = new LoginDomain();
		
		Date curDate = new Date();
		
		loginDomain.setLoginDate(curDate);
		loginDomain.setLastAccessDate(curDate);
		loginDomain.setUserId(userDomain.getId());
		loginDomain.setUserCode(userDomain.getUserCode());
		loginDomain.setMobileNumber(mobileNumber);
		Date expireDate = DateTools.addMinutes(curDate, 240);
		loginDomain.setExpireDate(expireDate);
		
		
		loginDomain.setCreateDate(curDate);
		loginDomain.setUpdateDate(curDate);
		
		loginDomain.setLoginType(ConstLoginType.SHORT_TERM_STATIC);
		String token = UUID.randomUUID().toString();
		loginDomain.setToken(token);
		
		loginMapper.insert(loginDomain);
		
		CachedLoginToken cachedLoginToken = new CachedLoginToken();
		cachedLoginToken.setExpireDate(expireDate.getTime());
		cachedLoginToken.setMobileNumber(mobileNumber);
		cachedLoginToken.setUserCode(userDomain.getUserCode());
		cachedLoginToken.setUserId(userDomain.getId());
		
		String loginTokenKey = buildLongTokenKey(token);
		cacheService.put(loginTokenKey, cachedLoginToken, 240);
		
		return token;
	}

}