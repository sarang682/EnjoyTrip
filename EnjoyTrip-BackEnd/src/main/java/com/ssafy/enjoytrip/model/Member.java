package com.ssafy.enjoytrip.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private String userId;
	private String userName;
	private String userPassword;
	private String emailId;
	private String emailDomain;
	private String joinDate;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getEmailDomain() {
		return emailDomain;
	}
	
	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}
	
	public String getJoinDate() {
		return joinDate;
	}
	
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
}
