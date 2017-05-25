package com.hawk.ecom.mall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.mall.persist.mapperex.MallRoleUserExMapper;

@Service
public class AuthService {
	
	@Autowired
	private MallRoleUserExMapper mallRoleUserExMapper;
		
	public  boolean hasAnyRole(String userCode,List<String> roles){
		return mallRoleUserExMapper.hasAnyRole(userCode, roles)>0;
	}

}
