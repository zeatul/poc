package com.hawk.ecom.svp.service;

import com.hawk.framework.dic.validation.annotation.Valid;

public class AOPTestService {
	
	@Valid
	public void exec(){
		System.out.println("AOPTestService");
	}

}
