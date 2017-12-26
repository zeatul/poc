package com.ccb.acten.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AutoInjectPhoneFactory {
	
	@Autowired
	@Qualifier("phone1")
	private Phone phone1;
	
	public AutoInjectPhoneFactory(){
		
	}

}
