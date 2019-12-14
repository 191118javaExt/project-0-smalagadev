package com.revature.models;

public class User {
	private int customerId;
	private String first_name;
	private String last_name;
	private String username; 
	private int creditScore; //between 500 1000
	private boolean approved;
	private double checking;
	private double savings;
	
	public User() {
		super();
	}
	
	public User(String first, String last, String user, int creditScore) {
		this.first_name = first;
		this.last_name = last;
		this.username = user;
		this.creditScore = creditScore;
		
//		this.customerId = random serial value
		this.approved = false;
		this.checking = 0;
		this.savings = 0;
		
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

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
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
