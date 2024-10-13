/**
 * 
 */
package com.arcitech;

import java.util.Scanner;

import com.arcitech.helper.ApplicationPropertiesHelper;
import com.arcitech.helper.DatabaseHelper;

/**
 * 
 */
public class HelperApplication {
	public static void main(String[] args) throws Exception {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println();
			System.out.println("1. Application Properties Help");
			System.out.println("2. Database Help");
			System.out.println("3. Exit");
			System.out.print(" Enter your choice: ");
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				System.out.println("\nCalling Application Properties Helper");
				ApplicationPropertiesHelper.start(sc);
				break;
			case 2:
				System.out.println("\nCalling Database Helper");
				DatabaseHelper.start(sc);
				break;
			case 3:
				System.out.println("\nThank You!");
				break;
			default:
				System.out.println("Invalid choice!");
				System.out.println("Try Again!");
				break;
			}

		} while (choice != 3);
		sc.close();
	}
}
