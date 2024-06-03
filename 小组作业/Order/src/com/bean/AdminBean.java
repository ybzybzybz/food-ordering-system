package com.bean;

public class AdminBean {

	
	private String account;
	private String password;
	private String type;
	public AdminBean(String account, String password, String type) {
		super();
		this.account = account;
		this.password = password;
		this.type = type;
	}
	

	
	public AdminBean() {
		super();
	}



	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
