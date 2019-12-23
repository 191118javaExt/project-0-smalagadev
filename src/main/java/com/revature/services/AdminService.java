package com.revature.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Admin;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.AdminDAO;
import com.revature.repositories.AdminDAOImpl;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.EmployeeDAOImpl;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class AdminService {
	
	private AdminDAO repository = null;
	private EmployeeDAO e_repository = new EmployeeDAOImpl();
	private UserDAO u_repository = new UserDAOImpl();
	private Scanner scan = new Scanner(System.in);
	
	public AdminService() {
		repository = new AdminDAOImpl();
	}
	
	public AdminService(AdminDAO dao) {
		repository = dao;
	}
	
	public List<User> findAllUsers(){
		return u_repository.findAll();
	}
	
	public List<Employee> findAllEmployees(){
		return e_repository.findAll();
	}
	
	public boolean insert(User u, String password) {
		return u_repository.insert(u, password);
	}
	
	public Admin login() {
		String username;
		String password;
		Admin user;
		
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
	
	public boolean menu(Admin a) {
		Scanner in = new Scanner(System.in);
		String option = "";
		boolean running = true;
		// Default Texts
		String divider = "\n===================";
		String optionText = "\nSelect from you options:\n";
		
		do {
			System.out.println(divider);
			System.out.println(optionText);
			System.out.println("1. View Customers\n2. Manage Applications\n3. Manage Accounts\n4. Sign out");
			
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
					System.out.println(divider);
					System.out.println("Manage Accounts");
					manageUsers();
					break;
				case "4":
					System.out.println("Signing out...");
					running = false;
					break;
				default:
					System.out.println("\nUse a number to make a selection.");
					option = in.nextLine();
				}
			}
			catch (Exception e) {
				System.out.println("\nUse a number to make a selection.");
			}
			
		}while(running);
		in.close();
		return false;
	}
	

	public void manageUsers() {
		UserService us = new UserService();
		int id;
		String option;
		List<User> users = findAllUsers();
		
		System.out.println("Customer ID\tFirst Name\tLast Name\tChecking\tSavings");
		
		for(User user : users) {
			if(user.isApproved()) {
				System.out.println(user.getCustomerId() + "\t\t" + user.getFirst_name() + "\t\t" + 
            		user.getLast_name() + "\t\t$" + user.getChecking() + "\t\t$" + user.getSavings());
			}
        }
		
		try {
			System.out.println("\nEnter a User ID to manage or enter any non-numerical value to exit: ");
			id = scan.nextInt();
			System.out.println("\nName: " + users.get(id).getFirst_name() + users.get(id).getLast_name() + "\t ID: " + users.get(id).getCustomerId());
			System.out.println("Checking: $ " + users.get(id).getChecking() + "\t Savings: $" + users.get(id).getSavings() + "\n");
			
			System.out.println("1. Edit first name\n2. Edit last name\n3. Withdraw from user\n4. Deposit to user"
					+ "\n5. Transfer between accounts\n6. Delete user\n7. Return to main menu");
			
			option = scan.nextLine();
			option = scan.nextLine();
			
			switch(option) {
			case "1":
				System.out.println("Edit first name:");
				users.get(id).setFirst_name(scan.nextLine());
				u_repository.update(users.get(id));
				break;
			case "2":
				System.out.println("Edit last name:");
				users.get(id).setLast_name(scan.nextLine());
				u_repository.update(users.get(id));
				break;
			case "3":
				System.out.println("Withdraw from user");
				us.withdraw(users.get(id));
				break;
			case "4":
				System.out.println("Deposit to user");
				us.deposit(users.get(id));
				break;
			case "5":
				System.out.println("Transfer between accounts");
				us.transfer(users.get(id));
				break;
			case "6":
				System.out.println("Delete user");
				u_repository.delete(users.get(id));
				System.out.println("User deleted");
				break;
			default:
				break;
			}
			
		}
		catch(InputMismatchException e) {
			System.out.println("Please enter a valid ID");
		}
		

	}
	public void viewApplications() {
		int id;
		List<User> users = findAllUsers();
		
		
		System.out.println("Customer ID\tFirst Name\tLast Name");
		
		for(User user : users) {
			if(!user.isApproved()) {
				System.out.println(user.getCustomerId() + "\t\t" + user.getFirst_name()+ "\t\t" + user.getLast_name());
			}
        }
		System.out.println("Enter an ID to approve: ");
		
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
