package com.hawk.ecom.muser.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.ecom.muser.exception.DuplicateMallUserRuntimeException;
import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.exception.MallForbiddenUserStatusRuntimeException;
import com.hawk.ecom.muser.exception.MallTokenInvalidRuntimeException;
import com.hawk.ecom.muser.exception.MallTokenLogoutRuntimeException;
import com.hawk.ecom.muser.exception.MallTokenTimeoutRuntimeException;
import com.hawk.ecom.muser.exception.MallUnMatchedPasswordRuntimeException;
import com.hawk.ecom.muser.exception.MallUserIsReservedRuntimeException;
import com.hawk.ecom.muser.exception.MallUserNotFoundRuntimeException;
import com.hawk.ecom.muser.constant.ConstMallLoginStatus;
import com.hawk.ecom.muser.constant.ConstMallLoginType;
import com.hawk.ecom.muser.constant.ConstMallUserStatus;
import com.hawk.ecom.muser.constant.ConstMallUserType;
import com.hawk.ecom.muser.persist.domain.MallLoginDomain;
import com.hawk.ecom.muser.persist.domain.MallUserDomain;
import com.hawk.ecom.muser.persist.domain.MallUserHistoryDomain;
import com.hawk.ecom.muser.persist.mapper.MallLoginMapper;
import com.hawk.ecom.muser.persist.mapper.MallUserHistoryMapper;
import com.hawk.ecom.muser.persist.mapper.MallUserMapper;
import com.hawk.ecom.muser.request.MallCreateUserParam;
import com.hawk.ecom.muser.request.MallListUserParam;
import com.hawk.ecom.muser.request.MallLoginParam;
import com.hawk.ecom.muser.request.MallRemoveUserParam;
import com.hawk.ecom.muser.request.MallResetPasswordParam;
import com.hawk.ecom.muser.request.MallUpdatePasswordParam;
import com.hawk.ecom.muser.request.MallUpdateUserParam;
import com.hawk.ecom.muser.request.MallUpdateUserStatusParam;
import com.hawk.ecom.sms.exception.UnMatchedVeriCodeRuntimeException;
import com.hawk.ecom.sms.service.SmsService;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class MallUserService {
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private MallUserMapper mallUserMapper;
	
	private MallUserHistoryMapper mallUserHistoryMapper;
	
	@Autowired
	private MallLoginMapper mallLoginMapper;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private MallAuthService authService;
	
	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;

	
	@Autowired
	@Qualifier("mallUserCodeSequenceService")
	private PkGenService mallUserCodeSequenceService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	private String password(String loginPwd,String userCode,Date date){
		final String key = "Mall@2#%Hawk";
		return DigestUtils.md5Hex(StringTools.concatWithSymbol(":", loginPwd,key,userCode,date.getTime()));
	}
	
	public static void main(String[] args){
		Date date = DateTools.parse("2017-05-24 00:00:00", DateTools.DATETIME_PATTERN);
		System.out.println(new MallUserService().password("hawk@1234","000002",date));
	}
	
	public MallUserDomain queryMallUserByUserCode(String userCode){
		if (StringTools.isNullOrEmpty(userCode))
			return null;
		MybatisParam params = new MybatisParam().put("userCode", userCode);
		return MybatisTools.single(mallUserMapper.loadDynamic(params));
	}
	
	public MallUserDomain loadMallUserByUserCode(String userCode){
		MallUserDomain mallUserDomain = queryMallUserByUserCode(userCode);
		if(mallUserDomain == null)
			throw new MallUserNotFoundRuntimeException();
		return mallUserDomain;
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
		if (mallUserDomain.getUserStatus() == ConstMallUserStatus.FORBIDDEN){
			throw new MallForbiddenUserStatusRuntimeException();
		}
		
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
		mallLoginDomain.setUserCode(mallUserDomain.getUserCode());
		mallLoginMapper.insert(mallLoginDomain);
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
			mallLoginMapper.updateWithoutNull(mallLoginDomain);
		}
	}
	
	private String buildMallUserLongTokenKey(String token){
		return StringTools.concat("_","mall","user","token",token);
	}
	
	public MallUserDomain loginInfo(String token){
		if (StringTools.isNullOrEmpty(token)){
			return null;
		}
		MallUserDomain mallUserDomain = null;
		String loginTokenKey = buildMallUserLongTokenKey(token);
		MallCachedLoginToken cachedMallLoginToken = cacheService.get(loginTokenKey, MallCachedLoginToken.class);
		if (cachedMallLoginToken!=null){
			mallUserDomain = new MallUserDomain();
			mallUserDomain.setMobileNumber(cachedMallLoginToken.getMobileNumber());
			mallUserDomain.setUserCode(cachedMallLoginToken.getUserCode());
			mallUserDomain.setUserName(cachedMallLoginToken.getUserName());
			mallUserDomain.setId(cachedMallLoginToken.getUserId());
		}else{		
			MallLoginDomain mallLoginDomain = mallLoginMapper.load(token);
			if (mallLoginDomain == null){
				throw new MallTokenInvalidRuntimeException();
			}
			
			if (mallLoginDomain.getLoginStatus() == ConstMallLoginStatus.LOGOUT){
				throw new MallTokenLogoutRuntimeException();
			}
			
			cachedMallLoginToken = new MallCachedLoginToken();
			cachedMallLoginToken.setExpireDate(mallLoginDomain.getExpireDate().getTime());
			cachedMallLoginToken.setMobileNumber(mallLoginDomain.getMobileNumber());
			cachedMallLoginToken.setUserCode(mallLoginDomain.getUserCode());
			cachedMallLoginToken.setUserId(mallLoginDomain.getUserId());
			
			mallUserDomain = mallUserMapper.load(mallLoginDomain.getUserId());
			if (mallUserDomain == null)
				throw new MallUserNotFoundRuntimeException();
			
			cachedMallLoginToken.setUserName(mallUserDomain.getUserName());
			
			cacheService.put(loginTokenKey, cachedMallLoginToken, 240);
		}
		
		if (System.currentTimeMillis() >= cachedMallLoginToken.getExpireDate()){
			throw new MallTokenTimeoutRuntimeException();
		}
		
		
		return mallUserDomain;
	}
	
	
	private final static String MUSER_CODE_HEAD = "msr";
	private String generateMallUserCode() {
		/**
		 * 8位及8位以上的数字构成的字符串
		 */
		return StringTools.concat(MUSER_CODE_HEAD,mallUserCodeSequenceService.genPk());

	}
	
	/**
	 * 创建商城用户
	 * @param mallCreateUserParam
	 */
	@Valid
	public MallUserDomain createUser(@Valid MallCreateUserParam mallCreateUserParam){
		
		
		if (!authService.hasAnyRole(mallCreateUserParam.getOperatorCode(), Arrays.asList("superadmin","admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		logger.info("Start to create user, mobileNumber={}",mallCreateUserParam.getMobileNumber());
		MallUserDomain mallUserDomain = new MallUserDomain();
		Date curDate = new Date();
		mallUserDomain.setCreateDate(curDate);
		mallUserDomain.setCreateUserCode(mallCreateUserParam.getOperatorCode());		
		mallUserDomain.setIdNumber(mallCreateUserParam.getIdNumber());
		mallUserDomain.setIdType(mallCreateUserParam.getIdType());
		
		mallUserDomain.setMobileNumber(mallCreateUserParam.getMobileNumber());
		mallUserDomain.setUpdateDate(curDate);
		mallUserDomain.setUpdateUserCode(mallCreateUserParam.getOperatorCode());
		
		mallUserDomain.setUserBirthday(null);
		
		mallUserDomain.setIsReserved(0);
		
		
		mallUserDomain.setUserName(mallCreateUserParam.getUserName());
		mallUserDomain.setUserSex(mallCreateUserParam.getUserSex());
		mallUserDomain.setUserStatus(ConstMallUserStatus.NORMAL);
		mallUserDomain.setUserType(ConstMallUserType.NORMAL);
		
		String mallUserCode = generateMallUserCode();
		mallUserDomain.setUserEmail(StringTools.concat(mallUserCode, "@email"));
		mallUserDomain.setUserAccount(StringTools.concat(mallUserCode, "@account"));
		String loginPwd = UUID.randomUUID().toString().replaceAll("-", "").substring(5, 15)+"@Hawk.master";
		loginPwd = password(loginPwd, mallUserCode, curDate);
		mallUserDomain.setLoginPwd(loginPwd);
		mallUserDomain.setUserCode(mallUserCode);
		mallUserDomain.setId(pkGenService.genPk());
		
		try {
			mallUserMapper.insert(mallUserDomain);
		} catch (DuplicateKeyException ex) {
			throw new DuplicateMallUserRuntimeException();
		}
		
		return mallUserDomain;
	}
	
	@Valid
	public void updateUser(@NotNull("参数") @Valid MallUpdateUserParam mallUpdateUserParam){
		if (!authService.hasAnyRole(mallUpdateUserParam.getOperatorCode(), Arrays.asList("superadmin","admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		MallUserDomain mallUserDomain = queryMallUserByUserCode(mallUpdateUserParam.getUserCode());
		
		if (mallUserDomain  == null){
			throw new MallUserNotFoundRuntimeException();
		}
		
		MallUserDomain update = new MallUserDomain();
		update.setId(mallUserDomain.getId());
		update.setUserName(mallUpdateUserParam.getUserName());
		update.setUserSex(mallUpdateUserParam.getUserSex());
		update.setUserBirthday(mallUpdateUserParam.getUserBirthday());
		update.setUpdateDate(new Date());
		update.setUpdateUserCode(mallUpdateUserParam.getOperatorCode());
		mallUserMapper.updateWithoutNull(update);
	}
	
	@Valid
	public List<MallUserDomain> listMallUser(@NotNull("参数") @Valid MallListUserParam mallListUserParam){
		if (!authService.hasAnyRole(mallListUserParam.getOperatorCode(), Arrays.asList("superadmin","admin"))){
			throw new IllegalAccessRuntimeException();
		}
		if (StringTools.isNullOrEmpty(mallListUserParam.getOrder())){
			mallListUserParam.setOrder("create_date desc");
		}
		MybatisParam params = MybatisTools.page(new MybatisParam(), mallListUserParam);
		return mallUserMapper.loadDynamicPaging(params);
	}
	
	/**
	 * 创建商城用户
	 * @param mallCreateUserParam
	 */
	@Valid
	@Transactional
	public void removeUser(@Valid MallRemoveUserParam mallRemoveUserParam){
		if (!authService.hasAnyRole(mallRemoveUserParam.getOperatorCode(), Arrays.asList("superadmin","admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		for (String userCode : mallRemoveUserParam.getUserCodes()){
			MallUserDomain mallUserDomain = loadMallUserByUserCode(userCode);
			if (ConstBoolean.parse(mallUserDomain.getIsReserved())){
				throw new MallUserIsReservedRuntimeException();
			}
			
			/**
			 * 转入历史库
			 */
			MallUserHistoryDomain mallUserHistoryDomain = new MallUserHistoryDomain();
			DomainTools.copy(mallUserDomain, mallUserHistoryDomain);
			
			mallUserHistoryMapper.insert(mallUserHistoryDomain);
			
			/**
			 * 删除
			 */
			mallUserMapper.delete(mallUserDomain.getId());
		}
	}
	
	@Valid
	public void updateUserStatus(@NotNull("参数") @Valid MallUpdateUserStatusParam mallUpdateUserStatusParam){
		if (!authService.hasAnyRole(mallUpdateUserStatusParam.getOperatorCode(), Arrays.asList("superadmin","admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		for (String userCode : mallUpdateUserStatusParam.getUserCodes()){
			MallUserDomain mallUserDomain = queryMallUserByUserCode(userCode);
			
			if (mallUserDomain  == null){
				throw new MallUserNotFoundRuntimeException();
			}
			
			MallUserDomain update = new MallUserDomain();
			update.setId(mallUserDomain.getId());
			update.setUserStatus(mallUpdateUserStatusParam.getUserStatus());
			update.setUpdateDate(new Date());
			update.setUpdateUserCode(mallUpdateUserStatusParam.getOperatorCode());
			mallUserMapper.updateWithoutNull(update);
		}
	}
	
}
