package com.ccb.acten.ioc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
public class AutoCreatePhoneFactory {	
	@Autowired
	@Qualifier("phone2")
	private Phone phone2;	
	public AutoCreatePhoneFactory(){		
	}
	
	@Override
	public String toString() {
		return "brand="+phone2.getBrand()+ "type=" + phone2.getType();
	}
}
