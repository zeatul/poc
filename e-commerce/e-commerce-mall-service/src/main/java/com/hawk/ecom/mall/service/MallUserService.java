package com.hawk.ecom.mall.service;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.mall.exception.MallUserNotFoundRuntimeException;
import com.hawk.ecom.mall.exception.UnMatchedMallPasswordRuntimeException;
import com.hawk.ecom.mall.persist.domain.MallLoginDomain;
import com.hawk.ecom.mall.persist.domain.MallUserDomain;
import com.hawk.ecom.mall.persist.mapper.MallUserMapper;
import com.hawk.ecom.mall.request.MallLoginParam;
import com.hawk.ecom.user.exception.UnMatchedPasswordRuntimeException;
import com.hawk.ecom.user.exception.UserNotFoundRuntimeException;
import com.hawk.ecom.user.persist.domain.UserDomain;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class MallUserService {
	
	@Autowired
	private MallUserMapper mallUserMapper;
	
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
			throw new UnMatchedMallPasswordRuntimeException();
		}
		return mallUserDomain;
	}
	
	@Valid
	public void login(@Valid MallLoginParam mallLoginParam){
		MallUserDomain mallUserDomain = checkPassword(mallLoginParam.getMobileNumber(),mallLoginParam.getLoginPwd());
		MallLoginDomain mallLoginDomain = new MallLoginDomain();
		Date curDate = new Date();
	}
}
