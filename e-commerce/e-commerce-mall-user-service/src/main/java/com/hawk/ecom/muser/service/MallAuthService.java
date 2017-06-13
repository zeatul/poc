package com.hawk.ecom.muser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.muser.persist.mapperex.MallRoleUserExMapper;



@Service
public class MallAuthService {
	
	@Autowired
	private MallRoleUserExMapper mallRoleUserExMapper;
		
	public  boolean hasAnyRole(String userCode,List<String> roles){
		return mallRoleUserExMapper.hasAnyRole(userCode, roles)>0;
	}

}
