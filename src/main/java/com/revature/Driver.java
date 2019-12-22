package com.revature;

import java.util.Scanner;

import com.revature.models.Admin;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.services.*;

public class Driver {

	public static void main(String[] args) {
		AdminService as = new AdminService();
		EmployeeService es = new EmployeeService();
		UserService us = new UserService();
		Admin a;
		Employee e;
		User u;
		
		System.out.println("Welcome to your online Banking.\n");
		Scanner in = new Scanner(System.in);
		String option; 
		boolean running = true;
		// Default Texts
		String divider = "\n===================";
		String optionText = "\nSelect from you options:\n";
		

		
		while(running){
			System.out.println(divider);
			System.out.println(optionText);
			System.out.println("1. Employee Login\n2. Customer Login\n3. Sign up\n4. Exit");
			
		
			try{
				option = in.nextLine();
				switch(option) {
				case "1":
					System.out.println(divider);
					System.out.println("Employee Login");
					e = es.login();
					if(e != null) {
						if(e.getRole().equals("Bank Admin")) {
							a = new Admin(e);
							running = as.menu(a);
						}
						else {
							running = es.menu(e);
						}
					}
					break;
					
				case "2":
					System.out.println(divider);
					System.out.println("Customer Login");
					u = us.login();
					if(u != null) {
						running = us.menu(u);
					}	
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
			catch (Exception ex) {
				System.out.println("\nPlease enter a valid number to make a selection.");
			}
		}
		
		in.close();
		System.out.println(divider);
		System.out.println("\nThank you for using our service.");
		System.out.println(divider);
	}

}
