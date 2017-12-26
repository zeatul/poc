package com.ccb.acten.ioc;

public class PhoneFactory {	

	private Phone phone;	
	
	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	
	public PhoneFactory(){		
	}
	
	public PhoneFactory(Phone phone){
		this.phone = phone;
	}
}
