package com.revature;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Yoooooo, what up player, welcome to the bank of the streets.");
		Scanner in = new Scanner(System.in);
		int option = 0;
		

		do {
			System.out.println("So, whatchu tryna do? Hit them digits to pick ya potion.");
			System.out.println("1. Let's get this money");
			System.out.println("2. Ball out");
			System.out.println("3. Oooooh flex on em.");
			System.out.println("4. Get back out there and make that paper");
			try {
				option = in.nextInt();
			}
			catch (InputMismatchException e) {
				
			}
			
		}while(option != 4);
		
		System.out.println("Aight, I'll catch you later.");
		in.close();
	}

}
