package com.hawk.ecom.mall.service;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hawk.ecom.mall.constant.ConstMallLoginStatus;
import com.hawk.ecom.mall.constant.ConstMallLoginType;
import com.hawk.ecom.mall.exception.MallTokenInvalidRuntimeException;
import com.hawk.ecom.mall.exception.MallTokenLogoutRuntimeException;
import com.hawk.ecom.mall.exception.MallTokenTimeoutRuntimeException;
import com.hawk.ecom.mall.exception.MallUserNotFoundRuntimeException;
import com.hawk.ecom.mall.exception.MallUnMatchedPasswordRuntimeException;
import com.hawk.ecom.mall.persist.domain.MallLoginDomain;
import com.hawk.ecom.mall.persist.domain.MallUserDomain;
import com.hawk.ecom.mall.persist.mapper.MallLoginMapper;
import com.hawk.ecom.mall.persist.mapper.MallUserMapper;
import com.hawk.ecom.mall.request.MallCreateUserParam;
import com.hawk.ecom.mall.request.MallLoginParam;
import com.hawk.ecom.mall.request.MallResetPasswordParam;
import com.hawk.ecom.mall.request.MallUpdatePasswordParam;
import com.hawk.ecom.mall.response.MallUserInfoResponse;
import com.hawk.ecom.sms.exception.UnMatchedVeriCodeRuntimeException;
import com.hawk.ecom.sms.service.SmsService;
import com.hawk.ecom.user.exception.MobileNumberRegisteredRuntimeException;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class MallUserService {
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private MallUserMapper mallUserMapper;
	
	@Autowired
	private MallLoginMapper mallLoginMapper;
	
	@Autowired
	private CacheService cacheService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public String password(String loginPwd,String userCode,Date date){
		final String key = "Mall@2#%Hawk";
		return DigestUtils.md5Hex(StringTools.concatWithSymbol(":", loginPwd,key,userCode,date.getTime()));
	}
	
	public MallUserDomain queryMallUserByMobileNumber(String mobileNumber){
		MybatisParam params = new MybatisParam().put("mobileNumber", mobileNumber);
		return MybatisTools.single(mallUserMapper.loadDynamic(params));
	}
	
	private MallUserDomain checkPassword(String mobileNumber, String password){
		MallUserDomain mallUserDomain = queryMallUserByMobileNumber(mobileNumber);
		if (mallUserDomain == null){
			throw new MallUserNotFoundRuntimeException();
		}		
		
		password = password(password, mallUserDomain.getUserCode(), mallUserDomain.getCreateDate());
		
		if (!password.equals(mallUserDomain.getLoginPwd())){
			/**
			 * TODO:连续登陆n次,24小时，禁止登陆
			 */
			throw new MallUnMatchedPasswordRuntimeException();
		}
		return mallUserDomain;
	}
	
	@Valid
	public void resetPassword(@Valid MallResetPasswordParam mallResetPasswordParam){
		String veriCode = mallResetPasswordParam.getVeriCode();		
		String mobileNumber = mallResetPasswordParam.getMobileNumber();
		String newPassword = mallResetPasswordParam.getNewPassword();
		if (!smsService.checkVeriCode(mobileNumber, veriCode)){
			throw new UnMatchedVeriCodeRuntimeException();
		}
		
		MallUserDomain mallUserDomain = queryMallUserByMobileNumber(mobileNumber);
		if (mallUserDomain == null){
			throw new MallUserNotFoundRuntimeException();
		}
		
		/**
		 * 更新密码
		 */
		updatePassword(mallUserDomain,newPassword);
	}
	
	@Valid
	public void updatePassword(@Valid MallUpdatePasswordParam  mallUpdatePasswordParam){
		String mobileNumber = mallUpdatePasswordParam.getMobileNumber();
		String oldPassword = mallUpdatePasswordParam.getOldPassword();
		String newPassword = mallUpdatePasswordParam.getNewPassword();
		MallUserDomain mallUserDomain = checkPassword(mobileNumber, oldPassword);
		
		updatePassword(mallUserDomain , newPassword);
	}
	
	private void updatePassword(MallUserDomain mallUserDomain , String newPassword){
		MallUserDomain updatDomain =  new MallUserDomain();
		
		updatDomain.setLoginPwd(password(newPassword,mallUserDomain.getUserCode(),mallUserDomain.getCreateDate()));
		updatDomain.setUpdateDate(new Date());
		updatDomain.setLastAccessDate(updatDomain.getUpdateDate());
		updatDomain.setId(mallUserDomain.getId());
		mallUserMapper.updateWithoutNull(updatDomain);
	}
	
	@Valid
	public String login(@Valid MallLoginParam mallLoginParam){
		MallUserDomain mallUserDomain = checkPassword(mallLoginParam.getMobileNumber(),mallLoginParam.getLoginPwd());
		MallLoginDomain mallLoginDomain = new MallLoginDomain();
		Date curDate = new Date();
		mallLoginDomain.setCreateDate(curDate);
		mallLoginDomain.setDeviceBrand(null);
		mallLoginDomain.setDeviceModel(null);
		mallLoginDomain.setDurationSecond(4*3600);
		Date expireDate = DateTools.addMinutes(curDate, 240);
		mallLoginDomain.setExpireDate(expireDate);
		mallLoginDomain.setImei(null);
		mallLoginDomain.setLastAccessDate(curDate);
		mallLoginDomain.setLoginChannel(mallLoginParam.getLoginChannel());
		mallLoginDomain.setLoginDate(curDate);
		mallLoginDomain.setLoginIp(mallLoginParam.getLoginIp());
		mallLoginDomain.setLoginStatus(ConstMallLoginStatus.NORMAL);
		mallLoginDomain.setLoginType(ConstMallLoginType.SHORT_TERM_STATIC);
		mallLoginDomain.setMobileNumber(mallUserDomain.getMobileNumber());
		mallLoginDomain.setOperatingSystem(null);
		mallLoginDomain.setToken(UUID.randomUUID().toString());
		mallLoginDomain.setUpdateDate(curDate);
		mallLoginDomain.setUserAccount(mallUserDomain.getUserAccount());
		mallLoginDomain.setUserAgent(mallLoginParam.getUserAgent());
		mallLoginDomain.setUserEmail(mallUserDomain.getUserEmail());
		mallLoginDomain.setUserId(mallUserDomain.getId());
		mallUserMapper.insert(mallUserDomain);
		return mallLoginDomain.getToken();
	}
	
	public void logout (String token){
		if (StringTools.isNullOrEmpty(token)){
			return ;
		}
		
		String mallLoginTokenKey = buildMallUserLongTokenKey(token);
		cacheService.delete(mallLoginTokenKey);
		
		MallLoginDomain mallLoginDomain = mallLoginMapper.load(token);
		
		if (mallLoginDomain == null)
			return;
		
		if (mallLoginDomain.getLoginStatus() == ConstMallLoginStatus.NORMAL){
			mallLoginDomain.setLoginStatus(ConstMallLoginStatus.LOGOUT);
			mallLoginDomain.setUpdateDate(new Date());
			mallLoginMapper.update(mallLoginDomain);
		}
	}
	
	private String buildMallUserLongTokenKey(String token){
		return StringTools.concat("_","mall","user","token",token);
	}
	
	public MallUserInfoResponse loginInfo(String token){
		if (StringTools.isNullOrEmpty(token)){
			return null;
		}
		String loginTokenKey = buildMallUserLongTokenKey(token);
		CachedMallLoginToken cachedMallLoginToken = cacheService.get(loginTokenKey, CachedMallLoginToken.class);
		if (cachedMallLoginToken!=null){
			
		}else{		
			MallLoginDomain mallLoginDomain = mallLoginMapper.load(token);
			if (mallLoginDomain == null){
				throw new MallTokenInvalidRuntimeException();
			}
			
			if (mallLoginDomain.getLoginStatus() == ConstMallLoginStatus.LOGOUT){
				throw new MallTokenLogoutRuntimeException();
			}
			
			cachedMallLoginToken = new CachedMallLoginToken();
			cachedMallLoginToken.setExpireDate(mallLoginDomain.getExpireDate().getTime());
			cachedMallLoginToken.setMobileNumber(mallLoginDomain.getMobileNumber());
			cachedMallLoginToken.setUserCode(mallLoginDomain.getUserCode());
			cachedMallLoginToken.setUserId(mallLoginDomain.getUserId());
			
			MallUserDomain mallUserDomain = mallUserMapper.load(mallLoginDomain.getUserId());
			if (mallUserDomain == null)
				throw new MallUserNotFoundRuntimeException();
			
			cachedMallLoginToken.setUserName(mallUserDomain.getUserName());
			
			cacheService.put(loginTokenKey, cachedMallLoginToken, 240);
		}
		
		if (System.currentTimeMillis() >= cachedMallLoginToken.getExpireDate()){
			throw new MallTokenTimeoutRuntimeException();
		}
		
		MallUserInfoResponse mallUserInfoResponse = new MallUserInfoResponse();
		mallUserInfoResponse.setMobileNumber(cachedMallLoginToken.getMobileNumber());
		mallUserInfoResponse.setUserCode(cachedMallLoginToken.getUserCode());
		mallUserInfoResponse.setUserId(cachedMallLoginToken.getUserId());
		mallUserInfoResponse.setUserName(cachedMallLoginToken.getUserName());
		return mallUserInfoResponse;
	}
	
	/**
	 * 控制权限
	 * @param mallCreateUserParam
	 */
	@Valid
	public void createUser(@Valid MallCreateUserParam mallCreateUserParam){
		logger.info("Start to create user, mobileNumber={}",mallCreateUserParam.getMobileNumber());
		MallUserDomain mallUserDomain = new MallUserDomain();
		Date curDate = new Date();
		mallUserDomain.setCreateDate(curDate);
		mallUserDomain.setCreateUserCode(mallCreateUserParam.getOperatorCode());
		
		mallUserDomain.setIdNumber(idNumber);
		mallUserDomain.setIdType(idType);
		mallUserDomain.setLoginPwd(loginPwd);
		mallUserDomain.setMobileNumber(mobileNumber);
		mallUserDomain.setUpdateDate(updateDate);
		mallUserDomain.setUpdateUserCode(updateUserCode);
		mallUserDomain.setUserAccount(userAccount);
		mallUserDomain.setUserBirthday(userBirthday);
		
		mallUserDomain.setUserEmail(userEmail);
		mallUserDomain.setUserName(userName);
		mallUserDomain.setUserSex(userSex);
		mallUserDomain.setUserStatus(userStatus);
		
		mallUserDomain.setUserCode(userCode);
		mallUserDomain.setId(id);
		
		try {
			userMapper.insert(userDomain);
		} catch (DuplicateKeyException ex) {
			throw new MobileNumberRegisteredRuntimeException();
		}
	}
	
}
