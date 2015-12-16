package com.brothers.sharegoods.model;

public class UserBean {
	private int userId;
	private String email;
	private String password;
	private String userName;
	private String sex;
	private String country;
	private String city;
	
	public int getUserId() {
		return userId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getSex() {
		return sex;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String toString() {
		return "" + 1;
	}
}
