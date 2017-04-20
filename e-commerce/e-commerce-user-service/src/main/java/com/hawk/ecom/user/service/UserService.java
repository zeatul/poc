package com.hawk.ecom.user.service;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hawk.ecom.pub.constant.ConstBoolean;
import com.hawk.ecom.user.constant.ConstUserStatus;
import com.hawk.ecom.user.exception.MobileNumberRegisteredRuntimeException;
import com.hawk.ecom.user.persist.domain.UserDomain;
import com.hawk.ecom.user.persist.mapper.UserMapper;
import com.hawk.ecom.user.request.CreateUserParam;
import com.hawk.framework.dic.validation.annotation.Valid;
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
	private UserMapper userMapper;

	public UserDomain queryUserByMobileNumber(String mobileNumber) {
		MybatisParam params = new MybatisParam().put("mobileNumber", mobileNumber);
		return MybatisTools.single(userMapper.loadDynamic(params));
	}

	@Valid
	public void createUser(@Valid CreateUserParam createUserParam) {
		UserDomain userDomain = new UserDomain();
		Date curDate = new Date();

		userDomain.setCreateDate(curDate);
		userDomain.setIsEmailVerified(ConstBoolean.FALSE);
		userDomain.setIsMobileVerified(ConstBoolean.TRUE);
		userDomain.setLastAccessDate(curDate);

		String userCode = generateUserCode();
		/**
		 * TODO:签名
		 */
		String loginPwd = createUserParam.getLoginPwd();
		userDomain.setLoginPwd(loginPwd);

		userDomain.setMobileNumber(createUserParam.getMobileNumber());
		// userDomain.setRegisterChannel(registerChannel);
		userDomain.setRegisterIp(createUserParam.getRegisterIp());
		userDomain.setUpdateDate(curDate);
		userDomain.setUserAccount(StringTools.concat(userCode, "@account.ecom"));
		// userDomain.setUserActiveness(userActiveness);
		userDomain.setUserAgent(createUserParam.getUserAgent());
		userDomain.setUserCode(userCode);
		userDomain.setUserEmail(StringTools.concat(userCode, "@mail.ecom"));
		// userDomain.setUserLevel(userLevel);
		// userDomain.setUserName(userName);
		// userDomain.setUserNickname(userNickname);
		// userDomain.setUserSex(userSex);
		userDomain.setUserStatus(ConstUserStatus.NORMAL);
		userDomain.setUserStatusChangeDate(curDate);
		//
		userDomain.setId(pkGenService.genPk());

		try {
			userMapper.insert(userDomain);
		} catch (DuplicateKeyException ex) {
			throw new MobileNumberRegisteredRuntimeException();
		}

	}

	private String generateUserCode() {
		/**
		 * 8位及8位以上的数字构成的字符串
		 */
		return new Long(userCodeSequenceService.genPk() + 10000000).toString();

	}
}
