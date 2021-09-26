package com.hsbc.entity;

import java.util.Date;

public class User {
	private int userId; // new field
	private String name;
	private String emailId;
	private String role;
	private String userName;
	private String password;
	private String lastLogin;
	private int employeeId;
	private String employeeName;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String name, String emailId, String role, String password, String lastLogin) {
		super();
		this.userId = userId;
		this.name = name;
		this.emailId = emailId;
		this.role = role;
		this.password = password;
		this.lastLogin = lastLogin;
		this.employeeId = userId;
		this.employeeName = name;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [employeeName=" + employeeName + ", employeeId=" + employeeId + ", emailId=" + emailId + ", role="
				+ role + "]";
	}

}
