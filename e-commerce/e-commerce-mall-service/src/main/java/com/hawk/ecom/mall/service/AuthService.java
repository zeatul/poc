package com.hawk.ecom.mall.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
		
	public  boolean hasAnyRole(String userCode,List<String> roleCodes){
		return false;
	}

}
