package com.revature.models;

public class User {
	private int customerId;
	private String first_name;
	private String last_name;
	private String username; 
	private boolean approved;
	private double checking;
	private double savings;
	
	public User() {
		super();
	}
	
	public User(String first, String last, String user) {
		this.first_name = first;
		this.last_name = last;
		this.username = user;	
		this.approved = false;
		this.checking = 0;
		this.savings = 0;
		
	}
	
	public User(int id, String first, String last, String user, boolean approved, double checking, double savings) {
		this.customerId = id;
		this.first_name = first;
		this.last_name = last;
		this.username = user;	
		this.approved = approved;
		this.checking = checking;
		this.savings = savings;
	}
	
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public double getChecking() {
		return checking;
	}

	public void setChecking(double checking) {
		this.checking = checking;
	}

	public double getSavings() {
		return savings;
	}

	public void setSavings(double savings) {
		this.savings = savings;
	}

	
	
	
}
