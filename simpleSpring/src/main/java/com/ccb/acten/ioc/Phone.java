package com.ccb.acten.ioc;

public class Phone {
	
	private String brand,type;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	
	public Phone(){
		this.brand = "unknown";
		this.type = "unknown";
	}
	
	public Phone(String brand){
		this.brand = brand;
	}
	
	public Phone(String brand ,String type){
		this.brand = brand;
		this.type = type;
	}
}
