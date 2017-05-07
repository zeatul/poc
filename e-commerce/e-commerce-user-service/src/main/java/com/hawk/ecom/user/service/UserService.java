package com.hawk.ecom.user.service;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hawk.ecom.sms.service.SmsService;
import com.hawk.ecom.user.constant.ConstRegisterChannel;
import com.hawk.ecom.user.constant.ConstUserStatus;
import com.hawk.ecom.user.exception.MobileNumberRegisteredRuntimeException;
import com.hawk.ecom.user.exception.UnMatchedVeriCodeRuntimeException;
import com.hawk.ecom.user.exception.UserNotFoundRuntimeException;
import com.hawk.ecom.user.persist.domain.UserDomain;
import com.hawk.ecom.user.persist.mapper.UserMapper;
import com.hawk.ecom.user.request.CreateUserParam;
import com.hawk.ecom.user.request.ResetPasswordParam;
import com.hawk.ecom.user.request.SsoParam;
import com.hawk.ecom.user.request.UpdatePasswordParam;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;

	@Autowired
	@Qualifier("userCodeSequenceService")
	private PkGenService userCodeSequenceService;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private UserMapper userMapper;
	
	

	public UserDomain queryUserByMobileNumber(String mobileNumber) {
		MybatisParam params = new MybatisParam().put("mobileNumber", mobileNumber);
		return MybatisTools.single(userMapper.loadDynamic(params));
	}
	
	public String password(String loginPwd,String userCode,Date curDate){
		return DigestUtils.md5Hex(StringTools.concatWithSymbol(":", loginPwd,userCode,curDate.getTime()));
	}
	
	@Valid
	public String sso(@Valid SsoParam ssoParam){
		String mobileNumber = ssoParam.getMobileNumber();
		String password = UUID.randomUUID().toString().replaceAll("-", "");
		String registerIp = ssoParam.getRegisterIp();
		String userAgent = ssoParam.getUserAgent();
		String registerChannel = ConstRegisterChannel.SSO + "_" + ssoParam.getAppid();
		
		UserDomain userDomain = queryUserByMobileNumber(mobileNumber);
		
		if (userDomain == null){
			CreateUserParam createUserParam = new CreateUserParam();
			password = password.substring(0, 16);
			createUserParam.setMobileNumber(mobileNumber);
			createUserParam.setRegisterIp(registerIp);
			createUserParam.setRegisterChannel(registerChannel);
			createUserParam.setUserAgent(userAgent);
			createUserParam.setLoginPwd(password);
			createUser(createUserParam);
		}else{
			loginService.cacheSsoPassword(mobileNumber,password);
		}
		return loginService.login(mobileNumber, password, registerIp, userAgent);
	}

	@Valid
	public void createUser(@Valid CreateUserParam createUserParam) {
		logger.info("Start to create user, mobileNumber={}",createUserParam.getMobileNumber());
		UserDomain userDomain = new UserDomain();
		Date curDate = new Date();

		userDomain.setCreateDate(curDate);
		userDomain.setIsEmailVerified(ConstBoolean.FALSE);
		userDomain.setIsMobileVerified(ConstBoolean.TRUE);
		userDomain.setLastAccessDate(curDate);

		String userCode = generateUserCode();
		
		String loginPwd = createUserParam.getLoginPwd();
		loginPwd = password(loginPwd,userCode,curDate);
		userDomain.setLoginPwd(loginPwd);

		userDomain.setMobileNumber(createUserParam.getMobileNumber());
		userDomain.setRegisterChannel(createUserParam.getRegisterChannel());
		userDomain.setRegisterIp(createUserParam.getRegisterIp());
		userDomain.setUpdateDate(curDate);
		userDomain.setUserAccount(StringTools.concat(userCode, "@account"));
		userDomain.setUserActiveness(100);
		userDomain.setUserAgent(createUserParam.getUserAgent());
		userDomain.setUserCode(userCode);
		userDomain.setUserEmail(StringTools.concat(userCode, "@email"));
		 userDomain.setUserLevel(1);
		// userDomain.setUserName(userName);
		// userDomain.setUserNickname(userNickname);
		// userDomain.setUserSex(userSex);
		userDomain.setUserType(100);
		userDomain.setUserStatus(ConstUserStatus.NORMAL);
		userDomain.setUserStatusChangeDate(curDate);
		userDomain.setId(pkGenService.genPk());

		try {
			userMapper.insert(userDomain);
		} catch (DuplicateKeyException ex) {
			throw new MobileNumberRegisteredRuntimeException();
		}

		logger.info("Succeed to create user, mobileNumber={}",createUserParam.getMobileNumber());
	}

	
	
	@Valid
	public void resetPassword(@Valid ResetPasswordParam resetPasswordParam){
		String veriCode = resetPasswordParam.getVeriCode();		
		String mobileNumber = resetPasswordParam.getMobileNumber();
		String newPassword = resetPasswordParam.getNewPassword();
		if (!smsService.checkVeriCode(mobileNumber, veriCode)){
			throw new UnMatchedVeriCodeRuntimeException();
		}
		
		UserDomain userDomain = queryUserByMobileNumber(mobileNumber);
		if (userDomain == null){
			throw new UserNotFoundRuntimeException();
		}
		
		/**
		 * 更新密码
		 */
		updatePassword(userDomain,newPassword);
	}
	
	@Valid
	public void updatePassword(@Valid UpdatePasswordParam  updatePasswordParam){
		String mobileNumber = updatePasswordParam.getMobileNumber();
		String oldPassword = updatePasswordParam.getOldPassword();
		String newPassword = updatePasswordParam.getNewPassword();
		UserDomain userDomain = loginService.checkPassword(mobileNumber, oldPassword);
		
		updatePassword(userDomain , newPassword);
	}
	
	
	private void updatePassword(UserDomain userDomain , String newPassword){
		UserDomain updatDomain =  new UserDomain();
		
		updatDomain.setLoginPwd(password(newPassword,userDomain.getUserCode(),userDomain.getCreateDate()));
		updatDomain.setUpdateDate(new Date());
		updatDomain.setLastAccessDate(userDomain.getUpdateDate());
		updatDomain.setId(userDomain.getId());
		userMapper.updateWithoutNull(updatDomain);
	}
	
	private String generateUserCode() {
		/**
		 * 8位及8位以上的数字构成的字符串
		 */
		return new Long(userCodeSequenceService.genPk() + 10000000).toString();

	}
}
