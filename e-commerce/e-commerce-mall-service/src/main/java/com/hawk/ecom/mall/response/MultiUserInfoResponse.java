package com.hawk.ecom.mall.response;

import java.util.Date;
import java.util.List;

import com.hawk.framework.pub.web.ResponseData;

public class MultiUserInfoResponse implements ResponseData{
	
	public MultiUserInfoResponse(List<UserInfo> userInfos) {
		setUserInfos(userInfos);
	}


	public List<UserInfo> getUserInfos() {
		return userInfos;
	}


	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
		this.count = userInfos == null ? 0 : userInfos.size();
	}


	/**
	 * 返回记录数量
	 * 
	 * @return
	 */
	public Integer getCount() {
		return this.count;
	}

	private int count = 0;
	
	
	private List<UserInfo> userInfos;
	
	
	public static class UserInfo{
		
		
		public String getUserCode() {
			return userCode;
		}



		public void setUserCode(String userCode) {
			this.userCode = userCode;
		}



		public String getMobileNumber() {
			return mobileNumber;
		}



		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}



		public String getUserName() {
			return userName;
		}



		public void setUserName(String userName) {
			this.userName = userName;
		}



		public String getUserSex() {
			return userSex;
		}



		public void setUserSex(String userSex) {
			this.userSex = userSex;
		}



		public Integer getUserStatus() {
			return userStatus;
		}



		public void setUserStatus(Integer userStatus) {
			this.userStatus = userStatus;
		}



		public Date getCreateDate() {
			return createDate;
		}



		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}



		/**
		 * 用户编号 user_code
		 */
		private String userCode;
		
		
		/**
		 * 手机号 mobile_number
		 */
		private String mobileNumber;
		
	
		
		/**
		 * 用户姓名 user_name
		 */
		private String userName;
		
		/**
		 * 用户性别 user_sex
		 */
		private String userSex;
		
		
		/**
		 * 用户状态 user_status
		 */
		private Integer userStatus;
		
	
		
		/**
		 * 创建日期 create_date
		 */
		private Date createDate;
		
		
	}

}
