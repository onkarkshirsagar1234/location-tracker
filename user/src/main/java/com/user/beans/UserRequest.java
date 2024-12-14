package com.user.beans;

public class UserRequest {
	
	private String userName;
    private String userMobileNo;
    private String userEmailID;
    private String password;
    private boolean isAdmin ;
    private boolean isActive ;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMobileNo() {
		return userMobileNo;
	}
	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}
	public String getUserEmailID() {
		return userEmailID;
	}
	public void setUserEmailID(String userEmailID) {
		this.userEmailID = userEmailID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
