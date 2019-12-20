package com.revature.models;

public class Admin extends Employee {
	
	public Admin() {
		super();
	}
	
	public Admin(int employeeId, String first, String last, String user, String password) {
		super(employeeId, first, last, user, password);
	}
}
