package com.hawk.ecom.svp.service;

import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;

public class AOPTestService {
	
	@Valid
	public void exec(@NotEmpty("mobileNumber") String mobileNumber){
		System.out.println(mobileNumber);
		System.out.println("AOPTestService");
	}

}
