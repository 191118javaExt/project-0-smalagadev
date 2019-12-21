package com.revature.models;

public class Employee {
	private int employeeId;
	private String first_name;
	private String last_name;
	private String username;
	private String password;
	private String role;
	
	public Employee() {
		super();
	}
	
	public Employee(int employeeId, String first, String last, String user, String password, String role) {
		this.employeeId = employeeId;
		this.first_name = first;
		this.last_name = last;
		this.username = user;
		this.password = password;
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
