package com.hawk.ecom.user.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hawk.ecom.user.annotation.NotLogin;

public class UpdateUserInfoParam {
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer  userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	private String nickname;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	
	private String sex;
	
	@NotLogin
	private Integer userId;

}
