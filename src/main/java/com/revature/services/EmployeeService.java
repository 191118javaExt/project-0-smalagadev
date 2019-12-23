package com.revature.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class EmployeeService {
	EmployeeDAO repository = null;
	UserDAO u_repository = new UserDAOImpl();
	Scanner scan = new Scanner(System.in);
	
	public EmployeeService() {
		repository = new EmployeeDAOImpl();
	}
	
	public EmployeeService(EmployeeDAO dao) {
		repository = dao;
	}
	
	public List<User> findAllUsers(){
		return u_repository.findAll();
	}
	
	public boolean insert(User u, String password) {
		return u_repository.insert(u, password);
	}
	
	public boolean menu(Employee e) {
		Scanner in = new Scanner(System.in);
		String option;
		boolean running = true;
		// Default Texts
		String divider = "\n===================";
		String optionText = "\nSelect from you options:\n";
		
		do {
			System.out.println(divider);
			System.out.println(optionText);
			System.out.println("1. View Customers");
			System.out.println("2. Manage Applications");
			System.out.println("3. Sign out");
			
			try{
				option = in.nextLine();
				switch(option) {
				case "1":
					System.out.println(divider);
					System.out.println("View Customers");
					viewUsers();
					break;
				case "2":
					System.out.println(divider);
					System.out.println("Manage Applications");
					viewApplications();
					break;
				case "3":
					System.out.println("Signing out...");
					running = false;
					break;
				default:
					System.out.println("\nUse a number to make a selection.");
					option = in.nextLine();
				}
			}
			catch (Exception ex) {
				System.out.println("\nUse a number to make a selection.");
				option = "";
			}
			
		}while(running);
		in.close();
		return false;
	}
	
	public Employee login() {
		String username;
		String password;
		Employee user;
		
		for(int i = 3; i > 0; i--) {
			System.out.println("Enter your username: ");
			username = scan.nextLine();

			
			System.out.println("Enter your password: ");
			password = scan.nextLine();
			
			try {
				user = repository.findByUsername(username);
				
				if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					System.out.println("Welcome " + user.getFirst_name() + " " + user.getLast_name() + ".");
					return user;
				}
				System.out.println("\nInvalid credentials. " + (i - 1) + " attempts remaining.\n");
			}
			catch(Exception e) {
				System.out.println("\nInvalid credentials. " + (i - 1) + " attempts remaining.\n");
			}
						
		}
		System.out.println("Too many attempts.");
		return null;
	}
	
	public void viewApplications() {
		int id;
		List<User> users = findAllUsers();
		System.out.println("\nCustomer ID\tFirst Name\tLast Name");
		
		for(User user : users) {
			if(!user.isApproved()) {
				System.out.println(user.getCustomerId() + "\t\t" + user.getFirst_name()+ "\t\t" + user.getLast_name());
			}
        }
		System.out.println("\nEnter an ID to approve or enter any non-numerical value to exit:");
		
		try {
			id = scan.nextInt();
			User u = u_repository.findById(id);
			u.setApproved(true);
			u_repository.update(u);
			System.out.println(u.getFirst_name() + " " + u.getLast_name() + " approved.");
		}
		catch(InputMismatchException e) {
			System.out.println("Please enter a valid ID");
		}
	}
	
	public void viewUsers(){
		List<User> users = findAllUsers();
		
		System.out.println("Customer ID\tFirst Name\tLast Name\tChecking\tSavings");
		
		for(User user : users) {
			if(user.isApproved()) {
				System.out.println(user.getCustomerId() + "\t\t" + user.getFirst_name() + "\t\t" + 
            		user.getLast_name() + "\t\t$" + user.getChecking() + "\t\t$" + user.getSavings());
			}
        }		
	}
	

}
