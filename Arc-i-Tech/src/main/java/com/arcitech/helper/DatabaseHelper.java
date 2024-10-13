/**
 * 
 */
package com.arcitech.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author Ajay G.
 */
public class DatabaseHelper {
	public static final String DB_NAME = "arcitech_db";
	private final Scanner sc;
	private Connection conn;

	private DatabaseHelper() {
		this(new Scanner(System.in));
	}

	private DatabaseHelper(Scanner sc) {
		this.sc = sc;
		this.createConnection();
	}

	public static void start(Scanner sc) throws Exception {

		int choice = 0;
		DatabaseHelper databaseHelper = new DatabaseHelper(sc);
		do {
			System.out.println();
			System.out.println("1. Create Database");
			System.out.println("2. Exit");
			System.out.print(" Enter your choice: ");
			choice = Integer.parseInt(databaseHelper.sc.nextLine());
			switch (choice) {
			case 1:
				System.out.println("\nCreating Database");
				databaseHelper.createDatabase();
				break;
			case 2:
				System.out.println("\nThank You!");
				break;
			default:
				System.out.println("Invalid choice!");
				System.out.println("Try Again!");
				break;
			}

		} while (choice != 2);
	}

	private void createConnection() {
		ApplicationPropertiesHelper propertiesHelper = ApplicationPropertiesHelper.getInstance();
		try {
			this.conn = DriverManager.getConnection(propertiesHelper.getProperty("spring.datasource.url"),
					propertiesHelper.getProperty("spring.datasource.username"),
					propertiesHelper.getProperty("spring.datasource.password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDatabase() {
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DatabaseHelper.DB_NAME);
			System.out.println("Database created successfully...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
