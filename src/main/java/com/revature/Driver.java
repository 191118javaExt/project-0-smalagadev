package com.revature;

import java.util.Scanner;

import com.revature.services.*;

public class Driver {

	public static void main(String[] args) {
		AdminService as = new AdminService();
		EmployeeService es = new EmployeeService();
		UserService us = new UserService();
		
		System.out.println("Welcome to your online Banking.\n");
		Scanner in = new Scanner(System.in);
		String option; 
		boolean running = true;
		// Default Texts
		String divider = "\n===================";
		String optionText = "\nSelect from you options:\n";
		

		
		do {
			System.out.println(divider);
			System.out.println(optionText);
			System.out.println("1. Employee Login");
			System.out.println("2. Customer Login");
			System.out.println("3. Sign up");
			System.out.println("4. Exit");
			
		
			try{
				option = in.nextLine();
				switch(option) {
				case "1":
					System.out.println(divider);
					System.out.println("Employee Login");
					es.login();
					es.options();
					break;
				case "2":
					System.out.println(divider);
					System.out.println("Customer Login");
					us.login();
					us.options();
					break;
				case "3":
					System.out.println(divider);
					System.out.println("Sign up");
					us.signup();
					break;
				case "4":

					running = false;
					break;
				default:
					System.out.println("Use the following options above to make a selection.");
				}
			}
			catch (Exception e) {
				System.out.println("Please enter a valid number to make a selection.");
				
			}
			
		}while(running);
		
		in.close();
		System.out.println(divider);
		System.out.println("\nThank you for using our service.");
	}

}
