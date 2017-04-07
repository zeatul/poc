package com.hawk.ecom.svp.service;

import java.util.Date;

import com.hawk.framework.dic.validation.ValidateException;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;

public class AOPTestService {
	
	@Valid
	public void exec(@NotEmpty("mobileNumber") String mobileNumber) throws ValidateException{
		System.out.println(mobileNumber);
		System.out.println("AOPTestService");
	}
	
	public static class ComplexParam{
		
		public String getMobilePhone() {
			return mobilePhone;
		}

		public void setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		@NotEmpty("mobileNumber")
		private String mobilePhone;
		
//		@NotNull("age")
		private Integer age;
		
		@NotNull("createDate")
		private Date date;
	}
	
	@Valid
	public void exec(@Valid ComplexParam param) throws ValidateException{
		System.out.println("AOPTestService.exec2 ComplexParam");
	}

}
