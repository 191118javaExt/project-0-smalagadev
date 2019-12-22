package com.revature.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserService {
	UserDAO repository = null;
	Scanner scan = new Scanner(System.in);
	
	public UserService() {
		repository = new UserDAOImpl();
	}
	
	public UserService(UserDAO dao) {
		repository = dao;
	}
	
	public boolean options(User u) {
		Scanner in = new Scanner(System.in);
		repository = new UserDAOImpl();
		String option;
		boolean running = true;
		// Default Texts
		String divider = "\n===================";
		String optionText = "\nSelect from your options:\n";
		
		if(!u.isApproved()) {
			System.out.println("Your account has not yet been approved.");
			System.out.println("Press Enter to continue.");
			in.nextLine();
			in.close();
			return true;
		}
		
		do {
			System.out.println(divider);
			System.out.println(optionText);
			System.out.println("1. Withdraw\n2. Deposit\n3. Transfer between accounts\n4. View Balance\n5. Sign out");		
		
			try{
				option = in.nextLine();
				switch(option) {
				case "1":
					System.out.println(divider);
					System.out.println("Withdaw:\n");
					withdraw(u);
					break;
					
				case "2":
					System.out.println(divider);
					System.out.println("Deposit\n");
					deposit(u);
					break;
					
				case "3":
					System.out.println(divider);
					System.out.println("Transfer\n");
					break;
					
				case "4":
					System.out.println(divider);
					System.out.println("View Balance\n");
					viewBalance(u);
					break;
					
				case "5":
					System.out.println("Signing out...");
					running = false;
					break;
					
				default:
					System.out.println("Use a number to make a selection.");
					option = in.nextLine();
				}
			}
			catch (Exception e) {
				System.out.println("Please use a number to make a selection.");
				option = "";
			}
			
		}while(running);
		in.close();
		return true;
	}
	
	public User login() {
		String username;
		String password;
		User user;
		
		for(int i = 3; i > 0; i--) {
			System.out.println("Enter your username: ");
			username = scan.nextLine().toLowerCase();

			
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
	
	public boolean signup() {
		System.out.println("Enter your first name: ");
		String first = scan.nextLine();
		System.out.println("Enter your last name: ");
		String last = scan.nextLine();
		System.out.println("Enter a username: ");
		String user = scan.nextLine().toLowerCase();
		System.out.println("Enter a password: ");
		String password = scan.nextLine();
		
		try {
			User u = new User(0, first, last, user, password); // 0 will be ignored when creating the record for the table.
			System.out.println("Thank you for your submission. You will have access to your account when our bank administator approves your application.");
			return repository.insert(u, password);
		}
		catch(Exception e) {
			System.out.println("Account could not be created. Please try again.");
			return false;
		}
		
		
	}
	
	public boolean withdraw(User u) {
		String option;
		double amount;
		boolean running = true;
		
		
		while(running){
			System.out.println("Select an account:\n");
			System.out.println("1. Checking\n2. Savings\n3. Return to menu.");
			option = scan.nextLine();
			
			switch(option) {
				case "1":
					
					System.out.println("\nChecking Account: $" + u.getChecking() );
					System.out.println("\nEnter amount: ");
					System.out.println("\n\n<- Enter any non-numerical key to go back.");
					try {						
						amount = scan.nextDouble();
						
						while(amount < 0 || amount > u.getChecking()) {
							if(amount < 0) {
								System.out.println("Please enter a valid amount: ");
							}
							
							if(amount > u.getChecking()) {
								System.out.println("Insufficient funds. Please enter a valid amount: ");
							}
							
							amount = scan.nextDouble();
						}
						u.setChecking(u.getChecking() - amount);
						System.out.println("\nNew balance: " + u.getChecking());
					}
					catch(InputMismatchException e) {
						System.out.println("Returning to main menu...");

					}										
					repository.update(u); //change update functions in userDAOImpl
					running = false;
					break;
					
				case "2":
					System.out.println("\nSavings Account: $" + u.getSavings() );
					System.out.println("\nEnter amount: ");
					System.out.println("\n\n<- Enter any non-numerical key to go back.");
					try {						
						amount = scan.nextDouble();
						
						while(amount < 0 || amount > u.getSavings()) {
							if(amount < 0) {
								System.out.println("Please enter a valid amount: ");
							}
							
							if(amount > u.getChecking()) {
								System.out.println("Insufficient funds. Please enter a valid amount: ");
							}
							
							amount = scan.nextDouble();
						}
						u.setSavings(u.getSavings() - amount);
						System.out.println("\nNew balance: " + u.getSavings());
					}
					catch(InputMismatchException e) {
						System.out.println("Returning to main menu...");

					}										
					repository.update(u); //change update functions in userDAOImpl
					running = false;
					break;
				case "3":
					running = false;
					break;	
				default:
					System.out.println("Enter a valid option.");			
			}
		}
		
		return false;
	}
	
	public boolean deposit(User u) {
		String option;
		double amount;
		boolean running = true;
		
		
		while(running){
			System.out.println("Select an account:\n");
			System.out.println("1. Checking\n2. Savings\n3. Return to menu.");
			option = scan.nextLine();
			
			switch(option) {
				case "1":
					
					System.out.println("\nChecking Account: $" + u.getChecking() );
					System.out.println("\nEnter amount: ");
					System.out.println("\n\n<- Enter any non-numerical key to go back.");
					try {						
						amount = scan.nextDouble();
						
						while(amount < 0) {
							if(amount < 0) {
								System.out.println("Please enter a valid amount: ");
							}
							
							amount = scan.nextDouble();
						}
						u.setChecking(u.getChecking() + amount);
						System.out.println("\nNew balance: " + u.getChecking());
					}
					catch(InputMismatchException e) {
						System.out.println("Returning to main menu...");

					}										
					repository.update(u); //change update functions in userDAOImpl
					running = false;
					break;
					
				case "2":
					System.out.println("\nSavings Account: $" + u.getSavings() );
					System.out.println("\nEnter amount: ");
					System.out.println("\n\n<- Enter any non-numerical key to go back.");
					try {						
						amount = scan.nextDouble();
						
						while(amount < 0) {
							if(amount < 0) {
								System.out.println("Please enter a valid amount: ");
							}
							
							amount = scan.nextDouble();
						}
						u.setSavings(u.getSavings() + amount);
						System.out.println("\nNew balance: " + u.getSavings());
					}
					catch(InputMismatchException e) {
						System.out.println("Returning to main menu...");

					}										
					repository.update(u); //change update functions in userDAOImpl
					running = false;
					break;
				case "3":
					running = false;
					break;	
				default:
					System.out.println("Enter a valid option.");			
			}
		}
		
		return false;
	}
	
	public void viewBalance(User u) {
		System.out.println(u.getFirst_name() + " " + u.getLast_name() + "\n");
		System.out.println("Checking: $" + u.getChecking());
		System.out.println("Savings:  $" + u.getSavings());

		System.out.println("\nPress Enter to continue to menu.");
		scan.nextLine();
	}
	
	
	public boolean transfer(double amount) {
		return false;
	}
}
