package com.hawk.ecom.user.service;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.user.constant.ConstLoginStatus;
import com.hawk.ecom.user.constant.ConstLoginType;
import com.hawk.ecom.user.exception.TokenInvalidRuntimeException;
import com.hawk.ecom.user.exception.TokenLogoutRuntimeException;
import com.hawk.ecom.user.exception.TokenTimeoutRuntimeException;
import com.hawk.ecom.user.exception.UnMatchedPasswordRuntimeException;
import com.hawk.ecom.user.exception.UserNotFoundRuntimeException;
import com.hawk.ecom.user.persist.domain.LoginDomain;
import com.hawk.ecom.user.persist.domain.UserDomain;
import com.hawk.ecom.user.persist.mapper.LoginMapper;
import com.hawk.ecom.user.request.LoginParam;
import com.hawk.ecom.user.response.UserInfoResponse;
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
	
	private String ssoPwdKey(String mobileNumber){
		return StringTools.concatWithSymbol(":", getClass().getName(),"sso",mobileNumber);
	}
	
	public void cacheSsoPassword(String mobileNumber ,String password){
		String key = ssoPwdKey(mobileNumber);
		cacheService.put(key, password, 60);
	}
	
	public String cachedSsoPassword(String mobileNumber){
		String key = ssoPwdKey(mobileNumber);
		return cacheService.get(key, String.class);
	}
	
	public UserInfoResponse loginInfo(String token){
		if (StringTools.isNullOrEmpty(token)){
//			throw new TokenEmptyException();
			return null;
		}
		String loginTokenKey = buildUserLoginTokenKey(token);
		CachedLoginToken cachedLoginToken = cacheService.get(loginTokenKey, CachedLoginToken.class);
		if (cachedLoginToken!=null){
			
		}else{		
			LoginDomain loginDomain = loginMapper.load(token);
			if (loginDomain == null){
				throw new TokenInvalidRuntimeException();
			}
			
			if (loginDomain.getLoginStatus() == ConstLoginStatus.LOGOUT){
				throw new TokenLogoutRuntimeException();
			}
			
			cachedLoginToken = new CachedLoginToken();
			cachedLoginToken.setExpireDate(loginDomain.getExpireDate().getTime());
			cachedLoginToken.setMobileNumber(loginDomain.getMobileNumber());
			cachedLoginToken.setUserCode(loginDomain.getUserCode());
			cachedLoginToken.setUserId(loginDomain.getUserId());
			cacheService.put(loginTokenKey, cachedLoginToken, 240);
		}
		
		if (System.currentTimeMillis() >= cachedLoginToken.getExpireDate()){
			throw new TokenTimeoutRuntimeException();
		}
		
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		userInfoResponse.setMobileNumber(cachedLoginToken.getMobileNumber());
		userInfoResponse.setUserCode(cachedLoginToken.getUserCode());
		return userInfoResponse;
	}
	
		
	@Valid
	public String login(@Valid LoginParam loginParam){
		return login(loginParam.getMobileNumber(),loginParam.getLoginPwd(),loginParam.getLoginIp(),loginParam.getUserAgent(),loginParam.getLoginChannel());
	}
	
	public void logout (String token){
		if (StringTools.isNullOrEmpty(token)){
			return ;
		}
		
		String loginTokenKey = buildUserLoginTokenKey(token);
		cacheService.delete(loginTokenKey);
		
		LoginDomain loginDomain = loginMapper.load(token);
		
		if (loginDomain == null)
			return;
		
		if (loginDomain.getLoginStatus() == ConstLoginStatus.NORMAL){
			loginDomain.setLoginStatus(ConstLoginStatus.LOGOUT);
			loginDomain.setUpdateDate(new Date());
			loginMapper.update(loginDomain);
		}
	}
	
	private String buildUserLoginTokenKey(String token){
		return StringTools.concat("_","user","token",token);
	}
	
	public UserDomain checkPassword(String mobileNumber, String password){
		UserDomain userDomain = userService.queryUserByMobileNumber(mobileNumber);
		if (userDomain == null){
			throw new UserNotFoundRuntimeException();
		}
		
		/**
		 * sso临时密码判断
		 */
		if (password.length() == UUID.randomUUID().toString().replaceAll("-", "").length()){
			String cachedPassword = cachedSsoPassword(mobileNumber);
			if (password.equals(cachedPassword)){
				return userDomain;
			}
		}
		
		password = userService.password(password, userDomain.getUserCode(), userDomain.getCreateDate());
		if (!password.equals(userDomain.getLoginPwd())){
			/**
			 * TODO:连续登陆n次,24小时，禁止登陆
			 */
			throw new UnMatchedPasswordRuntimeException();
		}
		return userDomain;
	}
	
	public String login(String mobileNumber, String password,String loginIp ,String userAgent,String loginChannel){
		UserDomain userDomain = checkPassword(mobileNumber,password);
		
		LoginDomain loginDomain = new LoginDomain();
		
		Date curDate = new Date();
		
		loginDomain.setLoginDate(curDate);
		loginDomain.setLastAccessDate(curDate);
		loginDomain.setUserId(userDomain.getId());
		loginDomain.setUserCode(userDomain.getUserCode());
		loginDomain.setMobileNumber(mobileNumber);
		loginDomain.setLoginIp(loginIp);
		loginDomain.setUserAgent(userAgent);
		loginDomain.setLoginChannel(loginChannel);
		loginDomain.setLoginStatus(ConstLoginStatus.NORMAL);
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
		
		String loginTokenKey = buildUserLoginTokenKey(token);
		cacheService.put(loginTokenKey, cachedLoginToken, 240);
		
		logger.info("{} login success",mobileNumber);
		
		return token;
	}

}
