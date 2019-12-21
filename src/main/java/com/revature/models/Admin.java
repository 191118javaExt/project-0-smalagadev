package com.revature.models;

public class Admin extends Employee {
	
	public Admin() {
		super();
	}
	
	public Admin(int employeeId, String first, String last, String user, String password, String role) {
		super(employeeId, first, last, user, password, role);
	}
	
	public Admin(Employee e) {
		super(e.getEmployeeId() , e.getFirst_name(), e.getLast_name(), e.getUsername(), e.getPassword(), e.getRole());
	}
}
