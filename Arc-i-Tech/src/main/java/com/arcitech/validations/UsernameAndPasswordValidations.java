package com.arcitech.validations;

public class UsernameAndPasswordValidations {

	private UsernameAndPasswordValidations() {
		throw new UnsupportedOperationException("Unsupport UserValidations");
	}

	public static boolean checkloginValidations(String username, String password) {
		return validUserName(username) && validPassword(password);
	}

	public static boolean validUserName(String username) {
		if (username == null || username.isEmpty()) {
			return false;
		}
		return username.matches("[a-zA-Z0-9]{5,15}");
	}

	public static boolean validPassword(String password) {
		if (password == null || password.isEmpty()) {
			return false;
		}
		return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,20}$");
	}
}
